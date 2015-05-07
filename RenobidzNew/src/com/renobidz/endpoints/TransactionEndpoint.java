package com.renobidz.endpoints;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Named;

import JavaAPI.HttpsPostRequest;
import JavaAPI.Purchase;
import JavaAPI.Receipt;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.endpoints.dto.CreditCardDTO;
import com.renobidz.endpoints.dto.TransactionDTO;
import com.renobidz.endpoints.util.converters.CreditCardConverter;
import com.renobidz.endpoints.util.converters.TransactionConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.TransactionDAO;
import com.renobidz.store.entity.CreditCard;
import com.renobidz.store.entity.Transaction;
import com.renobidz.store.entity.util.MONERIS;

/**
 * Created by lmgagne on 15-01-26.
 *
 * Transaction Cloud Endpoints
 *
 */

@Api(name = "transactionEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class TransactionEndpoint extends AbstractEndpoint<TransactionDAO>{
    private static final Logger logger = Logger.getLogger(TransactionEndpoint.class.getName());

    @Override
    protected TransactionDAO getDAO() {
        return TransactionDAO.getInstance();
    }

    protected TransactionConverter getConverter(){
        return TransactionConverter.getInstance();
    }

    protected CreditCardConverter getConverterCreditCard(){
        return CreditCardConverter.getInstance();
    }

    /**
     * @param creditcardDTO, amount
     * @throws DatabaseException
     * @throws IOException
     *
     * create new transaction
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
    public Response create(CreditCardDTO creditcardDTO, @Named("amount") String amount) throws DatabaseException, IOException {
        logger.info("creating the transaction entity");
        Date createDate = new Date();

        CreditCard creditCard = getConverterCreditCard().toEntity(creditcardDTO);
        String order_id = "order_" + createDate.getTime();
        String cust_id = "customer1";
        //String pan = "5454545454545454";
        String pan = creditCard.getCardNumber();
        //String expdate = "1111";
        String expdate = creditCard.getExpMonth() + creditCard.getExpYear().substring(2);
        String crypt = "7";

        Purchase p = new Purchase(order_id, amount, pan, expdate, crypt);

        //Dynamic Descriptor
        p.setDynamicDescriptor("12345");

        //Using status check constructor
        HttpsPostRequest mpgReq =
                new HttpsPostRequest(MONERIS.host, MONERIS.store_id, MONERIS.api_token, p, false);

        try {
            Receipt receipt = mpgReq.getReceipt();
            Transaction transaction = new Transaction();

            transaction.setTransAmount(receipt.getTransAmount());
            transaction.setTxn_number(receipt.getTxnNumber());
            transaction.setReceiptId(receipt.getReceiptId());
            transaction.setTransType(receipt.getTransType());
            transaction.setResponseCode(receipt.getResponseCode());

            //**** create an invoice when successful??
            //***************************************

            /*System.out.println("CardType = " + receipt.getCardType());
            System.out.println("ReferenceNum = " + receipt.getReferenceNum());
            System.out.println("ISO = " + receipt.getISO());
            System.out.println("BankTotals = " + receipt.getBankTotals());
            System.out.println("Message = " + receipt.getMessage());
            System.out.println("AuthCode = " + receipt.getAuthCode());
            System.out.println("Complete = " + receipt.getComplete());
            System.out.println("TransDate = " + receipt.getTransDate());
            System.out.println("TransTime = " + receipt.getTransTime());
            System.out.println("Ticket = " + receipt.getTicket());
            System.out.println("TimedOut = " + receipt.getTimedOut());
            System.out.println("StatusCode = " + receipt.getStatusCode());
            System.out.println("StatusMessage = " + receipt.getStatusMessage());
            System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());*/

            getDAO().createWithID(transaction);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Response(STATUS.SUCCESS, "Transaction has been added successfully.");
    }

    /**
     * @param id
     * @return
     * @throws DatabaseException
     *
     * get the transaction entity by id
     */
    @ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public TransactionDTO get(@Named("id") Long id) throws DatabaseException {
        logger.info("loading the transaction entity by id : "+id);
        Transaction transaction = getDAO().getById(Transaction.class, id);
        return getConverter().toDTO(transaction);
    }

    /**
     * @param transactionDTO
     * @return
     * @throws DatabaseException
     *
     * update the transaction entity
     */
    @ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
    public Response update(TransactionDTO transactionDTO) throws DatabaseException {
        logger.info("updating the transaction entity");
        Transaction isExistingTransaction = getDAO().getById(Transaction.class, transactionDTO.getId());
        if(isExistingTransaction != null){
            getDAO().update(Transaction.class, isExistingTransaction.getId(), isExistingTransaction);
            return new Response(STATUS.SUCCESS, "Transaction updated successfully");
        }else{
            return new Response(STATUS.FAILURE, "Transaction doesn't exists");
        }
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the transaction entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the transaction entity by id : "+id);
        getDAO().delete(Transaction.class, id);
        return new Response(STATUS.SUCCESS, "Transaction deleted successfully");
    }
}

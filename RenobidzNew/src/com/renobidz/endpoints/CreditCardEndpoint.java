package com.renobidz.endpoints;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import JavaAPI.CardVerification;
import JavaAPI.HttpsPostRequest;
import JavaAPI.Receipt;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.endpoints.dto.CreditCardDTO;
import com.renobidz.endpoints.util.converters.CreditCardConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.CreditCardDAO;
import com.renobidz.store.entity.CreditCard;
import com.renobidz.store.entity.util.CREDITCARD;
import com.renobidz.store.entity.util.MONERIS;

/**
 * Created by lmgagne on 15-01-19.
 *
 * CreditCard Cloud Endpoints
 *
 */

@Api(name = "creditCardEndpoint", version = "v1", description = "Lets you manage credit cards associated to a user.", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class CreditCardEndpoint extends AbstractEndpoint<CreditCardDAO>{
    private static final Logger logger = Logger.getLogger(CreditCardEndpoint.class.getName());

    @Override
    protected CreditCardDAO getDAO() {
        return CreditCardDAO.getInstance();
    }

    protected CreditCardConverter getConverter(){
        return CreditCardConverter.getInstance();
    }

    /**
     * @param creditCardDTO: : a credit card
     * @throws DatabaseException
     * @throws IOException
     *
     * create new creditCard
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
    public Response create(CreditCardDTO creditCardDTO) throws DatabaseException, IOException {
        logger.info("creating the creditCard entity");
        CreditCard creditCard = getConverter().toEntity(creditCardDTO);
        creditCard.setIsValidated(Boolean.FALSE);

        //Validation possible only for VISA and MASTERCARD. AMEX are created without any validation.
        if (creditCard.getCardType().equals(CREDITCARD.VISA) || creditCard.getCardType().equals(CREDITCARD.MASTERCARD)) {
            Date date = new Date();

            //TODO Revise order_id
            //TODO should the credit card number encoded?
            String order_id = "TEST" + date.getTime();

            String pan = creditCard.getCardNumber();
            String expdate = creditCard.getExpMonth() + creditCard.getExpYear().substring(2);
            String crypt = "7";

            //AvsInfo avs = new AvsInfo ("123", "Edgar Street", "M1M1M1");
            //CvdInfo cvd = new CvdInfo ("1", "099");

            CardVerification cv = new CardVerification(order_id, pan, expdate, crypt);

            //cv.setAvsInfo (avs);
            //cv.setCvdInfo (cvd);

            HttpsPostRequest mpgReq = new HttpsPostRequest(MONERIS.host, MONERIS.store_id, MONERIS.api_token, cv);

            try {
                Receipt receipt = mpgReq.getReceipt();
                Integer responseCode = Integer.parseInt(receipt.getResponseCode());
                if (responseCode >= 0 && responseCode <= 49) {
                    creditCard.setIsValidated(Boolean.TRUE);
                }
            } catch (Exception e) {
                return new Response(STATUS.FAILURE, "Credit card number is invalid.");
            }
        }

        Long id = getDAO().createWithID(creditCard);
        if (id != null) {
            return new Response(STATUS.SUCCESS, "Credit Card has been successfully created.");
        } else {
            return new Response(STATUS.FAILURE, "Credit Card has NOT been created.");

        }
    }

    /**
     * @param userId: id of a specific user
     * @return List<CreditCardDTO>: list of all credit cards associated to a specific user
     * @throws DatabaseException
     *
     * listByUserId the creditCard entity by id
     */
    @ApiMethod(name = "listByUserId", path="listByUserId", httpMethod = HttpMethod.POST)
    public List<CreditCardDTO> listByUserId(@Named("userId") Long userId) throws DatabaseException {
        logger.info("loading all creditCard entity by user id : "+userId);
        List<CreditCard> list = getDAO().listByUserId(userId);
        return getConverter().toDTOsList(list);
    }

    /**
     * @param id: id specific credit card
     * @return CreditCardDTO: a specific credit card details
     * @throws DatabaseException
     *
     * get the creditCard entity by id
     */
    @ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public CreditCardDTO get(@Named("id") Long id) throws DatabaseException {
        logger.info("loading the creditCard entity by id : "+id);
        CreditCard creditCard = getDAO().getById(CreditCard.class, id);
        return getConverter().toDTO(creditCard);
    }

    /**
     * @param creditCardDTO: a credit card
     * @return Response: SUCCESS or FAILURE along with a message
     * @throws DatabaseException
     *
     * update the creditCard entity
     */
    @ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
    public Response update(CreditCardDTO creditCardDTO) throws DatabaseException {
        logger.info("updating the creditCard entity");
        CreditCard isExistingCreditCard = getDAO().getById(CreditCard.class, creditCardDTO.getId());

        if(isExistingCreditCard != null){
            isExistingCreditCard.setCardHolderName(creditCardDTO.getCardHolderName());
            isExistingCreditCard.setCardNumber(creditCardDTO.getCardNumber());
            isExistingCreditCard.setExpMonth(creditCardDTO.getExpMonth());
            isExistingCreditCard.setExpYear(creditCardDTO.getExpYear());
            isExistingCreditCard.setAvsNumber(creditCardDTO.getAvsNumber());
            isExistingCreditCard.setAvsStreet(creditCardDTO.getAvsStreet());
            isExistingCreditCard.setAvsZipCode(creditCardDTO.getAvsZipCode());
            isExistingCreditCard.setCvdCode(creditCardDTO.getCvdCode());

            getDAO().update(CreditCard.class, isExistingCreditCard.getId(), isExistingCreditCard);
            return new Response(STATUS.SUCCESS, "Credit Card updated successfully");
        }else{
            return new Response(STATUS.FAILURE, "Credit Card doesn't exists");
        }
    }

    /**
     * @param id: : id specific credit card
     * @throws DatabaseException
     *
     * delete the creditCard entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the creditCard entity by id : "+id);
        getDAO().delete(CreditCard.class, id);
        return new Response(STATUS.SUCCESS, "Credit Card deleted successfully");
    }

}

package com.renobidz.endpoints;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.endpoints.dto.SupplierDTO;
import com.renobidz.endpoints.util.converters.SupplierConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.SupplierDAO;
import com.renobidz.store.entity.Supplier;

/**
 * Created by lmgagne on 15-01-19.
 *
 * Supplier Cloud Endpoints
 *
 */

@Api(name = "supplierEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class SupplierEndpoint extends AbstractEndpoint<SupplierDAO>{
    private static final Logger logger = Logger.getLogger(SupplierEndpoint.class.getName());

    @Override
    protected SupplierDAO getDAO() {
        return SupplierDAO.getInstance();
    }

    protected SupplierConverter getConverter(){
        return SupplierConverter.getInstance();
    }

    /**
     * @param supplierDTO
     * @throws DatabaseException
     * @throws IOException
     *
     * create new supplier
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
    public Response create(SupplierDTO supplierDTO) throws DatabaseException, IOException {
        logger.info("creating the supplier entity");
        Supplier supplier = getConverter().toEntity(supplierDTO);
        getDAO().createWithID(supplier);
        return new Response(STATUS.SUCCESS, "Supplier has been added successfully.");
    }

    /**
     * @param id
     * @return
     * @throws DatabaseException
     *
     * get the supplier entity by id
     */
    @ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public SupplierDTO get(@Named("id") Long id) throws DatabaseException {
        logger.info("loading the supplier entity by id : "+id);
        Supplier supplier = getDAO().getById(Supplier.class, id);
        return getConverter().toDTO(supplier);
    }

    /**
     * @param supplierDTO
     * @return
     * @throws DatabaseException
     *
     * update the supplier entity
     */
    @ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
    public Response update(SupplierDTO supplierDTO) throws DatabaseException {
        logger.info("updating the supplier entity");
        Supplier isExistingSupplier = getDAO().getById(Supplier.class, supplierDTO.getId());
        if(isExistingSupplier != null){
            isExistingSupplier.setName(supplierDTO.getName());

            getDAO().update(Supplier.class, isExistingSupplier.getId(), isExistingSupplier);
            return new Response(STATUS.SUCCESS, "Supplier updated successfully");
        }else{
            return new Response(STATUS.FAILURE, "Supplier doesn't exists");
        }
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the supplier entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the supplier entity by id : "+id);
        getDAO().delete(Supplier.class, id);
        return new Response(STATUS.SUCCESS, "Supplier deleted successfully");
    }
}

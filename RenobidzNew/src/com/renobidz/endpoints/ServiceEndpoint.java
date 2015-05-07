package com.renobidz.endpoints;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.endpoints.dto.ServiceDTO;
import com.renobidz.endpoints.util.converters.ServiceConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.ServiceDAO;
import com.renobidz.store.entity.Service;

/**
 * Created by lmgagne on 15-01-19.
 *
 * Service Cloud Endpoints
 *
 */

@Api(name = "serviceEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class ServiceEndpoint extends AbstractEndpoint<ServiceDAO>{
    private static final Logger logger = Logger.getLogger(ServiceEndpoint.class.getName());

    @Override
    protected ServiceDAO getDAO() {
        return ServiceDAO.getInstance();
    }

    protected ServiceConverter getConverter(){
        return ServiceConverter.getInstance();
    }

    /**
     * @param serviceDTO
     * @throws DatabaseException
     * @throws IOException
     *
     * create new service
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
    public Response create(ServiceDTO serviceDTO) throws DatabaseException, IOException {
        logger.info("creating the service entity");
        Date date = new Date();

        Service service = getConverter().toEntity(serviceDTO);
        service.setIsActive(true);
        service.setActivationDate(date);

        getDAO().createWithID(service);
        return new Response(STATUS.SUCCESS, "Service has been added successfully.");
    }

    /**
     * @param userId
     * @return
     * @throws DatabaseException
     *
     * listByUserId the service entity by id
     */
    @ApiMethod(name = "listByUserId", path="listByUserId", httpMethod = HttpMethod.POST)
    public List<ServiceDTO> listByUserId(@Named("userId") Long userId) throws DatabaseException {
        logger.info("loading all service entity by user id : "+userId);
        List<Service> list = getDAO().listByUserId(userId);
        return getConverter().toDTOsList(list);
    }

    /**
     * @param id
     * @return
     * @throws DatabaseException
     *
     * get the service entity by id
     */
    @ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public ServiceDTO get(@Named("id") Long id) throws DatabaseException {
        logger.info("loading the service entity by id : "+id);
        Service service = getDAO().getById(Service.class, id);
        return getConverter().toDTO(service);
    }

    /**
     * @param serviceDTO
     * @return
     * @throws DatabaseException
     *
     * update the service entity
     */
    @ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
    public Response update(ServiceDTO serviceDTO) throws DatabaseException {
        logger.info("updating the service entity");
        Service isExistingService = getDAO().getById(Service.class, serviceDTO.getId());
        if(isExistingService != null){
            isExistingService.setName(serviceDTO.getName());
            isExistingService.setDescription(serviceDTO.getDescription());
            isExistingService.setPrice(serviceDTO.getPrice());
            isExistingService.setBillingPlan(serviceDTO.getBillingPlan());
            isExistingService.setIsActive(serviceDTO.getIsActive());
            isExistingService.setActivationDate(serviceDTO.getActivationDate());
            isExistingService.setDeactivationDate(serviceDTO.getDeactivationDate());

            getDAO().update(Service.class, isExistingService.getId(), isExistingService);
            return new Response(STATUS.SUCCESS, "Service updated successfully");
        }else{
            return new Response(STATUS.FAILURE, "Service doesn't exists");
        }
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the service entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the service entity by id : "+id);
        getDAO().delete(Service.class, id);
        return new Response(STATUS.SUCCESS, "Service deleted successfully");
    }
}

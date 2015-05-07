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
import com.renobidz.endpoints.dto.CompanyDTO;
import com.renobidz.endpoints.util.converters.CompanyConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.CompanyDAO;
import com.renobidz.store.entity.Company;

/**
 * @author Ankur
 * 
 * Company Cloud Endpoints
 * 
 */
@Api(name = "companyEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class CompanyEndpoint extends AbstractEndpoint<CompanyDAO>{
	private static final Logger logger = Logger.getLogger(CompanyEndpoint.class.getName());
	
	@Override
	protected CompanyDAO getDAO() {
		return CompanyDAO.getInstance();
	}
	
	protected CompanyConverter getConverter(){
		return CompanyConverter.getInstance();
	}
	
	/**
	 * @param user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * create new company 
	 */
	@ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
	public Response create(CompanyDTO companyDTO) throws DatabaseException, IOException {
		logger.info("creating the company entity");
		Company isExisting = getDAO().getByUserId(companyDTO.getUserId());
		if(isExisting == null){
			Company company = getConverter().toEntity(companyDTO);
			getDAO().createWithID(company);
		}else{
			Company company = getConverter().toEntity(companyDTO);
			company.setId(isExisting.getId());
			getDAO().update(Company.class, isExisting.getId(), company);
		}
		return new Response(STATUS.SUCCESS, "Company details has been updated successfully.");
	}
	
	/**
	 * @param id
	 * @return
	 * @throws DatabaseException
	 * 
	 * getByUserId the company entity by id
	 */
	@ApiMethod(name = "getByUserId", path="getByUserId", httpMethod = HttpMethod.POST)
    public CompanyDTO getByUserId(@Named("userId") Long userId) throws DatabaseException {
		logger.info("loading all company entity by user id : "+userId);
		Company company = getDAO().getByUserId(userId);
		return getConverter().toDTO(company);
    }
	
	/**
	 * @param id
	 * @return
	 * @throws DatabaseException
	 * 
	 * get the company entity by id
	 */
	@ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public CompanyDTO get(@Named("id") Long id) throws DatabaseException {
		logger.info("loading the company entity by id : "+id);
		Company company = getDAO().getById(Company.class, id);
		return getConverter().toDTO(company);
    }
	
	/**
	 * @param id
	 * @return
	 * @throws DatabaseException
	 * 
	 * update the company entity
	 */
	@ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
	public Response updateUser(CompanyDTO companyDTO) throws DatabaseException {
		logger.info("updating the company entity");
		Company isExisting = getDAO().getById(Company.class, companyDTO.getId());
		if(isExisting != null){
			//TODO: Not needed as of now
			//getDAO().update(Company.class, isExistingUser.getId(), isExistingUser);
			return new Response(STATUS.SUCCESS, "Company updated successfully");
		}else{
			return new Response(STATUS.FAILURE, "Company doesn't exists");
		}
    }
	
	/**
	 * @param id
	 * @throws DatabaseException
	 * 
	 * delete the company entity by id
	 */
	@ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
		logger.info("deleting the company entity by id : "+id);
		getDAO().delete(Company.class, id);
		return new Response(STATUS.SUCCESS, "Company deleted successfully");
    }
}

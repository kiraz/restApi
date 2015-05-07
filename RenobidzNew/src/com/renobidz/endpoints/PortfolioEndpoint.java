package com.renobidz.endpoints;

import java.io.IOException;
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
import com.renobidz.endpoints.dto.PortfolioDTO;
import com.renobidz.endpoints.util.converters.PortfolioConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.PortfolioDAO;
import com.renobidz.store.entity.Portfolio;

/**
 * @author Ankur
 * 
 * Portfolio Cloud Endpoints
 * 
 */
@Api(name = "portfolioEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class PortfolioEndpoint extends AbstractEndpoint<PortfolioDAO>{
	private static final Logger logger = Logger.getLogger(PortfolioEndpoint.class.getName());
	
	@Override
	protected PortfolioDAO getDAO() {
		return PortfolioDAO.getInstance();
	}
	
	protected PortfolioConverter getConverter(){
		return PortfolioConverter.getInstance();
	}
	
	/**
	 * @param user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * create new portfolio 
	 */
	@ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
	public Response create(PortfolioDTO dto) throws DatabaseException, IOException {
		logger.info("creating the portfolio entity");
		Portfolio portfolio = getConverter().toEntity(dto);
		getDAO().createWithID(portfolio);
		return new Response(STATUS.SUCCESS, "Portfolio has been added successfully.");
	}
	
	/**
	 * @param id
	 * @return
	 * @throws DatabaseException
	 * 
	 * getByUserId the portfolio entity by id
	 */
	@ApiMethod(name = "getByUserId", path="getByUserId", httpMethod = HttpMethod.POST)
    public List<PortfolioDTO> getByUserId(@Named("userId") Long userId) throws DatabaseException {
		logger.info("loading all portfolio entity by user id : "+userId);
		List<Portfolio> portfolios = getDAO().listByUserId(userId);
		return getConverter().toDTOsList(portfolios);
    }
	
	/**
	 * @param id
	 * @return
	 * @throws DatabaseException
	 * 
	 * get the portfolio entity by id
	 */
	@ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public PortfolioDTO get(@Named("id") Long id) throws DatabaseException {
		logger.info("loading the portfolio entity by id : "+id);
		Portfolio portfolio = getDAO().getById(Portfolio.class, id);
		return getConverter().toDTO(portfolio);
    }
	
	/**
	 * @param id
	 * @return
	 * @throws DatabaseException
	 * 
	 * update the portfolio entity
	 */
	@ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
	public Response updateUser(PortfolioDTO portfolioDTO) throws DatabaseException {
		logger.info("updating the portfolio entity");
		Portfolio isExisting = getDAO().getById(Portfolio.class, portfolioDTO.getId());
		if(isExisting != null){
			//TODO: Not needed as of now
			//getDAO().update(Portfolio.class, isExisting.getId(), isExisting);
			return new Response(STATUS.SUCCESS, "Portfolio updated successfully");
		}else{
			return new Response(STATUS.FAILURE, "Portfolio doesn't exists");
		}
    }
	
	/**
	 * @param id
	 * @throws DatabaseException
	 * 
	 * delete the portfolio entity by id
	 */
	@ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
		logger.info("deleting the portfolio entity by id : "+id);
		getDAO().delete(Portfolio.class, id);
		return new Response(STATUS.SUCCESS, "Portfolio deleted successfully");
    }
}

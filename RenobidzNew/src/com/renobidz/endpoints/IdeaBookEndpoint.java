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
import com.renobidz.endpoints.dto.IdeaBookDTO;
import com.renobidz.endpoints.util.converters.IdeaBookConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.IdeaBookDAO;
import com.renobidz.store.entity.IdeaBook;

/**
 * Created by lmgagne on 15-01-26.
 *
 * IdeaBook Cloud Endpoints
 *
 */

@Api(name = "ideaBookEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class IdeaBookEndpoint extends AbstractEndpoint<IdeaBookDAO>{
    private static final Logger logger = Logger.getLogger(IdeaBookEndpoint.class.getName());

    @Override
    protected IdeaBookDAO getDAO() {
        return IdeaBookDAO.getInstance();
    }

    protected IdeaBookConverter getConverter(){
        return IdeaBookConverter.getInstance();
    }

    /**
     * @param ideaBookDTO
     * @throws DatabaseException
     * @throws IOException
     *
     * create new ideaBook
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
    public Response create(IdeaBookDTO ideaBookDTO) throws DatabaseException, IOException {
        logger.info("creating the ideabook entity");
        IdeaBook ideaBook = getConverter().toEntity(ideaBookDTO);
        getDAO().createWithID(ideaBook);
        return new Response(STATUS.SUCCESS, "Supplier has been added successfully.");
    }

    /**
     * @param userId
     * @return
     * @throws DatabaseException
     *
     * listByUserId the ideaBook entity by id
     */
    @ApiMethod(name = "listByUserId", path="listByUserId", httpMethod = HttpMethod.POST)
    public List<IdeaBookDTO> listByUserId(@Named("userId") Long userId) throws DatabaseException {
        logger.info("loading all ideaBook entity by user id : "+userId);
        List<IdeaBook> list = getDAO().listByUserId(userId);
        return getConverter().toDTOsList(list);
    }

    /**
     * @param id
     * @return
     * @throws DatabaseException
     *
     * get the ideaBook entity by id
     */
    @ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public IdeaBookDTO get(@Named("id") Long id) throws DatabaseException {
        logger.info("loading the ideaBook entity by id : "+id);
        IdeaBook ideaBook = getDAO().getById(IdeaBook.class, id);
        return getConverter().toDTO(ideaBook);
    }

    /**
     * @param ideaBookDTO
     * @return
     * @throws DatabaseException
     *
     * update the ideaBook entity
     */
    @ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
    public Response update(IdeaBookDTO ideaBookDTO) throws DatabaseException {
        logger.info("updating the ideaBook entity");
        IdeaBook isExistingIdeaBook = getDAO().getById(IdeaBook.class, ideaBookDTO.getId());
        //TODO:
        
        return new Response(STATUS.FAILURE, "");
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the ideaBook entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the ideaBook entity by id : "+id);
        getDAO().delete(IdeaBook.class, id);
        return new Response(STATUS.SUCCESS, "Idea Book deleted successfully");
    }

}
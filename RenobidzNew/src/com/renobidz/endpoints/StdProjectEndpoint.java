package com.renobidz.endpoints;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.endpoints.dto.StdProjectDTO;
import com.renobidz.endpoints.util.converters.StdProjectConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.StdProjectDAO;
import com.renobidz.store.entity.StdProject;
import com.renobidz.store.entity.util.LANGUAGE;

/**
 * @author lmgagne
 *
 * StdProject Cloud Endpoints
 *
 */
@Api(name = "stdProjectEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class StdProjectEndpoint extends AbstractEndpoint<StdProjectDAO>{
    private static final Logger logger = Logger.getLogger(StdProjectEndpoint.class.getName());

    @Override
    protected StdProjectDAO getDAO() {
        return new StdProjectDAO();
    }

    protected StdProjectConverter getConverter(){
        return StdProjectConverter.getInstance();
    }

    /**
     * @param stdProjectDTO
     * @throws DatabaseException
     * @throws IOException
     *
     * create new StdProject
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, httpMethod = "POST")
    public Response create(StdProjectDTO stdProjectDTO) throws DatabaseException, IOException {
        logger.info("creating the stdProject entity");
        StdProject stdProject = getConverter().toEntity(stdProjectDTO);

        Long id = getDAO().createWithID(stdProject);

        if (id != null) {
            return new Response(STATUS.SUCCESS, "Standard Project successfully created with id=");
        }else{
            return new Response(STATUS.FAILURE, "Standard Project not created successfully");
        }
    }

    /**
     * @param language
     * @return list<StdProject>
     * @throws DatabaseException
     *
     * get all StdProject by language
     */
    @ApiMethod(name = Path.OperationUrl.GET)
    public List<StdProjectDTO> getByLang(@Named("language") LANGUAGE language) throws DatabaseException {
        logger.info("loading all StdProject entity by language="+language);
        List<StdProject> list = getDAO().getByLang(language);
        return getConverter().toDTOsList(list);
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the StdProject entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting StdProject entity by id="+id);
        getDAO().delete(StdProject.class, id);
        return new Response(STATUS.SUCCESS, "Standard Project deleted successfully");
    }
}

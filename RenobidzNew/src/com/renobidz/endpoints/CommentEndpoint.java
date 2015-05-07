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
import com.renobidz.endpoints.dto.CommentDTO;
import com.renobidz.endpoints.util.converters.CommentConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.CommentDAO;
import com.renobidz.store.entity.Comment;

/**
 * @author lmgagne
 *
 * Comment Cloud Endpoints
 *
 */
@Api(name = "commentEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class CommentEndpoint extends AbstractEndpoint<CommentDAO>{
    private static final Logger logger = Logger.getLogger(CommentEndpoint.class.getName());

    @Override
    protected CommentDAO getDAO() {
        return CommentDAO.getInstance();
    }

    protected CommentConverter getConverter(){
        return CommentConverter.getInstance();
    }

    /**
     * @param commentDTO
     * @throws DatabaseException
     * @throws IOException
     *
     * create new comment
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
    public Response create(CommentDTO commentDTO) throws DatabaseException, IOException {
        logger.info("creating the comment entity");
        Comment comment = getConverter().toEntity(commentDTO);
        getDAO().createWithID(comment);
        return new Response(STATUS.SUCCESS, "Comment has been added successfully.");
    }

    /**
     * @param questionId
     * @return
     * @throws DatabaseException
     *
     * listByUserId the comment entity by id
     */
    @ApiMethod(name = "listByQuestionId", path="listByQuestionId", httpMethod = HttpMethod.POST)
    public List<CommentDTO> listBySupplierId(@Named("questionId") Long questionId) throws DatabaseException {
        logger.info("loading all comment entity by question id : "+questionId);
        List<Comment> list = getDAO().listByQuestionId(questionId);
        return getConverter().toDTOsList(list);
    }

    /**
     * @param id
     * @return
     * @throws DatabaseException
     *
     * get the comment entity by id
     */
    @ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public CommentDTO get(@Named("id") Long id) throws DatabaseException {
        logger.info("loading the comment entity by id : "+id);
        Comment comment = getDAO().getById(Comment.class, id);
        return getConverter().toDTO(comment);
    }

    /**
     * @param commentDTO
     * @return
     * @throws DatabaseException
     *
     * update the comment entity
     */
    @ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
    public Response update(CommentDTO commentDTO) throws DatabaseException {
        logger.info("updating the comment entity");
        Comment isExistingComment = getDAO().getById(Comment.class, commentDTO.getId());
        if(isExistingComment != null){
            isExistingComment.setComment(commentDTO.getComment());
            isExistingComment.setCreationDate(commentDTO.getCreationDate());
            isExistingComment.setNbLikes(commentDTO.getNbLikes());
            getDAO().update(Comment.class, isExistingComment.getId(), isExistingComment);
            return new Response(STATUS.SUCCESS, "Comment updated successfully");
        }else{
            return new Response(STATUS.FAILURE, "Comment doesn't exists");
        }
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the comment entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the comment entity by id : "+id);
        getDAO().delete(Comment.class, id);
        return new Response(STATUS.SUCCESS, "Comment deleted successfully");
    }
}

package com.renobidz.endpoints;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.endpoints.dto.QuestionDTO;
import com.renobidz.endpoints.util.converters.QuestionConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.QuestionDAO;
import com.renobidz.store.entity.Question;

/** An endpoint class we are exposing */
@Api(name = "questionEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "entity.store.renobidz.com", ownerName = "entity.store.renobidz.com", packagePath=""))
public class QuestionEndpoint extends AbstractEndpoint<QuestionDAO>{
    private static final Logger logger = Logger.getLogger(QuestionEndpoint.class.getName());

    @Override
    protected QuestionDAO getDAO() {
        return new QuestionDAO();
    }

    protected QuestionConverter getConverter(){
        return QuestionConverter.getInstance();
    }

    /**
     * @param questionDTO
     * @throws DatabaseException
     * @throws IOException
     *
     * create new question
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, httpMethod = "POST")
    public Response create(QuestionDTO questionDTO) throws DatabaseException, IOException {
        logger.info("creating the question entity");
        Question question = getConverter().toEntity(questionDTO);

        Date creationDate = new Date();
        question.setCreationDate(creationDate);

        question.setNbLikes(0);
        question.setNbViewed(0);
        question.setNbComments(0);

        Long id = getDAO().createWithID(question);

        if (id != null){
            return new Response(STATUS.SUCCESS, "Question created successfully with id=" + id);
        }else{
            return new Response(STATUS.FAILURE, "ERROR: Question not created");
        }
    }

    /**
     * @param
     * @return
     * @throws DatabaseException
     *
     * list all question entity
     */
    @ApiMethod(name = "list", path="list", httpMethod = ApiMethod.HttpMethod.POST)
    public List<QuestionDTO> list() throws DatabaseException {
        logger.info("loading all question entity");

        List<Question> list = getDAO().list(Question.class);
        return getConverter().toDTOsList(list);
    }

    /**
     * @param id
     * @return
     * @throws DatabaseException
     *
     * increment # of likes of a question
     */
    @ApiMethod(name = "addLike", path="addLike", httpMethod = ApiMethod.HttpMethod.POST)
    public Response addLike(@Named("id") Long id) throws DatabaseException {
        logger.info("incrementing # of likes for question id : "+id);

        Question question = getDAO().getById(Question.class, id);
        question.setNbLikes(question.getNbLikes()+1);

        getDAO().update(Question.class, question.getId(), question);

        return new Response(STATUS.SUCCESS, "Address updated successfully");
    }

    /**
     * @param questionDTO
     * @return
     * @throws DatabaseException
     *
     * update the question entity
     */
    @ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = ApiMethod.HttpMethod.PUT)
    public Response update(QuestionDTO questionDTO) throws DatabaseException {
        logger.info("updating the question entity");
        Question isExistingQuestion = getDAO().getById(Question.class, questionDTO.getId());
        if(isExistingQuestion != null){
            isExistingQuestion.setTitle(questionDTO.getTitle());
            isExistingQuestion.setDescription(questionDTO.getDescription());
            isExistingQuestion.setNbViewed(questionDTO.getNbViewed());
            isExistingQuestion.setNbLikes(questionDTO.getNbLikes());
            isExistingQuestion.setNbComments(questionDTO.getNbComments());
            isExistingQuestion.setUserName(questionDTO.getUserName());

            getDAO().update(Question.class, isExistingQuestion.getId(), isExistingQuestion);
            return new Response(STATUS.SUCCESS, "Question updated successfully");
        }else{
            return new Response(STATUS.FAILURE, "Question doesn't exists");
        }
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the question entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = ApiMethod.HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the question entity by id : "+id);
        getDAO().delete(Question.class, id);
        return new Response(STATUS.SUCCESS, "Question deleted successfully");
    }
}
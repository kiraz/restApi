package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.Comment;
import com.renobidz.store.entity.Question;

/**
 * Created by lmgagne on 14-12-30.
 */
public class CommentDAO extends AbstractDAO {

    private static CommentDAO singleton = null;

    private CommentDAO() {

    }

    /**
     * @return
     *
     * Created Singleton Instance so that everytime a new object is not initialized into the memory
     *
     */
    public static CommentDAO getInstance() {
        if (singleton == null) {
            singleton = new CommentDAO();
        }
        return singleton;
    }

    /**
     * @param questionId
     * @return
     */

    public List<Comment> listByQuestionId(Long questionId) {
        return ofy().load().type(Comment.class).filter("questionId", Ref.create(Key.create(Question.class, questionId))).list();
    }
}

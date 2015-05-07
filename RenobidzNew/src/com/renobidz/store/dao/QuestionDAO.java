package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.Question;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 14-12-30.
 */
public class QuestionDAO extends AbstractDAO {

    /**
     * @param question
     * @return
     */
    public List<Question> getByUserId(Long id) {
        return ofy().load().type(Question.class).filter("user", Ref.create(Key.create(User.class, id))).list();
    }
}

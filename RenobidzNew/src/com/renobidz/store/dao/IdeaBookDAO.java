package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.IdeaBook;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 15-01-19.
 *
 * Datastore operations on CreditCard Entity
 *
 */
public class IdeaBookDAO extends AbstractDAO{

    private static IdeaBookDAO singleton = null;

    private IdeaBookDAO() {

    }

    /**
     * @return
     *
     * Created Singleton Instance so that everytime a new object is not initialized into the memory
     *
     */
    public static IdeaBookDAO getInstance() {
        if (singleton == null) {
            singleton = new IdeaBookDAO();
        }
        return singleton;
    }

    /**
     * @param id
     * @return
     */
    public List<IdeaBook> listByUserId(Long id) {
        return ofy().load().type(IdeaBook.class).filter("user", Ref.create(Key.create(User.class, id))).list();
    }

}

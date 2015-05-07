package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.CreditCard;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 15-01-19.
 *
 * Datastore operations on CreditCard Entity
 *
 */
public class CreditCardDAO extends AbstractDAO{

    private static CreditCardDAO singleton = null;

    private CreditCardDAO() {

    }

    /**
     * @return
     *
     * Created Singleton Instance so that everytime a new object is not initialized into the memory
     *
     */
    public static CreditCardDAO getInstance() {
        if (singleton == null) {
            singleton = new CreditCardDAO();
        }
        return singleton;
    }

    /**
     * @param id
     * @return
     */
    public List<CreditCard> listByUserId(Long id) {
        return ofy().load().type(CreditCard.class).filter("user", Ref.create(Key.create(User.class, id))).list();
    }

}

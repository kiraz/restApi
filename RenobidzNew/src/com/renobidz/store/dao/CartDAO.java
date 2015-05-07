package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.Cart;
import com.renobidz.store.entity.User;

/**
 * @author Ankur
 *
 * Datastore operations on Cart Entity
 *
 */
public class CartDAO extends AbstractDAO {

    private static CartDAO singleton = null;

    private CartDAO() {

    }

    /**
     * @return
     *
     * Created Singleton Instance so that everytime a new object is not initialized into the memory
     *
     */
    public static CartDAO getInstance() {
        if (singleton == null) {
            singleton = new CartDAO();
        }
        return singleton;
    }

    /**
     * @param userId
     * @return
     */
    public Cart getCartByUserId(Long userId) {
        return ofy().load().type(Cart.class).filter("userId", Ref.create(Key.create(User.class, userId))).first().now();
    }
}

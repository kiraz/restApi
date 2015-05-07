package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.Service;
import com.renobidz.store.entity.User;

/**
 * Created by lmgagne on 15-01-19.
 *
 * Datastore operations on Service Entity
 *
 */
public class ServiceDAO extends AbstractDAO{

    private static ServiceDAO singleton = null;

    private ServiceDAO() {

    }

    /**
     * @return
     *
     * Created Singleton Instance so that everytime a new object is not initialized into the memory
     *
     */
    public static ServiceDAO getInstance() {
        if (singleton == null) {
            singleton = new ServiceDAO();
        }
        return singleton;
    }

    /**
     * @param id
     * @return
     */
    public List<Service> listByUserId(Long id) {
        return ofy().load().type(Service.class).filter("userId", Ref.create(Key.create(User.class, id))).list();
    }

}

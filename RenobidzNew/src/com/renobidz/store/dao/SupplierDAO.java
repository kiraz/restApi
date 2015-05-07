package com.renobidz.store.dao;

import com.renobidz.store.common.util.AbstractDAO;

/**
 * Created by lmgagne on 15-01-19.
 *
 * Datastore operations on Supplier Entity
 *
 */
public class SupplierDAO extends AbstractDAO{

    private static SupplierDAO singleton = null;

    private SupplierDAO() {

    }

    /**
     * @return
     *
     * Created Singleton Instance so that everytime a new object is not initialized into the memory
     *
     */
    public static SupplierDAO getInstance() {
        if (singleton == null) {
            singleton = new SupplierDAO();
        }
        return singleton;
    }
}

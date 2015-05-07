package com.renobidz.store.dao;

import com.renobidz.store.common.util.AbstractDAO;

/**
 * Created by lmgagne on 15-01-26.
 *
 * Datastore operations on Transaction Entity
 *
 */
public class TransactionDAO extends AbstractDAO{

    private static TransactionDAO singleton = null;

    private TransactionDAO() {

    }

    /**
     * @return
     *
     * Created Singleton Instance so that everytime a new object is not initialized into the memory
     *
     */
    public static TransactionDAO getInstance() {
        if (singleton == null) {
            singleton = new TransactionDAO();
        }
        return singleton;
    }
}

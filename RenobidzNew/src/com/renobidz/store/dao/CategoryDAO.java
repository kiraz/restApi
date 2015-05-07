package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.Category;
import com.renobidz.store.entity.util.LANGUAGE;

/**
 * Created by lmgagne on 14-12-30.
 */
public class CategoryDAO extends AbstractDAO {

    private static CategoryDAO singleton = null;

    private CategoryDAO() {

    }

    /**
     * @return
     *
     * Created Singleton Instance so that everytime a new object is not initialized into the memory
     *
     */
    public static CategoryDAO getInstance() {
        if (singleton == null) {
            singleton = new CategoryDAO();
        }
        return singleton;
    }

    /**
     * @param language
     * @return
     */

    public List<Category> getByLang(LANGUAGE language) {
        return ofy().load().type(Category.class).filter("language", language).list();
    }
}

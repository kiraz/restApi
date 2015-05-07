package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.StdProject;
import com.renobidz.store.entity.util.LANGUAGE;

/**
 * Created by lmgagne on 14-12-30.
 */
public class StdProjectDAO extends AbstractDAO {

    /**
     * @param language
     * @return
     */

    public List<StdProject> getByLang(LANGUAGE language) {
        return ofy().load().type(StdProject.class).filter("language", language).list();
    }
}

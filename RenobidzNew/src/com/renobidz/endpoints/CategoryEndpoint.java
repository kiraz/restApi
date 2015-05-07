package com.renobidz.endpoints;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.CategoryDAO;
import com.renobidz.store.entity.Category;
import com.renobidz.store.entity.util.LANGUAGE;

/**
 * @author lmgagne
 *
 * Category Cloud Endpoints
 *
 */
@Api(name = "categoryEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class CategoryEndpoint extends AbstractEndpoint<CategoryDAO>{
    private static final Logger logger = Logger.getLogger(CategoryEndpoint.class.getName());

    @Override
    protected CategoryDAO getDAO() {
        return CategoryDAO.getInstance();
    }

    /**
     * @param category
     * @throws DatabaseException
     * @throws IOException
     *
     * create new category
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, httpMethod = "POST")
    public Response create(Category category) throws DatabaseException, IOException {
        logger.info("creating the category entity");
        Long id = getDAO().createWithID(category);

        if (id != null) {
            return new Response(STATUS.SUCCESS, "Category successfully created with id=");
        }else{
            return new Response(STATUS.FAILURE, "Category not created successfully");
        }
    }

    /**
     * @param language
     * @return list<Category>
     * @throws DatabaseException
     *
     * get all categories by language
     */
    @ApiMethod(name = Path.OperationUrl.GET)
    public List<Category> getByLang(@Named("language") LANGUAGE language) throws DatabaseException {
        logger.info("loading all categories entity by language="+language);
        List<Category> categories = getDAO().getByLang(language);
        return categories;
    }

    /**
     * @param category
     * @return
     * @throws DatabaseException
     *
     * update the category entity
     */
    @ApiMethod(name = Path.OperationUrl.UPDATE)
    public Response update(Category category) throws DatabaseException {
        logger.info("updating the category entity");
        Category isExistingCategory = getDAO().getById(Category.class, category.getId());

        if(isExistingCategory != null){
            getDAO().update(Category.class, category.getId(), category);
            return new Response(STATUS.SUCCESS, "Category updated successfully");
        }else{
            return new Response(STATUS.FAILURE, "Category doesn't exist");
        }
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the category entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE)
    public void delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the category entity by id="+id);
        getDAO().delete(Category.class, id);
    }
}

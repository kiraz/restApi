package com.renobidz.store.dao;

import java.util.ArrayList;
import java.util.List;

import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.Product;

/**
 * @author lmgagne
 *
 * Datastore operations on Product Entity
 *
 */
public class ProductDAO extends AbstractDAO {

    private static ProductDAO singleton = null;

    private ProductDAO() {

    }

    /**
     * @return
     *
     * Created Singleton Instance so that everytime a new object is not initialized into the memory
     *
     */
    public static ProductDAO getInstance() {
        if (singleton == null) {
            singleton = new ProductDAO();
        }
        return singleton;
    }

    /**
     *
     */
    public List<Product> listProductByCartId(Long cartId){
       /* List<Cart> listProductRef = ofy().load().type(Cart.class).filter("id", cartId).list();
        List<Product> listProduct = new ArrayList<Product>(0);
        for(Product product : listProductRef){

            Product aproduct = getDAO().getById(Product.class, listProductRef.);

            listProduct.add(aproduct);
            );
        }*/
        List<Product> listProduct = new ArrayList<Product>(0);
        return listProduct;
    }
}

package com.renobidz.endpoints;

import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.endpoints.dto.CartDTO;
import com.renobidz.endpoints.util.converters.CartConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.CartDAO;
import com.renobidz.store.entity.Cart;

/**
 * @author lmgagne
 *
 * Cart Cloud Endpoints
 *
 */
@Api(name = "cartEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class CartEndpoint extends AbstractEndpoint<CartDAO>{
    private static final Logger logger = Logger.getLogger(CartEndpoint.class.getName());

    @Override
    protected CartDAO getDAO() {
        return CartDAO.getInstance();
    }

    protected CartConverter getConverter(){
        return CartConverter.getInstance();
    }

    /**
     * @param userId
     * @return
     * @throws DatabaseException
     *
     * listByUserId the cart entity by id
     */
    /*@ApiMethod(name = "listByUserId", path="listByUserId", httpMethod = HttpMethod.POST)
    public List<CartDTO> listByUserId(@Named("userId") Long userId) throws DatabaseException {
        logger.info("loading all cart entity by user id : "+userId);
        //List<Cart> list = getDAO().listByUserId(userId);
        return getConverter().toDTOsList(list);
    }*/

    /**
     * @param id
     * @return
     * @throws DatabaseException
     *
     * get the cart entity by id
     */
    @ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public CartDTO get(@Named("id") Long id) throws DatabaseException {
        logger.info("loading the cart entity by id : "+id);
        Cart cart = getDAO().getById(Cart.class, id);
        return getConverter().toDTO(cart);
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the cart entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the cart entity by id : "+id);
        getDAO().delete(Cart.class, id);
        return new Response(STATUS.SUCCESS, "Cart deleted successfully");
    }
}

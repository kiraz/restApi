package com.renobidz.endpoints;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.endpoints.dto.ProductDTO;
import com.renobidz.endpoints.util.converters.ProductConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.ProductDAO;
import com.renobidz.store.entity.Product;

/**
 * @author lmgagne
 *
 * Product Cloud Endpoints
 *
 */
@Api(name = "productEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class ProductEndpoint extends AbstractEndpoint<ProductDAO>{
    private static final Logger logger = Logger.getLogger(ProductEndpoint.class.getName());

    @Override
    protected ProductDAO getDAO() {
        return ProductDAO.getInstance();
    }

    protected ProductConverter getConverter(){
        return ProductConverter.getInstance();
    }

    /**
     * @param productDTO
     * @throws DatabaseException
     * @throws IOException
     *
     * create new product
     */
    @ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
    public Response create(ProductDTO productDTO) throws DatabaseException, IOException {
        logger.info("creating the product entity");
        Product product = getConverter().toEntity(productDTO);
        getDAO().createWithID(product);
        return new Response(STATUS.SUCCESS, "Product has been added successfully.");
    }

    /**
     * @param supplierId
     * @return
     * @throws DatabaseException
     *
     * listByUserId the product entity by id
     */
    /*(@ApiMethod(name = "listBySupplierId", path="listBySupplierId", httpMethod = HttpMethod.POST)
    public List<ProductDTO> listBySupplierId(@Named("supplierId") Long supplierId) throws DatabaseException {
        logger.info("loading all product entity by supplier id : "+supplierId);
        List<Product> list = getDAO().listBySupplierId(supplierId);
        return getConverter().toDTOsList(list);
    }*/

    /**
     * @param id
     * @return
     * @throws DatabaseException
     *
     * get the product entity by id
     */
    @ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public ProductDTO get(@Named("id") Long id) throws DatabaseException {
        logger.info("loading the product entity by id : "+id);
        Product product = getDAO().getById(Product.class, id);
        return getConverter().toDTO(product);
    }

    /**
     * @param productDTO
     * @return
     * @throws DatabaseException
     *
     * update the product entity
     */
    @ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
    public Response update(ProductDTO productDTO) throws DatabaseException {
        logger.info("updating the product entity");
        Product isExistingProduct = getDAO().getById(Product.class, productDTO.getId());
        if(isExistingProduct != null){
            isExistingProduct.setName(productDTO.getName());
            isExistingProduct.setDescription(productDTO.getDescription());
            isExistingProduct.setManufacturer(productDTO.getManufacturer());
            isExistingProduct.setHeight(productDTO.getHeight());
            isExistingProduct.setWeight(productDTO.getWeight());
            isExistingProduct.setShipping(productDTO.getShipping());
            isExistingProduct.setPrice(productDTO.getPrice());
            getDAO().update(Product.class, isExistingProduct.getId(), isExistingProduct);
            return new Response(STATUS.SUCCESS, "Product updated successfully");
        }else{
            return new Response(STATUS.FAILURE, "Product doesn't exists");
        }
    }

    /**
     * @param id
     * @throws DatabaseException
     *
     * delete the product entity by id
     */
    @ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
        logger.info("deleting the product entity by id : "+id);
        getDAO().delete(Product.class, id);
        return new Response(STATUS.SUCCESS, "Product deleted successfully");
    }
}

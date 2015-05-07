package com.renobidz.endpoints;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.endpoints.dto.AddressDTO;
import com.renobidz.endpoints.util.converters.AddressConverter;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.AddressDAO;
import com.renobidz.store.entity.Address;

/**
 * @author Ankur
 * 
 * Address Cloud Endpoints
 *
 */
@Api(name = "addressEndpoint", version = "v1", description = "Lets you manage addresses for all users.", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class AddressEndpoint extends AbstractEndpoint<AddressDAO>{
	private static final Logger logger = Logger.getLogger(AddressEndpoint.class.getName());
	
	@Override
	protected AddressDAO getDAO() {
		return AddressDAO.getInstance();
	}
	
	protected AddressConverter getConverter(){
		return AddressConverter.getInstance();
	}
	
	/**
	 * @param addressDTO: full address of a user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * create a new address
	 */
	@ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
	public Response create(AddressDTO addressDTO) throws DatabaseException, IOException {
		logger.info("creating the address entity");
        //TODO See why toUpperCase does not seem to work
        /*String stateUpper = addressDTO.getState().toString().toUpperCase();
        logger.info(stateUpper);
        addressDTO.setState(STATE.valueOf(stateUpper));

        String countryUpper = addressDTO.getCountry().toString().toUpperCase();
        if (countryUpper.equals("UNITED STATES")){
            countryUpper = "US";
        }
        addressDTO.setCountry(COUNTRY.valueOfName(countryUpper));*/

        Address address = getConverter().toEntity(addressDTO);

		getDAO().createWithID(address);
		return new Response(STATUS.SUCCESS, "Address has been added successfully.");
	}
	
	/**
	 * @param userId: id of a specific user
	 * @return List<AddressDTO>: list of all addresses associated to a specific user
	 * @throws DatabaseException
	 * 
	 * list addresses specific to a user
	 */
	@ApiMethod(name = "listByUserId", path="listByUserId", httpMethod = HttpMethod.POST)
    public List<AddressDTO> listByUserId(@Named("userId") Long userId) throws DatabaseException {
		logger.info("loading all address entity by user id : "+userId);
		List<Address> list = getDAO().listByUserId(userId);
		return getConverter().toDTOsList(list);
    }
	
	/**
	 * @param id: id of a specific address
	 * @return AddressDTO: a specific address details
	 * @throws DatabaseException
	 * 
	 * get a specific address using its id
	 */
	@ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public AddressDTO get(@Named("id") Long id) throws DatabaseException {
		logger.info("loading the address entity by id : "+id);
		Address address = getDAO().getById(Address.class, id);
		return getConverter().toDTO(address);
    }
	
	/**
	 * @param addressDTO: an address
	 * @return Response: SUCCESS or FAILURE along with a message
	 * @throws DatabaseException
	 * 
	 * update a specific address using its id
	 */
	@ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
	public Response updateUser(AddressDTO addressDTO) throws DatabaseException {
		logger.info("updating the address entity");
		Address isExistingUser = getDAO().getById(Address.class, addressDTO.getId());
		if(isExistingUser != null){
			isExistingUser.setAddressLine1(addressDTO.getAddressLine1());
			isExistingUser.setAddressLine2(addressDTO.getAddressLine2());
			isExistingUser.setCity(addressDTO.getCity());
			isExistingUser.setZipCode(addressDTO.getZipCode());
			isExistingUser.setState(addressDTO.getState());
			isExistingUser.setCountry(addressDTO.getCountry());
			//TODO: Not needed as of now
			//getDAO().update(Address.class, isExistingUser.getId(), isExistingUser);
			return new Response(STATUS.SUCCESS, "Address updated successfully");
		}else{
			return new Response(STATUS.FAILURE, "Address doesn't exists");
		}
    }
	
	/**
	 * @param id : id of a specific address
	 * @throws DatabaseException
	 * 
	 * delete a specific address using its id
	 */
	@ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public Response delete(@Named("id") Long id) throws DatabaseException {
		logger.info("deleting the address entity by id : "+id);
		getDAO().delete(Address.class, id);
		return new Response(STATUS.SUCCESS, "Address deleted successfully");
    }
}

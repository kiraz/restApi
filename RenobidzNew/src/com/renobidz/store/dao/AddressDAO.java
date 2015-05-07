package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.Address;
import com.renobidz.store.entity.User;

/**
 * @author Ankur
 *
 * Datastore operations on Address Entity
 * 
 */
public class AddressDAO extends AbstractDAO {
	
	private static AddressDAO singleton = null;

	private AddressDAO() {
		
	}
	
	/**
	 * @return
	 * 
	 * Created Singleton Instance so that everytime a new object is not initialized into the memory
	 * 
	 */
	public static AddressDAO getInstance() {
		if (singleton == null) {
			singleton = new AddressDAO();
		}
		return singleton;
	}

	/**
	 * @param id
	 * @return
	 */
	public List<Address> listByUserId(Long id) {
		return ofy().load().type(Address.class).filter("user", Ref.create(Key.create(User.class, id))).list();
	}
}

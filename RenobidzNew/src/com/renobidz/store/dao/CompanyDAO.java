package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.Company;
import com.renobidz.store.entity.User;

/**
 * @author Ankur
 *
 * Datastore operations on Company Entity
 * 
 */
public class CompanyDAO extends AbstractDAO {
	
	private static CompanyDAO singleton = null;

	private CompanyDAO() {
		
	}
	
	/**
	 * @return
	 * 
	 * Created Singleton Instance so that everytime a new object is not initialized into the memory
	 * 
	 */
	public static CompanyDAO getInstance() {
		if (singleton == null) {
			singleton = new CompanyDAO();
		}
		return singleton;
	}

	/**
	 * @param id
	 * @return
	 */
	public Company getByUserId(Long id) {
		return ofy().load().type(Company.class).filter("user", Ref.create(Key.create(User.class, id))).first().now();
	}
}

package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.User;

/**
 * @author Ankur
 *
 * Datastore operations on User Entity
 * 
 */
public class UserDAO extends AbstractDAO {
	
	private static UserDAO singleton = null;

	private UserDAO() {
		
	}

	/**
	 * @return
	 * 
	 * Created Singleton Instance so that everytime a new object is not initialized into the memory
	 * 
	 */
	public static UserDAO getInstance() {
		if (singleton == null) {
			singleton = new UserDAO();
		}
		return singleton;
	}

	/**
	 * @param email
	 * @return
	 */
	public User getByEmail(String email) {
		return ofy().load().type(User.class).filter("email", email).first().now();
	}
}

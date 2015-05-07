package com.renobidz.store.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.renobidz.store.common.util.AbstractDAO;
import com.renobidz.store.entity.Portfolio;
import com.renobidz.store.entity.User;

/**
 * @author Ankur
 *
 * Datastore operations on Address Entity
 * 
 */
public class PortfolioDAO extends AbstractDAO {
	
	private static PortfolioDAO singleton = null;

	private PortfolioDAO() {
		
	}
	
	/**
	 * @return
	 * 
	 * Created Singleton Instance so that everytime a new object is not initialized into the memory
	 * 
	 */
	public static PortfolioDAO getInstance() {
		if (singleton == null) {
			singleton = new PortfolioDAO();
		}
		return singleton;
	}

	/**
	 * @param email
	 * @return
	 */
	public List<Portfolio> listByUserId(Long id) {
		return ofy().load().type(Portfolio.class).filter("user", Ref.create(Key.create(User.class, id))).list();
	}
}

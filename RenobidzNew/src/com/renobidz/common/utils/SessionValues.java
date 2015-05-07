package com.renobidz.common.utils;

import javax.servlet.http.HttpServletRequest;

import com.renobidz.store.entity.User;

public class SessionValues {
	private static SessionValues singleton = null;

	private SessionValues() {
		
	}

	/**
	 * @return
	 * 
	 * Created Singleton Instance so that everytime a new object is not initialized into the memory
	 * 
	 */
	public static SessionValues getInstance() {
		if (singleton == null) {
			singleton = new SessionValues();
		}
		return singleton;
	}
	
	private static final String USER_SESSION_OBJECT = "user";
	
	public User getUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute(USER_SESSION_OBJECT);
	}
	
	public void setUser(HttpServletRequest request, User user){
		request.getSession().setAttribute(USER_SESSION_OBJECT, user);
	}
}

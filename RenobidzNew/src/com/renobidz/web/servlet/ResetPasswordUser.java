package com.renobidz.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.UserDAO;
import com.renobidz.store.entity.User;

@SuppressWarnings("serial")
public class ResetPasswordUser extends HttpServlet{

	protected UserDAO getDAO() {
		return UserDAO.getInstance();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		String key = request.getParameter("key");
		User isExistingUser = null;
		try {
			isExistingUser = getDAO().getById(User.class, Long.parseLong(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DatabaseException e){
            e.printStackTrace();
        }
		if(isExistingUser != null){
			if(isExistingUser.getPasswordResetKey() != null && isExistingUser.getPasswordResetKey().equalsIgnoreCase(key)){
				try {
					isExistingUser.setPasswordResetKey(null);
					getDAO().update(User.class, isExistingUser.getId(), isExistingUser);
				} catch (DatabaseException e) {
					e.printStackTrace();
				}
				response.sendRedirect("/resetpassword.html?email="+isExistingUser.getEmail());
			}else if(isExistingUser.getPasswordResetKey() == null){
				response.sendRedirect("/login.html?error=This url is already expired");
			}else{
				response.sendRedirect("/login.html?error=Invalid redirect url");	
			}
		}else{
			response.sendRedirect("/login.html?error=Invalid user id");
		}
	}
}

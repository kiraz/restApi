package com.renobidz.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.renobidz.common.utils.SessionValues;
import com.renobidz.store.dao.UserDAO;
import com.renobidz.store.entity.User;

@SuppressWarnings("serial")
public class UserDashboard extends HttpServlet{

	protected UserDAO getDAO() {
		return UserDAO.getInstance();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		User user = SessionValues.getInstance().getUser(request);
		if(user != null){
			request.getRequestDispatcher("/pages/user-dashboard.jsp").forward(request, response);
		}else{
			response.sendRedirect("/");
		}
	}
}

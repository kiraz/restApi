package com.renobidz.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.renobidz.common.utils.SessionValues;
import com.renobidz.store.dao.CompanyDAO;
import com.renobidz.store.entity.Company;
import com.renobidz.store.entity.User;

@SuppressWarnings("serial")
public class CompanyDashboard extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		User user = SessionValues.getInstance().getUser(request);
		if(user != null){
			Company company = CompanyDAO.getInstance().getByUserId(user.getId());
			if(company != null){
				request.setAttribute("company", company);
				request.getRequestDispatcher("/company-dashboard.jsp").forward(request, response);	
			}else{
				response.sendRedirect("/user/dashboard");
			}
		}else{
			response.sendRedirect("/");
		}
	}
}

package com.renobidz.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.sociallogin.common.SocialProfileInfo;
import com.myweb.sociallogin.facebook.request.FacebookAuthenticationHandler;
import com.myweb.sociallogin.facebook.request.FacebookOAuthRequest;
import com.myweb.sociallogin.google.request.GoogleAuthenticationHandler;
import com.myweb.sociallogin.google.request.GoogleOAuthRequest;
import com.renobidz.common.utils.SessionValues;
import com.renobidz.store.dao.UserDAO;
import com.renobidz.store.entity.User;
import com.renobidz.store.entity.util.SOCIALWEBSITE;

@SuppressWarnings("serial")
public class SocialLoginSuccessServlet extends HttpServlet{

	protected UserDAO getDAO() {
		return UserDAO.getInstance();
	}
	
	public Boolean saveUser(HttpServletRequest request, SocialProfileInfo socialProfileInfo){
		User isExistingUser = getDAO().getByEmail(socialProfileInfo.getEmail());
		if(isExistingUser == null){
			User user = new User();
			user.setFirstName(socialProfileInfo.getFirstName());
			user.setLastName(socialProfileInfo.getLastName());
			user.setEmail(socialProfileInfo.getEmail());
			user.setProfilePicture(socialProfileInfo.getProfilePicture());
			user.setCreationDate(System.currentTimeMillis());
			user.setIsLocked(false);	// Lock user
			//user.setSocialwebsite(SOCIALWEBSITE.valueOf(socialProfileInfo.getSocialwebsite().name()));
            user.setSocialwebsite(SOCIALWEBSITE.FACEBOOK);
			user.setIsSocialUser(true);
			getDAO().createWithID(user);
			SessionValues.getInstance().setUser(request, user);
			return true;
		}else{
			SessionValues.getInstance().setUser(request, isExistingUser);
			return false;
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String logintype = request.getParameter("logintype");
		if(logintype.equalsIgnoreCase("GOOGLE")){
			GoogleAuthenticationHandler googleAuthenticationHandler = new GoogleAuthenticationHandler();
			Map<String, Object> keyValuesMap = googleAuthenticationHandler.extractAccessToken(request.getQueryString());
			GoogleOAuthRequest googleOAuthRequest = new GoogleOAuthRequest("406637482960-3m2c8nddahqd6jkd7tr90fu8p1fpq0cm.apps.googleusercontent.com", "3kay-VgkvhvOvkz8ZZZM7TWg", "http://localhost:8888/confirm?logintype=google", keyValuesMap.get("code").toString());
			SocialProfileInfo socialProfileInfo = googleAuthenticationHandler.getSocialProfileInfo(googleOAuthRequest);
			if(socialProfileInfo != null && socialProfileInfo.getError() == null){
				Boolean isUserCreated = saveUser(request, socialProfileInfo);
				if(isUserCreated){
					response.sendRedirect("/user/dashboard");
				}else{
					response.sendRedirect("/user/dashboard");
				}
			}else{
				response.sendRedirect("?error=Oops ! We are not able to connect with your "+logintype.toLowerCase()+" profile.");
			}
		}else if(logintype.equalsIgnoreCase("FACEBOOK")){
			FacebookAuthenticationHandler facebookAuthenticationHandler = new FacebookAuthenticationHandler();
			Map<String, Object> keyValuesMap = facebookAuthenticationHandler.extractAccessToken(request.getQueryString());
			FacebookOAuthRequest oAuthRequestFacebook = new FacebookOAuthRequest("766305786750276", "7b47006880e96942b7e2f5fae75ab41a", "https://fabled-cocoa-612.appspot.com/confirm?logintype=facebook", keyValuesMap.get("code").toString());
			SocialProfileInfo socialProfileInfo = facebookAuthenticationHandler.getSocialProfileInfo(oAuthRequestFacebook);
			if(socialProfileInfo != null && socialProfileInfo.getError() == null){
				Boolean isUserCreated = saveUser(request, socialProfileInfo);
				if(isUserCreated){
					response.sendRedirect("/user/dashboard");
				}else{
					response.sendRedirect("/user/dashboard");
				}
			}else{
				response.sendRedirect("?error=Oops ! We are not able to connect with your "+logintype.toLowerCase()+" profile.");
			}
		}
	}
}

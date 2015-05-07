package com.renobidz.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.sociallogin.common.SOCIALWEBSITE;
import com.myweb.sociallogin.facebook.request.FACEBOOK_SCOPES;
import com.myweb.sociallogin.facebook.request.FacebookAuthenticationHandler;
import com.myweb.sociallogin.facebook.request.FacebookOAuthRequest;
import com.myweb.sociallogin.google.request.GOOGLE_SCOPES;
import com.myweb.sociallogin.google.request.GoogleAuthenticationHandler;
import com.myweb.sociallogin.google.request.GoogleOAuthRequest;

@SuppressWarnings("serial")
public class SocialLoginRedirectServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String socialLoginSite = req.getParameter("socialLoginSite");
		if(SOCIALWEBSITE.valueOf(socialLoginSite) == SOCIALWEBSITE.GOOGLE){
			GoogleAuthenticationHandler googleAuthenticationHandler = new GoogleAuthenticationHandler();
			List<GOOGLE_SCOPES> googlescopes = new ArrayList<GOOGLE_SCOPES>(0);
			googlescopes.add(GOOGLE_SCOPES.USER_INFO);
			googlescopes.add(GOOGLE_SCOPES.USER_PROFILE);
			GoogleOAuthRequest googleOAuthRequest = new GoogleOAuthRequest("406637482960-3m2c8nddahqd6jkd7tr90fu8p1fpq0cm.apps.googleusercontent.com", null, "http://localhost:8888/confirm?logintype=google", googlescopes);
			resp.sendRedirect(googleAuthenticationHandler.getOAuthUrl(googleOAuthRequest));
			
		}else if(SOCIALWEBSITE.valueOf(socialLoginSite) == SOCIALWEBSITE.FACEBOOK){
			FacebookAuthenticationHandler facebookAuthenticationHandler = new FacebookAuthenticationHandler();
			List<FACEBOOK_SCOPES> googlescopes = new ArrayList<FACEBOOK_SCOPES>(0);
			googlescopes.add(FACEBOOK_SCOPES.USER_EMAIL_PROFILE);
			googlescopes.add(FACEBOOK_SCOPES.USER_PUBLISH_ACTION);
			FacebookOAuthRequest facebookOAuthRequest = new FacebookOAuthRequest("766305786750276", null, "https://fabled-cocoa-612.appspot.com/confirm?logintype=facebook", googlescopes);
			resp.sendRedirect(facebookAuthenticationHandler.getOAuthUrl(facebookOAuthRequest));
		}else{
			resp.sendRedirect("/");
		}
	}
}

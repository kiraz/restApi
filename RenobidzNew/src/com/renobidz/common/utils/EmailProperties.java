package com.renobidz.common.utils;

/**
 * @author Ankur
 *
 * Set all email related properties here
 * 
 */
public interface EmailProperties {
	String sendingEmail = "lmgagne@videotron.ca";
	String siteUrl = "https://fabled-cocoa-612.appspot.com/";		// Make sure the last forward "/" is always there
	
	String emailTemplateFolder= "WEB-INF/email_templates";
	String emailTemplate_verify_user= "email_verify.ftl";
	String emailTemplate_reset_password= "reset_password.ftl";
}

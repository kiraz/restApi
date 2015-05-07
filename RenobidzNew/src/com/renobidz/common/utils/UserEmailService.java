package com.renobidz.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.renobidz.store.entity.User;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class UserEmailService {
	/**
	 * @param user
	 * @param verifyUrl
	 * @param messageSubject
	 * @param templateName
	 * @throws IOException
	 * 
	 * method for sending emails like registration, forget_password
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void selectAndSendEmail(User user, String verifyUrl, String messageSubject, String templateName) throws IOException{
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(EmailProperties.emailTemplateFolder));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template temp = cfg.getTemplate(templateName);

		/* Create a data-model */
		Map root = new HashMap();
		Map latest = new HashMap();
		latest.put("name", user.getFirstName());
		latest.put("email", user.getEmail());
		latest.put("verifyUrl", verifyUrl);
		root.put("user", latest);

		/* Merge data-model with template */
		Writer out = new StringWriter();
		try {
			temp.process(root, out);
			//System.out.println(out.toString());
			EmailSender emailSender = new EmailSender();
			emailSender.sendHtmlEmail(EmailProperties.sendingEmail, user.getEmail(), messageSubject, out.toString());
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		out.flush();
	}
}

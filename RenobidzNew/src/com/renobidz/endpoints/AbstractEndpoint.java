package com.renobidz.endpoints;

import com.renobidz.store.common.util.AbstractDAO;

/**
 * @author Ankur
 *
 * @param <TemplateDAO>
 */
public abstract class AbstractEndpoint<TemplateDAO extends AbstractDAO> {
	/**
	 * Method to return the domain dao
	 */
	protected abstract TemplateDAO getDAO();
	
}

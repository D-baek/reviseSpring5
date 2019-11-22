package com.doosan.web.ctx;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebContext extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletContext.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"}; 
	}

}
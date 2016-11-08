package com.sudhu.elasticapp.main.init;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * @Override public void onStartup(ServletContext servletContext) throws
	 * ServletException { AnnotationConfigWebApplicationContext context = new
	 * AnnotationConfigWebApplicationContext();
	 * context.register(ApplicationBeanContext.class);
	 * servletContext.addListener(new ContextLoaderListener(context));
	 * ServletRegistration.Dynamic servlet = servletContext.addServlet("spring",
	 * new DispatcherServlet(context)); servlet.addMapping("/");
	 * servlet.setLoadOnStartup(0); }
	 */

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationBeanContext.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		Filter[] singleton = { new CORSFilter() };
		return singleton;
	}
}

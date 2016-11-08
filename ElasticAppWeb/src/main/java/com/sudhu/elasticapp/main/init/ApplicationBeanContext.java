package com.sudhu.elasticapp.main.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.sudhu.elasticapp.common.constants.CommonConstants;
import com.sudhu.elasticapp.home.controller.ApplicationFilter;

/**
 * Created by sudha on 02-Oct-16.
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.sudhu")
public class ApplicationBeanContext extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/script/**").addResourceLocations("/script/");
		registry.addResourceHandler("/style/**").addResourceLocations("/style/");
		registry.addResourceHandler("/font/**").addResourceLocations("/font/");
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		super.addResourceHandlers(registry);
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		Map<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		mediaTypes.put("html", MediaType.TEXT_HTML);
		configurer.mediaTypes(mediaTypes);
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}

	@Bean
	public ContentNegotiatingViewResolver getViewResolver(ContentNegotiationManager manager) {

		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();

		InternalResourceViewResolver r1 = new InternalResourceViewResolver();
		r1.setPrefix("/WEB-INF/view/");
		r1.setSuffix(".jsp");
		viewResolvers.add(r1);

		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();

		viewResolver.setContentNegotiationManager(manager);
		viewResolver.setViewResolvers(viewResolvers);
		List<View> views = new ArrayList<>();
		views.add(new MappingJackson2JsonView());
		viewResolver.setDefaultViews(views);

		return viewResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.getApplicationFilter());
		super.addInterceptors(registry);
	}

	@Bean
	public HandlerInterceptorAdapter getApplicationFilter() {
		return new ApplicationFilter();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// converters.add(new MappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}

	@Bean
	public RequestMappingHandlerAdapter annotationMethodHandlerAdapter() {
		final RequestMappingHandlerAdapter annotationMethodHandlerAdapter = new RequestMappingHandlerAdapter();
		final MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();

		List<HttpMessageConverter<?>> httpMessageConverter = new ArrayList<HttpMessageConverter<?>>();
		httpMessageConverter.add(mappingJacksonHttpMessageConverter);

		String[] supportedHttpMethods = { "POST", "GET", "HEAD" };

		annotationMethodHandlerAdapter.setMessageConverters(httpMessageConverter);
		annotationMethodHandlerAdapter.setSupportedMethods(supportedHttpMethods);
		return annotationMethodHandlerAdapter;
	}

	@Bean
	public DataSource getDataSource() throws Exception {
		Context context = new InitialContext();
		return (DataSource) context.lookup(CommonConstants.JNDI_NAME);
	}

	@Bean
	public NamedParameterJdbcOperations getJdbcOperation() throws Exception {
		NamedParameterJdbcOperations jdbcOperations = new NamedParameterJdbcTemplate(getDataSource());
		return jdbcOperations;
	}

}

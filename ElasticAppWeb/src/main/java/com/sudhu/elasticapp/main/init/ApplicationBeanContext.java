package com.sudhu.elasticapp.main.init;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.common.cache.CacheBuilder;
import com.sudhu.elasticapp.common.constants.CommonConstants;
import com.sudhu.elasticapp.home.controller.ApplicationFilter;

/**
 * Created by sudha on 02-Oct-16.
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.sudhu")
@EnableCaching
public class ApplicationBeanContext extends WebMvcConfigurerAdapter {

   
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
        registry.addResourceHandler("/theme/**").addResourceLocations("/theme/");
        registry.addResourceHandler("/font/**").addResourceLocations("/font/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public ContentNegotiatingViewResolver getViewResolver(ContentNegotiationManager  manager){

        List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();

        InternalResourceViewResolver r1 = new InternalResourceViewResolver();
        r1.setPrefix("/WEB-INF/jsp/");
        r1.setSuffix(".jsp");
        r1.setViewClass(JstlView.class);
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
    public HandlerInterceptorAdapter getApplicationFilter(){
        return new ApplicationFilter();
    }

    @Bean
    public DataSource getDataSource() throws Exception{
        Context context = new InitialContext();
        return (DataSource) context.lookup(CommonConstants.JNDI_NAME);
    }

    @Bean
    public NamedParameterJdbcOperations getJdbcOperation() throws Exception{
        NamedParameterJdbcOperations jdbcOperations = new NamedParameterJdbcTemplate(getDataSource());
        return jdbcOperations;
    }

    @Bean(name="ApplicationCache")
    public CacheManager getCacheManager(){
    	GuavaCacheManager cacheManager = new GuavaCacheManager(CommonConstants.APPLICATION_CACHE);
    	CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
    		       .maximumSize(100)
    		       .expireAfterWrite(10, TimeUnit.DAYS);
    	cacheManager.setCacheBuilder(cacheBuilder);
    	return cacheManager;
    }

}

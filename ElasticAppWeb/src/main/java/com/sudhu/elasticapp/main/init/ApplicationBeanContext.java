package com.sudhu.elasticapp.main.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sudha on 02-Oct-16.
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.sudhu")
public class ApplicationBeanContext extends WebMvcConfigurerAdapter {

    /*
   * Configure ContentNegotiationManager
   */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true).defaultContentType(
                MediaType.TEXT_HTML);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
        registry.addResourceHandler("/theme/**").addResourceLocations("/theme/");
        registry.addResourceHandler("/font/**").addResourceLocations("/font/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public ViewResolver getViewResolver(ContentNegotiationManager  manager){

        List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();

        InternalResourceViewResolver r1 = new InternalResourceViewResolver();
        r1.setPrefix("/WEB-INF/jsp/");
        r1.setSuffix(".jsp");
        r1.setViewClass(JstlView.class);
        viewResolvers.add(r1);

        JsonViewResolver jsonViewResolver = new JsonViewResolver();
        viewResolvers.add(jsonViewResolver);

        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setContentNegotiationManager(manager);
        viewResolver.setViewResolvers(viewResolvers);
        return viewResolver;
    }

}

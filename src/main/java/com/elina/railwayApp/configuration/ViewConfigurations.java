package com.elina.railwayApp.configuration;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.TimeZone;

@Log4j
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.elina.railwayApp")
public class ViewConfigurations extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        TimeZone.setDefault(TimeZone.getTimeZone("UTS"));
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }




    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.warn("ADD RESOURCE");
        registry.addResourceHandler("/resource/**").addResourceLocations("/resources/");
        registry.addResourceHandler("*.css").addResourceLocations("/resources/css/");
        registry.addResourceHandler("*.js").addResourceLocations("/resources/js/");
    }
}



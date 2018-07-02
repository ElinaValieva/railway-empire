package com.elina.railwayApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.TimeZone;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.elina.railwayApp")
public class ViewConfigurations implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/pages/");
        viewResolver.setSuffix(".jsp");
        TimeZone.setDefault(TimeZone.getTimeZone("UTS"));
        return viewResolver;
    }
/*
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/static/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/static/images/");
    }
*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}



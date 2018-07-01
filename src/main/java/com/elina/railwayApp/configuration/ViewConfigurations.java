package com.elina.railwayApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;
import java.util.TimeZone;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.elina.railwayApp")
@PropertySource(value = {"classpath:mail.ru.properties"})
public class ViewConfigurations implements WebMvcConfigurer {

    private static final String MAIL_HOST = "mail.host";
    private static final String MAIL_PORT = "mail.port";
    private static final String MAIL_USERNAME = "mail.username";
    private static final String MAIL_PASSWORD = "mail.password";
    private static final String MAIL_SMTP_STARTUP_ENABLE = "mail.smtp.starttls.enable";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    private static final String MAIL_DEBUG = "mail.debug";

    @Autowired
    private Environment environment;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
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
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getRequiredProperty(MAIL_HOST));
        mailSender.setPort(Integer.parseInt(environment.getRequiredProperty(MAIL_PORT)));
        mailSender.setUsername(environment.getRequiredProperty(MAIL_USERNAME));
        mailSender.setPassword(environment.getProperty(MAIL_PASSWORD));
        Properties javaMailProperties = new Properties();
        javaMailProperties.put(MAIL_SMTP_STARTUP_ENABLE, environment.getRequiredProperty(MAIL_SMTP_STARTUP_ENABLE));
        javaMailProperties.put(MAIL_SMTP_AUTH, environment.getRequiredProperty(MAIL_SMTP_AUTH));
        javaMailProperties.put(MAIL_TRANSPORT_PROTOCOL, environment.getRequiredProperty(MAIL_TRANSPORT_PROTOCOL));
        javaMailProperties.put(MAIL_DEBUG, environment.getRequiredProperty(MAIL_DEBUG));
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}



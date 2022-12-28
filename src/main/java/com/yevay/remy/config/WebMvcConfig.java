package com.yevay.remy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] allowedOrigins = new String[]{"http://localhost:5173"};
    private static final String mapping = "/**";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(mapping)
                .allowedOrigins(allowedOrigins)
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(registry.hasMappingForPattern("/static/**")) {
            return;
        }
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }
}

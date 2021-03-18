package com.edward.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Access-Control-Allow-Origin 跨域
 * 跨域解决方案
 * 1.nginx反向代理
 * 2.jsonp
 * 3.@CrossOrigin
 * 4.配置webmvcconfigurer
 */
@Configuration
public class CrosConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT","DELETE", "OPTIONS", "HEAD")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}

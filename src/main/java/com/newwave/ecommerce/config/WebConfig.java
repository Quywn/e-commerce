package com.newwave.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Áp dụng CORS cho tất cả các endpoint
                .allowedOrigins("http://localhost:63342")  // Địa chỉ của frontend
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")  // Các phương thức HTTP cho phép
                .allowedHeaders("*")  // Cho phép tất cả các header
                .allowCredentials(true);  // Nếu cần gửi cookie, JWT, v.v.
    }
}
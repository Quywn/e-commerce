package com.newwave.ecommerce.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");  // Chuyển hướng người dùng đến trang đăng nhập nếu chưa đăng nhập
            return false;  // Dừng việc xử lý request
        }
        return true;  // Tiếp tục xử lý request
    }
}

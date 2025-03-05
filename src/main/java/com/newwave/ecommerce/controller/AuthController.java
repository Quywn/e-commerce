package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.request.LoginRequest;
import com.newwave.ecommerce.domain.response.JwtResponse;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.secure.JwtTokenUtil;
import com.newwave.ecommerce.service.impl.UserServiceImpl;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserServiceImpl userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
            if (!userService.checkPassword(loginRequest.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
            // Xác thực thông tin đăng nhập
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Tạo JWT token
            String token = jwtTokenUtil.generateToken(loginRequest.getUsername());

            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException | JOSEException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Logic để hủy token

        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        // Giả sử người dùng đã đăng nhập, ta lưu thông tin người dùng vào session
        session.setAttribute("user", "admin");
        return "Logged in successfully!";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "Logged out successfully!";
    }
}

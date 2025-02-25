//package com.newwave.ecommerce.controller;
//
//import com.newwave.ecommerce.domain.request.LoginRequest;
//import com.newwave.ecommerce.domain.response.JwtResponse;
//import com.newwave.ecommerce.secure.JwtTokenUtil;
//import com.nimbusds.jose.JOSEException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthController {
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenUtil jwtTokenProvider;
//
//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenUtil;
//    }
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            // Xác thực thông tin đăng nhập
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Tạo JWT token
//            String token = jwtTokenProvider.generateToken(loginRequest.getUsername());
//
//            return ResponseEntity.ok(new JwtResponse(token));
//        } catch (AuthenticationException | JOSEException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request) {
//        // Logic để hủy token
//
//        return ResponseEntity.ok("Logged out successfully");
//    }
//}

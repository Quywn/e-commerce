package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.UserDTO;
import com.newwave.ecommerce.entity.User;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.UserRepo;
import com.newwave.ecommerce.secure.UserPrincipal;
import com.newwave.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }



    // Kiểm tra mật khẩu người dùng có hợp lệ không
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    public String signUp(UserDTO userDTO) {
        if (userRepo.findByUsername(userDTO.getUsername()) != null || userRepo.findByEmail(userDTO.getEmail()) != null) {
            return "Username/email is already in use";
        }
        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        return "Success saved user +" + userRepo.save(user);
    }

//    @Override
//    public UserDTO updateEmailByUsername(UserDTO user) {
//        userRepo.updateEmailByUsername(user.getUsername());
//        return null;
//    }
//
//    @Override
//    public UserDTO updatePasswordByUsername(UserDTO user) {
//        userRepo.updatePasswordByUsername(user.getUsername());
//        return null;
//    }
}

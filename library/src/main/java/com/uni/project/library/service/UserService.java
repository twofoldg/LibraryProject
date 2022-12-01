package com.uni.project.library.service;

import com.uni.project.library.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findUserByEmail(String email);

    User saveUser(User user);

    User findUserById(Long id);
}

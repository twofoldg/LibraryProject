package com.uni.project.library.service;

import com.uni.project.library.entity.User;

public interface UserService {
    User findUserByEmail(String email);

    User saveUser(User user);

    User findUserById(Long id);
}

package com.uni.project.library.service.impl;

import com.uni.project.library.entity.User;
import com.uni.project.library.exception.NotFoundExceptionCustom;
import com.uni.project.library.repository.UserRepository;
import com.uni.project.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundExceptionCustom("User with email " + email + " not found"));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundExceptionCustom("User with id " + id + " not found"));
    }
}

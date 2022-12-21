package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.UserService;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.model.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service("UserService")
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;


    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

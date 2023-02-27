package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.UserService;
import com.yevay.remy.model.domain.User;
import com.yevay.remy.core.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserService")
public class DefaultUserService implements UserService {

    private final static String USER_NOT_FOUND_ERROR = "User with login [%s] not found";

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return getByLogin(username);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException(String.format(USER_NOT_FOUND_ERROR, login)));
    }
}

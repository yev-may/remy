package com.yevay.remy.core.service;

import com.yevay.remy.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User user);

    User getByLogin(String login);

}

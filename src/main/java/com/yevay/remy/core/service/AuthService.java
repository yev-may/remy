package com.yevay.remy.core.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails auth(String login, String password);

}

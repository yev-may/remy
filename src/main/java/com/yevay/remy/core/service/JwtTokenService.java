package com.yevay.remy.core.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtTokenService {

    String generateToken(UserDetails user);

    String getUsernameFromToken(String token);

    Date getExpirationDateFromToken(String token);

    boolean validateToken(String token);

}

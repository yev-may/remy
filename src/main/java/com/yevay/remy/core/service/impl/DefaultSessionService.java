package com.yevay.remy.core.service.impl;

import com.yevay.remy.core.service.SessionService;
import com.yevay.remy.model.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultSessionService implements SessionService {

    @Override
    public User getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .filter(User.class::isInstance)
                .map(User.class::cast)
                .orElseThrow(() -> new IllegalStateException("User not authenticated"));
    }
}

package com.yevay.remy.config.filter;

import com.yevay.remy.core.facade.JwtAuthFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer_";
    private static final String TOKEN_HEADER_NAME = "Authorization";

    private final JwtAuthFacade jwtAuthFacade;

    public JwtRequestFilter(JwtAuthFacade jwtAuthFacade) {
        this.jwtAuthFacade = jwtAuthFacade;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        processJwtAuth(request);
        filterChain.doFilter(request, response);
    }

    private void processJwtAuth(HttpServletRequest request) {
        if (notAuthed()) {
            getToken(request)
                .ifPresent(token -> jwtAuthFacade.auth(token, request));
        }
    }

    private Optional<String> getToken(HttpServletRequest request) {
        return getAuthFromRequest(request)
                .map(this::getTokenFromAuth);
    }

    private Optional<String> getAuthFromRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(TOKEN_HEADER_NAME));
    }

    private String getTokenFromAuth(String auth) {
        return auth.substring(TOKEN_PREFIX.length());
    }

    private boolean notAuthed() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }
}

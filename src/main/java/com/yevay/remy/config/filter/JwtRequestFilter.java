package com.yevay.remy.config.filter;

import com.yevay.remy.core.service.JwtTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;

    public JwtRequestFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
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
                .filter(this::validateToken)
                .ifPresent(token -> auth(token, request));
        }
    }

    private Optional<String> getToken(HttpServletRequest request) {
        return getAuthFromRequest(request)
                .map(this::getTokenFromAuth);
    }

    private Optional<String> getAuthFromRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"));
    }

    private String getTokenFromAuth(String auth) {
        return auth.substring(TOKEN_PREFIX.length());
    }

    private boolean notAuthed() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    public void auth(String token, HttpServletRequest request) {
        String usernameFromToken = jwtTokenService.getUsernameFromToken(token);
        UserDetails user = userDetailsService.loadUserByUsername(usernameFromToken);
        UsernamePasswordAuthenticationToken auth = createAuthToken(user, request);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private UsernamePasswordAuthenticationToken createAuthToken(UserDetails user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }

    public boolean validateToken(String token) {
        return jwtTokenService.validateToken(token);
    }
}

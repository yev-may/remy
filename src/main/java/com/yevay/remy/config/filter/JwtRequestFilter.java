package com.yevay.remy.config.filter;

import com.yevay.remy.core.service.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
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

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer_";

    private final UserDetailsService userDetailsService;
    private final JwtTokenService jwtTokenService;

    public JwtRequestFilter(UserDetailsService userDetailsService, JwtTokenService jwtTokenService) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        processJwtAuth(request);
        filterChain.doFilter(request, response);
    }

    private void processJwtAuth(HttpServletRequest request) {
        if (containsToken(request)) {
            String jwtToken = getToken(request);
            String username = getUsername(jwtToken);

            if (canAuth(username)) {
                auth(username, jwtToken, request);
            }
        } else {
            System.out.println("Jwt token is not provided");
        }
    }

    private boolean containsToken(HttpServletRequest request) {
        String requestAuthorization = request.getHeader("Authorization");
        return requestAuthorization != null && requestAuthorization.startsWith(TOKEN_PREFIX);
    }

    private String getToken(HttpServletRequest request) {
        String requestAuthorization = request.getHeader("Authorization");
        return requestAuthorization.substring(TOKEN_PREFIX.length());
    }

    private String  getUsername(String token) {
        try {
            return jwtTokenService.getUsernameFromToken(token);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unable to get JWT token");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT token has expired");
        }
    }

    private boolean canAuth(String username) {
        return username != null && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void auth(String username, String jwtToken, HttpServletRequest request) {
        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (jwtTokenService.validateToken(jwtToken, user)) {
            UsernamePasswordAuthenticationToken auth = createAuthToken(user, request);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private UsernamePasswordAuthenticationToken createAuthToken(UserDetails user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authToken;
    }
}

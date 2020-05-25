package com.cinema.api.security.jwt;

import com.cinema.api.service.impl.LoginModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.cinema.api.security.jwt.JwtTokenProvider.AUTHORIZATION;
import static com.cinema.api.security.jwt.JwtTokenProvider.BEARER_;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(AUTHORIZATION);
        String token = "";

        if (header != null && header.startsWith(BEARER_)) {
            token = header.replace(BEARER_, "");
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtTokenProvider.validateToken(token)) {
                    Authentication authentication = jwtTokenProvider.setAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}

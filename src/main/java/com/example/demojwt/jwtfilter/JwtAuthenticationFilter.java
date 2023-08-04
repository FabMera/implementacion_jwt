package com.example.demojwt.jwtfilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpHeaders;

//OncePerRequestFilter: Es una clase abstracta que nos permite crear un filtro que se ejecuta una vez por cada petición.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //DOFILTERINTERNAL: Es el método que se ejecuta por cada petición, en este método es donde se debe implementar la lógica del filtro.

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = getTokenFromRequest(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(authHeader)&& authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }
}

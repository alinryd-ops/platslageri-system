package com.platslageri.platslageri_app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class PinFilter extends OncePerRequestFilter {

    @Value("${app.pin}")
    private String correctPin;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");

        String path = request.getRequestURI();

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        if (path.equals("/") || path.contains("index.html") || path.contains("/ping")) {
            filterChain.doFilter(request, response);
            return;
        }

        String pin = request.getHeader("X-PIN");

        if (pin == null || !pin.trim().equals(correctPin.trim())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Fel PIN\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
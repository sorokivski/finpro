package com.project.nix.config.security.filter;


import com.project.nix.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter
{
    private final JWTService jwtService;

    @Value("${spring.security.jwt.name}")
    private String tokenName;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        SecurityContextHolder.getContext()
                .setAuthentication(Optional.ofNullable(((HttpServletRequest) request).getHeader(tokenName))
                        .flatMap(jwtService::parse)
                        .orElse(null)
                );

        chain.doFilter(request, response);
    }
}

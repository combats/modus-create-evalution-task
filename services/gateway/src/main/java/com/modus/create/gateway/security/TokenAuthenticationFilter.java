package com.modus.create.gateway.security;

import com.modus.create.gateway.service.TokenParser;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class TokenAuthenticationFilter extends GenericFilterBean {

    private String authTokenHeader;
    private TokenParser tokenParser;
    private AuthContext authContext;

    public TokenAuthenticationFilter(String authTokenHeader,
                                     TokenParser tokenParser,
                                     AuthContext authContext
    ) {
        this.authTokenHeader = authTokenHeader;
        this.tokenParser = tokenParser;
        this.authContext = authContext;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String authToken = request.getHeader(authTokenHeader);
        UUID userId;
        try {
            userId = tokenParser.parseAccessToken(authToken);

        } catch (Exception ex) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        authContext.setAuthentication(userId.toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
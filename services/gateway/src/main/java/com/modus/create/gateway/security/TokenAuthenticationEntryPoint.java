package com.modus.create.gateway.security;

import com.google.gson.Gson;
import com.modus.create.gateway.exception.ExceptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Value("${com.modus.create.authentication.token.header}")
    private String authTokenHeader;
    @Autowired
    private Gson gson;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        String authToken = request.getHeader(authTokenHeader);

        if (isBlank(authToken)) {
            response.setStatus(PRECONDITION_FAILED.value());
            String exceptionMessage = gson.toJson(new ExceptionDto("Header " + authTokenHeader + " is not found"));
            response.getWriter().write(exceptionMessage);
        } else {
            response.setStatus(UNAUTHORIZED.value());
            String exceptionMessage = gson.toJson(new ExceptionDto("Authentication token " + authToken + " is incorrect"));
            response.getWriter().write(exceptionMessage);
        }
    }
}
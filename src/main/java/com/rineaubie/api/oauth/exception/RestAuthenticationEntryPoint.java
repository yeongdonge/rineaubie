package com.rineaubie.api.oauth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException authException
    ) throws IOException, ServletException {
        authException.printStackTrace();
        log.info("인증되지 않은 Response 입니다. Massage = {}", authException.getMessage());
        response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                authException.getLocalizedMessage()
        );
    }
}

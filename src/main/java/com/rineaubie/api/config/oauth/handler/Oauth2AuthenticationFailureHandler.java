package com.rineaubie.api.config.oauth.handler;

import com.rineaubie.api.config.oauth.utils.CookieUtil;
import com.rineaubie.api.config.oauth.repository.Oauth2AuthorizationBasedOnCookieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class Oauth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final Oauth2AuthorizationBasedOnCookieRepository authorizationRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String targetUri = CookieUtil.getCookie(request, Oauth2AuthorizationBasedOnCookieRepository.OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(Cookie::getValue)
                .orElse("/");

        exception.printStackTrace();

        targetUri = UriComponentsBuilder.fromUriString(targetUri)
                .queryParam("error", exception.getLocalizedMessage())
                .build().toUriString();

        authorizationRepository.removeAuthorizationRequestCookie(request, response);

        getRedirectStrategy().sendRedirect(request, response, targetUri);
    }
}

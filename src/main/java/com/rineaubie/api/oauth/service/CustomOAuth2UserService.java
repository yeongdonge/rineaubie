package com.rineaubie.api.oauth.service;

import com.rineaubie.api.domain.user.User;
import com.rineaubie.api.oauth.entity.ProviderType;
import com.rineaubie.api.oauth.entity.Role;
import com.rineaubie.api.oauth.entity.UserPrincipal;
import com.rineaubie.api.oauth.exception.OauthProviderMissMatchException;
import com.rineaubie.api.oauth.info.Oauth2UserInfo;
import com.rineaubie.api.oauth.info.Oauth2UserInfoFactory;
import com.rineaubie.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            log.info("process=========================");
            return this.process(userRequest, user);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }

    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase(Locale.ROOT));

        Oauth2UserInfo userInfo = Oauth2UserInfoFactory.getOauth2UserInfo(providerType, user.getAttributes());
        User saveduser = userRepository.findByUserId(userInfo.getId());

        if (saveduser != null) {
            if (providerType != saveduser.getProviderType()) {
                throw new OauthProviderMissMatchException((
                        providerType + " 계정으로 가입되어 있습니다." +
                                saveduser.getProviderType() + " 로 로그인 해주세요."
                ));
            }
            updateUser(saveduser, userInfo);
        } else {
            saveduser = createUser(userInfo, providerType);
        }
        return UserPrincipal.create(saveduser, user.getAttributes());
    }

    private User createUser(Oauth2UserInfo userInfo, ProviderType providerType) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(
                userInfo.getId(),
                userInfo.getName(),
                userInfo.getEmail(),
                "Y",
                userInfo.getImageUrl(),
                providerType,
                Role.USER,
                now,
                now
        );
        return userRepository.saveAndFlush(user);
    }

    private User updateUser(User user, Oauth2UserInfo userInfo) {
        if (userInfo.getName() != null && !user.getUsername().equals(userInfo.getName())) {
            user.setUsername(userInfo.getName());
        }
        if (userInfo.getImageUrl() != null && !user.getProfileImageUrl().equals(userInfo.getImageUrl())) {
            user.setProfileImageUrl(userInfo.getImageUrl());
        }
        return user;
    }
}

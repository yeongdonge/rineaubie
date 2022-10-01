package com.rineaubie.api.oauth.info;

import com.rineaubie.api.oauth.entity.ProviderType;
import com.rineaubie.api.oauth.info.impl.FacebookOAuth2UserInfo;
import com.rineaubie.api.oauth.info.impl.GoogleOauth2UserInfo;
import com.rineaubie.api.oauth.info.impl.KakaoOAuth2UserInfo;
import com.rineaubie.api.oauth.info.impl.NaverOAuth2UserInfo;

import java.util.Map;

public class Oauth2UserInfoFactory {

    public static Oauth2UserInfo getOauth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOauth2UserInfo(attributes);
            case FACEBOOK: return new FacebookOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("올바르지 않은 소셜로그인 타입입니다.");
        }
    }
}

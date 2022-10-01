package com.rineaubie.api.oauth.info;

import lombok.Getter;

import java.util.Map;

public abstract class Oauth2UserInfo {

    @Getter
    protected Map<String, Object> attributes;

    public Oauth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId();
    public abstract String getName();
    public abstract String getEmail();
    public abstract String getImageUrl();

}

package com.rineaubie.api.config.oauth.exception;

public class OauthProviderMissMatchException extends RuntimeException{

    public OauthProviderMissMatchException(String message) {
        super(message);
    }
}

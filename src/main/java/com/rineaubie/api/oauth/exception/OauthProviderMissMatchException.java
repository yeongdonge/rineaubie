package com.rineaubie.api.oauth.exception;

public class OauthProviderMissMatchException extends RuntimeException{

    public OauthProviderMissMatchException(String message) {
        super(message);
    }
}

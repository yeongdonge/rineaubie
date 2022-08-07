package com.rineaubie.api.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * status -> 404
 */
@Slf4j
public class PostNotFound extends RineauException {

    private static final String MESSAGE = "존재하지 않는 글입니다.";
    public PostNotFound() {
        super(MESSAGE);
        log.error(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}

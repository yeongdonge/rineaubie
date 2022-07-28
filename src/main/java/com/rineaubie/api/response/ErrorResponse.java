package com.rineaubie.api.response;


import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * {
 *     "code": "400",
 *     "massage": "잘못된 요청입니다.",
 *     "validation": {
 *         "title": "값을 입력해주세요"
 *     }
 * }
 */

@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

    private final String code;
    private final String message;

    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    public void addValidation(String field, String message) {
        validation.put(field, message);
    }
}

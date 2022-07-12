package com.rineaubie.api.controller;

import com.rineaubie.api.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PostController {

    // Http Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
    // 글 등록
    // POST Method

    // SSR -> jsp, thymeleaf, mustache,
        // -> html rendering
    // SPA
    // vue, nuxt
    // react, next
        // -> javascript + <-> API (JSON)

    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate params) {
        // 데이터를 검증하는 이유

        // 1. client 개발자가 깜빡할 수 있다. 실수로 값을 안보낼 수 있다.
        // 2. client bug 값 누락
        // 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다.
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
        // 5. 서버 개발자의 편안함을 위해서...?

        String title = params.getTitle();
        String content = params.getContent();
        log.info("params={}", params.toString());
//        if (result.hasErrors()) {
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            FieldError firstFieldError = fieldErrors.get(0);
//            String fieldName = firstFieldError.getField(); // title
//            String errorMessage = firstFieldError.getDefaultMessage();// 에러 메시지
//
//            Map<String, String> error = new HashMap<>();
//            error.put(fieldName, errorMessage);
//            return error;
//        }
//        // {"title": "타이틀 값이 없습니다."}
        return Map.of();
    }
}

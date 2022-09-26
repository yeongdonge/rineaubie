package com.rineaubie.api.config.oauth.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "일반 사용자"),
    GUEST("GUEST", "게스트");

    private final String key;
    private final String title;

    public static Role of(String key) {
        return Arrays.stream(Role.values())
                .filter(r -> r.getTitle().equals(key))
                .findAny()
                .orElse(GUEST);
    }
}

//package com.rineaubie.api.config.auth.dto;
//
//import com.rineaubie.api.config.auth.entity.Role;
//import com.rineaubie.api.config.auth.entity.User;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
//@Getter
//public class OauthAttributes {
//    private Map<String, Object> attributes;
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//    private String pictue;
//
//    @Builder
//    public OauthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String pictue) {
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.email = email;
//        this.pictue = pictue;
//    }
//
//    public static OauthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attribues) {
//        return ofGoogle(userNameAttributeName, attribues);
//    }
//
//    private static OauthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//        return OauthAttributes.builder()
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
//                .pictue((String) attributes.get("picture"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public User toEntity() {
//        return User.builder()
//                .name(name)
//                .email(email)
//                .picture(pictue)
//                .role(Role.GUEST)
//                .build();
//    }
//}

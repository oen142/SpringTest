package com.jong.admin.config.auth.dto;

import com.jong.admin.domain.user.Role;
import com.jong.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttribute {

    private Map<String , Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttribute(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttribute of(String registrationId , String userNameAttributeName , Map<String , Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver("id" , attributes);
        }
        return ofGoogle(userNameAttributeName , attributes);
    }

    private static OAuthAttribute ofGoogle(String userNameAttributeName , Map<String , Object> attributes){
        return OAuthAttribute.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    private static OAuthAttribute ofNaver(String userNameAttributeName , Map<String , Object> attributes){
        Map<String, Object> response = ((Map<String , Object>)attributes.get("response"));

        return OAuthAttribute.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}

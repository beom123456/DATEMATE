package com.beom.datemate.oauth;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String provider, Map<String, Object> attributes){
        if("google".equals(provider)){
            return new GoogleUserInfo(attributes);
        }
        else if("naver".equals(provider)){
            return new NaverUserInfo(attributes);
        }
        else if("kakao".equals(provider)){
            return new KakaoUserInfo(attributes);
        }
        throw new IllegalArgumentException("Unknown provider: " + provider);
    }

}

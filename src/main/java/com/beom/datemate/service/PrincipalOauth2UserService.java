package com.beom.datemate.service;


import com.beom.datemate.config.PrincipalDetails;
import com.beom.datemate.constant.Role;
import com.beom.datemate.entity.User;
import com.beom.datemate.repository.LoginRepository;
import com.beom.datemate.repository.SignupRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final PasswordEncoder passwordEncoder;
    private final LoginRepository loginRepository;
    private final SignupRepository signupRepository;


    public PrincipalOauth2UserService(PasswordEncoder passwordEncoder, LoginRepository loginRepository, SignupRepository signupRepository) {
        this.passwordEncoder = passwordEncoder;
        this.loginRepository = loginRepository;
        this.signupRepository = signupRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider +"_" + providerId;
        String password = passwordEncoder.encode("임의비밀번호");
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        Role role = Role.valueOf("USER");

        Optional<User> userEntity = loginRepository.findByUsername(username);

        User user = User.builder()
                .username(username)
                .name(name)
                .password(password)
                .email(email)
                .role(role)
                .provider(provider)
                .providerId(providerId)
                .build();

        if(userEntity.isEmpty()){
            signupRepository.save(user);
        }
        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}

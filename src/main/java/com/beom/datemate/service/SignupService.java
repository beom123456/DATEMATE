package com.beom.datemate.service;



import com.beom.datemate.dto.SignupDto;
import com.beom.datemate.entity.User;
import com.beom.datemate.repository.SignupRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SignupService implements ISignupService{

    private final SignupRepository signupRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupService(SignupRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.signupRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User signup(SignupDto signupDto) {
        //validateDuplicate(signupDto);
        User user = User.createUser(signupDto, passwordEncoder, new ModelMapper());
        return signupRepository.save(user);
    }

    private void validateDuplicate(SignupDto signupDto) {
        User findUser = signupRepository.findByUsername(signupDto.getUsername());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

    }


}

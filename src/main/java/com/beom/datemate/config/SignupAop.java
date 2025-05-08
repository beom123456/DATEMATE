package com.beom.datemate.config;



import com.beom.datemate.dto.SignupDto;
import com.beom.datemate.entity.User;
import com.beom.datemate.repository.SignupRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Aspect
@Component
public class SignupAop {

    private final SignupRepository signupRepository;

    public SignupAop(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }

    @Before("execution(* com.beom.datemate.controller.SignupController.signup(..)) && args(signupDto, bindingResult)")
    public void validateDuplicate(SignupDto signupDto, BindingResult bindingResult) {

        User findUser = signupRepository.findByUsername(signupDto.getUsername());
        User findEmail = signupRepository.findByEmail(signupDto.getEmail());
        System.out.println("findUser" +findUser);

        //중복 아이디
        if (findUser != null) {
            bindingResult.addError(new FieldError("signupDto", "username", "중복된 아이디입니다."));
        }
        
        //비밀번호, 비밀번호확인 불일치
        if(!signupDto.getPassword().equals(signupDto.getCheckPassword())){
            bindingResult.addError(new FieldError("signupDto", "checkPassword", "비밀번호와 비밀번호 확인이 일치하지 않습니다."));
        }

        //이메일 중복
        if(findEmail != null){
            bindingResult.addError(new FieldError("signupDto", "email", "등록된 이메일입니다."));
        }
    }
}
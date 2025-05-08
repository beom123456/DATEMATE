package com.beom.datemate.controller;



import com.beom.datemate.dto.SignupDto;
import com.beom.datemate.service.ISignupService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final ISignupService signupService;

    public SignupController(ISignupService signupService) {
        this.signupService = signupService;
    }

    //회원가입
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        return "signup/signupForm";
    }

    //회원가입
    @PostMapping("/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signup/signupForm";
        }

        signupService.signup(signupDto);

        return "redirect:/login";
    }



}

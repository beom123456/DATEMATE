package com.beom.datemate.service;


import com.beom.datemate.dto.SignupDto;
import com.beom.datemate.entity.User;

public interface ISignupService {
    User signup(SignupDto signupDto);
}

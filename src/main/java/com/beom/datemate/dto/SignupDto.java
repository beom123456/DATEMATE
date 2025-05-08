package com.beom.datemate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignupDto {

    @NotEmpty(message="이름이 입력되지 않았습니다.")
    private String name;

    @NotEmpty(message="아이디가 입력되지 않았습니다.")
    private String username;

    @NotEmpty(message = "비밀번호가 입력되지 않았습니다.")
    @Length(min=8, max=20, message="비밀번호는 최소8글자입니다.")
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).*$", message = "비밀번호에는 적어도 하나의 특수문자를 포함해야 합니다.")
    private String password;

    @NotEmpty(message = "비밀번호가 입력되지 않았습니다.")
    @Length(min=8, max=20, message="비밀번호는 최소8글자입니다.")
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).*$", message = "비밀번호에는 적어도 하나의 특수문자를 포함해야 합니다.")
    private String checkPassword;

    @NotEmpty(message="이메일이 입력되지 않았습니다.")
    @Email(message="이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message="주소가 입력되지 않았습니다.")
    private String address;

}

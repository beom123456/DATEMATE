package com.beom.datemate.entity;


import com.beom.datemate.constant.Role;
import com.beom.datemate.dto.SignupDto;
import groovy.transform.ToString;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name="user_table")
public class User {

    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    @Column(name="user_idx")
    private Long idx;

    @Column(name="user_name")
    private String username;

    private String name;

    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private Timestamp timestamp;

    private String provider;
    private String providerId;

    private static ModelMapper modelMapper = new ModelMapper();

    public static User createUser(SignupDto signupDto, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        User user = modelMapper.map(signupDto, User.class);
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setRole(Role.USER);
        return user;
    }

    public SignupDto entityToDto(User user){
        SignupDto signupDto = modelMapper.map(user, SignupDto.class);
        return signupDto;
    }

    @Builder
    public User(String username, String password, String email, Role role, String provider, String providerId, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}

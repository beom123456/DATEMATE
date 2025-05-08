package com.beom.datemate.repository;


import com.beom.datemate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignupRepository extends JpaRepository<User, Long> {

    //User findByUserId(String username);
    User findByUsername(String username);
    User findByEmail(String email);
    //Optional<User> findByUserId(String userId);
}

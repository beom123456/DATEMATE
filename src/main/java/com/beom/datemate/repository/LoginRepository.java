package com.beom.datemate.repository;


import com.beom.datemate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    //User findByUsername(String username);
}

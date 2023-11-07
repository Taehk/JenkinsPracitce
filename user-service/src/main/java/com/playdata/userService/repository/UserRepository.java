package com.playdata.userService.repository;

import com.playdata.userService.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserId(String userId);
    Optional<User> findUserByUuid(String uuid);
}

package com.rineaubie.api.repository.user;

import com.rineaubie.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserId(String userId);
    Optional<User> findByEmail(String email);


}

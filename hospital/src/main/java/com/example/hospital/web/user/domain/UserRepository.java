package com.example.hospital.web.user.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{


    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByuserName(String userName);
}

package com.music.tagger.persistence.repository;

import com.music.tagger.exceptions.UserNotFoundException;
import com.music.tagger.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email) throws UserNotFoundException;

    boolean existsUserByEmail(String email);
}

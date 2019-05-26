package com.music.tagger.service;

import com.music.tagger.exceptions.UserAlreadyExistsException;
import com.music.tagger.persistence.entity.User;
import com.music.tagger.persistence.repository.RoleRepository;
import com.music.tagger.persistence.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collections;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(@NonNull User newUser) throws UserAlreadyExistsException, RoleNotFoundException {
        if (userRepository.existsUserByEmail(newUser.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address: " + newUser.getEmail());
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setEnabled(true);
        newUser.setRoles(Collections.singletonList((roleRepository.findByName(ROLE_USER).orElseThrow(RoleNotFoundException::new))));
        return userRepository.saveAndFlush(newUser);
    }
}

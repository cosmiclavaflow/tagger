package com.music.tagger.service;

import com.music.tagger.exceptions.UserAlreadyExistsException;
import com.music.tagger.persistence.entity.User;

import javax.management.relation.RoleNotFoundException;

public interface UserService {

    User registerNewUser(User newUser) throws UserAlreadyExistsException, RoleNotFoundException;
}

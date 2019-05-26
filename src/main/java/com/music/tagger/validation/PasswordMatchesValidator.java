package com.music.tagger.validation;

import com.music.tagger.controller.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<CheckPasswordsMatch, Object> {

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
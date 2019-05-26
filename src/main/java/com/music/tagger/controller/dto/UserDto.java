package com.music.tagger.controller.dto;

import com.music.tagger.validation.CheckPasswordsMatch;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@CheckPasswordsMatch
public class UserDto {

    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;

    @NotNull
    @Email(message = "{Format.userDto.email}")
    private String email;

    @NotNull
    @Size(min = 2, max = 25)
    private String password;

    @NotNull
    @Size(min = 2, max = 25)
    private String matchingPassword;
}

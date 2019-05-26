package com.music.tagger.controller;

import com.music.tagger.controller.dto.UserDto;
import com.music.tagger.controller.response.BasicResponse;
import com.music.tagger.persistence.entity.User;
import com.music.tagger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationController {

    private final UserService userService;

    private final ModelMapper mapper;

    @PostMapping("/registration")
    public BasicResponse showRegistrationPage(@Valid UserDto newUserAccount, HttpServletRequest request) throws RoleNotFoundException {
        User newUser = new User();
        mapper.map((newUserAccount), newUser);
        userService.registerNewUser(newUser);
        //TODO: add OnRegistrationCompleteEvent which will send verification message to email
        return new BasicResponse("success");
    }

}

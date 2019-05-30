package com.music.tagger.service;

import com.music.tagger.exceptions.RoleNotFoundException;
import com.music.tagger.exceptions.UserAlreadyExistsException;
import com.music.tagger.exceptions.UserNotFoundException;
import com.music.tagger.persistence.entity.User;
import com.music.tagger.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setFirstName("David");
        user.setLastName("Heller");
        user.setEmail("legion@test.com");
        user.setPassword("qw");
    }

    @Test
    @Transactional
    @Rollback
    public void shouldRegisterUser() throws Exception {
        userService.registerNewUser(user);
        assertNotNull(userRepository.findByEmail(user.getEmail()));
    }


    @Transactional
    @Rollback
    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrowUserAlreadyExistsException() throws RoleNotFoundException, UserAlreadyExistsException, UserNotFoundException {
        User duplicateUser = user;
        duplicateUser.setFirstName("David2");
        duplicateUser.setLastName("Heller2");

        userService.registerNewUser(user);
        assertNotNull(userRepository.findByEmail(user.getEmail()));
        userService.registerNewUser(duplicateUser);
    }
}
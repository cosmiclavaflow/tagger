package com.music.tagger.listeners;

import com.music.tagger.exceptions.PrivilegeNotFoundException;
import com.music.tagger.exceptions.RoleNotFoundException;
import com.music.tagger.exceptions.UserNotFoundException;
import com.music.tagger.persistence.entity.Privilege;
import com.music.tagger.persistence.entity.Role;
import com.music.tagger.persistence.entity.User;
import com.music.tagger.persistence.repository.PrivilegeRepository;
import com.music.tagger.persistence.repository.RoleRepository;
import com.music.tagger.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // API

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event)  {
        if (alreadySetup) {
            return;
        }


        try {
            //create initial privileges
            Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
            Privilege passwordPrivilege = null;
            passwordPrivilege = createPrivilegeIfNotFound("CHANGE_PASSWORD_PRIVILEGE");

            //create initial roles
            List<Privilege> adminPrivileges = new ArrayList<Privilege>(Arrays.asList(readPrivilege, writePrivilege, passwordPrivilege));
            List<Privilege> userPrivileges = new ArrayList<Privilege>(Arrays.asList(readPrivilege, passwordPrivilege));
            Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
            createRoleIfNotFound("ROLE_USER", userPrivileges);

            //create initial user
            createUserIfNotFound("test@test.com", "Test", "Test", "test", new ArrayList<>(Arrays.asList(adminRole)));

            alreadySetup = true;
        } catch (PrivilegeNotFoundException | UserNotFoundException | RoleNotFoundException e) {
            log.error(e.getMessage());
            alreadySetup = false;
        }
    }

    @Transactional
    protected Privilege createPrivilegeIfNotFound(String name) throws PrivilegeNotFoundException {
        Privilege privilege = privilegeRepository.findByName(name).orElse(null);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    protected Role createRoleIfNotFound(String name, List<Privilege> privileges) throws RoleNotFoundException {
        Role role = roleRepository.findByName(name).orElseGet(() -> new Role(name));

        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    protected User createUserIfNotFound(String email, String firstName, String lastName, String password, List<Role> roles) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(true);
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }
}

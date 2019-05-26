package com.music.tagger.persistence.repository;

import com.music.tagger.exceptions.RoleNotFoundException;
import com.music.tagger.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name) throws RoleNotFoundException;
}

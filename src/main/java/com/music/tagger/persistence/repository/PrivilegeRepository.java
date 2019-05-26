package com.music.tagger.persistence.repository;

import com.music.tagger.exceptions.PrivilegeNotFoundException;
import com.music.tagger.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByName(String name) throws PrivilegeNotFoundException;
}

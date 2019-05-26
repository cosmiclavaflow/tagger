package com.music.tagger.persistence.repository;

import com.music.tagger.persistence.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}

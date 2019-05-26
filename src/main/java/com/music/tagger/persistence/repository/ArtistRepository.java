package com.music.tagger.persistence.repository;

import com.music.tagger.persistence.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}

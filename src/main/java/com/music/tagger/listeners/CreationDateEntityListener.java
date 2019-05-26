package com.music.tagger.listeners;

import com.music.tagger.persistence.entity.superclass.BasicEntity;

import javax.persistence.PrePersist;
import java.time.LocalDate;

public class CreationDateEntityListener {

    @PrePersist
    private void prePersist(BasicEntity entity) {
        entity.setCreationDate(LocalDate.now());
    }

}

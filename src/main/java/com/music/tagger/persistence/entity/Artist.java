package com.music.tagger.persistence.entity;


import com.music.tagger.persistence.entity.superclass.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Artist")
@Table(name = "artists")
@EqualsAndHashCode(callSuper = true)
public class Artist extends NamedEntity {

    @Column(name = "photo_url")
    @ElementCollection
    private List<String> photoUrl = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Album> albumList = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Track> trackList = new ArrayList<>();

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + getId() +
                ", name='" + name  +
                ", photoUrl=" + photoUrl +
                ", albumList=" + albumList +
                ", trackList=" + trackList +
                '}';
    }
}

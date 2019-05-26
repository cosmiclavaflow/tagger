package com.music.tagger.persistence.entity;

import com.music.tagger.persistence.entity.superclass.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "Track")
@Table(name = "tracks")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Track extends NamedEntity {

    @Column(name = "number_in_album")
    private int numberInAlbum;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    private Album album;

    @Override
    public String toString() {
        return "Track{" +
                "id=" + getId() +
                ", name='" + name +
                ", numberInAlbum=" + numberInAlbum +
                ", artist=" + artist +
                ", album=" + album +
                '}';
    }
}

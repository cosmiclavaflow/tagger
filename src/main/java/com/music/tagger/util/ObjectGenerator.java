package com.music.tagger.util;

import com.music.tagger.persistence.entity.Album;
import com.music.tagger.persistence.entity.Artist;
import com.music.tagger.persistence.entity.Track;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class ObjectGenerator {

    public static Track generateTrackHierarchy(){
        Artist artist = new Artist();
        Album album = new Album();
        Track track = new Track();

        artist.setTrackList(Arrays.asList(track));
        artist.setAlbumList(Arrays.asList(album));

        album.setArtist(artist);
        album.setTrackList(Arrays.asList(track));

        track.setArtist(artist);
        track.setAlbum(album);

        return track;
    }
}

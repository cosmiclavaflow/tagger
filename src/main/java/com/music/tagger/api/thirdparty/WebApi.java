package com.music.tagger.api.thirdparty;

import com.music.tagger.persistence.entity.Track;

public interface WebApi {

    Track findTrack(String artistName, String trackName) throws Exception;
}

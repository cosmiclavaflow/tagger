package com.music.tagger.api.thirdparty.dto;

import lombok.Data;

@Data
public class ApiTrackDto {

    private String artistName;

    private String albumName;

    private String trackName;

    private String albumCoverUrl;
}

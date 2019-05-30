package com.music.tagger.api.thirdparty.musixmatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.tagger.api.thirdparty.WebApi;
import com.music.tagger.api.thirdparty.dto.ApiTrackDto;
import com.music.tagger.persistence.entity.Track;
import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@PropertySource(value = "classpath:credentials.properties")
public class MusixmatchWebApi implements WebApi {

    @Value("${musixmatch.apikey}")
    private String apiKey;

    @Override
    public Track findTrack(String artistName, String trackName) throws Exception {
        List<Track> trackList;
//        TrackSeachMessage message;
        Map<String, String> requestParams = new HashMap<>();

        requestParams.put(RequestConstants.API_KEY, apiKey);
        requestParams.put(RequestConstants.QUERY_ARTIST, artistName);
        requestParams.put(RequestConstants.QUERY_TRACK, trackName);
        requestParams.put(RequestConstants.TRACK_ORDER, "desc");
//        requestParams.put(RequestConstants.PAGE, page);
//        requestParams.put(RequestConstants.PAGE_SIZE, pageSize);

        String response;

        try {
            response = MusixMatchRequest.sendRequest(UrlFormater.formatParamsToString(Methods.TRACK_SEARCH, requestParams));
        } catch (MusixMatchException e) {
            throw new Exception();
        }

        ObjectMapper objectMapper = new ObjectMapper();
//        jsonNodeToTrackList(objectMapper.readTree(response));

        CoverArtArchiveClient client = new DefaultCoverArtArchiveClient();


        return null;
    }

    private List<ApiTrackDto> jsonNodeToTrackList(JsonNode rawResponse) {
        List<JsonNode> jsonNode = rawResponse.findValues("track");

        List<ApiTrackDto> trackList = new ArrayList<>();
        for (JsonNode singleNode :jsonNode) {
            ApiTrackDto trackDto = new ApiTrackDto();
            trackDto.setTrackName(singleNode.findValue("track_name").textValue());
            trackDto.setAlbumName(singleNode.findValue("album_name").textValue());
            trackDto.setArtistName(singleNode.findValue("artist_name").textValue());
            trackList.add(trackDto);
        }

        return null;
    }
}

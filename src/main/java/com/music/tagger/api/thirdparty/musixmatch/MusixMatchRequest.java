package com.music.tagger.api.thirdparty.musixmatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class MusixMatchRequest {

    public static String sendRequest(String request) throws MusixMatchException {
        StringBuilder response = new StringBuilder();

        try {

            String apiUrl = RequestConstants.API_URL + RequestConstants.API_VERSION
                    + RequestConstants.URL_DELIM + request;

            URL url = new URL(apiUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");


            BufferedReader in = new BufferedReader(new InputStreamReader(
                    url.openStream() /*urlConnection.getInputStream()*/, StandardCharsets.UTF_8));
            String str;

            while ((str = in.readLine()) != null) {
                response.append(str);
            }

            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new MusixMatchException(e.getMessage());
        }

        return response.toString();
    }
}

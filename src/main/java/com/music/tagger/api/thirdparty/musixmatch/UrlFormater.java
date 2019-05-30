package com.music.tagger.api.thirdparty.musixmatch;

import lombok.experimental.UtilityClass;

import java.net.URLEncoder;
import java.util.Map;

@UtilityClass
public class UrlFormater {


    public static String formatParamsToString(String methodName, Map<String, String> requestParams) {
        StringBuilder paramString = new StringBuilder();

        paramString.append(methodName).append("?");

        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            paramString.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue()));
            paramString.append("&");
        }

        paramString = new StringBuilder(paramString.substring(0, paramString.length() - 1));

        return paramString.toString();
    }
}

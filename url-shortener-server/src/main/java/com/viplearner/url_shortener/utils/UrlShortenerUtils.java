package com.viplearner.url_shortener.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlShortenerUtils {
    public static boolean isValidUrl(String urlString) {
        if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
            urlString = "http://" + urlString;  // Add default protocol
        }
        try {
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}

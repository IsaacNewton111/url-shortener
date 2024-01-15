package com.viplearner.url_shortener.resource;

import com.viplearner.url_shortener.backend.DatabaseBackend;
import com.viplearner.url_shortener.dto.UrlObject;
import com.viplearner.url_shortener.resource.responses.GetShortUrlResponse;
import com.viplearner.url_shortener.utils.HexUtil;
import com.viplearner.url_shortener.utils.UrlShortenerUtils;
import org.postgresql.util.PSQLException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetShortUrlResource implements GetShortUrlService {
    private final DatabaseBackend databaseBackend;

    public GetShortUrlResource(DatabaseBackend databaseBackend) {
        this.databaseBackend = databaseBackend;
    }
    @Override
    public GetShortUrlResponse getShortUrl(final String url) {
        System.out.printf("Received request to shorten url %s%n", url);
        //check if url is valid
        if(!UrlShortenerUtils.isValidUrl(url)){
            return new GetShortUrlResponse( "Invalid url", null);
        }

        String urlString = url;

        if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
            urlString = "http://" + urlString;
        }

        //generate shortenedurl using hashing
        String shortUrl = getShortenedUrl();
        if(shortUrl == null){
            return new GetShortUrlResponse("Error generating short url", null);
        }

        //add to database
        try {
            databaseBackend.insertUrl(new UrlObject(urlString, shortUrl));
        }catch (Exception e){
            System.out.printf("Exception: %s", e);
            return new GetShortUrlResponse("Looks like you already generated this url", String.format("%s",shortUrl));
        }
        return new GetShortUrlResponse("Success", String.format("%s",shortUrl));
    }

    private String getShortenedUrl(){
        while(true){
            final String shortenedUrl = generateRandomString(6);
            UrlObject urlObject = databaseBackend.findByShortUrl(shortenedUrl);
            if(urlObject == null){
                return shortenedUrl;
            }
        }
    }

    public String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) Math.floor(Math.random() * charPool.length());
            sb.append(charPool.charAt(randomIndex));
        }
        return sb.toString();
    }
}

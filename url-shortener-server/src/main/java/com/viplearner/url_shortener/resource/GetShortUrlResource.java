package com.viplearner.url_shortener.resource;

import com.viplearner.url_shortener.backend.DatabaseBackend;
import com.viplearner.url_shortener.dto.UrlObject;
import com.viplearner.url_shortener.resource.responses.GetShortUrlResponse;
import com.viplearner.url_shortener.utils.HexUtil;
import com.viplearner.url_shortener.utils.UrlShortenerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import javax.naming.spi.ResolveResult;
import javax.ws.rs.core.Response;
import org.postgresql.util.PSQLException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Api(
        value = "get URL",
        description = "Get short URL"
)
public class GetShortUrlResource implements GetShortUrlService {
    private final DatabaseBackend databaseBackend;

    public GetShortUrlResource(DatabaseBackend databaseBackend) {
        this.databaseBackend = databaseBackend;
    }
    @Override
    @ApiOperation(
            value = "Get short URL",
            response = Response.class
    )
    @ApiResponse(
            code = 200,
            message = "Short URL successfully created",
            response = Response.class
    )
    public Response getShortUrl(final String url) {
        //check if url is valid
        if(!UrlShortenerUtils.isValidUrl(url)){
            return Response.ok(new GetShortUrlResponse( "Invalid url", null)).build();
        }

        String urlString = url;

        if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
            urlString = "http://" + urlString;
        }

        //generate shortenedurl using hashing
        String shortUrl = getShortenedUrl();
        if(shortUrl == null){
            return Response.ok(new GetShortUrlResponse("Error generating short url", null)).build();
        }

        //add to database
        try {
            databaseBackend.insertUrl(new UrlObject(urlString, shortUrl));
        }catch (Exception e){
            System.out.printf("Exception: %s", e);
            return Response.ok(new GetShortUrlResponse("Looks like you already generated this url", String.format("%s",shortUrl))).build();
        }
        return Response.ok(new GetShortUrlResponse("Success", String.format("%s",shortUrl))).build();
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

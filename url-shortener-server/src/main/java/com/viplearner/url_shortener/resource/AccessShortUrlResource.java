package com.viplearner.url_shortener.resource;

import com.viplearner.url_shortener.backend.DatabaseBackend;
import com.viplearner.url_shortener.dto.UrlObject;

import javax.ws.rs.core.Response;
import java.net.URI;

public class AccessShortUrlResource implements AccessShortUriService{
    DatabaseBackend databaseBackend;
    public AccessShortUrlResource(DatabaseBackend databaseBackend) {
        this.databaseBackend = databaseBackend;
    }

    @Override
    public Response accessShortUrl(final String url) {
        UrlObject urlObject = databaseBackend.findByShortUrl(url);

        if (urlObject == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        System.out.printf("Url: %s%n", urlObject.getUrl());
//        URI uri =
//        System.out.printf("url ");
        return Response.seeOther(URI.create(urlObject.getUrl())).build();
    }
}

package com.viplearner.url_shortener.resource;

import com.viplearner.url_shortener.resource.responses.GetShortUrlResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/{short-url}")
public interface AccessShortUriService {
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    Response accessShortUrl(@PathParam("short-url") String url);
}

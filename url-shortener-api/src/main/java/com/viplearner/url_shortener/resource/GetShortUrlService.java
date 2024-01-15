package com.viplearner.url_shortener.resource;

import com.viplearner.url_shortener.resource.responses.GetShortUrlResponse;

import javax.ws.rs.*;

@Path("get-short-url")
public interface GetShortUrlService {

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    GetShortUrlResponse getShortUrl(@QueryParam("url") String url);
}

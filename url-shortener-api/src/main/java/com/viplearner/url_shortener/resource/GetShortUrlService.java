package com.viplearner.url_shortener.resource;

import com.viplearner.url_shortener.resource.responses.GetShortUrlResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("url")
public interface GetShortUrlService {

    @GET
    @Path("/get")
    @Produces("application/json")
    Response getShortUrl(@QueryParam("url") String url);
}

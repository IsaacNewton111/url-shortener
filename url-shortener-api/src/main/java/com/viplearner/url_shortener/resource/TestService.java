package com.viplearner.url_shortener.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("test-service")
public interface TestService {

    @GET
    @Path("/ping")
    Response ping(@QueryParam("pong") String pong);

}

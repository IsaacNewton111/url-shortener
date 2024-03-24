package com.viplearner.url_shortener.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import javax.ws.rs.core.Response;

@Api(
        value = "ping",
        description = "Test resource"
)
public class TestResource implements TestService {

    @Override
    @ApiOperation(
            value = "Ping",
            response = Response.class

    )
    @ApiResponse(
            code = 200,
            message = "Pong",
            response = Response.class
    )
    public Response ping(String pong) {
        return Response.ok("ping-" + pong).build();
    }

}
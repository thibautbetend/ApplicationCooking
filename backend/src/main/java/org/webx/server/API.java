package org.webx.server;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.grizzly.http.server.Request;


@Path("findCountry")
public class API {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public APIResponse getIt(
        @DefaultValue("") @QueryParam("country") String country,
        @Context Request request
    ) {
        return new ApiHandler().handleCall(country, request);
    }

}

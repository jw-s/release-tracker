package com.github.joelws.release.tracker.endpoint.release;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/release")
@Api(value = "/release", description = "Endpoint to interact with releases")
public class DeleteReleaseEndpoint extends ResourceEndpoint<String> {

    public DeleteReleaseEndpoint(BusinessService<String> service) {
        super(service);
    }

    @DELETE
    @Path("/{name}")
    @ApiOperation(value = "Delete Release")
    @Override
    public Response method(@ApiParam(required = true) @PathParam("name") final String name) {
        return service.delete(name);
    }
}
package com.github.joelws.release.tracker.endpoint.release

import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint
import javax.ws.rs.Consumes
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/release")
open class UpdateReleaseEndpoint(service: BusinessService<String>) : ResourceEndpoint<String>(service) {

    @PUT
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    fun update(json: String) = service.update(json)
}
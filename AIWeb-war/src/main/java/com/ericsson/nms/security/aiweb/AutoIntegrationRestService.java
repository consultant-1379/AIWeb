package com.ericsson.nms.security.aiweb;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * AutoIntegrationRestService class is used to provide authenticated access to
 * the initial configuration files for the newly integrated NEs. AIWeb component
 * is used by the nodes to download Initial Configuration files as a part of the
 * auto integration process by exposing the HTTPS interface by RESTful services.
 * 
 * AIWeb authenticates the clients using a public key certificate.
 * 
 * @author edobpet
 * 
 */

@Path("/")
public class AutoIntegrationRestService{

	@GET
	@Path("/{param}")
	public Response getConfigFile(@PathParam("param") String fileName) {
		
		
		String result = "File name passed is " + fileName;
		System.out.println(result);
		return Response.status(200).entity(result).build();
	}
}

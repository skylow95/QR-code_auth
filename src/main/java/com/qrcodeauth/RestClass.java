package com.qrcodeauth;

import com.qrcodeauth.service.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestClass {

	private Service service = new Service();

	@GET
	@Path("/create-qr-code")
	@Produces("image/png")
	public Response getQRCodeImage(@QueryParam("uuid") String uuid) throws Exception {
		Response.ResponseBuilder ok = Response.ok(service.getQRCode(uuid));
		ok.header("Content-Disposition", "attachment; filename=image.png");
		return ok.build();
	}
}

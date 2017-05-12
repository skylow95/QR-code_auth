package com.qrcodeauth;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.qrcodeauth.service.Service;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	public Response getQRCodeImage() throws Exception {
		Response.ResponseBuilder ok = Response.ok(service.getQRCode());
		ok.header("Content-Disposition", "attachment; filename=image.png");
		return ok.build();
	}

	@POST
	@Path("/check-qr-code")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response checkQRCode(MultipartBody multipartBody) throws FormatException, ChecksumException, NotFoundException, IOException {
		return Response.ok(service.decryptQRCode(multipartBody)).build();
	}
}

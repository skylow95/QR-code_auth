package com.qrcodeauth;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.qrcodeauth.dto.UserCheckRestDto;
import com.qrcodeauth.service.Service;
import com.qrcodeauth.service.UserCheckService;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Path("/rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestClass {

	private Service service = new Service();
	private UserCheckService userCheckService = new UserCheckService();


	@GET
	@Path("/create-qr-code")
	@Produces("image/png")
	public Response getQRCodeImage() throws Exception {
		Response.ResponseBuilder ok = Response.ok(service.getQRCode());
		ok.header("Content-Disposition", "attachment; filename=image.png");
		return ok.build();
	}

	@POST
	@Path("/decrypt-qr-code")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response decryptQRCode(MultipartBody multipartBody) throws Exception {
		return Response.ok(service.decryptQRCode(multipartBody)).build();
	}

	@POST
	@Path("/user-check")
	public Response checkUser(UserCheckRestDto request) throws NoSuchAlgorithmException {
		return Response.ok(userCheckService.checkUser(request)).build();
	}
}

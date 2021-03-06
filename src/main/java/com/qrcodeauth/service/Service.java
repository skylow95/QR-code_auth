package com.qrcodeauth.service;

import com.qrcodeauth.utils.QRCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class Service {

	public File getQRCode(String value) throws Exception {
		BufferedImage image = QRCodeUtils.generateQRCode(value);
		File myNewPNGFile = new File("ImageAsPNG.png");
		if (image != null) {
			ImageIO.write(image, "png", myNewPNGFile);
		}

		return myNewPNGFile;

	}

	public String decryptQRCode(MultipartBody multipartBody) throws Exception {
		for (Attachment attachment : multipartBody.getAllAttachments()) {
			if ("file".equalsIgnoreCase(attachment.getContentDisposition().getParameter("name"))) {
				return decryptQRCode(attachment.getDataHandler().getInputStream());
			}
		}

		return StringUtils.EMPTY;
	}

	private String decryptQRCode(InputStream inputStream) throws Exception {
		return QRCodeUtils.decryptQRCode(inputStream);
	}
}

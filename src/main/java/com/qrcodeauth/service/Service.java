package com.qrcodeauth.service;

import com.qrcodeauth.utils.QRCodeUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Service {

	public File getQRCode(String inputUUID) throws Exception {

		UUID uuid = UUID.fromString(inputUUID);


		BufferedImage image = QRCodeUtils.generateQRCode(uuid);
		File myNewPNGFile = new File("ImageAsPNG.png");
		if (image != null) {
			ImageIO.write(image, "png", myNewPNGFile);
		}

		return myNewPNGFile;

	}
}

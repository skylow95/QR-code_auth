package com.qrcodeauth.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public class QRCodeUtils {

	public static BufferedImage generateQRCode(UUID uuid) throws Exception {

		int size = 250;
		try {

			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			Integer lengthOTP = (int) (Math.random() * 36);
			Integer sessionKey = (int) (Math.random() * 100);

			QRCodeWriter qrCodeWriter = new QRCodeWriter();

			BitMatrix byteMatrix = qrCodeWriter.encode(
					uuid.toString(),
					BarcodeFormat.QR_CODE,
					size,
					size,
					hintMap
			);

			int CrunchifyWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
					BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < CrunchifyWidth; i++) {
				for (int j = 0; j < CrunchifyWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}

			String encrypt = RSAUtils.encrypt(uuid.toString(), RSAUtils.pair.getPublic());
			String decrypt = RSAUtils.decrypt(encrypt, RSAUtils.pair.getPrivate());

			System.out.println(uuid.toString());
			System.out.println(decrypt);
			System.out.println(decrypt.equals(uuid.toString()));

			return image;

		} catch (WriterException e) {
			return null;
		}
	}
}

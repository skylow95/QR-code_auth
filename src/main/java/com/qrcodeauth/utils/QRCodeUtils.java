package com.qrcodeauth.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qrcodeauth.model.AuthincationModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeUtils {

	public static BufferedImage generateQRCode() throws Exception {
		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		int size = 250;

		try {
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			Integer lengthOTP = (int) (Math.random() * 10) + 1;
			Integer sessionKey = (int) (Math.random() * 100);

			new AuthincationModel(sessionKey, lengthOTP);

			String plainText = AuthincationModel.getLengthOTP().toString() + AuthincationModel.getSessionKey().toString();

			String encrypt = RSAUtils.encrypt(plainText, RSAUtils.pair.getPublic());
			System.out.println(encrypt);

			QRCodeWriter qrCodeWriter = new QRCodeWriter();

			BitMatrix byteMatrix = qrCodeWriter.encode(
					encrypt,
					BarcodeFormat.QR_CODE,
					size,
					size,
					hintMap
			);

			int imageWidth = byteMatrix.getWidth();
			image = new BufferedImage(imageWidth, imageWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, imageWidth, imageWidth);
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < imageWidth; i++) {
				for (int j = 0; j < imageWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}

		} catch (WriterException e) {
			e.getStackTrace();
		}

		return image;
	}

	public static String decryptQRCode(InputStream inputStream)
			throws FormatException, ChecksumException, NotFoundException, IOException {
		Map<DecodeHintType, Object> hintMap = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
		hintMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		QRCodeReader qrCodeReader = new QRCodeReader();

		BufferedImage image = ImageIO.read(inputStream);
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = qrCodeReader.decode(bitmap, hintMap);

		return result.getText();
	}
}

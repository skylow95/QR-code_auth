package com.qrcodeauth.service;

import com.qrcodeauth.dto.UserCheckRestDto;
import com.qrcodeauth.model.AuthincationModel;
import com.qrcodeauth.utils.HashUtils;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
public class UserCheckService {

	public Boolean checkUser(UserCheckRestDto request) throws NoSuchAlgorithmException {
		String key = request.getKey();
		String hashedValue = HashUtils.hashedValue(AuthincationModel.getSessionKey().toString());
		byte[] bytes = hashedValue.getBytes(Charset.forName("UTF-8"));
		String hashedKey = Base64.encodeBase64String(bytes);
		hashedKey = hashedKey.substring(0, AuthincationModel.getLengthOTP() - 1);

		return hashedKey.equalsIgnoreCase(key);
	}
}

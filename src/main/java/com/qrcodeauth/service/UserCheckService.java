package com.qrcodeauth.service;

import com.qrcodeauth.dto.UserCheckRestDto;
import com.qrcodeauth.model.WebSessionsHandler;
import com.qrcodeauth.utils.RSAUtils;
import org.apache.xml.security.exceptions.Base64DecodingException;
import org.apache.xml.security.utils.Base64;

import javax.websocket.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
public class UserCheckService {

	public Boolean checkUser(UserCheckRestDto request) throws NoSuchAlgorithmException, Base64DecodingException, UnsupportedEncodingException {
		String key = request.getKey();
		String token = request.getToken();

		byte[] bytes = Base64.decode(key);
		String decryptedKey = new String(bytes, RSAUtils.UTF_8);
		Session session = (Session) WebSessionsHandler.getInstance().getQrSessionsMap().get(decryptedKey);
		if (session != null) {

		}
		return false;
	}
}

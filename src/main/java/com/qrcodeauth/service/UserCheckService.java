package com.qrcodeauth.service;

import com.qrcodeauth.model.WebSessionsHandler;
import com.qrcodeauth.utils.RSAUtils;
import org.apache.xml.security.exceptions.Base64DecodingException;
import org.apache.xml.security.utils.Base64;

import javax.websocket.Session;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
public class UserCheckService {

	public Boolean checkUser(String key, String token) throws NoSuchAlgorithmException, Base64DecodingException, IOException {
		byte[] bytes = Base64.decode(key);
		String decryptedKey = new String(bytes, RSAUtils.UTF_8);
		Session session = WebSessionsHandler.getInstance().getQrSessionsMap().get(decryptedKey).getSession();
		if (session != null) {
			session.getBasicRemote().sendText("wsloginuser###" + token);
			return true;
		}

		return false;
	}
}

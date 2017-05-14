package com.qrcodeauth.model;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public class WebSessionsHandler {

	private static WebSessionsHandler ourInstance = new WebSessionsHandler();

	public static WebSessionsHandler getInstance() {
		if (ourInstance == null) {
			synchronized (WebSessionsHandler.class) {
				if (ourInstance == null) {
					return new WebSessionsHandler();
				}
			}
		}
		return ourInstance;
	}

	private WebSessionsHandler() {
	}

	private Map<String, WebSession> qrSessionsMap = new HashMap<String, WebSession>();
	private Map<String, WebSession> authSessionsMap = new HashMap<String, WebSession>();

	public void createNewQRSession(String qrCode, Session session) {
		clearQRSession(qrCode);

		qrSessionsMap.put(qrCode,
				new WebSession(qrCode, session)
		);
	}

	public WebSession assignQRtoUser(String qrCode, String user) {
		if (qrSessionsMap.containsKey(qrCode)) {

			WebSession session = qrSessionsMap.get(qrCode);
			qrSessionsMap.remove(qrCode);

			session.setUser(user);
			authSessionsMap.put(user, session);

			return session;
		}

		return null;
	}

	public void clearQRSession(String qrCode) {
		if (qrSessionsMap.containsKey(qrCode)) {

			WebSession session = qrSessionsMap.get(qrCode);
			session.setSession(null);
			qrSessionsMap.remove(qrCode);
		}
	}
}


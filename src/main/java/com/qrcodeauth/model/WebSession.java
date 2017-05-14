package com.qrcodeauth.model;

import javax.websocket.Session;

public class WebSession {

	private String user = null;
	private String qrCode = null;
	private Session session = null;

	public WebSession(String qrCode, Session session) {
		this.qrCode = qrCode;
		this.session = session;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}

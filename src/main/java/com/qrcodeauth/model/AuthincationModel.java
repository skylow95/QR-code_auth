package com.qrcodeauth.model;

public class AuthincationModel {

	private static Integer sessionKey;

	private static Integer lengthOTP;

	public AuthincationModel(Integer sessionKey, Integer lengthOTP) {
		AuthincationModel.sessionKey = sessionKey;
		AuthincationModel.lengthOTP = lengthOTP;
	}

	public static Integer getSessionKey() {
		return sessionKey;
	}

	public static void setSessionKey(Integer sessionKey) {
		AuthincationModel.sessionKey = sessionKey;
	}

	public static Integer getLengthOTP() {
		return lengthOTP;
	}

	public static void setLengthOTP(Integer lengthOTP) {
		AuthincationModel.lengthOTP = lengthOTP;
	}
}

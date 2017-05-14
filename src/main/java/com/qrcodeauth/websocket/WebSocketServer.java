package com.qrcodeauth.websocket;

import com.qrcodeauth.model.WebSessionsHandler;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/action")
public class WebSocketServer {

	@OnOpen
	public void open(Session session) throws IOException {
		System.out.println("New session opened: " + session.getId());
		WebSessionsHandler.getInstance().createNewQRSession(session.getId(), session);
		session.getBasicRemote().sendText("wsready###" + session.getId());
	}

	@OnClose
	public void close(Session session) {
		WebSessionsHandler.getInstance().clearQRSession(session.getId());
	}

	@OnError
	public void onError(Throwable error) {
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
	}
}

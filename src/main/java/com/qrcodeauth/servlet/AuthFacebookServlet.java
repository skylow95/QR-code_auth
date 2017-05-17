package com.qrcodeauth.servlet;

import com.qrcodeauth.facebookconfig.FBConnection;
import com.qrcodeauth.facebookconfig.FBGraph;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class AuthFacebookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accessToken = request.getParameter("token");
		if (StringUtils.isEmpty(accessToken)) {
			throw new RuntimeException("Token is absent.");
		}

		//		FBConnection fbConnection = new FBConnection(accessToken);
		HttpSession httpSession = request.getSession();
		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		String userName = fbProfileData.get("name");
		httpSession.setAttribute("user", userName);
		httpSession.setMaxInactiveInterval(30 * 60);
		Cookie cookie = new Cookie("user", userName);
		response.addCookie(cookie);
		response.sendRedirect("http://localhost:8080/main");
	}
}

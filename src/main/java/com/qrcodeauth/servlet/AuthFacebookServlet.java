package com.qrcodeauth.servlet;

import com.qrcodeauth.facebookconfig.FBConnection;
import com.qrcodeauth.facebookconfig.FBGraph;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Bogdan on 16.05.2017.
 */
public class AuthFacebookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = req.getParameter("token");
		if (StringUtils.isEmpty(accessToken)) {
			throw new RuntimeException("Token is absent.");
		}

//		FBConnection fbConnection = new FBConnection(accessToken);
		HttpSession httpSession = req.getSession();
		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		String userName = fbProfileData.get("first_name");
		//		ServletOutputStream out = res.getOutputStream();
		//		out.println("<h1>Facebook Login using Java</h1>");
		//		out.println("<h2>Application Main Menu</h2>");
//		out.println("<div>Welcome " + userName);
//		out.println("<div>Your Email: " + fbProfileData.get("email"));
//		out.println("<div>You are " + fbProfileData.get("gender"));
		httpSession.setAttribute("user", userName);
		httpSession.setMaxInactiveInterval(30 * 60);
		Cookie cookie = new Cookie("user", userName);
		res.addCookie(cookie);
		res.sendRedirect(res.encodeURL("/jsp/main.jsp"));
	}
}

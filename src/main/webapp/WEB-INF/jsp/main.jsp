<%--
  Created by IntelliJ IDEA.
  User: bogdan
  Date: 5/14/17
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<%
    String user = (String) session.getAttribute("user");
    String sessionID = session.getId();
%>
<h1>Welcome <%= user%>, Login successful through Facebook. Your session ID is <%= sessionID%></h1>
</body>
</html>

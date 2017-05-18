<%--
  Created by IntelliJ IDEA.
  User: bogdan
  Date: 5/13/17
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        const protocol = 'http://';
        const domainName = document.location.host + document.location.pathname;
        initWebSocket();

        function initWebSocket() {
            var wsUri = "ws://" + domainName + "action";
            var websocket = new WebSocket(wsUri);

            websocket.onopen = function (evt) {
                console.log("WebSocket Opened");
            };

            websocket.onclose = function (evt) {
                console.log("WebSocket Closed");
                websocket = null;
            };

            function sendRequestToAuthUser(token) {
                window.location.href = protocol + domainName + 'login-facebook?token=' + token;
            }

            websocket.onmessage = function (evt) {
                var rawdata = evt.data;

                if (rawdata.startsWith("wsready###")) {
                    var code = rawdata.split("###")[1];
                    console.log("qr received: " + code);
                    $("#qrcode").attr("src", protocol + domainName + "services/json/rest/create-qr-code?id=" + code);
                } else if (rawdata.startsWith("wsloginuser###")) {
                    var token = rawdata.split("###")[1];
                    sendRequestToAuthUser(token);
                }
            };

            websocket.onerror = function (evt) {
                console.log("Error: " + evt);
            };
        }
    </script>
</head>
<body>
<h1>Test qr code application</h1>
<div id="qrcodearea">
    <img id="qrcode" src="">
</div>
</body>
</html>

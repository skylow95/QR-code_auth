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
        console.log("Starting WhatsApp Clone");
        initWebSocket();

        function initWebSocket() {
            var domainName = document.location.host + document.location.pathname;
            var wsUri = "ws://" + domainName + "action";
            var websocket = new WebSocket(wsUri);

            websocket.onopen = function (evt) {
                console.log("WebSocket Opened");
            };

            websocket.onclose = function (evt) {
                console.log("WebSocket Closed");
                websocket = null;
                username = null;
            };

            websocket.onmessage = function (evt) {

                var rawdata = evt.data;

                if (rawdata.startsWith("wsready###")) {

                    var qrUuid = rawdata.split("###")[1];
                    console.log("UUID for qr received: " + qrUuid);
                    $("#qrcode").attr("src", "http://" + domainName + "services/json/rest/create-qr-code?id=" + qrUuid);

                    // } else if (rawdata.startsWith("authed###")) {
                    //
                    //     username = rawdata.split("###")[1];
                    //     console.log("Auth done using mobile for user: " + username);
                    //     qrcodeareaDiv.hide();
                    //     chatareaDiv.css('display', 'block');
                    //
                    // } else if (rawdata.startsWith("msg###")) {
                    //
                    //     var user = rawdata.split("###")[1];
                    //     var msg = rawdata.split("###")[2];
                    //     console.log("message from : " + user + " with data: " + msg + " \n");
                    //
                    //     if (username === user) {
                    //         // FROM ME
                    //         chatList.append("<div class=\"list-group-item list-group-item-success\">I said:" + msg + "</div>");
                    //     } else {
                    //         // FROM OTHER PEOPLE
                    //         chatList.append("<div class=\"list-group-item list-group-item-warning\" style=\"text-align: right\">" + user + " said:" + msg + "</div>");
                    //     }

                } else if (rawdata.startsWith("wsloginuser###")) {
                    token = rawdata.split("###")[1];
                    console.error("your token is " + token);
                }

            };


            websocket.onerror = function (evt) {
                console.error("Error: " + evt);
            };
        }
    </script>
</head>
<body>
<h1>TEST</h1>
<div id="qrcodearea">
    <img id="qrcode" src="">
</div>
</body>
</html>

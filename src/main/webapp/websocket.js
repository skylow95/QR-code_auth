/**
 * Created by bogdan on 5/13/17.
 */
$(document).ready(function(){
    console.log("Starting WhatsApp Clone");
    initWebSocket();
});

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
            $("#qrcode").attr("src", "http://" + domainName + "/services/json/rest/create-qr-code" + qrUuid);

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

        } else {
            console.error("Unknown Stuff: " + rawdata);
        }

    };


    websocket.onerror = function (evt) {
        console.error("Error: " + evt);
    };
}



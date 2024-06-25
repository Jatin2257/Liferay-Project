<%@page import="chat.service.service.ChatMessageLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<liferay-theme:defineObjects />

<style>
body {
	font-family: Arial, sans-serif;
}

.chatbox {
	width: 100%;
	height: 500px;
	border: 1px solid #ccc;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.chatbox-header {
	background-color: #007bff;
	color: white;
	padding: 10px;
	text-align: center;
}

.chatbox-body {
	padding: 10px;
	overflow-y: scroll;
	flex-grow: 1;
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
}

.message {
	margin-bottom: 15px;
	display: flex;
	flex-direction: column;
}

.message p {
	margin: 0;
}

.message .time {
	display: block;
	color: #999;
	font-size: 12px;
	margin-top: 5px;
}

.message.user {
	align-items: flex-end;
	text-align: right;
}

.message.user p {
	background-color: #dcf8c6;
	border-radius: 10px;
	padding: 10px;
}

.message.other {
	align-items: flex-start;
	text-align: left;
}

.message.other p {
	background-color: #f1f0f0;
	border-radius: 10px;
	padding: 10px;
}

.chatbox-footer {
	display: flex;
	padding: 10px;
	border-top: 1px solid #ccc;
}

#messageInput, #fileInput {
	flex-grow: 1;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	margin-right: 10px;
}

#sendButton {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 10px 20px;
	cursor: pointer;
	border-radius: 4px;
}

#sendButton:hover {
	background-color: #0056b3;
}
</style>

<portlet:renderURL var="cancleURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>


<div class="container-fluid container-fluid-max-xl "
	style="width: 50%; margin-top: 10px;">
	<h2 align="center">Ticket Information</h2>
	<aui:form>
		<aui:input label="Ticket Id" name="ticketId" disabled="true"
			value="${Ticket.ticketId}" />
		<aui:input label="Requester" name="requester" disabled="true"
			value="${Ticket.requester}" />
		<aui:input label="Email" name="email" disabled="true"
			value="${Ticket.email}" />
		<aui:input label="Subject" name="subject" disabled="true"
			value="${Ticket.subject}" />
		<aui:input label="Description" name="description" disabled="true"
			value="${Ticket.description}" />
		<aui:input label="Department" name="department" disabled="true"
			value="${Ticket.department}" />
		<aui:input label="Category" name="category" disabled="true"
			value="${Ticket.category}" />
		<aui:input label="Sub category" name="subcategory" disabled="true"
			value="${Ticket.subcategory}" />
		<aui:input label="Status" name="status" disabled="true"
			value="${Ticket.status}" />
		<aui:input label="Create Date" name="createdate" disabled="true"
			value="${Ticket.createdate}" />

		<a href="${cancleURL}" class="btn btn-primary">Back</a>

	</aui:form>

</div>

<hr>
<div class="container-fluid container-fluid-max-xl chatbox"
	style="width: 50%">
	<div class="chatbox-header">
		<h3>Chat</h3>
	</div>
	<div class="chatbox-body" id="chatboxBody">
		<c:forEach var="chatMessage" items="${messageByTicketId}">
			<div class="message ${chatMessage.userId == userId ? 'user' : 'other'}">
				<c:if test="${not empty chatMessage.message && not empty chatMessage.imageattached}">
					<p align="left" style="font-weight: bold;">User:
						${chatMessage.userId}</p>
					<c:if test="${not empty chatMessage.imageattached}">
						<img src="data:image/png;base64,${chatMessage.imageattached}"
							style="width: 100px; height: 100px;" alt="Image">
					</c:if>
					<p>${chatMessage.message}</p>
					<span class="time">${chatMessage.timestamp}</span>
				</c:if>
				<c:if test="${empty chatMessage.message && not empty chatMessage.imageattached}">
					<p align="left" style="font-weight: bold;">User:
						${chatMessage.userId}</p>
					<img src="data:image/png;base64,${chatMessage.imageattached}"
						style="width: 100px; height: 100px;" alt="Image">
					<span class="time">${chatMessage.timestamp}</span>
				</c:if>

				<c:if test="${not empty chatMessage.message && empty chatMessage.imageattached}">
					<p align="left" style="font-weight: bold;">User:
						${chatMessage.userId}</p>
					<p>${chatMessage.message}</p>
					<span class="time">${chatMessage.timestamp}</span>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<div class="chatbox-footer">
		<input type="text" id="messageInput" placeholder="Type a message...">
		<input type="file" id="fileInput">
		<button type="submit" id="sendButton" onclick="sendMessage(${Ticket.ticketId},${userId});">Send</button>
	</div>
</div>

<script type="text/javascript">
function sendMessage(ticketId, userId) {
    var message = document.getElementById('messageInput').value;
    var fileInput = document.getElementById('fileInput').files[0];
    console.log("message is," + message);
    console.log("ticket Id ", ticketId);
    console.log("userId ", userId);
    console.log("fileInput :: ", fileInput);

    if (!fileInput) {
        console.log("No file selected.");
        sendRequest(message, ticketId, userId, '');
    } else {
        var reader = new FileReader();
        reader.onload = function(e) {
            var arrayBuffer = e.target.result;
            var bytes = new Uint8Array(arrayBuffer);
            var binary = '';
            for (var i = 0; i < bytes.byteLength; i++) {
                binary += String.fromCharCode(bytes[i]);
            }
            var base64 = window.btoa(binary);

            console.log("Message:", message);
            console.log("Ticket ID:", ticketId);
            console.log("User ID:", userId);
            console.log("Base64:", base64);

            sendRequest(message, ticketId, userId, base64);
        };
        reader.readAsArrayBuffer(fileInput);
    }
}

function sendRequest(message, ticketId, userId, base64) {
    AUI().use('aui-io-request', function(A) {
        A.io.request('<portlet:resourceURL id="sendMessage" />', {
            method: 'POST',
            data: {
                '<portlet:namespace />message': message,
                '<portlet:namespace />userId': userId,
                '<portlet:namespace />ticketId': ticketId,
                '<portlet:namespace />base64': base64
            },
            on: {
                success: function() {
                    console.log("Message sent successfully.");
                    window.location.reload();
                },
                failure: function() {
                    alert('Failed to send message.');
                }
            }
        });
    });
}
</script>
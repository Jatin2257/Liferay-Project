package com.ticket.web.portlet.action;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ticket.web.constants.TicketWebPortletKeys;
import com.ticket.web.user.notification.SendNotificationToUserHandler;

import java.io.File;
import java.util.Base64;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import chat.service.service.ChatMessageLocalService;
import helpdesk.service.model.Ticket;
import helpdesk.service.service.TicketLocalService;

@Component(immediate = true, property = { "javax.portlet.name=" + TicketWebPortletKeys.TICKETWEB,
		"mvc.command.name=sendMessage" }, service = MVCResourceCommand.class)
public class SendMessageMVCResource implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		System.out.println("Send Message Resource Called");
		try {
			long userId = ParamUtil.getLong(resourceRequest, "userId");
			User user = userLocalService.getUser(userId);
			long ticketId = ParamUtil.getLong(resourceRequest, "ticketId");
			Ticket ticket = ticketLocalService.getTicket(ticketId);
			String message = ParamUtil.getString(resourceRequest, "message");

			String imageBase64 = ParamUtil.getString(resourceRequest, "base64");
			System.out.println("Image base64 data ::" + imageBase64);

			System.out.println(" message : " + message);
			chatMessageLocalService.addChatMessage(ticketId, userId, message, imageBase64);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Reference
	private ChatMessageLocalService chatMessageLocalService;

	@Reference
	private UserLocalService userLocalService;

	@Reference
	private TicketLocalService ticketLocalService;

}

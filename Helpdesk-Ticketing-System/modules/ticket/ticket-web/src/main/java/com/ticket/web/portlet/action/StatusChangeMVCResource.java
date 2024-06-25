package com.ticket.web.portlet.action;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ticket.web.constants.TicketWebPortletKeys;
import com.ticket.web.user.notification.SendNotificationToUserHandler;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import helpdesk.service.model.Ticket;
import helpdesk.service.service.TicketLocalService;

@Component(immediate = true, property = { "javax.portlet.name=" + TicketWebPortletKeys.TICKETWEB,
		"mvc.command.name=statusChange" }, service = MVCResourceCommand.class)
public class StatusChangeMVCResource implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		try {
			System.out.println("Called Status change resource");
			String status = ParamUtil.getString(resourceRequest, "status");
			long ticketId = ParamUtil.getLong(resourceRequest, "ticketId");
			long userId = ParamUtil.getLong(resourceRequest, "userId");
			System.out.println("status ::" + status);
			System.out.println("ticketId :: " + ticketId);
			System.out.println("UserId :: " + userId);

			Ticket ticket = ticketLocalService.fetchTicket(ticketId);
			ticket.setStatus(status);
			ticketLocalService.updateTicket(ticket);
			User user = userLocalService.getUser(userId);
			JSONObject payloadJson = JSONFactoryUtil.createJSONObject();
			payloadJson.put("ticketId", ticket.getTicketId());
			payloadJson.put("title", "Ticket Update : " + user.getFullName());
			payloadJson.put("status", "Ticket Raised : " + ticket.getSubject() + ",Ticket Update Status : " + status);
			new SendNotificationToUserHandler().sendNotification(user, payloadJson);

			System.out.println("Status Change Successfully");
		} catch (Exception e) {
			System.out.println("Exception Occur While Changing Status " + e.getMessage());
		}

		return false;
	}

	@Reference
	private TicketLocalService ticketLocalService;

	@Reference
	private UserLocalService userLocalService;

}

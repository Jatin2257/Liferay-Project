package com.ticket.web.portlet.action;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.ticket.web.constants.TicketWebPortletKeys;
import com.ticket.web.user.notification.SendNotificationToUserHandler;

import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import helpdesk.service.model.Ticket;
import helpdesk.service.service.TicketLocalService;

@Component(immediate = true, property = { "javax.portlet.name=" + TicketWebPortletKeys.TICKETWEB,
		"mvc.command.name=reOpenTicket" }, service = MVCResourceCommand.class)

public class ReOpenTicketMVCResource implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
			long ticketId = ParamUtil.getLong(resourceRequest, "ticketId");
			Ticket ticket = ticketLocalService.fetchTicket(ticketId);
			long userId = ticket.getUserId();
			User user = userLocalService.getUser(userId);
			ticket.setStatus("Open");
			ticketLocalService.updateTicket(ticket);
			Role role = roleLocalService.getRole(CompanyThreadLocal.getCompanyId(), ticket.getDepartment());
			List<User> users = userLocalService.getRoleUsers(role.getRoleId());
			JSONObject payloadJson = JSONFactoryUtil.createJSONObject();
			payloadJson.put("ticketId", ticket.getTicketId());
			payloadJson.put("title", "Ticket ReOpen : " + user.getFullName());
			payloadJson.put("status",
					"Ticket Id : " + ticket.getTicketId() + " ,Ticket Subject :" + ticket.getSubject());
			for (User user1 : users) {
				new SendNotificationToUserHandler().sendNotification(user1, payloadJson);
			}
			new SendNotificationToUserHandler().sendNotification(user, payloadJson);
			System.out.println("Ticket Reopen SuccessFully ");
			System.out.println("notification send Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Reference
	private TicketLocalService ticketLocalService;

	@Reference
	private RoleLocalService roleLocalService;

	@Reference
	private UserLocalService userLocalService;

}

package com.ticket.web.modellistener;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.ticket.web.constants.TicketWebPortletKeys;
import com.ticket.web.user.notification.SendNotificationToUserHandler;

import java.util.List;

import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import helpdesk.service.model.Ticket;

@Component(immediate = true, property = {
		"javax.portlet.name=" + TicketWebPortletKeys.TICKETWEB }, service = ModelListener.class)
public class TicketModelListener extends BaseModelListener<Ticket> {

	@Override
	public void onAfterCreate(Ticket model) throws ModelListenerException {
		System.out.println("User Ticket Created");
		try {
			User user = userLocalService.fetchUser(model.getUserId());
			System.out.println("Ticket model listener called");
			JSONObject payloadJson = JSONFactoryUtil.createJSONObject();
			payloadJson.put("ticketId", model.getTicketId());
			payloadJson.put("title", "New Ticket Create By : " + user.getFullName());
			payloadJson.put("status", "Ticket Raised : "+model.getSubject());

			Role role = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(), model.getDepartment());
			List<User> users = UserLocalServiceUtil.getRoleUsers(role.getRoleId());

			for (User user1 : users) {
				new SendNotificationToUserHandler().sendNotification(user1, payloadJson);
				MailMessage mailMessage = new MailMessage();
				mailMessage.setTo(new InternetAddress(user1.getEmailAddress()));
				mailMessage.setFrom(new InternetAddress("katariyajatin247@gmail.com"));
				mailMessage.setSubject(model.getSubject());
				mailMessage.setBody("Your Department: " + model.getDepartment() + "Ticket Description: "
						+ model.getDescription() + "Create Date: " + model.getCreatedate());
				MailServiceUtil.sendEmail(mailMessage);
				System.out.println("Ticket Mail Send Successfully");
			}

			new SendNotificationToUserHandler().sendNotification(user, payloadJson);
			MailMessage mailMessage = new MailMessage();
			mailMessage.setTo(new InternetAddress(model.getEmail()));
			mailMessage.setFrom(new InternetAddress("katariyajatin247@gmail.com"));
			mailMessage.setSubject(model.getSubject());
			mailMessage.setBody("Your Department: " + model.getDepartment() + "Ticket Description: "
					+ model.getDescription() + "Create Date: " + model.getCreatedate());
			MailServiceUtil.sendEmail(mailMessage);
			System.out.println("Ticket Mail Send Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onAfterCreate(model);
	}

	@Reference
	RoleLocalService roleLocalService;

	@Reference
	UserLocalService userLocalService;

}

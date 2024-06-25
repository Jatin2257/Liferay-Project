package com.ticket.web.user.notification;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = UserNotificationHandler.class)
public class SendNotificationToUserHandler extends BaseUserNotificationHandler {
	public static final String PORLET_ID = "com_ticket_web_TicketWebPortlet_INSTANCE_lvfv";

	public SendNotificationToUserHandler() {
		setPortletId(SendNotificationToUserHandler.PORLET_ID);
	}

	@Override
	protected String getBody(UserNotificationEvent userNotificationEvent, ServiceContext serviceContext)
			throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(userNotificationEvent.getPayload());
		String notificationTitle = jsonObject.getString("title");
		String notificationDetails = jsonObject.getString("status");
		String body = StringUtil.replace(getBodyTemplate(), new String[] { "[$TITLE$]", "[$BODY_TEXT$]" },
				new String[] { "<strong>" + notificationTitle + "</strong>", notificationDetails });
		return body;
	}

	@Override
	protected String getLink(UserNotificationEvent userNotificationEvent, ServiceContext serviceContext)
			throws Exception {
		return super.getLink(userNotificationEvent, serviceContext);
	}

	@Override
	protected String getBodyTemplate() throws Exception {
		return "<div class=\"title\">[$TITLE$]</div><div>[$BODY_TEXT$]</div>";
	}

	public void sendNotification(User user, JSONObject payload) throws PortalException {
		if (user != null) {

			long userNotificationEventId = CounterLocalServiceUtil.increment();
			UserNotificationEvent notification = UserNotificationEventLocalServiceUtil
					.createUserNotificationEvent(userNotificationEventId);

			notification.setCompanyId(user.getCompanyId());
			notification.setUserId(user.getUserId());
			notification.setPayload(payload.toString());
			notification.setDeliveryType(UserNotificationDeliveryConstants.TYPE_WEBSITE);
			notification.setTimestamp(new Date().getTime());
			notification.setArchived(false);
			notification.setDelivered(true);
			notification.setType("com_ticket_web_TicketWebPortlet_INSTANCE_lvfv");

			UserNotificationEventLocalServiceUtil.addUserNotificationEvent(notification);

			System.out.println("Notification sent to user: " + user.getUserId());
		} else {
			System.out.println("User not found.");
		}
	}


}

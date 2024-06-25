package com.ticket.web.portlet.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ticket.web.constants.TicketWebPortletKeys;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.swing.plaf.basic.BasicToolBarUI.DockingListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import helpdesk.service.model.Ticket;
import helpdesk.service.service.TicketLocalService;

@Component(immediate = true, property = { "javax.portlet.name=" + TicketWebPortletKeys.TICKETWEB,
		"mvc.command.name=createTicket" }, service = MVCActionCommand.class)
public class CreateTicketMVCAction extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = themeDisplay.getUser();
			String portletId = PortalUtil.getPortletId(actionRequest);
			System.out.println("The portlet Id::" + portletId);
			if (user != null) {
				
				long ticketId = counterLocalService.increment();
				Ticket ticket = ticketLocalService.createTicket(ticketId);
				String subject = ParamUtil.getString(actionRequest, "subject");
				String description = ParamUtil.getString(actionRequest, "description");
				long categoryId = ParamUtil.getLong(actionRequest, "category");
				AssetCategory assetCategory = assetCategoryLocalService.getCategory(categoryId);
				String category = assetCategory.getName();
				long subCategoryId = ParamUtil.getLong(actionRequest, "subCategory");
				AssetCategory assetSubCategory = assetCategoryLocalService.getCategory(subCategoryId);
				String subCategory = assetSubCategory.getName();
				String department = ParamUtil.getString(actionRequest, "department");
				String priority = ParamUtil.getString(actionRequest, "priority");
				LocalDate localDate = LocalDate.now();
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String createDate = localDate.format(dateTimeFormatter);
				System.out.println("current date:=" + createDate);
				UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
				File file = uploadRequest.getFile("file");
				byte[] imageBytes = null;
				String imageBase64 = null;

				if (file != null && file.length() > 0) {
					imageBytes = FileUtil.getBytes(file);
				}

				if (imageBytes != null && imageBytes.length > 0) {
					imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
				} else {
					imageBase64 = "No image Inserted";
				}

				ticketLocalService.addTicket(ticketId, user.getUserId(), user.getFullName(), user.getEmailAddress(),
						subject, description, department, category, subCategory, priority, imageBase64, "Open",
						createDate);

				System.out.println("Ticket Information :User Id " + user.getUserId() + "," + user.getFullName()
						+ ",  Ticket Id: " + ticketId + ",  Subject:  " + subject + ",  Description:  " + description
						+ ",  category:  " + category + ",  subcategory:  " + subCategory + ",  Deparment:  "
						+ department + ",  priority:  " + priority + ",Create Date :" + createDate);

			} else {
				System.out.println("User is Nulll");
			}
			
		} catch (Exception e) {
			System.out.println("Error Occur" + e.getMessage());
		}

	}

	@Reference
	private CounterLocalService counterLocalService;

	@Reference
	private TicketLocalService ticketLocalService;

	@Reference
	private AssetCategoryLocalService assetCategoryLocalService;
}

package com.ticket.web.portlet;

import com.ticket.web.constants.TicketWebPortletKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.CountryLocalService;
import com.liferay.portal.kernel.service.CountryLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import helpdesk.service.model.Ticket;
import helpdesk.service.service.TicketLocalService;

/**
 * @author Jatin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=TicketWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TicketWebPortletKeys.TICKETWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TicketWebPortlet extends MVCPortlet {
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		String resourceId = resourceRequest.getResourceID();
		
		if("exportCSV".equals(resourceId)) {
			exportDataToCSV(resourceRequest, resourceResponse);
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	
	private void exportDataToCSV(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException {
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long userId = themeDisplay.getUserId();
			User user = userLocalService.getUser(userId);
			long deparmentVocabularyId = 50321;
			List<AssetCategory> deparmentList = AssetCategoryLocalServiceUtil
					.getVocabularyCategories(deparmentVocabularyId, -1, -1, null);
			
			long[] roleIdsArray = user.getRoleIds();
			List<Long> roleIds = Arrays.stream(roleIdsArray).boxed().collect(Collectors.toList());
			List<String> roleNames = new ArrayList<>();
			for (Long roleId : roleIds) {
				Role role = roleLocalService.fetchRole(roleId);
				if (role != null) {
					roleNames.add(role.getName());
				}
			}
			
			List<Ticket> tickets = null;
			boolean found = false;
			for (String role : roleNames) {
			    for (AssetCategory department : deparmentList) {
			        if (role.equals(department.getName())) {
			        	System.out.println("department called");
			            tickets = ticketLocalService.findTicketByDepartment(department.getName());
			            found = true;
			            break;
			        }
			    }
			    if (found) {
			        break;
			    }
			}
	
			if (!found) {
				System.out.println("user called");
			    tickets = ticketLocalService.findTicketByUserId(userId);
			    
			}
			resourceResponse.setContentType("text/csv");
			resourceResponse.addProperty("Content-Disposition", "attachment; filename=\"tickets.csv\"");
			
	        PrintWriter printWriter = resourceResponse.getWriter();
	        printWriter.println("Ticket Id,Requester,Subject,Description,Department,Priority,Category,Sub Category,Create Date,Status");
	        
	        for (Ticket ticket : tickets) {
	        	printWriter.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
	                    ticket.getTicketId(),
	                    ticket.getRequester(),
	                    ticket.getSubject(),
	                    ticket.getDescription(),
	                    ticket.getDepartment(),
	                    ticket.getPriority(),
	                    ticket.getCategory(),
	                    ticket.getSubcategory(),
	                    ticket.getCreatedate(),
	                    ticket.getStatus()
	                );
			}
	        
	        printWriter.flush();
	        printWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Reference
	private TicketLocalService ticketLocalService;
	
	@Reference
	private UserLocalService userLocalService;
	
	@Reference
	private RoleLocalService roleLocalService;
}
package com.ticket.dashboard.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.ticket.dashboard.constants.TicketDashboardPortletKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import helpdesk.service.model.Ticket;
import helpdesk.service.service.TicketLocalService;

/**
 * @author Jatin
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=TicketDashboard", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TicketDashboardPortletKeys.TICKETDASHBOARD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class TicketDashboardPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		System.out.println("called view");
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long userId = themeDisplay.getUserId();
			User user = userLocalService.getUser(userId);
			System.out.println("the userId is :: " + userId);

			Map<String, Integer> ticketCounts = getTicketCountsByStatus(userId);
			Map<String, Integer> ticketCountsByPriority = getTicketCountsByPriority(userId);

			List<Ticket> openTicketList = getTicketsByStatus(userId, "Open");
			List<Ticket> inProgressTicketList = getTicketsByStatus(userId, "In-Progress");
			List<Ticket> closedTicketList = getTicketsByStatus(userId, "Closed");

			long deparmentVocabularyId = 50321;
			List<AssetCategory> deparmentList = assetCategoryLocalService
					.getVocabularyCategories(deparmentVocabularyId, -1, -1, null);

			boolean isDepartment = false;
			boolean found = false;

			long[] roleIdsArray = user.getRoleIds();
			List<Long> roleIds = Arrays.stream(roleIdsArray).boxed().collect(Collectors.toList());
			List<String> roleNames = new ArrayList<>();
			for (Long roleId : roleIds) {
				Role role = roleLocalService.fetchRole(roleId);
				if (role != null) {
					roleNames.add(role.getName());
				}
			}

			for (String role : roleNames) {
				for (AssetCategory department : deparmentList) {
					if (role.equals(department.getName())) {
						isDepartment = true;
						int openTicketDepartment = getTicketsCountByDepartmentAndStatus(department.getName(), "Open");
						int inProgressTicketDepartment = getTicketsCountByDepartmentAndStatus(department.getName(),"In-Progress");
						int closedTicketDepartment = getTicketsCountByDepartmentAndStatus(department.getName(),"Closed");
						int normalPriorityTicketCounts = getTicketsCountByDepartmentAndPriority(department.getName(), "normal");
						int mediumPriorityTicketCounts = getTicketsCountByDepartmentAndPriority(department.getName(), "medium");
						int highPriorityTicketCounts = getTicketsCountByDepartmentAndPriority(department.getName(), "High");
						List<Ticket> opendepartmentTicketsList = getDepartmentTicketsByStatus(department.getName(), "Open");
						List<Ticket> inProgressdepartmentTicketsList = getDepartmentTicketsByStatus(department.getName(), "In-Progress");
						List<Ticket> closeddepartmentTicketsList = getDepartmentTicketsByStatus(department.getName(), "Closed");
						renderRequest.setAttribute("openTicketDepartment", openTicketDepartment);
						renderRequest.setAttribute("inProgressTicketDepartment", inProgressTicketDepartment);
						renderRequest.setAttribute("closedTicketDepartment", closedTicketDepartment);
						renderRequest.setAttribute("normalPriorityTicketCounts", normalPriorityTicketCounts);
						renderRequest.setAttribute("mediumPriorityTicketCounts", mediumPriorityTicketCounts);
						renderRequest.setAttribute("highPriorityTicketCounts", highPriorityTicketCounts);
						renderRequest.setAttribute("opendepartmentTicketsList", opendepartmentTicketsList);
						renderRequest.setAttribute("inProgressdepartmentTicketsList", inProgressdepartmentTicketsList);
						renderRequest.setAttribute("closeddepartmentTicketsList", closeddepartmentTicketsList);
						renderRequest.setAttribute("isDepartment", isDepartment);
						found = true;
						break;
					}
				}
				if (found) {
					break;
				}
			}

			if (!found) {
				renderRequest.setAttribute("openTickets", ticketCounts.get("Open"));
				renderRequest.setAttribute("closedTickets", ticketCounts.get("Closed"));
				renderRequest.setAttribute("inProgressTickets", ticketCounts.get("In-Progress"));
				renderRequest.setAttribute("ticketCountsByPriority", ticketCountsByPriority);
				renderRequest.setAttribute("openTicketList", openTicketList);
				renderRequest.setAttribute("inProgressTicketList", inProgressTicketList);
				renderRequest.setAttribute("closedTicketList", closedTicketList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.doView(renderRequest, renderResponse);
	}

	public Map<String, Integer> getTicketCountsByStatus(long userId) {
		System.out.println("");
		Map<String, Integer> counts = new HashMap<>();

		String[] statuses = { "Open", "In-Progress", "Closed" };
		for (String status : statuses) {
			counts.put(status, getTicketsCountByUserIdAndStatus(userId, status));
		}
		System.out.println("");
		return counts;
	}

	public int getTicketsCountByUserIdAndStatus(long userId, String status) {
		DynamicQuery dynamicQuery = ticketLocalService.dynamicQuery().add(RestrictionsFactoryUtil.eq("userId", userId))
				.add(RestrictionsFactoryUtil.eq("status", status));
		return (int) ticketLocalService.dynamicQueryCount(dynamicQuery);
	}

	public int getTicketsCountByDepartmentAndStatus(String department, String status) {
		DynamicQuery dynamicQuery = ticketLocalService.dynamicQuery()
				.add(RestrictionsFactoryUtil.eq("department", department))
				.add(RestrictionsFactoryUtil.eq("status", status));

		return (int) ticketLocalService.dynamicQueryCount(dynamicQuery);
	}
	
	public int getTicketsCountByDepartmentAndPriority(String department, String priority) {
	    DynamicQuery dynamicQuery = ticketLocalService.dynamicQuery()
	            .add(RestrictionsFactoryUtil.eq("department", department))
	            .add(RestrictionsFactoryUtil.eq("priority", priority));

	    return (int) ticketLocalService.dynamicQueryCount(dynamicQuery);
	}

	private Map<String, Integer> getTicketCountsByPriority(long userId) {
		Map<String, Integer> counts = new HashMap<>();

		try {

			DynamicQuery dynamicQuery = ticketLocalService.dynamicQuery()
					.add(RestrictionsFactoryUtil.eq("userId", userId)).setProjection(
							ProjectionFactoryUtil.projectionList().add(ProjectionFactoryUtil.groupProperty("priority"))
									.add(ProjectionFactoryUtil.rowCount(), "ticketCount"));

			List<Object[]> results = ticketLocalService.dynamicQuery(dynamicQuery);
			for (Object[] result : results) {
				String priority = (String) result[0];
				long ticketCount = (long) result[1];
				counts.put(priority, (int) ticketCount);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return counts;
	}

	private List<Ticket> getTicketsByStatus(long userId, String status) {
		DynamicQuery dynamicQuery = ticketLocalService.dynamicQuery().add(RestrictionsFactoryUtil.eq("userId", userId))
				.add(RestrictionsFactoryUtil.eq("status", status));

		return ticketLocalService.dynamicQuery(dynamicQuery);
	}
	
	private List<Ticket> getDepartmentTicketsByStatus(String department, String status) {
		DynamicQuery dynamicQuery = ticketLocalService.dynamicQuery().add(RestrictionsFactoryUtil.eq("department", department))
				.add(RestrictionsFactoryUtil.eq("status", status));

		return ticketLocalService.dynamicQuery(dynamicQuery);
	}

	@Reference
	private TicketLocalService ticketLocalService;

	@Reference
	private UserLocalService userLocalService;

	@Reference
	private RoleLocalService roleLocalService;
	
	@Reference
	private AssetCategoryLocalService assetCategoryLocalService;
}
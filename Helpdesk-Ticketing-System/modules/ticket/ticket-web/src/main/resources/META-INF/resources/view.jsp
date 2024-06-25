<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="helpdesk.service.model.Ticket"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.liferay.portal.kernel.model.Role"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="helpdesk.service.service.TicketLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetCategory"%>
<%@page import="java.util.List"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"%>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<liferay-theme:defineObjects/>

<%
	long categoryVocabularyId = 50301;
	long deparmentVocabularyId = 50321;
	List<AssetCategory> categoryList = AssetCategoryLocalServiceUtil
			.getVocabularyCategories(categoryVocabularyId, -1, -1, null);
	List<AssetCategory> deparmentList = AssetCategoryLocalServiceUtil
			.getVocabularyCategories(deparmentVocabularyId, -1, -1, null);
	
	long userId=themeDisplay.getUserId();
	User user1 = UserLocalServiceUtil.getUser(userId);
	long[] roleIdsArray = user1.getRoleIds();
	List<Long> roleIds = Arrays.stream(roleIdsArray).boxed().collect(Collectors.toList());
	List<String> roleNames = new ArrayList<>();
	  for (Long roleId : roleIds) {
	        Role role = RoleLocalServiceUtil.fetchRole(roleId);
	        if (role != null) {
	            roleNames.add(role.getName());
	        }
	  }
%>
<portlet:actionURL name="createTicket" var="createTicketURL"/>
<portlet:resourceURL id="statusChange" var="statusChangeURL"/>

<div class="container-fluid container-fluid-max-xl">
	<aui:form action="${createTicketURL}">
		<legend style="align-items: center;" align="top">Create New Ticket</legend>
		<aui:input label="Subject" name="subject" type="text">
			<aui:validator name="required"></aui:validator>
		</aui:input>
		<aui:input label="Description" name="description" type="textarea"
			required="true" />
		<aui:row>
			<aui:col width="50">
				<aui:select label="Category" name="category"
					onChange="getAllSubcategories(this.value)" required="true">
					<aui:option>Select Category</aui:option>
					<%
						for (AssetCategory category : categoryList) {
							if (category.getParentCategoryId() == 0) {
					%>
					<aui:option value="<%=category.getCategoryId()%>"><%=category.getName()%></aui:option>
					<%
						}
					  }
					%>
				</aui:select>
			</aui:col>
			<aui:col width="50">
				<aui:select id="subcategory" label="Sub Category" name="subCategory"
					required="true">
					<aui:option>Select SubCategory</aui:option>
				</aui:select>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:select label="Deparment" name="department" required="true">
					<aui:option>Select Department</aui:option>
					<%
						for (AssetCategory deparment : deparmentList) {
					%>
					<aui:option value="<%=deparment.getName()%>"><%=deparment.getName()%></aui:option>
					<%
						}
					%>
				</aui:select>
			</aui:col>
			<aui:col width="50">
				<aui:select label="Priority" name="priority" required="true">
					<aui:option value="normal">Normal</aui:option>
					<aui:option value="medium">Medium</aui:option>
					<aui:option value="High">High</aui:option>
				</aui:select>
			</aui:col>
		</aui:row>
		<aui:input type="file" name="file" label="File Attachment">
			<aui:validator name="acceptFiles">'jpg,png,jpeg'</aui:validator>
		</aui:input>

		<aui:button-row>
			<aui:button value="Save" name="saveButton" type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

	<hr>
	<br>
	
	
	
<%

List<Ticket> tickets = null;
int total1 = 0;
boolean isDepartment = false;
boolean isUser = false;
boolean found = false;
for (String role : roleNames) {
    for (AssetCategory department : deparmentList) {
        if (role.equals(department.getName())) {
            tickets = TicketLocalServiceUtil.findTicketByDepartment(role);
            total1 = TicketLocalServiceUtil.countTicketByDepartment(role);
            isDepartment = true;
            found = true;
            break;
        }
    }
    if (found) {
        break;
    }
}

if (!found) {
    tickets = TicketLocalServiceUtil.findTicketByUserId(userId);
    total1 = TicketLocalServiceUtil.countTicketByUserID(userId);
    isUser = true;
}
%>
<portlet:resourceURL id="exportCSV" var="exportCSVURL"/>
<div class="container-fluid container-fluid-max-xl " style="width: 1300px;height: 800px;margin-top: 30px">
	<a style="align-items: flex-end;" href="${exportCSVURL}">Export Data to CSV</a>
	<liferay-ui:search-container 
		emptyResultsMessage="No Ticket Data Avaiable" delta="5" deltaConfigurable="true"
		total="<%=total1%>">
		
			<% List<Ticket> subTicket = ListUtil.subList(tickets, searchContainer.getStart(), searchContainer.getEnd()); %>
		<liferay-ui:search-container-results results="<%=subTicket %>" />
		<liferay-ui:search-container-row className="helpdesk.service.model.Ticket" modelVar="Ticket">
		<liferay-ui:search-container-column-text property="ticketId" name="Ticket Id" />
		<liferay-ui:search-container-column-text property="requester" name="Requester" />
		<liferay-ui:search-container-column-text property="subject" name="Subject" />
		<liferay-ui:search-container-column-text property="description" name="Description" />
		<liferay-ui:search-container-column-text property="department" name="Department" />
		<liferay-ui:search-container-column-text property="priority" name="Priority" />
		<liferay-ui:search-container-column-text property="category" name="Category" />
		<liferay-ui:search-container-column-text property="subcategory" name="Sub Category" />
		<liferay-ui:search-container-column-text property="createdate" name="Create Date" />
		<liferay-ui:search-container-column-text name="Status">
			<c:choose>
			    <c:when test="${Ticket.status == 'Open'}">
			        <span class="badge badge-success">Open</span>
			    </c:when>
			    <c:when test="${Ticket.status == 'In-Progress'}">
			        <span class="badge badge-primary">In-Progress</span>
			    </c:when>
			    <c:when test="${Ticket.status == 'Closed'}">
			        <span class="badge badge-danger">Closed</span>
			        <c:if test="<%= isUser%>">
						<a onclick="reopenTicket(${Ticket.ticketId});" class="btn btn-link">Reopen</a>
					</c:if>
			    </c:when>
			</c:choose>
		</liferay-ui:search-container-column-text>	
		<c:if test="<%=isDepartment %>">
			<liferay-ui:search-container-column-text name="Actions" >
					<aui:select id="status" name="status" onChange="changeStatus(this.value,${Ticket.getTicketId()},${Ticket.userId});">
						<aui:option>Update Status</aui:option>
						<aui:option value="In-Progress">In-Progress</aui:option>
						<aui:option value="Closed">Closed</aui:option>
					</aui:select>
			</liferay-ui:search-container-column-text>
		</c:if>
		
		<liferay-ui:search-container-column-text name="Image">
			<c:choose>
				<c:when test="${Ticket.imageattached eq 'No image Inserted' }">
					<p>No Image</p>
				</c:when>
				<c:otherwise>
					<img src="data:image/png;base64,${Ticket.imageattached}"
							style="width: 100px; height: 100px;" alt="Image">
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>	
		<liferay-ui:search-container-column-text>
			<portlet:renderURL var="chatboxURL">
				<portlet:param name="mvcRenderCommandName" value="ticketChatBox"/>
				<portlet:param name="ticketId" value="${Ticket.getTicketId()}"/>
			</portlet:renderURL>
			<a href="${chatboxURL}">View Ticket</a>
		</liferay-ui:search-container-column-text>
		
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>

</div>


<script type="text/javascript">
	function getAllSubcategories(categoryId) {
		console.log("Selected category ID:", categoryId);

		var subcategoryDropdown = document
				.getElementById("<portlet:namespace />subcategory");
		console.log("Subcategory dropdown element:", subcategoryDropdown);
		if (categoryId !== "") {
			if (subcategoryDropdown) {

				subcategoryDropdown.innerHTML = '<option value="">Loading...</option>';

				Liferay.Service(
								'/assetcategory/get-vocabulary-categories',
								{
									parentCategoryId : categoryId,
									vocabularyId : 50301,
									start : -1,
									end : -1,
									orderByComparator : null
								},
								function(response) {
									console.log(response);

									if (Array.isArray(response)) {
										console.log("Retrieved subcategories:",
												response);
										subcategoryDropdown.innerHTML = '<option value="">Select Subcategory</option>';

										response
												.forEach(function(subcategory) {

													var name = subcategory.name;

													subcategoryDropdown.innerHTML += '<option value="' + subcategory.categoryId + '">'
															+ name
															+ '</option>';
												});
									} else {
										console
												.error(
														"Invalid response format or no subcategory data found:",
														response);
									}
								});
			} else {
				console.error("Subcategory dropdown element not found.");
			}
		} else {
			subcategoryDropdown.innerHTML = '<option value="">Select Subcategory</option>';
		}
	}
	
	
	function changeStatus(status,ticketId,userId){
		console.log("status is ",status);
		console.log("ticket Id is ",ticketId);
		console.log("userId is ",userId)
		console.log("Called Status");
		var statusDropdown = document.getElementById("<portlet:namespace />status");
		
		AUI().use('aui-io-request',function(A){
	        A.io.request('<portlet:resourceURL id="statusChange" />', {
	            method: 'POST',
	            data: {
	                <portlet:namespace />status: status,
	                <portlet:namespace />ticketId: ticketId,
	                <portlet:namespace />userId: userId
	                
	            },
	            on: {
	                success: function() {
	                    console.log("Status Change successfully")
	                    window.location.reload();
	                },
	       	         failure: function() {
	                    alert('Failed to change status.');
	                }
	            }
	        });
		});
	}
	
	function reopenTicket(ticketId){
		
		console.log("ticketId :: ",ticketId);
		
		AUI().use('aui-io-request',function(A){
	        A.io.request('<portlet:resourceURL id="reOpenTicket" />', {
	            method: 'POST',
	            data: {
	                <portlet:namespace />ticketId: ticketId
	            },
	            on: {
	                success: function() {
	                    console.log("Status Change successfully")
	                    window.location.reload();
	                },
	       	         failure: function() {
	                    alert('Failed to change status.');
	                }
	            }
	        });
		});
		
	}

	
</script>




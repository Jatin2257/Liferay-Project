<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:renderURL var="addUserRenderURL">
	<portlet:param name="mvcRenderCommandName" value="addUserRender"/>
</portlet:renderURL>

<a href="${addUserRenderURL}" class="btn btn-success">Register User</a>
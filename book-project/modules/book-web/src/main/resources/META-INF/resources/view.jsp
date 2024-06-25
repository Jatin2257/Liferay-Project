<%@page import="com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.Property"%>
<%@page import="com.liferay.portal.kernel.dao.orm.DynamicQuery"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.liferay.object.model.ObjectEntry"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.object.service.ObjectEntryLocalServiceUtil"%>
<%@page import="com.liferay.list.type.model.ListTypeDefinition"%>
<%@page import="com.liferay.list.type.service.ListTypeDefinitionLocalServiceUtil"%>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<liferay-theme:defineObjects />

<%
long objectDefinitionId = 53415;
DynamicQuery dynamicQuery = ObjectEntryLocalServiceUtil.dynamicQuery();
Property property = PropertyFactoryUtil.forName("objectDefinitionId");
dynamicQuery.add(property.eq(objectDefinitionId));
List<ObjectEntry> objectEntries = ObjectEntryLocalServiceUtil.dynamicQuery(dynamicQuery);
int total1 = objectEntries.size();
// onSubmit="return validateForm(event)"
%>
<liferay-ui:error key="html-tags-error" message="html-tags-error" />
<portlet:actionURL var="addBookURL" name="addBook"/>
<div class="container-fluid container-fluid-max-xl" style="width: 650px; height: 650px; margin-top: 50px">
<aui:form  action="${addBookURL}" name="bookForm" onSubmit="return validateForm(event)" >
	<h3 align="center">Book Information</h3>
	<aui:input label="Book Name" name="bookName" type="text" required="true"/>
	<aui:input label="Author" name="author" type="text" required="true"/>
	<aui:input label="Price" name="price" type="text" required="true">
		<aui:validator name="digits"/>
	</aui:input>
	<aui:input label="Publish Date" name="publishDate" type="date" required="true"/>
	<aui:select label="Book Type" name="bookType" required="true">
		<aui:option>Select Book Type</aui:option>
		<aui:option value="novel">Novel</aui:option>
		<aui:option value="biography">Biography</aui:option>
		<aui:option value="poetry">Poetry</aui:option>
	</aui:select>
	<aui:button-row>
		<aui:button value="submit" name="submit" type="submit"></aui:button>
	</aui:button-row>
</aui:form>
</div>

<div>
<liferay-ui:search-container emptyResultsMessage="No Ticket Data Avaiable" delta="5" deltaConfigurable="true" total="<%=total1 %>">
	<%List<ObjectEntry> subObjectEntries = ListUtil.subList(objectEntries, searchContainer.getStart(), searchContainer.getEnd()); %>
    <liferay-ui:search-container-results results="<%=subObjectEntries %>"/>

    <liferay-ui:search-container-row modelVar="objectEntry" className="com.liferay.object.model.ObjectEntry">
    	<liferay-ui:search-container-column-text name="Book Id" value="<%=String.valueOf(objectEntry.getObjectEntryId()) %>" />
        <liferay-ui:search-container-column-text name="Author" value="<%= (String) objectEntry.getValues().get("author") %>" />
        <liferay-ui:search-container-column-text name="book Name" value="<%= (String) objectEntry.getValues().get("bookName") %>" />
        <liferay-ui:search-container-column-text name="book Type" value="<%= (String) objectEntry.getValues().get("bookType") %>" />
        <liferay-ui:search-container-column-text name="price" value="<%= String.valueOf(objectEntry.getValues().get("price"))  %>" />
        <liferay-ui:search-container-column-text name="publish date" value="<%= String.valueOf(objectEntry.getValues().get("publishDate"))  %>" />
        <liferay-ui:search-container-column-text name="Actions" >
        	<portlet:actionURL name="deleteBook" var="deleteBookURL">
        		<portlet:param name="bookId" value="<%=String.valueOf(objectEntry.getObjectEntryId()) %>"/>
			</portlet:actionURL>
        	<portlet:renderURL var="editBookURL">
        		<portlet:param name="bookId" value="<%=String.valueOf(objectEntry.getObjectEntryId()) %>"/>
        		<portlet:param name="mvcRenderCommandName" value="editBook"/>
        	</portlet:renderURL>
        	<a href="${editBookURL}" class="btn btn-primary">Edit</a>
        	<a href="${deleteBookURL}" class="btn btn-danger">Delete</a>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>
</div>

<script type="text/javascript">
function validateForm(event) {
    var bookName = document.forms["<portlet:namespace/>bookForm"]["<portlet:namespace/>bookName"].value;
    var author = document.forms["<portlet:namespace/>bookForm"]["<portlet:namespace/>author"].value;
    var regex = /^[a-zA-Z0-9]*$/;

    if (!regex.test(bookName) || !regex.test(author)) {
        alert("no special character allowed");
        event.preventDefault(); // Prevent the form submission
        return false;
    }
    return true;
}
</script>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<portlet:actionURL name="editBook" var="editBookURL">
	<portlet:param name="bookId" value="${objectEntry.values['c_bookId']}"/>
</portlet:actionURL> 

<portlet:renderURL var="BackURL">
	<portlet:param name="mvcRenderCommandName" value="/view.jsp"/>
</portlet:renderURL>


<div class="container-fluid container-fluid-max-xl" style="width: 650px; height: 650px; margin-top: 50px">    
<aui:form action="${editBookURL}" onSubmit="return validateForm(event)">
	<h3 align="center">Book Information</h3>
	<aui:input label="Book Name" name="bookName" type="text" required="true" value="${objectEntry.values['bookName']}"/>
	<aui:input label="Author" name="author" type="text" required="true" value="${objectEntry.values['author']}"/>
	<aui:input label="Price" name="price" type="text" required="true" value="${objectEntry.values['price']}">
		<aui:validator name="digits"/>
	</aui:input>
	<aui:input label="Publish Date" name="publishDate" type="date" required="true" value="${objectEntry.values['publishDate'].toString().substring(0, 10)}" />
	<aui:select label="Book Type" name="bookType" required="true">
		<aui:option>Select Book Type</aui:option>
		<aui:option value="novel" selected="${objectEntry.values['bookType'].equals('novel') ? true : false}">Novel</aui:option>
		<aui:option value="biography" selected="${objectEntry.values['bookType'].equals('biography') ? true : false}">Biography</aui:option>
		<aui:option value="poetry" selected="${objectEntry.values['bookType'].equals('poetry') ? true : false}">Poetry</aui:option>
	</aui:select>
	<aui:button-row>
		<a href="${BackURL}" class="btn btn-light">Back</a>
		<aui:button value="submit" name="submit" type="submit"></aui:button>
	</aui:button-row>
</aui:form>
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

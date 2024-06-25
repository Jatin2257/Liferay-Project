<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.model.Role"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil"%>
<%@taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<portlet:actionURL name="userRegister" var="addUserURL" />
<portlet:renderURL var="cancleURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>
<liferay-theme:defineObjects />
<%
	User user1 = themeDisplay.getUser();
	boolean isAdmin=false;
	
	Role roleAdmin = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), "Admin");
	isAdmin = user1.getRoles().contains(roleAdmin);
	
	if(!isAdmin){
		response.sendRedirect(themeDisplay.getURLSignIn());
		return;
	}

	List<Role> roles = RoleLocalServiceUtil.getRoles(-1, -1);
%>

<div class="container-fluid container-fluid-max-xl"
	style="width: 650px; height: 650px; margin-top: 50px">
	<aui:form id="userForm">
		<h3 align="center">User Registration Form</h3>
		<hr>
		<aui:row>
			<aui:col width="30">
				<aui:input label="Screen Name" name="screenName" type="text">
					<aui:validator name="required" />
				</aui:input>
			</aui:col>
			<aui:col width="30">
				<aui:input label="First Name" name="firstName" type="text">
					<aui:validator name="required" />
				</aui:input>
			</aui:col>
			<aui:col width="30">
				<aui:input label="Last Name" name="lastName" type="text">
					<aui:validator name="required" />
				</aui:input>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="Email Address" name="email" type="text">
					<aui:validator name="required" />
					<aui:validator name="email" />
				</aui:input>
			</aui:col>
			<aui:col width="50">
				<aui:select label="Select Role" name="role" required="true">
				<aui:option>Select Role</aui:option>
					<%
						for (Role role : roles) {
					%>
					<aui:option value="<%=role.getName()%>"><%=role.getName()%></aui:option>
					<%
						}
					%>
				</aui:select>
			</aui:col>
		</aui:row>
		<aui:row>
			<aui:col width="50">
				<aui:input label="Password" name="password1" type="password" required="true"></aui:input>
			</aui:col>
			<aui:col width="50">
				<aui:input label="Confirm Password" name="password2" type="password" required="true"></aui:input>
			</aui:col>
		</aui:row>
		<aui:button-row>
			<aui:button value="Save" type="submit" name="submitButton"
				onClick="registerUser();"></aui:button>
		</aui:button-row>

	</aui:form>
</div>

<script>	

// 	javascript function which register user 
	function registerUser(){
		
		 console.log("Function Called")
		 var email = document.getElementById('<portlet:namespace />email').value;
         var firstName = document.getElementById('<portlet:namespace />firstName').value;
         var lastName = document.getElementById('<portlet:namespace />lastName').value;
         var password1 = document.getElementById('<portlet:namespace />password1').value;
         var password2 = document.getElementById('<portlet:namespace />password2').value;
         var role = document.getElementById('<portlet:namespace />role').value;
         var screenName = document.getElementById('<portlet:namespace />screenName').value;
		
         var userData = {
                 "email": email,
                 "firstName": firstName,
                 "lastName":  lastName,
                 "password1": password1,
                 "password2": password2,
                 "role": role,
                 "screenName": screenName
          };
         console.log(userData);
         var xhr = new XMLHttpRequest();
         xhr.open('POST', 'http://localhost:8080/o/user-rest/v1.0/add-user', true);
         xhr.setRequestHeader('Content-Type', 'application/json');
         xhr.setRequestHeader('Accept', 'application/json');
         var username ='test@liferay.com';
         var password = '123';
         var basicAuth = 'Basic '+ btoa(username +':'+ password);
         xhr.setRequestHeader('Authorization', basicAuth);
			
         xhr.onreadystatechange = function() {
             if (xhr.readyState === 4) {
                   if (xhr.status === 200) {
                       alert('User registered successfully!');
                       window.location.reload();
                   } else {
                       alert('Failed to register user.');
                   }
               }
           };

        xhr.send(JSON.stringify(userData));		
	}
	
	window.registerUser=registerUser;




</script>



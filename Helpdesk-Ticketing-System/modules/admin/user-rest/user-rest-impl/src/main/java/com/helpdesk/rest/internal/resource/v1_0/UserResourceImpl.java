package com.helpdesk.rest.internal.resource.v1_0;

import com.helpdesk.rest.dto.v1_0.NewUser;
import com.helpdesk.rest.dto.v1_0.UserObject;
import com.helpdesk.rest.resource.v1_0.UserResource;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.sites.kernel.util.SitesUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Jatin
 */
@Component(properties = "OSGI-INF/liferay/rest/v1_0/user.properties", scope = ServiceScope.PROTOTYPE, service = UserResource.class)
public class UserResourceImpl extends BaseUserResourceImpl {
	
	/*
	 * add User to Liferay database table 
	 * 
	 */
	
	@Override
	public UserObject addUser(NewUser newUser) throws Exception {
		System.out.println("Called Rest ");
		long companyId = contextCompany.getCompanyId();
		long creatorUserId = contextUser.getUserId();
		String password1 = newUser.getPassword1();
		String password2 = newUser.getPassword2();
		String screenName = newUser.getScreenName();
		String emailAddress = newUser.getEmail();
		System.out.println("email is "+emailAddress);
		String firstName = newUser.getFirstName();
		String lastName = newUser.getLastName();
		String middleName = "";
		String jobTitle = "";
		int birthdayDay = 2;
		int birthdayMonth = 6;
		int birthdayYear = 1980;
		int type = 1;
		long prefixListTypeId = 0;
		long suffixListTypeId = 0;
		long[] groupIds = new long[0];
		long[] organizationIds = new long[0];
		System.out.println("email:"+emailAddress);
		System.out.println("firstName:"+firstName);
		System.out.println("LastName :"+lastName);
		System.out.println("Screen Name:"+screenName);
		System.out.println("password1:"+password1);
		System.out.println("password 2"+password2);
		long[] roleIds = new long[1];
		roleIds[0] = getRole(newUser.getRole());
		System.out.println("Role: "+newUser.getRole());
		long[] userGroupIds = new long[0];
		boolean autoScreenName = false;
		boolean autoPassword = false;
		boolean male = true;
		Locale locale = contextUser.getLocale();
		boolean sendEmail = false;
		ServiceContext serviceContext = new ServiceContext();
		
		User user = userLocalService.addUser(creatorUserId, companyId, autoPassword, password1, password2, autoScreenName,
				screenName, emailAddress, locale, firstName, middleName, lastName, prefixListTypeId, suffixListTypeId,
				male, birthdayMonth, birthdayDay, birthdayYear, jobTitle, type, groupIds, organizationIds, roleIds,
				userGroupIds, sendEmail, serviceContext);
		System.out.println("User Register Successfully");
		groupLocalService.addUserGroup(user.getUserId(), 49721);
		
		UserObject userObject = new UserObject();
		userObject.setUserId(user.getUserId());
		userObject.setScreenName(user.getScreenName());
		userObject.setFirstName(user.getFirstName());
		userObject.setLastName(user.getLastName());
		userObject.setStatusCode(200);
		userObject.setEmail(user.getEmailAddress());
		userObject.setStatusMessage("User Added Successfully");
		
		return userObject;
	}

	private long getRole(String role) throws PortalException {
		Role userRole = roleLocalService.getRole(contextCompany.getCompanyId(), role);
		return userRole.getRoleId();
	}

	@Reference
	private RoleLocalService RoleLocalService;

	@Reference
	private UserLocalService userLocalService;
	
	@Reference
	private GroupLocalService groupLocalService;
}
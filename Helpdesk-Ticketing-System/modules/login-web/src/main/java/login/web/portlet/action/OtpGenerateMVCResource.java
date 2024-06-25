package login.web.portlet.action;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.mail.internet.AddressException;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import login.web.constants.LoginWebPortletKeys;
import login.web.otp.OtpGeneratorUtil;

@Component(immediate = true, property = { "javax.portlet.name=" + LoginWebPortletKeys.LOGINWEB,
		"javax.portlet.name=" + LoginPortletKeys.LOGIN, "javax.portlet.resource-bundle=content.Language",
		"mvc.command.name=otpGenerate" }, service = MVCResourceCommand.class)
public class OtpGenerateMVCResource implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		System.out.println("=======Resource Called=====");
		String email = ParamUtil.getString(resourceRequest, "email");
		String generateOTP = OtpGeneratorUtil.generateOTP();
		try {
			Company company = PortalUtil.getCompany(resourceRequest);
			User user = userLocalService.getUserByEmailAddress(company.getCompanyId(), email);
			if (user != null) {
				OtpGeneratorUtil.storeOTP(user.getUserId(), generateOTP);
				OtpGeneratorUtil.sendOTPEmail(email, generateOTP);
				String retriveOTP = OtpGeneratorUtil.retriveOTP(user.getUserId());
				System.out.println("your otp is " + retriveOTP);
				SessionMessages.add(resourceRequest, "success");
			} else {
				System.out.println("User Doesn't exist");
			}

		} catch (AddressException | PortalException e) {
			System.out.println("No Such User Founded : " + e.getMessage());
			SessionErrors.add(resourceRequest, "error");
		}

		System.out.println("Email Id :" + email);
		return false;
	}

	@Reference
	private UserLocalService userLocalService;

}

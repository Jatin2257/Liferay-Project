package login.web.portlet.action;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import com.liferay.portal.kernel.util.Validator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.osgi.service.component.annotations.Component;

import login.web.constants.LoginWebPortletKeys;
import login.web.otp.OtpGeneratorUtil;

@Component(immediate = true, property = { "javax.portlet.name=" + LoginWebPortletKeys.LOGINWEB,
		"javax.portlet.name=" + LoginPortletKeys.LOGIN, "mvc.command.name=/login/login" }, service = AutoLogin.class)
public class CustomLoginMVCAction implements AutoLogin {

	@Override
	public String[] login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws AutoLoginException {
		try {
			long companyId = PortalUtil.getCompanyId(httpServletRequest);
			String namespace = PortalUtil.getPortletNamespace(LoginPortletKeys.LOGIN);
			String login = ParamUtil.getString(httpServletRequest, namespace + "login");
			String inputOtp = ParamUtil.getString(httpServletRequest, namespace + "opt");
			System.out.println("otp::" + inputOtp);
			System.out.println("login::" + login);

			User user = UserLocalServiceUtil.getUserByEmailAddress(companyId, login);
			PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(permissionChecker);
			boolean isValidateOtp = OtpGeneratorUtil.validateOTP(user.getUserId(), inputOtp);

			if (Validator.isNotNull(login) && Validator.isNotNull(inputOtp)) {
				if (isValidateOtp) {
					System.out.println("Entered===");
					String[] credentials = new String[3];
					credentials[0] = String.valueOf(user.getUserId());
					credentials[1] = user.getPassword();
					credentials[2] = Boolean.TRUE.toString();
//					OtpGeneratorUtil.clearOTP(user.getUserId());
					return credentials;
				}
			}

		} catch (Exception e) {
			System.out.println("Error ocuur while during " + e.getMessage());
		}

		return null;
	}

}

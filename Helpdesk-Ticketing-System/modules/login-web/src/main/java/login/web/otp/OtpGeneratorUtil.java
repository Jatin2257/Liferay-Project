package login.web.otp;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.io.Serializable;
import java.security.SecureRandom;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import login.web.constants.OtpConstants;

public class OtpGeneratorUtil {

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final String OTP_EXPANDO_ATTRIBUTE = "OTP Field";
	private static final String TIME_EXPANDO_ATTRIBUTE = "OTP TimeStamp";
	
	public static String generateOTP() {
		StringBuilder otp = new StringBuilder(OtpConstants.OTP_LENGTH);
		for (int i = 0; i < OtpConstants.OTP_LENGTH; i++) {
			otp.append(OtpConstants.DIGITS.charAt(secureRandom.nextInt(OtpConstants.DIGITS.length())));
		}
		return otp.toString();
	}

	public static void storeOTP(long userId, String otp) {
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			user.getExpandoBridge().setAttribute(OTP_EXPANDO_ATTRIBUTE, otp);
			long storeOtpTime = System.currentTimeMillis();
			user.getExpandoBridge().setAttribute(TIME_EXPANDO_ATTRIBUTE, storeOtpTime);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String retriveOTP(long userId) throws PortalException {
		User user = UserLocalServiceUtil.getUser(userId);
		return (String) user.getExpandoBridge().getAttribute(OTP_EXPANDO_ATTRIBUTE);
	}

	public static void clearOTP(long userId) throws PortalException {
		User user = UserLocalServiceUtil.getUser(userId);
		user.getExpandoBridge().setAttribute(OTP_EXPANDO_ATTRIBUTE, "0");
		user.getExpandoBridge().setAttribute(TIME_EXPANDO_ATTRIBUTE, 0);
	}

	public static void sendOTPEmail(String emailAddress, String otp) throws AddressException {
		MailMessage mailMessage = new MailMessage();
		mailMessage.setTo(new InternetAddress(emailAddress));
		mailMessage.setFrom(new InternetAddress("katariyajatin247@gmail.com"));
		mailMessage.setSubject("OTP (One Time Password)");
		mailMessage.setBody("your opt is :" + otp);
		MailServiceUtil.sendEmail(mailMessage);
		System.out.println("Opt Send SuccessFully");
	}

	public static boolean validateOTP(long userId, String inputOtp) throws PortalException {
		long currentTime = System.currentTimeMillis();
		User user = UserLocalServiceUtil.getUser(userId);
		long storeOtpTime = (long) user.getExpandoBridge().getAttribute(TIME_EXPANDO_ATTRIBUTE);
		String generateOTP = (String) user.getExpandoBridge().getAttribute(OTP_EXPANDO_ATTRIBUTE);
		long fiveMinutesInMills = 5 * 60 * 1000;
		long differenTime = currentTime - storeOtpTime;

		return inputOtp.equals(generateOTP) && differenTime <= fiveMinutesInMills;
	}

}

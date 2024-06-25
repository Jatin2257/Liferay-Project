package com.book.web.portlet.action;

import com.book.web.api.CommonApiUtil;
import com.book.web.constants.BookWebPortletKeys;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + BookWebPortletKeys.BOOKWEB,
		"mvc.command.name=addBook" }, service = MVCActionCommand.class)
public class AddBookMVCAction extends BaseMVCActionCommand {

	private static final Log _log = LogFactoryUtil.getLog(AddBookMVCAction.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userId = themeDisplay.getUserId();
		String bookName = ParamUtil.getString(actionRequest, "bookName");
		String author = ParamUtil.getString(actionRequest, "author");
		long price = ParamUtil.getLong(actionRequest, "price");
		String publishDate = ParamUtil.getString(actionRequest, "publishDate");
		String bookType = ParamUtil.getString(actionRequest, "bookType");
		_log.info("Book Name : " + bookName + ",author : " + author + ",price :" + price + ",publish date : "
				+ publishDate + ",book Type : " + bookType);
		String screenName = getScreenNameByUserId(userId);
		System.out.println("Screen Name : " + screenName);
		
		// Validate input field
		if (!validateInput(bookName) || !validateInput(author)) {
		    SessionErrors.add(actionRequest, "html-tags-error");
		    return;
		}
		
		// Create a Json Object
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("bookName", bookName);
		jsonObject.put("author", author);
		jsonObject.put("publishDate", publishDate);
		jsonObject.put("bookType", bookType);
		jsonObject.put("screenName", screenName);
		jsonObject.put("price", price);

		String requestBody = jsonObject.toString();

		// Custom Rest Api Url
		String apiUrl = "http://localhost:8080/o/books/addbook";
		CommonApiUtil.callApi(apiUrl, "POST", requestBody);

	}

	/*
	 * Method which get Screen name of current user based on userId
	 * 
	 * @Param long userId - userId of current user
	 */
	private String getScreenNameByUserId(long userId) {
		
		// api which register in o/api in liferay for user
		String apiUrl = "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts/" + userId;
		String response = CommonApiUtil.callApi(apiUrl, "GET", null); // called api through common api called method 

		if (response != null) {
			JSONObject jsonResponse = null;
			try {
				jsonResponse = JSONFactoryUtil.createJSONObject(response);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			String screenName = jsonResponse.getString("alternateName");
			return screenName;
		} else {
			return null;
		}

	}

	/*
	 * method which validate input field
	 * 
	 * @Param String input - input data of particular input field
	 */
	private boolean validateInput(String input) {
	    // Regular expression to match only alphanumeric characters
	    String regex = "^[a-zA-Z0-9]*$";
	    return input != null && input.matches(regex);
	}
}

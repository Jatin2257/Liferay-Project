package com.book.web.portlet.action;

import com.book.web.api.CommonApiUtil;
import com.book.web.constants.BookWebPortletKeys;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + BookWebPortletKeys.BOOKWEB,
		"mvc.command.name=editBook" }, service = MVCActionCommand.class)
public class EditBookMVCAction extends BaseMVCActionCommand {

	private static final Log _log = LogFactoryUtil.getLog(EditBookMVCAction.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		_log.info("======Edit Book Action Called=====");

		long bookId = ParamUtil.getLong(actionRequest, "bookId");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userId = themeDisplay.getUserId();
		String bookName = ParamUtil.getString(actionRequest, "bookName");
		String author = ParamUtil.getString(actionRequest, "author");
		long price = ParamUtil.getLong(actionRequest, "price");
		String publishDate = ParamUtil.getString(actionRequest, "publishDate");
		String bookType = ParamUtil.getString(actionRequest, "bookType");
		String screenName = getScreenNameByUserId(userId);
		System.out.println("Screen Name : " + screenName);

		// Validate input field
		if (!validateInput(bookName) || !validateInput(author)) {
			SessionErrors.add(actionRequest, "html-tags-error");
			return;
		}
		_log.info("Book Id :" + bookId + ",Screen Name :" + screenName + ",Book Name : " + bookName + ",author : "
				+ author + ",price :" + price + ",publish date : " + publishDate + ",book Type : " + bookType);

		// Create a Json object
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("bookName", bookName);
		jsonObject.put("author", author);
		jsonObject.put("publishDate", publishDate);
		jsonObject.put("bookType", bookType);
		jsonObject.put("screenName", screenName);
		jsonObject.put("price", price);

		String requestBody = jsonObject.toString();

		String apiUrl = "http://localhost:8080/o/books/editbook/" + bookId;
		CommonApiUtil.callApi(apiUrl, "PUT", requestBody);

	}

	/*
	 * method which get screenname by api based on userId
	 * 
	 * @Param long userId - userId of current User in liferay
	 */
	private String getScreenNameByUserId(long userId) {
		String apiUrl = "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts/" + userId;
		String response = CommonApiUtil.callApi(apiUrl, "GET", null);

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

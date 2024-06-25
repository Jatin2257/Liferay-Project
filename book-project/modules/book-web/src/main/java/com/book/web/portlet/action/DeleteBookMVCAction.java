package com.book.web.portlet.action;

import com.book.web.api.CommonApiUtil;
import com.book.web.constants.BookWebPortletKeys;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + BookWebPortletKeys.BOOKWEB,
				"mvc.command.name=deleteBook" 
		},
		service = MVCActionCommand.class
		)
public class DeleteBookMVCAction extends BaseMVCActionCommand {
	
	private static final Log _log = LogFactoryUtil.getLog(DeleteBookMVCAction.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		_log.info("=========Delete Book Method Called========");
		
		long bookId = ParamUtil.getLong(actionRequest, "bookId");
		_log.info("BookId :: "+bookId);
		
		String apiUrl = "http://localhost:8080/o/books/deletebook/"+bookId;// custom rest api url
		CommonApiUtil.callApi(apiUrl, "DELETE", null); // called api method to delete particular book entry
		_log.info("Book Deleted Successfully");
	}

}

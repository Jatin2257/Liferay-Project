package com.book.web.portlet.action;

import com.book.web.constants.BookWebPortletKeys;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + BookWebPortletKeys.BOOKWEB,
		"mvc.command.name=editBook" }, service = MVCRenderCommand.class)
public class EditBookMVCRender implements MVCRenderCommand {

	private static final Log _log = LogFactoryUtil.getLog(EditBookMVCRender.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		_log.info("=======Edit Render Method Called======= ");
		try {
			long bookId = ParamUtil.getLong(renderRequest, "bookId");
			_log.info("Book Id :" + bookId);
			ObjectEntry objectEntry = objectEntryLocalService.getObjectEntry(bookId);
			renderRequest.setAttribute("objectEntry", objectEntry);
			_log.info("Book Entry :" + objectEntry.getValues());

		} catch (PortalException e) {
			e.printStackTrace();
		}

		return "/editbook.jsp";
	}

	@Reference
	private ObjectEntryLocalService objectEntryLocalService;
}

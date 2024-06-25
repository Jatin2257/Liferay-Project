package com.ticket.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.ticket.web.constants.TicketWebPortletKeys;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import chat.service.model.ChatMessage;
import chat.service.service.ChatMessageLocalService;
import helpdesk.service.model.Ticket;
import helpdesk.service.service.TicketLocalService;

@Component(immediate = true, property = { "javax.portlet.name=" + TicketWebPortletKeys.TICKETWEB,
		"mvc.command.name=ticketChatBox" }, service = MVCRenderCommand.class)
public class TicketChatMVCRender implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long userId = themeDisplay.getUserId();
			long ticketId = ParamUtil.getLong(renderRequest, "ticketId");
			Ticket ticket = ticketLocalService.getTicket(ticketId);
			System.out.println("userId " + userId + ",ticketId :" + ticketId);

			List<ChatMessage> messageByTicketId = chatMessageLocalService.getMessageByTicketId(ticketId);

			renderRequest.setAttribute("Ticket", ticket);
			renderRequest.setAttribute("userId", userId);
			renderRequest.setAttribute("messageByTicketId", messageByTicketId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/chatbox.jsp";
	}

	@Reference
	private TicketLocalService ticketLocalService;

	@Reference
	private ChatMessageLocalService chatMessageLocalService;

}

/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package chat.service.service.impl;

import chat.service.model.ChatMessage;
import chat.service.service.base.ChatMessageLocalServiceBaseImpl;
import chat.service.service.persistence.ChatMessagePersistence;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.aop.AopService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=chat.service.model.ChatMessage",
	service = AopService.class
)
public class ChatMessageLocalServiceImpl
	extends ChatMessageLocalServiceBaseImpl {
	
	
	public void addChatMessage(long ticketId,long userId,String message,String imageAttached) {
		ChatMessage chatMessage = chatMessagePersistence1.create(counterLocalService1.increment(ChatMessage.class.getName()));
		chatMessage.setTicketId(ticketId);
		chatMessage.setUserId(userId);
		chatMessage.setMessage(message);
		chatMessage.setImageattached(imageAttached);
		chatMessagePersistence1.update(chatMessage);
	}
	
	public List<ChatMessage> getMessageByTicketId(long ticketId){
		return chatMessagePersistence1.findByticketId(ticketId);
	}
	
	@Reference
	private ChatMessagePersistence chatMessagePersistence1;
	
	@Reference
	private CounterLocalService counterLocalService1;
}
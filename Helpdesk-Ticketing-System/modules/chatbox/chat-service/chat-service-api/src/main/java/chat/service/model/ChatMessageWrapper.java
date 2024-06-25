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

package chat.service.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ChatMessage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ChatMessage
 * @generated
 */
public class ChatMessageWrapper
	extends BaseModelWrapper<ChatMessage>
	implements ChatMessage, ModelWrapper<ChatMessage> {

	public ChatMessageWrapper(ChatMessage chatMessage) {
		super(chatMessage);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("messageId", getMessageId());
		attributes.put("userId", getUserId());
		attributes.put("ticketId", getTicketId());
		attributes.put("message", getMessage());
		attributes.put("timestamp", getTimestamp());
		attributes.put("imageattached", getImageattached());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long messageId = (Long)attributes.get("messageId");

		if (messageId != null) {
			setMessageId(messageId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long ticketId = (Long)attributes.get("ticketId");

		if (ticketId != null) {
			setTicketId(ticketId);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
		}

		Date timestamp = (Date)attributes.get("timestamp");

		if (timestamp != null) {
			setTimestamp(timestamp);
		}

		String imageattached = (String)attributes.get("imageattached");

		if (imageattached != null) {
			setImageattached(imageattached);
		}
	}

	@Override
	public ChatMessage cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the imageattached of this chat message.
	 *
	 * @return the imageattached of this chat message
	 */
	@Override
	public String getImageattached() {
		return model.getImageattached();
	}

	/**
	 * Returns the message of this chat message.
	 *
	 * @return the message of this chat message
	 */
	@Override
	public String getMessage() {
		return model.getMessage();
	}

	/**
	 * Returns the message ID of this chat message.
	 *
	 * @return the message ID of this chat message
	 */
	@Override
	public long getMessageId() {
		return model.getMessageId();
	}

	/**
	 * Returns the primary key of this chat message.
	 *
	 * @return the primary key of this chat message
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the ticket ID of this chat message.
	 *
	 * @return the ticket ID of this chat message
	 */
	@Override
	public long getTicketId() {
		return model.getTicketId();
	}

	/**
	 * Returns the timestamp of this chat message.
	 *
	 * @return the timestamp of this chat message
	 */
	@Override
	public Date getTimestamp() {
		return model.getTimestamp();
	}

	/**
	 * Returns the user ID of this chat message.
	 *
	 * @return the user ID of this chat message
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this chat message.
	 *
	 * @return the user uuid of this chat message
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the imageattached of this chat message.
	 *
	 * @param imageattached the imageattached of this chat message
	 */
	@Override
	public void setImageattached(String imageattached) {
		model.setImageattached(imageattached);
	}

	/**
	 * Sets the message of this chat message.
	 *
	 * @param message the message of this chat message
	 */
	@Override
	public void setMessage(String message) {
		model.setMessage(message);
	}

	/**
	 * Sets the message ID of this chat message.
	 *
	 * @param messageId the message ID of this chat message
	 */
	@Override
	public void setMessageId(long messageId) {
		model.setMessageId(messageId);
	}

	/**
	 * Sets the primary key of this chat message.
	 *
	 * @param primaryKey the primary key of this chat message
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the ticket ID of this chat message.
	 *
	 * @param ticketId the ticket ID of this chat message
	 */
	@Override
	public void setTicketId(long ticketId) {
		model.setTicketId(ticketId);
	}

	/**
	 * Sets the timestamp of this chat message.
	 *
	 * @param timestamp the timestamp of this chat message
	 */
	@Override
	public void setTimestamp(Date timestamp) {
		model.setTimestamp(timestamp);
	}

	/**
	 * Sets the user ID of this chat message.
	 *
	 * @param userId the user ID of this chat message
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this chat message.
	 *
	 * @param userUuid the user uuid of this chat message
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected ChatMessageWrapper wrap(ChatMessage chatMessage) {
		return new ChatMessageWrapper(chatMessage);
	}

}
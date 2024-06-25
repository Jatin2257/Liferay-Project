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

package chat.service.model.impl;

import chat.service.model.ChatMessage;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ChatMessage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ChatMessageCacheModel
	implements CacheModel<ChatMessage>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ChatMessageCacheModel)) {
			return false;
		}

		ChatMessageCacheModel chatMessageCacheModel =
			(ChatMessageCacheModel)object;

		if (messageId == chatMessageCacheModel.messageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, messageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{messageId=");
		sb.append(messageId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", ticketId=");
		sb.append(ticketId);
		sb.append(", message=");
		sb.append(message);
		sb.append(", timestamp=");
		sb.append(timestamp);
		sb.append(", imageattached=");
		sb.append(imageattached);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ChatMessage toEntityModel() {
		ChatMessageImpl chatMessageImpl = new ChatMessageImpl();

		chatMessageImpl.setMessageId(messageId);
		chatMessageImpl.setUserId(userId);
		chatMessageImpl.setTicketId(ticketId);

		if (message == null) {
			chatMessageImpl.setMessage("");
		}
		else {
			chatMessageImpl.setMessage(message);
		}

		if (timestamp == Long.MIN_VALUE) {
			chatMessageImpl.setTimestamp(null);
		}
		else {
			chatMessageImpl.setTimestamp(new Date(timestamp));
		}

		if (imageattached == null) {
			chatMessageImpl.setImageattached("");
		}
		else {
			chatMessageImpl.setImageattached(imageattached);
		}

		chatMessageImpl.resetOriginalValues();

		return chatMessageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		messageId = objectInput.readLong();

		userId = objectInput.readLong();

		ticketId = objectInput.readLong();
		message = objectInput.readUTF();
		timestamp = objectInput.readLong();
		imageattached = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(messageId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(ticketId);

		if (message == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(message);
		}

		objectOutput.writeLong(timestamp);

		if (imageattached == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imageattached);
		}
	}

	public long messageId;
	public long userId;
	public long ticketId;
	public String message;
	public long timestamp;
	public String imageattached;

}
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

package helpdesk.service.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import helpdesk.service.model.Ticket;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Ticket in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TicketCacheModel implements CacheModel<Ticket>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TicketCacheModel)) {
			return false;
		}

		TicketCacheModel ticketCacheModel = (TicketCacheModel)object;

		if (ticketId == ticketCacheModel.ticketId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ticketId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{ticketId=");
		sb.append(ticketId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", requester=");
		sb.append(requester);
		sb.append(", email=");
		sb.append(email);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", description=");
		sb.append(description);
		sb.append(", department=");
		sb.append(department);
		sb.append(", category=");
		sb.append(category);
		sb.append(", subcategory=");
		sb.append(subcategory);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", imageattached=");
		sb.append(imageattached);
		sb.append(", status=");
		sb.append(status);
		sb.append(", createdate=");
		sb.append(createdate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Ticket toEntityModel() {
		TicketImpl ticketImpl = new TicketImpl();

		ticketImpl.setTicketId(ticketId);
		ticketImpl.setUserId(userId);

		if (requester == null) {
			ticketImpl.setRequester("");
		}
		else {
			ticketImpl.setRequester(requester);
		}

		if (email == null) {
			ticketImpl.setEmail("");
		}
		else {
			ticketImpl.setEmail(email);
		}

		if (subject == null) {
			ticketImpl.setSubject("");
		}
		else {
			ticketImpl.setSubject(subject);
		}

		if (description == null) {
			ticketImpl.setDescription("");
		}
		else {
			ticketImpl.setDescription(description);
		}

		if (department == null) {
			ticketImpl.setDepartment("");
		}
		else {
			ticketImpl.setDepartment(department);
		}

		if (category == null) {
			ticketImpl.setCategory("");
		}
		else {
			ticketImpl.setCategory(category);
		}

		if (subcategory == null) {
			ticketImpl.setSubcategory("");
		}
		else {
			ticketImpl.setSubcategory(subcategory);
		}

		if (priority == null) {
			ticketImpl.setPriority("");
		}
		else {
			ticketImpl.setPriority(priority);
		}

		if (imageattached == null) {
			ticketImpl.setImageattached("");
		}
		else {
			ticketImpl.setImageattached(imageattached);
		}

		if (status == null) {
			ticketImpl.setStatus("");
		}
		else {
			ticketImpl.setStatus(status);
		}

		if (createdate == null) {
			ticketImpl.setCreatedate("");
		}
		else {
			ticketImpl.setCreatedate(createdate);
		}

		ticketImpl.resetOriginalValues();

		return ticketImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketId = objectInput.readLong();

		userId = objectInput.readLong();
		requester = objectInput.readUTF();
		email = objectInput.readUTF();
		subject = objectInput.readUTF();
		description = objectInput.readUTF();
		department = objectInput.readUTF();
		category = objectInput.readUTF();
		subcategory = objectInput.readUTF();
		priority = objectInput.readUTF();
		imageattached = objectInput.readUTF();
		status = objectInput.readUTF();
		createdate = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(ticketId);

		objectOutput.writeLong(userId);

		if (requester == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(requester);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (subject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subject);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (department == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(department);
		}

		if (category == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(category);
		}

		if (subcategory == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subcategory);
		}

		if (priority == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(priority);
		}

		if (imageattached == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imageattached);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (createdate == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(createdate);
		}
	}

	public long ticketId;
	public long userId;
	public String requester;
	public String email;
	public String subject;
	public String description;
	public String department;
	public String category;
	public String subcategory;
	public String priority;
	public String imageattached;
	public String status;
	public String createdate;

}
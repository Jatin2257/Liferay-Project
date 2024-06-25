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

package helpdesk.service.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Ticket}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Ticket
 * @generated
 */
public class TicketWrapper
	extends BaseModelWrapper<Ticket> implements ModelWrapper<Ticket>, Ticket {

	public TicketWrapper(Ticket ticket) {
		super(ticket);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketId", getTicketId());
		attributes.put("userId", getUserId());
		attributes.put("requester", getRequester());
		attributes.put("email", getEmail());
		attributes.put("subject", getSubject());
		attributes.put("description", getDescription());
		attributes.put("department", getDepartment());
		attributes.put("category", getCategory());
		attributes.put("subcategory", getSubcategory());
		attributes.put("priority", getPriority());
		attributes.put("imageattached", getImageattached());
		attributes.put("status", getStatus());
		attributes.put("createdate", getCreatedate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketId = (Long)attributes.get("ticketId");

		if (ticketId != null) {
			setTicketId(ticketId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String requester = (String)attributes.get("requester");

		if (requester != null) {
			setRequester(requester);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String department = (String)attributes.get("department");

		if (department != null) {
			setDepartment(department);
		}

		String category = (String)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		String subcategory = (String)attributes.get("subcategory");

		if (subcategory != null) {
			setSubcategory(subcategory);
		}

		String priority = (String)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		String imageattached = (String)attributes.get("imageattached");

		if (imageattached != null) {
			setImageattached(imageattached);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String createdate = (String)attributes.get("createdate");

		if (createdate != null) {
			setCreatedate(createdate);
		}
	}

	@Override
	public Ticket cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the category of this ticket.
	 *
	 * @return the category of this ticket
	 */
	@Override
	public String getCategory() {
		return model.getCategory();
	}

	/**
	 * Returns the createdate of this ticket.
	 *
	 * @return the createdate of this ticket
	 */
	@Override
	public String getCreatedate() {
		return model.getCreatedate();
	}

	/**
	 * Returns the department of this ticket.
	 *
	 * @return the department of this ticket
	 */
	@Override
	public String getDepartment() {
		return model.getDepartment();
	}

	/**
	 * Returns the description of this ticket.
	 *
	 * @return the description of this ticket
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the email of this ticket.
	 *
	 * @return the email of this ticket
	 */
	@Override
	public String getEmail() {
		return model.getEmail();
	}

	/**
	 * Returns the imageattached of this ticket.
	 *
	 * @return the imageattached of this ticket
	 */
	@Override
	public String getImageattached() {
		return model.getImageattached();
	}

	/**
	 * Returns the primary key of this ticket.
	 *
	 * @return the primary key of this ticket
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the priority of this ticket.
	 *
	 * @return the priority of this ticket
	 */
	@Override
	public String getPriority() {
		return model.getPriority();
	}

	/**
	 * Returns the requester of this ticket.
	 *
	 * @return the requester of this ticket
	 */
	@Override
	public String getRequester() {
		return model.getRequester();
	}

	/**
	 * Returns the status of this ticket.
	 *
	 * @return the status of this ticket
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the subcategory of this ticket.
	 *
	 * @return the subcategory of this ticket
	 */
	@Override
	public String getSubcategory() {
		return model.getSubcategory();
	}

	/**
	 * Returns the subject of this ticket.
	 *
	 * @return the subject of this ticket
	 */
	@Override
	public String getSubject() {
		return model.getSubject();
	}

	/**
	 * Returns the ticket ID of this ticket.
	 *
	 * @return the ticket ID of this ticket
	 */
	@Override
	public long getTicketId() {
		return model.getTicketId();
	}

	/**
	 * Returns the user ID of this ticket.
	 *
	 * @return the user ID of this ticket
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this ticket.
	 *
	 * @return the user uuid of this ticket
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
	 * Sets the category of this ticket.
	 *
	 * @param category the category of this ticket
	 */
	@Override
	public void setCategory(String category) {
		model.setCategory(category);
	}

	/**
	 * Sets the createdate of this ticket.
	 *
	 * @param createdate the createdate of this ticket
	 */
	@Override
	public void setCreatedate(String createdate) {
		model.setCreatedate(createdate);
	}

	/**
	 * Sets the department of this ticket.
	 *
	 * @param department the department of this ticket
	 */
	@Override
	public void setDepartment(String department) {
		model.setDepartment(department);
	}

	/**
	 * Sets the description of this ticket.
	 *
	 * @param description the description of this ticket
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the email of this ticket.
	 *
	 * @param email the email of this ticket
	 */
	@Override
	public void setEmail(String email) {
		model.setEmail(email);
	}

	/**
	 * Sets the imageattached of this ticket.
	 *
	 * @param imageattached the imageattached of this ticket
	 */
	@Override
	public void setImageattached(String imageattached) {
		model.setImageattached(imageattached);
	}

	/**
	 * Sets the primary key of this ticket.
	 *
	 * @param primaryKey the primary key of this ticket
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the priority of this ticket.
	 *
	 * @param priority the priority of this ticket
	 */
	@Override
	public void setPriority(String priority) {
		model.setPriority(priority);
	}

	/**
	 * Sets the requester of this ticket.
	 *
	 * @param requester the requester of this ticket
	 */
	@Override
	public void setRequester(String requester) {
		model.setRequester(requester);
	}

	/**
	 * Sets the status of this ticket.
	 *
	 * @param status the status of this ticket
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the subcategory of this ticket.
	 *
	 * @param subcategory the subcategory of this ticket
	 */
	@Override
	public void setSubcategory(String subcategory) {
		model.setSubcategory(subcategory);
	}

	/**
	 * Sets the subject of this ticket.
	 *
	 * @param subject the subject of this ticket
	 */
	@Override
	public void setSubject(String subject) {
		model.setSubject(subject);
	}

	/**
	 * Sets the ticket ID of this ticket.
	 *
	 * @param ticketId the ticket ID of this ticket
	 */
	@Override
	public void setTicketId(long ticketId) {
		model.setTicketId(ticketId);
	}

	/**
	 * Sets the user ID of this ticket.
	 *
	 * @param userId the user ID of this ticket
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this ticket.
	 *
	 * @param userUuid the user uuid of this ticket
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
	protected TicketWrapper wrap(Ticket ticket) {
		return new TicketWrapper(ticket);
	}

}
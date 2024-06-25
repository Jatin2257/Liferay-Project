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

package helpdesk.service.service.impl;

import com.liferay.portal.aop.AopService;

import java.util.List;

import helpdesk.service.model.Ticket;
import helpdesk.service.service.base.TicketLocalServiceBaseImpl;
import helpdesk.service.service.persistence.TicketPersistence;
import helpdesk.service.service.TicketLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = "model.class.name=helpdesk.service.model.Ticket", service = AopService.class)
public class TicketLocalServiceImpl extends TicketLocalServiceBaseImpl {
	
	@Reference
	private TicketPersistence ticketPersistence1;
	
	public void addTicket(long ticketId,long userId, String requester, String email, String subject, String description,
			String department, String category, String subcategory, String priority, String imageattached,
			String status, String createDate) {
		Ticket ticket = ticketPersistence1.create(ticketId);
		ticket.setUserId(userId);
		ticket.setRequester(requester);
		ticket.setEmail(email);
		ticket.setSubject(subject);
		ticket.setDescription(description);
		ticket.setDepartment(department);
		ticket.setCategory(category);
		ticket.setSubcategory(subcategory);
		ticket.setPriority(priority);
		ticket.setImageattached(imageattached);
		ticket.setStatus(status);
		ticket.setCreatedate(createDate);
		
		ticketPersistence1.update(ticket);
	}
	public List<Ticket> findTicketByUserId(long userId){
		List<Ticket> tickets = ticketPersistence1.findByuserId(userId);
		return tickets;
	}
	
	public List<Ticket> findTicketByDepartment(String department){
		List<Ticket> departmentTickets = ticketPersistence1.findBydepartment(department);
		return departmentTickets;
	}
	
	public int countTicketByUserID(long userId) {
		return ticketPersistence1.countByuserId(userId);
	}
	
	public int countTicketByDepartment(String department) {
		return ticketPersistence1.countBydepartment(department);
	}
	
	
}
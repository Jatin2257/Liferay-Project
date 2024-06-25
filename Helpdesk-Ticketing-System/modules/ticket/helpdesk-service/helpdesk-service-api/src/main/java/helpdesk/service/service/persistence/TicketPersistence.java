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

package helpdesk.service.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import helpdesk.service.exception.NoSuchTicketException;
import helpdesk.service.model.Ticket;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ticket service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketUtil
 * @generated
 */
@ProviderType
public interface TicketPersistence extends BasePersistence<Ticket> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketUtil} to access the ticket persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the tickets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching tickets
	 */
	public java.util.List<Ticket> findByuserId(long userId);

	/**
	 * Returns a range of all the tickets where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of tickets
	 * @param end the upper bound of the range of tickets (not inclusive)
	 * @return the range of matching tickets
	 */
	public java.util.List<Ticket> findByuserId(long userId, int start, int end);

	/**
	 * Returns an ordered range of all the tickets where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of tickets
	 * @param end the upper bound of the range of tickets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tickets
	 */
	public java.util.List<Ticket> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator);

	/**
	 * Returns an ordered range of all the tickets where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of tickets
	 * @param end the upper bound of the range of tickets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tickets
	 */
	public java.util.List<Ticket> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	public Ticket findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Ticket>
				orderByComparator)
		throws NoSuchTicketException;

	/**
	 * Returns the first ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	public Ticket fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator);

	/**
	 * Returns the last ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	public Ticket findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Ticket>
				orderByComparator)
		throws NoSuchTicketException;

	/**
	 * Returns the last ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	public Ticket fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator);

	/**
	 * Returns the tickets before and after the current ticket in the ordered set where userId = &#63;.
	 *
	 * @param ticketId the primary key of the current ticket
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	public Ticket[] findByuserId_PrevAndNext(
			long ticketId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Ticket>
				orderByComparator)
		throws NoSuchTicketException;

	/**
	 * Removes all the tickets where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of tickets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching tickets
	 */
	public int countByuserId(long userId);

	/**
	 * Returns all the tickets where department = &#63;.
	 *
	 * @param department the department
	 * @return the matching tickets
	 */
	public java.util.List<Ticket> findBydepartment(String department);

	/**
	 * Returns a range of all the tickets where department = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketModelImpl</code>.
	 * </p>
	 *
	 * @param department the department
	 * @param start the lower bound of the range of tickets
	 * @param end the upper bound of the range of tickets (not inclusive)
	 * @return the range of matching tickets
	 */
	public java.util.List<Ticket> findBydepartment(
		String department, int start, int end);

	/**
	 * Returns an ordered range of all the tickets where department = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketModelImpl</code>.
	 * </p>
	 *
	 * @param department the department
	 * @param start the lower bound of the range of tickets
	 * @param end the upper bound of the range of tickets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tickets
	 */
	public java.util.List<Ticket> findBydepartment(
		String department, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator);

	/**
	 * Returns an ordered range of all the tickets where department = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketModelImpl</code>.
	 * </p>
	 *
	 * @param department the department
	 * @param start the lower bound of the range of tickets
	 * @param end the upper bound of the range of tickets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching tickets
	 */
	public java.util.List<Ticket> findBydepartment(
		String department, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	public Ticket findBydepartment_First(
			String department,
			com.liferay.portal.kernel.util.OrderByComparator<Ticket>
				orderByComparator)
		throws NoSuchTicketException;

	/**
	 * Returns the first ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	public Ticket fetchBydepartment_First(
		String department,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator);

	/**
	 * Returns the last ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	public Ticket findBydepartment_Last(
			String department,
			com.liferay.portal.kernel.util.OrderByComparator<Ticket>
				orderByComparator)
		throws NoSuchTicketException;

	/**
	 * Returns the last ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	public Ticket fetchBydepartment_Last(
		String department,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator);

	/**
	 * Returns the tickets before and after the current ticket in the ordered set where department = &#63;.
	 *
	 * @param ticketId the primary key of the current ticket
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	public Ticket[] findBydepartment_PrevAndNext(
			long ticketId, String department,
			com.liferay.portal.kernel.util.OrderByComparator<Ticket>
				orderByComparator)
		throws NoSuchTicketException;

	/**
	 * Removes all the tickets where department = &#63; from the database.
	 *
	 * @param department the department
	 */
	public void removeBydepartment(String department);

	/**
	 * Returns the number of tickets where department = &#63;.
	 *
	 * @param department the department
	 * @return the number of matching tickets
	 */
	public int countBydepartment(String department);

	/**
	 * Caches the ticket in the entity cache if it is enabled.
	 *
	 * @param ticket the ticket
	 */
	public void cacheResult(Ticket ticket);

	/**
	 * Caches the tickets in the entity cache if it is enabled.
	 *
	 * @param tickets the tickets
	 */
	public void cacheResult(java.util.List<Ticket> tickets);

	/**
	 * Creates a new ticket with the primary key. Does not add the ticket to the database.
	 *
	 * @param ticketId the primary key for the new ticket
	 * @return the new ticket
	 */
	public Ticket create(long ticketId);

	/**
	 * Removes the ticket with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketId the primary key of the ticket
	 * @return the ticket that was removed
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	public Ticket remove(long ticketId) throws NoSuchTicketException;

	public Ticket updateImpl(Ticket ticket);

	/**
	 * Returns the ticket with the primary key or throws a <code>NoSuchTicketException</code> if it could not be found.
	 *
	 * @param ticketId the primary key of the ticket
	 * @return the ticket
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	public Ticket findByPrimaryKey(long ticketId) throws NoSuchTicketException;

	/**
	 * Returns the ticket with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketId the primary key of the ticket
	 * @return the ticket, or <code>null</code> if a ticket with the primary key could not be found
	 */
	public Ticket fetchByPrimaryKey(long ticketId);

	/**
	 * Returns all the tickets.
	 *
	 * @return the tickets
	 */
	public java.util.List<Ticket> findAll();

	/**
	 * Returns a range of all the tickets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tickets
	 * @param end the upper bound of the range of tickets (not inclusive)
	 * @return the range of tickets
	 */
	public java.util.List<Ticket> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the tickets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tickets
	 * @param end the upper bound of the range of tickets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tickets
	 */
	public java.util.List<Ticket> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator);

	/**
	 * Returns an ordered range of all the tickets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of tickets
	 * @param end the upper bound of the range of tickets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of tickets
	 */
	public java.util.List<Ticket> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ticket>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the tickets from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of tickets.
	 *
	 * @return the number of tickets
	 */
	public int countAll();

}
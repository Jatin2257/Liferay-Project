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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import helpdesk.service.model.Ticket;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the ticket service. This utility wraps <code>helpdesk.service.service.persistence.impl.TicketPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketPersistence
 * @generated
 */
public class TicketUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Ticket ticket) {
		getPersistence().clearCache(ticket);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Ticket> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Ticket> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Ticket> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Ticket> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Ticket> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Ticket update(Ticket ticket) {
		return getPersistence().update(ticket);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Ticket update(Ticket ticket, ServiceContext serviceContext) {
		return getPersistence().update(ticket, serviceContext);
	}

	/**
	 * Returns all the tickets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching tickets
	 */
	public static List<Ticket> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

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
	public static List<Ticket> findByuserId(long userId, int start, int end) {
		return getPersistence().findByuserId(userId, start, end);
	}

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
	public static List<Ticket> findByuserId(
		long userId, int start, int end,
		OrderByComparator<Ticket> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

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
	public static List<Ticket> findByuserId(
		long userId, int start, int end,
		OrderByComparator<Ticket> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	public static Ticket findByuserId_First(
			long userId, OrderByComparator<Ticket> orderByComparator)
		throws helpdesk.service.exception.NoSuchTicketException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	public static Ticket fetchByuserId_First(
		long userId, OrderByComparator<Ticket> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	public static Ticket findByuserId_Last(
			long userId, OrderByComparator<Ticket> orderByComparator)
		throws helpdesk.service.exception.NoSuchTicketException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	public static Ticket fetchByuserId_Last(
		long userId, OrderByComparator<Ticket> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the tickets before and after the current ticket in the ordered set where userId = &#63;.
	 *
	 * @param ticketId the primary key of the current ticket
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	public static Ticket[] findByuserId_PrevAndNext(
			long ticketId, long userId,
			OrderByComparator<Ticket> orderByComparator)
		throws helpdesk.service.exception.NoSuchTicketException {

		return getPersistence().findByuserId_PrevAndNext(
			ticketId, userId, orderByComparator);
	}

	/**
	 * Removes all the tickets where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of tickets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching tickets
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns all the tickets where department = &#63;.
	 *
	 * @param department the department
	 * @return the matching tickets
	 */
	public static List<Ticket> findBydepartment(String department) {
		return getPersistence().findBydepartment(department);
	}

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
	public static List<Ticket> findBydepartment(
		String department, int start, int end) {

		return getPersistence().findBydepartment(department, start, end);
	}

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
	public static List<Ticket> findBydepartment(
		String department, int start, int end,
		OrderByComparator<Ticket> orderByComparator) {

		return getPersistence().findBydepartment(
			department, start, end, orderByComparator);
	}

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
	public static List<Ticket> findBydepartment(
		String department, int start, int end,
		OrderByComparator<Ticket> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBydepartment(
			department, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	public static Ticket findBydepartment_First(
			String department, OrderByComparator<Ticket> orderByComparator)
		throws helpdesk.service.exception.NoSuchTicketException {

		return getPersistence().findBydepartment_First(
			department, orderByComparator);
	}

	/**
	 * Returns the first ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	public static Ticket fetchBydepartment_First(
		String department, OrderByComparator<Ticket> orderByComparator) {

		return getPersistence().fetchBydepartment_First(
			department, orderByComparator);
	}

	/**
	 * Returns the last ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	public static Ticket findBydepartment_Last(
			String department, OrderByComparator<Ticket> orderByComparator)
		throws helpdesk.service.exception.NoSuchTicketException {

		return getPersistence().findBydepartment_Last(
			department, orderByComparator);
	}

	/**
	 * Returns the last ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	public static Ticket fetchBydepartment_Last(
		String department, OrderByComparator<Ticket> orderByComparator) {

		return getPersistence().fetchBydepartment_Last(
			department, orderByComparator);
	}

	/**
	 * Returns the tickets before and after the current ticket in the ordered set where department = &#63;.
	 *
	 * @param ticketId the primary key of the current ticket
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	public static Ticket[] findBydepartment_PrevAndNext(
			long ticketId, String department,
			OrderByComparator<Ticket> orderByComparator)
		throws helpdesk.service.exception.NoSuchTicketException {

		return getPersistence().findBydepartment_PrevAndNext(
			ticketId, department, orderByComparator);
	}

	/**
	 * Removes all the tickets where department = &#63; from the database.
	 *
	 * @param department the department
	 */
	public static void removeBydepartment(String department) {
		getPersistence().removeBydepartment(department);
	}

	/**
	 * Returns the number of tickets where department = &#63;.
	 *
	 * @param department the department
	 * @return the number of matching tickets
	 */
	public static int countBydepartment(String department) {
		return getPersistence().countBydepartment(department);
	}

	/**
	 * Caches the ticket in the entity cache if it is enabled.
	 *
	 * @param ticket the ticket
	 */
	public static void cacheResult(Ticket ticket) {
		getPersistence().cacheResult(ticket);
	}

	/**
	 * Caches the tickets in the entity cache if it is enabled.
	 *
	 * @param tickets the tickets
	 */
	public static void cacheResult(List<Ticket> tickets) {
		getPersistence().cacheResult(tickets);
	}

	/**
	 * Creates a new ticket with the primary key. Does not add the ticket to the database.
	 *
	 * @param ticketId the primary key for the new ticket
	 * @return the new ticket
	 */
	public static Ticket create(long ticketId) {
		return getPersistence().create(ticketId);
	}

	/**
	 * Removes the ticket with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketId the primary key of the ticket
	 * @return the ticket that was removed
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	public static Ticket remove(long ticketId)
		throws helpdesk.service.exception.NoSuchTicketException {

		return getPersistence().remove(ticketId);
	}

	public static Ticket updateImpl(Ticket ticket) {
		return getPersistence().updateImpl(ticket);
	}

	/**
	 * Returns the ticket with the primary key or throws a <code>NoSuchTicketException</code> if it could not be found.
	 *
	 * @param ticketId the primary key of the ticket
	 * @return the ticket
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	public static Ticket findByPrimaryKey(long ticketId)
		throws helpdesk.service.exception.NoSuchTicketException {

		return getPersistence().findByPrimaryKey(ticketId);
	}

	/**
	 * Returns the ticket with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketId the primary key of the ticket
	 * @return the ticket, or <code>null</code> if a ticket with the primary key could not be found
	 */
	public static Ticket fetchByPrimaryKey(long ticketId) {
		return getPersistence().fetchByPrimaryKey(ticketId);
	}

	/**
	 * Returns all the tickets.
	 *
	 * @return the tickets
	 */
	public static List<Ticket> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Ticket> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Ticket> findAll(
		int start, int end, OrderByComparator<Ticket> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Ticket> findAll(
		int start, int end, OrderByComparator<Ticket> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the tickets from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of tickets.
	 *
	 * @return the number of tickets
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TicketPersistence getPersistence() {
		return _persistence;
	}

	private static volatile TicketPersistence _persistence;

}
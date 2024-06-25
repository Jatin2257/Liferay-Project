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

package helpdesk.service.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import helpdesk.service.exception.NoSuchTicketException;
import helpdesk.service.model.Ticket;
import helpdesk.service.model.TicketTable;
import helpdesk.service.model.impl.TicketImpl;
import helpdesk.service.model.impl.TicketModelImpl;
import helpdesk.service.service.persistence.TicketPersistence;
import helpdesk.service.service.persistence.TicketUtil;
import helpdesk.service.service.persistence.impl.constants.helpdeskPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the ticket service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TicketPersistence.class)
public class TicketPersistenceImpl
	extends BasePersistenceImpl<Ticket> implements TicketPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TicketUtil</code> to access the ticket persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TicketImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByuserId;
	private FinderPath _finderPathWithoutPaginationFindByuserId;
	private FinderPath _finderPathCountByuserId;

	/**
	 * Returns all the tickets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching tickets
	 */
	@Override
	public List<Ticket> findByuserId(long userId) {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Ticket> findByuserId(long userId, int start, int end) {
		return findByuserId(userId, start, end, null);
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
	@Override
	public List<Ticket> findByuserId(
		long userId, int start, int end,
		OrderByComparator<Ticket> orderByComparator) {

		return findByuserId(userId, start, end, orderByComparator, true);
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
	@Override
	public List<Ticket> findByuserId(
		long userId, int start, int end,
		OrderByComparator<Ticket> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByuserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByuserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<Ticket> list = null;

		if (useFinderCache) {
			list = (List<Ticket>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Ticket ticket : list) {
					if (userId != ticket.getUserId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_TICKET_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TicketModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<Ticket>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	@Override
	public Ticket findByuserId_First(
			long userId, OrderByComparator<Ticket> orderByComparator)
		throws NoSuchTicketException {

		Ticket ticket = fetchByuserId_First(userId, orderByComparator);

		if (ticket != null) {
			return ticket;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchTicketException(sb.toString());
	}

	/**
	 * Returns the first ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	@Override
	public Ticket fetchByuserId_First(
		long userId, OrderByComparator<Ticket> orderByComparator) {

		List<Ticket> list = findByuserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	@Override
	public Ticket findByuserId_Last(
			long userId, OrderByComparator<Ticket> orderByComparator)
		throws NoSuchTicketException {

		Ticket ticket = fetchByuserId_Last(userId, orderByComparator);

		if (ticket != null) {
			return ticket;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchTicketException(sb.toString());
	}

	/**
	 * Returns the last ticket in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	@Override
	public Ticket fetchByuserId_Last(
		long userId, OrderByComparator<Ticket> orderByComparator) {

		int count = countByuserId(userId);

		if (count == 0) {
			return null;
		}

		List<Ticket> list = findByuserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Ticket[] findByuserId_PrevAndNext(
			long ticketId, long userId,
			OrderByComparator<Ticket> orderByComparator)
		throws NoSuchTicketException {

		Ticket ticket = findByPrimaryKey(ticketId);

		Session session = null;

		try {
			session = openSession();

			Ticket[] array = new TicketImpl[3];

			array[0] = getByuserId_PrevAndNext(
				session, ticket, userId, orderByComparator, true);

			array[1] = ticket;

			array[2] = getByuserId_PrevAndNext(
				session, ticket, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Ticket getByuserId_PrevAndNext(
		Session session, Ticket ticket, long userId,
		OrderByComparator<Ticket> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TICKET_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TicketModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(ticket)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Ticket> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tickets where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByuserId(long userId) {
		for (Ticket ticket :
				findByuserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ticket);
		}
	}

	/**
	 * Returns the number of tickets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching tickets
	 */
	@Override
	public int countByuserId(long userId) {
		FinderPath finderPath = _finderPathCountByuserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TICKET_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"ticket.userId = ?";

	private FinderPath _finderPathWithPaginationFindBydepartment;
	private FinderPath _finderPathWithoutPaginationFindBydepartment;
	private FinderPath _finderPathCountBydepartment;

	/**
	 * Returns all the tickets where department = &#63;.
	 *
	 * @param department the department
	 * @return the matching tickets
	 */
	@Override
	public List<Ticket> findBydepartment(String department) {
		return findBydepartment(
			department, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Ticket> findBydepartment(
		String department, int start, int end) {

		return findBydepartment(department, start, end, null);
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
	@Override
	public List<Ticket> findBydepartment(
		String department, int start, int end,
		OrderByComparator<Ticket> orderByComparator) {

		return findBydepartment(
			department, start, end, orderByComparator, true);
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
	@Override
	public List<Ticket> findBydepartment(
		String department, int start, int end,
		OrderByComparator<Ticket> orderByComparator, boolean useFinderCache) {

		department = Objects.toString(department, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBydepartment;
				finderArgs = new Object[] {department};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBydepartment;
			finderArgs = new Object[] {
				department, start, end, orderByComparator
			};
		}

		List<Ticket> list = null;

		if (useFinderCache) {
			list = (List<Ticket>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Ticket ticket : list) {
					if (!department.equals(ticket.getDepartment())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_TICKET_WHERE);

			boolean bindDepartment = false;

			if (department.isEmpty()) {
				sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_3);
			}
			else {
				bindDepartment = true;

				sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TicketModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDepartment) {
					queryPos.add(department);
				}

				list = (List<Ticket>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	@Override
	public Ticket findBydepartment_First(
			String department, OrderByComparator<Ticket> orderByComparator)
		throws NoSuchTicketException {

		Ticket ticket = fetchBydepartment_First(department, orderByComparator);

		if (ticket != null) {
			return ticket;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("department=");
		sb.append(department);

		sb.append("}");

		throw new NoSuchTicketException(sb.toString());
	}

	/**
	 * Returns the first ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	@Override
	public Ticket fetchBydepartment_First(
		String department, OrderByComparator<Ticket> orderByComparator) {

		List<Ticket> list = findBydepartment(
			department, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket
	 * @throws NoSuchTicketException if a matching ticket could not be found
	 */
	@Override
	public Ticket findBydepartment_Last(
			String department, OrderByComparator<Ticket> orderByComparator)
		throws NoSuchTicketException {

		Ticket ticket = fetchBydepartment_Last(department, orderByComparator);

		if (ticket != null) {
			return ticket;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("department=");
		sb.append(department);

		sb.append("}");

		throw new NoSuchTicketException(sb.toString());
	}

	/**
	 * Returns the last ticket in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket, or <code>null</code> if a matching ticket could not be found
	 */
	@Override
	public Ticket fetchBydepartment_Last(
		String department, OrderByComparator<Ticket> orderByComparator) {

		int count = countBydepartment(department);

		if (count == 0) {
			return null;
		}

		List<Ticket> list = findBydepartment(
			department, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Ticket[] findBydepartment_PrevAndNext(
			long ticketId, String department,
			OrderByComparator<Ticket> orderByComparator)
		throws NoSuchTicketException {

		department = Objects.toString(department, "");

		Ticket ticket = findByPrimaryKey(ticketId);

		Session session = null;

		try {
			session = openSession();

			Ticket[] array = new TicketImpl[3];

			array[0] = getBydepartment_PrevAndNext(
				session, ticket, department, orderByComparator, true);

			array[1] = ticket;

			array[2] = getBydepartment_PrevAndNext(
				session, ticket, department, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Ticket getBydepartment_PrevAndNext(
		Session session, Ticket ticket, String department,
		OrderByComparator<Ticket> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TICKET_WHERE);

		boolean bindDepartment = false;

		if (department.isEmpty()) {
			sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_3);
		}
		else {
			bindDepartment = true;

			sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TicketModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindDepartment) {
			queryPos.add(department);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(ticket)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Ticket> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tickets where department = &#63; from the database.
	 *
	 * @param department the department
	 */
	@Override
	public void removeBydepartment(String department) {
		for (Ticket ticket :
				findBydepartment(
					department, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(ticket);
		}
	}

	/**
	 * Returns the number of tickets where department = &#63;.
	 *
	 * @param department the department
	 * @return the number of matching tickets
	 */
	@Override
	public int countBydepartment(String department) {
		department = Objects.toString(department, "");

		FinderPath finderPath = _finderPathCountBydepartment;

		Object[] finderArgs = new Object[] {department};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TICKET_WHERE);

			boolean bindDepartment = false;

			if (department.isEmpty()) {
				sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_3);
			}
			else {
				bindDepartment = true;

				sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDepartment) {
					queryPos.add(department);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_DEPARTMENT_DEPARTMENT_2 =
		"ticket.department = ?";

	private static final String _FINDER_COLUMN_DEPARTMENT_DEPARTMENT_3 =
		"(ticket.department IS NULL OR ticket.department = '')";

	public TicketPersistenceImpl() {
		setModelClass(Ticket.class);

		setModelImplClass(TicketImpl.class);
		setModelPKClass(long.class);

		setTable(TicketTable.INSTANCE);
	}

	/**
	 * Caches the ticket in the entity cache if it is enabled.
	 *
	 * @param ticket the ticket
	 */
	@Override
	public void cacheResult(Ticket ticket) {
		entityCache.putResult(TicketImpl.class, ticket.getPrimaryKey(), ticket);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the tickets in the entity cache if it is enabled.
	 *
	 * @param tickets the tickets
	 */
	@Override
	public void cacheResult(List<Ticket> tickets) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (tickets.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Ticket ticket : tickets) {
			if (entityCache.getResult(
					TicketImpl.class, ticket.getPrimaryKey()) == null) {

				cacheResult(ticket);
			}
		}
	}

	/**
	 * Clears the cache for all tickets.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TicketImpl.class);

		finderCache.clearCache(TicketImpl.class);
	}

	/**
	 * Clears the cache for the ticket.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Ticket ticket) {
		entityCache.removeResult(TicketImpl.class, ticket);
	}

	@Override
	public void clearCache(List<Ticket> tickets) {
		for (Ticket ticket : tickets) {
			entityCache.removeResult(TicketImpl.class, ticket);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(TicketImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(TicketImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new ticket with the primary key. Does not add the ticket to the database.
	 *
	 * @param ticketId the primary key for the new ticket
	 * @return the new ticket
	 */
	@Override
	public Ticket create(long ticketId) {
		Ticket ticket = new TicketImpl();

		ticket.setNew(true);
		ticket.setPrimaryKey(ticketId);

		return ticket;
	}

	/**
	 * Removes the ticket with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ticketId the primary key of the ticket
	 * @return the ticket that was removed
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	@Override
	public Ticket remove(long ticketId) throws NoSuchTicketException {
		return remove((Serializable)ticketId);
	}

	/**
	 * Removes the ticket with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ticket
	 * @return the ticket that was removed
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	@Override
	public Ticket remove(Serializable primaryKey) throws NoSuchTicketException {
		Session session = null;

		try {
			session = openSession();

			Ticket ticket = (Ticket)session.get(TicketImpl.class, primaryKey);

			if (ticket == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTicketException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ticket);
		}
		catch (NoSuchTicketException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Ticket removeImpl(Ticket ticket) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ticket)) {
				ticket = (Ticket)session.get(
					TicketImpl.class, ticket.getPrimaryKeyObj());
			}

			if (ticket != null) {
				session.delete(ticket);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ticket != null) {
			clearCache(ticket);
		}

		return ticket;
	}

	@Override
	public Ticket updateImpl(Ticket ticket) {
		boolean isNew = ticket.isNew();

		if (!(ticket instanceof TicketModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ticket.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(ticket);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ticket proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Ticket implementation " +
					ticket.getClass());
		}

		TicketModelImpl ticketModelImpl = (TicketModelImpl)ticket;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(ticket);
			}
			else {
				ticket = (Ticket)session.merge(ticket);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(TicketImpl.class, ticketModelImpl, false, true);

		if (isNew) {
			ticket.setNew(false);
		}

		ticket.resetOriginalValues();

		return ticket;
	}

	/**
	 * Returns the ticket with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ticket
	 * @return the ticket
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	@Override
	public Ticket findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTicketException {

		Ticket ticket = fetchByPrimaryKey(primaryKey);

		if (ticket == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTicketException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ticket;
	}

	/**
	 * Returns the ticket with the primary key or throws a <code>NoSuchTicketException</code> if it could not be found.
	 *
	 * @param ticketId the primary key of the ticket
	 * @return the ticket
	 * @throws NoSuchTicketException if a ticket with the primary key could not be found
	 */
	@Override
	public Ticket findByPrimaryKey(long ticketId) throws NoSuchTicketException {
		return findByPrimaryKey((Serializable)ticketId);
	}

	/**
	 * Returns the ticket with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ticketId the primary key of the ticket
	 * @return the ticket, or <code>null</code> if a ticket with the primary key could not be found
	 */
	@Override
	public Ticket fetchByPrimaryKey(long ticketId) {
		return fetchByPrimaryKey((Serializable)ticketId);
	}

	/**
	 * Returns all the tickets.
	 *
	 * @return the tickets
	 */
	@Override
	public List<Ticket> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Ticket> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Ticket> findAll(
		int start, int end, OrderByComparator<Ticket> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<Ticket> findAll(
		int start, int end, OrderByComparator<Ticket> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Ticket> list = null;

		if (useFinderCache) {
			list = (List<Ticket>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TICKET);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TICKET;

				sql = sql.concat(TicketModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Ticket>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tickets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Ticket ticket : findAll()) {
			remove(ticket);
		}
	}

	/**
	 * Returns the number of tickets.
	 *
	 * @return the number of tickets
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TICKET);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "ticketId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TICKET;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TicketModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ticket persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByuserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByuserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_finderPathWithPaginationFindBydepartment = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBydepartment",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"department"}, true);

		_finderPathWithoutPaginationFindBydepartment = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBydepartment",
			new String[] {String.class.getName()}, new String[] {"department"},
			true);

		_finderPathCountBydepartment = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBydepartment",
			new String[] {String.class.getName()}, new String[] {"department"},
			false);

		_setTicketUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setTicketUtilPersistence(null);

		entityCache.removeCache(TicketImpl.class.getName());
	}

	private void _setTicketUtilPersistence(
		TicketPersistence ticketPersistence) {

		try {
			Field field = TicketUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, ticketPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = helpdeskPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = helpdeskPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = helpdeskPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_TICKET =
		"SELECT ticket FROM Ticket ticket";

	private static final String _SQL_SELECT_TICKET_WHERE =
		"SELECT ticket FROM Ticket ticket WHERE ";

	private static final String _SQL_COUNT_TICKET =
		"SELECT COUNT(ticket) FROM Ticket ticket";

	private static final String _SQL_COUNT_TICKET_WHERE =
		"SELECT COUNT(ticket) FROM Ticket ticket WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "ticket.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Ticket exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Ticket exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TicketPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}
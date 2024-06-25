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

package chat.service.service.persistence.impl;

import chat.service.exception.NoSuchChatMessageException;
import chat.service.model.ChatMessage;
import chat.service.model.ChatMessageTable;
import chat.service.model.impl.ChatMessageImpl;
import chat.service.model.impl.ChatMessageModelImpl;
import chat.service.service.persistence.ChatMessagePersistence;
import chat.service.service.persistence.ChatMessageUtil;
import chat.service.service.persistence.impl.constants.ticketPersistenceConstants;

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

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the chat message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ChatMessagePersistence.class)
public class ChatMessagePersistenceImpl
	extends BasePersistenceImpl<ChatMessage> implements ChatMessagePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ChatMessageUtil</code> to access the chat message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ChatMessageImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByticketId;
	private FinderPath _finderPathWithoutPaginationFindByticketId;
	private FinderPath _finderPathCountByticketId;

	/**
	 * Returns all the chat messages where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @return the matching chat messages
	 */
	@Override
	public List<ChatMessage> findByticketId(long ticketId) {
		return findByticketId(
			ticketId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the chat messages where ticketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatMessageModelImpl</code>.
	 * </p>
	 *
	 * @param ticketId the ticket ID
	 * @param start the lower bound of the range of chat messages
	 * @param end the upper bound of the range of chat messages (not inclusive)
	 * @return the range of matching chat messages
	 */
	@Override
	public List<ChatMessage> findByticketId(long ticketId, int start, int end) {
		return findByticketId(ticketId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the chat messages where ticketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatMessageModelImpl</code>.
	 * </p>
	 *
	 * @param ticketId the ticket ID
	 * @param start the lower bound of the range of chat messages
	 * @param end the upper bound of the range of chat messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching chat messages
	 */
	@Override
	public List<ChatMessage> findByticketId(
		long ticketId, int start, int end,
		OrderByComparator<ChatMessage> orderByComparator) {

		return findByticketId(ticketId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the chat messages where ticketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatMessageModelImpl</code>.
	 * </p>
	 *
	 * @param ticketId the ticket ID
	 * @param start the lower bound of the range of chat messages
	 * @param end the upper bound of the range of chat messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching chat messages
	 */
	@Override
	public List<ChatMessage> findByticketId(
		long ticketId, int start, int end,
		OrderByComparator<ChatMessage> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByticketId;
				finderArgs = new Object[] {ticketId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByticketId;
			finderArgs = new Object[] {ticketId, start, end, orderByComparator};
		}

		List<ChatMessage> list = null;

		if (useFinderCache) {
			list = (List<ChatMessage>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ChatMessage chatMessage : list) {
					if (ticketId != chatMessage.getTicketId()) {
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

			sb.append(_SQL_SELECT_CHATMESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_TICKETID_TICKETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ChatMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ticketId);

				list = (List<ChatMessage>)QueryUtil.list(
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
	 * Returns the first chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat message
	 * @throws NoSuchChatMessageException if a matching chat message could not be found
	 */
	@Override
	public ChatMessage findByticketId_First(
			long ticketId, OrderByComparator<ChatMessage> orderByComparator)
		throws NoSuchChatMessageException {

		ChatMessage chatMessage = fetchByticketId_First(
			ticketId, orderByComparator);

		if (chatMessage != null) {
			return chatMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ticketId=");
		sb.append(ticketId);

		sb.append("}");

		throw new NoSuchChatMessageException(sb.toString());
	}

	/**
	 * Returns the first chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat message, or <code>null</code> if a matching chat message could not be found
	 */
	@Override
	public ChatMessage fetchByticketId_First(
		long ticketId, OrderByComparator<ChatMessage> orderByComparator) {

		List<ChatMessage> list = findByticketId(
			ticketId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat message
	 * @throws NoSuchChatMessageException if a matching chat message could not be found
	 */
	@Override
	public ChatMessage findByticketId_Last(
			long ticketId, OrderByComparator<ChatMessage> orderByComparator)
		throws NoSuchChatMessageException {

		ChatMessage chatMessage = fetchByticketId_Last(
			ticketId, orderByComparator);

		if (chatMessage != null) {
			return chatMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("ticketId=");
		sb.append(ticketId);

		sb.append("}");

		throw new NoSuchChatMessageException(sb.toString());
	}

	/**
	 * Returns the last chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat message, or <code>null</code> if a matching chat message could not be found
	 */
	@Override
	public ChatMessage fetchByticketId_Last(
		long ticketId, OrderByComparator<ChatMessage> orderByComparator) {

		int count = countByticketId(ticketId);

		if (count == 0) {
			return null;
		}

		List<ChatMessage> list = findByticketId(
			ticketId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the chat messages before and after the current chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param messageId the primary key of the current chat message
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next chat message
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	@Override
	public ChatMessage[] findByticketId_PrevAndNext(
			long messageId, long ticketId,
			OrderByComparator<ChatMessage> orderByComparator)
		throws NoSuchChatMessageException {

		ChatMessage chatMessage = findByPrimaryKey(messageId);

		Session session = null;

		try {
			session = openSession();

			ChatMessage[] array = new ChatMessageImpl[3];

			array[0] = getByticketId_PrevAndNext(
				session, chatMessage, ticketId, orderByComparator, true);

			array[1] = chatMessage;

			array[2] = getByticketId_PrevAndNext(
				session, chatMessage, ticketId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ChatMessage getByticketId_PrevAndNext(
		Session session, ChatMessage chatMessage, long ticketId,
		OrderByComparator<ChatMessage> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_CHATMESSAGE_WHERE);

		sb.append(_FINDER_COLUMN_TICKETID_TICKETID_2);

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
			sb.append(ChatMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(ticketId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(chatMessage)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ChatMessage> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the chat messages where ticketId = &#63; from the database.
	 *
	 * @param ticketId the ticket ID
	 */
	@Override
	public void removeByticketId(long ticketId) {
		for (ChatMessage chatMessage :
				findByticketId(
					ticketId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(chatMessage);
		}
	}

	/**
	 * Returns the number of chat messages where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @return the number of matching chat messages
	 */
	@Override
	public int countByticketId(long ticketId) {
		FinderPath finderPath = _finderPathCountByticketId;

		Object[] finderArgs = new Object[] {ticketId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CHATMESSAGE_WHERE);

			sb.append(_FINDER_COLUMN_TICKETID_TICKETID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(ticketId);

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

	private static final String _FINDER_COLUMN_TICKETID_TICKETID_2 =
		"chatMessage.ticketId = ?";

	public ChatMessagePersistenceImpl() {
		setModelClass(ChatMessage.class);

		setModelImplClass(ChatMessageImpl.class);
		setModelPKClass(long.class);

		setTable(ChatMessageTable.INSTANCE);
	}

	/**
	 * Caches the chat message in the entity cache if it is enabled.
	 *
	 * @param chatMessage the chat message
	 */
	@Override
	public void cacheResult(ChatMessage chatMessage) {
		entityCache.putResult(
			ChatMessageImpl.class, chatMessage.getPrimaryKey(), chatMessage);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the chat messages in the entity cache if it is enabled.
	 *
	 * @param chatMessages the chat messages
	 */
	@Override
	public void cacheResult(List<ChatMessage> chatMessages) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (chatMessages.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ChatMessage chatMessage : chatMessages) {
			if (entityCache.getResult(
					ChatMessageImpl.class, chatMessage.getPrimaryKey()) ==
						null) {

				cacheResult(chatMessage);
			}
		}
	}

	/**
	 * Clears the cache for all chat messages.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ChatMessageImpl.class);

		finderCache.clearCache(ChatMessageImpl.class);
	}

	/**
	 * Clears the cache for the chat message.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChatMessage chatMessage) {
		entityCache.removeResult(ChatMessageImpl.class, chatMessage);
	}

	@Override
	public void clearCache(List<ChatMessage> chatMessages) {
		for (ChatMessage chatMessage : chatMessages) {
			entityCache.removeResult(ChatMessageImpl.class, chatMessage);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(ChatMessageImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ChatMessageImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new chat message with the primary key. Does not add the chat message to the database.
	 *
	 * @param messageId the primary key for the new chat message
	 * @return the new chat message
	 */
	@Override
	public ChatMessage create(long messageId) {
		ChatMessage chatMessage = new ChatMessageImpl();

		chatMessage.setNew(true);
		chatMessage.setPrimaryKey(messageId);

		return chatMessage;
	}

	/**
	 * Removes the chat message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message that was removed
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	@Override
	public ChatMessage remove(long messageId)
		throws NoSuchChatMessageException {

		return remove((Serializable)messageId);
	}

	/**
	 * Removes the chat message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the chat message
	 * @return the chat message that was removed
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	@Override
	public ChatMessage remove(Serializable primaryKey)
		throws NoSuchChatMessageException {

		Session session = null;

		try {
			session = openSession();

			ChatMessage chatMessage = (ChatMessage)session.get(
				ChatMessageImpl.class, primaryKey);

			if (chatMessage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChatMessageException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(chatMessage);
		}
		catch (NoSuchChatMessageException noSuchEntityException) {
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
	protected ChatMessage removeImpl(ChatMessage chatMessage) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chatMessage)) {
				chatMessage = (ChatMessage)session.get(
					ChatMessageImpl.class, chatMessage.getPrimaryKeyObj());
			}

			if (chatMessage != null) {
				session.delete(chatMessage);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (chatMessage != null) {
			clearCache(chatMessage);
		}

		return chatMessage;
	}

	@Override
	public ChatMessage updateImpl(ChatMessage chatMessage) {
		boolean isNew = chatMessage.isNew();

		if (!(chatMessage instanceof ChatMessageModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(chatMessage.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(chatMessage);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in chatMessage proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ChatMessage implementation " +
					chatMessage.getClass());
		}

		ChatMessageModelImpl chatMessageModelImpl =
			(ChatMessageModelImpl)chatMessage;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(chatMessage);
			}
			else {
				chatMessage = (ChatMessage)session.merge(chatMessage);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ChatMessageImpl.class, chatMessageModelImpl, false, true);

		if (isNew) {
			chatMessage.setNew(false);
		}

		chatMessage.resetOriginalValues();

		return chatMessage;
	}

	/**
	 * Returns the chat message with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the chat message
	 * @return the chat message
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	@Override
	public ChatMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChatMessageException {

		ChatMessage chatMessage = fetchByPrimaryKey(primaryKey);

		if (chatMessage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChatMessageException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return chatMessage;
	}

	/**
	 * Returns the chat message with the primary key or throws a <code>NoSuchChatMessageException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	@Override
	public ChatMessage findByPrimaryKey(long messageId)
		throws NoSuchChatMessageException {

		return findByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns the chat message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message, or <code>null</code> if a chat message with the primary key could not be found
	 */
	@Override
	public ChatMessage fetchByPrimaryKey(long messageId) {
		return fetchByPrimaryKey((Serializable)messageId);
	}

	/**
	 * Returns all the chat messages.
	 *
	 * @return the chat messages
	 */
	@Override
	public List<ChatMessage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the chat messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of chat messages
	 * @param end the upper bound of the range of chat messages (not inclusive)
	 * @return the range of chat messages
	 */
	@Override
	public List<ChatMessage> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the chat messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of chat messages
	 * @param end the upper bound of the range of chat messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of chat messages
	 */
	@Override
	public List<ChatMessage> findAll(
		int start, int end, OrderByComparator<ChatMessage> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the chat messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of chat messages
	 * @param end the upper bound of the range of chat messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of chat messages
	 */
	@Override
	public List<ChatMessage> findAll(
		int start, int end, OrderByComparator<ChatMessage> orderByComparator,
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

		List<ChatMessage> list = null;

		if (useFinderCache) {
			list = (List<ChatMessage>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CHATMESSAGE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CHATMESSAGE;

				sql = sql.concat(ChatMessageModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ChatMessage>)QueryUtil.list(
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
	 * Removes all the chat messages from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ChatMessage chatMessage : findAll()) {
			remove(chatMessage);
		}
	}

	/**
	 * Returns the number of chat messages.
	 *
	 * @return the number of chat messages
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CHATMESSAGE);

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
		return "messageId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CHATMESSAGE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ChatMessageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the chat message persistence.
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

		_finderPathWithPaginationFindByticketId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByticketId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"ticketId"}, true);

		_finderPathWithoutPaginationFindByticketId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByticketId",
			new String[] {Long.class.getName()}, new String[] {"ticketId"},
			true);

		_finderPathCountByticketId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByticketId",
			new String[] {Long.class.getName()}, new String[] {"ticketId"},
			false);

		_setChatMessageUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setChatMessageUtilPersistence(null);

		entityCache.removeCache(ChatMessageImpl.class.getName());
	}

	private void _setChatMessageUtilPersistence(
		ChatMessagePersistence chatMessagePersistence) {

		try {
			Field field = ChatMessageUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, chatMessagePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = ticketPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = ticketPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ticketPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CHATMESSAGE =
		"SELECT chatMessage FROM ChatMessage chatMessage";

	private static final String _SQL_SELECT_CHATMESSAGE_WHERE =
		"SELECT chatMessage FROM ChatMessage chatMessage WHERE ";

	private static final String _SQL_COUNT_CHATMESSAGE =
		"SELECT COUNT(chatMessage) FROM ChatMessage chatMessage";

	private static final String _SQL_COUNT_CHATMESSAGE_WHERE =
		"SELECT COUNT(chatMessage) FROM ChatMessage chatMessage WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "chatMessage.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ChatMessage exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ChatMessage exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ChatMessagePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}
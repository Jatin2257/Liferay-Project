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

package chat.service.service.persistence;

import chat.service.model.ChatMessage;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the chat message service. This utility wraps <code>chat.service.service.persistence.impl.ChatMessagePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ChatMessagePersistence
 * @generated
 */
public class ChatMessageUtil {

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
	public static void clearCache(ChatMessage chatMessage) {
		getPersistence().clearCache(chatMessage);
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
	public static Map<Serializable, ChatMessage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ChatMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChatMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChatMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ChatMessage> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ChatMessage update(ChatMessage chatMessage) {
		return getPersistence().update(chatMessage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ChatMessage update(
		ChatMessage chatMessage, ServiceContext serviceContext) {

		return getPersistence().update(chatMessage, serviceContext);
	}

	/**
	 * Returns all the chat messages where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @return the matching chat messages
	 */
	public static List<ChatMessage> findByticketId(long ticketId) {
		return getPersistence().findByticketId(ticketId);
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
	public static List<ChatMessage> findByticketId(
		long ticketId, int start, int end) {

		return getPersistence().findByticketId(ticketId, start, end);
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
	public static List<ChatMessage> findByticketId(
		long ticketId, int start, int end,
		OrderByComparator<ChatMessage> orderByComparator) {

		return getPersistence().findByticketId(
			ticketId, start, end, orderByComparator);
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
	public static List<ChatMessage> findByticketId(
		long ticketId, int start, int end,
		OrderByComparator<ChatMessage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByticketId(
			ticketId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat message
	 * @throws NoSuchChatMessageException if a matching chat message could not be found
	 */
	public static ChatMessage findByticketId_First(
			long ticketId, OrderByComparator<ChatMessage> orderByComparator)
		throws chat.service.exception.NoSuchChatMessageException {

		return getPersistence().findByticketId_First(
			ticketId, orderByComparator);
	}

	/**
	 * Returns the first chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat message, or <code>null</code> if a matching chat message could not be found
	 */
	public static ChatMessage fetchByticketId_First(
		long ticketId, OrderByComparator<ChatMessage> orderByComparator) {

		return getPersistence().fetchByticketId_First(
			ticketId, orderByComparator);
	}

	/**
	 * Returns the last chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat message
	 * @throws NoSuchChatMessageException if a matching chat message could not be found
	 */
	public static ChatMessage findByticketId_Last(
			long ticketId, OrderByComparator<ChatMessage> orderByComparator)
		throws chat.service.exception.NoSuchChatMessageException {

		return getPersistence().findByticketId_Last(
			ticketId, orderByComparator);
	}

	/**
	 * Returns the last chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat message, or <code>null</code> if a matching chat message could not be found
	 */
	public static ChatMessage fetchByticketId_Last(
		long ticketId, OrderByComparator<ChatMessage> orderByComparator) {

		return getPersistence().fetchByticketId_Last(
			ticketId, orderByComparator);
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
	public static ChatMessage[] findByticketId_PrevAndNext(
			long messageId, long ticketId,
			OrderByComparator<ChatMessage> orderByComparator)
		throws chat.service.exception.NoSuchChatMessageException {

		return getPersistence().findByticketId_PrevAndNext(
			messageId, ticketId, orderByComparator);
	}

	/**
	 * Removes all the chat messages where ticketId = &#63; from the database.
	 *
	 * @param ticketId the ticket ID
	 */
	public static void removeByticketId(long ticketId) {
		getPersistence().removeByticketId(ticketId);
	}

	/**
	 * Returns the number of chat messages where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @return the number of matching chat messages
	 */
	public static int countByticketId(long ticketId) {
		return getPersistence().countByticketId(ticketId);
	}

	/**
	 * Caches the chat message in the entity cache if it is enabled.
	 *
	 * @param chatMessage the chat message
	 */
	public static void cacheResult(ChatMessage chatMessage) {
		getPersistence().cacheResult(chatMessage);
	}

	/**
	 * Caches the chat messages in the entity cache if it is enabled.
	 *
	 * @param chatMessages the chat messages
	 */
	public static void cacheResult(List<ChatMessage> chatMessages) {
		getPersistence().cacheResult(chatMessages);
	}

	/**
	 * Creates a new chat message with the primary key. Does not add the chat message to the database.
	 *
	 * @param messageId the primary key for the new chat message
	 * @return the new chat message
	 */
	public static ChatMessage create(long messageId) {
		return getPersistence().create(messageId);
	}

	/**
	 * Removes the chat message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message that was removed
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	public static ChatMessage remove(long messageId)
		throws chat.service.exception.NoSuchChatMessageException {

		return getPersistence().remove(messageId);
	}

	public static ChatMessage updateImpl(ChatMessage chatMessage) {
		return getPersistence().updateImpl(chatMessage);
	}

	/**
	 * Returns the chat message with the primary key or throws a <code>NoSuchChatMessageException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	public static ChatMessage findByPrimaryKey(long messageId)
		throws chat.service.exception.NoSuchChatMessageException {

		return getPersistence().findByPrimaryKey(messageId);
	}

	/**
	 * Returns the chat message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message, or <code>null</code> if a chat message with the primary key could not be found
	 */
	public static ChatMessage fetchByPrimaryKey(long messageId) {
		return getPersistence().fetchByPrimaryKey(messageId);
	}

	/**
	 * Returns all the chat messages.
	 *
	 * @return the chat messages
	 */
	public static List<ChatMessage> findAll() {
		return getPersistence().findAll();
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
	public static List<ChatMessage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<ChatMessage> findAll(
		int start, int end, OrderByComparator<ChatMessage> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<ChatMessage> findAll(
		int start, int end, OrderByComparator<ChatMessage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the chat messages from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of chat messages.
	 *
	 * @return the number of chat messages
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ChatMessagePersistence getPersistence() {
		return _persistence;
	}

	private static volatile ChatMessagePersistence _persistence;

}
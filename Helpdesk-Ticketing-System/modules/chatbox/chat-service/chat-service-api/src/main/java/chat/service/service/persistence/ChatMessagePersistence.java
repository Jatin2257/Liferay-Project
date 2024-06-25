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

import chat.service.exception.NoSuchChatMessageException;
import chat.service.model.ChatMessage;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the chat message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ChatMessageUtil
 * @generated
 */
@ProviderType
public interface ChatMessagePersistence extends BasePersistence<ChatMessage> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChatMessageUtil} to access the chat message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the chat messages where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @return the matching chat messages
	 */
	public java.util.List<ChatMessage> findByticketId(long ticketId);

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
	public java.util.List<ChatMessage> findByticketId(
		long ticketId, int start, int end);

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
	public java.util.List<ChatMessage> findByticketId(
		long ticketId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatMessage>
			orderByComparator);

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
	public java.util.List<ChatMessage> findByticketId(
		long ticketId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatMessage>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat message
	 * @throws NoSuchChatMessageException if a matching chat message could not be found
	 */
	public ChatMessage findByticketId_First(
			long ticketId,
			com.liferay.portal.kernel.util.OrderByComparator<ChatMessage>
				orderByComparator)
		throws NoSuchChatMessageException;

	/**
	 * Returns the first chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat message, or <code>null</code> if a matching chat message could not be found
	 */
	public ChatMessage fetchByticketId_First(
		long ticketId,
		com.liferay.portal.kernel.util.OrderByComparator<ChatMessage>
			orderByComparator);

	/**
	 * Returns the last chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat message
	 * @throws NoSuchChatMessageException if a matching chat message could not be found
	 */
	public ChatMessage findByticketId_Last(
			long ticketId,
			com.liferay.portal.kernel.util.OrderByComparator<ChatMessage>
				orderByComparator)
		throws NoSuchChatMessageException;

	/**
	 * Returns the last chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat message, or <code>null</code> if a matching chat message could not be found
	 */
	public ChatMessage fetchByticketId_Last(
		long ticketId,
		com.liferay.portal.kernel.util.OrderByComparator<ChatMessage>
			orderByComparator);

	/**
	 * Returns the chat messages before and after the current chat message in the ordered set where ticketId = &#63;.
	 *
	 * @param messageId the primary key of the current chat message
	 * @param ticketId the ticket ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next chat message
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	public ChatMessage[] findByticketId_PrevAndNext(
			long messageId, long ticketId,
			com.liferay.portal.kernel.util.OrderByComparator<ChatMessage>
				orderByComparator)
		throws NoSuchChatMessageException;

	/**
	 * Removes all the chat messages where ticketId = &#63; from the database.
	 *
	 * @param ticketId the ticket ID
	 */
	public void removeByticketId(long ticketId);

	/**
	 * Returns the number of chat messages where ticketId = &#63;.
	 *
	 * @param ticketId the ticket ID
	 * @return the number of matching chat messages
	 */
	public int countByticketId(long ticketId);

	/**
	 * Caches the chat message in the entity cache if it is enabled.
	 *
	 * @param chatMessage the chat message
	 */
	public void cacheResult(ChatMessage chatMessage);

	/**
	 * Caches the chat messages in the entity cache if it is enabled.
	 *
	 * @param chatMessages the chat messages
	 */
	public void cacheResult(java.util.List<ChatMessage> chatMessages);

	/**
	 * Creates a new chat message with the primary key. Does not add the chat message to the database.
	 *
	 * @param messageId the primary key for the new chat message
	 * @return the new chat message
	 */
	public ChatMessage create(long messageId);

	/**
	 * Removes the chat message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message that was removed
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	public ChatMessage remove(long messageId) throws NoSuchChatMessageException;

	public ChatMessage updateImpl(ChatMessage chatMessage);

	/**
	 * Returns the chat message with the primary key or throws a <code>NoSuchChatMessageException</code> if it could not be found.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message
	 * @throws NoSuchChatMessageException if a chat message with the primary key could not be found
	 */
	public ChatMessage findByPrimaryKey(long messageId)
		throws NoSuchChatMessageException;

	/**
	 * Returns the chat message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message, or <code>null</code> if a chat message with the primary key could not be found
	 */
	public ChatMessage fetchByPrimaryKey(long messageId);

	/**
	 * Returns all the chat messages.
	 *
	 * @return the chat messages
	 */
	public java.util.List<ChatMessage> findAll();

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
	public java.util.List<ChatMessage> findAll(int start, int end);

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
	public java.util.List<ChatMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatMessage>
			orderByComparator);

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
	public java.util.List<ChatMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatMessage>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the chat messages from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of chat messages.
	 *
	 * @return the number of chat messages
	 */
	public int countAll();

}
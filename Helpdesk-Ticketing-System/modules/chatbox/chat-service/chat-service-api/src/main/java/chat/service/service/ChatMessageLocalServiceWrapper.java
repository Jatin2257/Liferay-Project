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

package chat.service.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChatMessageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ChatMessageLocalService
 * @generated
 */
public class ChatMessageLocalServiceWrapper
	implements ChatMessageLocalService,
			   ServiceWrapper<ChatMessageLocalService> {

	public ChatMessageLocalServiceWrapper() {
		this(null);
	}

	public ChatMessageLocalServiceWrapper(
		ChatMessageLocalService chatMessageLocalService) {

		_chatMessageLocalService = chatMessageLocalService;
	}

	/**
	 * Adds the chat message to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ChatMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param chatMessage the chat message
	 * @return the chat message that was added
	 */
	@Override
	public chat.service.model.ChatMessage addChatMessage(
		chat.service.model.ChatMessage chatMessage) {

		return _chatMessageLocalService.addChatMessage(chatMessage);
	}

	@Override
	public void addChatMessage(
		long ticketId, long userId, String message, String imageAttached) {

		_chatMessageLocalService.addChatMessage(
			ticketId, userId, message, imageAttached);
	}

	/**
	 * Creates a new chat message with the primary key. Does not add the chat message to the database.
	 *
	 * @param messageId the primary key for the new chat message
	 * @return the new chat message
	 */
	@Override
	public chat.service.model.ChatMessage createChatMessage(long messageId) {
		return _chatMessageLocalService.createChatMessage(messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _chatMessageLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the chat message from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ChatMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param chatMessage the chat message
	 * @return the chat message that was removed
	 */
	@Override
	public chat.service.model.ChatMessage deleteChatMessage(
		chat.service.model.ChatMessage chatMessage) {

		return _chatMessageLocalService.deleteChatMessage(chatMessage);
	}

	/**
	 * Deletes the chat message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ChatMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message that was removed
	 * @throws PortalException if a chat message with the primary key could not be found
	 */
	@Override
	public chat.service.model.ChatMessage deleteChatMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _chatMessageLocalService.deleteChatMessage(messageId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _chatMessageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _chatMessageLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _chatMessageLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _chatMessageLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _chatMessageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>chat.service.model.impl.ChatMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _chatMessageLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>chat.service.model.impl.ChatMessageModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _chatMessageLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _chatMessageLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _chatMessageLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public chat.service.model.ChatMessage fetchChatMessage(long messageId) {
		return _chatMessageLocalService.fetchChatMessage(messageId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _chatMessageLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the chat message with the primary key.
	 *
	 * @param messageId the primary key of the chat message
	 * @return the chat message
	 * @throws PortalException if a chat message with the primary key could not be found
	 */
	@Override
	public chat.service.model.ChatMessage getChatMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _chatMessageLocalService.getChatMessage(messageId);
	}

	/**
	 * Returns a range of all the chat messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>chat.service.model.impl.ChatMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of chat messages
	 * @param end the upper bound of the range of chat messages (not inclusive)
	 * @return the range of chat messages
	 */
	@Override
	public java.util.List<chat.service.model.ChatMessage> getChatMessages(
		int start, int end) {

		return _chatMessageLocalService.getChatMessages(start, end);
	}

	/**
	 * Returns the number of chat messages.
	 *
	 * @return the number of chat messages
	 */
	@Override
	public int getChatMessagesCount() {
		return _chatMessageLocalService.getChatMessagesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _chatMessageLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<chat.service.model.ChatMessage> getMessageByTicketId(
		long ticketId) {

		return _chatMessageLocalService.getMessageByTicketId(ticketId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _chatMessageLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _chatMessageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the chat message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ChatMessageLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param chatMessage the chat message
	 * @return the chat message that was updated
	 */
	@Override
	public chat.service.model.ChatMessage updateChatMessage(
		chat.service.model.ChatMessage chatMessage) {

		return _chatMessageLocalService.updateChatMessage(chatMessage);
	}

	@Override
	public ChatMessageLocalService getWrappedService() {
		return _chatMessageLocalService;
	}

	@Override
	public void setWrappedService(
		ChatMessageLocalService chatMessageLocalService) {

		_chatMessageLocalService = chatMessageLocalService;
	}

	private ChatMessageLocalService _chatMessageLocalService;

}
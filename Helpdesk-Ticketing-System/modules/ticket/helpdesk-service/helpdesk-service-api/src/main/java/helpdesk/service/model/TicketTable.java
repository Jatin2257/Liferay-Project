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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;helpdesk_Ticket&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Ticket
 * @generated
 */
public class TicketTable extends BaseTable<TicketTable> {

	public static final TicketTable INSTANCE = new TicketTable();

	public final Column<TicketTable, Long> ticketId = createColumn(
		"ticketId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<TicketTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> requester = createColumn(
		"requester", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> email = createColumn(
		"email", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> subject = createColumn(
		"subject", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> department = createColumn(
		"department", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> category = createColumn(
		"category", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> subcategory = createColumn(
		"subcategory", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> priority = createColumn(
		"priority", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> imageattached = createColumn(
		"imageattached", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> status = createColumn(
		"status", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<TicketTable, String> createdate = createColumn(
		"createdate", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private TicketTable() {
		super("helpdesk_Ticket", TicketTable::new);
	}

}
create table helpdesk_Ticket (
	ticketId LONG not null primary key,
	userId LONG,
	requester VARCHAR(75) null,
	email VARCHAR(75) null,
	subject VARCHAR(75) null,
	description VARCHAR(75) null,
	department VARCHAR(75) null,
	category VARCHAR(75) null,
	subcategory VARCHAR(75) null,
	priority VARCHAR(75) null,
	imageattached TEXT null,
	status VARCHAR(75) null,
	createdate VARCHAR(75) null
);
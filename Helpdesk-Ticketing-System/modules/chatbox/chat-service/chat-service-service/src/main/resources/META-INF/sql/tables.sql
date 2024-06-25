create table ticket_ChatMessage (
	messageId LONG not null primary key,
	userId LONG,
	ticketId LONG,
	message VARCHAR(75) null,
	timestamp DATE null,
	imageattached TEXT null
);
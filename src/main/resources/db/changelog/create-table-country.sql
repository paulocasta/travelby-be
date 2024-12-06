create table country (
	id int8 primary key generated always as identity,
	name varchar(100) not null,
	code varchar(5) not null,
	constraint uc_country unique(name, code)
);
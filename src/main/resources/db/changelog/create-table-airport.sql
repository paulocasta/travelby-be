create table airport(
	id int8 primary key generated always as identity,
	name varchar(100) not null,
	code varchar(5) not null,
	city_id int8 not null,
	constraint airport_city_fk foreign key (city_id) references airport(id),
	constraint uc_airport unique(name, code)
);

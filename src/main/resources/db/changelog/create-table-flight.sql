create table flight (
	id int8 primary key generated always as identity,
	origin int8 not null,
	destination int8 not null,
	airline_id int not null,
	cabin_class varchar(10) not null,
	constraint origin_city_fk foreign key (origin) references city(id),
	constraint destination_city_fk foreign key (origin) references city(id),
	constraint airline_city_fk foreign key (origin) references airline(id)
);
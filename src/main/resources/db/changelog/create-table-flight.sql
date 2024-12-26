create table flight (
	id int8 primary key generated always as identity,
	origin_id int8 not null,
	destination_id int8 not null,
	airline_id int not null,
	departure_airport_id int not null,
	arrival_airport_id int not null,
	cabin_class varchar(20) not null,
	constraint origin_city_fk foreign key (origin_id) references city(id),
	constraint destination_city_fk foreign key (destination_id) references city(id),
	constraint airline_city_fk foreign key (airline_id) references airline(id),
	constraint uc_flight unique(origin_id, destination_id, airline_id, departure_airport_id, arrival_airport_id , cabin_class)
);
create table city (
	id int8 primary key generated always as identity,
	name varchar(100) not null,
	code varchar(5) not null,
	country_id int8 not null,
	constraint uc_city unique(name, code),
    constraint city_country_fk foreign key (country_id) references country(id)
);
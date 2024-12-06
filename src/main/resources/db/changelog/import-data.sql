-- Inserts
insert into airline (name, code)
values
('American Airlines', 'AA'),
('Air Canada','AC'),
('Air France', 'AF'),
('Aerolíneas Argentinas', 'AR'),
('British Airways','BA');

--
insert into country (name, code)
values
('Argentina', 'ARG'),
('Brasil','BRA'),
('Canada', 'CAN'),
('France', 'FRA'),
('United Kingdom', 'GBR'),
('United States','USA');

--
insert into city (name, code, country_id)
values
('Buenos Aires','ARBUE', 1),
('Rosario','ARROS', 1),
('Brasilia', 'BRBSB', 2),
('Florianopolis','BRFLN',2),
('Ottawa', 'CAOTT', 3),
('Edmonton','CAEDM',3),
('Paris','FRPAR',4),
('Beauvais', 'FRBVA',4),
('London', 'CALOD',5),
('Manchester','GBMNC',5),
('New York', 'USNYC',6),
('San Francisco','USSFO',6);

--
insert into airport (name, code, city_id)
values
('Jorge Newbury Airport','ARAEP', 1),
('Ministro Pistarini Airport','AREZE', 1),
('Islas Malvinas International Airport ','ROS', 2),
('Juscelino Kubitschek International Airport ', 'BSB', 3),
('Hercílio Luz International Airport','FLN',4),
('Ottawa Francisco International Airport ', 'YOW', 5),
('Edmonton International Airport ','YEG',6),
('París-Charles de Gaulle Airport','CDG',7),
('Beauvais Tillé Airport', 'BVA',8),
('London Airport', 'LCY',9),
('Mánchester Airport','MAN',10),
('John F. Kennedy International Airport ', 'JFK',11),
('San Francisco International Airport ','SFO', 12);
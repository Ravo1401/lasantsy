CREATE TABLE Product(
id serial primary key,
name varchar(50),
station_id integer REFERENCES Station(station_id)
);
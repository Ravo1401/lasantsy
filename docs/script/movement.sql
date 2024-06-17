CREATE TABLE Movement(
id serial primary key,
value DOUBLE PRECISION,
type varchar(50),
mouvement_datetime timestamp,
station_id integer REFERENCES Station(station_id)
);
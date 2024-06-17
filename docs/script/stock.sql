CREATE TABLE Stock(
id serial primary key,
value DOUBLE PRECISION,
Stock_datetime timestamp,
station_id integer REFERENCES Station(station_id)
);
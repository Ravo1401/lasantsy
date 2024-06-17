CREATE DATABASE lasantsy;

\c lasantsy


CREATE TABLE Station(
station_id  serial primary key,
location varchar(50)
);

CREATE TABLE Movement_Stock(
id serial primary key,
quantity integer,
Stock_datetime timestamp,
type varchar(50),
product_id int REFERENCES Product(id)
);

CREATE TABLE Product_template (
    id serial primary key,
    name varchar(50),
    price DOUBLE PRECISION
);

CREATE TABLE Product(
    id serial primary key,
    id_product_template int REFERENCES Product_template(id),
    station_id int REFERENCES Station(station_id)
);

CREATE TABLE Product_stock(
    id serial primary key,
    id_movement_stock int
);
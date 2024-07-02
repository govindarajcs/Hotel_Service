create database hotel_inventory;

use hotel_inventory;

create table hotel (id integer primary key, name varchar(50), location varchar(50), phone_no varchar(15), email_id varchar(50), status enum('ACTIVE', 'CLOSED'));

create table room_inventory(id integer primary key, hotel_id integer, room_type enum ('SINGLE_BED_AC', 'SINGLE_BED_NO_AC', 'DOUBLE_BED_AC', 'DOUBLE_BED_NO_AC'), room_count integer, rent int, constraint hotel_name_constraint foreign key(hotel_id) references hotel(id));
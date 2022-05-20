use Booking;
go
drop table if exists TakeAway;
drop table if exists ReservationTable;
drop table if exists SalesLine;
drop table if exists Product;
drop table if exists ProductType;
drop table if exists _Order;
drop table if exists Reservation;
drop table if exists _Table;
drop table if exists Employee;
drop table if exists Customer;
drop table if exists _Address;
drop table if exists CityZipcode;

create table CityZipcode(
	zipcode varchar(12),
	city nvarchar(40),
	country nvarchar(40),
	constraint pk_CityZipcode_zipcode Primary Key (zipcode)
);
create table _Address(
	id int Identity(1,1),
	zipcode varchar(12),
	streetName nvarchar(40),
	houseNo varchar(4),
	constraint fk_Address_zipcode Foreign Key (zipcode) references CityZipcode(zipcode),
	constraint pk_Address_id Primary Key (id)
);
create table Employee(
	id int Identity(1,1),
	firstName nvarchar(30),
	lastName nvarchar(30),
	phoneNo char(8),
	email varchar(40),
	addressId int,
	_role varchar(30),
	salary decimal,
	cpr char(10),
	constraint pk_Employee_id Primary Key (id),
	constraint fk_Employee_addressId Foreign Key (addressId) references _Address(id)
);
create table Customer(
	id int Identity(1,1),
	firstName nvarchar(30),
	lastName nvarchar(30),
	phoneNo char(8),
	email varchar(40),
	addressId int,
	constraint pk_Customer_id Primary Key (id),
	constraint fk_Customer_addressId Foreign Key(addressId) references _Address(id)
);

create table Reservation(
	id int Identity(1,1),
	guestCount int,
	dateTimeslot dateTime,
	note nvarchar(200),
	customerId int,
	constraint pk_Reservation_id Primary Key (id),
	constraint fk_Reservation_customerId Foreign Key (customerId) references Customer(id)
);
create table _Table(
	id int Identity(1,1),
	tableNo int unique,
	seats int,
	constraint pk_Table_id Primary Key (id)
);
create table ReservationTable(
	reservationId int,
	tableId int,
	constraint pk_reservationTable Primary Key(reservationId, tableId),
	constraint fk_reservationId Foreign Key (reservationId) references Reservation(id),
	constraint fk_tableId Foreign Key (tableId) references _Table(id)
);

create table _Order(
	--id int Identity(1, 1) ???
	orderNo int Identity(10000, 1),
	totalPrice decimal,
	status bit not null,
	tableId int,
	employeeId int,
	reservationId int,
	constraint pk_orderNo Primary Key(orderNo),
	constraint fk_Order_tableId Foreign Key(tableId) references _Table(id),
	constraint fk_Order_employeeId Foreign Key(employeeId) references Employee(id),
	constraint fk_Order_reservationId Foreign Key(reservationId) references Reservation(id)
);

create table ProductType(
	id int Identity(1,1),
	name varchar(20) unique,
	constraint pk_ProductType_id Primary Key(id)
);
create table Product(
	id int Identity(1,1),
	name nvarchar(40) unique,
	price decimal,
	typeId int,
	constraint pk_Product_id Primary Key (id),
	constraint fk_Product_typeId Foreign Key (typeId) references ProductType(id)
);
create table OrderLineItem(
	orderNo int,
	productId int,
	quantity int,
	constraint pk_orderNoProductId Primary Key (orderNo, productId),
	constraint fk_SaleLine_orderNo Foreign Key (orderNo) references _Order(orderNo),
	constraint fk_SaleLine_productId Foreign Key (productId) references Product(id)
);
create table TakeAway(
	id int Identity(1,1),
	dateTimeSlot dateTime,
	isDelivery bit not null,
	orderNo int, --maybe change
	customerId int,
	employeeId int,
	constraint fk_TakeAway_orderNo Foreign Key(orderNo) references _Order(orderNo),
	constraint fk_TakeAway_customerId Foreign Key(customerId) references Customer(id), 
	constraint fk_TakeAway_employeeId Foreign Key(employeeId) references Employee(id)
);
go

use Master;
go
use Booking;
go

create table CityZipcode(
	zipcode varchar(12),
	city nvarchar(40),
	country nvarchar(40),
	constraint pk_zipcode Primary Key (zipcode)
);
create table _Address(
	id int Identity(1,1),
	zipcode varchar(12),
	streetName nvarchar(40),
	houseNo varchar(4),
	constraint fk_zipcode Foreign Key (zipcode) references CityZipcode(zipcode),
	constraint pk_id Primary Key (id)
);
create table Employee(
	id int Identity(1,1),
	firstName nvarchar(30),
	lastName nvarchar(30),
	phoneNo char(8),
	email varchar(40),
	_addressId int,
	_role varchar(30),
	salary decimal,
	cpr char(10),
	constraint pk_id Primary Key (id),
	constraint fk__addressId Foreign Key (_addressId) references _Address(id)
);
create table Customer(
	id int Identity(1,1),
	firstName nvarchar(30),
	lastName nvarchar(30),
	phoneNo char(8),
	email varchar(40),
	_addressId int,
	constraint pk_id Primary Key (id),
	constraint fk__addressId Foreign Key(_addressId) references _Address(id)
);
create table TakeAway(
	id int Identity(1,1),
	dateTimeSlot dateTime,
	isDelivery bit not null,
	orderNo int, --maybe change
	customerId int,
	employeeId int,
	constraint fk_orderNo Foreign Key(orderNo) references ,
	constraint fk_customerId Foreign Key(customerId) references Customer(id), 
	constraint fk_employeeId Foreign Key(employeeId) references Employee(id)
);
create table Reservation(
	id int Identity(1,1),
	guestCount int,
	dateTimeslot dateTime,
	note nvarchar(200),
	customerId int,
	constraint pk_id (id),
	constraint fk_id Foreign Key (customerId) references Customer(id)
);
create table ReservationTable(
	reservationId int,
	tableNo int,
	constraint pk_reservationTable Primary Key(reservationId, tableNo),
	constraint fk_reservationId Foreign Key (reservationId) references Reservation(id),
	constraint fk_tableNo Foreign Key (tableId) references Table(id)
);
create table _Table(
	id int Identity(1,1)
	tableNo int,
	seats int,
	constraint pk_id Primary Key (id)
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
	constraint fk_tableId Foreign Key(tableId) references _Table(id),
	constraint fk_employeeId Foreign Key(employeeId) references Employee(id),
	constraint fk_reservationId Foreign Key(reservationId) references Reservation(id)
);
create table SalesLine(
	orderNo int,
	productId int,
	quantity int,
	constraint pk_orderNoProductId Primary Key (orderNo, productId),
	constraint fk_orderNo Foreign Key (orderNo) references _Order(orderNo),
	constraint fk_productId Foreign Key (productId) references Product(id)
);
create table Product(
	id int Identity(1,1),
	productNo varchar(3),
	name nvarchar(40),
	price decimal,
	typeId int,
	constraint pk_id Primary Key (id),
	constraint fk_typeId Foreign Key (typeId) references ProductType(id)
);
create table ProductType(
	id int Identity(1,1),
	name varchar(20)
	constraint pk_id Primary Key(id)
);
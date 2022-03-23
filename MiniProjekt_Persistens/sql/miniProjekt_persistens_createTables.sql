use [DMA-CSD-S212_10436664]
go
drop table SaleLineItem;
drop table SaleOrder;
drop table Product;
drop table Supplier;
drop table Customer;
drop table Employee;
drop table Person;
drop table _Address;
drop table CityZipcode;
go

create table CityZipcode(
	zipcode varchar(12),
	city nvarchar(40),
	country nvarchar(30),
	constraint pk_CityZipcode primary key (zipcode)
);
create table _Address(
	id int Identity(1,1),
	zipcode varchar(12),
	streetName nvarchar(40),
	houseNumber nvarchar(40),
	constraint pk_Address primary key (id),
	constraint fk_AddressZipcode foreign key (zipcode) references CityZipcode(zipcode)
);
create table Person(
	id int Identity(1,1),
	fname nvarchar(20),
	lname nvarchar(20),
	phoneNo varchar(8),
	email nvarchar(50),
	personType int not null,
	addressId int unique,
	constraint pk_Person primary key (id),
	constraint fk_address foreign key (addressId) references _Address(id)
);

create table Employee(
	personId int,
	salary decimal,
	constraint pk_Employee primary key (personId),
	constraint fk_Employee_personId foreign key (personId) references Person(id)
);
create table Customer(
	personId int,
	isClub bit not null,
	constraint pk_Customer primary key (personId),
	constraint fk_Customer_personId foreign key (personId) references Person(id)
);
create table Supplier(
	id int Identity(1,1),
	name nvarchar(40),
	addressId int,
	phoneNo varchar(20),
	email nvarchar(40),
	constraint pk_Supplier primary key (id),
	constraint fk_addressId foreign key (addressId) references _Address(id)
);
create table Product(
	id int Identity(1,1),
	supplierId int,
	name nvarchar(40),
	productNo varchar(10),
	description nvarchar(100),
	purchasePrice decimal,
	salesPrice decimal,
	rentPrice decimal,
	countryOfOrigin nvarchar(40),
	stock int,
	minStock int,
	size varchar(6),
	colour varchar(15),
	calibre varchar(20),
	materiel varchar(20),
	type varchar(20),
	constraint pk_Product primary key (id),
	constraint fk_supplierId foreign key (supplierId) references Supplier(id)
);
create table SaleOrder(
	id int Identity(1,1),
	orderNo varchar(10),
	_date datetime,
	deliveryStatus bit not null,
	deliveryDate datetime,
	employeeId int,
	customerId int,
	constraint pk_SaleOrder primary key (id),
	constraint fk_employeeId foreign key (employeeId) references Employee(personId),
	constraint fk_customerId foreign key (customerId) references Customer(personId)
);
create table SaleLineItem(
	id int Identity(1,1),
	saleOrderId int not null,
	productId int not null,
	amount int not null,
	constraint pk_SaleLineItem primary key (id),
	constraint fk_saleOrderId foreign key (saleOrderId) references SaleOrder(id),
	constraint fk_productId   foreign key (productId) references Product(id),
	constraint amount_notNegative check(amount > 0)
);

go
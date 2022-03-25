use [DMA-CSD-S212_10436664];
go

--CityZipcode dummydata
insert into CityZipcode (zipcode, city, country)
values ('8850', 'Bjerringbro', 'Denmark');
insert into CityZipcode (zipcode, city, country)
values ('9220', 'Aalborg Øst', 'Denmark');
insert into CityZipcode (zipcode, city, country)
values ('8800', 'Viborg', 'Denmark');
insert into CityZipcode (zipcode, city, country)
values ('77070', 'Houston', 'USA');
insert into CityZipcode (zipcode, city, country)
values ('22111', 'Hamburg', 'Germany');

--_Address dummydata
insert into _Address (zipcode, streetName, houseNumber)
values ('8850', 'Møllevej', '45');
insert into _Address (zipcode, streetName, houseNumber)
values ('8850', 'Møllevej', '10b');
insert into _Address (zipcode, streetName, houseNumber)
values ('9220', 'Gjellerupstien', '5');
insert into _Address (zipcode, streetName, houseNumber)
values ('9220', 'Tornhøjvej', '2');
insert into _Address (zipcode, streetName, houseNumber)
values ('8800', 'Sct. Mathias Gade', '22');
insert into _Address (zipcode, streetName, houseNumber)
values ('77070', '77th Street', '5150');
insert into _Address (zipcode, streetName, houseNumber)
values ('77070', '78th Street', '1204');
insert into _Address (zipcode, streetName, houseNumber)
values ('22111', 'Hauptsstrase', '60');

--Person dummydata
insert into Person(fname, lname, phoneNo, email, personType, addressId)
values ('Mike', 'Wazowski', '88885555', 'mikewazoski@mail.dk', 1, 3);
insert into Person(fname, lname, phoneNo, email, personType, addressId)
values ('Fru', 'Wazowski', '88886666', 'fruwazoski@mail.dk', 1, 1);
insert into Person(fname, lname, phoneNo, email, personType, addressId)
values ('Nina', 'Munkholm', '12345678', 'munkholm@mail.dk', 2, 4);
insert into Person(fname, lname, phoneNo, email, personType, addressId)
values ('Kent', 'Andersen', '98765432', 'k_andersen@mail.dk', 2, 2);

--Customer dummydata
insert into Customer(personId, isClub)
values (1, 0);
insert into Customer(personId, isClub)
values (2, 0);

--Employee dummydata
insert into Employee(personId, salary)
values (3, 20000);
insert into Employee(personId, salary)
values (4, 30000);

--Supplier dummydata
insert into Supplier(name, addressId, phoneNo, email)
values ('Cowboy Stopshop', 6, '881902938', 'cowboystopstop@mail.com');
insert into Supplier(name, addressId, phoneNo, email)
values ('Gun Replicas A/S', 7, '192949922', 'weloveguns@mail.com');

--Product dummydata
insert into Product
(supplierId, name, productNo, description, purchasePrice, salesPrice, rentPrice, 
countryOfOrigin, stock, minStock, size, colour, calibre, materiel, type)
values 
(1, 'Cowboy Hat', '0001', 'En cowboy hat fra det vilde vesten', 100, 120, 20, 'USA', 20, 5, 'XL', 'Black', null, null, null);
insert into Product
(supplierId, name, productNo, description, purchasePrice, salesPrice, rentPrice, 
countryOfOrigin, stock, minStock, size, colour, calibre, materiel, type)
values 
(2, 'Desert Eagle', '0002', 'En desert eagle pistol', 500, 550, 100, 'USA', 5, 1, null, null, '50mm', 'Aluminium', null);
insert into Product
(supplierId, name, productNo, description, purchasePrice, salesPrice, rentPrice, 
countryOfOrigin, stock, minStock, size, colour, calibre, materiel, type)
values 
(2, 'Slangebøsse', '0050', 'Stenskyderen. Brugt af hr. Kevin VU', 250, 280, 30, 'Danmark', 15, 3, null, null, 'Rocks', 'Wood', null);

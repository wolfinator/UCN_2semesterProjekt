use [DMA-CSD-S212_10436664];
go

insert into _Table (tableNo, seats) values (1, 2);
insert into _Table (tableNo, seats) values (2, 2);
insert into _Table (tableNo, seats) values (3, 2);
insert into _Table (tableNo, seats) values (4, 2);
insert into _Table (tableNo, seats) values (5, 2);
insert into _Table (tableNo, seats) values (6, 2);
insert into _Table (tableNo, seats) values (7, 2);
insert into _Table (tableNo, seats) values (8, 2);
insert into _Table (tableNo, seats) values (9, 2);
insert into _Table (tableNo, seats) values (10, 2);
insert into _Table (tableNo, seats) values (11, 4);
insert into _Table (tableNo, seats) values (12, 4);
insert into _Table (tableNo, seats) values (13, 4);
insert into _Table (tableNo, seats) values (14, 4);
insert into _Table (tableNo, seats) values (15, 4);

insert into ProductType (name) values ('Forret'); -- 1
insert into ProductType (name) values ('Hovedret'); -- 2
insert into ProductType (name) values ('Drikkevare'); -- 3

insert into Product (name, price, typeId) values (N'Gỏi Cuốn Xá Xíu', 55, 1);
insert into Product (name, price, typeId) values (N'Gỏi Cuốn Heo Quay', 55, 1);
insert into Product (name, price, typeId) values (N'Gỏi Cuốn Gà', 55, 1);

insert into Product (name, price, typeId) values (N'Phở Bò', 85, 2);
insert into Product (name, price, typeId) values (N'Mì Quảng Gà Trứng Cút', 85, 2);
insert into Product (name, price, typeId) values (N'Bánh Hỏi Heo Quay', 89, 2);

insert into Product (name, price, typeId) values (N'Ramlösa uden brus', 20, 3);
insert into Product (name, price, typeId) values (N'Hjemmelavet lemonade', 39, 3);
insert into Product (name, price, typeId) values (N'Bia saigon, vietnamesisk øl ', 49, 3);
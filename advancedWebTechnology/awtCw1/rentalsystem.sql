privatecustomercreate database rentalSystem;
use rentalSystem;

drop table if exists privateCustomer;
create table privateCustomer(
customerId varchar(10) not null,
customerName varchar(100) not null,
customerCardNo varchar(20) ,
primary key (customerId)
);

insert into privatecustomer ( customerId, customerName, customerCardNo)
VALUES ( 1001, "James", 100001 );
insert into privatecustomer ( customerId, customerName, customerCardNo)
VALUES ( 1002, "WestBook", 100002 );
insert into privatecustomer ( customerId, customerName, customerCardNo)
VALUES ( 1003, "Oven", 100003 );
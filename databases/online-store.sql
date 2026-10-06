drop database if exists Online_store;
create database Online_store;
use Online_store;

create table Categories(
cat_id int  not null primary key ,
cat_name varchar(50)
);

create table Products(
pro_id int not null primary key,
pro_name varchar(50),
price int not null,
cat_id int ,
check(price>0),
foreign key (cat_id) references Categories(cat_id)on update cascade on delete cascade 
);

create table Customers(
cust_id int not null,
first_name varchar(50),
last_name varchar(50),
email varchar(20) unique,
city varchar(20) default 'gaza	',
primary key(cust_id)
);

create table Orders(
order_id int not null,
order_date date,
cust_id int not null,
primary key(order_id),
foreign key (cust_id) references Customers(cust_id)on update cascade on delete cascade
);

create table Order_Items(
order_id int not null,
pro_id int not null,
quantity int,
unit_price int,
primary key (order_id, pro_id),
foreign key (order_id) references Orders(order_id)on update cascade on delete cascade,
foreign key (pro_id) references Products(pro_id)on update cascade on delete cascade
);
alter table Products add discount int;

insert into Categories (cat_id,cat_name)
values (1,'phone'),
	(2,'electronics'),
(3,'books'),
(4,'clothes'),
(5,'shoes');

insert into Products (pro_id,pro_name,price,cat_id)
values (1,'samsung',1500,1),
	(2,'laptop',1100,2),
(3,'shirt',50,4),
(4,'story book',20,3),
(5,'nike',150,5); 

insert into Customers (cust_id,first_name,last_name,email)
values (1,'ali','ahmad','ali@gmail.com'),
	 (2,'sara','omer','sara@gmail.com'),
 (3,'omer','ahmad','omer@gmail.com'),
 (4,'alaa','selim','alaa@gmail.com'),
 (5,'selim','mohammad','selim@gmail.com'); 
 
 insert into Orders (order_id,order_date,cust_id)
values (1,'2025-05-10',5),
  (2,'2025-07-20',3),
 (3,'2025-08-25',4),
 (4,'2025-09-20',1),
 (5,'2025-11-25',2);
 
 
 insert into Order_Items (order_id,pro_id,quantity,unit_price)
values (1,5,1,150),
	 (2,3,2,50),
 (3,1,1,1500),
 (4,2,2,1100),
 (5,4,3,20); 

update Products set price=1.10*price where cat_id=5 ;
update Products set discount=20 where cat_id=1 and price>150 ;
delete from Orders where order_date <'2025-07-20';
delete from Categories where cat_id=4;

select concat_ws(' ',first_name,last_name) as 'Full Name' from Customers;
select * from Products where (pro_name like 'S%' or pro_name like 'A%') or price > 100;
select upper(first_name) as ' name with upper case' from Customers;
select * from Products order by price desc;

select*from Orders;
select count(order_id) from Orders;
select avg(price) from Products;
select min(price) from Products;
select max(price) from Products;
select cat_id,count(*) from Products group by cat_id;
select order_id ,sum(quantity) from  Order_Items group by order_id;

select Categories.cat_name ,sum(Products.price) from  Categories join Products on Categories.cat_id=Products.cat_id group by Categories.cat_name;
select pro_id, sum(quantity) from Order_Items group by pro_id having sum(quantity)>10;
select Customers.first_name,Orders.order_id from Customers inner join Orders on Customers.cust_id=Orders.cust_id;
select Customers.first_name,Orders.order_id from Customers left join Orders on Customers.cust_id=Orders.cust_id;
select Categories.cat_id,Products.pro_name from Products right join Categories on Products.cat_id=Categories.cat_id;
select Customers.first_name,Categories.cat_name from Customers join Orders on Customers.cust_id=Orders.cust_id
 join Order_Items on Orders.order_id=Order_Items.order_id
 join Products on Order_Items.pro_id=Products.pro_id
 join Categories on Products.cat_id=Categories.cat_id;
 select pro_name as 'product 'from Products union  select cat_name as 'category 'from Categories















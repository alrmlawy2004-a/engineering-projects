drop database if exists librar_management;
create database  librar_management;
use  librar_management;
create table categories(
cat_id int primary key auto_increment,
cat_name varchar(100) not null
); 

create table books(
book_id int primary key,
book_title varchar(50),
author varchar(20),
c_id int
);

create table members(
mem_id int primary key,
mem_name varchar(50),
age int
);

create table borrowings(
borrow_date datetime default now(),
book_id int,
mem_id int,
primary key (borrow_date, book_id, mem_id),
foreign key (book_id) references books(book_id)on update cascade on delete cascade,
foreign key (mem_id) references members(mem_id)on update cascade on delete cascade

);

alter table books add categories_id int ,
          add constraint fk_categories_books foreign key(c_id) references categories(cat_id);	
          
          insert into categories(cat_name)
          value('novel'),
          ('story'),
          ('imaginary');
          
          insert into books(book_id,book_title,author,c_id)
          value(1,'fear','osama al musli',1),
          (2,'leila and the wolf','alaa',2),
          (3,'alwalmiya','osama al musli',1);
          
           insert into members(mem_id,mem_name,age )
          value(1,'Ahmed saim',17),
          (2,'Deyaa sami',18),
          (3,'Baree Eyadc',18);
          
           insert into borrowings(book_id,mem_id )
          value(1,2),
          (2,1),
          (3,2);
          
          update books set author='Baree Eyadc' where book_id= 2 ;
          update members set age=age+2;
          delete from members where  mem_id%2=0;
          delete from categories where cat_id=1;
          
          
          
          select*from books;
          select*from categories;
          select*from members;
          select*from books;
         
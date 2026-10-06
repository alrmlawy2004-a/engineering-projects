drop database if exists students;
create database students;
use students;
create table students(
id_student int primary key auto_increment,
first_name varchar(50) not null,
last_name varchar(50),
enrollment_status varchar(10) default ('Active'),
birth_date date 
);
alter table students add column university_number varchar(15);
alter table students modify last_name varchar(30);
insert into students (first_name,last_name,birth_date) values('Fadi','Zaki',10810);
insert into students (first_name,university_number) values
                                          ('Lina','56789U'),
                                          ('Samer','12345U');

select*from students;

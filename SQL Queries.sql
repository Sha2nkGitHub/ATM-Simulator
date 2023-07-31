/* Bank Management System */

create database bankmanagementsystem;
use bankmanagementsystem;
create table signup(formno varchar(20), name varchar(50), father_name varchar(50), dob varchar(20), gender varchar(20), email varchar(50), marital_status varchar(20), address varchar(200), city varchar(40), state varchar(50), pincode varchar(20));
create table signuptwo(formno varchar(20), religion varchar(20), category varchar(20), income varchar(20), education varchar(20), occupation varchar(20), aadhar varchar(20), pan varchar(20), seniorcitizen varchar(20), existingaccount varchar(20));
create table signupthree(formno varchar(20), accountype varchar(200), cardumber varchar(25), pin varchar(10), facility varchar(300));
create table login(formno varchar(20), cardnumber varchar(25), pin varchar(10));
create table bank(pin varchar(10), date varchar(50), type varchar(50), amount varchar(20));

show tables;
select * from signupthree;
select * from login;
select * from signuptwo;
select * from bank;
select * from signup;









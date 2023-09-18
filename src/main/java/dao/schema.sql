/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  haydenaish
 * Created: 21 Aug 2023
 */

drop table if exists Customer;
drop table if exists Product;

create table if not exists Customer (
    customerId INT auto_increment Primary key,
    username varchar(50) not null,
    firstName varchar(50) not null,
    surname varchar(50) not null,
    password varchar(50) not null,
    emailAddress varchar(50) not null,
    shippingAddress text not null
);

create table if not exists Product (
    productId varchar(50) primary key,
    name varchar(50) not null,
    description text,
    category varchar(50) not null,
    listPrice Decimal(10,2) not null,
    quantityInStock Decimal(10,2) not null
);
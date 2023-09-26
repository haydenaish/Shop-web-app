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
drop table if exists Sale;
drop table if exists SaleItem;

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

create table if not exists Sale(
    saleId INT auto_increment Primary key,
    date date not null,
    customer INT not null,
    status varchar(50) not null,
    constraint sale_fk foreign key (customer) references Customer (customerID)
);

create table if not exists SaleItem(
    product varchar(50),
    saleId INT,
    salePrice Decimal (10,2) not null,
    quantityPurchased Decimal (10,2) not null,
    constraint sale_item_fk foreign key (saleId) references Sale (saleId),
    constraint sale_item_fk1 foreign key (product) references Product (productId),
    constraint sale_item_pk primary key (product, saleID, quantityPurchased)
);

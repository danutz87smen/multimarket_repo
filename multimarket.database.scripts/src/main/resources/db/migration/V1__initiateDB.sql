 create table CATEGORY(
 	ID int primary key,
 	name varchar(200),
 	parent_category int REFERENCES CATEGORY(ID) 
 );
 
create table PRODUCT(
	ID int primary key,
    NAME varchar(100) not null,
    DESCRIPTION varchar(300),
    CATGORY_ID int REFERENCES CATEGORY(ID),
    STOCK int not null
 );
 
 create table FEATURE(
 	ID int primary key,
 	name varchar(200)
 );
 
 create table PRODUCT_FEATURE(
 	ID int primary key, 
 	product int REFERENCES PRODUCT(ID),
 	feature  int REFERENCES FEATURE(ID),
 	feature_value varchar(200)
 );
 
 create table ORDER_LIST (
 	ID int primary key,
 	account_id int,
 	order_date timestamp,
 	tvavalue int8,
 	total_price int8
);


 create table ORDER_ITEM (
 	ID int primary key,
 	product_id int references product(id),
 	quantity int,
 	tvavalue int8,
 	price int8
);


 create SEQUENCE product_SEQ start 1;
 create SEQUENCE feature_SEQ start 1;
 create SEQUENCE product_feature_SEQ start 1;
 create SEQUENCE category_SEQ start 1;
 create SEQUENCE order_SEQ start 1;
 create SEQUENCE order_item_SEQ start 1;
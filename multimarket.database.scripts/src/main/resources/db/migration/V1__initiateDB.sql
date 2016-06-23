 create table CATEGORY(
 	ID int8 primary key,
 	name varchar(200),
 	parent_category int8 REFERENCES CATEGORY(ID) 
 );
 
create table PRODUCT(
	ID int8 primary key,
    NAME varchar(100) not null,
    DESCRIPTION varchar(300),
    CATGORY_ID int8 REFERENCES CATEGORY(ID),
    STOCK int not null,
    PRICE int8,
    PHOTOS varchar(300)
 );
 
 create table FEATURE(
 	ID int8 primary key,
 	name varchar(200)
 );
 
 create table PRODUCT_FEATURE(
 	ID int8 primary key, 
 	product int8 REFERENCES PRODUCT(ID),
 	feature  int8 REFERENCES FEATURE(ID),
 	feature_value varchar(200)
 );

 create SEQUENCE product_SEQ start 1;
 create SEQUENCE feature_SEQ start 1;
 create SEQUENCE product_feature_SEQ start 1;
 create SEQUENCE category_SEQ start 1;

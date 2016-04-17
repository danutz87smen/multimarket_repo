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
 
 create SEQUENCE product_SEQ start 1;
 create SEQUENCE feature_SEQ start 1;
 create SEQUENCE product_feature_SEQ start 1;
 create SEQUENCE category_SEQ start 1;
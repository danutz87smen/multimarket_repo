create table if not exists CATEGORY(
 	ID int primary key,
 	name varchar(200),
 	parent_category int REFERENCES CATEGORY(ID) 
 );
 
create table if not exists PRODUCT(
	ID int primary key,
    NAME varchar(100) not null,
    DESCRIPTION varchar(300),
    CATGORY_ID int REFERENCES CATEGORY(ID),
    STOCK int not null
 );
 
 create table if not exists FEATURE(
 	ID int primary key,
 	name varchar(200)
 );
 
 create table if not exists PRODUCT_FEATURE(
 	ID int primary key, 
 	product int REFERENCES PRODUCT(ID),
 	feature  int REFERENCES FEATURE(ID),
 	feature_value varchar(200)
 );
 
 create SEQUENCE if not exists  product_SEQ start 1;
 create SEQUENCE if not exists  feature_SEQ start 1;
 create SEQUENCE if not exists  product_feature_SEQ start 1;
 create SEQUENCE if not exists  category_SEQ start 1;
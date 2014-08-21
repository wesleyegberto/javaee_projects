CREATE SCHEMA TOYSHOP_DB;

USE TOYSHOP_DB;

/* Tables to Realm */

CREATE TABLE USER
(
	username VARCHAR(20) PRIMARY KEY,
	password VARCHAR(32) NOT NULL,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE ROLE
(
	rolename VARCHAR(20) PRIMARY KEY
);

CREATE TABLE USER_ACL
(
	username VARCHAR(20) NOT NULL REFERENCES USER(username),
	rolename VARCHAR(20) NOT NULL REFERENCES ROLE(rolename),
	PRIMARY KEY(username, rolename)
);

/*
-- Inserts for Users and Roles --
The password was created using JBoss lib Picketbox 4.0.x: org.jboss.security.auth.spi.Util.Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, "123123")
Encrypted password Qpf0SxOVUjUkWySXOZ16kw== Is 123123
 */
insert into USER values ('wesley', 'Qpf0SxOVUjUkWySXOZ16kw==', 'Wesley Egberto'),('odair', 'Qpf0SxOVUjUkWySXOZ16kw==', 'Odair Jose');
insert into ROLE values ('Manager'),('Customer'),('Guest');
insert into USER_ACL values ('wesleyegberto', 'Manager'),('wesleyegberto','Customer'),('odair','Customer');



insert into PRODUCT (name, description, category, price, stockQnty, picture) values
('Hulk', 'Hulk from Avengeres movie', 'Movie',  299, 10, 'avengers-hulk.jpg'),
('Batman', 'Batman from Batman The Dark Knight movie', 'Movie', 260, 10, 'batman.jpg'),
('Iron Man', 'Iron Man from Iron Man movie', 'Movie', 320, 10, 'ironman.jpg'),
('Buzz Lightyear', 'Buzz Lightyear from Toy Story movie', 'Movie', 200, 10, 'toystory-buzzlightyear.jpg'),
('Wood', 'Wood from Toy Story movie', 'Movie', 210, 10, 'toystory-wood.jpg'),
('Wolverine', 'Wolverine from Wolverine Imortal movie', 'Movie', 340, 10, 'wolverine-imortal.jpg'),
('The Claw', 'The Claw from Toy Story movie', 'Movie',  129, 10, 'toystory-theclaw.jpg');



select * from PRODUCT;

select * from CUSTOMER;

select * from WEB_ORDER
inner join WEB_ORDER_PRODUCT
on id = WEB_ORDER_id;
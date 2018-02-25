USE DATABASE edesia;

CREATE TABLE users (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    description varchar(255),
    role varchar(255),
    position varchar(255),
    speciality varchar(255)
); 

CREATE TABLE command (
    id int NOT NULL AUTO_INCREMENT,
    from int,
    to int,
    status varchar(255)
); 

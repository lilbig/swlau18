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
    oreder_id int NOT NULL AUTO_INCREMENT,
    from_id int,
    to_id int,
    status varchar(255)
); 

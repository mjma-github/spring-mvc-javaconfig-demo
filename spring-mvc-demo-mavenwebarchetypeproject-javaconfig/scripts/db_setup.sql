CREATE DATABASE `hb_student_tracker`;
CREATE DATABASE `hb_student_security`;

CREATE USER 'hb_student'@'localhost' IDENTIFIED BY 'hb_student';
GRANT ALL ON hb_student_tracker.* TO 'hb_student'@'localhost';

CREATE USER 'hb_security'@'localhost' IDENTIFIED BY 'hb_security';
GRANT ALL ON hb_student_security.* TO 'hb_security'@'localhost';

USE hb_student_security;

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
);

CREATE TABLE `authorities` (
  `username` varchar(45) NOT NULL,
  `authority` varchar(45) NOT NULL
);

--App db objects to be created on hibernate startup

--Create initial admin user

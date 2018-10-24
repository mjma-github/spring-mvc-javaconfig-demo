CREATE DATABASE `hb_student_security` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `hb_student_security`.`authorities`
(`username`,
`authority`)
VALUES
(<{username: }>,
<{authority: }>);

'hanabi', 'ROLE_ADMIN'
'mj', 'ROLE_USER'

-----

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

'hanabi', '{bcrypt}$2a$10$T7LTWAj.kAtAnLtLt5ytQeBTnzBrYAdNdwIokrAddJBDU0qNF1nQe', '1'
'mj', '{bcrypt}$2a$10$2zwm07j9I0XnbsuRZLpUBeL1V66eG.3mgxoUmxC5fnJgs0Wtafdkm', '1'
'user01', '{bcrypt}$2a$10$sZVFLnQ3Elt.Hl8J7YZyL.EbyqSTGGDIcd3eD5ZqI/hZ0g6IpD5Te', '1'

///////////////////////////////////////////////////////////////////

CREATE DATABASE `hb_student_tracker` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TITLE_UNIQUE` (`title`),
  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
  CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=695 DEFAULT CHARSET=latin1;

INSERT INTO `hb_student_tracker`.`course`
(`id`,
`title`,
`instructor_id`)
VALUES
(<{id: }>,
<{title: }>,
<{instructor_id: }>);

689	The art of manure	510
690	Kachichas 101	510
691	The chowdy course	510
692	The Sempai guide	511
693	Surviving Shinji course	511
694	Mastering crest worms	511
-----

CREATE TABLE `course_student` (
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  PRIMARY KEY (`course_id`,`student_id`),
  KEY `FK_STUDENT1` (`student_id`),
  CONSTRAINT `FK_COURSE1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STUDENT1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `hb_student_tracker`.`course_student`
(`course_id`,
`student_id`)
VALUES
(<{course_id: }>,
<{student_id: }>);

694	67
689	68
694	68
-----

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `hb_student_tracker`.`employee`
(`id`,
`first_name`,
`last_name`,
`company`)
VALUES
(<{id: }>,
<{first_name: }>,
<{last_name: }>,
<{company: }>);
-----

CREATE TABLE `instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `instructor_detail_id` int(11) DEFAULT NULL,
  `course_summary_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`instructor_detail_id`),
  KEY `FK_COURSE_SUMMARY_idx` (`course_summary_id`),
  CONSTRAINT `FK_COURSE_SUMMARY_DETAIL` FOREIGN KEY (`course_summary_id`) REFERENCES `instructor_course_summary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=512 DEFAULT CHARSET=latin1;

INSERT INTO `hb_student_tracker`.`instructor`
(`id`,
`first_name`,
`last_name`,
`email`,
`instructor_detail_id`,
`course_summary_id`)
VALUES
(<{id: }>,
<{first_name: }>,
<{last_name: }>,
<{email: }>,
<{instructor_detail_id: }>,
<{course_summary_id: }>);

510	MJ	Alonso	mj@test.com	229	
511	Sakura	Matou	sakura@test.com	230	
					
-----

CREATE TABLE `instructor_course_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_authored_count` int(11) DEFAULT '0',
  `course_enrolled_count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `hb_student_tracker`.`instructor_course_summary`
(`id`,
`course_authored_count`,
`course_enrolled_count`)
VALUES
(<{id: }>,
<{course_authored_count: 0}>,
<{course_enrolled_count: 0}>);

-----

CREATE TABLE `instructor_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `youtube_url` varchar(128) DEFAULT NULL,
  `hobbies` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=latin1;

INSERT INTO `hb_student_tracker`.`instructor_detail`
(`id`,
`youtube_url`,
`hobbies`)
VALUES
(<{id: }>,
<{youtube_url: }>,
<{hobbies: }>);

229	tube-u.com	gunpla
230	youtube.com/bestgirl	cookin		

-----

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `review` varchar(256) NOT NULL,
  `review_date` date NOT NULL,
  `reviewer` varchar(50) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_COURSE_idx` (`course_id`),
  CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `hb_student_tracker`.`review`
(`id`,
`review`,
`review_date`,
`reviewer`,
`course_id`)
VALUES
(<{id: }>,
<{review: }>,
<{review_date: }>,
<{reviewer: }>,
<{course_id: }>);

-----

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;

INSERT INTO `hb_student_tracker`.`student`
(`id`,
`first_name`,
`last_name`,
`date_of_birth`,
`email`)
VALUES
(<{id: }>,
<{first_name: }>,
<{last_name: }>,
<{date_of_birth: }>,
<{email: }>);

67	Shirou	Emiya	2018-03-20 11:33:32	shirou@test.com
68	Artoria	Pendragon	2018-03-19 00:00:00	saber-san@test.com
69	Rin	Tohsaka	2018-03-20 11:33:32	rin@test.com
70	sdfsdf	sdfsdfsd	2000-01-01 00:00:00	sdf@sdf
71	aaa	bbb	2000-01-01 00:00:00	aaa@bbb
73	222***	222***	1999-12-31 00:00:00	222@222
74	zzz	zzz	2000-01-01 00:00:00	zzz@zzz
				
-----




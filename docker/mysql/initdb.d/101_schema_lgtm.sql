CREATE TABLE `sample` (
  `id` int(3) NOT NULL,
  `family_name` varchar(20) NOT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
);
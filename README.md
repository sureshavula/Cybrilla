# Cybrilla
Project is about Implementing URL Shortener service via a REST API

DB Schema :
 CREATE TABLE `cybrilla` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `long_url` varchar(1000) NOT NULL,
  `short_url` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `long_url` (`long_url`,`short_url`)
);

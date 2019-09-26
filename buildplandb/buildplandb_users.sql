CREATE DATABASE  IF NOT EXISTS `buildplandb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `buildplandb`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: buildplandb
-- ------------------------------------------------------
-- Server version	5.6.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  `floor_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT '1',
  PRIMARY KEY (`idusers`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_dep_id_idx` (`department_id`),
  KEY `fk_pos_id_idx` (`position_id`),
  KEY `fk_f_id_idx` (`floor_id`),
  KEY `fk_r_id_idx` (`room_id`),
  CONSTRAINT `fk_dep_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`iddepartment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_f_id` FOREIGN KEY (`floor_id`) REFERENCES `floor` (`idfloor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pos_id` FOREIGN KEY (`position_id`) REFERENCES `position` (`idposition`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_r_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`idroom`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Elvin','Shahmuradov','e.sahmuradov@mail.ru','0515349925',1,1,4,25,1),(2,'Nicat','Necefov','nicat@mail.ru','0502000000',4,5,3,19,1),(3,'Jale','Quluzade','jale@mail.ru','0555555555',4,1,4,25,1),(4,'Etibar','Nadirov','etibar@mail.ru','0555111111',1,2,4,25,1),(5,'Haci','Aliyev','haci@mail.ru','0522222222',1,3,4,25,1),(6,'Seymur','Kerimov','seymur@mail.ru','0512121212',2,4,4,25,1),(7,'Kenan','Fetullayev','kenan@mail.ru','0512323232',1,1,4,25,1),(8,'Azer','Memmedov','azer@mail.ru','0555454545',1,2,4,25,1),(9,'Neriman','Nehmedov','neriman@mail.ru','0708888888',2,3,4,25,1),(10,'Sole','Zeynalova','sole@mail.ru','0709999999',3,1,4,26,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-26 15:37:59

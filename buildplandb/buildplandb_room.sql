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
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `idroom` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `max_person` int(11) DEFAULT NULL,
  `floor_id` int(11) DEFAULT NULL,
  `active` int(11) DEFAULT '1',
  PRIMARY KEY (`idroom`),
  KEY `fk_fl_id_idx` (`floor_id`),
  CONSTRAINT `fk_fl_id` FOREIGN KEY (`floor_id`) REFERENCES `floor` (`idfloor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'101','Work',6,1,1),(2,'102','Work',6,1,1),(3,'103','Work',6,1,1),(4,'104','Work',6,1,1),(5,'105','Work',6,1,1),(6,'106','Work',6,1,1),(7,'107','Work',6,1,1),(8,'108','Work',6,1,1),(9,'201','Work',6,2,1),(10,'202','Work',6,2,1),(11,'203','Work',6,2,1),(12,'204','Work',6,2,1),(13,'205','Work',6,2,1),(14,'206','Work',6,2,1),(15,'207','Work',6,2,1),(16,'208','Work',6,2,1),(17,'301','Work',6,3,1),(18,'302','Work',6,3,1),(19,'303','Work',6,3,1),(20,'304','Work',6,3,1),(21,'305','Work',6,3,1),(22,'306','Work',6,3,1),(23,'307','Work',6,3,1),(24,'308','Work',6,3,1),(25,'401','Work',6,4,1),(26,'402','Work',6,4,1),(27,'403','Work',6,4,1),(28,'404','Work',6,4,1),(29,'405','Work',6,4,1),(30,'406','Work',6,4,1),(31,'407','Work',6,4,1),(32,'408','Work',6,4,1),(33,'501','Work',6,5,1),(34,'502','Work',6,5,1),(35,'503','Work',6,5,1),(36,'504','Work',6,5,1),(37,'505','Work',6,5,1),(38,'506','Work',6,5,1),(39,'507','Work',6,5,1),(40,'508','Work',6,5,1),(41,'109 (Meeting room for 10 person)','Meeting',10,1,1),(42,'110 (Meeting room for 20 person)','Meeting',20,1,1),(43,'209 (Meeting room for 10 person)','Meeting',10,2,1),(44,'210 (Meeting room for 20 person)','Meeting',20,2,1),(45,'309 (Meeting room for 10 person)','Meeting',10,3,1),(46,'310 (Meeting room for 20 person)','Meeting',20,3,1),(47,'409 (Meeting room for 10 person)','Meeting',10,4,1),(48,'410 (Meeting room for 20 person)','Meeting',20,4,1),(49,'509 (Meeting room for 10 person)','Meeting',10,5,1),(50,'510 (Meeting room for 20 person)','Meeting',20,5,1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-26 15:38:02

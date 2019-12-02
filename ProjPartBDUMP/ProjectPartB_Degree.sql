-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ProjectPartB
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.18.04.4

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
-- Table structure for table `Degree`
--

DROP TABLE IF EXISTS `Degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Degree` (
  `Degree_name` varchar(10) NOT NULL,
  `Degree_descr` text,
  PRIMARY KEY (`Degree_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Degree`
--

LOCK TABLES `Degree` WRITE;
/*!40000 ALTER TABLE `Degree` DISABLE KEYS */;
INSERT INTO `Degree` VALUES ('AA-AS','Associate in Arts & Sciences (2 years)\r'),('AA-DTA','Direct Transfer Agreement (2 years)\r'),('AA-M','Associate in Mathematics (2 years)\r'),('BA-J','Bachelor of Arts: Journalism (4 years)\r'),('BS-BS','Bachelor of Science: Biosciences (4 years)\r'),('BS-CPTS','Bachelor of Science: Computer Science (4 years)\r'),('BS-DA','Bachelor of Science: Data Analytics (4 years)\r'),('BSH-I','Bachelor of SHIELD: Investigation (4 years)\r'),('BSH-SH','Bachelor of SHILED: Superhero (N/A)\r'),('DBA','Doctor of Buisness Administration (8 years)\r'),('MBA','Master of Buisness Administration (6 years)\r');
/*!40000 ALTER TABLE `Degree` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-02 12:57:29

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
-- Table structure for table `Degree_sought`
--

DROP TABLE IF EXISTS `Degree_sought`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Degree_sought` (
  `Ssn` char(9) NOT NULL,
  `Degree_name` varchar(10) NOT NULL,
  PRIMARY KEY (`Ssn`,`Degree_name`),
  KEY `Degree_name` (`Degree_name`),
  CONSTRAINT `Degree_sought_ibfk_1` FOREIGN KEY (`Ssn`) REFERENCES `Student` (`Ssn`),
  CONSTRAINT `Degree_sought_ibfk_2` FOREIGN KEY (`Degree_name`) REFERENCES `Degree` (`Degree_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Degree_sought`
--

LOCK TABLES `Degree_sought` WRITE;
/*!40000 ALTER TABLE `Degree_sought` DISABLE KEYS */;
INSERT INTO `Degree_sought` VALUES ('355640010','AA-DTA'),('689345153','BA-J'),('689354615','BA-J'),('689555542','BS-BS'),('689784138','BS-BS'),('368465135','BS-CPTS'),('389135481','BS-CPTS'),('435853516','BS-CPTS'),('488202454','BS-CPTS'),('664138974','BS-CPTS'),('435853516','BS-DA'),('689435588','BS-DA'),('216861534','BSH-I'),('654651384','BSH-I'),('216861534','BSH-SH'),('689442512','DBA'),('772943548','MBA');
/*!40000 ALTER TABLE `Degree_sought` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-02 12:57:28
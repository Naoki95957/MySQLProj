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
-- Table structure for table `Citizenship`
--

DROP TABLE IF EXISTS `Citizenship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Citizenship` (
  `Ssn` char(9) NOT NULL,
  `Country_name` varchar(25) NOT NULL,
  PRIMARY KEY (`Ssn`,`Country_name`),
  KEY `Country_name` (`Country_name`),
  CONSTRAINT `Citizenship_ibfk_1` FOREIGN KEY (`Ssn`) REFERENCES `Student` (`Ssn`),
  CONSTRAINT `Citizenship_ibfk_2` FOREIGN KEY (`Country_name`) REFERENCES `Country` (`Country_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Citizenship`
--

LOCK TABLES `Citizenship` WRITE;
/*!40000 ALTER TABLE `Citizenship` DISABLE KEYS */;
INSERT INTO `Citizenship` VALUES ('689345153','Canada'),('689354615','Canada'),('689442512','Canada'),('689555542','Canada'),('689784138','Canada'),('664138974','Denmark'),('368465135','France'),('689442512','France'),('355640010','Portugal'),('435853516','Spain'),('654651384','Sweden'),('389135481','Switzerland'),('216861534','UK'),('488202454','UK'),('772943548','UK'),('216861534','Wakanda'),('689435588','Wakanda');
/*!40000 ALTER TABLE `Citizenship` ENABLE KEYS */;
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

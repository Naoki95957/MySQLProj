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
-- Table structure for table `Transcript`
--

DROP TABLE IF EXISTS `Transcript`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Transcript` (
  `Ssn` char(9) NOT NULL,
  `C_id` varchar(10) NOT NULL,
  `D_id` varchar(10) NOT NULL,
  `U_id` char(8) NOT NULL,
  `Semester` varchar(25) NOT NULL,
  `Grade` float DEFAULT NULL,
  PRIMARY KEY (`Ssn`,`C_id`,`D_id`,`U_id`,`Semester`),
  KEY `C_id` (`C_id`,`D_id`,`U_id`),
  KEY `D_id` (`D_id`,`U_id`),
  KEY `U_id` (`U_id`),
  CONSTRAINT `Transcript_ibfk_1` FOREIGN KEY (`Ssn`) REFERENCES `Student` (`Ssn`),
  CONSTRAINT `Transcript_ibfk_2` FOREIGN KEY (`C_id`, `D_id`, `U_id`) REFERENCES `Course` (`C_id`, `D_id`, `U_id`),
  CONSTRAINT `Transcript_ibfk_3` FOREIGN KEY (`D_id`, `U_id`) REFERENCES `Department` (`D_id`, `U_id`),
  CONSTRAINT `Transcript_ibfk_4` FOREIGN KEY (`U_id`) REFERENCES `University` (`U_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transcript`
--

LOCK TABLES `Transcript` WRITE;
/*!40000 ALTER TABLE `Transcript` DISABLE KEYS */;
INSERT INTO `Transcript` VALUES ('355640010','CS301','CS-L022','10000000','Fall 2018',2.2),('355640010','CS331','CS-L022','10000000','Fall 2018',1.5),('355640010','PHYS104','PHYS-C141','10000000','Fall 2018',3.9),('689442512','FGHT.211','FGHT-BMH3','20000000','Summer 2019',4),('772943548','CHEM161','CHEM.1004','30000000','Fall 2018',3.8),('772943548','CMST138','EH2L01','40000000','Winter 2018',3),('772943548','CMST418','EH2L01','40000000','Winter 2018',2.9),('772943548','CS311','CS-L022','50000000','Spring 2018',3.4),('772943548','ENG031','COM-R075','50000000','Spring 2019',1.5),('772943548','ENG031','COM-R075','50000000','Summer 2019',2.7);
/*!40000 ALTER TABLE `Transcript` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-02 12:57:30

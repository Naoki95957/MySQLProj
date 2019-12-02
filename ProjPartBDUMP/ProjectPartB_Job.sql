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
-- Table structure for table `Job`
--

DROP TABLE IF EXISTS `Job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Job` (
  `Ssn` char(9) NOT NULL,
  `J_name` varchar(25) NOT NULL,
  `J_type` varchar(25) DEFAULT NULL,
  `Emp_name` varchar(25) NOT NULL,
  `Emp_phone` char(10) DEFAULT NULL,
  `Emp_address` varchar(60) DEFAULT NULL,
  `Hrs_per_week` float DEFAULT NULL,
  PRIMARY KEY (`Ssn`,`J_name`,`Emp_name`),
  CONSTRAINT `Job_ibfk_1` FOREIGN KEY (`Ssn`) REFERENCES `Student` (`Ssn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Job`
--

LOCK TABLES `Job` WRITE;
/*!40000 ALTER TABLE `Job` DISABLE KEYS */;
INSERT INTO `Job` VALUES ('368465135','Dishwasher','Food Worker','Bellevue College','4259991724','3000 Landerholm Cir SE Bellevue WA',8),('368465135','Line Cook','Food Worker','Bellevue College','4259991724','3000 Landerholm Cir SE Bellevue WA',8),('488202454','Line Cook','Food Worker','Bellevue College','4259991724','3000 Landerholm Cir SE Bellevue WA',15.5),('689354615','Editor','Journalist','HillValley School Paper','5678890050','121 Gigawatts Dr Abestos WY',18),('689435588','Ticket Booth Cashier','Sales','University of Washington','4259994563','1410 NE Campus Pkwy Seattle WA',7.5),('689442512','Arsenal DM','Distribution Manager','Joker Inc.','6666666666','10040 Louis Ln Metropolis NY',35),('689555542','Lab Assistant','Scientist','Doc Oc Laboratory','1234567890','344 Clinton St Metropolis NY',5.5),('689555542','Teacher Assistant','Educator','Metropolis University','1234567890','344 Clinton St Metropolis NY',10),('689784138','Math Tutor','Educator','Bellevue College','4259991724','3000 Landerholm Cir SE Bellevue WA',12.5),('772943548','Operational Coordinator','Construction Management','Wakanda Enterprises','111222333','Space Needle Seattle WA',29.5);
/*!40000 ALTER TABLE `Job` ENABLE KEYS */;
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

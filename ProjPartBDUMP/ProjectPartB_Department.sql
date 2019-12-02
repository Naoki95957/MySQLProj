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
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department` (
  `D_id` varchar(10) NOT NULL,
  `U_id` char(8) NOT NULL,
  `D_name` varchar(50) DEFAULT NULL,
  `D_address` varchar(60) DEFAULT NULL,
  `Head_name` varchar(25) DEFAULT NULL,
  `Head_address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`D_id`,`U_id`),
  KEY `U_id` (`U_id`),
  CONSTRAINT `Department_ibfk_1` FOREIGN KEY (`U_id`) REFERENCES `University` (`U_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
INSERT INTO `Department` VALUES ('CHEM.1004','30000000','Bio-Chemistry','1800 Penny Dr Metropolis NY','Dr. Otto Octavius','123 cephalopod Ct Metropolis NY\r'),('COM-R075','50000000','Communications','2023 King Ln NE Seattle WA','Jane Huang','2023 King Ln NE Seattle WA\r'),('CS-L022','10000000','Computer Science','L Building Bellevue WA','Sara Farag','20390 208th St SE Redmond WA\r'),('CS-L022','50000000','Computer Science','185 E Stevens Way NE Seattle WA','Richard Billo','185 E Stevens Way NE Seattle WA\r'),('CSI-BMH1','20000000','Criminal Investigation','38th St Gotham City NJ','Georg Cantor','123 Ancient Cir Gotham City NJ\r'),('EH2L01','40000000','Communications','1st St Abestos WY','Hellen Keller','123 Nowhere St Abestos WY\r'),('FGHT-BMH3','20000000','Martial Arts','67th St Gotham City NJ','Pablo Picasso','125 Abstract Cir Gotham City NJ\r'),('INT-BMH4','20000000','Interigation Techniques','39th St Gotham City NJ','Isaac Newton','123 Apple Dr Gotham City NJ\r'),('JOUR.2089','30000000','PhotoJournalism','195 Louis Ln Metropolis NY','Peter Parker','20 Ingram St Metropolis NY\r'),('MATH-A001','50000000','Mathematics','4350 Padelford Hall Seattle WA','Alexi Elezi','4350 Padelford Hall Seattle WA\r'),('PHYS-C141','10000000','Physics','C Building Bellevue WA','Erwin Schrödinger','2115 148th St Bellevue WA\r');
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
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

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
-- Table structure for table `Majors`
--

DROP TABLE IF EXISTS `Majors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Majors` (
  `Ssn` char(9) NOT NULL,
  `Mj_name` varchar(25) NOT NULL,
  `D_id` varchar(10) DEFAULT NULL,
  `U_id` char(8) DEFAULT NULL,
  PRIMARY KEY (`Ssn`,`Mj_name`),
  KEY `D_id` (`D_id`,`U_id`),
  KEY `U_id` (`U_id`),
  CONSTRAINT `Majors_ibfk_1` FOREIGN KEY (`Ssn`) REFERENCES `Student` (`Ssn`),
  CONSTRAINT `Majors_ibfk_2` FOREIGN KEY (`D_id`, `U_id`) REFERENCES `Department` (`D_id`, `U_id`),
  CONSTRAINT `Majors_ibfk_3` FOREIGN KEY (`U_id`) REFERENCES `University` (`U_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Majors`
--

LOCK TABLES `Majors` WRITE;
/*!40000 ALTER TABLE `Majors` DISABLE KEYS */;
INSERT INTO `Majors` VALUES ('689555542','Chemical Engineering','CHEM.1004','30000000'),('689784138','Bio-Engineering','CHEM.1004','30000000'),('368465135','Computer Science','CS-L022','10000000'),('389135481','Computer Science','CS-L022','10000000'),('488202454','Computer Science','CS-L022','10000000'),('664138974','Computer Science','CS-L022','10000000'),('435853516','Computer Science','CS-L022','50000000'),('216861534','Holmes Style Deduction','CSI-BMH1','20000000'),('654651384','DNA Testing','CSI-BMH1','20000000'),('654651384','Fingerprint Matching','CSI-BMH1','20000000'),('689345153','Digital Media','EH2L01','40000000'),('689354615','Shakespearean English','EH2L01','40000000'),('216861534','Black Panther','FGHT-BMH3','20000000'),('689442512','Media Litigation','JOUR.2089','30000000'),('435853516','Applied Mathematics','MATH-A001','50000000'),('689435588','Applied Mathematics','MATH-A001','50000000'),('772943548','Buisiness Statistics','MATH-A001','50000000'),('355640010','Engineering','PHYS-C141','10000000'),('355640010','Newtonian Physics','PHYS-C141','10000000');
/*!40000 ALTER TABLE `Majors` ENABLE KEYS */;
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

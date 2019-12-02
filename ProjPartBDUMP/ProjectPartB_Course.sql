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
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `C_id` varchar(10) NOT NULL,
  `D_id` varchar(10) NOT NULL,
  `U_id` char(8) NOT NULL,
  `C_name` varchar(50) DEFAULT NULL,
  `Credits` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`C_id`,`D_id`,`U_id`),
  KEY `U_id` (`U_id`),
  KEY `D_id` (`D_id`,`U_id`),
  CONSTRAINT `Course_ibfk_1` FOREIGN KEY (`U_id`) REFERENCES `University` (`U_id`),
  CONSTRAINT `Course_ibfk_2` FOREIGN KEY (`D_id`, `U_id`) REFERENCES `Department` (`D_id`, `U_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES ('CHEM161','CHEM.1004','30000000','General Chemistry I',6),('CHEM262','CHEM.1004','30000000','Organic Chemistry II',6),('CMST138','EH2L01','40000000','Media Digital Law and Ethics',6),('CMST418','EH2L01','40000000','Investigative Journalism',8),('CS210','CS-L022','10000000','Fundementals of Computer Science',5),('CS301','CS-L022','10000000','Data Structures',5),('CS301','CS-L022','50000000','Fundementals of Computer Science',5),('CS311','CS-L022','50000000','Data Structures',5),('CS331','CS-L022','10000000','Database Systems',5),('CSI.101','CSI-BMH1','20000000','Introduction to Criminology',5),('ENG031','COM-R075','50000000','College Writing Seminar',3),('ENG311','JOUR.2089','30000000','Investigative Journalism',4),('FGHT.211','FGHT-BMH3','20000000','Taekwondo',4),('FGHT.509','FGHT-BMH3','20000000','Advanced BJJ',10),('INT.355','INT-BMH4','20000000','Interogation Case study: the Riddler',3),('JAP201','JOUR.2089','30000000','Japanese II',5),('JAP211','EH2L01','40000000','Japanese 2',6),('MAT208','MATH-A001','50000000','Linear Algebra',5),('MAT208B','MATH-A001','50000000','Linear Algebra Lab',2),('PHYS104','PHYS-C141','10000000','Discoveries in Physics',6),('PHYS141','PHYS-C141','10000000','General Physics I',6);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
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

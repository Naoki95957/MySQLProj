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
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `Ssn` char(9) NOT NULL,
  `Fname` varchar(25) DEFAULT NULL,
  `Minit` char(1) DEFAULT NULL,
  `Lname` varchar(25) DEFAULT NULL,
  `Bdate` date DEFAULT NULL,
  `Sex` char(1) DEFAULT NULL,
  `S_address` varchar(60) DEFAULT NULL,
  `S_type` varchar(9) DEFAULT NULL,
  `V_type` varchar(4) DEFAULT NULL,
  `I20_start` date DEFAULT NULL,
  `I20_exp` date DEFAULT NULL,
  `U_id` char(8) DEFAULT NULL,
  `Enroll_date` date DEFAULT NULL,
  `Class` year(4) DEFAULT NULL,
  PRIMARY KEY (`Ssn`),
  KEY `V_type` (`V_type`),
  KEY `U_id` (`U_id`),
  CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`V_type`) REFERENCES `Visa` (`V_type`),
  CONSTRAINT `Student_ibfk_2` FOREIGN KEY (`U_id`) REFERENCES `University` (`U_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES ('216861534','Scott','Z','Hennen','1987-05-14','M','635 Cheriton Dr Port Shepstone 4246 South Africa','undergrad','M1','2019-07-04','2021-07-04','20000000','2019-08-23',2021),('355640010','Kathy','J','Foote','1996-04-22','F','R Maria M Tavares 7050-343 QUINTA DOS PRETOS Portugal','undergrad','F1','2018-07-01','2020-07-01','10000000','2018-08-25',2020),('368465135','Adele','G','Audette','1988-03-22','F','69 place Stanislas 92000 NANTERRE France','undergrad','F1','2019-08-01','2021-08-01','10000000','2019-08-28',2021),('389135481','Willie','J','Scoles','1991-10-29','M','Fortunastrasse 68 3766 Eschi Switzerland','undergrad','F1','2019-07-03','2021-07-03','10000000','2019-07-29',2021),('435853516','Delilah','C','Copeland','1994-04-27','F','Ventanilla de Beas 27850 Viveiro Spain','undergrad','J1','2018-07-05','2020-07-05','50000000','2018-08-25',2020),('488202454','Chris','P','Geiger','1989-07-01','M','35 Princes Street ROMSLEY B62 0SU UK','undergrad','F1','2019-07-01','2021-07-01','10000000','2019-08-25',2021),('654651384','Maureen','A','Desoto','1998-08-07','F','Angsgatan 29 730 60 Ramnas Sweden','undergrad','M1','2019-06-30','2021-06-30','20000000','2019-08-24',2021),('664138974','Alvin','C','Jones','1990-12-20','M','Mikkelenborgvej 79 1363 Kobenhavn K Denmark','undergrad','J1','2019-07-27','2021-07-27','10000000','2019-09-01',2021),('689345153','Bobbie','D','Breeden','1988-07-18','F','788 Bloor St Vegreville AB T0B 4L0 Canada','undergrad','F1','2019-07-28','2021-07-28','40000000','2019-08-12',2021),('689354615','James','S','May','1989-12-17','M','381 9th Ave Woodstock ON N4S 6J6 Canada','undergrad','F1','2019-08-10','2021-08-10','40000000','2019-08-09',2021),('689435588','Ben','V','Piper','1980-07-07','M','4872 Black Panther Ln Wakanda City Wakanda','undergrad','F1','2018-07-23','2020-07-23','50000000','2018-08-12',2020),('689442512','Suzanne','L','Gero','1992-10-03','F','2010 Lauzon Parkway Tecumseh ON N8N 1L7 Canada','graduate','H1','2019-07-25','2021-07-25','30000000','2019-08-11',2021),('689555542','Charles','R','Bitting','1998-08-08','M','400 Hastings St Vancouver BC V6C 1B4 Canada','undergrad','F1','2019-07-24','2021-07-24','30000000','2019-08-14',2021),('689784138','Sharon','J','Miller','1992-12-11','F','3751 Stum Lake Rd Chilanko Forks BC V0L 1H0 Canada','undergrad','F1','2019-08-15','2021-08-15','30000000','2019-08-12',2021),('772943548','Susan','G','Fields','1986-11-19','F','33 Annfield Rd BAYLHAM IP6 5ND UK','graduate','OPT','2019-07-02','2021-07-02','50000000','2019-09-01',2021);
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
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

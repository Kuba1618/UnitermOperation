CREATE DATABASE  IF NOT EXISTS `masi_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `masi_db`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: masi_db
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `uniterm`
--

DROP TABLE IF EXISTS `uniterm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uniterm` (
  `projectId` int NOT NULL,
  `unitermId` int NOT NULL AUTO_INCREMENT,
  `nameOfProject` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `a` varchar(45) DEFAULT NULL,
  `b` varchar(45) DEFAULT NULL,
  `operation` varchar(45) DEFAULT NULL,
  `expression` varchar(45) DEFAULT NULL,
  `startX` varchar(45) DEFAULT NULL,
  `startY` varchar(45) DEFAULT NULL,
  `endX` varchar(45) DEFAULT NULL,
  `endY` varchar(45) DEFAULT NULL,
  `length` int DEFAULT NULL,
  PRIMARY KEY (`unitermId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uniterm`
--

LOCK TABLES `uniterm` WRITE;
/*!40000 ALTER TABLE `uniterm` DISABLE KEYS */;
INSERT INTO `uniterm` VALUES (945,7,'cdsnanme','cvbdes','aaa','bbb',',','aaa , bbb','50.0','50.0','106.0','50.0',9),(530,8,'cdsnanmeaaaa','cvbdesaaaaa','aaa','bbb',',','aaa , bbb','50.0','50.0','106.0','50.0',9),(573,9,'project222','bdcp','bbb','ddd',',','bbb , ddd','50.0','50.0','106.0','50.0',9),(573,10,'project222','bdcp','ccc','pp',';','ccc ; pp','50.0','100.0','100.0','100.0',8),(705,11,'aasbbd','ccdd','aaaas','bbbd',',','aaaas , bbbd','50.0','50.0','131.0','50.0',12),(705,12,'aasbbd','ccdd','cc','dd',',','cc , dd','50.0','100.0','94.0','50.0',7),(116,13,'nowyProjekt','opis','wwwwwwwwwww','xxxxxxxxxxxxxxxxxxx',';','wwwwwwwwwww ; xxxxxxxxxxxxxxxxxxx','50.0','50.0','296.0','50.0',33),(116,14,'nowyProjekt','opis','cccc','eeeeeee',',','cccc , eeeeeee','50.0','100.0','145.0','50.0',14),(280,15,'abcdewz','Laphabet: abcdewz','a','c',';','a ; c','50.0','50.0','82.0','50.0',5),(280,16,'abcdewz','Laphabet: abcdewz','abb','cd',',','abb , cd','50.0','100.0','100.0','50.0',8),(280,17,'abcdewz','Laphabet: abcdewz','abbcccccd','cdeeeeewwwww',',','abbcccccd , cdeeeeewwwww','50.0','150.0','224.0','50.0',24),(280,18,'abcdewz','Laphabet: abcdewz','abbcccccdzzzzzzzzz','cdeeeeewwwwwzzzz',',','abbcccccdzzzzzzzzz , cdeeeeewwwwwzzzz','50.0','200.0','324.0','50.0',37),(934,20,'test','bbbdd','dddccc','eeeddd',',','dddccc , eeeddd','50.0','100.0','152.0','50.0',15),(934,21,'test','bbbdd','dddccczzzzzzzz','eeedddee',',','dddccczzzzzzzz , eeedddee','50.0','150.0','230.0','50.0',25),(832,24,'test222','test111','eeeee','dddddddd',',','eeeee , dddddddd','50.0','50.0','159.0','50.0',16),(832,25,'test222','test111','dddddddddd','bbbbbbbbbbbbb',',','dddddddddd , bbbbbbbbbbbbb','50.0','100.0','236.0','50.0',26),(832,26,'test222','test111','pppppppppppppppp','ooooooooooooo',';','pppppppppppppppp ; ooooooooooooo','50.0','150.0','289.0','50.0',32),(1026,27,'ab','testdatabase','a','b',';','a ; b','50.0','50.0','82.0','50.0',5);
/*!40000 ALTER TABLE `uniterm` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-30 10:08:40

-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: testdatabase
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `cans`
--

DROP TABLE IF EXISTS `cans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cans` (
  `id` int(11) NOT NULL,
  `anstext` varchar(50) NOT NULL,
  `mode` tinyint(1) unsigned NOT NULL,
  `q_id` int(11) NOT NULL,
  `corr` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `q_id` (`q_id`),
  CONSTRAINT `cans_ibfk_1` FOREIGN KEY (`q_id`) REFERENCES `ques` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cans`
--

LOCK TABLES `cans` WRITE;
/*!40000 ALTER TABLE `cans` DISABLE KEYS */;
INSERT INTO `cans` VALUES (0,'babaevo',1,0,1),(1,'mosqow',1,0,1),(2,'Sydney',1,0,0);
/*!40000 ALTER TABLE `cans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fans`
--

DROP TABLE IF EXISTS `fans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fans` (
  `id` int(11) NOT NULL,
  `anstext` varchar(50) NOT NULL,
  `q_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `q_id` (`q_id`),
  CONSTRAINT `fans_ibfk_1` FOREIGN KEY (`q_id`) REFERENCES `ques` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fans`
--

LOCK TABLES `fans` WRITE;
/*!40000 ALTER TABLE `fans` DISABLE KEYS */;
INSERT INTO `fans` VALUES (0,'4',3);
/*!40000 ALTER TABLE `fans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ques`
--

DROP TABLE IF EXISTS `ques`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ques` (
  `id` int(11) NOT NULL,
  `text` varchar(50) NOT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ques`
--

LOCK TABLES `ques` WRITE;
/*!40000 ALTER TABLE `ques` DISABLE KEYS */;
INSERT INTO `ques` VALUES (0,'Russian towns',2),(1,'Australian town',1),(3,'2+2',0);
/*!40000 ALTER TABLE `ques` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rans`
--

DROP TABLE IF EXISTS `rans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rans` (
  `id` int(11) NOT NULL,
  `anstext` varchar(50) NOT NULL,
  `mode` tinyint(1) unsigned NOT NULL,
  `q_id` int(11) NOT NULL,
  `corr` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `q_id` (`q_id`),
  CONSTRAINT `rans_ibfk_1` FOREIGN KEY (`q_id`) REFERENCES `ques` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rans`
--

LOCK TABLES `rans` WRITE;
/*!40000 ALTER TABLE `rans` DISABLE KEYS */;
INSERT INTO `rans` VALUES (9,'Sydney',1,1,1),(10,'mosqow',1,1,0);
/*!40000 ALTER TABLE `rans` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-19 23:46:39

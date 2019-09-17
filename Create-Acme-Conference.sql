-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Conference
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` double NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `paper` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e5pj3p954weftv1pkj03bi227` (`paper`),
  CONSTRAINT `FK_e5pj3p954weftv1pkj03bi227` FOREIGN KEY (`paper`) REFERENCES `paper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (371,0,120,'Room1','2019-10-10 16:00:00','Activity1\'s summary','Activity1\'s title','PANEL',NULL),(372,0,120,'Room2','2019-10-10 18:00:00','Activity2\'s summary','Activity2\'s title','TUTORIAL',NULL),(382,0,120,'Room3','2019-10-10 16:00:00','Activity3\'s summary','Activity3\'s title','PANEL',NULL);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_attachments`
--

DROP TABLE IF EXISTS `activity_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_attachments` (
  `activity` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_916v3jcuugw4gcbq077unphry` (`activity`),
  CONSTRAINT `FK_916v3jcuugw4gcbq077unphry` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_attachments`
--

LOCK TABLES `activity_attachments` WRITE;
/*!40000 ALTER TABLE `activity_attachments` DISABLE KEYS */;
INSERT INTO `activity_attachments` VALUES (371,'http://google.com/attachments1.html'),(371,'http://google.com/attachments2.html'),(372,'http://google.com/attachments3.html'),(372,'http://google.com/attachments4.html'),(382,'http://google.com/attachments5.html'),(382,'http://google.com/attachments6.html');
/*!40000 ALTER TABLE `activity_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_comments`
--

DROP TABLE IF EXISTS `activity_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_comments` (
  `activity` int(11) NOT NULL,
  `comments` int(11) NOT NULL,
  UNIQUE KEY `UK_9gmyydxpgjmdidqny20xi5lxj` (`comments`),
  KEY `FK_j1txeccncu6hu8v4be5fbemc8` (`activity`),
  CONSTRAINT `FK_j1txeccncu6hu8v4be5fbemc8` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_9gmyydxpgjmdidqny20xi5lxj` FOREIGN KEY (`comments`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_comments`
--

LOCK TABLES `activity_comments` WRITE;
/*!40000 ALTER TABLE `activity_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_sections`
--

DROP TABLE IF EXISTS `activity_sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_sections` (
  `activity` int(11) NOT NULL,
  `sections` int(11) NOT NULL,
  UNIQUE KEY `UK_ammde603taaasrv7w0ap7e6pk` (`sections`),
  KEY `FK_ach9k0im1dlba0r2a2nayfj5x` (`activity`),
  CONSTRAINT `FK_ach9k0im1dlba0r2a2nayfj5x` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_ammde603taaasrv7w0ap7e6pk` FOREIGN KEY (`sections`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_sections`
--

LOCK TABLES `activity_sections` WRITE;
/*!40000 ALTER TABLE `activity_sections` DISABLE KEYS */;
INSERT INTO `activity_sections` VALUES (372,373),(372,374);
/*!40000 ALTER TABLE `activity_sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_speakers`
--

DROP TABLE IF EXISTS `activity_speakers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_speakers` (
  `activity` int(11) NOT NULL,
  `speakers` varchar(255) DEFAULT NULL,
  KEY `FK_gv1l9oyi1so4xaqspu468aq6v` (`activity`),
  CONSTRAINT `FK_gv1l9oyi1so4xaqspu468aq6v` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_speakers`
--

LOCK TABLES `activity_speakers` WRITE;
/*!40000 ALTER TABLE `activity_speakers` DISABLE KEYS */;
INSERT INTO `activity_speakers` VALUES (371,'Eduardo García Valles'),(371,'Pepita Pérez Galdós'),(372,'Romualdo Garrido López'),(372,'Clara Gaitán Llanos'),(382,'Paz Almuhedano Roldán'),(382,'Raimundo Flores Cabra');
/*!40000 ALTER TABLE `activity_speakers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i7xei45auwq1f6vu25985riuh` (`user_account`),
  UNIQUE KEY `UK_awymvli3olnnumqow6wf060pa` (`email`),
  CONSTRAINT `FK_i7xei45auwq1f6vu25985riuh` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  UNIQUE KEY `UK_jj3mmcc0vjobqibj67dvuwihk` (`email`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (335,0,'Admin1 Address','admin1@us.es','Admin1 Middle Name','Admin1 Name','666666666','https://photoadmin1.jpg','Admin1 Surname',321),(336,0,'Admin2 Address','admin2@us.es','Admin2 Middle Name','Admin2 Name','666666666','https://photoadmin2.jpg','Admin2 Surname',322);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `puntuation` int(11) NOT NULL,
  `finder` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ng8tt0uu0j791w837h00d4owq` (`finder`),
  UNIQUE KEY `UK_rjptsoy3q9dcfysbnmy1g0g31` (`user_account`),
  UNIQUE KEY `UK_grm3merlhi91rac0mu26swyhf` (`email`),
  CONSTRAINT `FK_rjptsoy3q9dcfysbnmy1g0g31` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_ng8tt0uu0j791w837h00d4owq` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (337,0,'Author1 Address','author1@us.es','Author1 Middle Name','Author1 Name','666666666','https://photoauthor1.jpg','Author1 Surname',323,'Author1 alias',0,338),(339,0,'Author2 Address','author2@us.es','Author2 Middle Name','Author2 Name','666666666','https://photoauthor2.jpg','Author2 Surname',324,'Author2 alias',0,340),(341,0,'Author3 Address','author3@us.es','Author3 Middle Name','Author3 Name','666666666','https://photoauthor3.jpg','Author3 Surname',325,'Author3 alias',0,342),(343,0,'Author4 Address','author4@us.es','Author4 Middle Name','Author4 Name','666666666','https://photoauthor4.jpg','Author4 Surname',326,'Author4 alias',0,344);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_es` varchar(255) DEFAULT NULL,
  `father` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5ot81c3teoleyxk4l0vwkxg47` (`father`),
  CONSTRAINT `FK_5ot81c3teoleyxk4l0vwkxg47` FOREIGN KEY (`father`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (399,0,'CONFERENCE','CONFERENCIA',NULL),(400,0,'Computer Science','Ciencia computacional',399),(401,0,'Artificial Intelligence','Inteligencia Artificial',400),(402,0,'Deep Learning','Aprendizaje Profundo',401),(403,0,'Instance-based learning','Aprendizaje basado en instancias',401),(404,0,'Regression','Regresión',401),(405,0,'Software Engineering','Ingeniería del Software',399),(406,0,'Big Data','Macrodatos',405),(407,0,'Big Processing','Macroprocesamiento',405),(408,0,'Methods','Métodos',405),(409,0,'Tools','Herramientas',405),(410,0,'Physics','Física',399),(411,0,'Cinematic','Cinemática',410),(412,0,'Electricity','Electricidad',410),(413,0,'Electronics','Electrónica',410),(414,0,'Biology','Biología',399),(415,0,'Animals','Animales',414),(416,0,'Plants','Plantas',414),(417,0,'Fungi','Hongos',414);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_conferences`
--

DROP TABLE IF EXISTS `category_conferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_conferences` (
  `category` int(11) NOT NULL,
  `conferences` int(11) NOT NULL,
  UNIQUE KEY `UK_829224co1lo56admpgcc5cslm` (`conferences`),
  KEY `FK_id1hs759rvkxjnfn7gr956l33` (`category`),
  CONSTRAINT `FK_id1hs759rvkxjnfn7gr956l33` FOREIGN KEY (`category`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_829224co1lo56admpgcc5cslm` FOREIGN KEY (`conferences`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_conferences`
--

LOCK TABLES `category_conferences` WRITE;
/*!40000 ALTER TABLE `category_conferences` DISABLE KEYS */;
INSERT INTO `category_conferences` VALUES (402,370),(402,381);
/*!40000 ALTER TABLE `category_conferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference`
--

DROP TABLE IF EXISTS `conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `acronym` varchar(255) DEFAULT NULL,
  `camera_ready_deadline` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `fee` double NOT NULL,
  `is_final` bit(1) DEFAULT NULL,
  `notification_deadline` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `submission_deadline` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `venue` varchar(255) DEFAULT NULL,
  `administrator` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qjub9mvn5bioqccyew7xcoan` (`administrator`),
  CONSTRAINT `FK_qjub9mvn5bioqccyew7xcoan` FOREIGN KEY (`administrator`) REFERENCES `administrator` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference`
--

LOCK TABLES `conference` WRITE;
/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
INSERT INTO `conference` VALUES (370,0,'Conference1\'s acronym','2019-10-03 21:00:00','2019-10-11 21:00:00',200,'','2019-10-02 21:00:00','2019-10-10 21:00:00','2019-10-01 21:00:00','Conference1\'s summary','Conference1\'s title','Conference1\'s venue',335),(381,0,'Conference2\'s acronym','2019-10-03 21:00:00','2019-10-20 21:00:00',100,'','2019-10-02 21:00:00','2019-10-10 21:00:00','2019-10-01 21:00:00','Conference2\'s summary','Conference2\'s title','Conference2\'s venue',336);
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_activities`
--

DROP TABLE IF EXISTS `conference_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference_activities` (
  `conference` int(11) NOT NULL,
  `activities` int(11) NOT NULL,
  UNIQUE KEY `UK_8aa5t83jo5au40ul1o1mx9hp5` (`activities`),
  KEY `FK_60tvmyyemxoce7qvrh79gn6s7` (`conference`),
  CONSTRAINT `FK_60tvmyyemxoce7qvrh79gn6s7` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_8aa5t83jo5au40ul1o1mx9hp5` FOREIGN KEY (`activities`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_activities`
--

LOCK TABLES `conference_activities` WRITE;
/*!40000 ALTER TABLE `conference_activities` DISABLE KEYS */;
INSERT INTO `conference_activities` VALUES (370,371),(370,372),(381,382);
/*!40000 ALTER TABLE `conference_activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_comments`
--

DROP TABLE IF EXISTS `conference_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference_comments` (
  `conference` int(11) NOT NULL,
  `comments` int(11) NOT NULL,
  UNIQUE KEY `UK_mrfqx9ga6xe1edavk0aqtn3pt` (`comments`),
  KEY `FK_nlfhgghq9n88q908p83spmi2q` (`conference`),
  CONSTRAINT `FK_nlfhgghq9n88q908p83spmi2q` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_mrfqx9ga6xe1edavk0aqtn3pt` FOREIGN KEY (`comments`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_comments`
--

LOCK TABLES `conference_comments` WRITE;
/*!40000 ALTER TABLE `conference_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `conference_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_registrations`
--

DROP TABLE IF EXISTS `conference_registrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference_registrations` (
  `conference` int(11) NOT NULL,
  `registrations` int(11) NOT NULL,
  UNIQUE KEY `UK_byb3ifm614q83cxhdl370t88r` (`registrations`),
  KEY `FK_a80re0xyv7centmxh0wl6yaky` (`conference`),
  CONSTRAINT `FK_a80re0xyv7centmxh0wl6yaky` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_byb3ifm614q83cxhdl370t88r` FOREIGN KEY (`registrations`) REFERENCES `registration` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_registrations`
--

LOCK TABLES `conference_registrations` WRITE;
/*!40000 ALTER TABLE `conference_registrations` DISABLE KEYS */;
INSERT INTO `conference_registrations` VALUES (370,393),(370,394),(370,395),(370,396),(381,397),(381,398);
/*!40000 ALTER TABLE `conference_registrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_submissions`
--

DROP TABLE IF EXISTS `conference_submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference_submissions` (
  `conference` int(11) NOT NULL,
  `submissions` int(11) NOT NULL,
  UNIQUE KEY `UK_lr999enm2fvo69pisuj07fxtd` (`submissions`),
  KEY `FK_ctd995ey2u21eh7cabjbm05vi` (`conference`),
  CONSTRAINT `FK_ctd995ey2u21eh7cabjbm05vi` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_lr999enm2fvo69pisuj07fxtd` FOREIGN KEY (`submissions`) REFERENCES `submission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_submissions`
--

LOCK TABLES `conference_submissions` WRITE;
/*!40000 ALTER TABLE `conference_submissions` DISABLE KEYS */;
INSERT INTO `conference_submissions` VALUES (370,375),(370,377),(370,379),(381,383);
/*!40000 ALTER TABLE `conference_submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_parameters`
--

DROP TABLE IF EXISTS `configuration_parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_parameters` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `default_country` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `message_es` varchar(255) DEFAULT NULL,
  `sys_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_parameters`
--

LOCK TABLES `configuration_parameters` WRITE;
/*!40000 ALTER TABLE `configuration_parameters` DISABLE KEYS */;
INSERT INTO `configuration_parameters` VALUES (314,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','+34','Spain','Welcome to Acme Conference! Your scientific event manager','¡Bienvenidos a Acme Conference! Su gestor de eventos científicos','Acme Conference');
/*!40000 ALTER TABLE `configuration_parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_parameters_credit_card_makes`
--

DROP TABLE IF EXISTS `configuration_parameters_credit_card_makes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_parameters_credit_card_makes` (
  `configuration_parameters` int(11) NOT NULL,
  `credit_card_makes` varchar(255) DEFAULT NULL,
  KEY `FK_a9n3u2k74xxd1n2l12aqxmyho` (`configuration_parameters`),
  CONSTRAINT `FK_a9n3u2k74xxd1n2l12aqxmyho` FOREIGN KEY (`configuration_parameters`) REFERENCES `configuration_parameters` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_parameters_credit_card_makes`
--

LOCK TABLES `configuration_parameters_credit_card_makes` WRITE;
/*!40000 ALTER TABLE `configuration_parameters_credit_card_makes` DISABLE KEYS */;
INSERT INTO `configuration_parameters_credit_card_makes` VALUES (314,'VISA'),(314,'MASTER'),(314,'DINNERS'),(314,'AMEX');
/*!40000 ALTER TABLE `configuration_parameters_credit_card_makes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_parameters_topics`
--

DROP TABLE IF EXISTS `configuration_parameters_topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_parameters_topics` (
  `configuration_parameters` int(11) NOT NULL,
  `topics` int(11) NOT NULL,
  UNIQUE KEY `UK_m3bjptrqs2hap3n180au3ogv1` (`topics`),
  KEY `FK_d2l2g1pcedi6uikktb8bdutgr` (`configuration_parameters`),
  CONSTRAINT `FK_d2l2g1pcedi6uikktb8bdutgr` FOREIGN KEY (`configuration_parameters`) REFERENCES `configuration_parameters` (`id`),
  CONSTRAINT `FK_m3bjptrqs2hap3n180au3ogv1` FOREIGN KEY (`topics`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_parameters_topics`
--

LOCK TABLES `configuration_parameters_topics` WRITE;
/*!40000 ALTER TABLE `configuration_parameters_topics` DISABLE KEYS */;
INSERT INTO `configuration_parameters_topics` VALUES (314,315),(314,316),(314,317),(314,318),(314,319);
/*!40000 ALTER TABLE `configuration_parameters_topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_parameters_void_words`
--

DROP TABLE IF EXISTS `configuration_parameters_void_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_parameters_void_words` (
  `configuration_parameters` int(11) NOT NULL,
  `void_words` varchar(255) DEFAULT NULL,
  KEY `FK_pydh1a3eevs9slgqtu2yhjd9q` (`configuration_parameters`),
  CONSTRAINT `FK_pydh1a3eevs9slgqtu2yhjd9q` FOREIGN KEY (`configuration_parameters`) REFERENCES `configuration_parameters` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_parameters_void_words`
--

LOCK TABLES `configuration_parameters_void_words` WRITE;
/*!40000 ALTER TABLE `configuration_parameters_void_words` DISABLE KEYS */;
INSERT INTO `configuration_parameters_void_words` VALUES (314,'a'),(314,'able'),(314,'about'),(314,'across'),(314,'after'),(314,'all'),(314,'almost'),(314,'also'),(314,'am'),(314,'among'),(314,'an'),(314,'and'),(314,'any'),(314,'are'),(314,'as'),(314,'at'),(314,'be'),(314,'because'),(314,'been'),(314,'but'),(314,'by'),(314,'can'),(314,'cannot'),(314,'could'),(314,'dear'),(314,'did'),(314,'do'),(314,'does'),(314,'either'),(314,'else'),(314,'ever'),(314,'every'),(314,'for'),(314,'from'),(314,'get'),(314,'got'),(314,'had'),(314,'has'),(314,'have'),(314,'he'),(314,'her'),(314,'hers'),(314,'him'),(314,'his'),(314,'how'),(314,'however'),(314,'i'),(314,'if'),(314,'in'),(314,'into'),(314,'is'),(314,'it'),(314,'its'),(314,'just'),(314,'least'),(314,'let'),(314,'like'),(314,'likely'),(314,'may'),(314,'me'),(314,'might'),(314,'most'),(314,'must'),(314,'my'),(314,'neither'),(314,'no'),(314,'nor'),(314,'not'),(314,'of'),(314,'off'),(314,'often'),(314,'on'),(314,'only'),(314,'or'),(314,'other'),(314,'our'),(314,'own'),(314,'rather'),(314,'said'),(314,'say'),(314,'says'),(314,'she'),(314,'should'),(314,'since'),(314,'so'),(314,'some'),(314,'than'),(314,'that'),(314,'the'),(314,'their'),(314,'them'),(314,'then'),(314,'there'),(314,'these'),(314,'they'),(314,'this'),(314,'tis'),(314,'to'),(314,'too'),(314,'twas'),(314,'us'),(314,'wants'),(314,'was'),(314,'we'),(314,'were'),(314,'what'),(314,'when'),(314,'where'),(314,'which'),(314,'while'),(314,'who'),(314,'whom'),(314,'why'),(314,'will'),(314,'with'),(314,'would'),(314,'yet'),(314,'you'),(314,'your'),(314,'a'),(314,'acá'),(314,'ahí'),(314,'ajena'),(314,'ajeno'),(314,'ajenas'),(314,'ajenos'),(314,'al'),(314,'algo'),(314,'algún'),(314,'alguna'),(314,'alguno'),(314,'algunos'),(314,'algunas'),(314,'allá/allí'),(314,'ambos'),(314,'ante'),(314,'antes'),(314,'aquel'),(314,'aquella'),(314,'aquello'),(314,'aquellas'),(314,'aquellos'),(314,'aquí'),(314,'arriba'),(314,'así'),(314,'atrás'),(314,'aun'),(314,'aunque'),(314,'bajo'),(314,'bastante'),(314,'bien'),(314,'cabe'),(314,'cada'),(314,'casi'),(314,'cierto'),(314,'cierta'),(314,'ciertos'),(314,'ciertas'),(314,'como'),(314,'con'),(314,'conmigo'),(314,'conseguimos'),(314,'conseguir'),(314,'consigo'),(314,'consigue'),(314,'consiguen'),(314,'consigues'),(314,'contigo'),(314,'contra'),(314,'cual'),(314,'cuales'),(314,'cualquier'),(314,'cualquiera'),(314,'cualquieras'),(314,'cuan'),(314,'cuando'),(314,'cuanto'),(314,'cuanta'),(314,'cuantos'),(314,'cuantas'),(314,'de'),(314,'dejar'),(314,'del'),(314,'demás'),(314,'demasiada'),(314,'demasiado'),(314,'demasiadas'),(314,'demasiados'),(314,'dentro'),(314,'desde'),(314,'donde'),(314,'dos'),(314,'el'),(314,'él'),(314,'ella'),(314,'ello'),(314,'ellas'),(314,'ellos'),(314,'empleáis'),(314,'emplean'),(314,'emplear'),(314,'empleas'),(314,'empleo'),(314,'en'),(314,'encima'),(314,'entonces'),(314,'entre'),(314,'era'),(314,'eras'),(314,'eramos'),(314,'eran'),(314,'eres'),(314,'es'),(314,'esa'),(314,'ese'),(314,'eso'),(314,'esas'),(314,'esos'),(314,'esta'),(314,'estas'),(314,'estaba'),(314,'estado'),(314,'estáis'),(314,'estamos'),(314,'están'),(314,'estar'),(314,'este'),(314,'esto'),(314,'estos'),(314,'estoy'),(314,'etc'),(314,'fin'),(314,'fue'),(314,'fueron'),(314,'fui'),(314,'fuimos'),(314,'gueno'),(314,'ha'),(314,'hace '),(314,'haces'),(314,'hacéis'),(314,'hacemos'),(314,'hacen'),(314,'hacer'),(314,'hacia'),(314,'hago'),(314,'hasta'),(314,'incluso'),(314,'intenta'),(314,'intentas'),(314,'intentáis'),(314,'intentamos'),(314,'intentan'),(314,'intentar'),(314,'intento'),(314,'ir'),(314,'jamás'),(314,'junto'),(314,'juntos'),(314,'la'),(314,'lo'),(314,'las'),(314,'los'),(314,'largo'),(314,'más'),(314,'me'),(314,'menos'),(314,'mi'),(314,'mis'),(314,'mía'),(314,'mías'),(314,'mientras'),(314,'mío'),(314,'míos'),(314,'misma'),(314,'mismo'),(314,'mismas'),(314,'mismos'),(314,'modo'),(314,'mucha'),(314,'muchas'),(314,'muchísima'),(314,'muchísimo'),(314,'muchísimas'),(314,'muchísimos'),(314,'mucho'),(314,'muchos'),(314,'muy'),(314,'nada'),(314,'ni'),(314,'ningún'),(314,'ninguna'),(314,'ninguno'),(314,'ningunas'),(314,'ningunos'),(314,'no'),(314,'nos'),(314,'nosotras'),(314,'nosotros'),(314,'nuestra'),(314,'nuestro'),(314,'nuestras'),(314,'nuestros'),(314,'nunca'),(314,'os'),(314,'otra'),(314,'otro'),(314,'otras'),(314,'otros'),(314,'para'),(314,'parecer'),(314,'pero'),(314,'poca'),(314,'poco'),(314,'pocas'),(314,'pocos'),(314,'podéis'),(314,'podemos'),(314,'poder'),(314,'podría'),(314,'podrías'),(314,'podríais'),(314,'podríamos'),(314,'podrían'),(314,'por'),(314,'por qué'),(314,'porque'),(314,'primero'),(314,'puede'),(314,'pueden'),(314,'puedo'),(314,'pues'),(314,'que'),(314,'qué'),(314,'querer'),(314,'quién'),(314,'quienes'),(314,'quienesquiera'),(314,'quienquiera'),(314,'quizá'),(314,'quizás'),(314,'sabe'),(314,'sabes'),(314,'saben'),(314,'sabéis'),(314,'sabemos'),(314,'saber'),(314,'se'),(314,'según'),(314,'ser'),(314,'si'),(314,'sí'),(314,'siempre'),(314,'siendo'),(314,'sin'),(314,'sino'),(314,'so'),(314,'sobre'),(314,'sois'),(314,'solamente'),(314,'solo'),(314,'sólo'),(314,'somos'),(314,'soy'),(314,'sr'),(314,'sra'),(314,'sres'),(314,'sta'),(314,'su'),(314,'sus'),(314,'suya'),(314,'suyo'),(314,'suyas'),(314,'suyos'),(314,'tal'),(314,'tales'),(314,'también'),(314,'tampoco'),(314,'tan'),(314,'tanta'),(314,'tanto'),(314,'tantas'),(314,'tantos'),(314,'te'),(314,'tenéis'),(314,'tenemos'),(314,'tener'),(314,'tengo'),(314,'ti'),(314,'tiempo'),(314,'tiene'),(314,'tienen'),(314,'toda'),(314,'todo'),(314,'todas'),(314,'todos'),(314,'tomar'),(314,'trabaja'),(314,'trabajo'),(314,'trabajáis'),(314,'trabajamos'),(314,'trabajan'),(314,'trabajar'),(314,'trabajas'),(314,'tras'),(314,'tú'),(314,'tu'),(314,'tus'),(314,'tuya'),(314,'tuyo'),(314,'tuyas'),(314,'tuyos'),(314,'último'),(314,'ultimo'),(314,'un'),(314,'una'),(314,'unos'),(314,'unas'),(314,'usa'),(314,'usas'),(314,'usáis'),(314,'usamos'),(314,'usan'),(314,'usar'),(314,'uso'),(314,'usted'),(314,'ustedes'),(314,'va'),(314,'van'),(314,'vais'),(314,'valor'),(314,'vamos'),(314,'varias'),(314,'varios'),(314,'vaya'),(314,'verdadera'),(314,'vosotras'),(314,'vosotros'),(314,'voy'),(314,'vuestra'),(314,'vuestro'),(314,'vuestras'),(314,'vuestros'),(314,'y'),(314,'ya'),(314,'yo');
/*!40000 ALTER TABLE `configuration_parameters_void_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expiration_month` int(11) NOT NULL,
  `expiration_year` int(11) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `owner` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (362,0,'Acme Conference\'s brand',458,3,20,'author1','VISA','4716299708340661',337),(363,0,'Acme Conference\'s brand',427,12,20,'author2','MASTER','5391370983869986',339),(364,0,'Acme Conference\'s brand',719,4,20,'author3','AMEX','378842052333621',341),(365,0,'Acme Conference\'s brand',779,1,20,'author4','AMEX','372871781415462',343),(366,0,'Acme Conference\'s brand',736,1,20,'sponsor1','AMEX','5592372558099087',349),(367,0,'Acme Conference\'s brand',333,1,20,'sponsor2','AMEX','5180982685379471',350),(368,0,'Acme Conference\'s brand',399,1,20,'sponsor3','AMEX','5178144882955470',351),(369,0,'Acme Conference\'s brand',899,1,20,'sponsor4','AMEX','5341202554082692',352);
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n9t1ayk0x7h5vrsfuhygo043j` (`category`),
  CONSTRAINT `FK_n9t1ayk0x7h5vrsfuhygo043j` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
INSERT INTO `finder` VALUES (338,0,NULL,NULL,NULL,NULL,NULL),(340,0,NULL,NULL,NULL,NULL,NULL),(342,0,NULL,NULL,NULL,NULL,NULL),(344,0,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `sender` int(11) DEFAULT NULL,
  `topic` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9e5snvu7d6i2dypiwctuvm8fs` (`topic`),
  CONSTRAINT `FK_9e5snvu7d6i2dypiwctuvm8fs` FOREIGN KEY (`topic`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (353,0,'Message1\'s body','2018-03-05 22:00:00','Message1\'s subject',335,319),(354,0,'Message2\'s body','2018-03-05 22:00:00','Message2\'s subject',337,319),(355,0,'Message3\'s body','2018-03-05 22:00:00','Message3\'s subject',339,319),(356,0,'Message4\'s body','2018-03-05 22:00:00','Message4\'s subject',341,319),(357,0,'Message5\'s body','2018-03-05 22:00:00','Message5\'s subject',343,319),(358,0,'Message1\'s body','2018-03-05 22:00:00','Message1\'s subject',345,319),(359,0,'Message7\'s body','2018-03-05 22:00:00','Message7\'s subject',346,319),(360,0,'Message8\'s body','2018-03-05 22:00:00','Message8\'s subject',347,319),(361,0,'Message9\'s body','2018-08-05 22:00:00','Message9\'s subject',348,319);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_recipients`
--

DROP TABLE IF EXISTS `message_recipients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_recipients` (
  `message` int(11) NOT NULL,
  `recipients` int(11) NOT NULL,
  KEY `FK_1odmg2n3n487tvhuxx5oyyya2` (`message`),
  CONSTRAINT `FK_1odmg2n3n487tvhuxx5oyyya2` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_recipients`
--

LOCK TABLES `message_recipients` WRITE;
/*!40000 ALTER TABLE `message_recipients` DISABLE KEYS */;
INSERT INTO `message_recipients` VALUES (353,337),(353,339),(354,339),(354,341),(355,341),(355,343),(356,343),(356,345),(357,345),(357,346),(358,346),(358,347),(359,347),(359,348),(360,348),(360,335),(361,335),(361,337);
/*!40000 ALTER TABLE `message_recipients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `camera_ready` bit(1) NOT NULL,
  `document` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (376,0,'\0','http://google.com/paperdoc1.html','Paper1\'s summary','Paper1\'s title'),(378,0,'\0','http://google.com/paperdoc2.html','Paper2\'s summary','Paper2\'s title'),(380,0,'\0','http://google.com/paperdoc3.html','Paper3\'s summary','Paper3\'s title'),(384,0,'\0','http://google.com/paperdoc4.html','Paper4\'s summary','Paper4\'s title');
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper_authors`
--

DROP TABLE IF EXISTS `paper_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper_authors` (
  `paper` int(11) NOT NULL,
  `authors` int(11) NOT NULL,
  KEY `FK_9h4r7lf49uxxsgsmf9tgh1m5k` (`authors`),
  KEY `FK_ae9a28ln0ji506i8uoqhv563h` (`paper`),
  CONSTRAINT `FK_ae9a28ln0ji506i8uoqhv563h` FOREIGN KEY (`paper`) REFERENCES `paper` (`id`),
  CONSTRAINT `FK_9h4r7lf49uxxsgsmf9tgh1m5k` FOREIGN KEY (`authors`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper_authors`
--

LOCK TABLES `paper_authors` WRITE;
/*!40000 ALTER TABLE `paper_authors` DISABLE KEYS */;
INSERT INTO `paper_authors` VALUES (376,337),(376,339),(378,339),(378,341),(380,341),(380,343),(384,343),(384,337);
/*!40000 ALTER TABLE `paper_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `author` int(11) NOT NULL,
  `credit_card` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jpwxbrhvii9ekt338bmrdatfg` (`author`),
  KEY `FK_o81sqbmy1blyga0cbtlhbho4d` (`credit_card`),
  CONSTRAINT `FK_o81sqbmy1blyga0cbtlhbho4d` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`),
  CONSTRAINT `FK_jpwxbrhvii9ekt338bmrdatfg` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (393,0,337,362),(394,0,339,363),(395,0,341,364),(396,0,343,365),(397,0,337,362),(398,0,339,363);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `decision` varchar(255) DEFAULT NULL,
  `originality_score` double NOT NULL,
  `quality_score` double NOT NULL,
  `readability_score` double NOT NULL,
  `reviewer` int(11) NOT NULL,
  `submission` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_26pbnwfa1gklnebnnsotvqt88` (`reviewer`),
  KEY `FK_a0lt5jqh9b7s1gw3q77nywxxn` (`submission`),
  CONSTRAINT `FK_a0lt5jqh9b7s1gw3q77nywxxn` FOREIGN KEY (`submission`) REFERENCES `submission` (`id`),
  CONSTRAINT `FK_26pbnwfa1gklnebnnsotvqt88` FOREIGN KEY (`reviewer`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (385,0,'BORDER-LINE',7.5,7.5,7.5,345,375),(386,0,'ACCEPT',7.5,7.5,7.5,346,375),(387,0,'BORDER-LINE',7.5,7.5,7.5,346,377),(388,0,'ACCEPT',7.5,7.5,7.5,347,377),(389,0,'BORDER-LINE',7.5,7.5,7.5,347,379),(390,0,'ACCEPT',7.5,7.5,7.5,348,379),(391,0,'REJECT',4,4,4,348,383),(392,0,'BORDER-LINE',7.5,7.5,7.5,345,383);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_comments`
--

DROP TABLE IF EXISTS `report_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_comments` (
  `report` int(11) NOT NULL,
  `comments` int(11) NOT NULL,
  UNIQUE KEY `UK_d4y879fifriio51somrqna2ax` (`comments`),
  KEY `FK_rv98td83ckn33xg2dtq0jq62u` (`report`),
  CONSTRAINT `FK_rv98td83ckn33xg2dtq0jq62u` FOREIGN KEY (`report`) REFERENCES `report` (`id`),
  CONSTRAINT `FK_d4y879fifriio51somrqna2ax` FOREIGN KEY (`comments`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_comments`
--

LOCK TABLES `report_comments` WRITE;
/*!40000 ALTER TABLE `report_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer`
--

DROP TABLE IF EXISTS `reviewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ite8gbxlfjyy7g7wqqiyjhkmn` (`user_account`),
  UNIQUE KEY `UK_h8t15e4s3v8vv07tp9rrt3qy3` (`email`),
  CONSTRAINT `FK_ite8gbxlfjyy7g7wqqiyjhkmn` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer`
--

LOCK TABLES `reviewer` WRITE;
/*!40000 ALTER TABLE `reviewer` DISABLE KEYS */;
INSERT INTO `reviewer` VALUES (345,0,'Reviewer1 Address','reviewer1@us.es','Reviewer1 Middle Name','Reviewer1 Name','666666666','https://photoreviewer1.jpg','Reviewer1 Surname',327),(346,0,'Reviewer2 Address','reviewer2@us.es','Reviewer2 Middle Name','Reviewer2 Name','666666666','https://photoreviewer2.jpg','Reviewer2 Surname',328),(347,0,'Reviewer3 Address','reviewer3@us.es','Reviewer3 Middle Name','Reviewer3 Name','666666666','https://photoreviewer3.jpg','Reviewer3 Surname',329),(348,0,'Reviewer4 Address','reviewer4@us.es','Reviewer4 Middle Name','Reviewer4 Name','666666666','https://photoreviewer4.jpg','Reviewer4 Surname',330);
/*!40000 ALTER TABLE `reviewer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer_expertise`
--

DROP TABLE IF EXISTS `reviewer_expertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewer_expertise` (
  `reviewer` int(11) NOT NULL,
  `expertise` varchar(255) DEFAULT NULL,
  KEY `FK_5jghl4veyunssejabmkb34iy4` (`reviewer`),
  CONSTRAINT `FK_5jghl4veyunssejabmkb34iy4` FOREIGN KEY (`reviewer`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer_expertise`
--

LOCK TABLES `reviewer_expertise` WRITE;
/*!40000 ALTER TABLE `reviewer_expertise` DISABLE KEYS */;
INSERT INTO `reviewer_expertise` VALUES (345,'Maths'),(345,'Physics'),(346,'French'),(346,'Spanish'),(347,'History'),(347,'Archaeology'),(348,'Industrial design'),(348,'Marketing');
/*!40000 ALTER TABLE `reviewer_expertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (373,0,'Section1\'s summary','Section1\'s title'),(374,0,'Section2\'s summary','Section2\'s title');
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section_pictures`
--

DROP TABLE IF EXISTS `section_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section_pictures` (
  `section` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_fpgvw49vb6tytfbfcghj3o8sv` (`section`),
  CONSTRAINT `FK_fpgvw49vb6tytfbfcghj3o8sv` FOREIGN KEY (`section`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_pictures`
--

LOCK TABLES `section_pictures` WRITE;
/*!40000 ALTER TABLE `section_pictures` DISABLE KEYS */;
INSERT INTO `section_pictures` VALUES (373,'http://google.com/picture1.html'),(373,'http://google.com/picture1.htm2'),(374,'http://google.com/picture3.html'),(374,'http://google.com/picture4.htm2');
/*!40000 ALTER TABLE `section_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_du2w5ldt8rvlvxvtr7vyxk7g3` (`user_account`),
  UNIQUE KEY `UK_7inh7wiji1x5vpu5u3vh0funf` (`email`),
  CONSTRAINT `FK_du2w5ldt8rvlvxvtr7vyxk7g3` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
INSERT INTO `sponsor` VALUES (349,0,'Sponsor1 Address','sponsor1@us.es','Sponsor1 Middle Name','Sponsor1 Name','666666666','https://photosponsor1.jpg','Sponsor1 Surname',331),(350,0,'Sponsor2 Address','sponsor2@us.es','Sponsor2 Middle Name','Sponsor2 Name','666666666','https://photosponsor2.jpg','Sponsor2 Surname',332),(351,0,'Sponsor3 Address','sponsor3@us.es','Sponsor3 Middle Name','Sponsor3 Name','666666666','https://photosponsor3.jpg','Sponsor3 Surname',333),(352,0,'Sponsor4 Address','sponsor4@us.es','Sponsor4 Middle Name','Sponsor4 Name','666666666','https://photosponsor4.jpg','Sponsor4 Surname',334);
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsorship`
--

DROP TABLE IF EXISTS `sponsorship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsorship` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `targeturl` varchar(255) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  `credit_card` int(11) NOT NULL,
  `sponsor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7okrey9gs578grrdgg4qph5rt` (`conference`),
  KEY `FK_b1c71pwhg9slb986j8kl7uul1` (`credit_card`),
  KEY `FK_huglhkud0ihqdljyou4eshra6` (`sponsor`),
  CONSTRAINT `FK_huglhkud0ihqdljyou4eshra6` FOREIGN KEY (`sponsor`) REFERENCES `sponsor` (`id`),
  CONSTRAINT `FK_7okrey9gs578grrdgg4qph5rt` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_b1c71pwhg9slb986j8kl7uul1` FOREIGN KEY (`credit_card`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsorship`
--

LOCK TABLES `sponsorship` WRITE;
/*!40000 ALTER TABLE `sponsorship` DISABLE KEYS */;
INSERT INTO `sponsorship` VALUES (422,0,'https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/2fd32b58324945.59f83720f0b79.jpg','https://targetURL.org',370,366,349),(423,0,'https://findlogovector.com/wp-content/uploads/2018/12/fanta-logo-vector.png','https://targetURL.org',370,366,349),(424,0,'https://ichef.bbci.co.uk/news/660/cpsprodpb/66A2/production/_107847262_gettyimages-1152276563-594x594.jpg','https://targetURL.org',370,367,350),(425,0,'https://is4-ssl.mzstatic.com/image/thumb/Purple123/v4/34/0c/ff/340cff7f-65bf-8466-aec7-bab2c5c2fd43/AppIcon-0-1x_U007emarketing-0-0-85-220-0-10.png/1200x630wa.png','https://targetURL.org',381,367,350),(426,0,'https://gabrielapatron.com/wp-content/uploads/2019/01/Glovo.jpg','https://targetURL.org',381,368,351);
/*!40000 ALTER TABLE `sponsorship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `notified` bit(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  `paper` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ssk77t9sokwi9utdru9hvodul` (`author`),
  KEY `FK_3osm3qjvt3r41xau9xwf5nkdt` (`paper`),
  CONSTRAINT `FK_3osm3qjvt3r41xau9xwf5nkdt` FOREIGN KEY (`paper`) REFERENCES `paper` (`id`),
  CONSTRAINT `FK_ssk77t9sokwi9utdru9hvodul` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
INSERT INTO `submission` VALUES (375,0,'2018-09-10 19:00:00','\0','UNDER-REVIEW','AAA-AUT1',337,376),(377,0,'2018-09-10 19:00:00','\0','UNDER-REVIEW','AAA-AUT2',339,378),(379,0,'2018-09-10 19:00:00','\0','UNDER-REVIEW','AAA-AUT3',341,380),(383,0,'2018-09-10 19:00:00','\0','UNDER-REVIEW','AAA-AUT4',343,384);
/*!40000 ALTER TABLE `submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission_reviewers`
--

DROP TABLE IF EXISTS `submission_reviewers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission_reviewers` (
  `submission` int(11) NOT NULL,
  `reviewers` int(11) NOT NULL,
  KEY `FK_rx31lgg0k67efoplhxv8len0c` (`reviewers`),
  KEY `FK_iwsj2ioiue7vmn5bnhxb2oatb` (`submission`),
  CONSTRAINT `FK_iwsj2ioiue7vmn5bnhxb2oatb` FOREIGN KEY (`submission`) REFERENCES `submission` (`id`),
  CONSTRAINT `FK_rx31lgg0k67efoplhxv8len0c` FOREIGN KEY (`reviewers`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission_reviewers`
--

LOCK TABLES `submission_reviewers` WRITE;
/*!40000 ALTER TABLE `submission_reviewers` DISABLE KEYS */;
INSERT INTO `submission_reviewers` VALUES (375,345),(375,346),(377,346),(377,347),(379,347),(379,348),(383,348),(383,345);
/*!40000 ALTER TABLE `submission_reviewers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_es` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (315,0,'INQUIRY','CONSULTA'),(316,0,'REBUTTAL','REFUTACIÓN'),(317,0,'REGISTRATION','REGISTRO'),(318,0,'TOPIC','TÓPICO'),(319,0,'OTHER','OTRO'),(320,0,'DECISION','DECISIÓN');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (321,0,'d5cee333abe432891a0de57d0ee38713','administrator1'),(322,0,'82954495ff7e2a735ed2192c35b2cd00','administrator2'),(323,0,'b312ba4ffd5245fa2a1ab819ec0d0347','author1'),(324,0,'9bd97baef2b853ec00cc3cffd269f679','author2'),(325,0,'c59a474d5ade296a15ebc40d6c4e8e11','author3'),(326,0,'9c479f8cda01e6c46195dd03adc2b9a1','author4'),(327,0,'6ce19528a40dde9521d97cf7ba264eca','reviewer1'),(328,0,'2693b57f0f59df94caacefb811e99851','reviewer2'),(329,0,'315d31e7c8f3a136610aafa220d689be','reviewer3'),(330,0,'5293e8663cbb7c157ff83eeae25177d3','reviewer4'),(331,0,'42c63ad66d4dc07ed17753772bef96d6','sponsor1'),(332,0,'3dc67f80a03324e01b1640f45d107485','sponsor2'),(333,0,'857a54956061fdc1b88d7722cafe6519','sponsor3'),(334,0,'a8be034fe44a453e912feadc414dc803','sponsor4');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (321,'ADMINISTRATOR'),(322,'ADMINISTRATOR'),(323,'AUTHOR'),(324,'AUTHOR'),(325,'AUTHOR'),(326,'AUTHOR'),(327,'REVIEWER'),(328,'REVIEWER'),(329,'REVIEWER'),(330,'REVIEWER'),(331,'SPONSOR'),(332,'SPONSOR'),(333,'SPONSOR'),(334,'SPONSOR');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vaste`
--

DROP TABLE IF EXISTS `vaste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vaste` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `is_final` bit(1) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `publication_moment` datetime DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `administrator` int(11) NOT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_eetue5938unl5gbqyb0qlq62p` (`administrator`),
  KEY `FK_n7vtygrlrwuuwcrbr5ww7w8pq` (`conference`),
  CONSTRAINT `FK_n7vtygrlrwuuwcrbr5ww7w8pq` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_eetue5938unl5gbqyb0qlq62p` FOREIGN KEY (`administrator`) REFERENCES `administrator` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vaste`
--

LOCK TABLES `vaste` WRITE;
/*!40000 ALTER TABLE `vaste` DISABLE KEYS */;
INSERT INTO `vaste` VALUES (418,0,'Body vaste1','','http://vaste1.jpg','2019-09-04 20:00:00','19-w6-0904','TEST vaste1',335,370),(419,0,'Body vaste2','','http://vaste2.jpg','2019-08-01 20:00:00','19-h2-0801','TEST vaste2',335,370),(420,0,'Body vaste3','','http://vaste3.jpg','2019-06-01 20:00:00','19-Kz-0601','TEST vaste3',336,381),(421,0,'Body vaste4','','http://vaste4.jpg','2019-05-01 20:00:00','19-p8-0501','TEST vaste4',336,381);
/*!40000 ALTER TABLE `vaste` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-16 20:01:48

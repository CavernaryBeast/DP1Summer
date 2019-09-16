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
INSERT INTO `activity` VALUES (360,0,120,'Room1','2019-10-10 16:00:00','Activity1\'s summary','Activity1\'s title','PANEL',NULL),(361,0,120,'Room2','2019-10-10 18:00:00','Activity2\'s summary','Activity2\'s title','TUTORIAL',NULL),(371,0,120,'Room3','2019-10-10 16:00:00','Activity3\'s summary','Activity3\'s title','PANEL',NULL);
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
INSERT INTO `activity_attachments` VALUES (360,'http://google.com/attachments1.html'),(360,'http://google.com/attachments2.html'),(361,'http://google.com/attachments3.html'),(361,'http://google.com/attachments4.html'),(371,'http://google.com/attachments5.html'),(371,'http://google.com/attachments6.html');
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
INSERT INTO `activity_sections` VALUES (361,362),(361,363);
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
INSERT INTO `activity_speakers` VALUES (360,'Eduardo García Valles'),(360,'Pepita Pérez Galdós'),(361,'Romualdo Garrido López'),(361,'Clara Gaitán Llanos'),(371,'Paz Almuhedano Roldán'),(371,'Raimundo Flores Cabra');
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
INSERT INTO `administrator` VALUES (325,0,'Admin Address','admin@us.es','Admin Middle Name','Admin Name','666666666','https://photoadmin.jpg','Admin Surname',312);
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
INSERT INTO `author` VALUES (326,0,'Author1 Address','author1@us.es','Author1 Middle Name','Author1 Name','666666666','https://photoauthor1.jpg','Author1 Surname',313,'Author1 alias',0,327),(328,0,'Author2 Address','author2@us.es','Author2 Middle Name','Author2 Name','666666666','https://photoauthor2.jpg','Author2 Surname',314,'Author2 alias',0,329),(330,0,'Author3 Address','author3@us.es','Author3 Middle Name','Author3 Name','666666666','https://photoauthor3.jpg','Author3 Surname',315,'Author3 alias',0,331),(332,0,'Author4 Address','author4@us.es','Author4 Middle Name','Author4 Name','666666666','https://photoauthor4.jpg','Author4 Surname',316,'Author4 alias',0,333);
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
INSERT INTO `category` VALUES (388,0,'CONFERENCE','CONFERENCIA',NULL),(389,0,'Computer Science','Ciencia computacional',388),(390,0,'Artificial Intelligence','Inteligencia Artificial',389),(391,0,'Deep Learning','Aprendizaje Profundo',390),(392,0,'Instance-based learning','Aprendizaje basado en instancias',390),(393,0,'Regression','Regresión',390),(394,0,'Software Engineering','Ingeniería del Software',388),(395,0,'Big Data','Macrodatos',394),(396,0,'Big Processing','Macroprocesamiento',394),(397,0,'Methods','Métodos',394),(398,0,'Tools','Herramientas',394),(399,0,'Physics','Física',388),(400,0,'Cinematic','Cinemática',399),(401,0,'Electricity','Electricidad',399),(402,0,'Electronics','Electrónica',399),(403,0,'Biology','Biología',388),(404,0,'Animals','Animales',403),(405,0,'Plants','Plantas',403),(406,0,'Fungi','Hongos',403);
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
INSERT INTO `category_conferences` VALUES (391,359),(391,370);
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
INSERT INTO `conference` VALUES (359,0,'Conference1\'s acronym','2019-10-03 21:00:00','2019-10-11 21:00:00',200,'','2019-10-02 21:00:00','2019-10-10 21:00:00','2019-10-01 21:00:00','Conference1\'s summary','Conference1\'s title','Conference1\'s venue',325),(370,0,'Conference2\'s acronym','2019-10-03 21:00:00','2019-10-20 21:00:00',100,'','2019-10-02 21:00:00','2019-10-10 21:00:00','2019-10-01 21:00:00','Conference2\'s summary','Conference2\'s title','Conference2\'s venue',325);
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
INSERT INTO `conference_activities` VALUES (359,360),(359,361),(370,371);
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
INSERT INTO `conference_registrations` VALUES (359,382),(359,383),(359,384),(359,385),(370,386),(370,387);
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
INSERT INTO `conference_submissions` VALUES (359,364),(359,366),(359,368),(370,372);
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
INSERT INTO `configuration_parameters` VALUES (304,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','+34','Spain','Welcome to Acme Conference! Your scientific event manager','¡Bienvenidos a Acme Conference! Su gestor de eventos científicos','Acme Conference');
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
INSERT INTO `configuration_parameters_credit_card_makes` VALUES (304,'VISA'),(304,'MASTER'),(304,'DINNERS'),(304,'AMEX');
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
INSERT INTO `configuration_parameters_topics` VALUES (304,305),(304,306),(304,307),(304,308),(304,309),(304,310),(304,311);
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
INSERT INTO `configuration_parameters_void_words` VALUES (304,'a'),(304,'able'),(304,'about'),(304,'across'),(304,'after'),(304,'all'),(304,'almost'),(304,'also'),(304,'am'),(304,'among'),(304,'an'),(304,'and'),(304,'any'),(304,'are'),(304,'as'),(304,'at'),(304,'be'),(304,'because'),(304,'been'),(304,'but'),(304,'by'),(304,'can'),(304,'cannot'),(304,'could'),(304,'dear'),(304,'did'),(304,'do'),(304,'does'),(304,'either'),(304,'else'),(304,'ever'),(304,'every'),(304,'for'),(304,'from'),(304,'get'),(304,'got'),(304,'had'),(304,'has'),(304,'have'),(304,'he'),(304,'her'),(304,'hers'),(304,'him'),(304,'his'),(304,'how'),(304,'however'),(304,'i'),(304,'if'),(304,'in'),(304,'into'),(304,'is'),(304,'it'),(304,'its'),(304,'just'),(304,'least'),(304,'let'),(304,'like'),(304,'likely'),(304,'may'),(304,'me'),(304,'might'),(304,'most'),(304,'must'),(304,'my'),(304,'neither'),(304,'no'),(304,'nor'),(304,'not'),(304,'of'),(304,'off'),(304,'often'),(304,'on'),(304,'only'),(304,'or'),(304,'other'),(304,'our'),(304,'own'),(304,'rather'),(304,'said'),(304,'say'),(304,'says'),(304,'she'),(304,'should'),(304,'since'),(304,'so'),(304,'some'),(304,'than'),(304,'that'),(304,'the'),(304,'their'),(304,'them'),(304,'then'),(304,'there'),(304,'these'),(304,'they'),(304,'this'),(304,'tis'),(304,'to'),(304,'too'),(304,'twas'),(304,'us'),(304,'wants'),(304,'was'),(304,'we'),(304,'were'),(304,'what'),(304,'when'),(304,'where'),(304,'which'),(304,'while'),(304,'who'),(304,'whom'),(304,'why'),(304,'will'),(304,'with'),(304,'would'),(304,'yet'),(304,'you'),(304,'your'),(304,'a'),(304,'acá'),(304,'ahí'),(304,'ajena'),(304,'ajeno'),(304,'ajenas'),(304,'ajenos'),(304,'al'),(304,'algo'),(304,'algún'),(304,'alguna'),(304,'alguno'),(304,'algunos'),(304,'algunas'),(304,'allá/allí'),(304,'ambos'),(304,'ante'),(304,'antes'),(304,'aquel'),(304,'aquella'),(304,'aquello'),(304,'aquellas'),(304,'aquellos'),(304,'aquí'),(304,'arriba'),(304,'así'),(304,'atrás'),(304,'aun'),(304,'aunque'),(304,'bajo'),(304,'bastante'),(304,'bien'),(304,'cabe'),(304,'cada'),(304,'casi'),(304,'cierto'),(304,'cierta'),(304,'ciertos'),(304,'ciertas'),(304,'como'),(304,'con'),(304,'conmigo'),(304,'conseguimos'),(304,'conseguir'),(304,'consigo'),(304,'consigue'),(304,'consiguen'),(304,'consigues'),(304,'contigo'),(304,'contra'),(304,'cual'),(304,'cuales'),(304,'cualquier'),(304,'cualquiera'),(304,'cualquieras'),(304,'cuan'),(304,'cuando'),(304,'cuanto'),(304,'cuanta'),(304,'cuantos'),(304,'cuantas'),(304,'de'),(304,'dejar'),(304,'del'),(304,'demás'),(304,'demasiada'),(304,'demasiado'),(304,'demasiadas'),(304,'demasiados'),(304,'dentro'),(304,'desde'),(304,'donde'),(304,'dos'),(304,'el'),(304,'él'),(304,'ella'),(304,'ello'),(304,'ellas'),(304,'ellos'),(304,'empleáis'),(304,'emplean'),(304,'emplear'),(304,'empleas'),(304,'empleo'),(304,'en'),(304,'encima'),(304,'entonces'),(304,'entre'),(304,'era'),(304,'eras'),(304,'eramos'),(304,'eran'),(304,'eres'),(304,'es'),(304,'esa'),(304,'ese'),(304,'eso'),(304,'esas'),(304,'esos'),(304,'esta'),(304,'estas'),(304,'estaba'),(304,'estado'),(304,'estáis'),(304,'estamos'),(304,'están'),(304,'estar'),(304,'este'),(304,'esto'),(304,'estos'),(304,'estoy'),(304,'etc'),(304,'fin'),(304,'fue'),(304,'fueron'),(304,'fui'),(304,'fuimos'),(304,'gueno'),(304,'ha'),(304,'hace '),(304,'haces'),(304,'hacéis'),(304,'hacemos'),(304,'hacen'),(304,'hacer'),(304,'hacia'),(304,'hago'),(304,'hasta'),(304,'incluso'),(304,'intenta'),(304,'intentas'),(304,'intentáis'),(304,'intentamos'),(304,'intentan'),(304,'intentar'),(304,'intento'),(304,'ir'),(304,'jamás'),(304,'junto'),(304,'juntos'),(304,'la'),(304,'lo'),(304,'las'),(304,'los'),(304,'largo'),(304,'más'),(304,'me'),(304,'menos'),(304,'mi'),(304,'mis'),(304,'mía'),(304,'mías'),(304,'mientras'),(304,'mío'),(304,'míos'),(304,'misma'),(304,'mismo'),(304,'mismas'),(304,'mismos'),(304,'modo'),(304,'mucha'),(304,'muchas'),(304,'muchísima'),(304,'muchísimo'),(304,'muchísimas'),(304,'muchísimos'),(304,'mucho'),(304,'muchos'),(304,'muy'),(304,'nada'),(304,'ni'),(304,'ningún'),(304,'ninguna'),(304,'ninguno'),(304,'ningunas'),(304,'ningunos'),(304,'no'),(304,'nos'),(304,'nosotras'),(304,'nosotros'),(304,'nuestra'),(304,'nuestro'),(304,'nuestras'),(304,'nuestros'),(304,'nunca'),(304,'os'),(304,'otra'),(304,'otro'),(304,'otras'),(304,'otros'),(304,'para'),(304,'parecer'),(304,'pero'),(304,'poca'),(304,'poco'),(304,'pocas'),(304,'pocos'),(304,'podéis'),(304,'podemos'),(304,'poder'),(304,'podría'),(304,'podrías'),(304,'podríais'),(304,'podríamos'),(304,'podrían'),(304,'por'),(304,'por qué'),(304,'porque'),(304,'primero'),(304,'puede'),(304,'pueden'),(304,'puedo'),(304,'pues'),(304,'que'),(304,'qué'),(304,'querer'),(304,'quién'),(304,'quienes'),(304,'quienesquiera'),(304,'quienquiera'),(304,'quizá'),(304,'quizás'),(304,'sabe'),(304,'sabes'),(304,'saben'),(304,'sabéis'),(304,'sabemos'),(304,'saber'),(304,'se'),(304,'según'),(304,'ser'),(304,'si'),(304,'sí'),(304,'siempre'),(304,'siendo'),(304,'sin'),(304,'sino'),(304,'so'),(304,'sobre'),(304,'sois'),(304,'solamente'),(304,'solo'),(304,'sólo'),(304,'somos'),(304,'soy'),(304,'sr'),(304,'sra'),(304,'sres'),(304,'sta'),(304,'su'),(304,'sus'),(304,'suya'),(304,'suyo'),(304,'suyas'),(304,'suyos'),(304,'tal'),(304,'tales'),(304,'también'),(304,'tampoco'),(304,'tan'),(304,'tanta'),(304,'tanto'),(304,'tantas'),(304,'tantos'),(304,'te'),(304,'tenéis'),(304,'tenemos'),(304,'tener'),(304,'tengo'),(304,'ti'),(304,'tiempo'),(304,'tiene'),(304,'tienen'),(304,'toda'),(304,'todo'),(304,'todas'),(304,'todos'),(304,'tomar'),(304,'trabaja'),(304,'trabajo'),(304,'trabajáis'),(304,'trabajamos'),(304,'trabajan'),(304,'trabajar'),(304,'trabajas'),(304,'tras'),(304,'tú'),(304,'tu'),(304,'tus'),(304,'tuya'),(304,'tuyo'),(304,'tuyas'),(304,'tuyos'),(304,'último'),(304,'ultimo'),(304,'un'),(304,'una'),(304,'unos'),(304,'unas'),(304,'usa'),(304,'usas'),(304,'usáis'),(304,'usamos'),(304,'usan'),(304,'usar'),(304,'uso'),(304,'usted'),(304,'ustedes'),(304,'va'),(304,'van'),(304,'vais'),(304,'valor'),(304,'vamos'),(304,'varias'),(304,'varios'),(304,'vaya'),(304,'verdadera'),(304,'vosotras'),(304,'vosotros'),(304,'voy'),(304,'vuestra'),(304,'vuestro'),(304,'vuestras'),(304,'vuestros'),(304,'y'),(304,'ya'),(304,'yo');
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
INSERT INTO `credit_card` VALUES (351,0,'Acme Conference\'s brand',458,3,20,'author1','VISA','4716299708340661',326),(352,0,'Acme Conference\'s brand',427,12,20,'author2','MASTER','5391370983869986',328),(353,0,'Acme Conference\'s brand',719,4,20,'author3','AMEX','378842052333621',330),(354,0,'Acme Conference\'s brand',779,1,20,'author4','AMEX','372871781415462',332),(355,0,'Acme Conference\'s brand',736,1,20,'sponsor1','AMEX','5592372558099087',338),(356,0,'Acme Conference\'s brand',333,1,20,'sponsor2','AMEX','5180982685379471',339),(357,0,'Acme Conference\'s brand',399,1,20,'sponsor3','AMEX','5178144882955470',340),(358,0,'Acme Conference\'s brand',899,1,20,'sponsor4','AMEX','5341202554082692',341);
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
INSERT INTO `finder` VALUES (327,0,NULL,NULL,NULL,NULL,NULL),(329,0,NULL,NULL,NULL,NULL,NULL),(331,0,NULL,NULL,NULL,NULL,NULL),(333,0,NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `message` VALUES (342,0,'Message1\'s body','2018-03-05 22:00:00','Message1\'s subject',325,309),(343,0,'Message2\'s body','2018-03-05 22:00:00','Message2\'s subject',326,309),(344,0,'Message3\'s body','2018-03-05 22:00:00','Message3\'s subject',328,309),(345,0,'Message4\'s body','2018-03-05 22:00:00','Message4\'s subject',330,309),(346,0,'Message5\'s body','2018-03-05 22:00:00','Message5\'s subject',332,309),(347,0,'Message1\'s body','2018-03-05 22:00:00','Message1\'s subject',334,309),(348,0,'Message7\'s body','2018-03-05 22:00:00','Message7\'s subject',335,309),(349,0,'Message8\'s body','2018-03-05 22:00:00','Message8\'s subject',336,309),(350,0,'Message9\'s body','2018-08-05 22:00:00','Message9\'s subject',337,309);
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
INSERT INTO `message_recipients` VALUES (342,326),(342,328),(343,328),(343,330),(344,330),(344,332),(345,332),(345,334),(346,334),(346,335),(347,335),(347,336),(348,336),(348,337),(349,337),(349,325),(350,325),(350,326);
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
INSERT INTO `paper` VALUES (365,0,'\0','http://google.com/paperdoc1.html','Paper1\'s summary','Paper1\'s title'),(367,0,'\0','http://google.com/paperdoc2.html','Paper2\'s summary','Paper2\'s title'),(369,0,'\0','http://google.com/paperdoc3.html','Paper3\'s summary','Paper3\'s title'),(373,0,'\0','http://google.com/paperdoc4.html','Paper4\'s summary','Paper4\'s title');
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
INSERT INTO `paper_authors` VALUES (365,326),(365,328),(367,328),(367,330),(369,330),(369,332),(373,332),(373,326);
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
INSERT INTO `registration` VALUES (382,0,326,351),(383,0,328,352),(384,0,330,353),(385,0,332,354),(386,0,326,351),(387,0,328,352);
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
INSERT INTO `report` VALUES (374,0,'BORDER-LINE',7.5,7.5,7.5,334,364),(375,0,'ACCEPT',7.5,7.5,7.5,335,364),(376,0,'BORDER-LINE',7.5,7.5,7.5,335,366),(377,0,'ACCEPT',7.5,7.5,7.5,336,366),(378,0,'BORDER-LINE',7.5,7.5,7.5,336,368),(379,0,'ACCEPT',7.5,7.5,7.5,337,368),(380,0,'REJECT',4,4,4,337,372),(381,0,'BORDER-LINE',7.5,7.5,7.5,334,372);
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
INSERT INTO `reviewer` VALUES (334,0,'Reviewer1 Address','reviewer1@us.es','Reviewer1 Middle Name','Reviewer1 Name','666666666','https://photoreviewer1.jpg','Reviewer1 Surname',317),(335,0,'Reviewer2 Address','reviewer2@us.es','Reviewer2 Middle Name','Reviewer2 Name','666666666','https://photoreviewer2.jpg','Reviewer2 Surname',318),(336,0,'Reviewer3 Address','reviewer3@us.es','Reviewer3 Middle Name','Reviewer3 Name','666666666','https://photoreviewer3.jpg','Reviewer3 Surname',319),(337,0,'Reviewer4 Address','reviewer4@us.es','Reviewer4 Middle Name','Reviewer4 Name','666666666','https://photoreviewer4.jpg','Reviewer4 Surname',320);
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
INSERT INTO `reviewer_expertise` VALUES (334,'Maths'),(334,'Physics'),(335,'French'),(335,'Spanish'),(336,'History'),(336,'Archaeology'),(337,'Industrial design'),(337,'Marketing');
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
INSERT INTO `section` VALUES (362,0,'Section1\'s summary','Section1\'s title'),(363,0,'Section2\'s summary','Section2\'s title');
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
INSERT INTO `section_pictures` VALUES (362,'http://google.com/picture1.html'),(362,'http://google.com/picture1.htm2'),(363,'http://google.com/picture3.html'),(363,'http://google.com/picture4.htm2');
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
INSERT INTO `sponsor` VALUES (338,0,'Sponsor1 Address','sponsor1@us.es','Sponsor1 Middle Name','Sponsor1 Name','666666666','https://photosponsor1.jpg','Sponsor1 Surname',321),(339,0,'Sponsor2 Address','sponsor2@us.es','Sponsor2 Middle Name','Sponsor2 Name','666666666','https://photosponsor2.jpg','Sponsor2 Surname',322),(340,0,'Sponsor3 Address','sponsor3@us.es','Sponsor3 Middle Name','Sponsor3 Name','666666666','https://photosponsor3.jpg','Sponsor3 Surname',323),(341,0,'Sponsor4 Address','sponsor4@us.es','Sponsor4 Middle Name','Sponsor4 Name','666666666','https://photosponsor4.jpg','Sponsor4 Surname',324);
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
INSERT INTO `sponsorship` VALUES (407,0,'https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/2fd32b58324945.59f83720f0b79.jpg','https://targetURL.org',359,355,338),(408,0,'https://findlogovector.com/wp-content/uploads/2018/12/fanta-logo-vector.png','https://targetURL.org',359,355,338),(409,0,'https://ichef.bbci.co.uk/news/660/cpsprodpb/66A2/production/_107847262_gettyimages-1152276563-594x594.jpg','https://targetURL.org',359,356,339),(410,0,'https://is4-ssl.mzstatic.com/image/thumb/Purple123/v4/34/0c/ff/340cff7f-65bf-8466-aec7-bab2c5c2fd43/AppIcon-0-1x_U007emarketing-0-0-85-220-0-10.png/1200x630wa.png','https://targetURL.org',370,356,339),(411,0,'https://gabrielapatron.com/wp-content/uploads/2019/01/Glovo.jpg','https://targetURL.org',370,357,340);
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
INSERT INTO `submission` VALUES (364,0,'2018-09-10 19:00:00','\0','UNDER-REVIEW','AAA-AUT1',326,365),(366,0,'2018-09-10 19:00:00','\0','UNDER-REVIEW','AAA-AUT2',328,367),(368,0,'2018-09-10 19:00:00','\0','UNDER-REVIEW','AAA-AUT3',330,369),(372,0,'2018-09-10 19:00:00','\0','UNDER-REVIEW','AAA-AUT4',332,373);
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
INSERT INTO `submission_reviewers` VALUES (364,334),(364,335),(366,335),(366,336),(368,336),(368,337),(372,337),(372,334);
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
INSERT INTO `topic` VALUES (305,0,'INQUIRY','CONSULTA'),(306,0,'REBUTTAL','REFUTACIÓN'),(307,0,'REGISTRATION','REGISTRO'),(308,0,'TOPIC','TÓPICO'),(309,0,'OTHER','OTRO'),(310,0,'DECISION','DECISIÓN'),(311,0,'BASIC','BÁSICO');
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
INSERT INTO `user_account` VALUES (312,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(313,0,'b312ba4ffd5245fa2a1ab819ec0d0347','author1'),(314,0,'9bd97baef2b853ec00cc3cffd269f679','author2'),(315,0,'c59a474d5ade296a15ebc40d6c4e8e11','author3'),(316,0,'9c479f8cda01e6c46195dd03adc2b9a1','author4'),(317,0,'6ce19528a40dde9521d97cf7ba264eca','reviewer1'),(318,0,'2693b57f0f59df94caacefb811e99851','reviewer2'),(319,0,'315d31e7c8f3a136610aafa220d689be','reviewer3'),(320,0,'5293e8663cbb7c157ff83eeae25177d3','reviewer4'),(321,0,'42c63ad66d4dc07ed17753772bef96d6','sponsor1'),(322,0,'3dc67f80a03324e01b1640f45d107485','sponsor2'),(323,0,'857a54956061fdc1b88d7722cafe6519','sponsor3'),(324,0,'a8be034fe44a453e912feadc414dc803','sponsor4');
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
INSERT INTO `user_account_authorities` VALUES (312,'ADMINISTRATOR'),(313,'AUTHOR'),(314,'AUTHOR'),(315,'AUTHOR'),(316,'AUTHOR'),(317,'REVIEWER'),(318,'REVIEWER'),(319,'REVIEWER'),(320,'REVIEWER'),(321,'SPONSOR'),(322,'SPONSOR'),(323,'SPONSOR'),(324,'SPONSOR');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-16 23:29:34

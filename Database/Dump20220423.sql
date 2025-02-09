-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: oasip
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event`
(
    `eventId`          int          NOT NULL,
    `bookingName`      varchar(100) NOT NULL,
    `bookingEmail`     varchar(255) NOT NULL,
    `eventStartTime`   datetime     NOT NULL,
    `eventDuration`    int          NOT NULL,
    `eventNotes`       text,
    `EventCategory_id` int          NOT NULL,
    PRIMARY KEY (`eventId`),
    KEY                `fk_Event_EventCategory_idx` (`EventCategory_id`),
    CONSTRAINT `fk_Event_EventCategory` FOREIGN KEY (`EventCategory_id`) REFERENCES `eventcategory` (`eventCatedoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK
TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event`
VALUES (1, 'Preeyathorn Chaiyakum', 'preeyathorn.c@gmail.com', '2022-04-26 00:00:00', 30, '', 1);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `eventcategory`
--

DROP TABLE IF EXISTS `eventcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventcategory`
(
    `eventCatedoryId`          int          NOT NULL,
    `eventCategoryName`        varchar(100) NOT NULL,
    `eventCategoryDescription` text,
    `eventDuration`            int          NOT NULL,
    PRIMARY KEY (`eventCatedoryId`),
    UNIQUE KEY `eventCategoryName_UNIQUE` (`eventCategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventcategory`
--

LOCK
TABLES `eventcategory` WRITE;
/*!40000 ALTER TABLE `eventcategory` DISABLE KEYS */;
INSERT INTO `eventcategory`
VALUES (1, 'BackEnd Clinic', 'ช่องทางปรึษาสำหรับ Backend', 30);
/*!40000 ALTER TABLE `eventcategory` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-23  1:14:40

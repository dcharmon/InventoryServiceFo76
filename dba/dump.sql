-- MySQL dump 10.13  Distrib 8.4.6, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: inventory_service_fo76
-- ------------------------------------------------------
-- Server version	8.4.6

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `auth_subject` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `display_name` varchar(80) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `auth_subject` (`auth_subject`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'local-dev-user-1','local@example.com','Local Dev','2026-02-20 03:39:14');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `armor_base_resistance`
--

DROP TABLE IF EXISTS `armor_base_resistance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `armor_base_resistance` (
  `armor_type_id` int NOT NULL,
  `slot_group` enum('ARM','LEG','TORSO') NOT NULL,
  `damage_resistance` smallint NOT NULL,
  `energy_resistance` smallint NOT NULL,
  `radiation_resistance` smallint NOT NULL,
  PRIMARY KEY (`armor_type_id`,`slot_group`),
  CONSTRAINT `fk_base_res_type` FOREIGN KEY (`armor_type_id`) REFERENCES `armor_type` (`armor_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armor_base_resistance`
--

LOCK TABLES `armor_base_resistance` WRITE;
/*!40000 ALTER TABLE `armor_base_resistance` DISABLE KEYS */;
/*!40000 ALTER TABLE `armor_base_resistance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `armor_slot`
--

DROP TABLE IF EXISTS `armor_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `armor_slot` (
  `armor_slot_id` int NOT NULL AUTO_INCREMENT,
  `slot_name` varchar(30) NOT NULL,
  `slot_group` enum('ARM','LEG','TORSO') NOT NULL,
  PRIMARY KEY (`armor_slot_id`),
  UNIQUE KEY `slot_name` (`slot_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armor_slot`
--

LOCK TABLES `armor_slot` WRITE;
/*!40000 ALTER TABLE `armor_slot` DISABLE KEYS */;
INSERT INTO `armor_slot` VALUES (1,'Left Arm','ARM'),(2,'Right Arm','ARM'),(3,'Torso','TORSO'),(4,'Left Leg','LEG'),(5,'Right Leg','LEG');
/*!40000 ALTER TABLE `armor_slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `armor_type`
--

DROP TABLE IF EXISTS `armor_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `armor_type` (
  `armor_type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(80) NOT NULL,
  PRIMARY KEY (`armor_type_id`),
  UNIQUE KEY `type_name` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armor_type`
--

LOCK TABLES `armor_type` WRITE;
/*!40000 ALTER TABLE `armor_type` DISABLE KEYS */;
INSERT INTO `armor_type` VALUES (1,'Arctic marine armor'),(2,'Botsmith armor'),(3,'Brotherhood recon armor'),(4,'Civil Engineer armor'),(5,'Combat armor'),(6,'Covert scout armor'),(7,'Forest scout armor'),(8,'Leather armor'),(9,'Marine armor'),(10,'Metal armor'),(11,'Raider armor'),(12,'Robot armor'),(13,'Secret Service armor'),(14,'Solar armor'),(15,'Thorn armor'),(16,'Trapper armor'),(17,'Urban scout armor'),(18,'Wood armor');
/*!40000 ALTER TABLE `armor_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_armor_piece`
--

DROP TABLE IF EXISTS `user_armor_piece`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_armor_piece` (
  `user_armor_piece_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `armor_type_id` int NOT NULL,
  `armor_slot_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_armor_piece_id`),
  KEY `fk_uap_type` (`armor_type_id`),
  KEY `fk_uap_slot` (`armor_slot_id`),
  CONSTRAINT `fk_uap_slot` FOREIGN KEY (`armor_slot_id`) REFERENCES `armor_slot` (`armor_slot_id`),
  CONSTRAINT `fk_uap_type` FOREIGN KEY (`armor_type_id`) REFERENCES `armor_type` (`armor_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_armor_piece`
--

LOCK TABLES `user_armor_piece` WRITE;
/*!40000 ALTER TABLE `user_armor_piece` DISABLE KEYS */;
INSERT INTO `user_armor_piece` VALUES (1,1,8,1,'2026-02-20 03:41:46');
/*!40000 ALTER TABLE `user_armor_piece` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-22 14:01:33

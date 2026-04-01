-- MySQL dump 10.13  Distrib 8.4.6, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: inventory_service_fo76_test
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
  `user_id` int NOT NULL AUTO_INCREMENT,
  `auth_subject` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `display_name` varchar(80) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uq_app_user_auth_subject` (`auth_subject`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'local-dev-user-1','local@example.com','Local Dev','2026-02-22 21:21:38');
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
  `poison_resistance` smallint NOT NULL DEFAULT '0',
  `fire_resistance` smallint NOT NULL DEFAULT '0',
  `cryo_resistance` smallint NOT NULL DEFAULT '0',
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
  UNIQUE KEY `uq_armor_slot_slot_name` (`slot_name`)
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
  `weight_class` enum('Light','Sturdy','Heavy') DEFAULT NULL,
  PRIMARY KEY (`armor_type_id`),
  UNIQUE KEY `uq_armor_type_type_name` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armor_type`
--

LOCK TABLES `armor_type` WRITE;
/*!40000 ALTER TABLE `armor_type` DISABLE KEYS */;
INSERT INTO `armor_type` VALUES (1,'Arctic Marine Armor','Sturdy'),(2,'Botsmith Armor','Heavy'),(3,'Brotherhood Recon Armor','Heavy'),(4,'Civil Engineer Armor','Sturdy'),(5,'Covert Scout Armor','Light'),(6,'Forest Scout Armor','Light'),(7,'Heavy Combat Armor','Heavy'),(8,'Heavy Leather Armor','Heavy'),(9,'Heavy Metal Armor','Heavy'),(10,'Heavy Raider Armor','Heavy'),(11,'Heavy Robot Armor','Heavy'),(12,'Light Combat Armor','Light'),(13,'Light Leather Armor','Light'),(14,'Light Metal Armor','Light'),(15,'Light Raider Armor','Light'),(16,'Light Robot Armor','Light'),(17,'Marine Armor','Sturdy'),(18,'Secret Service Armor','Heavy'),(19,'Solar Armor','Light'),(20,'Sturdy Combat Armor','Sturdy'),(21,'Sturdy Leather Armor','Sturdy'),(22,'Sturdy Metal Armor','Sturdy'),(23,'Sturdy Raider Armor','Sturdy'),(24,'Sturdy Robot Armor','Sturdy'),(25,'Thorn Armor','Light'),(26,'Trapper Armor','Sturdy'),(27,'Urban Scout Armor','Light'),(28,'Wood Armor','Light');
/*!40000 ALTER TABLE `armor_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legendary_effect`
--

DROP TABLE IF EXISTS `legendary_effect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `legendary_effect` (
  `legendary_effect_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `description` varchar(255) NOT NULL,
  `star` tinyint NOT NULL,
  PRIMARY KEY (`legendary_effect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legendary_effect`
--

LOCK TABLES `legendary_effect` WRITE;
/*!40000 ALTER TABLE `legendary_effect` DISABLE KEYS */;
INSERT INTO `legendary_effect` VALUES (1,'Adrenal','+10 Damage and Energy Resistance per kill while on a Kill Streak (Max 10)',1),(2,'Aristocrat\'s','Grants up to +20 energy resistance and damage resistance, the higher your caps',1),(3,'Assassin\'s','-15% damage from humans',1),(4,'Auto Stim','Automatically use a Stimpak when hit while health is 25% or less, once every 60 seconds',1),(5,'Bolstering','Grants up to +35 energy resistance and damage resistance, the lower your health',1),(6,'Chameleon','Become invisible while sneaking and not moving',1),(7,'Cloaking','Being hit in melee causes the player to become invisible once every 30 seconds',1),(8,'Exterminator\'s','-15% damage from mirelurks and insects',1),(9,'Ghoul Slayer\'s','-15% damage from ghouls',1),(10,'Hunter\'s','-15% damage from animals',1),(11,'Life Saving','When incapacitated, gain a 50% chance to revive yourself with a Stimpak, once every 60 seconds',1),(12,'Lucid','Increases Damage Reduction up to +6% as you fill your Feral Meter',1),(13,'Mutant Slayer\'s','-15% damage from super mutants',1),(14,'Mutant\'s','+10 damage resistance and energy resistance while mutated',1),(15,'Nocturnal','+4 PER and AGI while cloaked',1),(16,'Overeater\'s','Increases damage reduction up to 6% as you fill your hunger and thirst meters',1),(17,'Regenerating','+0.5% heal rate',1),(18,'Troubleshooter\'s','-15% damage from robots',1),(19,'Unyielding','Gain up to +3 to all S.P.E.C.I.A.L. stats (except END) when health is low',1),(20,'Vanguard\'s','Grants up to +35 energy resistance and damage resistance, the higher your health',1),(21,'Weightless','-90% weight',1),(22,'Zealot\'s','-15% damage from scorched',1),(23,'Agility','+2 Agility',2),(24,'Antiseptic','+25% reduced disease chance from environmental hazards',2),(25,'Charisma','+2 Charisma',2),(26,'Elementalist','Increase All Resistances by +25',2),(27,'Endurance','+2 Endurance',2),(28,'Fierce','Fortify Limb Resistance based on Kill Streak count',2),(29,'Fireproof','+50 fire resistance',2),(30,'Glutton','Hunger and thirst grow 10% slower',2),(31,'Hardy','Receive 7% less explosion damage',2),(32,'HazMat','+50 radiation resistance',2),(33,'Intelligence','+2 Intelligence',2),(34,'Luck','+2 Luck',2),(35,'Pain Killer','Gain health over time while on a Kill Streak. Effect becomes stronger the higher the Kill Streak',2),(36,'Perception','+2 Perception',2),(37,'Poisoner\'s','+50 poison resistance',2),(38,'Powered','+5% action point regen',2),(39,'Rushing','Gain Action Points over time while on a Kill Streak. Effect becomes stronger the higher the Kill Streak',2),(40,'Strength','+2 Strength',2),(41,'Warming','+50 cryo resistance',2),(42,'Acrobat\'s','-50% fall damage',3),(43,'Active','Max AP Increased by +20',3),(44,'Adamantium','+15% limb damage resistance',3),(45,'Arms Keeper\'s','Weapon weights reduced by 20%',3),(46,'Belted','Ammo weight reduced by 20%',3),(47,'Burning','5% chance to deal 19 fire damage per second for 3 seconds to melee attackers',3),(48,'Cavalier\'s','90% damage taken while sprinting, compounding',3),(49,'Defender\'s','+5% chance to automatically block attacks',3),(50,'Dissipating','+0.25% radiation damage recovery',3),(51,'Diver\'s','Breathe underwater',3),(52,'Doctor\'s','+5% effectiveness of Stimpaks, RadAway, and Rad-X',3),(53,'Durability','+50 durability',3),(54,'Electrified','5% chance to deal 18 energy damage per second for 3 seconds to melee attackers',3),(55,'Frozen','5% chance to deal 12 cryo damage per second for 4 seconds to melee attackers',3),(56,'Healthy','Max HP Increased by +20',3),(57,'Pack Rat\'s','Junk item weights reduced by 20%',3),(58,'Reflex','2% Evade',3),(59,'Safecracker\'s','+1 hacking skill, +1 lockpicking skill',3),(60,'Secret Agent\'s','+25% less noise while sneaking, +25% reduced detection chance',3),(61,'Sentinel\'s','95% damage taken while not moving, compounding',3),(62,'Thru-hiker\'s','Food, drink, and chem weights reduced by 20%',3),(63,'Toxic','5% chance to deal 12 poison damage per second for 7 seconds to melee attackers',3),(64,'Battle-Loader\'s','15% chance to instantly reload when bashing enemies (up to 75% chance on full stack)',4),(65,'Bruiser\'s','Melee weapons deal +5% bonus damage (up to +25% on full stack)',4),(66,'Limit-Breaking','Each worn armor piece reduces the cost of critical hits by -10% (up to -50% on full stack)',4),(67,'Miasma\'s','When hit, a poisonous cloud harms nearby targets for 10s (poison damage increases per equipped piece)',4),(68,'Ranger\'s','Ranged weapons deal +5% bonus damage (up to +25% on full stack)',4),(69,'Runner\'s','Sprinting action point cost reduced by -20% (up to -100% on full stack)',4),(70,'Sawbones','Health regenerates slowly (+1 health per second) (up to +5 health per second on full stack)',4),(71,'Tanky\'s','+200 damage resist for 10s when standing still (20s cooldown) (up to +1000 on full stack)',4);
/*!40000 ALTER TABLE `legendary_effect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_armor_piece`
--

DROP TABLE IF EXISTS `user_armor_piece`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_armor_piece` (
  `user_armor_piece_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `armor_type_id` int NOT NULL,
  `armor_slot_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `star1_effect_id` int DEFAULT NULL,
  `star2_effect_id` int DEFAULT NULL,
  `star3_effect_id` int DEFAULT NULL,
  `star4_effect_id` int DEFAULT NULL,
  PRIMARY KEY (`user_armor_piece_id`),
  KEY `ix_uap_user` (`user_id`),
  KEY `ix_uap_type` (`armor_type_id`),
  KEY `ix_uap_slot` (`armor_slot_id`),
  KEY `fk_uap_star1` (`star1_effect_id`),
  KEY `fk_uap_star2` (`star2_effect_id`),
  KEY `fk_uap_star3` (`star3_effect_id`),
  KEY `fk_uap_star4` (`star4_effect_id`),
  CONSTRAINT `fk_uap_slot` FOREIGN KEY (`armor_slot_id`) REFERENCES `armor_slot` (`armor_slot_id`),
  CONSTRAINT `fk_uap_star1` FOREIGN KEY (`star1_effect_id`) REFERENCES `legendary_effect` (`legendary_effect_id`),
  CONSTRAINT `fk_uap_star2` FOREIGN KEY (`star2_effect_id`) REFERENCES `legendary_effect` (`legendary_effect_id`),
  CONSTRAINT `fk_uap_star3` FOREIGN KEY (`star3_effect_id`) REFERENCES `legendary_effect` (`legendary_effect_id`),
  CONSTRAINT `fk_uap_star4` FOREIGN KEY (`star4_effect_id`) REFERENCES `legendary_effect` (`legendary_effect_id`),
  CONSTRAINT `fk_uap_type` FOREIGN KEY (`armor_type_id`) REFERENCES `armor_type` (`armor_type_id`),
  CONSTRAINT `fk_uap_user` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_armor_piece`
--

LOCK TABLES `user_armor_piece` WRITE;
/*!40000 ALTER TABLE `user_armor_piece` DISABLE KEYS */;
INSERT INTO `user_armor_piece` VALUES (1,1,12,2,'2026-02-23 03:21:40',NULL,NULL,NULL,NULL),(5,1,5,3,'2026-04-01 19:04:25',19,NULL,NULL,NULL);
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

-- Dump completed on 2026-04-01 14:17:21

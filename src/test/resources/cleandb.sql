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
INSERT INTO `armor_base_resistance` VALUES (1,'ARM',39,33,21,14,21,39),(1,'LEG',39,33,21,14,21,39),(1,'TORSO',71,63,39,24,39,71),(2,'ARM',42,16,16,9,9,9),(2,'LEG',42,16,16,9,9,9),(2,'TORSO',81,33,33,16,16,16),(3,'ARM',57,22,22,9,19,11),(3,'LEG',57,22,22,9,19,11),(3,'TORSO',118,42,42,16,37,22),(4,'ARM',26,52,33,11,26,9),(4,'LEG',26,52,33,11,26,9),(4,'TORSO',52,102,62,22,52,16),(5,'ARM',26,26,14,14,9,9),(5,'LEG',26,26,14,14,9,9),(5,'TORSO',52,52,26,26,16,16),(6,'ARM',33,26,9,9,9,9),(6,'LEG',33,26,9,9,9,9),(6,'TORSO',68,47,16,16,16,16),(7,'ARM',36,31,18,14,23,14),(7,'LEG',36,31,18,14,23,14),(7,'TORSO',69,61,36,26,42,26),(8,'ARM',14,42,14,14,11,31),(8,'LEG',14,42,14,14,11,31),(8,'TORSO',26,77,26,31,23,61),(9,'ARM',69,11,14,11,9,11),(9,'LEG',69,11,14,11,9,11),(9,'TORSO',133,13,31,23,18,13),(10,'ARM',24,8,13,11,28,8),(10,'LEG',24,8,13,11,28,8),(10,'TORSO',49,16,24,19,56,16),(11,'ARM',54,23,18,9,14,14),(11,'LEG',54,23,18,9,14,14),(11,'TORSO',102,42,36,18,26,26),(12,'ARM',22,19,11,9,14,9),(12,'LEG',22,19,11,9,14,9),(12,'TORSO',42,37,22,16,26,16),(13,'ARM',9,26,9,9,7,19),(13,'LEG',9,26,9,9,7,19),(13,'TORSO',16,47,16,19,14,37),(14,'ARM',42,7,9,7,6,7),(14,'LEG',42,7,9,7,6,7),(14,'TORSO',81,14,19,14,11,14),(15,'ARM',15,5,8,7,17,5),(15,'LEG',15,5,8,7,17,5),(15,'TORSO',30,10,15,12,34,10),(16,'ARM',33,14,11,6,9,9),(16,'LEG',33,14,11,6,9,9),(16,'TORSO',62,26,22,11,16,16),(17,'ARM',26,10,15,8,8,5),(17,'LEG',26,10,15,8,8,5),(17,'TORSO',51,17,30,15,17,10),(18,'ARM',47,33,42,6,6,6),(18,'LEG',47,33,42,6,6,6),(18,'TORSO',95,68,81,11,11,11),(19,'ARM',26,29,14,14,19,9),(19,'LEG',26,29,14,14,19,9),(19,'TORSO',47,57,26,26,37,16),(20,'ARM',22,19,11,9,14,9),(20,'LEG',22,19,11,9,14,9),(20,'TORSO',54,48,28,20,33,20),(21,'ARM',11,33,11,11,9,24),(21,'LEG',11,33,11,11,9,24),(21,'TORSO',20,61,20,24,18,48),(22,'ARM',54,9,11,9,7,9),(22,'LEG',54,9,11,9,7,9),(22,'TORSO',105,18,24,18,14,18),(23,'ARM',19,6,10,9,22,6),(23,'LEG',19,6,10,9,22,6),(23,'TORSO',39,13,19,15,44,13),(24,'ARM',42,18,14,7,11,11),(24,'LEG',42,18,14,7,11,11),(24,'TORSO',80,33,28,14,20,20),(25,'ARM',26,26,11,22,9,9),(25,'LEG',26,26,11,22,9,9),(25,'TORSO',52,47,22,42,19,19),(26,'ARM',20,17,15,10,5,12),(26,'LEG',20,17,15,10,5,12),(26,'TORSO',38,34,30,20,10,23),(27,'ARM',33,26,9,9,9,9),(27,'LEG',33,26,9,9,9,9),(27,'TORSO',68,47,16,16,16,16),(28,'ARM',22,6,0,0,0,0),(28,'LEG',22,6,0,0,0,0),(28,'TORSO',45,9,0,0,0,0);
/*!40000 ALTER TABLE `armor_base_resistance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `armor_set_bonus`
--

DROP TABLE IF EXISTS `armor_set_bonus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `armor_set_bonus` (
                                   `armor_set_bonus_id` int NOT NULL AUTO_INCREMENT,
                                   `armor_type_id` int NOT NULL,
                                   `description` varchar(255) NOT NULL,
                                   `bonus_type` enum('PER_PIECE','FULL_SET') NOT NULL,
                                   `pieces_required` tinyint NOT NULL,
                                   PRIMARY KEY (`armor_set_bonus_id`),
                                   KEY `ix_armor_set_bonus_type` (`armor_type_id`),
                                   CONSTRAINT `fk_armor_set_bonus_type` FOREIGN KEY (`armor_type_id`) REFERENCES `armor_type` (`armor_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `armor_set_bonus`
--

LOCK TABLES `armor_set_bonus` WRITE;
/*!40000 ALTER TABLE `armor_set_bonus` DISABLE KEYS */;
INSERT INTO `armor_set_bonus` VALUES (1,5,'+5% Stealth in Shadows','PER_PIECE',1),(2,4,'10% chance of 150 Fire DMG to Melee Attackers. Weapons break 35% slower.','FULL_SET',5),(3,19,'Regenerates +4HP/s for you and nearby teammates while your health is above 60%','FULL_SET',5),(4,25,'Melee Attackers Bleed for 125 Damage over 5 seconds. Melee Attacks cause +250 Bleed Damage over 10 seconds. +15% Reduce Detection Chance. 5% Less Noise While Sneaking.','FULL_SET',5);
/*!40000 ALTER TABLE `armor_set_bonus` ENABLE KEYS */;
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
                                    `armor_category` enum('ANY','STANDARD','POWER_ARMOR') NOT NULL DEFAULT 'ANY',
                                    PRIMARY KEY (`legendary_effect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legendary_effect`
--

LOCK TABLES `legendary_effect` WRITE;
/*!40000 ALTER TABLE `legendary_effect` DISABLE KEYS */;
INSERT INTO `legendary_effect` VALUES (1,'Adrenal','+10 Damage and Energy Resistance per kill while on a Kill Streak (Max 10)',1,'ANY'),(2,'Aristocrat\'s','Grants up to +20 energy resistance and damage resistance, the higher your caps',1,'ANY'),(3,'Assassin\'s','-15% damage from humans',1,'ANY'),(4,'Auto Stim','Automatically use a Stimpak when hit while health is 25% or less, once every 60 seconds',1,'ANY'),(5,'Bolstering','Grants up to +35 energy resistance and damage resistance, the lower your health',1,'ANY'),(6,'Chameleon','Become invisible while sneaking and not moving',1,'ANY'),(7,'Cloaking','Being hit in melee causes the player to become invisible once every 30 seconds',1,'ANY'),(8,'Exterminator\'s','-15% damage from mirelurks and insects',1,'ANY'),(9,'Ghoul Slayer\'s','-15% damage from ghouls',1,'ANY'),(10,'Hunter\'s','-15% damage from animals',1,'ANY'),(11,'Life Saving','When incapacitated, gain a 50% chance to revive yourself with a Stimpak, once every 60 seconds',1,'STANDARD'),(12,'Lucid','Increases Damage Reduction up to +6% as you fill your Feral Meter',1,'ANY'),(13,'Mutant Slayer\'s','-15% damage from super mutants',1,'ANY'),(14,'Mutant\'s','+10 damage resistance and energy resistance while mutated',1,'ANY'),(15,'Nocturnal','+4 PER and AGI while cloaked',1,'ANY'),(16,'Overeater\'s','Increases damage reduction up to 6% as you fill your hunger and thirst meters',1,'ANY'),(17,'Regenerating','+0.5% heal rate',1,'ANY'),(18,'Troubleshooter\'s','-15% damage from robots',1,'ANY'),(19,'Unyielding','Gain up to +3 to all S.P.E.C.I.A.L. stats (except END) when health is low',1,'STANDARD'),(20,'Vanguard\'s','Grants up to +35 energy resistance and damage resistance, the higher your health',1,'ANY'),(21,'Heavyweight','Grants up to 10% Damage Reduction at Higher Encumbrance',1,'STANDARD'),(22,'Zealot\'s','-15% damage from scorched',1,'ANY'),(23,'Agility','+2 Agility',2,'ANY'),(24,'Antiseptic','+25% reduced disease chance from environmental hazards',2,'ANY'),(25,'Charisma','+2 Charisma',2,'ANY'),(26,'Elementalist','Increase All Resistances by +25',2,'ANY'),(27,'Endurance','+2 Endurance',2,'ANY'),(28,'Fierce','Fortify Limb Resistance based on Kill Streak count',2,'STANDARD'),(29,'Fireproof','+50 fire resistance',2,'ANY'),(30,'Glutton','Hunger and thirst grow 10% slower',2,'ANY'),(31,'Hardy','Receive 7% less explosion damage',2,'ANY'),(32,'HazMat','+50 radiation resistance',2,'ANY'),(33,'Intelligence','+2 Intelligence',2,'ANY'),(34,'Luck','+2 Luck',2,'ANY'),(35,'Pain Killer','Gain health over time while on a Kill Streak. Effect becomes stronger the higher the Kill Streak',2,'ANY'),(36,'Perception','+2 Perception',2,'ANY'),(37,'Poisoner\'s','+50 poison resistance',2,'ANY'),(38,'Powered','+5% action point regen',2,'ANY'),(39,'Rushing','Gain Action Points over time while on a Kill Streak. Effect becomes stronger the higher the Kill Streak',2,'ANY'),(40,'Strength','+2 Strength',2,'ANY'),(41,'Warming','+50 cryo resistance',2,'ANY'),(42,'Acrobat\'s','-50% fall damage',3,'ANY'),(43,'Active','Max AP Increased by +20',3,'ANY'),(44,'Adamantium','+15% limb damage resistance',3,'STANDARD'),(45,'Arms Keeper\'s','Weapon weights reduced by 20%',3,'ANY'),(46,'Belted','Ammo weight reduced by 20%',3,'ANY'),(47,'Burning','5% chance to deal 19 fire damage per second for 3 seconds to melee attackers',3,'ANY'),(48,'Cavalier\'s','90% damage taken while sprinting, compounding',3,'ANY'),(49,'Defender\'s','+5% chance to automatically block attacks',3,'ANY'),(50,'Dissipating','+0.25% radiation damage recovery',3,'ANY'),(51,'Diver\'s','Breathe underwater',3,'STANDARD'),(52,'Doctor\'s','+5% effectiveness of Stimpaks, RadAway, and Rad-X',3,'ANY'),(53,'Durability','+50 durability',3,'ANY'),(54,'Electrified','5% chance to deal 18 energy damage per second for 3 seconds to melee attackers',3,'ANY'),(55,'Frozen','5% chance to deal 12 cryo damage per second for 4 seconds to melee attackers',3,'ANY'),(56,'Healthy','Max HP Increased by +20',3,'ANY'),(57,'Pack Rat\'s','Junk item weights reduced by 20%',3,'ANY'),(58,'Reflex','2% Evade',3,'STANDARD'),(59,'Safecracker\'s','+1 hacking skill, +1 lockpicking skill',3,'ANY'),(60,'Secret Agent\'s','+25% less noise while sneaking, +25% reduced detection chance',3,'STANDARD'),(61,'Sentinel\'s','95% damage taken while not moving, compounding',3,'ANY'),(62,'Thru-hiker\'s','Food, drink, and chem weights reduced by 20%',3,'ANY'),(63,'Toxic','5% chance to deal 12 poison damage per second for 7 seconds to melee attackers',3,'ANY'),(64,'Battle-Loader\'s','15% chance to instantly reload when bashing enemies (up to 75% chance on full stack)',4,'ANY'),(65,'Bruiser\'s','Melee weapons deal +5% bonus damage (up to +25% on full stack)',4,'ANY'),(66,'Limit-Breaking','Each worn armor piece reduces the cost of critical hits by -10% (up to -50% on full stack)',4,'ANY'),(67,'Miasma\'s','When hit, a poisonous cloud harms nearby targets for 10s (poison damage increases per equipped piece)',4,'ANY'),(68,'Ranger\'s','Ranged weapons deal +5% bonus damage (up to +25% on full stack)',4,'ANY'),(69,'Runner\'s','Sprinting action point cost reduced by -20% (up to -100% on full stack)',4,'ANY'),(70,'Sawbones','Health regenerates slowly (+1 health per second) (up to +5 health per second on full stack)',4,'ANY'),(71,'Tanky\'s','+200 damage resist for 10s when standing still (20s cooldown) (up to +1000 on full stack)',4,'ANY'),(72,'Aegis','Fortifies physical & energy resists by +50 and poison, cryo & fire resists by +20 for wearer & teammates within a 50ft radius',4,'POWER_ARMOR'),(73,'Choo-Choo\'s','10% chance for 500 damage & \"Bloody Mess\" when sprinting into targets (up to 50% chance on full stack)',4,'POWER_ARMOR'),(74,'Propelling','Movement & sprint speed increased by +5% (up to +25% on full stack)',4,'POWER_ARMOR'),(75,'Radioactive-Powered','Gain +2 action point regen at the cost of rads (up to +10 action point regen on full stack)',4,'POWER_ARMOR'),(76,'Reflective','Return 10% of damage received from an enemy target back towards them (up to 50% on full stack)',4,'POWER_ARMOR'),(77,'Rejuvenator\'s','Gradually restores wearer\'s & teammates health & action points within a 50ft radius',4,'POWER_ARMOR'),(78,'Scanner\'s','Action point cost of V.A.T.S. attacks reduced by -5% (up to -25% on full stack)',4,'POWER_ARMOR'),(79,'Stalwart\'s','Power armor breaks 5% slower for owner & all teammates within a 50ft radius (up to 25% slower on full stack)',4,'POWER_ARMOR');
/*!40000 ALTER TABLE `legendary_effect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loadout`
--

DROP TABLE IF EXISTS `loadout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loadout` (
                           `loadout_id` int NOT NULL AUTO_INCREMENT,
                           `user_id` int NOT NULL,
                           `name` varchar(80) NOT NULL,
                           `notes` varchar(255) DEFAULT NULL,
                           `type` enum('STANDARD','POWER_ARMOR') NOT NULL DEFAULT 'STANDARD',
                           `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`loadout_id`),
                           KEY `ix_loadout_user` (`user_id`),
                           CONSTRAINT `fk_loadout_user` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loadout`
--

LOCK TABLES `loadout` WRITE;
/*!40000 ALTER TABLE `loadout` DISABLE KEYS */;
INSERT INTO `loadout` VALUES (1,1,'New Loadout','Some notes','STANDARD','2026-04-01 22:33:29');
/*!40000 ALTER TABLE `loadout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loadout_armor_piece`
--

DROP TABLE IF EXISTS `loadout_armor_piece`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loadout_armor_piece` (
                                       `loadout_id` int NOT NULL,
                                       `user_armor_piece_id` int NOT NULL,
                                       PRIMARY KEY (`loadout_id`,`user_armor_piece_id`),
                                       KEY `fk_lap_piece` (`user_armor_piece_id`),
                                       CONSTRAINT `fk_lap_loadout` FOREIGN KEY (`loadout_id`) REFERENCES `loadout` (`loadout_id`),
                                       CONSTRAINT `fk_lap_piece` FOREIGN KEY (`user_armor_piece_id`) REFERENCES `user_armor_piece` (`user_armor_piece_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loadout_armor_piece`
--

LOCK TABLES `loadout_armor_piece` WRITE;
/*!40000 ALTER TABLE `loadout_armor_piece` DISABLE KEYS */;
INSERT INTO `loadout_armor_piece` VALUES (1,1);
/*!40000 ALTER TABLE `loadout_armor_piece` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loadout_pa_frame`
--

DROP TABLE IF EXISTS `loadout_pa_frame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loadout_pa_frame` (
                                    `loadout_id` int NOT NULL,
                                    `user_pa_frame_id` int NOT NULL,
                                    PRIMARY KEY (`loadout_id`,`user_pa_frame_id`),
                                    KEY `fk_lpaf_frame` (`user_pa_frame_id`),
                                    CONSTRAINT `fk_lpaf_frame` FOREIGN KEY (`user_pa_frame_id`) REFERENCES `user_pa_frame` (`user_pa_frame_id`),
                                    CONSTRAINT `fk_lpaf_loadout` FOREIGN KEY (`loadout_id`) REFERENCES `loadout` (`loadout_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loadout_pa_frame`
--

LOCK TABLES `loadout_pa_frame` WRITE;
/*!40000 ALTER TABLE `loadout_pa_frame` DISABLE KEYS */;
/*!40000 ALTER TABLE `loadout_pa_frame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pa_base_resistance`
--

DROP TABLE IF EXISTS `pa_base_resistance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pa_base_resistance` (
                                      `pa_type_id` int NOT NULL,
                                      `pa_slot_id` int NOT NULL,
                                      `damage_resistance` smallint NOT NULL,
                                      `energy_resistance` smallint NOT NULL,
                                      `radiation_resistance` smallint NOT NULL,
                                      `poison_resistance` smallint NOT NULL DEFAULT '0',
                                      `fire_resistance` smallint NOT NULL DEFAULT '0',
                                      `cryo_resistance` smallint NOT NULL DEFAULT '0',
                                      PRIMARY KEY (`pa_type_id`,`pa_slot_id`),
                                      KEY `fk_pa_base_res_slot` (`pa_slot_id`),
                                      CONSTRAINT `fk_pa_base_res_slot` FOREIGN KEY (`pa_slot_id`) REFERENCES `pa_slot` (`pa_slot_id`),
                                      CONSTRAINT `fk_pa_base_res_type` FOREIGN KEY (`pa_type_id`) REFERENCES `pa_type` (`pa_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pa_base_resistance`
--

LOCK TABLES `pa_base_resistance` WRITE;
/*!40000 ALTER TABLE `pa_base_resistance` DISABLE KEYS */;
INSERT INTO `pa_base_resistance` VALUES (1,1,380,380,246,18,26,26),(1,2,617,617,420,30,45,45),(1,3,380,380,246,18,26,26),(1,4,380,380,246,18,26,26),(1,5,380,380,246,18,26,26),(1,6,380,380,246,18,26,26),(2,1,543,326,344,21,50,14),(2,2,911,543,570,33,86,24),(2,3,543,326,344,21,50,14),(2,4,543,326,344,21,50,14),(2,5,543,326,344,21,50,14),(2,6,543,326,344,21,50,14),(3,1,470,230,326,50,71,39),(3,2,756,383,543,86,111,63),(3,3,470,230,326,50,71,39),(3,4,470,230,326,50,71,39),(3,5,470,230,326,50,71,39),(3,6,470,230,326,50,71,39),(4,1,363,363,690,93,29,44),(4,2,627,627,1151,153,50,71),(4,3,363,363,690,93,29,44),(4,4,363,363,690,93,29,44),(4,5,363,363,690,93,29,44),(4,6,363,363,690,93,29,44),(5,1,447,326,275,33,33,33),(5,2,723,543,470,56,56,56),(5,3,447,326,275,33,33,33),(5,4,447,326,275,33,33,33),(5,5,447,326,275,33,33,33),(5,6,447,326,275,33,33,33),(6,1,425,425,344,24,24,63),(6,2,723,690,570,39,39,102),(6,3,425,425,344,24,24,63),(6,4,425,425,344,24,24,63),(6,5,425,425,344,24,24,63),(6,6,425,425,344,24,24,63),(7,1,518,425,543,39,44,33),(7,2,831,690,911,63,71,56),(7,3,518,425,543,39,44,33),(7,4,518,425,543,39,44,33),(7,5,518,425,543,39,44,33),(7,6,518,425,543,39,44,33),(8,1,657,470,599,39,33,24),(8,2,1098,756,1001,63,56,39),(8,3,657,470,599,39,33,24),(8,4,657,470,599,39,33,24),(8,5,657,470,599,39,33,24),(8,6,657,470,599,39,33,24),(9,1,425,326,792,29,50,50),(9,2,690,543,1328,50,86,78),(9,3,425,326,792,29,50,50),(9,4,425,326,792,29,50,50),(9,5,425,326,792,29,50,50),(9,6,425,326,792,29,50,50),(10,1,470,543,543,24,24,24),(10,2,756,911,911,39,39,39),(10,3,470,543,543,24,24,24),(10,4,470,543,543,24,24,24),(10,5,470,543,543,24,24,24),(10,6,470,543,543,24,24,24),(11,1,363,543,363,44,78,24),(11,2,627,911,627,71,132,39),(11,3,363,543,363,44,78,24),(11,4,363,543,363,44,78,24),(11,5,363,543,363,44,78,24),(11,6,363,543,363,44,78,24),(12,1,543,599,275,71,39,44),(12,2,911,1001,470,122,63,71),(12,3,543,599,275,71,39,44),(12,4,543,599,275,71,39,44),(12,5,543,599,275,71,39,44),(12,6,543,599,275,71,39,44);
/*!40000 ALTER TABLE `pa_base_resistance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pa_set_bonus`
--

DROP TABLE IF EXISTS `pa_set_bonus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pa_set_bonus` (
                                `pa_set_bonus_id` int NOT NULL AUTO_INCREMENT,
                                `pa_type_id` int NOT NULL,
                                `description` varchar(255) NOT NULL,
                                `bonus_type` enum('PER_PIECE','FULL_SET') NOT NULL,
                                `pieces_required` tinyint NOT NULL,
                                PRIMARY KEY (`pa_set_bonus_id`),
                                KEY `ix_pa_set_bonus_type` (`pa_type_id`),
                                CONSTRAINT `fk_pa_set_bonus_type` FOREIGN KEY (`pa_type_id`) REFERENCES `pa_type` (`pa_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pa_set_bonus`
--

LOCK TABLES `pa_set_bonus` WRITE;
/*!40000 ALTER TABLE `pa_set_bonus` DISABLE KEYS */;
INSERT INTO `pa_set_bonus` VALUES (1,2,'-2% Damage from Ballistic Weapons','PER_PIECE',1),(2,1,'+100 Carry Weight','FULL_SET',6),(3,4,'20 Poison Damage per Second to Nearby Targets','FULL_SET',6),(4,11,'+150 Poison Resistance, +75 Carry Weight','FULL_SET',6),(5,12,'+25% Fusion Core Duration, +25% AP Regen Rate','FULL_SET',6);
/*!40000 ALTER TABLE `pa_set_bonus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pa_slot`
--

DROP TABLE IF EXISTS `pa_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pa_slot` (
                           `pa_slot_id` int NOT NULL AUTO_INCREMENT,
                           `slot_name` varchar(30) NOT NULL,
                           `slot_group` enum('HEAD','ARM','TORSO','LEG') NOT NULL,
                           `allows_legendary` tinyint(1) NOT NULL DEFAULT '1',
                           PRIMARY KEY (`pa_slot_id`),
                           UNIQUE KEY `uq_pa_slot_slot_name` (`slot_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pa_slot`
--

LOCK TABLES `pa_slot` WRITE;
/*!40000 ALTER TABLE `pa_slot` DISABLE KEYS */;
INSERT INTO `pa_slot` VALUES (1,'Helmet','HEAD',0),(2,'Torso','TORSO',1),(3,'Left Arm','ARM',1),(4,'Right Arm','ARM',1),(5,'Left Leg','LEG',1),(6,'Right Leg','LEG',1);
/*!40000 ALTER TABLE `pa_slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pa_type`
--

DROP TABLE IF EXISTS `pa_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pa_type` (
                           `pa_type_id` int NOT NULL AUTO_INCREMENT,
                           `type_name` varchar(80) NOT NULL,
                           PRIMARY KEY (`pa_type_id`),
                           UNIQUE KEY `uq_pa_type_type_name` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pa_type`
--

LOCK TABLES `pa_type` WRITE;
/*!40000 ALTER TABLE `pa_type` DISABLE KEYS */;
INSERT INTO `pa_type` VALUES (1,'Excavator'),(2,'Hellcat'),(10,'Prototype X-01'),(3,'Raider'),(4,'Strangler'),(5,'T-45'),(6,'T-51b'),(7,'T-60'),(8,'T-65'),(9,'Ultracite'),(11,'Union'),(12,'Vulcan');
/*!40000 ALTER TABLE `pa_type` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_armor_piece`
--

LOCK TABLES `user_armor_piece` WRITE;
/*!40000 ALTER TABLE `user_armor_piece` DISABLE KEYS */;
INSERT INTO `user_armor_piece` VALUES (1,1,12,2,'2026-02-23 03:21:40',NULL,NULL,NULL,NULL),(5,1,5,3,'2026-04-01 19:04:25',19,NULL,NULL,NULL),(6,1,5,3,'2026-04-01 21:23:14',19,NULL,NULL,NULL),(7,1,5,3,'2026-04-02 00:14:05',19,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_armor_piece` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_pa_frame`
--

DROP TABLE IF EXISTS `user_pa_frame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_pa_frame` (
                                 `user_pa_frame_id` int NOT NULL AUTO_INCREMENT,
                                 `user_id` int NOT NULL,
                                 `frame_name` varchar(80) DEFAULT NULL,
                                 PRIMARY KEY (`user_pa_frame_id`),
                                 KEY `ix_user_pa_frame_user` (`user_id`),
                                 CONSTRAINT `fk_user_pa_frame_user` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_pa_frame`
--

LOCK TABLES `user_pa_frame` WRITE;
/*!40000 ALTER TABLE `user_pa_frame` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_pa_frame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_pa_piece`
--

DROP TABLE IF EXISTS `user_pa_piece`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_pa_piece` (
                                 `user_pa_piece_id` int NOT NULL AUTO_INCREMENT,
                                 `user_id` int NOT NULL,
                                 `pa_type_id` int NOT NULL,
                                 `pa_slot_id` int NOT NULL,
                                 `user_pa_frame_id` int DEFAULT NULL,
                                 `star1_effect_id` int DEFAULT NULL,
                                 `star2_effect_id` int DEFAULT NULL,
                                 `star3_effect_id` int DEFAULT NULL,
                                 `star4_effect_id` int DEFAULT NULL,
                                 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`user_pa_piece_id`),
                                 UNIQUE KEY `uq_pa_frame_slot` (`user_pa_frame_id`,`pa_slot_id`),
                                 KEY `ix_upap_user` (`user_id`),
                                 KEY `ix_upap_type` (`pa_type_id`),
                                 KEY `ix_upap_slot` (`pa_slot_id`),
                                 KEY `fk_upap_star1` (`star1_effect_id`),
                                 KEY `fk_upap_star2` (`star2_effect_id`),
                                 KEY `fk_upap_star3` (`star3_effect_id`),
                                 KEY `fk_upap_star4` (`star4_effect_id`),
                                 CONSTRAINT `fk_upap_frame` FOREIGN KEY (`user_pa_frame_id`) REFERENCES `user_pa_frame` (`user_pa_frame_id`),
                                 CONSTRAINT `fk_upap_slot` FOREIGN KEY (`pa_slot_id`) REFERENCES `pa_slot` (`pa_slot_id`),
                                 CONSTRAINT `fk_upap_star1` FOREIGN KEY (`star1_effect_id`) REFERENCES `legendary_effect` (`legendary_effect_id`),
                                 CONSTRAINT `fk_upap_star2` FOREIGN KEY (`star2_effect_id`) REFERENCES `legendary_effect` (`legendary_effect_id`),
                                 CONSTRAINT `fk_upap_star3` FOREIGN KEY (`star3_effect_id`) REFERENCES `legendary_effect` (`legendary_effect_id`),
                                 CONSTRAINT `fk_upap_star4` FOREIGN KEY (`star4_effect_id`) REFERENCES `legendary_effect` (`legendary_effect_id`),
                                 CONSTRAINT `fk_upap_type` FOREIGN KEY (`pa_type_id`) REFERENCES `pa_type` (`pa_type_id`),
                                 CONSTRAINT `fk_upap_user` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_pa_piece`
--

LOCK TABLES `user_pa_piece` WRITE;
/*!40000 ALTER TABLE `user_pa_piece` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_pa_piece` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-05 11:17:39
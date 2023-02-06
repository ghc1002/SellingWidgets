-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: sellingwidgets
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `appliance_blender`
--

DROP TABLE IF EXISTS `appliance_blender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appliance_blender` (
  `id` bigint NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `capacity` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK97qtpnsygql0c6e3gj8qiqown` FOREIGN KEY (`id`) REFERENCES `widget_appliance` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appliance_blender`
--

LOCK TABLES `appliance_blender` WRITE;
/*!40000 ALTER TABLE `appliance_blender` DISABLE KEYS */;
/*!40000 ALTER TABLE `appliance_blender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appliance_dryers`
--

DROP TABLE IF EXISTS `appliance_dryers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appliance_dryers` (
  `brand` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK62shioijnswnm4dhsontnbvsf` FOREIGN KEY (`id`) REFERENCES `widget_appliance` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appliance_dryers`
--

LOCK TABLES `appliance_dryers` WRITE;
/*!40000 ALTER TABLE `appliance_dryers` DISABLE KEYS */;
INSERT INTO `appliance_dryers` VALUES ('old','old','old',39),('LG','Steel','1234',165);
/*!40000 ALTER TABLE `appliance_dryers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appliance_microwave`
--

DROP TABLE IF EXISTS `appliance_microwave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appliance_microwave` (
  `brand` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKiw36pktb7m8ao1g7npiu7tn8b` FOREIGN KEY (`id`) REFERENCES `widget_appliance` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appliance_microwave`
--

LOCK TABLES `appliance_microwave` WRITE;
/*!40000 ALTER TABLE `appliance_microwave` DISABLE KEYS */;
INSERT INTO `appliance_microwave` VALUES ('new','new','new',41),('N/A','N/A','N/A',69),('LG','aluminum','Microwave',154);
/*!40000 ALTER TABLE `appliance_microwave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appliance_refrigerator`
--

DROP TABLE IF EXISTS `appliance_refrigerator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appliance_refrigerator` (
  `brand` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKsq4ah0l2ofavpslqmn8uya6e9` FOREIGN KEY (`id`) REFERENCES `widget_appliance` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appliance_refrigerator`
--

LOCK TABLES `appliance_refrigerator` WRITE;
/*!40000 ALTER TABLE `appliance_refrigerator` DISABLE KEYS */;
INSERT INTO `appliance_refrigerator` VALUES ('new','new','new',44);
/*!40000 ALTER TABLE `appliance_refrigerator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appliance_washers`
--

DROP TABLE IF EXISTS `appliance_washers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appliance_washers` (
  `brand` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKu318x22prpyc8s8nl3ip4syc` FOREIGN KEY (`id`) REFERENCES `widget_appliance` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appliance_washers`
--

LOCK TABLES `appliance_washers` WRITE;
/*!40000 ALTER TABLE `appliance_washers` DISABLE KEYS */;
/*!40000 ALTER TABLE `appliance_washers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant` (
  `id` bigint NOT NULL,
  `answer1` varchar(220) DEFAULT NULL,
  `answer2` varchar(220) DEFAULT NULL,
  `answer3` varchar(220) DEFAULT NULL,
  `answer4` varchar(220) DEFAULT NULL,
  `application_date` varchar(255) DEFAULT NULL,
  `email` varchar(220) DEFAULT NULL,
  `first_name` varchar(220) DEFAULT NULL,
  `last_name` varchar(220) DEFAULT NULL,
  `phone_number` varchar(220) DEFAULT NULL,
  `role_appliedfor` varchar(220) DEFAULT NULL,
  `reviewed` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant`
--

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_type`
--

DROP TABLE IF EXISTS `card_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_type` (
  `card_type` varchar(255) NOT NULL,
  PRIMARY KEY (`card_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_type`
--

LOCK TABLES `card_type` WRITE;
/*!40000 ALTER TABLE `card_type` DISABLE KEYS */;
INSERT INTO `card_type` VALUES ('American Express'),('Discover'),('Mastercard'),('Visa');
/*!40000 ALTER TABLE `card_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `condition`
--

DROP TABLE IF EXISTS `condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `condition` (
  `rating` bigint NOT NULL,
  `descriptor` varchar(255) NOT NULL,
  PRIMARY KEY (`rating`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `condition`
--

LOCK TABLES `condition` WRITE;
/*!40000 ALTER TABLE `condition` DISABLE KEYS */;
/*!40000 ALTER TABLE `condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direct_deposit_details`
--

DROP TABLE IF EXISTS `direct_deposit_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direct_deposit_details` (
  `id` bigint NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `accountholder_name` varchar(255) DEFAULT NULL,
  `routing_number` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direct_deposit_details`
--

LOCK TABLES `direct_deposit_details` WRITE;
/*!40000 ALTER TABLE `direct_deposit_details` DISABLE KEYS */;
INSERT INTO `direct_deposit_details` VALUES (63,'$2a$10$M5k.Kf9dVVkA5Ffny3yLG.tUgBVRwWJJFLH23dGSjWgLuM2R.UeUW','$2a$10$j4pV8Lx0tNFCgZKrNqK5EuTqahJM6rXW0HscUphZp4s.cXld42Qmi','$2a$10$UaL5SO6oNcjaG0NHtXxIMu4R35LQQJ2.egzohr6KKoep8AoNmY2jC','$2a$10$r/LZq70b/2zIjswVUxQKNOyvg9dVh1tvQACuJIDOzytqRbSKTdH3m');
/*!40000 ALTER TABLE `direct_deposit_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electronics_computers`
--

DROP TABLE IF EXISTS `electronics_computers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electronics_computers` (
  `gpu` varchar(255) DEFAULT NULL,
  `memory` varchar(255) DEFAULT NULL,
  `processor` varchar(255) DEFAULT NULL,
  `storage` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKpd8va376mhe3dgbod6m1e8utq` FOREIGN KEY (`id`) REFERENCES `widget_electronics` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electronics_computers`
--

LOCK TABLES `electronics_computers` WRITE;
/*!40000 ALTER TABLE `electronics_computers` DISABLE KEYS */;
INSERT INTO `electronics_computers` VALUES ('None','8','AMD','50GB',35),('Integrated','12','i7','500GB',65);
/*!40000 ALTER TABLE `electronics_computers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electronics_video_games`
--

DROP TABLE IF EXISTS `electronics_video_games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electronics_video_games` (
  `console` varchar(255) DEFAULT NULL,
  `developer` varchar(255) DEFAULT NULL,
  `item_condition` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK9x0k5wp82of0shjn6lc6q82oe` FOREIGN KEY (`id`) REFERENCES `widget_electronics` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electronics_video_games`
--

LOCK TABLES `electronics_video_games` WRITE;
/*!40000 ALTER TABLE `electronics_video_games` DISABLE KEYS */;
INSERT INTO `electronics_video_games` VALUES ('ps2','Insomniac','good','Ratchet and clank',37),('nintendo64','nintendo','mint','Mario 64',143);
/*!40000 ALTER TABLE `electronics_video_games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (191);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lawn_care_lawn_mower`
--

DROP TABLE IF EXISTS `lawn_care_lawn_mower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lawn_care_lawn_mower` (
  `blade_width` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `power_source` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK7qer5d96vq6emhe5m4g426oup` FOREIGN KEY (`id`) REFERENCES `widget_lawn_care` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lawn_care_lawn_mower`
--

LOCK TABLES `lawn_care_lawn_mower` WRITE;
/*!40000 ALTER TABLE `lawn_care_lawn_mower` DISABLE KEYS */;
INSERT INTO `lawn_care_lawn_mower` VALUES ('12 inches','yamaha','electric',48),('12 inches','yamaha','electric',49);
/*!40000 ALTER TABLE `lawn_care_lawn_mower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `market_listing`
--

DROP TABLE IF EXISTS `market_listing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `market_listing` (
  `id` bigint NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `price_per_item` decimal(10,2) DEFAULT NULL,
  `qty_available` bigint NOT NULL,
  `seller_id` bigint DEFAULT NULL,
  `widget_sold_id` bigint DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj77ex4y8ywq29uphgbf1o5ixy` (`seller_id`),
  KEY `FKarx832bifmicpnuhv8mov8uhx` (`widget_sold_id`),
  CONSTRAINT `FKarx832bifmicpnuhv8mov8uhx` FOREIGN KEY (`widget_sold_id`) REFERENCES `widget` (`id`),
  CONSTRAINT `FKj77ex4y8ywq29uphgbf1o5ixy` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `market_listing`
--

LOCK TABLES `market_listing` WRITE;
/*!40000 ALTER TABLE `market_listing` DISABLE KEYS */;
INSERT INTO `market_listing` VALUES (3,_binary '',500.00,40,1,2,NULL),(5,_binary '',50.20,50,1,4,NULL),(7,_binary '',70.00,27,1,6,NULL),(25,_binary '',10.00,410,20,24,NULL),(36,_binary '\0',250.00,0,34,35,'34oldmac.jpeg'),(38,_binary '\0',20.00,0,34,37,'34ratechetandclank.jpg'),(40,_binary '\0',500.00,1,34,39,'34broken dryer.jpeg'),(42,_binary '\0',50.00,1,34,41,'34brand new microwave.jpeg'),(45,_binary '\0',500.00,1,43,44,'43new fridge.jpeg'),(47,_binary '\0',2000.00,0,43,46,'43oldford.jpeg'),(49,_binary '\0',300.00,1,43,48,'43teslamower.jpeg'),(66,_binary '\0',800.00,20,1,65,'1surface-laptop-4-4-625x417-c.jpg'),(70,_binary '\0',40.00,80,1,69,'1surface-laptop-4-4-625x417-c.jpg'),(144,_binary '\0',50.00,2,1,143,'11mario64box.jpg'),(155,_binary '\0',50.00,1,1,154,'1microwave.jpeg'),(166,_binary '\0',500.00,25,1,165,'1dryer.jpeg');
/*!40000 ALTER TABLE `market_listing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` bigint NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `msg_date` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `owner_id` bigint DEFAULT NULL,
  `receiver_id` bigint DEFAULT NULL,
  `is_ticket` bit(1) DEFAULT NULL,
  `reviewer_name` varchar(255) DEFAULT NULL,
  `spam_reporter` varchar(255) DEFAULT NULL,
  `ticket_category` varchar(255) DEFAULT NULL,
  `user_feedback` varchar(255) DEFAULT NULL,
  `trash_owner_id` bigint DEFAULT NULL,
  `date_sent_to_trash` datetime(6) DEFAULT NULL,
  `expire_date` datetime(6) DEFAULT NULL,
  `trash_owner_receiver_id` bigint DEFAULT NULL,
  `date_sent_to_trash_owner` datetime(6) DEFAULT NULL,
  `date_sent_to_trash_receiver` datetime(6) DEFAULT NULL,
  `expire_date_owner` datetime(6) DEFAULT NULL,
  `expire_date_receiver` datetime(6) DEFAULT NULL,
  `is_admin_ticket` bit(1) DEFAULT NULL,
  `is_resolved` bit(1) DEFAULT NULL,
  `is_security_ticket` bit(1) DEFAULT NULL,
  `is_tech_ticket` bit(1) DEFAULT NULL,
  `offender` varchar(255) DEFAULT NULL,
  `my_spam_id` bigint DEFAULT NULL,
  `my_trash_id` bigint DEFAULT NULL,
  `old_owner_id` bigint DEFAULT NULL,
  `old_receiver_id` bigint DEFAULT NULL,
  `spam_owner_id` bigint DEFAULT NULL,
  `spam_owner_receiver_id` bigint DEFAULT NULL,
  `message_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpxb0wq1oxjlpdr5hsqr4fmblp` (`owner_id`),
  KEY `FK86f0kc2mt26ifwupnivu6v8oa` (`receiver_id`),
  KEY `FKqs3lfc2j6fe50qcu1yhp8yjpo` (`trash_owner_id`),
  KEY `FK6hr6tvmu58wie6gc4mtce78va` (`trash_owner_receiver_id`),
  KEY `FK9w9h5yjm1isbuvlbop9be1o83` (`my_spam_id`),
  KEY `FKhssxnw3vh00i999m4vy29nqum` (`my_trash_id`),
  KEY `FKm84dhk0gbpqej2vhdm8jbhr1r` (`old_owner_id`),
  KEY `FKddi7a9kwgxu54m4n1kj9kj3eg` (`old_receiver_id`),
  KEY `FK1jvd8cq0poxfgqhps169lbesm` (`spam_owner_id`),
  KEY `FK7c2fb44ldgo899ryrg5v6t966` (`spam_owner_receiver_id`),
  CONSTRAINT `FK1jvd8cq0poxfgqhps169lbesm` FOREIGN KEY (`spam_owner_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK6hr6tvmu58wie6gc4mtce78va` FOREIGN KEY (`trash_owner_receiver_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK7c2fb44ldgo899ryrg5v6t966` FOREIGN KEY (`spam_owner_receiver_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK86f0kc2mt26ifwupnivu6v8oa` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK9w9h5yjm1isbuvlbop9be1o83` FOREIGN KEY (`my_spam_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKddi7a9kwgxu54m4n1kj9kj3eg` FOREIGN KEY (`old_receiver_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKhssxnw3vh00i999m4vy29nqum` FOREIGN KEY (`my_trash_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKm84dhk0gbpqej2vhdm8jbhr1r` FOREIGN KEY (`old_owner_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKpxb0wq1oxjlpdr5hsqr4fmblp` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqs3lfc2j6fe50qcu1yhp8yjpo` FOREIGN KEY (`trash_owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (131,'<no content>','04/25 05:42','userName','userName','Hello',NULL,NULL,_binary '',NULL,'userName','Spam','none',NULL,NULL,NULL,NULL,'2022-04-25 05:43:07.315081',NULL,NULL,NULL,_binary '\0',_binary '\0',_binary '\0',_binary '\0','userName',1,NULL,1,1,1,NULL,NULL),(133,'<no content>','04/25 05:42','userName','userName','Hello',NULL,NULL,_binary '',NULL,'userName','Spam','none',NULL,NULL,NULL,NULL,'2022-04-25 05:42:37.252601',NULL,NULL,NULL,_binary '\0',_binary '\0',_binary '\0',_binary '\0','userName',1,NULL,1,1,1,NULL,NULL),(135,'howdy','04/25 05:42','userName','userName','test',NULL,NULL,_binary '',NULL,'userName','Spam','none',NULL,NULL,NULL,NULL,'2022-04-25 05:42:37.268559',NULL,NULL,NULL,_binary '\0',_binary '\0',_binary '\0',_binary '\0','userName',1,NULL,1,1,1,NULL,NULL),(163,'hello there','04/25 16:09','testAcc','userName','buy item',1,34,_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(182,'<no content>','04/25 05:42','userName','userName','Hello',NULL,NULL,_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2022-04-25 05:42:50.903531','2022-04-25 05:42:50.903531','2022-05-25 05:42:50.903531','2022-05-25 05:42:50.903531',_binary '\0',_binary '\0',_binary '\0',_binary '\0',NULL,NULL,NULL,1,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_details`
--

DROP TABLE IF EXISTS `payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_details` (
  `id` bigint NOT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `card_type` varchar(255) DEFAULT NULL,
  `cardholder_name` varchar(255) DEFAULT NULL,
  `expiration_date` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `security_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_details`
--

LOCK TABLES `payment_details` WRITE;
/*!40000 ALTER TABLE `payment_details` DISABLE KEYS */;
INSERT INTO `payment_details` VALUES (50,'$2a$10$V6Es7v/zAtNiPQD1IQBgpeAy0pcfUu5Zd6KmManUA/99fDKGeFhea','$2a$10$TC40nOHfHJnqiJgnZ2ii3OfOzvLbifWLugNV4TgCOfv0JdAu2DwsC','$2a$10$3Rc4rNvU.5e5ElQr7XL1T.9Z70thL4LRPdBG070C8uEYv9VksuO42','$2a$10$9a3Gpc.2dq0.Qs/9A6nmT.QfJhxeN1j2yw6QXo3kyQ8D/XkvS26Hy','$2a$10$mZS8w.MH3EaJAlqgzxbxTuZIBm3iLlfvHeNv7OV6oNcE6MCnWkh5W','$2a$10$7SXgimkkh4.ntDwktSO3MeSsEgyqjP/MitoCbCypJExQr/BD9vfXm');
/*!40000 ALTER TABLE `payment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paypal`
--

DROP TABLE IF EXISTS `paypal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paypal` (
  `id` bigint NOT NULL,
  `paypal_login` varchar(255) DEFAULT NULL,
  `paypal_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paypal`
--

LOCK TABLES `paypal` WRITE;
/*!40000 ALTER TABLE `paypal` DISABLE KEYS */;
INSERT INTO `paypal` VALUES (71,'$2a$10$kVZYBQuU.RQXG6i6Ux6orOgVmj2kxopb/mshmpMhvlDCP08rd0NnW','$2a$10$hPm6kOLKFNjU6VGwK/mjb.alUX7k2WtqCfs4ge0lQ2SDS6Zmz0mxu'),(83,'$2a$10$auAOmAJTwwIb/swpt0wI6.paLALzoMzRzj0xrKvR5.cHuWOtsvd3u','$2a$10$SvXl9P1wUct6dF68U7XK7uhL1PMXM9svmSX/ga5JhXG2j4uuTbI1.');
/*!40000 ALTER TABLE `paypal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_rating`
--

DROP TABLE IF EXISTS `seller_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seller_rating` (
  `id` bigint NOT NULL,
  `rating_name` varchar(255) DEFAULT NULL,
  `max_percent` float NOT NULL,
  `min_percent` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_rating`
--

LOCK TABLES `seller_rating` WRITE;
/*!40000 ALTER TABLE `seller_rating` DISABLE KEYS */;
INSERT INTO `seller_rating` VALUES (500,'Very Poor',0.2,0),(501,'Poor',0.4,0.2),(502,'Mixed',0.6,0.4),(503,'Good',0.8,0.6),(504,'Very Good',1,0.8);
/*!40000 ALTER TABLE `seller_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping` (
  `id` bigint NOT NULL,
  `arrival_date` date DEFAULT NULL,
  `carrier` varchar(255) DEFAULT NULL,
  `shipping_date` date DEFAULT NULL,
  `transaction_id` bigint DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbabhan3c6w96cl51g2ff39vky` (`transaction_id`),
  KEY `FKo5h10l5ml7fiwusifytld6sad` (`address_id`),
  CONSTRAINT `FKbabhan3c6w96cl51g2ff39vky` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`),
  CONSTRAINT `FKo5h10l5ml7fiwusifytld6sad` FOREIGN KEY (`address_id`) REFERENCES `shipping_address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping`
--

LOCK TABLES `shipping` WRITE;
/*!40000 ALTER TABLE `shipping` DISABLE KEYS */;
INSERT INTO `shipping` VALUES (55,'2022-03-30','USPS','2022-03-23',56,54),(75,NULL,NULL,NULL,76,74),(80,NULL,NULL,NULL,81,79),(87,'2022-04-30','USPS','2022-04-24',88,86),(160,NULL,NULL,NULL,161,159);
/*!40000 ALTER TABLE `shipping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_address`
--

DROP TABLE IF EXISTS `shipping_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_address` (
  `id` bigint NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `state_state_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1dj2u94ecnqf3xiibfd9lp4lx` (`state_state_name`),
  CONSTRAINT `FK1dj2u94ecnqf3xiibfd9lp4lx` FOREIGN KEY (`state_state_name`) REFERENCES `state_details` (`state_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_address`
--

LOCK TABLES `shipping_address` WRITE;
/*!40000 ALTER TABLE `shipping_address` DISABLE KEYS */;
INSERT INTO `shipping_address` VALUES (54,'05212','Jacob','152 Street','Arkansas'),(74,'55442','Jacob','1st Road','Arkansas'),(79,'54667','Jacob','1st Road','Hawaii'),(86,'43415','new user','2nd Road','California'),(91,'12345','fdsa','fdas','Alabama'),(159,'16057','Billy Bob','1 Morrow Way Slippery Rock','Pennsylvania');
/*!40000 ALTER TABLE `shipping_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state_details`
--

DROP TABLE IF EXISTS `state_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `state_details` (
  `state_name` varchar(255) NOT NULL,
  `sales_tax_rate` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`state_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state_details`
--

LOCK TABLES `state_details` WRITE;
/*!40000 ALTER TABLE `state_details` DISABLE KEYS */;
INSERT INTO `state_details` VALUES ('Alabama',4.00),('Alaska',0.00),('Arizona',5.60),('Arkansas',6.50),('California',7.25),('Colorado',2.90),('Connecticut',6.35),('Delaware',0.00),('Florida',6.00),('Georgia',4.00),('Hawaii',4.00),('Idaho',6.00),('Illinois',6.25),('Indiana',7.00),('Iowa',6.00),('Kansas',6.50),('Kentucky',6.00),('Louisiana',4.45),('Maine',5.50),('Maryland',6.00),('Massachusetts',6.25),('Michigan',6.00),('Minnesota',6.88),('Mississippi',7.00),('Missouri',4.23),('Montana',0.00),('Nebraska',5.50),('Nevada',6.85),('New Hampshire',0.00),('New Jersey',6.63),('New Mexico',5.13),('New York',4.00),('North Carolina',4.75),('North Dakota',5.00),('Ohio',5.75),('Oklahoma',4.50),('Oregon',0.00),('Pennsylvania',6.00),('Rhode Island',7.00),('South Carolina',6.00),('South Dakota',4.50),('Tennessee',7.00),('Texas',6.25),('Utah',5.95),('Vermont',6.00),('Virginia',5.30),('Washington',6.50),('West Virginia',6.00),('Wisconsin',5.00),('Wyoming',4.00);
/*!40000 ALTER TABLE `state_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistics`
--

DROP TABLE IF EXISTS `statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statistics` (
  `id` bigint NOT NULL,
  `category` int DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `hour` int NOT NULL,
  `value` float NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistics`
--

LOCK TABLES `statistics` WRITE;
/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;
INSERT INTO `statistics` VALUES (60,3,'2022-04-24 23:09:08.553963',23,1,'userName logged in'),(61,3,'2022-04-24 23:09:18.172234',23,1,'jacobh logged in'),(62,3,'2022-04-24 23:09:51.626640',23,1,'userName logged in'),(67,3,'2022-04-24 23:17:04.969431',23,1,'jacobh logged in'),(68,3,'2022-04-24 23:17:13.483161',23,1,'userName logged in'),(72,0,'2022-04-24 23:21:53.142007',23,250,'1 old mac(s) were sold for a total of: $250.0'),(73,1,'2022-04-24 23:21:53.142007',23,1,'old mac: was sold'),(77,0,'2022-04-24 23:22:33.453194',23,20,'1 Ratchet and clank(s) were sold for a total of: $20.0'),(78,1,'2022-04-24 23:22:33.453194',23,1,'Ratchet and clank: was sold'),(82,3,'2022-04-24 23:22:58.032446',23,1,'jacobh logged in'),(84,0,'2022-04-24 23:23:53.456184',23,800,'20 Microwave(s) were sold for a total of: $800.0'),(85,1,'2022-04-24 23:23:53.456184',23,1,'Microwave: was sold'),(89,0,'2022-04-24 23:24:30.695826',23,500,'1 new fridge(s) were sold for a total of: $500.0'),(90,1,'2022-04-24 23:24:30.695826',23,1,'new fridge: was sold'),(94,3,'2022-04-24 23:25:33.337768',23,1,'userName logged in'),(95,3,'2022-04-24 23:28:48.879033',23,1,'jacobh logged in'),(96,3,'2022-04-24 23:29:32.654930',23,1,'jacobh logged in'),(97,3,'2022-04-24 23:29:45.955869',23,1,'userName logged in'),(98,3,'2022-04-24 23:29:52.841450',23,1,'jacobh logged in'),(99,3,'2022-04-24 23:29:59.835775',23,1,'userName logged in'),(100,3,'2022-04-24 23:33:11.226786',23,1,'jacobh logged in'),(101,3,'2022-04-24 23:33:27.075162',23,1,'userName logged in'),(102,3,'2022-04-24 23:36:38.305220',23,1,'userName logged in'),(103,3,'2022-04-24 23:39:40.713885',23,1,'jacobh logged in'),(104,3,'2022-04-25 05:09:51.369693',5,1,'useradminwidget logged in'),(105,2,'2022-04-25 05:10:22.380454',5,1,'Account with username: usercustomerwidget was created'),(107,2,'2022-04-25 05:10:42.521861',5,1,'Account with username: usertechwidget was created'),(109,2,'2022-04-25 05:11:06.956125',5,1,'Account with username: userhiringwidget was created'),(111,2,'2022-04-25 05:11:26.734849',5,1,'Account with username: usersaleswidget was created'),(113,2,'2022-04-25 05:11:47.696475',5,1,'Account with username: usersecuritywidget was created'),(115,3,'2022-04-25 05:15:32.097605',5,1,'useradminwidget logged in'),(116,3,'2022-04-25 05:15:48.483730',5,1,'useradminwidget logged in'),(119,2,'2022-04-25 05:24:56.419927',5,1,'Account with username: timadminwidget2 was created'),(121,2,'2022-04-25 05:26:15.227354',5,1,'Account with username: timuser was created'),(123,3,'2022-04-25 05:27:14.682885',5,1,'timuser logged in'),(126,3,'2022-04-25 05:38:23.234024',5,1,'usersaleswidget logged in'),(127,3,'2022-04-25 05:41:54.570823',5,1,'userName logged in'),(128,5,'2022-04-25 05:42:08.593940',5,1,'userName sent a message to: userName'),(130,5,'2022-04-25 05:42:15.823626',5,1,'userName sent a message to: userName'),(132,5,'2022-04-25 05:42:20.553705',5,1,'userName sent a message to: userName'),(134,5,'2022-04-25 05:42:28.881710',5,1,'userName sent a message to: userName'),(142,3,'2022-04-25 06:51:06.572558',6,1,'userName logged in'),(145,3,'2022-04-25 12:51:00.441625',12,1,'userName logged in'),(147,3,'2022-04-25 15:46:57.636544',15,1,'userName logged in'),(148,3,'2022-04-25 15:49:29.426450',15,1,'userName logged in'),(149,3,'2022-04-25 15:49:35.084905',15,1,'userName logged in'),(150,3,'2022-04-25 15:51:27.159338',15,1,'userName logged in'),(151,3,'2022-04-25 15:56:09.035482',15,1,'userName logged in'),(152,3,'2022-04-25 15:57:22.437542',15,1,'userName logged in'),(153,3,'2022-04-25 15:57:27.433463',15,1,'userName logged in'),(156,3,'2022-04-25 16:02:02.645956',16,1,'userName logged in'),(157,0,'2022-04-25 16:06:28.594888',16,2000,'1 old ford(s) were sold for a total of: $2000.0'),(158,1,'2022-04-25 16:06:28.594888',16,1,'old ford: was sold'),(162,5,'2022-04-25 16:09:06.593906',16,1,'userName sent a message to: testAcc'),(164,3,'2022-04-25 16:10:17.686348',16,1,'userName logged in'),(167,3,'2022-04-25 16:18:00.806168',16,1,'userName logged in'),(168,3,'2022-04-25 16:18:04.998566',16,1,'userName logged in'),(169,3,'2022-04-25 16:20:33.070467',16,1,'userName logged in'),(170,3,'2022-04-25 16:21:04.426163',16,1,'userName logged in'),(171,3,'2022-04-25 16:21:08.697657',16,1,'userName logged in'),(172,3,'2022-04-25 16:22:38.436909',16,1,'userName logged in'),(173,3,'2022-04-25 16:23:27.491320',16,1,'userName logged in'),(174,3,'2022-04-25 16:41:30.360903',16,1,'userName logged in'),(175,3,'2022-04-25 16:43:53.901467',16,1,'userName logged in'),(176,3,'2022-04-25 16:43:58.160599',16,1,'userName logged in'),(177,3,'2022-04-25 16:44:57.419965',16,1,'userName logged in'),(178,3,'2022-04-25 16:45:39.250830',16,1,'userName logged in'),(179,3,'2022-04-25 16:46:26.019067',16,1,'userName logged in'),(180,3,'2022-09-10 20:03:17.135480',20,1,'useradminwidget logged in'),(181,3,'2022-09-10 20:04:08.161176',20,1,'userName logged in'),(183,3,'2022-09-10 20:06:46.854370',20,1,'userName logged in'),(184,3,'2022-10-01 20:49:33.340090',20,1,'userName logged in'),(185,3,'2022-10-19 22:43:17.449114',22,1,'userName logged in'),(186,3,'2022-10-19 23:20:47.647511',23,1,'userName logged in'),(187,3,'2022-10-20 00:15:39.793020',0,1,'userName logged in'),(188,3,'2022-10-20 00:16:51.028041',0,1,'userName logged in'),(189,3,'2022-10-20 00:27:06.907368',0,1,'userName logged in'),(190,3,'2022-11-02 03:04:16.748764',3,1,'userName logged in');
/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategory` (
  `name` varchar(255) NOT NULL,
  `parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` bigint NOT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `updated_at` varchar(255) DEFAULT NULL,
  `assigned_to_id` bigint DEFAULT NULL,
  `created_by_id` bigint DEFAULT NULL,
  `assigned_at` varchar(255) DEFAULT NULL,
  `resolved_at` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfatbq9qkusl0yex88521x9fk6` (`assigned_to_id`),
  KEY `FKbot3adlibnkqwfrh38v72jjo1` (`created_by_id`),
  CONSTRAINT `FKbot3adlibnkqwfrh38v72jjo1` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfatbq9qkusl0yex88521x9fk6` FOREIGN KEY (`assigned_to_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_message`
--

DROP TABLE IF EXISTS `ticket_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_message` (
  `ticket_id` bigint NOT NULL,
  `message_id` bigint NOT NULL,
  KEY `FK3iu6bcuba9nivwynd1qk8vwf1` (`message_id`),
  KEY `FKmk0m6f9h58ho377ycgdocotwe` (`ticket_id`),
  CONSTRAINT `FK3iu6bcuba9nivwynd1qk8vwf1` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  CONSTRAINT `FKmk0m6f9h58ho377ycgdocotwe` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_message`
--

LOCK TABLES `ticket_message` WRITE;
/*!40000 ALTER TABLE `ticket_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL,
  `purchase_date` date DEFAULT NULL,
  `qty_bought` int NOT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `buyer_id` bigint DEFAULT NULL,
  `market_listing_id` bigint DEFAULT NULL,
  `shipping_entry_id` bigint DEFAULT NULL,
  `seller_profit` decimal(10,2) DEFAULT NULL,
  `total_price_after_taxes` decimal(10,2) DEFAULT NULL,
  `total_price_before_taxes` decimal(10,2) DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKosd6qqlkyqp8gk4gjisggqev0` (`buyer_id`),
  KEY `FKmoxh3homji0gvlfcw51kuc5w5` (`market_listing_id`),
  KEY `FKaasul5qbkvi7jujotj7rgst7l` (`shipping_entry_id`),
  KEY `FKs37irexq9hyvl7pqyqya2i0dn` (`seller_id`),
  CONSTRAINT `FKaasul5qbkvi7jujotj7rgst7l` FOREIGN KEY (`shipping_entry_id`) REFERENCES `shipping` (`id`),
  CONSTRAINT `FKmoxh3homji0gvlfcw51kuc5w5` FOREIGN KEY (`market_listing_id`) REFERENCES `market_listing` (`id`),
  CONSTRAINT `FKosd6qqlkyqp8gk4gjisggqev0` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKs37irexq9hyvl7pqyqya2i0dn` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (56,'2022-03-06',50,NULL,1,25,55,NULL,532.50,500.00,20),(76,'2022-04-24',1,NULL,1,36,75,226.31,266.25,250.00,34),(81,'2022-04-24',1,NULL,1,38,80,17.68,20.80,20.00,34),(88,'2022-04-24',20,NULL,20,70,87,729.30,858.00,800.00,1),(161,'2022-04-25',1,NULL,1,47,160,1802.00,2120.00,2000.00,43);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `creation_date` varchar(255) DEFAULT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `disabled_account` bit(1) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_verification` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `user_description` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `seller_rating_id` bigint DEFAULT NULL,
  `user_role_id` bigint DEFAULT NULL,
  `payment_details_id` bigint DEFAULT NULL,
  `max` int NOT NULL,
  `passwordconf` varchar(30) DEFAULT NULL,
  `sender` tinyblob,
  `direct_deposit_details_id` bigint DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `secret1` varchar(255) DEFAULT NULL,
  `secret2` varchar(255) DEFAULT NULL,
  `secret3` varchar(255) DEFAULT NULL,
  `user_secret1` varchar(200) DEFAULT NULL,
  `user_secret2` varchar(200) DEFAULT NULL,
  `user_secret3` varchar(200) DEFAULT NULL,
  `user_image` varchar(255) DEFAULT NULL,
  `paypal_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKktx9d9kb7i5wgkeutmh361fjl` (`seller_rating_id`),
  KEY `FKh2wc2dtfdo8maylne7mgubowq` (`user_role_id`),
  KEY `FKpugbk3182ai5tvq8wi0ae2evv` (`payment_details_id`),
  KEY `FK8gx4h2hmvvmma3pe1vfg7etk3` (`direct_deposit_details_id`),
  KEY `FKhufqq16v6gpibkrlbie53nd84` (`paypal_id`),
  CONSTRAINT `FK8gx4h2hmvvmma3pe1vfg7etk3` FOREIGN KEY (`direct_deposit_details_id`) REFERENCES `direct_deposit_details` (`id`),
  CONSTRAINT `FKh2wc2dtfdo8maylne7mgubowq` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `FKhufqq16v6gpibkrlbie53nd84` FOREIGN KEY (`paypal_id`) REFERENCES `paypal` (`id`),
  CONSTRAINT `FKktx9d9kb7i5wgkeutmh361fjl` FOREIGN KEY (`seller_rating_id`) REFERENCES `seller_rating` (`id`),
  CONSTRAINT `FKpugbk3182ai5tvq8wi0ae2evv` FOREIGN KEY (`payment_details_id`) REFERENCES `payment_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '\0',_binary '\0','2022-02-20',_binary '\0',_binary '\0','','test@tester.com','fqdqmdyoha',_binary '','$2a$10$yynCjuGntBkkX2m859LnBeYyJ9TS5N6ryrZ2lO.OOBuq5jR1Bzkzq','ROLE_USER','userDeschere','userName',NULL,NULL,50,0,NULL,NULL,63,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1surface-laptop-4-4-625x417-c.jpg',71),(8,_binary '\0',_binary '\0','2022-02-20',_binary '\0',_binary '\0',NULL,'tewatwe@test.com','xowotnxnvu',_binary '','$2a$10$FnrxvrCjxbBjjl5XWI2nCOUpqvTlCp2LVpIsfhbSq0U60B5xuHxRK','ROLE_USER','tewatwaetwedesc','newUser',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,_binary '\0',_binary '\0','2022-02-21',_binary '\0',_binary '\0',NULL,'test@tester.com','gxfoqjzfoa',_binary '','$2a$10$Z1nwcoVJZGu2ZBeZzLdbQeo9/OzpANnt9a8OgwTaCPGdN5J3sk6K6','ROLE_USER','Nicholas','nickd',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,_binary '\0',_binary '\0','2022-02-21',_binary '\0',_binary '\0','Jacob_Tester','test@tester.com','lhglseppzh',_binary '','$2a$10$gDzbpcKSKRDQh6mS.e0hr.vtcfg.oNmg.nd8JJlgiZqKwlF2EwWHy','ROLE_USER','Jacob','userName2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,83),(21,_binary '\0',_binary '\0','2022-02-21',_binary '\0',_binary '\0',NULL,'namer@name.com','ghkkllstnu',_binary '','$2a$10$.pRuDcQzwJxBzLsgjRmTNeqPIJ4Ncc2zSgEhCWvMNg3ZQwQM3rzJW','ROLE_USER','Timothy','timd',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,_binary '\0',_binary '\0','2022-03-06',_binary '\0',_binary '\0',NULL,'mtest9293@gmail.com','zihbbotswb',_binary '','$2a$10$ebFOJs88r6QnskKpSnDYpuG9NaHEno0b2xkjI1worC.MR3AUbDkFO','ROLE_USER','','testAcc',NULL,NULL,NULL,200,'password',_binary '�\�\0ur\0[Ljava.lang.String;�\�V\�\�{G\0\0xp\0\0\0pppppppppppppppppppp',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,_binary '\0',_binary '\0','2022-03-06',_binary '\0',_binary '\0',NULL,'mtest9293@gmail.com','lxbgkkmlva',_binary '','$2a$10$WH1rexv507TIwZ2RCSevf.t7Xj9ks7NdUEzDG4NMp7sfWXDp.P8V.','ROLE_USER','','nickTest',NULL,NULL,NULL,200,'password',_binary '�\�\0ur\0[Ljava.lang.String;�\�V\�\�{G\0\0xp\0\0\0pppppppppppppppppppp',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(58,_binary '\0',_binary '\0','2022-03-07',_binary '\0',_binary '\0',NULL,'tmd1021@sru.edu',NULL,_binary '','$2a$10$YjpI0ak3J9eKKHjlii0heevip93h9NDOtqAxacc0v.D8x2xlgKuJy','ROLE_ADMIN','','useradminwidget',NULL,NULL,NULL,200,'useradmin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(59,_binary '\0',_binary '\0','2022-03-07',_binary '\0',_binary '\0',NULL,'test@test.com',NULL,_binary '','$2a$10$.GndlDgEzXtOTmOYpDHlVO9GSwKbU5QdHZLGg6j9.ar4Ba/H/KnE2','ROLE_USER',NULL,'testuser',NULL,NULL,NULL,200,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(106,_binary '\0',_binary '\0','2022-04-25',_binary '\0',_binary '\0',NULL,'taddamd1021@sru.edu',NULL,_binary '','$2a$10$4rVpuQuJCO7h.ksC36YWp.87jvj5OpHKDsTXgtf4Nb9ei57uSNb9G','ROLE_CUSTOMERSERVICE',NULL,'usercustomerwidget',NULL,NULL,NULL,200,'pethfexgpd',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(108,_binary '\0',_binary '\0','2022-04-25',_binary '\0',_binary '\0',NULL,'tmsddawd1021@sru.edu',NULL,_binary '','$2a$10$2gMfUVv/rmUV8M0cDk5bjOeh8A2j0bQXL3x3TgHHDk8H7UHLab4LS','ROLE_TECHNICALSERVICE',NULL,'usertechwidget',NULL,NULL,NULL,200,'qgghmmftrb',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(110,_binary '\0',_binary '\0','2022-04-25',_binary '\0',_binary '\0',NULL,'tdsdmsdad1021@sru.edu',NULL,_binary '','$2a$10$Oaoq39cwIcN14Ais2oe5xe.1HpLg9rIREn5EXLVfwUIdPAhpGFes2','ROLE_HIRINGMANAGER',NULL,'userhiringwidget',NULL,NULL,NULL,200,'xplsbsuloo',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(112,_binary '\0',_binary '\0','2022-04-25',_binary '\0',_binary '\0',NULL,'tsdmsadwdsdwa1021@sru.edu',NULL,_binary '','$2a$10$PY6jwIQfE5cRJVfUXdxaQOB.1wqqnbmj1.edNMKtUAwdqZ8PALDkC','ROLE_SALES',NULL,'usersaleswidget',NULL,NULL,NULL,200,'ejvemzxkzs',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(114,_binary '\0',_binary '\0','2022-04-25',_binary '\0',_binary '\0',NULL,'tasdwmdawd1021@sru.edu',NULL,_binary '','$2a$10$6Hoouh4QNJ7SthcQCTf3qOLdhMscfoPxDSkXwQjB0u/Yqb7997xZu','ROLE_SECURITY',NULL,'usersecuritywidget',NULL,NULL,NULL,200,'viqxomlryx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(122,_binary '\0',_binary '\0','2022-04-25',_binary '\0',_binary '\0',NULL,'dougherty.tdoc@gmail.com','uygchacaey',_binary '','$2a$10$Conjpd7KR1GQI/Ik2GtguON8G2.9WB1y0hiDEBdy7KyYSkG/ks/IW','ROLE_USER','','timuser',NULL,NULL,NULL,200,'mvarjpzssz',NULL,NULL,'Timothy','Dougherty','+14127124259','+1       USA','What is your mother\'s maiden name?','What is your favorite band?','What is your favorite beverage?','peterson','rush','sprite',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_list`
--

DROP TABLE IF EXISTS `user_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_list` (
  `id` bigint NOT NULL,
  `owner_id` bigint DEFAULT NULL,
  `block_id` bigint DEFAULT NULL,
  `friend_id` bigint DEFAULT NULL,
  `suggested_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcm5v2h26bync5k8lryy4f62gq` (`owner_id`),
  KEY `FKfx13elm6u0iw6ouneqqaco3d2` (`block_id`),
  KEY `FKs9oojc9bse8up7fldfel23mae` (`friend_id`),
  KEY `FKm5th8lhjcqi10jmnat9aj24x4` (`suggested_id`),
  CONSTRAINT `FKcm5v2h26bync5k8lryy4f62gq` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfx13elm6u0iw6ouneqqaco3d2` FOREIGN KEY (`block_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKm5th8lhjcqi10jmnat9aj24x4` FOREIGN KEY (`suggested_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKs9oojc9bse8up7fldfel23mae` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_list`
--

LOCK TABLES `user_list` WRITE;
/*!40000 ALTER TABLE `user_list` DISABLE KEYS */;
INSERT INTO `user_list` VALUES (124,122,NULL,NULL,34),(125,122,NULL,NULL,1),(136,1,NULL,20,NULL),(137,1,NULL,34,NULL),(138,1,59,NULL,NULL),(139,1,NULL,NULL,1),(140,1,NULL,NULL,43),(141,1,NULL,43,NULL),(146,1,NULL,NULL,34);
/*!40000 ALTER TABLE `user_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL,
  `authority_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_car`
--

DROP TABLE IF EXISTS `vehicle_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_car` (
  `make` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `transmission_type` varchar(255) DEFAULT NULL,
  `wheel_drive` varchar(255) DEFAULT NULL,
  `year` int NOT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK90n5vfxpy20afm9pq0upbr97x` FOREIGN KEY (`id`) REFERENCES `widget_vehicles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_car`
--

LOCK TABLES `vehicle_car` WRITE;
/*!40000 ALTER TABLE `vehicle_car` DISABLE KEYS */;
INSERT INTO `vehicle_car` VALUES ('ranger','ford','Automatic','Two Wheel Drive',1980,46);
/*!40000 ALTER TABLE `vehicle_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_lawn_mower`
--

DROP TABLE IF EXISTS `vehicle_lawn_mower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_lawn_mower` (
  `blade_width` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `power_source` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKlkd2yh93olnm87k9in9pony91` FOREIGN KEY (`id`) REFERENCES `widget_vehicles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_lawn_mower`
--

LOCK TABLES `vehicle_lawn_mower` WRITE;
/*!40000 ALTER TABLE `vehicle_lawn_mower` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle_lawn_mower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `widget`
--

DROP TABLE IF EXISTS `widget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `widget` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `sub_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `widget`
--

LOCK TABLES `widget` WRITE;
/*!40000 ALTER TABLE `widget` DISABLE KEYS */;
INSERT INTO `widget` VALUES (2,'desc','name','appliance',NULL,'microwave'),(4,'test','newWidget','appliance',NULL,'dryer'),(6,'desc','newName','appliance',NULL,'washer'),(22,'Bicycle','Jacob\'s Widget','vehicle',NULL,'car'),(24,'Dishwasher Dial','Jacob\'s Widget','appliance',NULL,'washer'),(35,'an old mac','old mac','electronic',NULL,'computer'),(37,'a ps2 game','Ratchet and clank','electronic',NULL,'videoGame'),(39,'dont buy this dryer','old dryer','appliance',NULL,'dryer'),(41,'it mostly works','a new microwave','appliance',NULL,'microwave'),(44,'it keeps things cold','new fridge','appliance',NULL,'fridge'),(46,'its an old truck','old ford','vehicle',NULL,'car'),(48,'its a new mower','new mower','lawnCare',NULL,'lawnMower'),(65,'','Laptop','electronic',NULL,'computer'),(69,'','Microwave','appliance',NULL,'microwave'),(143,'old video game','Mario 64','electronic',NULL,'videoGame'),(154,'just a microwave','microwave','appliance',NULL,'microwave'),(165,'an old old dryer','old dryer','appliance',NULL,'dryer');
/*!40000 ALTER TABLE `widget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `widget_appliance`
--

DROP TABLE IF EXISTS `widget_appliance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `widget_appliance` (
  `color` varchar(255) DEFAULT NULL,
  `height` int NOT NULL,
  `item_condition` varchar(255) DEFAULT NULL,
  `length` int NOT NULL,
  `width` int NOT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKs6kywsj4v0h19eqw2lvuobit` FOREIGN KEY (`id`) REFERENCES `widget` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `widget_appliance`
--

LOCK TABLES `widget_appliance` WRITE;
/*!40000 ALTER TABLE `widget_appliance` DISABLE KEYS */;
INSERT INTO `widget_appliance` VALUES ('green',30,'good',24,36,39),('black',16,'ok',15,15,41),('white',65,'ok',20,37,44),('Blue',20,'Almost new',20,20,69),('White',12,'good',22,12,154),('Black',24,'Moderate',36,36,165);
/*!40000 ALTER TABLE `widget_appliance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `widget_electronics`
--

DROP TABLE IF EXISTS `widget_electronics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `widget_electronics` (
  `id` bigint NOT NULL,
  `entertainment_use` varchar(255) DEFAULT NULL,
  `office_use` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK6tnrbya3jyf2tske9xt2emxac` FOREIGN KEY (`id`) REFERENCES `widget` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `widget_electronics`
--

LOCK TABLES `widget_electronics` WRITE;
/*!40000 ALTER TABLE `widget_electronics` DISABLE KEYS */;
INSERT INTO `widget_electronics` VALUES (35,'1','Yes'),(37,'Yes','Yes'),(65,'1','Yes'),(143,'Yes','No');
/*!40000 ALTER TABLE `widget_electronics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `widget_lawn_care`
--

DROP TABLE IF EXISTS `widget_lawn_care`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `widget_lawn_care` (
  `tool_type` varchar(255) DEFAULT NULL,
  `yard_size` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKtm0et4853ohd7kdqnatqk6lme` FOREIGN KEY (`id`) REFERENCES `widget` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `widget_lawn_care`
--

LOCK TABLES `widget_lawn_care` WRITE;
/*!40000 ALTER TABLE `widget_lawn_care` DISABLE KEYS */;
INSERT INTO `widget_lawn_care` VALUES ('mower','large',48),('mower','large',49);
/*!40000 ALTER TABLE `widget_lawn_care` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `widget_vehicles`
--

DROP TABLE IF EXISTS `widget_vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `widget_vehicles` (
  `item_condition` varchar(255) DEFAULT NULL,
  `terrain` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  `road_safe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKro6rqt19x2tpst839wcgkofjw` FOREIGN KEY (`id`) REFERENCES `widget` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `widget_vehicles`
--

LOCK TABLES `widget_vehicles` WRITE;
/*!40000 ALTER TABLE `widget_vehicles` DISABLE KEYS */;
INSERT INTO `widget_vehicles` VALUES ('Good: Rust Free','Off Road',46,'Yes');
/*!40000 ALTER TABLE `widget_vehicles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `widget_wishlist_entry`
--

DROP TABLE IF EXISTS `widget_wishlist_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `widget_wishlist_entry` (
  `user_id` bigint NOT NULL,
  `widget_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`widget_id`),
  KEY `FKckx8dsi8fcqqsmurdhmpugrk2` (`widget_id`),
  CONSTRAINT `FKckx8dsi8fcqqsmurdhmpugrk2` FOREIGN KEY (`widget_id`) REFERENCES `widget` (`id`),
  CONSTRAINT `FKkfdnao9k2btmv3uusccdot60d` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `widget_wishlist_entry`
--

LOCK TABLES `widget_wishlist_entry` WRITE;
/*!40000 ALTER TABLE `widget_wishlist_entry` DISABLE KEYS */;
INSERT INTO `widget_wishlist_entry` VALUES (8,6);
/*!40000 ALTER TABLE `widget_wishlist_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `widgets_info`
--

DROP TABLE IF EXISTS `widgets_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `widgets_info` (
  `id` bigint NOT NULL,
  `attribute` varchar(255) DEFAULT NULL,
  `sub_category` varchar(255) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  `visible` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `widgets_info`
--

LOCK TABLES `widgets_info` WRITE;
/*!40000 ALTER TABLE `widgets_info` DISABLE KEYS */;
INSERT INTO `widgets_info` VALUES (1,'entertainment_use','computer','widget_electronics',_binary ''),(2,'office_use','computer','widget_electronics',_binary ''),(3,'gpu','computer','electronics_computers',_binary ''),(4,'memory','computer','electronics_computers',_binary ''),(5,'processor','computer','electronics_computers',_binary ''),(6,'storage','computer','electronics_computers',_binary ''),(7,'entertainment_use','videoGame','widget_electronics',_binary ''),(8,'office_use','videoGame','widget_electronics',_binary ''),(9,'console','videoGame','electronics_video_games',_binary ''),(10,'developer','videoGame','electronics_video_games',_binary ''),(11,'item_condition','videoGame','electronics_video_games',_binary ''),(12,'title','videoGame','electronics_video_games',_binary ''),(13,'color','dryer','widget_appliance',_binary ''),(14,'height','dryer','widget_appliance',_binary ''),(15,'item_condition','dryer','widget_appliance',_binary ''),(16,'length','dryer','widget_appliance',_binary ''),(17,'width','dryer','widget_appliance',_binary ''),(18,'brand','dryer','appliance_dryers',_binary ''),(19,'material','dryer','appliance_dryers',_binary ''),(20,'model','dryer','appliance_dryers',_binary ''),(21,'color','blender','widget_appliance',_binary ''),(22,'height','blender','widget_appliance',_binary ''),(23,'item_condition','blender','widget_appliance',_binary ''),(24,'length','blender','widget_appliance',_binary ''),(25,'width','blender','widget_appliance',_binary ''),(26,'brand','blender','appliance_blender',_binary ''),(27,'material','blender','appliance_blender',_binary ''),(28,'model','blender','appliance_blender',_binary ''),(29,'capacity','blender','appliance_blender',_binary ''),(30,'color','microwave','widget_appliance',_binary ''),(31,'height','microwave','widget_appliance',_binary ''),(32,'item_condition','microwave','widget_appliance',_binary ''),(33,'length','microwave','widget_appliance',_binary ''),(34,'width','microwave','widget_appliance',_binary ''),(35,'brand','microwave','appliance_microwave',_binary ''),(36,'material','microwave','appliance_microwave',_binary ''),(37,'model','microwave','appliance_microwave',_binary ''),(38,'color','refrigerator','widget_appliance',_binary ''),(39,'height','refrigerator','widget_appliance',_binary ''),(40,'item_condition','refrigerator','widget_appliance',_binary ''),(41,'length','refrigerator','widget_appliance',_binary ''),(42,'width','refrigerator','widget_appliance',_binary ''),(43,'brand','refrigerator','appliance_refrigerator',_binary ''),(44,'material','refrigerator','appliance_refrigerator',_binary ''),(45,'model','refrigerator','appliance_refrigerator',_binary ''),(46,'color','washer','widget_appliance',_binary ''),(47,'height','washer','widget_appliance',_binary ''),(48,'item_condition','washer','widget_appliance',_binary ''),(49,'length','washer','widget_appliance',_binary ''),(50,'width','washer','widget_appliance',_binary ''),(51,'brand','washer','appliance_washers',_binary ''),(52,'material','washer','appliance_washers',_binary ''),(53,'model','washer','appliance_washers',_binary ''),(54,'item_condition','car','widget_vehicles',_binary ''),(55,'terrain','car','widget_vehicles',_binary ''),(56,'road_safe','car','widget_vehicles',_binary ''),(57,'make','car','vehicle_car',_binary ''),(58,'model','car','vehicle_car',_binary ''),(59,'transmission_type','car','vehicle_car',_binary ''),(60,'wheel_drive','car','vehicle_car',_binary ''),(61,'year','car','vehicle_car',_binary ''),(62,'tool_type','lawnMower','widget_lawn_care',_binary ''),(63,'yard_size','lawnMower','widget_lawn_care',_binary ''),(64,'blade_width','lawnMower','lawn_care_lawn_mower',_binary ''),(65,'brand','lawnMower','lawn_care_lawn_mower',_binary ''),(66,'power_source','lawnMower','lawn_care_lawn_mower',_binary '');
/*!40000 ALTER TABLE `widgets_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-02 10:49:00

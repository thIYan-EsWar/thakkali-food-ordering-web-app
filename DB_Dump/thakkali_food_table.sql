-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: thakkali
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
-- Table structure for table `food_table`
--

DROP TABLE IF EXISTS `food_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_table` (
  `food_id` int NOT NULL AUTO_INCREMENT,
  `manager_id` int NOT NULL,
  `food_name` varchar(100) NOT NULL,
  `food_category` varchar(100) NOT NULL,
  `food_description` varchar(200) NOT NULL,
  `unit_price` float NOT NULL,
  `image_url` varchar(1000) NOT NULL,
  `image_alt` varchar(100) NOT NULL,
  PRIMARY KEY (`food_id`),
  KEY `manager_id_idx` (`manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_table`
--

LOCK TABLES `food_table` WRITE;
/*!40000 ALTER TABLE `food_table` DISABLE KEYS */;
INSERT INTO `food_table` VALUES (3,13,'Chicken briyani','Indian','Steam cooked flavored rice with chicken',150,'#','Briyani'),(5,13,'Mutton briyani','Indian','Steam cooked flavored rice with lamb meet',199,'#','Mutton Briyani'),(7,13,'Fermented Fish curry','Tamilian','Sour, a day fermented fish curry.',359,'#','Fish curry'),(10,13,'Parotta','Tamilian','Oil roasted refined wheat bread',15,'#','Parotta'),(11,13,'Cinthamani Food','Tamilian','Spicy fried chicken',259,'#','Chicken fry'),(14,13,'Fish briyani','Indian','Steam cooked rice with fish fries',229,'#','Fish briyani');
/*!40000 ALTER TABLE `food_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-09 16:40:25

-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: final_happyhouse
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `comment_content` varchar(100) DEFAULT NULL,
  `comment_deal_id` int DEFAULT NULL,
  `comment_user_id` varchar(100) DEFAULT NULL,
  `comment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'하이',1,'ssafy','2021-11-22 12:27:16'),(2,'하이',1,'ssafy','2021-11-22 12:27:18'),(3,'하이',1,'ssafy','2021-11-22 12:27:25'),(4,'하이',1,'ssafy','2021-11-22 12:27:41'),(5,'ssafyddd',1,'ssafy','2021-11-22 12:27:56'),(6,'하이하이',1,'ssafy','2021-11-22 12:35:16'),(7,'하이루',1,'ssafy','2021-11-22 13:00:04'),(8,'dfdffdf',1,'ssafy','2021-11-22 13:03:45'),(9,'df',2,'ssafy','2021-11-22 13:18:26'),(10,'ddddd',2,'ssafy','2021-11-22 13:21:23'),(11,'dfdfd',2,'ssafy','2021-11-22 13:23:11'),(12,'하이',2,'ssafy','2021-11-22 13:26:16'),(13,'안녕',2,'ssafy','2021-11-22 13:28:19'),(14,'이상해',2,'ssafy','2021-11-22 13:31:50'),(15,'ㅈㅂ',2,'ssafy','2021-11-22 13:34:49'),(16,'안녕~',2,'admin','2021-11-23 16:29:22'),(17,'롯데캐슬 너무 멋져요',4,'test','2021-11-24 09:43:31'),(18,'쫌만 깎아주세요 ㅜㅜ',4,'test','2021-11-24 09:43:50'),(19,'쫌만 깎아주세요 ㅜㅜ',4,'test','2021-11-24 09:43:55'),(20,'쫌만 깎아주세요 ㅜㅜ',4,'test','2021-11-24 09:44:03'),(21,'쫌만 깎아주세요 ㅜㅜ',4,'test','2021-11-24 09:44:05'),(22,'dffdf',4,'eunji','2021-11-24 13:16:18'),(23,'sssssss',4,'eunji','2021-11-24 13:16:24'),(24,'sssssss',4,'eunji','2021-11-24 13:16:27'),(25,'댓글작성 test',8,'admin','2021-11-24 16:54:00'),(26,'댓글 작성',4,'admin','2021-11-24 16:55:30'),(27,'댓글 되나요? ',8,'admin','2021-11-25 00:33:29'),(28,'하이루',8,'admin','2021-11-25 00:33:54'),(29,'하이루',8,'admin','2021-11-25 00:34:00'),(30,'하이루ㅇㅇ',8,'admin','2021-11-25 00:34:02'),(31,'댓글',4,'eunji','2021-11-25 03:32:14'),(32,'연락 드렸습니다. 답장 기다리겠습니다!',10,'admin','2021-11-25 06:15:44');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-25 19:32:03

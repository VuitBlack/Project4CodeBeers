-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: codebeers
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
-- Table structure for table `Articulos`
--

DROP TABLE IF EXISTS `Articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Articulos` (
  `id` varchar(16) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(64) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `pvp` float NOT NULL,
  `gastosEnvio` float NOT NULL,
  `preparacion` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Articulos`
--

LOCK TABLES `Articulos` WRITE;
/*!40000 ALTER TABLE `Articulos` DISABLE KEYS */;
INSERT INTO `Articulos` VALUES ('0001','HDD-500Gb',150,10,10),('0002','RAM-2Gb',100,10,10),('091','Artículo 9',999,99,10),('1','Artículo 1',11.11,6.66,1),('2','Artículo 2',22.22,7.77,30),('3','Artículo 3',33.33,8.88,40),('4','Artículo 4',44.44,9.99,50),('4455','9',100,2,50),('5','Artículo 5',55.55,1.11,1),('6','Artículo 6',666.66,10,100),('7','Artículo 7',100,15,10),('777','HDD',77,100,10),('8','Artículo 8',888,115,110),('9','Artículo 8',888,115,110);
/*!40000 ALTER TABLE `Articulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Clientes`
--

DROP TABLE IF EXISTS `Clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Clientes` (
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(64) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `domicilio` varchar(64) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `nif` varchar(9) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `tipoCliente` varchar(45) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Clientes`
--

LOCK TABLES `Clientes` WRITE;
/*!40000 ALTER TABLE `Clientes` DISABLE KEYS */;
INSERT INTO `Clientes` VALUES ('afd@akjf.com','Pedro','adfajk','1','Estándar'),('cliente1@email.com','Cliente 1','Casa del Ciente 1','11111111A','Premium'),('cliente2@email.com','Cliente 2','Casa del Ciente 2','22222222B','Premium'),('cliente3@email.com','Cliente 3','Casa del Ciente 3','33333333C','Estándar'),('cliente4@email.com','Cliente 4','Casa del Ciente 4','44444444D','Estándar'),('cliente5@email.com','Cliente 5','Casa del Ciente 5','55555555E','Estándar'),('cliente6@email.com','Cliente 6','Calle del cliente 6','66666666F','Estándar'),('cliente7@email.com','Cliente 7','Calle del cliente 7','77777777G','Estándar'),('cliente8@email.com','Cliente 88','Calle del Cliente 88','88888888A','Estándar'),('cliente9@email.com','cliente_09','Calle del Cliente 900','99999999Z','Premium'),('mcafee@email.com','McAfee','USA','0001X','Premium'),('microsoft@email.com','Microsoft','USA','0002X','Estándar'),('pajaroloco@email.com','Woody','Pino de la esquina','7777P','Premium'),('perico@perico.','Adrian','ksdfljksl','55','Premium'),('priatasdelcaribe@pirate.com','Adri el crack','calle piratas','999','Premium'),('tomjones@usa.com','Tom Jones','USA','0000X','Premium');
/*!40000 ALTER TABLE `Clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pedidos`
--

DROP TABLE IF EXISTS `Pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pedidos` (
  `num` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `fechaHora` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `cliente` varchar(32) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `articulo` varchar(32) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`num`),
  KEY `Cliente_idx` (`cliente`),
  KEY `articulo_idx` (`articulo`),
  CONSTRAINT `articulo` FOREIGN KEY (`articulo`) REFERENCES `Articulos` (`id`),
  CONSTRAINT `cliente` FOREIGN KEY (`cliente`) REFERENCES `Clientes` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pedidos`
--

LOCK TABLES `Pedidos` WRITE;
/*!40000 ALTER TABLE `Pedidos` DISABLE KEYS */;
INSERT INTO `Pedidos` VALUES (1,10,'2022-11-18 17:50:37','cliente1@email.com','1'),(5,10,'2022-11-29 16:09:59','afd@akjf.com','1'),(6,10,'2022-12-19 11:08:55','tomjones@usa.com','777'),(9,100,'2022-12-19 16:30:25','microsoft@email.com','0002');
/*!40000 ALTER TABLE `Pedidos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-19 18:49:45

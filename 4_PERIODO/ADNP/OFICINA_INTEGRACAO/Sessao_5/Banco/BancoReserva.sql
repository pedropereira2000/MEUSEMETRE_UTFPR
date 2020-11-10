-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `ambiente`
--

DROP TABLE IF EXISTS `ambiente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ambiente` (
  `id_ambi` int NOT NULL AUTO_INCREMENT,
  `bloco_ambi` char(2) NOT NULL,
  `salaNum_ambi` char(4) NOT NULL,
  `tipoSala_ambi` int NOT NULL COMMENT '1 = Sala Teórica',
  PRIMARY KEY (`id_ambi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ambiente`
--

LOCK TABLES `ambiente` WRITE;
/*!40000 ALTER TABLE `ambiente` DISABLE KEYS */;
/*!40000 ALTER TABLE `ambiente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cli` int NOT NULL AUTO_INCREMENT,
  `nome_cli` varchar(45) NOT NULL,
  `documento_cli` varchar(15) NOT NULL,
  PRIMARY KEY (`id_cli`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Pedro','125.555.112-85');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacao_cliente`
--

DROP TABLE IF EXISTS `informacao_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacao_cliente` (
  `id_cli_info` int NOT NULL,
  `contato_info` varchar(13) NOT NULL,
  `contatoAlt_info` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id_cli_info`),
  CONSTRAINT `fk_Informacao_Cliente` FOREIGN KEY (`id_cli_info`) REFERENCES `cliente` (`id_cli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacao_cliente`
--

LOCK TABLES `informacao_cliente` WRITE;
/*!40000 ALTER TABLE `informacao_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `informacao_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacao_servidor`
--

DROP TABLE IF EXISTS `informacao_servidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `informacao_servidor` (
  `id_info_serv` int NOT NULL,
  `emailAlternativo_info` varchar(100) DEFAULT NULL,
  `contato_info` varchar(13) NOT NULL,
  `contatoAlt_info` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id_info_serv`),
  CONSTRAINT `fk_Informacao_Servidor` FOREIGN KEY (`id_info_serv`) REFERENCES `servidor` (`id_serv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacao_servidor`
--

LOCK TABLES `informacao_servidor` WRITE;
/*!40000 ALTER TABLE `informacao_servidor` DISABLE KEYS */;
/*!40000 ALTER TABLE `informacao_servidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `id_res` int NOT NULL AUTO_INCREMENT,
  `contribuicao_res` tinyint(1) NOT NULL COMMENT 'True/False',
  `dataInicio_res` date NOT NULL COMMENT 'DD/MM/AAAA',
  `periodoReservado_res` int NOT NULL COMMENT 'Os horários são de, M1 até M6, T1 até T6 e N1 até N5\nPara todos horarios = X1\n11 = M1\n12 = M2\n13 = M3\n14 = M4\n15 = M5 \n16 = M6\n21 = T1',
  `observacao_res` varchar(100) NOT NULL,
  `servidor_res` int NOT NULL,
  `cliente_res` int NOT NULL,
  `ambiente_res` int NOT NULL COMMENT 'Descrição dos motivos/ objetivos da reserva',
  PRIMARY KEY (`id_res`),
  KEY `fk_Reserva_Servidor_idx` (`servidor_res`),
  KEY `fk_Reserva_Cliente_idx` (`cliente_res`),
  KEY `fk_Reserva_Ambiente_idx` (`ambiente_res`),
  CONSTRAINT `fk_Reserva_Ambiente` FOREIGN KEY (`ambiente_res`) REFERENCES `ambiente` (`id_ambi`),
  CONSTRAINT `fk_Reserva_Cliente` FOREIGN KEY (`cliente_res`) REFERENCES `cliente` (`id_cli`),
  CONSTRAINT `fk_Reserva_Servidor` FOREIGN KEY (`servidor_res`) REFERENCES `servidor` (`id_serv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servidor`
--

DROP TABLE IF EXISTS `servidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servidor` (
  `id_serv` int NOT NULL COMMENT 'Siape do Servidor',
  `nome_serv` varchar(100) NOT NULL,
  `senha_serv` varchar(8) NOT NULL,
  `email_serv` varchar(100) NOT NULL,
  `ramal_serv` int NOT NULL,
  `foto_serv` varchar(100) NOT NULL,
  `permissao_serv` int NOT NULL,
  PRIMARY KEY (`id_serv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servidor`
--

LOCK TABLES `servidor` WRITE;
/*!40000 ALTER TABLE `servidor` DISABLE KEYS */;
/*!40000 ALTER TABLE `servidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mydb'
--

--
-- Dumping routines for database 'mydb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-02 20:59:25

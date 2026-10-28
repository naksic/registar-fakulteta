/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.28-MariaDB : Database - 0_registar_fakulteta
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`0_registar_fakulteta` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `0_registar_fakulteta`;

/*Table structure for table `administrativno_osoblje` */

DROP TABLE IF EXISTS `administrativno_osoblje`;

CREATE TABLE `administrativno_osoblje` (
  `id` bigint(20) NOT NULL,
  `radno_mesto` varchar(50) NOT NULL,
  `sektor` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `administrativno_osoblje_ibfk_1` FOREIGN KEY (`id`) REFERENCES `lice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `clan_uprave` */

DROP TABLE IF EXISTS `clan_uprave`;

CREATE TABLE `clan_uprave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `funkcija` varchar(30) NOT NULL,
  `mandat_uprave_id` bigint(20) NOT NULL,
  `lice_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mandat_uprave_id` (`mandat_uprave_id`),
  KEY `lice_id` (`lice_id`),
  CONSTRAINT `clan_uprave_ibfk_1` FOREIGN KEY (`mandat_uprave_id`) REFERENCES `mandat_uprave` (`id`),
  CONSTRAINT `clan_uprave_ibfk_2` FOREIGN KEY (`lice_id`) REFERENCES `lice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `fakultet` */

DROP TABLE IF EXISTS `fakultet`;

CREATE TABLE `fakultet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `skraceni_naziv` varchar(10) NOT NULL,
  `adresa` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `godina_osnivanja` int(11) NOT NULL,
  `pib` varchar(20) NOT NULL,
  `maticni_broj` varchar(20) NOT NULL,
  `website` varchar(50) NOT NULL,
  `status` varchar(30) NOT NULL,
  `univerzitet_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `univerzitet_id` (`univerzitet_id`),
  CONSTRAINT `fakultet_ibfk_1` FOREIGN KEY (`univerzitet_id`) REFERENCES `univerzitet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ulogovan` tinyint(1) NOT NULL,
  `univerzitet_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `univerzitet_id` (`univerzitet_id`),
  CONSTRAINT `korisnik_ibfk_1` FOREIGN KEY (`univerzitet_id`) REFERENCES `univerzitet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `lice` */

DROP TABLE IF EXISTS `lice`;

CREATE TABLE `lice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `datum_rodjenja` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `mandat_uprave` */

DROP TABLE IF EXISTS `mandat_uprave`;

CREATE TABLE `mandat_uprave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datum_pocetka` date NOT NULL,
  `datum_zavrsetka` date DEFAULT NULL,
  `fakultet_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fakultet_id` (`fakultet_id`),
  CONSTRAINT `mandat_uprave_ibfk_1` FOREIGN KEY (`fakultet_id`) REFERENCES `fakultet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `nastavno_osoblje` */

DROP TABLE IF EXISTS `nastavno_osoblje`;

CREATE TABLE `nastavno_osoblje` (
  `id` bigint(20) NOT NULL,
  `uza_oblast` varchar(100) NOT NULL,
  `datum_izbora` date NOT NULL,
  `zvanje` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `nastavno_osoblje_ibfk_1` FOREIGN KEY (`id`) REFERENCES `lice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `organizaciona_jedinica` */

DROP TABLE IF EXISTS `organizaciona_jedinica`;

CREATE TABLE `organizaciona_jedinica` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `fakultet_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fakultet_id` (`fakultet_id`),
  CONSTRAINT `organizaciona_jedinica_ibfk_1` FOREIGN KEY (`fakultet_id`) REFERENCES `fakultet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `broj_indeksa` varchar(20) NOT NULL,
  `godina_studija` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`id`) REFERENCES `lice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `univerzitet` */

DROP TABLE IF EXISTS `univerzitet`;

CREATE TABLE `univerzitet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `adresa` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefon` varchar(50) NOT NULL,
  `godina_osnivanja` int(11) NOT NULL,
  `website` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

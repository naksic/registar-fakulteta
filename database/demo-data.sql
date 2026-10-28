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

/*Data for the table `administrativno_osoblje` */

insert  into `administrativno_osoblje`(`id`,`radno_mesto`,`sektor`) values 
(4,'Finansijski analiticar','finansije'),
(9,'Pravnik','pravni'),
(12,'Privredni savetnik','privreda'),
(17,'Arhiva','arhiva'),
(21,'Pravni savetnik','pravni'),
(24,'Pravni analitičar','pravni');

/*Data for the table `clan_uprave` */

insert  into `clan_uprave`(`id`,`funkcija`,`mandat_uprave_id`,`lice_id`) values 
(2,'DEKAN',2,5),
(3,'PRODEKAN_ZA_FINANSIJE',2,6),
(5,'PRODEKAN_ZA_NAUKU',2,1),
(6,'DEKAN',3,7),
(7,'PRODEKAN_ZA_FINANSIJE',3,8),
(8,'SEKRETAR',3,9),
(9,'PRODEKAN_STUDENT',3,10),
(11,'DEKAN',4,16),
(12,'PRODEKAN_ZA_NASTAVU',4,14),
(13,'PRODEKAN_STUDENT',4,18),
(14,'SEKRETAR',4,17),
(17,'DEKAN',6,7),
(18,'PRODEKAN_ZA_NAUKU',6,8),
(19,'PRODEKAN_STUDENT',6,13),
(20,'SEKRETAR',6,12),
(21,'PRODEKAN_ZA_NASTAVU',6,6),
(24,'SEKRETAR',2,9),
(25,'PRODEKAN_ZA_NASTAVU',2,11),
(27,'DEKAN',8,5),
(28,'PRODEKAN_STUDENT',2,3),
(29,'DEKAN',10,23),
(30,'PRODEKAN_ZA_NASTAVU',10,20);

/*Data for the table `fakultet` */

insert  into `fakultet`(`id`,`naziv`,`skraceni_naziv`,`adresa`,`email`,`telefon`,`godina_osnivanja`,`pib`,`maticni_broj`,`website`,`status`,`univerzitet_id`) values 
(1,'Fakultet organizacionih nauka','FON','Jove Ilića 154','info@fon.bg.ac.rs','0113950800',1971,'123456789','07011234','www.fon.bg.ac.rs','AKTIVAN',1),
(2,'Elektrotehnički fakultet','ETF','Bulevar Kralja Aleksandra 73','etf@bg.ac.rs','011 3226992',1947,'123142125','1412513','www.etf.bg.ac.rs','UGASEN',1),
(3,'Elektronski fakultet','ELFAK','Aleksandra Medvedeva 4','elfak@ni.ac.rs','018 529105',1960,'11215151351','12534253543','www.elfak.ni.ac.rs','AKTIVAN',3),
(5,'Fakultet tehničkih nauka','FTN','Trg Dositeja Obradovića 6','ftndean@uns.ac.rs','021 6350 413',1960,'1111122222','315151355','www.ftn.uns.ac.rs','AKTIVAN',2),
(6,'Medicinski fakultet','MFUB','Dr Subotica starijeg','mf.bg@med.bg.ac.rs','011 363 6300',1920,'125135135','235643534','www.med.bg.ac.rs','AKTIVAN',1),
(7,'Fakultet medicinskih nauka','FMN','Svetozara Markovica 69','info@fmn.kg.ac.rs','034 306 800',1986,'101042779','07345496','www.medf.kg.ac.rs','UGASEN',4),
(8,'Ekonomski fakultet','EKNFAK','Trg kralja Aleksandra Ujedinitelja 11','ekonomski@eknfak.ni.ac.rs','018 528 601',1973,'100667088','07174705','www.eknfak.ni.ac.rs','AKTIVAN',3),
(9,'Tehnološki fakultet','TEHFAK','Bulevar Oslobodjenja 124','tehfak@ni.ac.rs','016 247 203',1979,'151642746','264376585','www.tf.ni.ac.rs','NEAKTIVAN',3),
(10,'Agronomski fakultet','AFK','Cara Dusana 34','afdekanat@kg.ac.rs','032 303 400',1978,'101122442','07281536','www.afc.kg.ac.rs','AKTIVAN',4),
(11,'Filozofski fakultet','FFUNS','Dr Zorana Djindjica 2','dekanat@ff.uns.ac.rs','021 450 690 ',1959,'64576234','52563474','www.ff.uns.ac.rs','AKTIVAN',2),
(12,'Pedagoški fakultet','PFU','Trg Svetog Save 36','fakultet@pfu.kg.ac.rs','031 521 952',1993,'71510951','153164235','www.pfu.kg.ac.rs','AKTIVAN',4),
(13,'Akademija umetnosti','AU','Djure Jaksica 7','aofarts@uns.ac.rs','021 422 176',1970,'513621513','1532543','www.akademija.uns.ac.rs','NEAKTIVAN',2),
(15,'Pravni fakultet','PFUB','Bulevar Kralja Aleksandra 67','pravni@ius.bg.ac.rs','011 302 7600',1808,'341531531','12135355','www.ius.bg.ac.rs','AKTIVAN',1),
(16,'Pravni fakultet','PRAFAK','Trg kralja Aleksandra Ujedinitelja 11','pravni@prafak.ni.ac.rs','018 500 201',1970,'1513531','5343223','www.prafak.ni.ac.rs','NEAKTIVAN',3),
(17,'Vojna akademija','VA','Veljka Lukića Kurjaka 33','dekan@va.mod.gov.rs','011 360 3070',1850,'532523532','13412455','www.va.mod.gov.rs','AKTIVAN',6);

/*Data for the table `korisnik` */

insert  into `korisnik`(`id`,`username`,`password`,`ulogovan`,`univerzitet_id`) values 
(1,'regfaks1','regfaks1',1,1),
(2,'regfaks2','regfaks2',0,2);

/*Data for the table `lice` */

insert  into `lice`(`id`,`ime`,`prezime`,`datum_rodjenja`,`email`,`telefon`) values 
(1,'Petar','Petrović','1980-05-15','petar.petrovic@fon.bg.ac.rs','0641234567'),
(3,'Nikola','Aksić','2004-05-30','na20230261@student.fon.bg.ac.rs','0631241245'),
(4,'Andrej','Aleksov','1975-06-24','andrej.aleksov@etf.bg.ac.rs','0640125715'),
(5,'Milenko','Tepić','1977-08-17','milenko.tepic@fon.bg.ac.rs','065124125'),
(6,'Andrija','Andrić','1975-08-27','andrija.andric@fon.bg.ac.rs','065151251'),
(7,'Dejan','Dejanović','1966-10-18','dejan.dejanovic@elfak.ni.ac.rs','065444333'),
(8,'Violeta','Miljković','1975-09-16','violeta.miljkovic@ekfak.ni.ac.rs','066125155'),
(9,'Marija','Mikić','1981-02-19','marija.mikic@elfak.ni.ac.rs','0614125125'),
(10,'Dimitrije','Filipović','2002-12-12','df20210100@student.elfak.ni.ac.rs','065444111'),
(11,'Dragana','Milosević','1972-10-17','dragana.milosevic@etf.bg.ac.rs','065115125'),
(12,'Milica','Popović','1982-04-20','milica.popovic@etf.bg.ac.rs','063151251'),
(13,'Vladimir','Koprivica','2003-07-24','vladimir.koprivica@student.etf.bg.ac.rs','060617125'),
(14,'Goran','Nojkić','1975-06-24','goran.nojkic@ff.uns.ac.rs','065441122'),
(15,'Mirjana','Mirjanić','1982-01-27','mirjana.mirjanic@ff.uns.ac.rs','062145112'),
(16,'Dara','Darić','1974-11-26','dara.daric@ff.uns.ac.rs','069124125'),
(17,'Dunja','Milicević','1982-01-11','dunja.milicevic@ff.uns.ac.rs','0631251512'),
(18,'Radomir','Putnik','2004-05-30','radomir.putnik@student.ff.uns.ac.rs','061124151'),
(20,'Petar','Bojović','1979-06-04','petar.bojovic@etf.bf.ac.rs','064 333 2224'),
(21,'Mila','Milović','1983-08-08','mila.milanovic@tehfak.ni.ac.rs','066 234432'),
(22,'Milan','Paprikić','2005-06-13','mp20240080@student.va.bg.ac.rs','068 5544221'),
(23,'Dragoljub','Mihajlović','1976-06-15','dragoljub.mihajlovic@va.gov.rs','0651112223'),
(24,'Nikola','Pašić','1968-07-16','nikola.pasic@va.gov.rs','063444552'),
(25,'Momčilo','Gavrić','2006-06-28','mg20250003@student.va.gov.rs','062334213');

/*Data for the table `mandat_uprave` */

insert  into `mandat_uprave`(`id`,`datum_pocetka`,`datum_zavrsetka`,`fakultet_id`) values 
(2,'2024-01-10',NULL,1),
(3,'2016-06-14','2020-02-27',3),
(4,'2018-06-13','2021-10-20',11),
(6,'2010-06-15','2014-05-17',1),
(7,'2025-10-21',NULL,9),
(8,'2018-05-14','2023-07-20',10),
(9,'2023-01-09','2024-10-24',9),
(10,'2022-05-18','2025-05-13',17);

/*Data for the table `nastavno_osoblje` */

insert  into `nastavno_osoblje`(`id`,`uza_oblast`,`datum_izbora`,`zvanje`) values 
(1,'Softversko inženjerstvo','2015-09-01','DOCENT'),
(5,'Operaciona istraživanja','2022-01-19','REDOVNI_PROFESOR'),
(6,'Finansijski menadžment','2023-11-14','VANREDNI_PROFESOR'),
(7,'Elektronika ','2014-12-24','REDOVNI_PROFESOR'),
(8,'Numerička analiza','2018-07-31','DOCENT'),
(11,'Strukture podataka i algoritmi','2018-08-31','VANREDNI_PROFESOR'),
(14,'Antička filozofija','2018-09-03','DOCENT'),
(15,'Sokratova filozofija','2022-10-25','ASISTENT'),
(16,'Filozofska istorija','2021-09-30','REDOVNI_PROFESOR'),
(20,'Vojna strategija','2019-01-16','VANREDNI_PROFESOR'),
(23,'Gerilsko ratovanje','2016-01-04','REDOVNI_PROFESOR');

/*Data for the table `organizaciona_jedinica` */

insert  into `organizaciona_jedinica`(`id`,`naziv`,`fakultet_id`) values 
(1,'Katedra za informacione sisteme',1),
(2,'Katedra za informacione tehnologije',2),
(3,'Katedra za elektroniku',3),
(4,'Katedra za robotiku',5),
(5,'Institut za anatomiju',6),
(6,'Katedra za molekularnu biologiju',7),
(7,'Katedra za mikroekonomiju',8),
(8,'Laboratorija za crnu metalurgiju',9),
(9,'Katedra za povrtarstvo',10),
(10,'Katedra za antičku filozofiju',11),
(11,'Institut za pedagogiju',12),
(13,'Odsek za pešadiju',17),
(14,'Katedra za naoružanje',17);

/*Data for the table `student` */

insert  into `student`(`id`,`broj_indeksa`,`godina_studija`) values 
(3,'2023/0261',3),
(10,'2021/0100',4),
(13,'2022/0005',4),
(18,'2023/0099',3),
(22,'2024/0080',2),
(25,'2025/0003',1);

/*Data for the table `univerzitet` */

insert  into `univerzitet`(`id`,`naziv`,`adresa`,`email`,`telefon`,`godina_osnivanja`,`website`) values 
(1,'Univerzitet u Beogradu','Studentski trg 1','info@bg.ac.rs','011 225 883',1808,'www.bg.ac.rs'),
(2,'Univerzitet u Novom Sadu','Dr Zorana Đinđića 1','rektorat@unc.ac.rs','021 485 2020',1960,'www.uns.ac.rs'),
(3,'Univerzitet u Nišu','Univerzitetski trg 2','uniuni@ni.ac.rs','018 589 250',1965,'www.ni.ac.rs'),
(4,'Univerzitet u Kragujevcu','Liceja Kneževine Srbije 1A','unikg@kg.ac.rs','034 370 270',1976,'www.kg.ac.rs'),
(6,'Univerzitet odbrane','Veljka Lukića Kurjaka 1','rectorate@mod.gov.rs','011 300 5201',2011,'www.uo.mod.gov.rs');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

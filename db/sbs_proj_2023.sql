/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.28-MariaDB : Database - sbs_proj_2023
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sbs_proj_2023` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `sbs_proj_2023`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `title` char(100) NOT NULL,
  `body` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `article` */

insert  into `article`(`id`,`regDate`,`updateDate`,`title`,`body`) values 
(1,'2023-08-16 00:24:09','2023-08-16 00:24:09','제목1','내용1'),
(2,'2023-08-16 00:24:09','2023-08-16 00:24:09','제목2','내용2'),
(3,'2023-08-16 00:24:09','2023-08-16 00:24:09','제목3','내용3');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `loginId` char(20) NOT NULL,
  `loginPw` char(60) NOT NULL,
  `name` char(20) NOT NULL,
  `authLevel` smallint(2) unsigned DEFAULT 3 COMMENT '(3=일반,7=관리자)',
  `nickName` char(20) NOT NULL,
  `cellphoneNo` char(20) NOT NULL,
  `email` char(50) NOT NULL,
  `delStatus` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전, 1=탈퇴)',
  `delDate` datetime DEFAULT NULL COMMENT '탈퇴날짜',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`regDate`,`updateDate`,`loginId`,`loginPw`,`name`,`authLevel`,`nickName`,`cellphoneNo`,`email`,`delStatus`,`delDate`) values 
(1,'2023-08-16 00:24:18','2023-08-16 00:24:18','admin','admin','관리자',7,'관리자','01011111111','admin@gmail.com',0,NULL),
(2,'2023-08-16 00:24:18','2023-08-16 00:24:18','user1','user1','유저1',7,'유저1','01011112222','user1@gmail.com',0,NULL),
(3,'2023-08-16 00:24:18','2023-08-16 00:24:18','user2','user2','유저2',7,'유저2','01011113333','user2@gmail.com',0,NULL),
(4,'2023-08-16 00:31:23','2023-08-16 00:31:23','user3','user3','유저3',3,'유저3','01011113333','user3@gmail.com',0,NULL),
(5,'2023-08-16 01:06:02','2023-08-16 01:06:02','user4','user4','유저4',3,'유저4','01011114444','user4@gmail.com',0,NULL),
(6,'2023-08-16 01:08:27','2023-08-16 01:08:27','user5','user5','유저5',3,'유저5','01011115555','user5@gmail.com',0,NULL),
(7,'2023-08-16 01:10:36','2023-08-16 01:10:36','user6','user6','유저6',3,'유저6','01011116666','user6@gmail.com',0,NULL),
(8,'2023-08-16 01:12:14','2023-08-16 01:12:14','user7','user7','유저7',3,'유저7','01011117777','user7@gmail.com',0,NULL),
(9,'2023-08-16 01:15:21','2023-08-16 01:15:21','user8','user8','유저8',3,'유저8','01011118888','user8@gmail.com',0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

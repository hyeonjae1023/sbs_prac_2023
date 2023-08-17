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
  `memberId` int(10) unsigned NOT NULL,
  `title` char(100) NOT NULL,
  `body` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `article` */

insert  into `article`(`id`,`regDate`,`updateDate`,`memberId`,`title`,`body`) values 
(1,'2023-08-16 22:38:51','2023-08-16 22:38:51',2,'제목1','내용1'),
(2,'2023-08-16 22:38:52','2023-08-16 22:38:52',2,'제목2','내용2'),
(3,'2023-08-16 22:38:54','2023-08-16 22:38:54',2,'제목3','내용3'),
(4,'2023-08-16 23:28:09','2023-08-16 23:28:09',2,'시발','캬캬');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`regDate`,`updateDate`,`loginId`,`loginPw`,`name`,`authLevel`,`nickName`,`cellphoneNo`,`email`,`delStatus`,`delDate`) values 
(1,'2023-08-16 22:39:01','2023-08-16 22:39:01','admin','admin','관리자',7,'관리자','01011111111','admin@gmail.com',0,NULL),
(2,'2023-08-16 22:39:04','2023-08-16 22:39:04','user1','user1','유저1',7,'유저1','01011112222','user1@gmail.com',0,NULL),
(3,'2023-08-16 22:39:04','2023-08-16 22:39:04','user2','user2','유저2',7,'유저2','01011113333','user2@gmail.com',0,NULL),
(4,'2023-08-16 23:37:24','2023-08-16 23:37:24','user8','user8','유저8',3,'유저8','01011118888','user8@gmail.com',0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

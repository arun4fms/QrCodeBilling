/*
SQLyog Community v8.4 RC2
MySQL - 5.0.15-nt : Database - qrcode
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`qrcode` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `qrcode`;

/*Table structure for table `productdetails` */

DROP TABLE IF EXISTS `productdetails`;

CREATE TABLE `productdetails` (
  `Sno` int(50) NOT NULL auto_increment,
  `productname` varchar(50) default NULL,
  `productquantity` varchar(50) default NULL,
  `productprice` varchar(50) default NULL,
  `Billno` int(10) NOT NULL,
  `Emailid` varchar(50) NOT NULL,
  `PaymentType` varchar(10) default NULL,
  `Status` varchar(10) default NULL,
  PRIMARY KEY  (`Sno`),
  UNIQUE KEY `Sno` (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `productdetails` */

insert  into `productdetails`(`Sno`,`productname`,`productquantity`,`productprice`,`Billno`,`Emailid`,`PaymentType`,`Status`) values (83,'5star','1','100.0',1447,'f','PayPal','Paid');

/*Table structure for table `registration` */

DROP TABLE IF EXISTS `registration`;

CREATE TABLE `registration` (
  `customerphone` varchar(50) default NULL,
  `customeremail` varchar(50) default NULL,
  `customername` varchar(50) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `registration` */

insert  into `registration`(`customerphone`,`customeremail`,`customername`) values ('1','f','fag'),('2','g@a','gdd'),('','','');

/*Table structure for table `userlogin` */

DROP TABLE IF EXISTS `userlogin`;

CREATE TABLE `userlogin` (
  `username` varchar(50) default NULL,
  `password` varchar(50) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userlogin` */

insert  into `userlogin`(`username`,`password`) values ('owner','owner');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

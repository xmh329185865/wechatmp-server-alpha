CREATE DATABASE  IF NOT EXISTS `xjtuana1.0` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `xjtuana1.0`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: xjtuana1.0
-- ------------------------------------------------------
-- Server version	5.6.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(45) NOT NULL,
  `department_desc` blob,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  `group_desc` blob,
  `group_domain` blob NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `group_role`
--

DROP TABLE IF EXISTS `group_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_role` (
  `group_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_role_name` varchar(45) NOT NULL,
  `group_role_desc` blob,
  `group_role_auth` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`group_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(45) NOT NULL,
  `job_desc` blob,
  `job_auth` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(45) NOT NULL DEFAULT '',
  `client_cell` varchar(45) NOT NULL DEFAULT '',
  `client_building` varchar(45) NOT NULL DEFAULT '',
  `client_room` varchar(45) NOT NULL DEFAULT '',
  `order_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_resvtime` timestamp NULL DEFAULT NULL,
  `order_description` blob,
  `order_status` int(11) NOT NULL DEFAULT '1',
  `order_supadmin` varchar(200) DEFAULT NULL,
  `order_nowadmin` varchar(45) DEFAULT NULL,
  `order_diagnosis` blob,
  `order_wechatid` varchar(50) DEFAULT 'not_wechat_order',
  `order_ratedesc` blob,
  `order_result` blob,
  `order_cercode` varchar(128) NOT NULL DEFAULT '',
  `order_solveway` blob,
  `order_ratelevel` int(11) DEFAULT NULL,
  `order_faultip` varchar(45) NOT NULL,
  `order_fixedtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_name` varchar(45) NOT NULL,
  `staff_job_name` varchar(45) DEFAULT NULL,
  `staff_cell` varchar(45) DEFAULT NULL,
  `staff_domain` blob,
  `staff_group_name` varchar(45) DEFAULT NULL,
  `staff_department_name` varchar(45) DEFAULT NULL,
  `staff_qq` varchar(45) DEFAULT NULL,
  `staff_gender` int(11) NOT NULL DEFAULT '0',
  `staff_grade` int(11) DEFAULT NULL,
  `staff_campus` varchar(45) DEFAULT NULL,
  `staff_oapwd` varchar(128) DEFAULT '111111',
  `staff_wechatacc` varchar(45) DEFAULT NULL,
  `staff_wechatopenid` varchar(45) DEFAULT NULL,
  `staff_addrbuilding` varchar(45) DEFAULT NULL,
  `staff_addrroom` varchar(45) DEFAULT NULL,
  `staff_job_id` int(11) DEFAULT NULL,
  `staff_group_id` int(11) DEFAULT NULL,
  `staff_department_id` int(11) DEFAULT NULL,
  `staff_group_role_id` int(11) DEFAULT NULL,
  `staff_group_role_name` varchar(45) DEFAULT NULL,
  `staff_stuid` varchar(45) NOT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `staff_stuid_UNIQUE` (`staff_stuid`)
) ENGINE=InnoDB AUTO_INCREMENT=2120903038 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wcmp_bizdefine`
--

DROP TABLE IF EXISTS `wcmp_bizdefine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wcmp_bizdefine` (
  `wcmpbiz_id` int(11) NOT NULL AUTO_INCREMENT,
  `wcmpbiz_classname` varchar(80) NOT NULL,
  `wcmpbiz_methodname` varchar(80) NOT NULL,
  `wcmpbiz_name` varchar(80) NOT NULL,
  `wcmpbiz_label` varchar(80) NOT NULL,
  `wcmpbiz_paramtypes` blob,
  `wcmpbiz_auth` varchar(45) DEFAULT 'all',
  `wcmpbiz_paramnames` blob,
  `wcmpbiz_desc` blob,
  `wcmpbiz_paramamount` int(11) NOT NULL,
  `wcmpbiz_methodbacktype` varchar(80) DEFAULT NULL,
  `wcmpbiz_methodbackgetter` varchar(80) DEFAULT NULL,
  `wcmpbiz_consparamtypes` blob,
  `wcmpbiz_consparamamount` int(11) NOT NULL,
  PRIMARY KEY (`wcmpbiz_id`),
  UNIQUE KEY `wcmpbiz_name_UNIQUE` (`wcmpbiz_name`),
  UNIQUE KEY `wcmpbiz_label_UNIQUE` (`wcmpbiz_label`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wechat_msgrec`
--

DROP TABLE IF EXISTS `wechat_msgrec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wechat_msgrec` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_wcmsgid` varchar(80) NOT NULL,
  `msg_timestamp` timestamp NULL DEFAULT NULL,
  `msg_content` blob,
  `msg_fromuser` varchar(45) NOT NULL,
  `msg_type` int(11) NOT NULL,
  `msg_touser` varchar(45) NOT NULL,
  `msg_picurl` varchar(500) DEFAULT NULL,
  `msg_mediaid` varchar(45) DEFAULT NULL,
  `msg_format` varchar(45) DEFAULT NULL,
  `msg_recognition` blob,
  `msg_thumbmediaid` varchar(45) DEFAULT NULL,
  `msg_location_x` varchar(45) DEFAULT NULL,
  `msg_location_y` varchar(45) DEFAULT NULL,
  `msg_scale` varchar(45) DEFAULT NULL,
  `msg_label` varchar(150) DEFAULT NULL,
  `msg_title` varchar(200) DEFAULT NULL,
  `msg_description` blob,
  `msg_url` varchar(300) DEFAULT NULL,
  `event_type` int(11) DEFAULT NULL,
  `msg_precision` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wechat_subscribers`
--

DROP TABLE IF EXISTS `wechat_subscribers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wechat_subscribers` (
  `wcuser_id` int(11) NOT NULL AUTO_INCREMENT,
  `wcuser_openid` varchar(45) NOT NULL,
  `subscribe_timestamp` timestamp NULL DEFAULT NULL,
  `wcuser_group_id` int(11) DEFAULT '0',
  `wcuser_wechatacc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`wcuser_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'xjtuana1.0'
--

--
-- Dumping routines for database 'xjtuana1.0'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-04  0:24:34

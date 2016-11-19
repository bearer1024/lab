/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : xx54

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 11/19/2016 22:13:33 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `OPTIONS`
-- ----------------------------
DROP TABLE IF EXISTS `OPTIONS`;
CREATE TABLE `OPTIONS` (
  `TEXT` longtext NOT NULL,
  `OPTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`OPTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `OPTIONS`
-- ----------------------------
BEGIN;
INSERT INTO `OPTIONS` VALUES ('Yes', '1'), ('No', '2');
COMMIT;

-- ----------------------------
--  Table structure for `QUESTION`
-- ----------------------------
DROP TABLE IF EXISTS `QUESTION`;
CREATE TABLE `QUESTION` (
  `QUESTION` varchar(250) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEADLINE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `QUESTION`
-- ----------------------------
BEGIN;
INSERT INTO `QUESTION` VALUES ('Should Shangri-La be marked on the map?', '1', '2016-12-01 00:00:00');
COMMIT;

-- ----------------------------
--  Table structure for `SECURITY_CODE`
-- ----------------------------
DROP TABLE IF EXISTS `SECURITY_CODE`;
CREATE TABLE `SECURITY_CODE` (
  `CODE` varchar(30) NOT NULL,
  `USED` int(11) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `SECURITY_CODE`
-- ----------------------------
BEGIN;
INSERT INTO `SECURITY_CODE` VALUES ('IOQNTMRW', '0'), ('KDFCDIGK', '0'), ('LNCSSCZM', '0'), ('LOIRWGLM', '0'), ('LRYUMOTX', '0'), ('MJLIMMLZ', '0'), ('NKIGTJUJ', '0'), ('RLLXYGEY', '0'), ('RUVUYMUJ', '0'), ('TSHETHQB', '1');
COMMIT;

-- ----------------------------
--  Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `admin`
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('admin', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` varchar(30) NOT NULL,
  `userEmail` varchar(30) NOT NULL,
  `userFullName` varchar(30) DEFAULT NULL,
  `dateOfBirth` varchar(30) DEFAULT NULL,
  `homeAddress` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `securityCode` varchar(30) NOT NULL,
  PRIMARY KEY (`userEmail`),
  UNIQUE KEY `userEmail` (`userEmail`),
  KEY `securityCode` (`securityCode`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`securityCode`) REFERENCES `SECURITY_CODE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('bearer', 'bearer1024@gmail.com', 'bearer xin xiong', '1993-08-10', 'leicester', '2454AD61C2ACC04F0A20FABF7F2BC96F28D19C434052FDEFDBB51B46FE534F89', 'TSHETHQB');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

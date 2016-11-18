
--
-- Note: You will need to create other necessary tables for storing users and votes.
--

--
-- Table structure for table `SECURITY_CODE`
--

use test;

-- 
-- table structure for user
-- 
DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `userName` varchar(30) NOT NULL,
  `userEmail` varchar(30) NOT NULL UNIQUE,
  `userFullName` varchar(30),
  `dateOfBirth` varchar(30),
  `homeAddress` varchar(50),
  `password` varchar(50),
  `securityCode` varchar(30),
  PRIMARY KEY (`userEmail`),
  FOREIGN KEY (securityCode) REFERENCES SECURITY_CODE(CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `SECURITY_CODE`;

CREATE TABLE `SECURITY_CODE` (
  `CODE` varchar(30) NOT NULL,
  `USED` int(11) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- data for table `SECURITY_CODE`
--

INSERT INTO `SECURITY_CODE` VALUES ('IOQNTMRW',0),('KDFCDIGK',0),('LNCSSCZM',0),('LOIRWGLM',0),('LRYUMOTX',0),('MJLIMMLZ',0),('NKIGTJUJ',0),('RLLXYGEY',0),('RUVUYMUJ',0),('TSHETHQB',0);


--
-- Table structure for table `QUESTION`
--

DROP TABLE IF EXISTS `QUESTION`;
CREATE TABLE `QUESTION` (
  `QUESTION` varchar(250) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEADLINE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
--
-- Dumping data for table `QUESTION`
--

INSERT INTO `QUESTION` VALUES ('Should Shangri-La be marked on the map?',1,'2016-12-01 00:00:00');


--
-- Table structure for table `OPTIONS`
--

DROP TABLE IF EXISTS `OPTIONS`;
CREATE TABLE `OPTIONS` (
  `TEXT` longtext NOT NULL,
  `OPTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`OPTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
--  data for table `OPTIONS`
--

INSERT INTO `OPTIONS` VALUES ('Yes',1),('No',2);


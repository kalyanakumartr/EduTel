# --------------------------------------------------------
# Host:                         localhost
# Database:                     edutel
# Server version:               5.6.10
# Server OS:                    Win64
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2015-02-03 18:46:56
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
# Dumping database structure for edutel
CREATE DATABASE IF NOT EXISTS `edutel` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `edutel`;


# Dumping structure for table edutel.onlinetestseriesexamquestionanswermapping
CREATE TABLE IF NOT EXISTS `onlinetestseriesexamquestionanswermapping` (
  `oeExamTestSeriesAutoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `oeQuestionId` varchar(50) NOT NULL,
  `oeTestSeriesAnswerVIA` varchar(50) DEFAULT NULL,
  `oeValidatedAnswer` bit(1) DEFAULT b'0',
  `createdBy` varchar(50) NOT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `downloaded` bit(1) NOT NULL,
  `uploadFileLastModifiedDate` datetime DEFAULT NULL,
  `uploadFileDate` datetime DEFAULT NULL,
  `uploadFileSize` bigint(20) DEFAULT NULL,
  `uploadFileName` varchar(100) DEFAULT NULL,
  `uploadFileFolderURL` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`oeExamTestSeriesAutoId`),
  KEY `FK_OCEQAM_OEQ_idx` (`oeQuestionId`),
  CONSTRAINT `FK_CAT_OEQ` FOREIGN KEY (`oeQuestionId`) REFERENCES `onlineexamquestion` (`oeQuestionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Data exporting was unselected.
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

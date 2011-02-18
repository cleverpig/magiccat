/*
MySQL Data Transfer
Source Host: localhost
Source Database: magiccat
Target Host: localhost
Target Database: magiccat
Date: 2011-2-18 15:48:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for blog
-- ----------------------------
CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `author` varchar(20) collate utf8_unicode_ci NOT NULL,
  `content` longtext collate utf8_unicode_ci NOT NULL,
  `modifyDate` datetime default NULL,
  `publishDate` datetime NOT NULL,
  `title` varchar(200) collate utf8_unicode_ci NOT NULL,
  `columnId` int(11) default NULL,
  `publisherId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1FA3C26E878B8` (`publisherId`),
  CONSTRAINT `FK1FA3C26E878B8` FOREIGN KEY (`publisherId`) REFERENCES `siteuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for blogcomment
-- ----------------------------
CREATE TABLE `blogcomment` (
  `id` int(11) NOT NULL,
  `commentTime` datetime default NULL,
  `content` varchar(200) collate utf8_unicode_ci NOT NULL,
  `blogId` int(11) default NULL,
  `blogReviewerId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2315843DEBF13A3` (`blogReviewerId`),
  KEY `FK2315843D2248F04E` (`blogId`),
  CONSTRAINT `FK2315843D2248F04E` FOREIGN KEY (`blogId`) REFERENCES `blog` (`id`),
  CONSTRAINT `FK2315843DEBF13A3` FOREIGN KEY (`blogReviewerId`) REFERENCES `siteuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for dic
-- ----------------------------
CREATE TABLE `dic` (
  `id` int(11) NOT NULL auto_increment,
  `entryId` varchar(4) NOT NULL,
  `entryOrder` varchar(2) NOT NULL,
  `entryVal` varchar(50) NOT NULL,
  `isEnabled` bit(1) NOT NULL,
  `catType` varchar(31) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for event
-- ----------------------------
CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `author` varchar(20) collate utf8_unicode_ci NOT NULL,
  `content` longtext collate utf8_unicode_ci NOT NULL,
  `modifyDate` datetime default NULL,
  `publishDate` datetime NOT NULL,
  `title` varchar(200) collate utf8_unicode_ci NOT NULL,
  `columnId` int(11) default NULL,
  `managerId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK403827AF473E29` (`managerId`),
  CONSTRAINT `FK403827AF473E29` FOREIGN KEY (`managerId`) REFERENCES `siteuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for event_paricipator
-- ----------------------------
CREATE TABLE `event_paricipator` (
  `eventId` int(11) NOT NULL,
  `paricipatorId` int(11) NOT NULL,
  PRIMARY KEY  (`eventId`,`paricipatorId`),
  KEY `FKBFAE8613B1C603F4` (`paricipatorId`),
  KEY `FKBFAE8613D63D5D40` (`eventId`),
  CONSTRAINT `FKBFAE8613D63D5D40` FOREIGN KEY (`eventId`) REFERENCES `event` (`id`),
  CONSTRAINT `FKBFAE8613B1C603F4` FOREIGN KEY (`paricipatorId`) REFERENCES `siteuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for eventcomment
-- ----------------------------
CREATE TABLE `eventcomment` (
  `id` int(11) NOT NULL,
  `commentTime` datetime default NULL,
  `content` varchar(200) collate utf8_unicode_ci NOT NULL,
  `blogId` int(11) default NULL,
  `eventReviewerId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK7894268529FA009B` (`eventReviewerId`),
  KEY `FK78942685D5A9F848` (`blogId`),
  CONSTRAINT `FK78942685D5A9F848` FOREIGN KEY (`blogId`) REFERENCES `event` (`id`),
  CONSTRAINT `FK7894268529FA009B` FOREIGN KEY (`eventReviewerId`) REFERENCES `siteuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for siteuser
-- ----------------------------
CREATE TABLE `siteuser` (
  `id` int(11) NOT NULL auto_increment,
  `nickName` varchar(50) character set utf8 collate utf8_unicode_ci NOT NULL,
  `userId` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `blog` VALUES ('1', 'cleverpig', '最新内容', '2011-02-18 13:30:41', '2011-02-18 11:05:36', 'new title', '1', null);
INSERT INTO `blog` VALUES ('2', 'cleverpig', '新内容', null, '2011-02-18 11:05:37', '新标题', '1', null);
INSERT INTO `blog` VALUES ('3', 'cleverpig', 'new content', null, '2011-02-18 13:30:37', 'new title', '1', null);
INSERT INTO `blog` VALUES ('4', 'cleverpig', '新标题', null, '2011-02-18 13:30:39', '新内容', '1', null);
INSERT INTO `blogcomment` VALUES ('1', '2011-02-18 11:05:37', '呵呵,评论一下', '2', '1');
INSERT INTO `blogcomment` VALUES ('2', '2011-02-18 11:05:39', '呵呵,新评论-2', '1', '1');
INSERT INTO `blogcomment` VALUES ('3', '2011-02-18 11:05:39', '呵呵,新评论-1', '1', '1');
INSERT INTO `blogcomment` VALUES ('4', '2011-02-18 13:30:39', '咱来评论一下', '4', '1');
INSERT INTO `blogcomment` VALUES ('5', '2011-02-18 13:30:41', '呵呵,新评论-2', '1', '1');
INSERT INTO `blogcomment` VALUES ('6', '2011-02-18 13:30:41', '呵呵,新评论-1', '1', '1');
INSERT INTO `dic` VALUES ('1', '01', '01', '栏目01', '', 'COLUMN');
INSERT INTO `dic` VALUES ('2', '02', '02', '栏目02', '', 'COLUMN');
INSERT INTO `dic` VALUES ('3', '03', '03', '栏目03', '', 'COLUMN');
INSERT INTO `dic` VALUES ('4', '04', '04', '栏目04', '', 'COLUMN');
INSERT INTO `dic` VALUES ('5', '05', '05', '栏目05', '', 'COLUMN');
INSERT INTO `dic` VALUES ('6', '06', '06', '栏目06', '', 'COLUMN');
INSERT INTO `siteuser` VALUES ('1', '聪明的猪', 'cleverpig');
INSERT INTO `siteuser` VALUES ('2', '约翰', 'john');

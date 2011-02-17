/*
MySQL Data Transfer
Source Host: localhost
Source Database: magiccat
Target Host: localhost
Target Database: magiccat
Date: 2011-2-17 17:16:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for blog
-- ----------------------------
CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `author` varchar(20) NOT NULL,
  `content` longtext character set utf8 collate utf8_unicode_ci NOT NULL,
  `modifyDate` datetime default NULL,
  `publishDate` datetime NOT NULL,
  `title` varchar(200) character set utf8 collate utf8_unicode_ci NOT NULL,
  `columnId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for blogcomment
-- ----------------------------
CREATE TABLE `blogcomment` (
  `id` int(11) NOT NULL,
  `commentTime` datetime default NULL,
  `content` varchar(200) character set utf8 collate utf8_unicode_ci NOT NULL,
  `userId` varchar(50) NOT NULL,
  `blogId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2315843D2248F04E` (`blogId`),
  CONSTRAINT `FK2315843D2248F04E` FOREIGN KEY (`blogId`) REFERENCES `blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `author` varchar(20) NOT NULL,
  `content` longtext NOT NULL,
  `modifyDate` datetime default NULL,
  `publishDate` datetime NOT NULL,
  `title` varchar(200) NOT NULL,
  `columnId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for event_participator
-- ----------------------------
CREATE TABLE `event_participator` (
  `event_id` int(11) NOT NULL,
  `paricipator_id` int(11) NOT NULL,
  PRIMARY KEY  (`event_id`,`paricipator_id`),
  KEY `FKBBCD8F0F38DCDCEB` (`event_id`),
  KEY `FKBBCD8F0FB454AD7B` (`paricipator_id`),
  CONSTRAINT `FKBBCD8F0FB454AD7B` FOREIGN KEY (`paricipator_id`) REFERENCES `participator` (`id`),
  CONSTRAINT `FKBBCD8F0F38DCDCEB` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for eventcomment
-- ----------------------------
CREATE TABLE `eventcomment` (
  `id` int(11) NOT NULL,
  `commentTime` datetime default NULL,
  `content` varchar(200) NOT NULL,
  `userId` varchar(50) NOT NULL,
  `blogId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK78942685D5A9F848` (`blogId`),
  CONSTRAINT `FK78942685D5A9F848` FOREIGN KEY (`blogId`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for participator
-- ----------------------------
CREATE TABLE `participator` (
  `id` int(11) NOT NULL auto_increment,
  `nickName` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `blog` VALUES ('1', 'cleverpig', '最新内容', '2011-02-17 17:03:43', '2011-02-17 14:07:45', 'new title', '1');
INSERT INTO `blog` VALUES ('2', 'cleverpig', 'new content', null, '2011-02-17 14:08:03', 'new title', '1');
INSERT INTO `blog` VALUES ('3', 'cleverpig', 'new content', null, '2011-02-17 14:33:19', 'new title', '1');
INSERT INTO `blog` VALUES ('4', 'cleverpig', 'new content', null, '2011-02-17 14:36:40', 'new title', '1');
INSERT INTO `blog` VALUES ('5', 'cleverpig', 'new content', null, '2011-02-17 14:36:41', 'new title', '1');
INSERT INTO `blog` VALUES ('6', 'cleverpig', 'new content', null, '2011-02-17 14:38:01', 'new title', '1');
INSERT INTO `blog` VALUES ('7', 'cleverpig', 'new content', null, '2011-02-17 14:38:02', 'new title', '1');
INSERT INTO `blog` VALUES ('8', 'cleverpig', 'new content', null, '2011-02-17 14:42:42', 'new title', '1');
INSERT INTO `blog` VALUES ('9', 'cleverpig', '新内容', null, '2011-02-17 14:42:43', '新标题', '1');
INSERT INTO `blog` VALUES ('10', 'cleverpig', 'new content', null, '2011-02-17 14:45:57', 'new title', '1');
INSERT INTO `blog` VALUES ('11', 'cleverpig', '新内容', null, '2011-02-17 14:45:58', '新标题', '1');
INSERT INTO `blog` VALUES ('12', 'cleverpig', 'new content', null, '2011-02-17 14:50:43', 'new title', '1');
INSERT INTO `blog` VALUES ('13', 'cleverpig', '新内容', null, '2011-02-17 14:50:44', '新标题', '1');
INSERT INTO `blog` VALUES ('14', 'cleverpig', 'new content', null, '2011-02-17 15:00:05', 'new title', '1');
INSERT INTO `blog` VALUES ('15', 'cleverpig', '新内容', null, '2011-02-17 15:00:05', '新标题', '1');
INSERT INTO `blog` VALUES ('16', 'cleverpig', 'new content', null, '2011-02-17 15:04:12', 'new title', '1');
INSERT INTO `blog` VALUES ('17', 'cleverpig', '新内容', null, '2011-02-17 15:04:13', '新标题', '1');
INSERT INTO `blogcomment` VALUES ('1', '2011-02-17 14:38:02', '呵呵,评论一下', 'cleverpig', '7');
INSERT INTO `blogcomment` VALUES ('2', '2011-02-17 14:42:43', '呵呵,评论一下', 'cleverpig', '9');
INSERT INTO `blogcomment` VALUES ('3', '2011-02-17 14:45:58', '呵呵,评论一下', 'cleverpig', '11');
INSERT INTO `blogcomment` VALUES ('4', '2011-02-17 14:50:44', '呵呵,评论一下', 'cleverpig', '13');
INSERT INTO `blogcomment` VALUES ('5', '2011-02-17 15:00:05', '呵呵,评论一下', 'cleverpig', '15');
INSERT INTO `blogcomment` VALUES ('6', '2011-02-17 15:04:13', '呵呵,评论一下', 'cleverpig', '17');
INSERT INTO `blogcomment` VALUES ('7', '2011-02-17 15:31:27', '呵呵,新评论-1', 'cleverpig', '1');
INSERT INTO `blogcomment` VALUES ('8', '2011-02-17 15:31:27', '呵呵,新评论-2', 'cleverpig', '1');
INSERT INTO `blogcomment` VALUES ('9', '2011-02-17 17:03:43', '呵呵,新评论-1', 'cleverpig', '1');
INSERT INTO `dic` VALUES ('1', '01', '01', '栏目01', '', 'COLUMN');
INSERT INTO `dic` VALUES ('2', '02', '02', '栏目02', '', 'COLUMN');
INSERT INTO `dic` VALUES ('3', '03', '03', '栏目03', '', 'COLUMN');
INSERT INTO `dic` VALUES ('4', '04', '04', '栏目04', '', 'COLUMN');
INSERT INTO `dic` VALUES ('5', '05', '05', '栏目05', '', 'COLUMN');
INSERT INTO `dic` VALUES ('6', '06', '06', '栏目06', '', 'COLUMN');

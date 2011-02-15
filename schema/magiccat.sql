/*
MySQL Data Transfer
Source Host: localhost
Source Database: magiccat
Target Host: localhost
Target Database: magiccat
Date: 2011-2-15 17:33:30
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for article
-- ----------------------------
CREATE TABLE `article` (
  `id` int(11) NOT NULL auto_increment,
  `author` varchar(20) NOT NULL,
  `content` longtext NOT NULL,
  `modifyDate` datetime default NULL,
  `publishDate` datetime NOT NULL,
  `title` varchar(200) NOT NULL,
  `columnId` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD458CCF68F6C3F3A` (`columnId`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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
-- Records 
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'cleverpig', 'new content', null, '2011-01-31 16:11:13', 'new title', null);
INSERT INTO `article` VALUES ('2', '', 'aaaa', null, '2011-01-31 22:29:52', 'aaa', null);
INSERT INTO `article` VALUES ('3', 'cleverpig', 'new content', null, '2011-02-12 11:30:38', 'new title', null);
INSERT INTO `article` VALUES ('4', 'cleverpig', 'new content', null, '2011-02-15 17:22:11', 'new title', null);
INSERT INTO `article` VALUES ('5', 'cleverpig', 'new content', null, '2011-02-15 17:27:17', 'new title', '1');
INSERT INTO `article` VALUES ('6', 'cleverpig', 'new content', null, '2011-02-15 17:28:54', 'new title', '1');
INSERT INTO `article` VALUES ('7', 'cleverpig', 'new content', null, '2011-02-15 17:29:12', 'new title', '1');
INSERT INTO `dic` VALUES ('1', '01', '01', '栏目01', '', 'COLUMN');
INSERT INTO `dic` VALUES ('2', '02', '02', '栏目02', '', 'COLUMN');
INSERT INTO `dic` VALUES ('3', '03', '03', '栏目03', '', 'COLUMN');
INSERT INTO `dic` VALUES ('4', '04', '04', '栏目04', '', 'COLUMN');
INSERT INTO `dic` VALUES ('5', '05', '05', '栏目05', '', 'COLUMN');
INSERT INTO `dic` VALUES ('6', '06', '06', '栏目06', '', 'COLUMN');

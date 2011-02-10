/*
Navicat MySQL Data Transfer

Source Server         : magiccat@localhost
Source Server Version : 50141
Source Host           : localhost:3306
Source Database       : magiccat

Target Server Type    : MYSQL
Target Server Version : 50141
File Encoding         : 65001

Date: 2011-02-06 16:27:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) NOT NULL,
  `content` longtext NOT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `publishDate` datetime NOT NULL,
  `title` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO article VALUES ('1', 'cleverpig', 'new content', null, '2011-01-31 16:11:13', 'new title');
INSERT INTO article VALUES ('2', '', 'aaaa', null, '2011-01-31 22:29:52', 'aaa');

-- ----------------------------
-- Table structure for `dic`
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catTypes` varchar(4) NOT NULL,
  `entryId` varchar(4) NOT NULL,
  `entryOrder` varchar(2) NOT NULL,
  `entryVal` varchar(50) NOT NULL,
  `isEnabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic
-- ----------------------------
INSERT INTO dic VALUES ('1', '01', '01', '01', '栏目01', '');
INSERT INTO dic VALUES ('2', '01', '02', '02', '栏目02', '');

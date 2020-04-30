/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : wzs

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 30/04/2020 13:38:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1003 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1000, 'wzs01@163.com', '0a7cd2f29c2c46302b0002ac6ecf6d03');
INSERT INTO `account` VALUES (1001, 'wzs02@163.com', 'c9a6d3a645392a8f0d6a7514aa59d712');
INSERT INTO `account` VALUES (1002, 'wzs03@163.com', '74b5d158e3a6263c26cddc5946d04e1e');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` bigint(0) NOT NULL,
  `password` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '123456', 'admin@163.com');

-- ----------------------------
-- Table structure for micro_evidence
-- ----------------------------
DROP TABLE IF EXISTS `micro_evidence`;
CREATE TABLE `micro_evidence`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `authorId` int(0) NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `citedPaper` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `judge` int(0) NULL DEFAULT 0,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of micro_evidence
-- ----------------------------
INSERT INTO `micro_evidence` VALUES (3, 1, '1-2-13', '123-312-4', '1-2-3', 'title111111111', '2020-04-28 18:55:30', 0, 'title332');
INSERT INTO `micro_evidence` VALUES (4, 0, '', '1232-213', 'undefined', '213123124141412', '2020-04-26 16:25:45', 0, '12312414');
INSERT INTO `micro_evidence` VALUES (5, 0, '1-4', '124', '213213-123', '213123', '2020-04-26 17:25:04', 0, '21321');
INSERT INTO `micro_evidence` VALUES (6, 0, '2', '123', '124', '123', '2020-04-26 17:29:31', 0, '123');
INSERT INTO `micro_evidence` VALUES (7, 0, '13-14', '213', '43243', '213', '2020-04-26 17:29:50', 0, '123123');
INSERT INTO `micro_evidence` VALUES (8, 0, '22', '231', '123', '1243', '2020-04-26 17:31:24', 0, '14');
INSERT INTO `micro_evidence` VALUES (9, 0, '13', '14', '12321', '124', '2020-04-26 17:32:50', 0, '124');
INSERT INTO `micro_evidence` VALUES (10, 0, '20-14', '122', '123-12321', '213123', '2020-04-27 14:22:18', 0, '123123');

-- ----------------------------
-- Table structure for micro_guess
-- ----------------------------
DROP TABLE IF EXISTS `micro_guess`;
CREATE TABLE `micro_guess`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `authorId` int(0) NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `citedEvidList` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `judge` int(0) NULL DEFAULT 0,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of micro_guess
-- ----------------------------
INSERT INTO `micro_guess` VALUES (3, 0, '1-13-4', '4-9-6', '13231-12', 'test1231221', '2020-04-29 00:08:49', 0, 'test11');

-- ----------------------------
-- Table structure for micro_notice
-- ----------------------------
DROP TABLE IF EXISTS `micro_notice`;
CREATE TABLE `micro_notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` int(0) NULL DEFAULT 0,
  `authorId` int(0) NULL DEFAULT NULL,
  `authorName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reference` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `judge` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of micro_notice
-- ----------------------------
INSERT INTO `micro_notice` VALUES (1, 1, 1, 'wzs', '1-2-3', '1-2-3', 'a-b-c', 'title', 'summary', '2020-04-30 13:35:02', 0);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `id` int(0) NOT NULL,
  `topicName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `flag` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (1, '马克思主义', 1);
INSERT INTO `topic` VALUES (2, '哲学', 1);
INSERT INTO `topic` VALUES (3, '社会科学总论', 1);
INSERT INTO `topic` VALUES (4, '政治法律', 1);
INSERT INTO `topic` VALUES (5, '军事', 1);
INSERT INTO `topic` VALUES (6, '经济', 1);
INSERT INTO `topic` VALUES (7, '文化', 1);
INSERT INTO `topic` VALUES (8, '语言', 1);
INSERT INTO `topic` VALUES (9, '文学', 1);
INSERT INTO `topic` VALUES (10, '艺术', 1);
INSERT INTO `topic` VALUES (11, '历史地理', 1);
INSERT INTO `topic` VALUES (12, '自然科学总论', 1);
INSERT INTO `topic` VALUES (13, ' 数理科学和化学', 1);
INSERT INTO `topic` VALUES (14, '天文学、地球科学', 1);
INSERT INTO `topic` VALUES (15, '生物科学', 1);
INSERT INTO `topic` VALUES (16, '医药、卫生', 1);
INSERT INTO `topic` VALUES (17, '农业科学', 1);
INSERT INTO `topic` VALUES (18, '工业技术', 1);
INSERT INTO `topic` VALUES (19, '交通运输', 1);
INSERT INTO `topic` VALUES (20, '航空、航天', 1);
INSERT INTO `topic` VALUES (21, '环境科学', 1);
INSERT INTO `topic` VALUES (22, '综合', 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `signature` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `works` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expertise` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `interest` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `contribution` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1003 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1000, 'wzs01@163.com', '张华', '男', '天津市', 'fighting!', '博士', '研究员', '1:Computer Sciences-2:Data Mining', '1:Statistics & Probability-2:High-performance computing-3:Music', '对数据挖掘领域感兴趣', '研究方向为数据挖掘');
INSERT INTO `user_info` VALUES (1001, 'wzs02@163.com', '王小伟', '男', '北京市', 'work hard', '本科生', '学生', '1:Computer Sciences-2:Data Mining', '1:Statistics & Probability-2:High-performance computing-3:Music', '对数据挖掘领域感兴趣', '研究方向为数据挖掘');
INSERT INTO `user_info` VALUES (1002, 'wzs03@163.com', '王莞尔', '女', '浙江省杭州市', 'work hard', '本科生', '学生', '1:Computer Sciences-2:Data Mining', '1:Statistics & Probability-2:High-performance computing-3:Music', '对数据挖掘领域感兴趣', '研究方向为数据挖掘');

SET FOREIGN_KEY_CHECKS = 1;

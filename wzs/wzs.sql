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

 Date: 13/05/2020 21:30:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activeCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `activeStatus` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'wzs1@163.com', '740cf33d334637bf2b3d00433cea6ec3', '', 1);
INSERT INTO `account` VALUES (2, 'wzs2@163.com', '0103b33cd3ddbf771af44b60418e0365', '', 1);
INSERT INTO `account` VALUES (3, 'wzs3@163.com', 'a8d324fb901015bef22df9ae607c0e65', '', 1);
INSERT INTO `account` VALUES (4, 'wzs4@163.com', '32db4b4d3d6d37bd0c02b1647645ab30', '', 1);
INSERT INTO `account` VALUES (5, 'wzs5@163.com', '635282d5f184b759b5730351016a39ba', '', 1);
INSERT INTO `account` VALUES (6, 'wzs6@163.com', 'bd717bd1e47d94bf67403dde69d54583', '', 1);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(0) NOT NULL,
  `password` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '123456', 'admin@163.com');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `noticeId` int(0) NULL DEFAULT NULL,
  `authorId` int(0) NULL DEFAULT NULL,
  `fromId` int(0) NULL DEFAULT NULL,
  `fromName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `toId` int(0) NULL DEFAULT NULL,
  `toName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 1, 2, 'Kenneth Frumkin', 0, NULL, 'great idea!', '2020-05-01 15:37:41');
INSERT INTO `comment` VALUES (2, 2, 2, 3, 'Jan Vandenbrande', 0, NULL, 'I think this is a good idea', '2020-05-01 15:37:41');
INSERT INTO `comment` VALUES (3, 1, 1, 3, 'Jan Vandenbrande', 2, 'Kenneth Frumkin', 'right!', '2020-05-01 15:37:41');
INSERT INTO `comment` VALUES (4, 1, 1, 2, 'Kenneth Frumkin', 0, NULL, 'good', '2020-05-01 15:37:40');
INSERT INTO `comment` VALUES (5, 2, 2, 3, 'Jan Vandenbrande', 0, NULL, 'welcome advice :)', '2020-05-01 15:37:44');
INSERT INTO `comment` VALUES (6, 1, 1, 3, 'Jan Vandenbrande', 2, 'Kenneth Frumkin', 'This idea helped me a lot', '2020-05-01 15:37:44');

-- ----------------------------
-- Table structure for favorite_info
-- ----------------------------
DROP TABLE IF EXISTS `favorite_info`;
CREATE TABLE `favorite_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `userID` int(0) NULL DEFAULT NULL,
  `noticeID` int(0) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite_info
-- ----------------------------
INSERT INTO `favorite_info` VALUES (1, 1, 1, '2020-04-30 13:35:02');
INSERT INTO `favorite_info` VALUES (2, 1, 2, '2020-04-30 13:35:12');
INSERT INTO `favorite_info` VALUES (3, 2, 1, '2020-04-30 13:35:22');
INSERT INTO `favorite_info` VALUES (4, 1, 7, '2020-05-13 20:39:55');

-- ----------------------------
-- Table structure for follow_info
-- ----------------------------
DROP TABLE IF EXISTS `follow_info`;
CREATE TABLE `follow_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `followerID` int(0) NULL DEFAULT NULL,
  `followingID` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of follow_info
-- ----------------------------
INSERT INTO `follow_info` VALUES (1, 1, 2);
INSERT INTO `follow_info` VALUES (2, 2, 1);
INSERT INTO `follow_info` VALUES (3, 3, 1);

-- ----------------------------
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like`  (
  `noticeId` int(0) NOT NULL,
  `id` int(0) NOT NULL,
  PRIMARY KEY (`noticeId`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of like
-- ----------------------------
INSERT INTO `like` VALUES (1, 2);
INSERT INTO `like` VALUES (1, 3);
INSERT INTO `like` VALUES (1, 4);
INSERT INTO `like` VALUES (2, 5);
INSERT INTO `like` VALUES (2, 6);
INSERT INTO `like` VALUES (3, 3);
INSERT INTO `like` VALUES (4, 5);
INSERT INTO `like` VALUES (7, 1);
INSERT INTO `like` VALUES (7, 2);
INSERT INTO `like` VALUES (7, 3);
INSERT INTO `like` VALUES (7, 4);
INSERT INTO `like` VALUES (8, 2);
INSERT INTO `like` VALUES (8, 3);
INSERT INTO `like` VALUES (8, 6);
INSERT INTO `like` VALUES (9, 1);
INSERT INTO `like` VALUES (9, 4);
INSERT INTO `like` VALUES (10, 2);
INSERT INTO `like` VALUES (10, 3);
INSERT INTO `like` VALUES (10, 5);

-- ----------------------------
-- Table structure for like_num
-- ----------------------------
DROP TABLE IF EXISTS `like_num`;
CREATE TABLE `like_num`  (
  `noticeId` int(0) NOT NULL,
  `num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`noticeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of like_num
-- ----------------------------
INSERT INTO `like_num` VALUES (1, 0);
INSERT INTO `like_num` VALUES (2, 0);
INSERT INTO `like_num` VALUES (3, 0);
INSERT INTO `like_num` VALUES (4, 0);
INSERT INTO `like_num` VALUES (5, 0);
INSERT INTO `like_num` VALUES (6, 0);
INSERT INTO `like_num` VALUES (7, 0);
INSERT INTO `like_num` VALUES (8, 0);
INSERT INTO `like_num` VALUES (9, 0);
INSERT INTO `like_num` VALUES (10, 0);
INSERT INTO `like_num` VALUES (11, 0);
INSERT INTO `like_num` VALUES (12, 0);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT 0,
  `fromUserId` int(0) NULL DEFAULT NULL,
  `fromUserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relatedNoticeId` int(0) NULL DEFAULT NULL,
  `relatedNoticeType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relatedNoticeTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `flag` int(0) NULL DEFAULT 1,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 3, 1, 2, 'Kenneth Frumkin', 1, '1', 'title1', '2020-05-01 20:42:45', 0, NULL);
INSERT INTO `message` VALUES (2, 1, 2, 2, 'Kenneth Frumkin', 2, '2', 'title2', '2020-05-08 17:33:46', 0, NULL);
INSERT INTO `message` VALUES (3, 2, 5, 1, 'Changqing Cheng', NULL, NULL, 'title3', '2020-05-08 17:33:45', 0, NULL);
INSERT INTO `message` VALUES (4, 1, 4, 3, 'Jan Vandenbrande', 1, '1', 'title1', '2020-05-08 17:33:44', 0, '我评论了你');
INSERT INTO `message` VALUES (5, 6, 4, 1, 'Changqing Cheng', 1, '1', 'title1', '2020-05-10 21:11:44', 0, '这是一条评论消息!');
INSERT INTO `message` VALUES (6, 2, 5, 1, 'Changqing Cheng', 1, '1', 'title1', '2020-05-10 21:14:27', 0, '这是一条评论回复的消息');
INSERT INTO `message` VALUES (7, 1, 6, 0, NULL, 1, '1', 'title', '2020-05-11 22:57:55', 0, NULL);
INSERT INTO `message` VALUES (8, 1, 7, 0, NULL, 1, '1', 'title', '2020-05-11 23:01:51', 0, NULL);
INSERT INTO `message` VALUES (9, 5, 3, 1, 'Changqing Cheng', 7, '2', 'Complex systems', '2020-05-13 20:39:55', 0, NULL);
INSERT INTO `message` VALUES (10, 4, 2, 1, 'Changqing Cheng', 8, '2', 'Remote culture telemedicine', '2020-05-13 21:12:53', 0, NULL);
INSERT INTO `message` VALUES (11, 4, 3, 1, 'Changqing Cheng', 8, '2', 'Remote culture telemedicine', '2020-05-13 21:12:57', 0, NULL);

-- ----------------------------
-- Table structure for micro_notice
-- ----------------------------
DROP TABLE IF EXISTS `micro_notice`;
CREATE TABLE `micro_notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` int(0) NULL DEFAULT 0,
  `authorId` int(0) NULL DEFAULT NULL,
  `authorName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reference` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `judge` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of micro_notice
-- ----------------------------
-- INSERT INTO `micro_notice` VALUES (1, 1, 5, 'Xiao Liu', '21', 'Liu, X., Gopal, V., and Kalagnanam, J. (2018), \"\"A Spatio-Temporal Modeling Framework for Weather Radar Image Data in Tropical Southeast Asia\"\",  Annals of Applied Statistics, 12(1), 378–407.', 'partial differential equations-spatio temporal model-uncertainty quantification-statistical learning', 'Weather Radar Image Data', 'Leveraging both data-driven approaches and physics knowledge for modeling and uncertainty quantification of spatio-temporal data arising from a generic class of convection-diffusion processes.', '', '2019-08-14 13:35:02', 1);
-- INSERT INTO `micro_notice` VALUES (2, 1, 3, 'Jan Vandenbrande', '21-30-22', 'Keyes et al., Multiphysics Simulations: Challenges and Opportunities, International Journal of High Performance Computing Applications.', 'modeling multi physics-multi scale-simulation', 'Multiphysics Simulations', 'Comprehensive survey paper on the difficulties (effort, limitations, accuracy) of building multi-physics simulators.', '', '2019-08-17 17:35:02', 1);
-- INSERT INTO `micro_notice` VALUES (3, 1, 6, 'Diane Stevens', '23', 'Crawford, Chris. “Inside Look at Using Telemedicine During COVID-19 Pandemic.” AAFP Home, American Academy of Family Physicians (AAFP), 23 Mar. 2020, www.aafp.org/news/health-of-the-public/20200323covidtelehealth.html.', '', 'Telehealth', 'Telehealth has become a useful tool to provide health care during the coronavirus crisis. Converting to telemedicine has the benefit of not worrying about adequate test kits or N95 masks or other PPE.', '', '2020-04-17 12:40:02', 1);
-- INSERT INTO `micro_notice` VALUES (4, 1, 4, 'Matt Mihelic, MD', '23', 'Center for Medicare and Medicaid Services, “Physicians and Other Clinicians: CMS Flexibilities to Fight COVID-19”, April 29, 2020.', '', 'COVID 19', 'The US Federal Government is recognizing the need for telehealth and is responding with an unprecedented array of temporary regulatory waivers and new rules that will enable telemedicine.', '', '2020-05-05 13:35:02', 1);
-- INSERT INTO `micro_notice` VALUES (5, 1, 1, 'Changqing Cheng', '2-3', 'Madden KS, Boehm GW, Lee SC, Grota LJ, Cohen N, Ader R. One-trial conditioning of the antibody response to hen egg lysozyme in rats. J Neuroimmunol. 2001;113:236-239.', 'rats-protein antigen', 'Protein antigen', 'In rats, after a single pairing of a novel taste with a protein antigen, the taste alone could elicit an IgG antibody response.', '', '2020-05-07 13:35:02', 0);
-- INSERT INTO `micro_notice` VALUES (6, 1, 2, 'Kenneth Frumkin', '11-12', 'Frumkin, K: Behavioral Conditioning, the Placebo Effect, and Emergency Department Pain Management. J. Emerg Med, 2020; in press. Version accepted for publication (MSWord), April 2020', '', 'The immune system', 'Review of behavioral conditioning of the immune system and drug effects. Benign taste stimuli paired with immunoactive or pharmacologic agents, when administered alone, elicit the same response.', '', '2020-05-08 13:35:02', 1);
-- INSERT INTO `micro_notice` VALUES (7, 2, 1, 'Changqing Cheng', '22-23', '1-2', 'Complex systems', 'Complex systems', 'While complex systems generally exhibit multi-scale and multi-physics behaviors, machine learning can make the tedious simulation realistic.', '', '2020-04-26 13:35:02', 1);
-- INSERT INTO `micro_notice` VALUES (8, 2, 1, 'Changqing Cheng', '2-3', '3-4', '', 'Remote culture telemedicine', 'How will issues such as credentialing, privacy, payment, liability, and regulation be addressed in remote culture telemedicine?', '', '2020-05-09 13:35:02', 1);
-- INSERT INTO `micro_notice` VALUES (9, 2, 2, 'Kenneth Frumkin', '2-3', '5-6', '', 'COVID', 'Can pairing a unique taste with COVID vaccine create a safe, cheap, conditioned stimulus to boost immunity? Could warfighters carry such conditioned immunity boosters downrange in the event of tactical exposure to biological agents?', '', '2020-05-09 13:35:02', 1);
-- INSERT INTO `micro_notice` VALUES (10, 1, 2, 'Kenneth Frumkin', '0-11-13-26', 'Cohen, V., et al. (2015). \"\"Development of an opioid reduction protocol in an emergency department.\"\" Am J Health Syst Pharm 72(23): 2080-2086.', 'pain-pain management-non opioids-combat-injury-analgesia-multimodal-certa', 'CERTA', 'In a 2015 proof-of-concept study, emergency patients with pain were successfully treated with an opioid-sparing protocol based on principles of channel enzyme receptor–targeted analgesia (CERTA).', '','2018-10-26 13:35:02', 0);
-- INSERT INTO `micro_notice` VALUES (11, 1, 3, 'Jan Vandenbrande', '21-30-22', 'Michopoulos, Farhat, Fish, \"\"Modeling and Simulation of Multiphysics Systems\"\", Trans. of the ASME, Vol 5.', 'modeling multi physics-multi scale-simulation', 'Multi physics systems', 'Survey paper describing challenges in multi-physics systems.', '', '2019-08-17 19:25:02', 0);
-- INSERT INTO `micro_notice` VALUES (12, 1, 3, 'Jan Vandenbrande', '1-12-14-27', 'Cohen, V., et al. (2015). \"\"Development of an opioid reduction protocol in an emergency department. Am J Health Syst Pharm 72(23): 2080-2086.', 'pain-pain management-non opioids-combat-injury-analgesia-multimodal-certa', 'CERTA', 'In a 2015 proof-of-concept study, emergency patients with pain were successfully treated with an opioid-sparing protocol based on principles of channel enzyme receptor–targeted analgesia (CERTA).', '', '2018-10-26 11:32:02', -1);

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `userId` int(0) NOT NULL,
  `noticeId` int(0) NOT NULL,
  `type` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`, `noticeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES (2, 5, 1);
INSERT INTO `review` VALUES (3, 5, 1);
INSERT INTO `review` VALUES (4, 5, -1);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `id` int(0) NOT NULL,
  `topicName` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `flag` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (0, '生命科学', 1);
INSERT INTO `topic` VALUES (1, '生物化学、生物物理学和结构生物学', 1);
INSERT INTO `topic` VALUES (2, '细胞与发育生物学', 1);
INSERT INTO `topic` VALUES (3, '生态学与进化生物学', 1);
INSERT INTO `topic` VALUES (4, '公共卫生', 1);
INSERT INTO `topic` VALUES (5, '遗传学和基因组学', 1);
INSERT INTO `topic` VALUES (6, '免疫学与传染病', 1);
INSERT INTO `topic` VALUES (7, '生物学、综合生物学和综合生物医学', 1);
INSERT INTO `topic` VALUES (8, '科学', 1);
INSERT INTO `topic` VALUES (9, '运动学', 1);
INSERT INTO `topic` VALUES (10, '微生物学', 1);
INSERT INTO `topic` VALUES (11, '神经科学与神经生物学', 1);
INSERT INTO `topic` VALUES (12, '护理部', 1);
INSERT INTO `topic` VALUES (13, '药理学、毒理学和环境卫生', 1);
INSERT INTO `topic` VALUES (14, '生理学', 1);
INSERT INTO `topic` VALUES (15, '动物科学', 1);
INSERT INTO `topic` VALUES (16, '昆虫学', 1);
INSERT INTO `topic` VALUES (17, '食品科学', 1);
INSERT INTO `topic` VALUES (18, '林业与森林科学', 1);
INSERT INTO `topic` VALUES (19, '营养', 1);
INSERT INTO `topic` VALUES (20, '植物科学', 1);
INSERT INTO `topic` VALUES (21, '物理科学与数学', 1);
INSERT INTO `topic` VALUES (22, '物理', 1);
INSERT INTO `topic` VALUES (23, '统计与概率', 1);
INSERT INTO `topic` VALUES (24, '工程', 1);
INSERT INTO `topic` VALUES (25, '航空航天工程', 1);
INSERT INTO `topic` VALUES (26, '生物医学工程与生物工程', 1);
INSERT INTO `topic` VALUES (27, '化学工程', 1);
INSERT INTO `topic` VALUES (28, '土木与环境工程', 1);
INSERT INTO `topic` VALUES (29, '计算机工程', 1);
INSERT INTO `topic` VALUES (30, '应用数学', 1);
INSERT INTO `topic` VALUES (31, '天体物理学和天文学', 1);
INSERT INTO `topic` VALUES (32, '化学', 1);
INSERT INTO `topic` VALUES (33, '计算机科学', 1);
INSERT INTO `topic` VALUES (34, '地球科学', 1);
INSERT INTO `topic` VALUES (35, '数学', 1);
INSERT INTO `topic` VALUES (36, '海洋学、大气科学和气象学', 1);
INSERT INTO `topic` VALUES (37, '电气与计算机工程', 1);
INSERT INTO `topic` VALUES (38, '工程科学与材料', 1);
INSERT INTO `topic` VALUES (39, '机械工程', 1);
INSERT INTO `topic` VALUES (40, '运筹学、系统工程与工业工程', 1);
INSERT INTO `topic` VALUES (41, '社会与行为科学', 1);
INSERT INTO `topic` VALUES (42, '农业与资源经济学', 1);
INSERT INTO `topic` VALUES (43, '人类学', 1);
INSERT INTO `topic` VALUES (44, '沟通', 1);
INSERT INTO `topic` VALUES (45, '经济', 1);
INSERT INTO `topic` VALUES (46, '地理', 1);
INSERT INTO `topic` VALUES (47, '语言学', 1);
INSERT INTO `topic` VALUES (48, '政治学', 1);
INSERT INTO `topic` VALUES (49, '公共事务、公共政策和公共行政', 1);
INSERT INTO `topic` VALUES (50, '心理学', 1);
INSERT INTO `topic` VALUES (51, '社会性', 1);
INSERT INTO `topic` VALUES (52, '艺术与人文', 1);
INSERT INTO `topic` VALUES (53, '美国研究', 1);
INSERT INTO `topic` VALUES (54, '经典', 1);
INSERT INTO `topic` VALUES (55, '比较文学', 1);
INSERT INTO `topic` VALUES (56, '英语语言文学', 1);
INSERT INTO `topic` VALUES (57, '法语和法语语言文学', 1);
INSERT INTO `topic` VALUES (58, '德语与文学', 1);
INSERT INTO `topic` VALUES (59, '语言、社会和文化', 1);
INSERT INTO `topic` VALUES (60, '历史', 1);
INSERT INTO `topic` VALUES (61, '艺术、建筑和考古学史', 1);
INSERT INTO `topic` VALUES (62, '音乐', 1);
INSERT INTO `topic` VALUES (63, '哲学', 1);
INSERT INTO `topic` VALUES (64, '宗教', 1);
INSERT INTO `topic` VALUES (65, '西班牙语和葡萄牙语及文学', 1);
INSERT INTO `topic` VALUES (66, '戏剧与表演研究', 1);
INSERT INTO `topic` VALUES (67, '其他', 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `picture` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `signature` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `works` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expertise` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `interest` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'wzs1@163.com', 'Changqing Cheng', '', 'male',  'fighting!', 'SSIE at BU', 'SUNY Binghamton', '23-24-29', '21-30-40');
INSERT INTO `user_info` VALUES (2, 'wzs2@163.com', 'Kenneth Frumkin', '', 'male',  'work hard!', 'PhD, MD; Emergency Physician: Military Medicine; Physiological Psychologist', 'Naval Medical Center, Portsmouth, Virginia', '23-24-29', '21-30-40');
INSERT INTO `user_info` VALUES (3, 'wzs3@163.com', 'Jan Vandenbrande', '', 'male',  'fighting!', 'SSIE at BU', 'DARPA/DSO', '30-38', '21-24');
INSERT INTO `user_info` VALUES (4, 'wzs4@163.com', 'Matt Mihelic, MD', '', 'male',  'work hard!', 'Associate Professor', 'University of Tennessee', '23-24-29', '21-30-40');
INSERT INTO `user_info` VALUES (5, 'wzs5@163.com', 'Xiao Liu', '', 'female', 'fighting!', 'Assistant Professor at University of Arkansas; Former Research Scientist at IBM T.J. Watson.', 'Industrial Engineering, University of Arkansas', '30-23-40', '21-34-36-24');
INSERT INTO `user_info` VALUES (6, 'wzs6@163.com', 'Diane Stevens', '', 'male', 'work hard!', 'Decades of involvement in the humanities, the arts, public service, and science.', 'UCLA, San Francisco State University', '15-52-56-59-61-63', '3-15-19-20');

DROP TABLE IF EXISTS `user_rating`;
CREATE TABLE `user_rating`  (
  `userId` int(0) NOT NULL,
  `noticeId` int(0) NOT NULL,
  `score` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`, `noticeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_rating
-- ----------------------------
INSERT INTO `user_rating` VALUES (1, 1, 5);
INSERT INTO `user_rating` VALUES (1, 2, 5);
INSERT INTO `user_rating` VALUES (1, 3, 0);
INSERT INTO `user_rating` VALUES (1, 4, 0);
INSERT INTO `user_rating` VALUES (1, 5, 0);
INSERT INTO `user_rating` VALUES (1, 6, 2);
INSERT INTO `user_rating` VALUES (1, 7, 7);
INSERT INTO `user_rating` VALUES (1, 8, 8);
INSERT INTO `user_rating` VALUES (1, 9, 2);
INSERT INTO `user_rating` VALUES (1, 10, 0);
INSERT INTO `user_rating` VALUES (1, 11, 0);
INSERT INTO `user_rating` VALUES (1, 12, 0);
INSERT INTO `user_rating` VALUES (1, 13, 0);
INSERT INTO `user_rating` VALUES (1, 14, 0);
INSERT INTO `user_rating` VALUES (1, 17, 0);
INSERT INTO `user_rating` VALUES (1, 18, 0);

SET FOREIGN_KEY_CHECKS = 1;

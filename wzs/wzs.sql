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
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activeCode` varchar(100) DEFAULT NULL,
  `activeStatus` int(0) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'wzs1@163.com', '740cf33d334637bf2b3d00433cea6ec3','',1);
INSERT INTO `account` VALUES (2, 'wzs2@163.com', '0103b33cd3ddbf771af44b60418e0365','',1);
INSERT INTO `account` VALUES (3, 'wzs3@163.com', 'a8d324fb901015bef22df9ae607c0e65','',1);
INSERT INTO `account` VALUES (4, 'wzs4@163.com', '32db4b4d3d6d37bd0c02b1647645ab30','',1);
INSERT INTO `account` VALUES (5, 'wzs5@163.com', '635282d5f184b759b5730351016a39ba','',1);
INSERT INTO `account` VALUES (6, 'wzs6@163.com', 'bd717bd1e47d94bf67403dde69d54583','',1);
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
-- Table structure for micro_evidence
-- ----------------------------
-- DROP TABLE IF EXISTS `micro_evidence`;
-- CREATE TABLE `micro_evidence`  (
--    `id` int(0) NOT NULL AUTO_INCREMENT,
--   `authorId` int(0) NULL DEFAULT NULL,
--   `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `citedPaper` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--    `time` datetime(0) NULL DEFAULT NULL,
--   `judge` int(0) NULL DEFAULT 0,
--    `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--     PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of micro_evidence
-- ----------------------------

-- INSERT INTO `micro_evidence` VALUES (3, 1, '1-2-13', '123-312-4', '1-2-3', 'title111111111', '2020-04-28 18:55:30', 0, 'title332');
-- INSERT INTO `micro_evidence` VALUES (4, 0, '', '1232-213', 'undefined', '213123124141412', '2020-04-26 16:25:45', 0, '12312414');
-- INSERT INTO `micro_evidence` VALUES (5, 0, '1-4', '124', '213213-123', '213123', '2020-04-26 17:25:04', 0, '21321');
-- INSERT INTO `micro_evidence` VALUES (6, 0, '2', '123', '124', '123', '2020-04-26 17:29:31', 0, '123');
-- INSERT INTO `micro_evidence` VALUES (7, 0, '13-14', '213', '43243', '213', '2020-04-26 17:29:50', 0, '123123');
-- INSERT INTO `micro_evidence` VALUES (8, 0, '22', '231', '123', '1243', '2020-04-26 17:31:24', 0, '14');
-- INSERT INTO `micro_evidence` VALUES (9, 0, '13', '14', '12321', '124', '2020-04-26 17:32:50', 0, '124');
-- INSERT INTO `micro_evidence` VALUES (10, 0, '20-14', '122', '123-12321', '213123', '2020-04-27 14:22:18', 0, '123123');

-- ----------------------------
-- Table structure for micro_guess
-- ----------------------------
-- DROP TABLE IF EXISTS `micro_guess`;
-- CREATE TABLE `micro_guess`  (
--   `id` int(0) NOT NULL AUTO_INCREMENT,
--   `authorId` int(0) NULL DEFAULT NULL,
--   `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
--   `citedEvidList` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
--   `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
--   `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
--   `time` datetime(0) NULL DEFAULT NULL,
--   `judge` int(0) NULL DEFAULT 0,
--   `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL,
--   PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE utf8_general_ci  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of micro_guess
-- ----------------------------
-- INSERT INTO `micro_guess` VALUES (3, 0, '1-13-4', '4-9-6', '13231-12', 'test1231221', '2020-04-29 00:08:49', 0, 'test11');

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
  `time` datetime(0) NULL DEFAULT NULL,
  `judge` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of micro_notice
-- ----------------------------
INSERT INTO `micro_notice` VALUES (1, 1, 5, 'Xiao Liu', '-21-', 'Liu, X., Gopal, V., and Kalagnanam, J. (2018), ""A Spatio-Temporal Modeling Framework for Weather Radar Image Data in Tropical Southeast Asia"",  Annals of Applied Statistics, 12(1), 378–407.', 'partial differential equations-spatio temporal model-uncertainty quantification-statistical learning', 'Weather Radar Image Data', 'Leveraging both data-driven approaches and physics knowledge for modeling and uncertainty quantification of spatio-temporal data arising from a generic class of convection-diffusion processes.', '2019-8-14 13:35:02', 1);
INSERT INTO `micro_notice` VALUES (2, 1, 3, 'Jan Vandenbrande', '-21-30-22-', 'Keyes et al., Multiphysics Simulations: Challenges and Opportunities, International Journal of High Performance Computing Applications.', 'modeling multi physics-multi scale-simulation', 'Multiphysics Simulations', 'Comprehensive survey paper on the difficulties (effort, limitations, accuracy) of building multi-physics simulators.', '2019-8-17 17:35:02', 1);
INSERT INTO `micro_notice` VALUES (3, 1, 6, 'Diane Stevens', '-23-', 'Crawford, Chris. “Inside Look at Using Telemedicine During COVID-19 Pandemic.” AAFP Home, American Academy of Family Physicians (AAFP), 23 Mar. 2020, www.aafp.org/news/health-of-the-public/20200323covidtelehealth.html.', '', 'Telehealth', 'Telehealth has become a useful tool to provide health care during the coronavirus crisis. Converting to telemedicine has the benefit of not worrying about adequate test kits or N95 masks or other PPE.', '2020-04-17 12:40:02', 1);
INSERT INTO `micro_notice` VALUES (4, 1, 4, 'Matt Mihelic, MD', '-23-', 'Center for Medicare and Medicaid Services, “Physicians and Other Clinicians: CMS Flexibilities to Fight COVID-19”, April 29, 2020.', '', 'COVID 19', 'The US Federal Government is recognizing the need for telehealth and is responding with an unprecedented array of temporary regulatory waivers and new rules that will enable telemedicine.', '2020-05-05 13:35:02', 1);
INSERT INTO `micro_notice` VALUES (5, 1, 2, 'Kenneth Frumkin', '-2-3-', 'Madden KS, Boehm GW, Lee SC, Grota LJ, Cohen N, Ader R. One-trial conditioning of the antibody response to hen egg lysozyme in rats. J Neuroimmunol. 2001;113:236-239.', '', 'protein antigen', 'In rats, after a single pairing of a novel taste with a protein antigen, the taste alone could elicit an IgG antibody response.', '2020-05-07 13:35:02', 1);
INSERT INTO `micro_notice` VALUES (6, 1, 2, 'Kenneth Frumkin', '-11-12-', 'Frumkin, K: Behavioral Conditioning, the Placebo Effect, and Emergency Department Pain Management. J. Emerg Med, 2020; in press. Version accepted for publication (MSWord), April 2020', '', 'the immune system', 'Review of behavioral conditioning of the immune system and drug effects. Benign taste stimuli paired with immunoactive or pharmacologic agents, when administered alone, elicit the same response.', '2020-05-08 13:35:02', 1);
INSERT INTO `micro_notice` VALUES (7, 2, 1, 'Changqing Cheng', '-22-23-', '1-2', '', 'complex systems', 'While complex systems generally exhibit multi-scale and multi-physics behaviors, machine learning can make the tedious simulation realistic.', '2020-04-26 13:35:02', 1);
INSERT INTO `micro_notice` VALUES (8, 2, 4, 'Matt Mihelic, MD', '-2-3-', '3-4', '', 'remote culture telemedicine', 'How will issues such as credentialing, privacy, payment, liability, and regulation be addressed in remote culture telemedicine?', '2020-05-09 13:35:02', 1);
INSERT INTO `micro_notice` VALUES (9, 2, 2, 'Kenneth Frumkin', '-2-3-', '5-6', '', 'COVID', 'Can pairing a unique taste with COVID vaccine create a safe, cheap, conditioned stimulus to boost immunity? Could warfighters carry such conditioned immunity boosters downrange in the event of tactical exposure to biological agents?', '2020-05-09 13:35:02', 1);
INSERT INTO `micro_notice` VALUES (10, 1, 2, 'Kenneth Frumkin', '-0-11-13-26-', 'Cohen, V., et al. (2015). ""Development of an opioid reduction protocol in an emergency department."" Am J Health Syst Pharm 72(23): 2080-2086.', 'pain-pain management-non opioids-combat-injury-analgesia-multimodal-certa', 'CERTA', 'In a 2015 proof-of-concept study, emergency patients with pain were successfully treated with an opioid-sparing protocol based on principles of channel enzyme receptor–targeted analgesia (CERTA).', '2018-10-26 13:35:02', 0);
INSERT INTO `micro_notice` VALUES (11, 1, 3, 'Jan Vandenbrande', '-21-30-22-', 'Michopoulos, Farhat, Fish, ""Modeling and Simulation of Multiphysics Systems"", Trans. of the ASME, Vol 5.', 'modeling multi physics-multi scale-simulation', 'multi physics systems', 'Survey paper describing challenges in multi-physics systems.', '2019-8-17 19:25:02', 0);
INSERT INTO `micro_notice` VALUES (12, 1, 3, 'Jan Vandenbrande', '-1-12-14-27-', 'Cohen, V., et al. (2015). ""Development of an opioid reduction protocol in an emergency department. Am J Health Syst Pharm 72(23): 2080-2086.','pain-pain management-non opioids-combat-injury-analgesia-multimodal-certa', 'CERTA', 'In a 2015 proof-of-concept study, emergency patients with pain were successfully treated with an opioid-sparing protocol based on principles of channel enzyme receptor–targeted analgesia (CERTA).','2018-10-26 11:32:02',-1 );
-- INSERT INTO `micro_notice` VALUES (1, 1, 1, 'wzs', '-1-2-3-', '1-2-3', 'abc', 'title', 'summary', '2020-04-30 13:35:02', 0);
-- INSERT INTO `micro_notice` VALUES (2, 2, 1, 'wzs','-1-13-4-', '4-9-6', 'keyword', 'test12', 'summary', '2020-04-30 14:08:49', 0);

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
INSERT INTO `topic` VALUES (0, 'LIFE SCIENCES', 1);
INSERT INTO `topic` VALUES (1, 'Biochemistry, Biophysics & Structural Biology', 1);
INSERT INTO `topic` VALUES (2, 'Cell & Developmental Biology', 1);
INSERT INTO `topic` VALUES (3, 'Ecology & Evolutionary Biology', 1);
INSERT INTO `topic` VALUES (4, 'Public Health', 1);
INSERT INTO `topic` VALUES (5, 'Genetics & Genomics', 1);
INSERT INTO `topic` VALUES (6, 'Immunology & Infectious Disease', 1);
INSERT INTO `topic` VALUES (7, 'Biology, Integrated Biology & Integrated Biomedical', 1);
INSERT INTO `topic` VALUES (8, 'Sciences', 1);
INSERT INTO `topic` VALUES (9, 'Kinesiology', 1);
INSERT INTO `topic` VALUES (10, 'Microbiology', 1);
INSERT INTO `topic` VALUES (11, 'Neuroscience & Neurobiology', 1);
INSERT INTO `topic` VALUES (12, 'Nursing', 1);
INSERT INTO `topic` VALUES (13, 'Pharmacology, Toxicology & Environmental Health', 1);
INSERT INTO `topic` VALUES (14, 'Physiology', 1);
INSERT INTO `topic` VALUES (15, 'Animal Sciences', 1);
INSERT INTO `topic` VALUES (16, 'Entomology', 1);
INSERT INTO `topic` VALUES (17, 'Food Science', 1);
INSERT INTO `topic` VALUES (18, 'Forestry & Forest Sciences', 1);
INSERT INTO `topic` VALUES (19, 'Nutrition', 1);
INSERT INTO `topic` VALUES (20, 'Plant Sciences', 1);
INSERT INTO `topic` VALUES (21, 'PHYSICAL SCIENCES & MATHEMATICS', 1);
INSERT INTO `topic` VALUES (22, 'Physics', 1);
INSERT INTO `topic` VALUES (23, 'Statistics & Probability', 1);
INSERT INTO `topic` VALUES (24, 'ENGINEERING', 1);
INSERT INTO `topic` VALUES (25, 'Aerospace Engineering', 1);
INSERT INTO `topic` VALUES (26, 'Biomedical Engineering & Bioengineering', 1);
INSERT INTO `topic` VALUES (27, 'Chemical Engineering', 1);
INSERT INTO `topic` VALUES (28, 'Civil & Environmental Engineering', 1);
INSERT INTO `topic` VALUES (29, 'Computer Engineering', 1);
INSERT INTO `topic` VALUES (30, 'Applied Mathematics', 1);
INSERT INTO `topic` VALUES (31, 'Astrophysics & Astronomy', 1);
INSERT INTO `topic` VALUES (32, 'Chemistry', 1);
INSERT INTO `topic` VALUES (33, 'Computer Sciences', 1);
INSERT INTO `topic` VALUES (34, 'Earth Sciences', 1);
INSERT INTO `topic` VALUES (35, 'Mathematics', 1);
INSERT INTO `topic` VALUES (36, 'Oceanography & Atmospheric Sciences & Meteorology', 1);
INSERT INTO `topic` VALUES (37, 'Electrical & Computer Engineering', 1);
INSERT INTO `topic` VALUES (38, 'Engineering Science & Materials', 1);
INSERT INTO `topic` VALUES (39, 'Mechanical Engineering', 1);
INSERT INTO `topic` VALUES (40, 'Operations Research, Systems Engineering & Industrial Engineering', 1);
INSERT INTO `topic` VALUES (41, 'SOCIAL & BEHAVIORAL SCIENCES', 1);
INSERT INTO `topic` VALUES (42, 'Agricultural & Resource Economics', 1);
INSERT INTO `topic` VALUES (43, 'Anthropology', 1);
INSERT INTO `topic` VALUES (44, 'Communication', 1);
INSERT INTO `topic` VALUES (45, 'Economics', 1);
INSERT INTO `topic` VALUES (46, 'Geography', 1);
INSERT INTO `topic` VALUES (47, 'Linguistics', 1);
INSERT INTO `topic` VALUES (48, 'Political Science', 1);
INSERT INTO `topic` VALUES (49, 'Public Affairs, Public Policy & Public Administration', 1);
INSERT INTO `topic` VALUES (50, 'Psychology', 1);
INSERT INTO `topic` VALUES (51, 'Sociolgy', 1);
INSERT INTO `topic` VALUES (52, 'ARTS & HUMANITIES', 1);
INSERT INTO `topic` VALUES (53, 'American Studies', 1);
INSERT INTO `topic` VALUES (54, 'Classics', 1);
INSERT INTO `topic` VALUES (55, 'Comparative Literature', 1);
INSERT INTO `topic` VALUES (56, 'English Language & Literature', 1);
INSERT INTO `topic` VALUES (57, 'French and Francophone Language and Literature', 1);
INSERT INTO `topic` VALUES (58, 'German Language and Literature', 1);
INSERT INTO `topic` VALUES (59, 'Languages, Societies & Cultures', 1);
INSERT INTO `topic` VALUES (60, 'History', 1);
INSERT INTO `topic` VALUES (61, 'History of Art, Architecture & Archaeology', 1);
INSERT INTO `topic` VALUES (62, 'Music', 1);
INSERT INTO `topic` VALUES (63, 'Philosophy', 1);
INSERT INTO `topic` VALUES (64, 'Religion', 1);
INSERT INTO `topic` VALUES (65, 'Spanish and Portuguese Language and Literature', 1);
INSERT INTO `topic` VALUES (66, 'Theatre & Performance Studies', 1);
INSERT INTO `topic` VALUES (67, 'Other', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite_info
-- ----------------------------
INSERT INTO `favorite_info` VALUES (1, 1, 1,'2020-04-30 13:35:02');
INSERT INTO `favorite_info` VALUES (2, 1, 2,'2020-04-30 13:35:12');
INSERT INTO `favorite_info` VALUES (3, 2, 1,'2020-04-30 13:35:22');

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
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `picture` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `signature` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `education` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `works` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expertise` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `interest` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `contribution` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1,'wzs1@163.com','Changqing Cheng','','male','NY','fighting!','SSIE at BU','SUNY Binghamton','23-24-29','21-30-40','','');
INSERT INTO `user_info` VALUES (2,'wzs2@163.com','Kenneth Frumkin','','male','Philadelphia Pennsylvania Area','work hard!','PhD, MD; Emergency Physician: Military Medicine; Physiological Psychologist','Naval Medical Center, Portsmouth, Virginia','23-24-29','21-30-40','','');
INSERT INTO `user_info` VALUES (3,'wzs3@163.com','Jan Vandenbrande','','male','Arlington, VA','fighting!','SSIE at BU','DARPA/DSO','30-38','21-24','','');
INSERT INTO `user_info` VALUES (4,'wzs4@163.com','Matt Mihelic, MD','','male','Graduate School of Medicine','work hard!','Associate Professor','University of Tennessee','23-24-29','21-30-40','','');
INSERT INTO `user_info` VALUES (5,'wzs5@163.com','Xiao Liu','','female','Fayetteville, Arkansas','fighting!','Assistant Professor at University of Arkansas; Former Research Scientist at IBM T.J. Watson.','Industrial Engineering, University of Arkansas','30-23-40','21-34-36-24','','');
INSERT INTO `user_info` VALUES (6,'wzs6@163.com','Diane Stevens','','male','Northern California','work hard!','Decades of involvement in the humanities, the arts, public service, and science.','UCLA, San Francisco State University','15-52-56-59-61-63','3-15-19-20','','');
-- INSERT INTO `user_info` VALUES (1, 'wzs01@163.com', '张华', '', 'male', '天津市', 'fighting!', '博士', '研究员', '1-2', '1-2-3', '对数据挖掘领域感兴趣', '研究方向为数据挖掘');
-- INSERT INTO `user_info` VALUES (2, 'wzs02@163.com', '王小伟', '', 'male', '北京市', 'work hard', '本科生', '学生', '1-2', '1-2-3', '对数据挖掘领域感兴趣', '研究方向为数据挖掘');
-- INSERT INTO `user_info` VALUES (3, 'wzs03@163.com', '王莞尔', '', 'female', '浙江省杭州市', 'work hard', '本科生', '学生', '1-2', '1-2-3', '对数据挖掘领域感兴趣', '研究方向为数据挖掘');

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
INSERT INTO `review` VALUES (1, 1, -1);
INSERT INTO `review` VALUES (2, 1, 1);
INSERT INTO `review` VALUES (3, 1, 1);

-- ----------------------------
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS `like_num`;
CREATE TABLE `like_num`  (
  `noticeId` int(0) NOT NULL,
  `num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`noticeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `like_num` VALUES (1,0);
INSERT INTO `like_num` VALUES (2,0);
INSERT INTO `like_num` VALUES (3,0);
INSERT INTO `like_num` VALUES (4,0);
INSERT INTO `like_num` VALUES (5,0);
INSERT INTO `like_num` VALUES (6,0);
INSERT INTO `like_num` VALUES (7,0);
INSERT INTO `like_num` VALUES (8,0);
INSERT INTO `like_num` VALUES (9,0);
INSERT INTO `like_num` VALUES (10,0);
INSERT INTO `like_num` VALUES (11,0);
INSERT INTO `like_num` VALUES (12,0);

DROP TABLE IF EXISTS `like`;
CREATE TABLE `like` (
  `noticeId` int(0) NOT NULL,
  `id` int(0) NOT NULL,
  PRIMARY KEY (`noticeId`, `id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 1, 1, 2, 'Kenneth Frumkin', 1, '1', 'title1', '2020-05-01 20:42:45', 0, NULL);
INSERT INTO `message` VALUES (2, 1, 2, 2, 'Kenneth Frumkin', 2, '2', 'title2', '2020-05-08 17:33:46', 0, NULL);
INSERT INTO `message` VALUES (3, 2, 5, 1, 'Changqing Cheng', NULL, NULL, 'title3', '2020-05-08 17:33:45', 0, NULL);
INSERT INTO `message` VALUES (4, 1, 4, 3, 'Jan Vandenbrande', 1, '1', 'title1', '2020-05-08 17:33:44', 0, '我评论了你');
INSERT INTO `message` VALUES (5, 1, 4, 1, 'Changqing Cheng', 1, '1', 'title1', '2020-05-10 21:11:44', 0, '这是一条评论消息!');
INSERT INTO `message` VALUES (6, 1, 5, 1, 'Changqing Cheng', 1, '1', 'title1', '2020-05-10 21:14:27', 0, '这是一条评论回复的消息');
INSERT INTO `message` VALUES (7, 1, 6, 0, NULL, 1, '1', 'title', '2020-05-11 22:57:55', 0, NULL);
INSERT INTO `message` VALUES (8, 1, 7, 0, NULL, 1, '1', 'title', '2020-05-11 23:01:51', 0, NULL);



SET FOREIGN_KEY_CHECKS = 1;



USE wzs;

SET NAMES utf8;
START TRANSACTION;
CREATE TABLE `admin` ( `admin_id` BIGINT NOT NULL PRIMARY KEY, `password` VARCHAR ( 33 ) NOT NULL,
`email` VARCHAR ( 33 ) DEFAULT NULL ) ENGINE = INNODB DEFAULT CHARSET = utf8;
INSERT INTO `admin`
VALUES
	( 1, '123456', 'admin@163.com' );

CREATE TABLE `class_info` ( `class_id` INT NOT NULL PRIMARY KEY, `class_name` VARCHAR ( 15 ) NOT NULL ) ENGINE = INNODB DEFAULT CHARSET = utf8;
INSERT INTO `class_info`
VALUES
	( 1, '马克思主义' ),
	( 2, '哲学' ),
	( 3, '社会科学总论' ),
	( 4, '政治法律' ),
	( 5, '军事' ),
	( 6, '经济' ),
	( 7, '文化' ),
	( 8, '语言' ),
	( 9, '文学' ),
	( 10, '艺术' ),
	( 11, '历史地理' ),
	( 12, '自然科学总论' ),
	( 13, ' 数理科学和化学' ),
	( 14, '天文学、地球科学' ),
	( 15, '生物科学' ),
	( 16, '医药、卫生' ),
	( 17, '农业科学' ),
	( 18, '工业技术' ),
	( 19, '交通运输' ),
	( 20, '航空、航天' ),
	( 21, '环境科学' ),
	( 22, '综合' );

CREATE TABLE `user_info` (
	`id` BIGINT NOT NULL PRIMARY KEY,
	`email` VARCHAR ( 33 ) NOT NULL,
	`name` VARCHAR ( 10 ) NOT NULL,
	`sex` VARCHAR ( 2 ) DEFAULT NULL,
	`address` VARCHAR ( 50 ) DEFAULT NULL,
  `signature` VARCHAR ( 32 ) DEFAULT NULL,
  `education` VARCHAR ( 20 ) DEFAULT NULL,
  `works` VARCHAR ( 50 ) DEFAULT NULL,

  `expertise` VARCHAR ( 32 ) DEFAULT NULL,
	`interest` VARCHAR ( 32 ) DEFAULT NULL,
  `introduction` text,
	`contribution` text
) ENGINE = INNODB DEFAULT CHARSET = utf8;
ALTER TABLE `user_info` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT,
AUTO_INCREMENT = 1000;
INSERT INTO `user_info` ( email, NAME, sex, address,signature, education, works,expertise,interest,introduction,contribution )
VALUES
	( 'wzs01@163.com','张华', '男', '天津市','fighting!','博士', '研究员','','','对数据挖掘领域感兴趣', '研究方向为数据挖掘' ),
	( 'wzs02@163.com','王小伟', '男', '北京市', 'work hard','本科生', '学生','','','对数据挖掘领域感兴趣', '研究方向为数据挖掘' ),
	( 'wzs03@163.com','王莞尔', '女', '浙江省杭州市','work hard','本科生', '学生','','','对数据挖掘领域感兴趣', '研究方向为数据挖掘' );

CREATE TABLE `account` ( `id` BIGINT NOT NULL PRIMARY KEY, `email` VARCHAR ( 33 ) NOT NULL,
`password` VARCHAR ( 33 ) NOT NULL ) ENGINE = INNODB DEFAULT CHARSET = utf8;
ALTER TABLE `account` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT,
AUTO_INCREMENT = 1000;
INSERT INTO `account`
VALUES
	( 1000, 'wzs01@163.com', '0a7cd2f29c2c46302b0002ac6ecf6d03' ),
	( 1001, 'wzs02@163.com', 'c9a6d3a645392a8f0d6a7514aa59d712' ),
	( 1002, 'wzs03@163.com', '74b5d158e3a6263c26cddc5946d04e1e' );

COMMIT;
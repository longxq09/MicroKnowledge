DROP DATABASE IF EXISTS wzs;
CREATE DATABASE wzs;
USE wzs;

SET NAMES utf8;
START TRANSACTION;
CREATE TABLE `admin` ( `admin_id` BIGINT NOT NULL PRIMARY KEY, `password` VARCHAR ( 33 ) NOT NULL,
`email` VARCHAR ( 33 ) DEFAULT NULL ) ENGINE = INNODB DEFAULT CHARSET = utf8;
INSERT INTO `admin`
VALUES
	( 1, '123456', 'admin@163.com' );

CREATE TABLE `topic` ( `id` INT NOT NULL PRIMARY KEY, `topicName` VARCHAR ( 20 ) NOT NULL,
											 `flag` INT DEFAULT NULL )
ENGINE = INNODB DEFAULT CHARSET = utf8;
INSERT INTO `topic`
VALUES
	( 1, '马克思主义',1),
	( 2, '哲学',1 ),
	( 3, '社会科学总论' ,1),
	( 4, '政治法律' ,1),
	( 5, '军事',1 ),
	( 6, '经济',1 ),
	( 7, '文化' ,1),
	( 8, '语言' ,1),
	( 9, '文学' ,1),
	( 10, '艺术' ,1),
	( 11, '历史地理' ,1),
	( 12, '自然科学总论',1 ),
	( 13, ' 数理科学和化学',1 ),
	( 14, '天文学、地球科学' ,1),
	( 15, '生物科学' ,1),
	( 16, '医药、卫生' ,1),
	( 17, '农业科学' ,1),
	( 18, '工业技术' ,1),
	( 19, '交通运输',1 ),
	( 20, '航空、航天' ,1),
	( 21, '环境科学' ,1),
	( 22, '综合' ,1);

CREATE TABLE `user_info` (
	`id` BIGINT NOT NULL PRIMARY KEY,
	`email` VARCHAR ( 33 ) NOT NULL,
	`name` VARCHAR ( 10 ) NOT NULL,
	`sex` VARCHAR ( 20 ) DEFAULT NULL,
	`address` VARCHAR ( 50 ) DEFAULT NULL,
  `signature` VARCHAR ( 32 ) DEFAULT NULL,
  `education` VARCHAR ( 20 ) DEFAULT NULL,
  `works` VARCHAR ( 50 ) DEFAULT NULL,

  `expertise` VARCHAR ( 256 ) DEFAULT NULL,
	`interest` VARCHAR ( 256 ) DEFAULT NULL,
  `introduction` text,
	`contribution` text
) ENGINE = INNODB DEFAULT CHARSET = utf8;
ALTER TABLE `user_info` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT,
AUTO_INCREMENT = 1000;
INSERT INTO `user_info` ( email, NAME, sex, address,signature, education, works,expertise,interest,introduction,contribution )
VALUES
	( 'wzs01@163.com','张华', '男', '天津市','fighting!','博士', '研究员','1:Computer Sciences-2:Data Mining','1:Statistics & Probability-2:High-performance computing-3:Music','对数据挖掘领域感兴趣', '研究方向为数据挖掘' ),
	( 'wzs02@163.com','王小伟', '男', '北京市', 'work hard','本科生', '学生','1:Computer Sciences-2:Data Mining','1:Statistics & Probability-2:High-performance computing-3:Music','对数据挖掘领域感兴趣', '研究方向为数据挖掘' ),
	( 'wzs03@163.com','王莞尔', '女', '浙江省杭州市','work hard','本科生', '学生','1:Computer Sciences-2:Data Mining','1:Statistics & Probability-2:High-performance computing-3:Music','对数据挖掘领域感兴趣', '研究方向为数据挖掘' );

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
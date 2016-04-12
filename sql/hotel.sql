-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2016 年 04 月 12 日 17:42
-- 服务器版本: 5.5.47
-- PHP 版本: 5.3.10-1ubuntu3.21

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `hotel`
--

-- --------------------------------------------------------

--
-- 表的结构 `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `user_id` int(4) NOT NULL,
  `comment_time` datetime NOT NULL,
  `comment_text` text CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci NOT NULL,
  `comment_id` int(5) NOT NULL AUTO_INCREMENT,
  `room_num` int(6) NOT NULL,
  `comment_star` int(1) NOT NULL,
  `comment_reply` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `user_name` text NOT NULL,
  UNIQUE KEY `comment_id` (`comment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- 转存表中的数据 `comments`
--

INSERT INTO `comments` (`user_id`, `comment_time`, `comment_text`, `comment_id`, `room_num`, `comment_star`, `comment_reply`, `user_name`) VALUES
(1, '2016-02-25 10:38:07', 'good,hahahaNoNONO', 1, 100001, 5, 'NishiShui', 'happy'),
(1, '2016-02-25 10:38:08', '', 2, 100001, 5, NULL, 'happy'),
(1, '2016-02-25 10:39:23', 'good,hahaha', 3, 100001, 5, NULL, 'happy'),
(1, '2016-04-09 11:01:17', '跟谁说了吗你说话费啊我是的人啊我是什么啊。', 4, 100001, 3, NULL, 'happy'),
(1, '2016-04-10 12:32:23', '不好好', 5, 100001, 2, NULL, '??WWW'),
(1, '2016-04-10 08:39:00', '哈哈哈', 6, 100001, 2, NULL, '??WWW'),
(1, '2016-04-10 09:47:02', '哈哈哈哈', 7, 100001, 2, NULL, '??WWW'),
(1, '2016-04-10 10:17:33', '很好很好很好', 8, 100001, 2, NULL, '??WWW'),
(13, '2016-04-10 10:26:42', '还合计', 9, 1020, 2, NULL, '????');

-- --------------------------------------------------------

--
-- 表的结构 `indents`
--

CREATE TABLE IF NOT EXISTS `indents` (
  `time_begin` date NOT NULL,
  `time_end` date NOT NULL,
  `room_num` int(6) NOT NULL,
  `indent_id` int(6) NOT NULL AUTO_INCREMENT,
  `indent_time` datetime NOT NULL,
  `indent_status` int(1) NOT NULL DEFAULT '1',
  `user_id` int(4) NOT NULL,
  `cost` float NOT NULL,
  `indent_type` int(1) NOT NULL,
  PRIMARY KEY (`indent_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2601576 ;

--
-- 转存表中的数据 `indents`
--

INSERT INTO `indents` (`time_begin`, `time_end`, `room_num`, `indent_id`, `indent_time`, `indent_status`, `user_id`, `cost`, `indent_type`) VALUES
('2016-02-25', '2016-02-26', 10001, 1, '2016-02-24 10:33:17', 1, 1, 1000, 1),
('2016-02-25', '2016-02-26', 10001, 2, '2016-02-24 10:33:51', 3, 1, 1000, 2),
('2016-04-01', '2016-08-15', 100001, 26, '2016-04-02 00:00:00', 3, 1, 31069, 1),
('2016-04-01', '2016-07-15', 100001, 2601546, '0000-00-00 00:00:00', 1, 0, 0, 1),
('2015-10-01', '2016-02-14', 10, 2601547, '2016-04-09 08:57:18', 1, 0, 123, 1),
('2015-10-01', '2016-02-14', 10, 2601548, '2016-04-09 08:57:29', 1, 0, 123, 2),
('2016-08-09', '2017-09-11', 1020, 2601549, '2016-04-09 08:59:34', 4, 1, 397996, 1),
('2016-04-10', '2016-04-10', 100001, 2601550, '2016-04-10 09:01:14', 2, 1, 10, 2),
('2016-04-10', '2016-04-10', 100001, 2601551, '2016-04-10 09:01:17', 2, 1, 100, 3),
('2016-04-10', '2016-04-10', 100001, 2601552, '2016-04-10 09:08:52', 3, 1, 10, 2),
('2016-04-10', '2016-04-10', 100001, 2601553, '2016-04-10 09:45:50', 4, 1, 10, 2),
('2016-04-10', '2016-04-10', 100001, 2601560, '2016-04-10 10:05:39', 4, 1, 10, 2),
('2016-04-10', '2016-04-10', 100001, 2601561, '2016-04-10 10:05:55', 3, 1, 10, 2),
('2016-04-10', '2016-04-18', 1020, 2601562, '2016-04-10 10:22:25', 1, 13, 7999.92, 1),
('2016-09-18', '2016-10-18', 1, 2601563, '2016-04-10 10:24:16', 1, 13, 29970, 1),
('2016-04-10', '2016-04-10', 1020, 2601564, '2016-04-10 10:26:11', 1, 13, 10, 2),
('2016-04-10', '2016-04-10', 1020, 2601565, '2016-04-10 10:26:13', 1, 13, 100, 3),
('2016-04-10', '2016-04-10', 100001, 2601566, '2016-04-10 10:49:40', 3, 1, 10, 2),
('2015-05-14', '2016-07-08', 1, 2601567, '2016-04-10 10:50:30', 1, 1, 420579, 1),
('2016-04-11', '2016-08-17', 1, 2601568, '2016-04-10 10:52:54', 1, 1, 127872, 1),
('2016-04-11', '2016-04-11', 100001, 2601569, '2016-04-11 09:29:54', 3, 1, 10, 2),
('2016-04-11', '2016-04-11', 100001, 2601570, '2016-04-11 09:40:02', 3, 1, 10, 2),
('2016-04-11', '2016-04-11', 100001, 2601571, '2016-04-11 09:48:02', 3, 1, 100, 3),
('2016-04-11', '2016-04-11', 100001, 2601572, '2016-04-11 09:53:00', 3, 1, 10, 2),
('2016-04-11', '2016-04-11', 100001, 2601573, '2016-04-11 14:25:10', 3, 1, 10, 2),
('2016-04-11', '2016-04-11', 100001, 2601574, '2016-04-11 14:25:26', 3, 1, 100, 3),
('2016-07-08', '2016-08-09', 1020, 2601575, '2016-04-11 14:26:56', 1, 1, 31999.7, 1);

-- --------------------------------------------------------

--
-- 表的结构 `messages`
--

CREATE TABLE IF NOT EXISTS `messages` (
  `user_id` int(4) NOT NULL,
  `msg_time` datetime NOT NULL,
  `msg_text` text NOT NULL,
  `msg_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `residents`
--

CREATE TABLE IF NOT EXISTS `residents` (
  `room_num` int(6) NOT NULL,
  `user_id` int(4) NOT NULL,
  `indent_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `rooms`
--

CREATE TABLE IF NOT EXISTS `rooms` (
  `room_num` int(6) NOT NULL,
  `room_type` int(1) NOT NULL,
  `room_area` float NOT NULL,
  `room_cost` float NOT NULL,
  `room_img` text NOT NULL,
  `room_facility` text NOT NULL,
  KEY `room_num` (`room_num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `rooms`
--

INSERT INTO `rooms` (`room_num`, `room_type`, `room_area`, `room_cost`, `room_img`, `room_facility`) VALUES
(1020, 1, 120.22, 999.99, 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.png', 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.pnghttp://42.96.148.66/hotel/img/CR-6zVHxqT58B.png'),
(1020, 1, 120.22, 999.99, 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.png', 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.pnghttp://42.96.148.66/hotel/img/CR-6zVHxqT58B.png'),
(1, 1, 120, 999, 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.png', 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.png'),
(100001, 1, 120, 999, 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.png', 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.pnghttp://42.96.148.66/hotel/img/CR-6zVHxqT58B.png'),
(1, 1, 120, 999, 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.png', 'http://42.96.148.66/hotel/img/CR-6zVHxqT58B.pnghttp://42.96.148.66/hotel/img/CR-6zVHxqT58B.png');

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(4) NOT NULL AUTO_INCREMENT,
  `user_nick` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_type` int(1) NOT NULL,
  `user_gender` int(1) NOT NULL,
  `user_years` int(3) NOT NULL,
  `user_email` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_time` datetime NOT NULL,
  `user_password` char(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_point` int(4) NOT NULL,
  `user_phone` text NOT NULL,
  `user_id_num` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `user_que` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_ans` text NOT NULL,
  `user_img` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`user_id`, `user_nick`, `user_type`, `user_gender`, `user_years`, `user_email`, `user_time`, `user_password`, `user_point`, `user_phone`, `user_id_num`, `user_name`, `user_que`, `user_ans`, `user_img`) VALUES
(1, '你好WWW', 1, 2, 13, 'vitress@qq.com', '2016-02-16 00:00:00', '34323432344refee3feeeeeeeeeeefde', 253, '1332232223', '142603199302032034', '232322', 'Who are you', 'Guess', 6),
(6, '测试', 1, 1, 15, 'test@example.com', '2016-02-21 04:18:27', 'fdfddfeefddfe', 0, '123456', '323234323', '47yhu', '你是猪么？', '???', 1),
(7, '测试', 1, 1, 15, 'test@example.com', '2016-02-21 04:20:15', 'fdfddfeefddfe', 0, '123456', '323234323', '王尼玛', '你是猪么？', '???', 1),
(8, '测试21', 1, 1, 11, 'test@example.com', '2016-02-21 04:21:43', 'fdfddfeefddfe', 142, '123456', '13121415', '王尼玛', '你是猪tou么？', '???', 1),
(10, 'djwe', 1, 1, 54, 'ak@qq.com', '2016-04-10 10:17:00', 'akakak', 1, '123456', '123456', 'appkppk', 'sdf', 'dfeef', 1),
(11, 'djwe', 1, 1, 54, 'ak@qq.com', '2016-04-10 10:17:09', 'akakak', 1, '123456', '123456', 'appkppk', 'sdf', 'dfeef', 1),
(12, 'djwe', 1, 1, 54, 'ak@qq.com', '2016-04-10 10:18:34', 'akakak', 0, '123456', '123456', 'appkppk', 'sdf', 'dfeef', 1),
(13, '好刚刚好', 1, 2, 56, 'hhjjkk', '2016-04-10 10:20:21', 'hhjjkk', 0, '123321', '56665566', '于浩', '洪湖公园', 'hjjggyu', 1),
(14, '庾花花', 1, 2, 26, 'hu@qq.com', '2016-04-10 10:12:17', '123', 0, '123456', '123456', '就不给', '一会会', 'hjj', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

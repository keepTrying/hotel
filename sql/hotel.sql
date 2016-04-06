-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2016 年 04 月 06 日 20:32
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
  UNIQUE KEY `comment_id` (`comment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `comments`
--

INSERT INTO `comments` (`user_id`, `comment_time`, `comment_text`, `comment_id`, `room_num`, `comment_star`, `comment_reply`) VALUES
(1, '2016-02-25 10:38:07', 'good,hahahaNoNONO', 1, 100001, 5, 'NishiShui'),
(1, '2016-02-25 10:38:08', '', 2, 100001, 5, NULL),
(1, '2016-02-25 10:39:23', 'good,hahaha', 3, 100001, 5, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `indents`
--

CREATE TABLE IF NOT EXISTS `indents` (
  `time_begin` date NOT NULL,
  `time_end` date NOT NULL,
  `pay` int(1) NOT NULL,
  `room_num` int(6) NOT NULL,
  `indent_id` int(6) NOT NULL AUTO_INCREMENT,
  `indent_time` datetime NOT NULL,
  `indent_status` int(1) NOT NULL,
  `user_id` int(4) NOT NULL,
  `cost` float NOT NULL,
  `indent_type` int(1) NOT NULL,
  PRIMARY KEY (`indent_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `indents`
--

INSERT INTO `indents` (`time_begin`, `time_end`, `pay`, `room_num`, `indent_id`, `indent_time`, `indent_status`, `user_id`, `cost`, `indent_type`) VALUES
('2016-02-25', '2016-02-26', 0, 10001, 1, '2016-02-24 10:33:17', 0, 1, 1000, 1),
('2016-02-25', '2016-02-26', 1, 10001, 2, '2016-02-24 10:33:51', 2, 1, 1000, 2);

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
  KEY `room_num` (`room_num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `rooms`
--

INSERT INTO `rooms` (`room_num`, `room_type`, `room_area`, `room_cost`, `room_img`) VALUES
(1020, 1, 120.22, 999.99, 'http://www.image.com'),
(1020, 1, 120.22, 999.99, 'http://www.image.com'),
(1, 1, 120, 999, 'alter'),
(1, 1, 120, 999, 'alter'),
(1, 1, 120, 999, 'alter');

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
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`user_id`, `user_nick`, `user_type`, `user_gender`, `user_years`, `user_email`, `user_time`, `user_password`, `user_point`, `user_phone`, `user_id_num`, `user_name`, `user_que`, `user_ans`) VALUES
(1, '你好WWW', 1, 0, 13, 'vitress@qq.com', '2016-02-16 00:00:00', '34323432344refee3feeeeeeeeeeefde', 23, '1332232223', '142603199302032034', '232322', 'Who are you', 'Guess'),
(2, '呵呵', 1, 1, 21, 'exameple@company.com', '2016-02-25 07:16:14', '1343434jj43rf', 0, '0', '', '0', '你是猪么？', '?????'),
(3, '呵呵', 1, 1, 21, 'exameple@company.com', '2016-02-25 07:16:14', '323234', 0, '0', '', '0', '你是猪么？', '?????'),
(4, '呵呵', 1, 1, 21, 'exameple@company.com', '2016-02-25 07:16:14', '1343434jj43rf', 0, '0', '', '0', '你是猪么？', '?????'),
(5, '测试', 1, 1, 15, 'test@example.com', '2016-02-21 02:46:58', 'fdfddfeefddfe', 0, '123456', '323234323', '0', '你是猪么', '??'),
(6, '测试', 1, 1, 15, 'test@example.com', '2016-02-21 04:18:27', 'fdfddfeefddfe', 0, '123456', '323234323', '0', '你是猪么？', '???'),
(7, '测试', 1, 1, 15, 'test@example.com', '2016-02-21 04:20:15', 'fdfddfeefddfe', 0, '123456', '323234323', '王尼玛', '你是猪么？', '???'),
(8, '测试21', 1, 1, 11, 'test@example.com', '2016-02-21 04:21:43', 'fdfddfeefddfe', 0, '123456', '13121415', '王尼玛', '你是猪tou么？', '???');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

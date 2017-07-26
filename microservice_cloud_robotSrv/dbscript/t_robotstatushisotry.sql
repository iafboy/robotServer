-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-07-25 07:15:52
-- 服务器版本： 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `robot`
--

-- --------------------------------------------------------

--
-- 表的结构 `t_robotstatushisotry`
--

CREATE TABLE `t_robotstatushisotry` (
  `taskid` varchar(9) NOT NULL,
  `distance` decimal(8,0) DEFAULT '0',
  `angle` decimal(4,1) DEFAULT '0.0',
  `lng` decimal(8,2) DEFAULT '0.00' COMMENT '经度',
  `lat` decimal(8,2) DEFAULT '0.00' COMMENT '纬度',
  `lpwm` int(11) NOT NULL DEFAULT '200' COMMENT '左轮PWM',
  `rpwm` int(11) NOT NULL DEFAULT '200' COMMENT '右轮PWM',
  `stopped` int(1) DEFAULT '0',
  `detail` varchar(200) DEFAULT NULL,
  `recordtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_robotstatushisotry`
--
ALTER TABLE `t_robotstatushisotry`
  ADD PRIMARY KEY (`taskid`),
  ADD UNIQUE KEY `taskid` (`taskid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

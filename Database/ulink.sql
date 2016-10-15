-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2016 at 07:05 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ulink`
--
CREATE DATABASE IF NOT EXISTS `ulink` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ulink`;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `passportNumber` varchar(45) NOT NULL,
  `clientName` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `dateOfBirth` varchar(45) DEFAULT NULL,
  `mainDiagnosis` varchar(45) DEFAULT NULL,
  `clientType` varchar(45) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `countryOfResidence` varchar(45) DEFAULT NULL,
  `billingStreet` varchar(45) DEFAULT NULL,
  `billingCity` varchar(45) DEFAULT NULL,
  `billingState` varchar(45) DEFAULT NULL,
  `billingCountry` varchar(45) DEFAULT NULL,
  `billingCode` varchar(45) DEFAULT NULL,
  `isMedicial` varchar(45) DEFAULT NULL,
  `isClaim` varchar(45) DEFAULT NULL,
  `claimInformation` varchar(45) DEFAULT NULL,
  `referralName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`passportNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`passportNumber`, `clientName`, `gender`, `dateOfBirth`, `mainDiagnosis`, `clientType`, `nationality`, `countryOfResidence`, `billingStreet`, `billingCity`, `billingState`, `billingCountry`, `billingCode`, `isMedicial`, `isClaim`, `claimInformation`, `referralName`) VALUES
('G1234456g', 'Sean', 'Male', '19091991', 'none', 'none', 'none', 'none', 'none', 'none', NULL, NULL, NULL, NULL, NULL, NULL, 'abcdefg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

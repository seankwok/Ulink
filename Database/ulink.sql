-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2017 at 02:31 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ulink`
--
CREATE DATABASE IF NOT EXISTS `ulink` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ulink`;

-- --------------------------------------------------------

--
-- Table structure for table `allcondition`
--

CREATE TABLE `allcondition` (
  `ID` int(11) NOT NULL,
  `conditionName` varchar(255) NOT NULL,
  `numOfYears` varchar(255) DEFAULT NULL,
  `ageRequired` int(90) DEFAULT NULL,
  `screening` varchar(255) DEFAULT NULL,
  `type` varchar(90) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `allcondition`
--

INSERT INTO `allcondition` (`ID`, `conditionName`, `numOfYears`, `ageRequired`, `screening`, `type`) VALUES
(48, 'cold1', '3', 12, 'cold', 'male'),
(45, 'pwqpqpw', '3432v', 3, 'pefkwekfq', 'infant');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `ID` int(255) NOT NULL,
  `AccountID` varchar(1000) NOT NULL,
  `clientOwner` varchar(50) DEFAULT NULL,
  `clientName` varchar(255) DEFAULT NULL,
  `clientType` varchar(50) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `dateOfBirth` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `medical` varchar(50) DEFAULT NULL,
  `mainDiagnosis` varchar(50) DEFAULT NULL,
  `referredBy` varchar(50) DEFAULT NULL,
  `PIC` varchar(255) NOT NULL,
  `appointment` varchar(50) DEFAULT NULL,
  `doctor` varchar(50) DEFAULT NULL,
  `specialty` varchar(50) DEFAULT NULL,
  `clinic` varchar(50) DEFAULT NULL,
  `otherDoctor` varchar(255) DEFAULT NULL,
  `followUpPerson` varchar(50) DEFAULT NULL,
  `followUpPIC` varchar(50) DEFAULT NULL,
  `hospitalAdmitted` varchar(50) DEFAULT NULL,
  `log` varchar(50) DEFAULT NULL,
  `claim` varchar(50) DEFAULT NULL,
  `visaRequestBy` varchar(255) NOT NULL,
  `visa` varchar(50) DEFAULT NULL,
  `visaType` varchar(50) DEFAULT NULL,
  `visaType2` varchar(50) DEFAULT NULL,
  `age` int(5) NOT NULL,
  `billingCity` varchar(1000) NOT NULL,
  `BillingCode` varchar(1000) NOT NULL,
  `BillingCountry` varchar(1000) NOT NULL,
  `BillingState` varchar(1000) NOT NULL,
  `BillingStreet` varchar(1000) NOT NULL,
  `CreatedTime` date NOT NULL,
  `phone` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `uploadtime`
--

CREATE TABLE `uploadtime` (
  `ID` int(5) NOT NULL,
  `dateTime` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `email` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  `roles` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`email`, `password`, `roles`) VALUES
('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin'),
('sean', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', 'admin'),
('abc', 'ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad', 'non-admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `allcondition`
--
ALTER TABLE `allcondition`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `uploadtime`
--
ALTER TABLE `uploadtime`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `allcondition`
--
ALTER TABLE `allcondition`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `uploadtime`
--
ALTER TABLE `uploadtime`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 23, 2016 at 01:39 AM
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
-- Table structure for table `client`
--

CREATE TABLE `client` (
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
  `referral_ID` int(5) DEFAULT NULL,
  `PIC_ID` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `clinic`
--

CREATE TABLE `clinic` (
  `clinicName` varchar(45) NOT NULL,
  `Address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clinic`
--

INSERT INTO `clinic` (`clinicName`, `Address`) VALUES
('ntu', 'ntu'),
('smu', 'smu');

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

CREATE TABLE `consultation` (
  `C_ID` int(11) NOT NULL,
  `passportNumber` varchar(45) NOT NULL,
  `doctorName` varchar(45) DEFAULT NULL,
  `dateTime` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consultation`
--

INSERT INTO `consultation` (`C_ID`, `passportNumber`, `doctorName`, `dateTime`) VALUES
(3, '123', 'kaixin', '2016-10-05 00:00:00.000000'),
(4, 'G1234456g', 'nab', '2016-10-19 00:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doctorName` varchar(45) NOT NULL,
  `speciality` varchar(45) DEFAULT NULL,
  `clinicName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doctorName`, `speciality`, `clinicName`) VALUES
('kaixin', 'abc', 'ntu'),
('nab', 'nab', 'smu'),
('nicole', 'nicole', 'ntu'),
('sean', 'sean', 'ntu'),
('sy', 'sy', 'smu');

-- --------------------------------------------------------

--
-- Table structure for table `picdetails`
--

CREATE TABLE `picdetails` (
  `PIC_ID` int(6) NOT NULL,
  `PICName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `referral`
--

CREATE TABLE `referral` (
  `referralName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `referral`
--

INSERT INTO `referral` (`referralName`) VALUES
('asd'),
('qwe');

-- --------------------------------------------------------

--
-- Table structure for table `referraldetails`
--

CREATE TABLE `referraldetails` (
  `referral_ID` int(5) NOT NULL,
  `referralName` varchar(45) NOT NULL,
  `r_dateTime` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `referraldetails`
--

INSERT INTO `referraldetails` (`referral_ID`, `referralName`, `r_dateTime`) VALUES
(1, 'asd', '2016-10-01 00:00:00.000000'),
(2, 'asd', '2016-10-20 00:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `roleName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  `email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`passportNumber`),
  ADD KEY `referral_ID` (`referral_ID`),
  ADD KEY `PIC_ID` (`PIC_ID`);

--
-- Indexes for table `clinic`
--
ALTER TABLE `clinic`
  ADD PRIMARY KEY (`clinicName`);

--
-- Indexes for table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`C_ID`),
  ADD KEY `passportNumber` (`passportNumber`),
  ADD KEY `doctorName` (`doctorName`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctorName`),
  ADD KEY `clinicName` (`clinicName`);

--
-- Indexes for table `picdetails`
--
ALTER TABLE `picdetails`
  ADD PRIMARY KEY (`PIC_ID`),
  ADD KEY `email` (`email`);

--
-- Indexes for table `referral`
--
ALTER TABLE `referral`
  ADD PRIMARY KEY (`referralName`);

--
-- Indexes for table `referraldetails`
--
ALTER TABLE `referraldetails`
  ADD PRIMARY KEY (`referral_ID`),
  ADD KEY `referralName` (`referralName`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleName`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`),
  ADD KEY `role` (`role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `C_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `referraldetails`
--
ALTER TABLE `referraldetails`
  MODIFY `referral_ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`referral_ID`) REFERENCES `referraldetails` (`referral_ID`),
  ADD CONSTRAINT `client_ibfk_2` FOREIGN KEY (`PIC_ID`) REFERENCES `picdetails` (`PIC_ID`);

--
-- Constraints for table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `consultation_ibfk_1` FOREIGN KEY (`passportNumber`) REFERENCES `client` (`passportNumber`),
  ADD CONSTRAINT `consultation_ibfk_2` FOREIGN KEY (`doctorName`) REFERENCES `doctor` (`doctorName`);

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`clinicName`) REFERENCES `clinic` (`clinicName`);

--
-- Constraints for table `picdetails`
--
ALTER TABLE `picdetails`
  ADD CONSTRAINT `picdetails_ibfk_1` FOREIGN KEY (`email`) REFERENCES `user` (`email`);

--
-- Constraints for table `referraldetails`
--
ALTER TABLE `referraldetails`
  ADD CONSTRAINT `referraldetails_ibfk_1` FOREIGN KEY (`referralName`) REFERENCES `referral` (`referralName`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleName`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

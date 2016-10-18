-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 16, 2016 at 04:07 PM
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
  `referralName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`passportNumber`, `clientName`, `gender`, `dateOfBirth`, `mainDiagnosis`, `clientType`, `nationality`, `countryOfResidence`, `billingStreet`, `billingCity`, `billingState`, `billingCountry`, `billingCode`, `isMedicial`, `isClaim`, `claimInformation`, `referralName`) VALUES
('1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'von'),
('123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'sean'),
('G1234456g', 'Sean', 'Male', '19091991', 'none', 'none', 'none', 'none', 'none', 'none', NULL, NULL, NULL, NULL, NULL, NULL, 'abcdefg'),
('test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'sean');

-- --------------------------------------------------------

--
-- Table structure for table `clinic`
--

CREATE TABLE `clinic` (
  `clinicName` varchar(45) NOT NULL,
  `clinicAddress` varchar(80) NOT NULL,
  `doctorName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clinic`
--

INSERT INTO `clinic` (`clinicName`, `clinicAddress`, `doctorName`) VALUES
('ntu', 'ntu', 'sean'),
('smu', 'smu', 'kaixin');

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

CREATE TABLE `consultation` (
  `consultation_ID` int(11) NOT NULL,
  `appointment` datetime NOT NULL,
  `doctorName` varchar(45) DEFAULT NULL,
  `clinicName` varchar(45) DEFAULT NULL,
  `passportNumber` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consultation`
--

INSERT INTO `consultation` (`consultation_ID`, `appointment`, `doctorName`, `clinicName`, `passportNumber`) VALUES
(5, '2016-10-04 00:00:00', 'sean', 'ntu', '1'),
(6, '2016-10-12 00:00:00', 'kaixin', 'ntu', '123'),
(7, '2016-10-01 00:00:00', 'kaixin', 'ntu', '123'),
(8, '2016-10-06 00:00:00', 'sean', 'ntu', '123'),
(9, '2016-10-02 00:00:00', 'kaixin', 'ntu', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doctorName` varchar(45) NOT NULL,
  `speciality` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doctorName`, `speciality`) VALUES
('kaixin', 'abcc'),
('sean', 'abc');

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
  ADD PRIMARY KEY (`passportNumber`);

--
-- Indexes for table `clinic`
--
ALTER TABLE `clinic`
  ADD PRIMARY KEY (`clinicName`),
  ADD KEY `doctorName` (`doctorName`);

--
-- Indexes for table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`consultation_ID`),
  ADD KEY `fk_doctorName` (`doctorName`),
  ADD KEY `fk_clinicName` (`clinicName`),
  ADD KEY `passportNumber` (`passportNumber`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctorName`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `consultation_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `clinic`
--
ALTER TABLE `clinic`
  ADD CONSTRAINT `clinic_ibfk_1` FOREIGN KEY (`doctorName`) REFERENCES `doctor` (`doctorName`);

--
-- Constraints for table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `consultation_ibfk_1` FOREIGN KEY (`passportNumber`) REFERENCES `client` (`passportNumber`),
  ADD CONSTRAINT `fk_clinicName` FOREIGN KEY (`clinicName`) REFERENCES `clinic` (`clinicName`),
  ADD CONSTRAINT `fk_doctorName` FOREIGN KEY (`doctorName`) REFERENCES `doctor` (`doctorName`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 24, 2016 at 02:56 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET FOREIGN_KEY_CHECKS=0;
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
-- Table structure for table `admission`
--

CREATE TABLE `admission` (
  `caseNumber` int(6) NOT NULL,
  `followUpID` varchar(45) DEFAULT NULL,
  `passportNumber` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admission`
--

INSERT INTO `admission` (`caseNumber`, `followUpID`, `passportNumber`) VALUES
(1, '123', 'S123qwe'),
(2, '123', 'G1234456g'),
(3, 'abc', 'qwewqewqeqw');

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `appointmentID` int(6) NOT NULL,
  `passportNumber` varchar(45) DEFAULT NULL,
  `appointmentDate` datetime(6) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appointmentID`, `passportNumber`, `appointmentDate`, `email`) VALUES
(1, 'G1234456g', '2016-10-19 00:00:00.000000', 'sean@gmail.com'),
(2, 'qwewqewqeqw', '2016-10-24 00:00:00.000000', 'seankwok794@gmail.com');

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
  `referral_ID` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`passportNumber`, `clientName`, `gender`, `dateOfBirth`, `mainDiagnosis`, `clientType`, `nationality`, `countryOfResidence`, `billingStreet`, `billingCity`, `billingState`, `billingCountry`, `billingCode`, `isMedicial`, `isClaim`, `claimInformation`, `referral_ID`) VALUES
('', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4),
('G1234456g', 'SeanClient', NULL, NULL, NULL, NULL, 'Indo', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
('qwewqewqeqw', 'SeanClient1', NULL, NULL, NULL, NULL, 'none', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
(1, 'S123qwe', 'doctorSean', '2016-10-05 00:00:00.000000');

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
('doctorSean', 'doctorSean', 'ntu'),
('doctorTest', 'doctorTest', 'smu');

-- --------------------------------------------------------

--
-- Table structure for table `followup`
--

CREATE TABLE `followup` (
  `followUpID` varchar(45) NOT NULL,
  `hospitalAdmitted` varchar(45) DEFAULT NULL,
  `hospitalAddress` varchar(45) DEFAULT NULL,
  `dateOfAdmission` datetime(6) DEFAULT NULL,
  `dischargeDate` datetime(6) DEFAULT NULL,
  `additionalSpecialist` varchar(45) DEFAULT NULL,
  `followUpRemarks` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `followup`
--

INSERT INTO `followup` (`followUpID`, `hospitalAdmitted`, `hospitalAddress`, `dateOfAdmission`, `dischargeDate`, `additionalSpecialist`, `followUpRemarks`) VALUES
('123', NULL, NULL, '2016-10-07 00:00:00.000000', NULL, NULL, NULL),
('abc', 'smuHospital', 'smuAddress', '2016-10-05 00:00:00.000000', '2016-10-21 00:00:00.000000', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mancondition`
--

CREATE TABLE `mancondition` (
  `conditionName` varchar(45) NOT NULL,
  `numOfYears` int(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
('referral Sean');

-- --------------------------------------------------------

--
-- Table structure for table `referraldetails`
--

CREATE TABLE `referraldetails` (
  `referral_ID` int(5) NOT NULL,
  `referralName` varchar(45) DEFAULT NULL,
  `r_dateTime` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `referraldetails`
--

INSERT INTO `referraldetails` (`referral_ID`, `referralName`, `r_dateTime`) VALUES
(3, 'referral Sean', '2016-10-07 00:00:00.000000'),
(4, 'referral Sean', '2016-10-14 00:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `roleName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`roleName`) VALUES
('Medicial'),
('Ops'),
('Visa');

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
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `role`, `email`) VALUES
('kaixin', 'kaixin', 'Ops', 'sean@gmail.com'),
('sean', 'sean', 'Medicial', 'seankwok794@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admission`
--
ALTER TABLE `admission`
  ADD PRIMARY KEY (`caseNumber`),
  ADD KEY `followUpID` (`followUpID`),
  ADD KEY `passportNumber` (`passportNumber`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointmentID`),
  ADD KEY `passportNumber` (`passportNumber`),
  ADD KEY `email` (`email`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`passportNumber`),
  ADD KEY `referral_ID` (`referral_ID`);

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
-- Indexes for table `followup`
--
ALTER TABLE `followup`
  ADD PRIMARY KEY (`followUpID`);

--
-- Indexes for table `mancondition`
--
ALTER TABLE `mancondition`
  ADD PRIMARY KEY (`conditionName`);

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
-- AUTO_INCREMENT for table `admission`
--
ALTER TABLE `admission`
  MODIFY `caseNumber` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `appointmentID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `consultation`
--
ALTER TABLE `consultation`
  MODIFY `C_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `referraldetails`
--
ALTER TABLE `referraldetails`
  MODIFY `referral_ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `admission`
--
ALTER TABLE `admission`
  ADD CONSTRAINT `admission_ibfk_1` FOREIGN KEY (`followUpID`) REFERENCES `followup` (`followUpID`),
  ADD CONSTRAINT `admission_ibfk_2` FOREIGN KEY (`passportNumber`) REFERENCES `client` (`passportNumber`);

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`passportNumber`) REFERENCES `client` (`passportNumber`),
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`email`) REFERENCES `user` (`email`);

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`referral_ID`) REFERENCES `referraldetails` (`referral_ID`);

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
-- Constraints for table `referraldetails`
--
ALTER TABLE `referraldetails`
  ADD CONSTRAINT `referraldetails_ibfk_1` FOREIGN KEY (`referralName`) REFERENCES `referral` (`referralName`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleName`);
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

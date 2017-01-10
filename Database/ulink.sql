-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2017 at 04:59 AM
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
  `accountID` varchar(50) NOT NULL,
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
  `age` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`accountID`, `clientOwner`, `clientName`, `clientType`, `company`, `nationality`, `gender`, `dateOfBirth`, `email`, `medical`, `mainDiagnosis`, `referredBy`, `PIC`, `appointment`, `doctor`, `specialty`, `clinic`, `otherDoctor`, `followUpPerson`, `followUpPIC`, `hospitalAdmitted`, `log`, `claim`, `visaRequestBy`, `visa`, `visaType`, `visaType2`, `age`) VALUES
('zcrm_1476737000002508009', 'Medical 1', 'New A', 'Individual', '', '', 'Female', '03/08/1983', 'C3&"@hotmail.com"', 'Visa', '', 'Smoo Ref', 'SmooZin', '', '', '', '', '', '', '', '', 'IF(B3="Medical 1","true","false")', 'Yes', 'IF(B3="Visa 1","true","false")', '', '', '', 34),
('zcrm_1476737000002509001', 'Medical 1', 'New B', 'Employee', 'Smoo Smoo', 'Filipino', 'Male', '', 'C4&"@hotmail.com"', 'Visa', '', '', '', '', '', '', '', '', '', '', '', 'IF(B4="Medical 1","true","false")', 'Yes', 'IF(B4="Visa 1","true","false")', '', '', '', 0),
('zcrm_1476737000002509005', 'Medical 1', 'New C', '', '', 'Indonesian', '', '29/08/1960', 'C5&"@hotmail.com"', 'Visa', '', '', 'SmooZin', '', '', '', '', '', '', '', '', 'IF(B5="Medical 1","true","false")', 'No', 'IF(B5="Visa 1","true","false")', '', '', '', 57),
('zcrm_1476737000002509019', 'Visa 1', 'New D', '', '', '', 'Male', '08/05/1962', 'C6&"@hotmail.com"', 'Visa', '', '', '', '', '', '', '', '', '', '', '', 'IF(B6="Medical 1","true","false")', 'No', 'IF(B6="Visa 1","true","false")', '', '', '', 55),
('zcrm_1476737000002509051', 'Medical 1', 'New E', 'Employee', 'Smoo Bee', '', 'Female', '25/01/1960', 'C7&"@hotmail.com"', 'Visa', '', 'Smoo Ref', '', '02/12/2016 16:00', 'Doctor Smoo A', 'SmooSpecialty', 'SmooClinic', '', '', '', 'Mount Elizabeth Orchard Hospital', 'IF(B7="Medical 1","true","false")', 'No', 'IF(B7="Visa 1","true","false")', '', '', '', 57),
('zcrm_1476737000002516001', 'Medical 1', 'New F', '', '', 'Australian', '', '01/01/1957', '', 'Visa', '', '', '', '', '', '', '', '', '', '', 'Mount Elizabeth Novena Hospital', 'IF(B8="Medical 1","true","false")', 'No', 'IF(B8="Visa 1","true","false")', '', '', '', 60),
('zcrm_1476737000002517017', 'Visa 1', 'New G', 'Individual', '', '', 'Female', '07/08/2011', '', 'Visa', '', '', '', '', '', '', '', '', '', '', '', 'IF(B9="Medical 1","true","false")', 'No', 'IF(B9="Visa 1","true","false")', '', '', '', 6),
('zcrm_1476737000002517021', 'Medical 1', 'New H', 'Employee', 'Smoo lah', 'Indonesian', 'Male', '', '', 'Visa', '', '', 'SmooZin', '12-Feb-2016', 'Doctor Smoo B', 'SmooSpecialty', 'SmooClinic', '', '', '', '', 'IF(B10="Medical 1","true","false")', 'No', 'IF(B10="Visa 1","true","false")', '', '', '', 0),
('zcrm_1476737000002518010', 'Medical 1', 'New I', 'Employee', '', '', '', '', '', 'Visa', '', '', '', '', '', '', '', '', '', '', '', 'IF(B11="Medical 1","true","false")', '', 'IF(B11="Visa 1","true","false")', '', '', '', 0),
('Total records in this page :20 Records', '', '', '', '', '', '', '', '', 'Visa', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 0),
('Report Generated by  :', 'Manager', '', '', '', '', '', '', '', 'Visa', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 0),
('Generated on  :', '05/12/2016 14:25', '', '', '', '', '', '', '', 'Visa', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 0);

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
  ADD PRIMARY KEY (`accountID`);

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
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

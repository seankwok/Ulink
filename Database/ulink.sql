-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 06, 2017 at 03:59 AM
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
  `numOfYears` int(90) DEFAULT NULL,
  `ageRequired` int(90) DEFAULT NULL,
  `screening` varchar(255) DEFAULT NULL,
  `type` varchar(90) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `allcondition`
--

INSERT INTO `allcondition` (`ID`, `conditionName`, `numOfYears`, `ageRequired`, `screening`, `type`) VALUES
(1, '1', 1, 1, '1', 'infant,female,male'),
(2, '1', 12, 1, '1', 'infant,female,male'),
(3, '2', 2, 2, '2', 'infant,female,male'),
(4, '2', 2, 2, '2', '[Ljava.lang.String;@f20ee96'),
(5, '2', 2, 2, '2', 'infant'),
(6, '2', 2, 2, '2', 'female'),
(7, '2', 2, 2, '2', 'male');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `passportNumber` varchar(45) NOT NULL,
  `clientName` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `dateOfBirth` datetime(6) DEFAULT NULL,
  `mainDiagnosis` varchar(45) DEFAULT NULL,
  `clientType` varchar(45) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `countryOfResidence` varchar(45) DEFAULT NULL,
  `billingStreet` varchar(45) DEFAULT NULL,
  `billingCity` varchar(45) DEFAULT NULL,
  `billingState` varchar(45) DEFAULT NULL,
  `billingCountry` varchar(45) DEFAULT NULL,
  `billingCode` varchar(45) DEFAULT NULL,
  `isMedical` varchar(45) DEFAULT NULL,
  `isClaim` varchar(45) DEFAULT NULL,
  `claimInformation` varchar(45) DEFAULT NULL,
  `referral_ID` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`passportNumber`, `clientName`, `gender`, `dateOfBirth`, `mainDiagnosis`, `clientType`, `nationality`, `countryOfResidence`, `billingStreet`, `billingCity`, `billingState`, `billingCountry`, `billingCode`, `isMedical`, `isClaim`, `claimInformation`, `referral_ID`) VALUES
('', 'test', 'Female', '2013-02-05 00:00:00.000000', 'undefined', 'undefined', '', '', '', '[object Object]', '', 'undefined', '', 'undefined', NULL, 'undefined', 3),
('a1', 'Merry Kusumawati Utomo', 'Female', '1954-08-25 00:00:00.000000', 'null', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1),
('a10', 'Briana Jones', 'Female', '1954-08-26 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 10),
('a100', 'Peter Joseph Curt Browne', 'Male', '1954-08-27 00:00:00.000000', 'null ', 'null ', 'New Zealand', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 100),
('a1000', 'Danilo Visto Caintoy', 'Male', '1954-08-28 00:00:00.000000', 'null ', 'null ', 'Filipino', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1000),
('a1001', 'KIT SOTHEARO', 'null', '1954-08-29 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1001),
('a1002', 'MS. KIT SOTHEARO', 'null', '1954-08-30 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1002),
('a1003', 'MS. YENNY', 'Female', '1954-08-31 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1003),
('a1004', 'Sambudi', 'Male', '1954-09-01 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1004),
('a1005', 'Marcello Wannes', 'Male', '1954-09-02 00:00:00.000000', 'null ', 'null ', 'Belgium', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1005),
('a1006', 'Ginoy Christian', 'Male', '1954-09-03 00:00:00.000000', 'null ', 'null ', 'Filipino', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1006),
('a1007', 'Tenabolo Yulius', 'Male', '1954-09-04 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1007),
('a1008', 'Budianto Ciawi', 'Male', '1954-09-05 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1008),
('a1009', 'Rasheed Ibrahim', 'Male', '1954-09-06 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1009),
('a101', 'Cooper Andrew Richard', 'Male', '1954-09-07 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 101),
('a1010', 'Lusiana Harjanto', 'Female', '1954-09-08 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1010),
('a1011', 'Erliny Rosalinda', 'Female', '1954-09-09 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1011),
('a1012', 'Erliny Rosalinda', 'Female', '1954-09-10 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1012),
('a1013', 'Ruth Magdalena Mulyo', 'Female', '1954-09-11 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1013),
('a1014', 'Jesslyn Yap', 'null', '1954-09-12 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1014),
('a1015', 'LIE HERMAN HALIEANTO', 'null', '1954-09-13 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1015),
('a1016', 'SISWANTO TANOKO', 'null', '1954-09-14 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1016),
('a1017', 'Yessica Tee', 'Female', '1954-09-15 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1017),
('a1018', 'Vivien Cheng Seng Yee', 'Female', '1954-09-16 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1018),
('a1019', 'Said Elkacimi', 'null', '1954-09-17 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1019),
('a102', 'Hartono Kho', 'Male', '1954-09-18 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 102),
('a1020', 'Curson Brandon Scott', 'null', '1954-09-19 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1020),
('a1021', 'Paul James Bartlett', 'Male', '1954-09-20 00:00:00.000000', 'null ', 'null ', 'UK', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1021),
('a1022', 'karen Maria Michael Limpens', 'null', '1954-09-21 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1022),
('a1023', 'Ms.karen Maria Michael Limpens', 'Female', '1954-09-22 00:00:00.000000', 'null ', 'null ', 'Netherlands', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1023),
('a1024', 'MARCO STACKE SILVA', 'Male', '1954-09-23 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1024),
('a1025', 'ELAINE SCHMITZ', 'Female', '1954-09-24 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1025),
('a1026', 'GIANLUCCA STACKE', 'Male', '1954-09-25 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1026),
('a1027', 'REBECCA STACKE', 'Female', '1954-09-26 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1027),
('a1028', 'FERGUSON KEVIN JACKSON', 'Male', '1954-09-27 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1028),
('a1029', 'KUEK ENG CHYE ANTHONY', 'Male', '1954-09-28 00:00:00.000000', 'null ', 'null ', 'Singaporean', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1029),
('a103', 'Budiaman Tanasal', 'Male', '1954-09-29 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 103),
('a1030', 'KUEK GIA GERARDA CORNEL', 'Female', '1954-09-30 00:00:00.000000', 'null ', 'null ', 'New Zealand', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1030),
('a1031', 'TOPPING GREGORY JAMES', 'Male', '1954-10-01 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1031),
('a1032', 'Mei Lin Clara Babot', 'null', '1954-10-02 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1032),
('a1033', 'Mr Teng Kin Seng', 'Male', '1954-10-03 00:00:00.000000', 'null ', 'null ', 'Malaysian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1033),
('a1034', 'Gazhali Sindhi Surataruna', 'Male', '1954-10-04 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1034),
('a1035', 'Mr Tebonarto', 'Male', '1954-10-05 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1035),
('a1036', 'Siena Klinzing Patrice', 'null', '1954-10-06 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1036),
('a1037', 'Mr Tarif Aziiz', 'Male', '1954-10-07 00:00:00.000000', 'null ', 'null ', 'Bangladeshi', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1037),
('a1038', 'Aswin Salim Andrison', 'Male', '1954-10-08 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1038),
('a1039', 'Kevin Tan Clinic For Diabetes Thyroid & Hormo', 'null', '1954-10-09 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1039),
('a104', 'Chin Sioe Kian', 'Female', '1954-10-10 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 104),
('a1040', 'Loh Boon Shang', 'Female', '1954-10-11 00:00:00.000000', 'null ', 'null ', 'Singaporean', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1040),
('a1041', 'Wong Chin Fook', 'Male', '1954-10-12 00:00:00.000000', 'null ', 'null ', 'Malaysian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1041),
('a1042', 'Kwok Onn Mun', 'Male', '1954-10-13 00:00:00.000000', 'null ', 'null ', 'Singaporean', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1042),
('a1043', 'Chen Chee Jang', 'Male', '1954-10-14 00:00:00.000000', 'null ', 'null ', 'Malaysian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1043),
('a1044', 'Ang Wee Boon', 'Male', '1954-10-15 00:00:00.000000', 'null ', 'null ', 'Malaysian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1044),
('a1045', 'Febriyana Santoso', 'Female', '1954-10-16 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1045),
('a1046', 'Febriyana Santoso', 'Female', '1954-10-17 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1046),
('a1047', 'Febriyana Santoso', 'Female', '1954-10-18 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1047),
('a1048', 'Febriyana Santoso', 'Female', '1954-10-19 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1048),
('a1049', 'Febriyana Santoso', 'Female', '1954-10-20 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1049),
('a105', 'SARA RAFEL CUFI', 'Female', '1954-10-21 00:00:00.000000', 'null ', 'null ', 'ZZZOthers', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 105),
('a1050', 'MUHAMMAD ABI RASYID', 'Male', '1954-10-22 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1050),
('a1051', 'Sugiyanto Rustiadi', 'Male', '1954-10-23 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1051),
('a1052', 'Yoehan Wahyudi Santoso', 'Male', '1954-10-24 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1052),
('a1053', 'Said Elkacimi', 'Male', '1954-10-25 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1053),
('a1054', 'Tevilyan Yudhistira Rusli', 'Male', '1954-10-26 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1054),
('a1055', 'Mr. Dhruv Venugopalan Menon', 'Male', '1954-10-27 00:00:00.000000', 'null ', 'null ', 'Indian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1055),
('a1056', 'Rodolfo Alfredo Giambelli', 'Male', '1954-10-28 00:00:00.000000', 'null ', 'null ', 'Italian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1056),
('a1057', 'Mr. Taylor Robert John', 'null', '1954-10-29 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1057),
('a1058', 'Mr Tebonarto', 'Male', '1954-10-30 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1058),
('a1059', 'Mr Tebonarto', 'Male', '1954-10-31 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1059),
('a106', 'Martinez Mengual Antonio', 'Male', '1954-11-01 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 106),
('a1060', 'Febriyana Santoso', 'Female', '1954-11-02 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1060),
('a1061', 'Vandi Andreanus', 'Male', '1954-11-03 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1061),
('a1062', 'Djoenaedi Joesoef', 'Male', '1954-11-04 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1062),
('a1063', 'Pearce Luke Bradley', 'Male', '1954-11-05 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1063),
('a1064', 'PT Indika Inti Corpindo', 'null', '1954-11-06 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1064),
('a1065', 'Mr Tebonarto', 'Male', '1954-11-07 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1065),
('a1066', 'Hira Akbar Wahaj', 'Male', '1954-11-08 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1066),
('a1067', 'Mr. Simen Skaare Eriksen', 'null', '1954-11-09 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1067),
('a1068', 'Christopher Dylan Mckeavenvy', 'Male', '1954-11-10 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1068),
('a1069', 'Holl Brian Francis', 'Male', '1954-11-11 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1069),
('a107', 'Budihardjo Chandra', 'Male', '1954-11-12 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 107),
('a1070', 'Goyat Jean Francois', 'Male', '1954-11-13 00:00:00.000000', 'null ', 'null ', 'France', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1070),
('a1071', 'Van Alstine Jeffery Victor', 'Male', '1954-11-14 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1071),
('a1072', 'Puspita Dewi', 'Female', '1954-11-15 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1072),
('a1073', 'Syafruddin Kambo', 'Male', '1954-11-16 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1073),
('a1074', 'Ulink', 'null', '1954-11-17 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1074),
('a1075', 'Febriyana Santoso', 'Female', '1954-11-18 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1075),
('a1076', 'Djoenaedi Joesoef', 'Male', '1954-11-19 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1076),
('a1077', 'Alvin Lynn Martin Jr', 'Male', '1954-11-20 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1077),
('a1078', 'Jacque De Beer', 'Male', '1954-11-21 00:00:00.000000', 'null ', 'null ', 'South African', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1078),
('a1079', 'DATO SRI FARID RIDZUAN', 'Male', '1954-11-22 00:00:00.000000', 'null ', 'null ', 'Malaysian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1079),
('a108', 'Philip George Martin', 'Male', '1954-11-23 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 108),
('a1080', 'PRICILLA TIU SIEW CHUEN', 'Female', '1954-11-24 00:00:00.000000', 'null ', 'null ', 'Malaysian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1080),
('a1081', 'SHARON CALLISTA RAIN', 'Female', '1954-11-25 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1081),
('a1082', 'Bryski Ulf', 'Male', '1954-11-26 00:00:00.000000', 'null ', 'null ', 'German', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1082),
('a1083', 'Wilson Lawrence Keith', 'Male', '1954-11-27 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1083),
('a1084', 'Kate Maree Druhan', 'Female', '1954-11-28 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1084),
('a1085', 'Seah Raymond  Charles o Grady', 'Male', '1954-11-29 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1085),
('a1086', 'Patrick Conor Druhan o  Grady', 'Male', '1954-11-30 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1086),
('a1087', 'Eamon Charles Druhan o Grady', 'Male', '1954-12-01 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1087),
('a1088', 'Eloise Catherine Druhan o Grady', 'Male', '1954-12-02 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1088),
('a1089', 'Liem Po Tjo', 'Male', '1954-12-03 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1089),
('a109', 'Timothy John Hammel', 'Male', '1954-12-04 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 109),
('a1090', 'Inti Chendana', 'Male', '1954-12-05 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1090),
('a1091', 'Febriyana Santoso', 'Female', '1954-12-06 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1091),
('a1092', 'Febriyana Santoso', 'Female', '1954-12-07 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1092),
('a1093', 'Neak Vantha', 'Female', '1954-12-08 00:00:00.000000', 'null ', 'null ', 'Cambodian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1093),
('a1094', 'W Calvin Lewis Collins', 'Male', '1954-12-09 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1094),
('a1095', 'Naufal Dira T, Mr', 'Male', '1954-12-10 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1095),
('a1096', 'TJOA BENG HUI', 'Male', '1954-12-11 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1096),
('a1097', 'Surya Candra', 'Male', '1954-12-12 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1097),
('a1098', 'Liem Agus Harianto', 'Male', '1954-12-13 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1098),
('a1099', 'Mdm Lay Lie Sian', 'Female', '1954-12-14 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1099),
('a11', 'David Mckenzie Lee', 'Male', '1954-12-15 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 11),
('a110', 'Rachmadi Joesoef', 'Male', '1954-12-16 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 110),
('a1100', 'Mdm Lay Lie Sian', 'Female', '1954-12-17 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1100),
('a1101', 'Thiono Prasetya Harry Hartono', 'null', '1954-12-18 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1101),
('a1102', 'Tang Maxwell', 'null', '1954-12-19 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1102),
('a1103', 'Novita Inge', 'null', '1954-12-20 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1103),
('a1104', 'Lie Bie Kim', 'null', '1954-12-21 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1104),
('a1105', 'Estrada Carbonell Juliana', 'Female', '1954-12-22 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1105),
('a1106', 'Ms. Estrada Carbonell Juliana', 'null', '1954-12-23 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1106),
('a1107', 'Leong  E Sun', 'Male', '1954-12-24 00:00:00.000000', 'null ', 'null ', 'Singaporean', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1107),
('a1108', 'MATHIAS SCHROEDER', 'Male', '1954-12-25 00:00:00.000000', 'null ', 'null ', 'Dutch', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1108),
('a1109', 'Martin Phillip George', 'Male', '1954-12-26 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1109),
('a111', 'Rosdiaty  Binti Zainul Abidin', 'Female', '1954-12-27 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 111),
('a1110', 'Kenneth Bruce Smith', 'Male', '1954-12-28 00:00:00.000000', 'null ', 'null ', 'UK', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1110),
('a1111', 'Ordanza Charlo Icalla', 'Male', '1954-12-29 00:00:00.000000', 'null ', 'null ', 'Filipino', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1111),
('a1112', 'Visto Danilo cainoy', 'Male', '1954-12-30 00:00:00.000000', 'null ', 'null ', 'Filipino', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1112),
('a1113', 'Abdullah  Hamed Ahmed Al Manji', 'Male', '1954-12-31 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1113),
('a1114', 'Joselito Apolinario Peralta Calayo', 'Male', '1955-01-01 00:00:00.000000', 'null ', 'null ', 'Filipino', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1114),
('a1115', 'Adrianta Widyanata', 'Male', '1955-01-02 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1115),
('a1116', 'Mr Tebonarto', 'Male', '1955-01-03 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1116),
('a1117', 'Mdm Lay Lie Sian', 'Female', '1955-01-04 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1117),
('a1118', 'Nadya Nathania Santoso', 'Female', '1955-01-05 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1118),
('a1119', 'Rahel Monalisa Hutagaol', 'null', '1955-01-06 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1119),
('a112', 'Santi Laksmi Surya', 'Female', '1955-01-07 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 112),
('a1120', 'Wilbert Sean Dennys', 'Male', '1955-01-08 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1120),
('a1121', 'Inti Chendana', 'Male', '1955-01-09 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1121),
('a1122', 'ROBERT MERRITT TRAISTER', 'Male', '1955-01-10 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1122),
('a1123', 'Gopalakrishnan Surianarayana', 'null', '1955-01-11 00:00:00.000000', 'null ', 'null ', 'Singaporean', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1123),
('a1124', 'Najmiah Binti Ringkas', 'null', '1955-01-12 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1124),
('a1125', 'F Lusiana Harjanto', 'Female', '1955-01-13 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1125),
('a1126', 'Claudia Ciona Simons', 'null', '1955-01-14 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1126),
('a1127', 'Theresia Merdeka Putri', 'null', '1955-01-15 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1127),
('a1128', 'Anke Andree', 'Female', '1955-01-16 00:00:00.000000', 'null ', 'null ', 'German', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1128),
('a1129', 'Ellisa waluyo', 'Female', '1955-01-17 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1129),
('a113', 'Candole Daal Mario', 'Male', '1955-01-18 00:00:00.000000', 'null ', 'null ', 'Filipino', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 113),
('a1130', 'Ms. Ellisa waluyo', 'null', '1955-01-19 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1130),
('a1131', 'Rosna', 'null', '1955-01-20 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1131),
('a1132', 'Lena Maria Suhr', 'null', '1955-01-21 00:00:00.000000', 'null ', 'null ', 'Swiss', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1132),
('a1133', 'Andrew William Wilson', 'Male', '1955-01-22 00:00:00.000000', 'null ', 'null ', 'UK', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1133),
('a1134', 'Ham Michael Jeffrey', 'Male', '1955-01-23 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1134),
('a1135', 'James Arthur Ahuman III', 'Male', '1955-01-24 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1135),
('a1136', 'Willis Keith Allen', 'Male', '1955-01-25 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1136),
('a1137', 'Rominger Christopher Neal', 'Male', '1955-01-26 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1137),
('a1138', 'Fox Robert Virgil', 'Male', '1955-01-27 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1138),
('a1139', 'Paldino IIIdonald Richard', 'Male', '1955-01-28 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1139),
('a114', 'Khin Win Kyi', 'Female', '1955-01-29 00:00:00.000000', 'null ', 'null ', 'Burmese', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 114),
('a1140', 'Eanes William Charles', 'Male', '1955-01-30 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1140),
('a1141', 'Davis Carl eric', 'Male', '1955-01-31 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1141),
('a1142', 'Anthony Paul Diemart', 'Male', '1955-02-01 00:00:00.000000', 'null ', 'null ', 'Canadian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1142),
('a1143', 'Ulink myanmar', 'null', '1955-02-02 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1143),
('a1144', 'Raymond Eka Febio', 'null', '1955-02-03 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1144),
('a1145', 'Win Htein', 'Male', '1955-02-04 00:00:00.000000', 'null ', 'null ', 'Burmese', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1145),
('a1146', 'SOEHONO TJIPTOHARDJO', 'null', '1955-02-05 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1146),
('a1147', 'TIN TIN SALY', 'null', '1955-02-06 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1147),
('a1148', 'Taufik Ahmad', 'Male', '1955-02-07 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1148),
('a1149', 'Leon Adriaan carstens', 'Male', '1955-02-08 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1149),
('a115', 'Wendy Saputra', 'null', '1955-02-09 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 115),
('a1150', 'Vergel Villamor Tanio', 'Male', '1955-02-10 00:00:00.000000', 'null ', 'null ', 'Filipino', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1150),
('a1151', 'Singapore International Eye Cataract Retina C', 'null', '1955-02-11 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1151),
('a1152', 'Ulin myanmar', 'null', '1955-02-12 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1152),
('a1153', 'Fei Tony Liu', 'Male', '1955-02-13 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1153),
('a1154', 'Fleischmann Klaus Bernhard', 'Male', '1955-02-14 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1154),
('a1155', 'Widagdho Santoso', 'Male', '1955-02-15 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1155),
('a1156', 'Wihelmus Yopi Mulyono', 'Male', '1955-02-16 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1156),
('a1157', 'Wihelmus Yopi Mulyono', 'Male', '1955-02-17 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1157),
('a1158', 'Denny Yoanata', 'Male', '1955-02-18 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1158),
('a1159', 'Odalric Arifin Shiayadi', 'Male', '1955-02-19 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1159),
('a116', 'Duncan Hustler', 'null', '1955-02-20 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 116),
('a1160', 'Gouw Giok Swan', 'Female', '1955-02-21 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1160),
('a1161', 'Mohamed Ismail Mohamed Sayeek', 'Male', '1955-02-22 00:00:00.000000', 'null ', 'null ', 'Sri Lanka', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1161),
('a1162', 'Rebecca L Edwards', 'Female', '1955-02-23 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1162),
('a1163', 'Taufik Ahmad', 'Male', '1955-02-24 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1163),
('a1164', 'Tjai Shong Tjie', 'Male', '1955-02-25 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1164),
('a1165', 'Gouw Giok Swan', 'Female', '1955-02-26 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1165),
('a1166', 'Vinsen Martheo', 'Male', '1955-02-27 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1166),
('a1167', 'Violison Martheo', 'Male', '1955-02-28 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1167),
('a1168', 'Richard Capone', 'Male', '1955-03-01 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1168),
('a1169', 'Gandung Pujo Purnomo', 'Male', '1955-03-02 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1169),
('a117', 'O & G Centre', 'null', '1955-03-03 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 117),
('a1170', 'Gandung Pujo Purnomo', 'Male', '1955-03-04 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1170),
('a1171', 'Jones Emrys Daniel Blakelee', 'Male', '1955-03-05 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1171),
('a1172', 'Jerram Donald William', 'Male', '1955-03-06 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1172),
('a1173', 'Seccombe James Henry Philip', 'Male', '1955-03-07 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1173),
('a1174', 'Jeane Marie Garcia Davis', 'null', '1955-03-08 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1174),
('a1175', 'Gandung Pujo Purnomo', 'Male', '1955-03-09 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1175),
('a1176', 'SRI HANDAYANI', 'Female', '1955-03-10 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1176),
('a1177', 'Temu Leksono Widodo', 'Male', '1955-03-11 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1177),
('a1178', 'FMC Technologies Singapore Pte Ltd', 'null', '1955-03-12 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1178),
('a1179', 'Celina Prema Citro', 'Female', '1955-03-13 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1179),
('a118', 'Martin Kuratli', 'Male', '1955-03-14 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 118),
('a1180', 'Veronica Gunawan Koh', 'Female', '1955-03-15 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1180),
('a1181', 'Cadence Kyrie Sanusi', 'null', '1955-03-16 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1181),
('a1182', 'Vivien Cheng Seng Yee', 'Female', '1955-03-17 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1182),
('a1183', 'Marcell Anggatama', 'null', '1955-03-18 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1183),
('a1184', 'Marcella Anggatama', 'null', '1955-03-19 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1184),
('a1185', 'Hariya Albertus', 'Male', '1955-03-20 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1185),
('a1186', 'Hariya Albertus', 'Male', '1955-03-21 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1186),
('a1187', 'Hariya Albertus', 'Male', '1955-03-22 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1187),
('a1188', 'Mathew Bion', 'Male', '1955-03-23 00:00:00.000000', 'null ', 'null ', 'German', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1188),
('a1189', 'Benedict Denzel Haryanto', 'Male', '1955-03-24 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1189),
('a119', 'Hiek Naiy', 'null', '1955-03-25 00:00:00.000000', 'null ', 'null ', 'Cambodian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 119),
('a1190', 'Surya Wonotirto', 'Male', '1955-03-26 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1190),
('a1191', 'Liem Po Tjo', 'Male', '1955-03-27 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1191),
('a1192', 'CHHAY LIMHEANG', 'null', '1955-03-28 00:00:00.000000', 'null ', 'null ', 'Cambodian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1192),
('a1193', 'Richelle Charisse Chow', 'Female', '1955-03-29 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1193),
('a1194', 'Craig Winnett', 'Male', '1955-03-30 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1194),
('a1195', 'Maureem Victoria Tarunadjaja', 'null', '1955-03-31 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1195),
('a1196', 'Grand Copthorne Waterfront Hotel', 'null', '1955-04-01 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1196),
('a1197', 'NIZAR SAIFUDIN HALIM', 'Male', '1955-04-02 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1197),
('a1198', 'Samantha Jocelyn Wijaya', 'Female', '1955-04-03 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1198),
('a1199', 'Ms. Samantha Jocelyn Wijaya', 'null', '1955-04-04 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1199),
('a12', 'Edward Newbold', 'Male', '1955-04-05 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 12),
('a120', 'KIM DEBRA FAIRBAIRN', 'Female', '1955-04-06 00:00:00.000000', 'null ', 'null ', 'Australian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 120),
('a1200', 'NURIASARI', 'Female', '1955-04-07 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1200),
('a1201', 'PT Taman Kemang Raya', 'null', '1955-04-08 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1201),
('a1202', 'Foo San Kan', 'Male', '1955-04-09 00:00:00.000000', 'null ', 'null ', 'Malaysian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1202),
('a1203', 'Foo San Kan', 'Male', '1955-04-10 00:00:00.000000', 'null ', 'null ', 'Malaysian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1203),
('a1204', 'Vivien Cheng Seng Yee', 'Female', '1955-04-11 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1204),
('a1205', 'SUNIL GOBIND BHOJWANI', 'null', '1955-04-12 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1205),
('a1206', 'Jozsef Micski', 'Male', '1955-04-13 00:00:00.000000', 'null ', 'null ', 'Swiss', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1206),
('a1207', 'Surya Wonotirto', 'Male', '1955-04-14 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1207),
('a1208', 'Tony John Djohan', 'Male', '1955-04-15 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1208),
('a1209', 'Ardian Hendarto', 'Male', '1955-04-16 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1209),
('a121', 'Wawan Darmawan', 'Male', '1955-04-17 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 121),
('a1210', 'Murli Mahesh Chandumal Mirpuri', 'Male', '1955-04-18 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1210),
('a1211', 'Paula Miller', 'Female', '1955-04-19 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1211),
('a1212', 'John Kleinhenz', 'Male', '1955-04-20 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1212),
('a1213', 'Leni', 'Female', '1955-04-21 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1213),
('a1214', 'Dedy Purnomo Oeij', 'Male', '1955-04-22 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1214),
('a1215', 'AMINDA BESTARI', 'Female', '1955-04-23 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1215),
('a1216', 'ANINDA BESTARI', 'null', '1955-04-24 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1216),
('a1217', 'Yoseph Ariel Noerdjaya', 'Male', '1955-04-25 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1217),
('a1218', 'Gitta Rakhmaningrum', 'Female', '1955-04-26 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1218),
('a1219', 'Giovani Valencia', 'Female', '1955-04-27 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1219),
('a122', 'A. ANNEKE DEN DULK VOS', 'Female', '1955-04-28 00:00:00.000000', 'null ', 'null ', 'Dutch', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 122),
('a1220', 'Giovani Valencia', 'Female', '1955-04-29 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1220),
('a1221', 'Ignasius Sago', 'Male', '1955-04-30 00:00:00.000000', 'null ', 'null ', 'Indonesian', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'No', 'null ', 1221),
('a1222', 'Gregory Clark', 'Male', '1955-05-01 00:00:00.000000', 'null ', 'null ', 'American', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'Yes', 'null ', 1222),
('a1223', 'PT Manaaged Pressure Operations', 'null', '1955-05-02 00:00:00.000000', 'null ', 'null ', 'null', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null ', 'null', 'null ', 1223),
('F1299823102', 'Client Tester 1', 'Male', '1982-04-20 00:00:00.000000', 'undefined', 'undefined', 'Chinese', 'China', '', '[object Object]', '', 'undefined', '', 'undefined', NULL, 'undefined', 3),
('G1234456g', 'SeanClient', 'Male', '1990-10-01 00:00:00.000000', NULL, NULL, 'Indo', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
('qwewqewqeqw', 'SeanClient1', 'Female', '1991-09-24 00:00:00.000000', NULL, NULL, 'none', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
('admin2', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'undefined');

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
  ADD PRIMARY KEY (`passportNumber`),
  ADD KEY `referral_ID` (`referral_ID`);

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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`referral_ID`) REFERENCES `referraldetails` (`referral_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

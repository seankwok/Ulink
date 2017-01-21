-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2017 at 05:41 AM
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

-- --------------------------------------------------------

--
-- Table structure for table `allcondition`
--

DROP TABLE IF EXISTS `allcondition`;
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

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `ID` int(255) NOT NULL,
  `AccountID` varchar(1000) NOT NULL,
  `clientOwner` varchar(50) NOT NULL,
  `clientName` varchar(255) NOT NULL,
  `clientType` varchar(50) NOT NULL,
  `company` varchar(255) NOT NULL,
  `nationality` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `dateOfBirth` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `medical` varchar(50) NOT NULL,
  `mainDiagnosis` varchar(50) NOT NULL,
  `referredBy` varchar(50) NOT NULL,
  `PIC` varchar(255) NOT NULL,
  `appointment` varchar(50) NOT NULL,
  `doctor` varchar(50) NOT NULL,
  `specialty` varchar(50) NOT NULL,
  `clinic` varchar(200) NOT NULL,
  `otherDoctor` varchar(255) NOT NULL,
  `followUpPerson` varchar(50) NOT NULL,
  `followUpPIC` varchar(50) NOT NULL,
  `hospitalAdmitted` varchar(50) NOT NULL,
  `log` varchar(50) NOT NULL,
  `claim` varchar(50) NOT NULL,
  `visaRequestBy` varchar(255) NOT NULL,
  `visa` varchar(50) NOT NULL,
  `visaType` varchar(50) NOT NULL,
  `visaType2` varchar(50) NOT NULL,
  `age` int(5) NOT NULL,
  `billingCity` varchar(1000) NOT NULL,
  `BillingCode` varchar(1000) NOT NULL,
  `BillingCountry` varchar(1000) NOT NULL,
  `BillingState` varchar(1000) NOT NULL,
  `BillingStreet` varchar(1000) NOT NULL,
  `CreatedTime` date NOT NULL,
  `phone` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`ID`, `AccountID`, `clientOwner`, `clientName`, `clientType`, `company`, `nationality`, `gender`, `dateOfBirth`, `email`, `medical`, `mainDiagnosis`, `referredBy`, `PIC`, `appointment`, `doctor`, `specialty`, `clinic`, `otherDoctor`, `followUpPerson`, `followUpPIC`, `hospitalAdmitted`, `log`, `claim`, `visaRequestBy`, `visa`, `visaType`, `visaType2`, `age`, `billingCity`, `BillingCode`, `BillingCountry`, `BillingState`, `BillingStreet`, `CreatedTime`, `phone`) VALUES
(1, 'zcrm_null', 'Medical 1', 'Alice', 'Individual', '', 'American', 'Male', '15/09/1967', '', 'Medical', '', 'US Emb', '', '27/06/2016 10:45', 'Kevin Tan Eng Kiat', 'Medicine - Endocrinology', 'Kevin Tan Clinic for Diabetes, Thyroid & Hormones Pte Ltd', '', 'Zin', '', '', 'false', 'Yes', '', 'false', '', '', 50, '', '', '', '', '', '2016-05-17', ''),
(2, 'zcrm_null', 'Medical 1', 'Bob', '', '', '', '', '15/09/1968', '', 'Visa', '', '', '', '', '', '', '', '', '', '', '', 'false', '', '', 'false', '', '', 49, '', '', '', '', '', '2016-06-17', ''),
(3, 'zcrm_null', 'Medical 1', 'Cheetah', '', '', 'Malaysian', '', '14/05/1942', '', 'Visa', '', 'EMA', '', '19/06/2016 09:00', '', '', '', 'Dr Richard Chew', 'Andreas', '', 'Mount Elizabeth Orchard Hospital', 'false', '', '', 'false', '', '', 75, '', '', '', '', '', '2016-06-20', ''),
(4, 'zcrm_null', 'Medical 1', 'Wok Seng ', 'Individual', '', 'Malaysian', '', '14/05/1942', '', 'Medical', '', 'EMA', '', '19/06/2016 17:00', '', '', '', 'Dr Richard Chew', 'Andreas', '', 'Mount Elizabeth Orchard Hospital', 'false', 'No', '', 'false', '', '', 75, '', '', '', '', '', '2016-07-20', ''),
(5, 'zcrm_1476737000001608001', 'Visa 1', 'Du DingDing', 'Employee', 'HUSKY CNOOC MADURA LIMITED', 'Chinese', 'Male', '09/06/1981', 'Dingyu.Du@cnoocuganda.com', 'Visa', '', '', '', '', '', '', '', '', '', '', '', 'false', '', 'Company', 'true', 'Indonesia - VTT 312 - More than 6 months', '', 36, '', '', '', '', '', '2016-07-20', '8613723757329'),
(6, 'zcrm_null', 'Medical 1', 'Minh Malina', 'Individual', '', 'Cambodian', 'Female', '21/09/1997', '', 'Medical', '', 'SMG', '', '20/06/2016 21:00', 'Kelvin Thia Teck Joo', 'Medicine - Gastroenterology', 'Hope Gastroenterology Liver Clinic', '', 'Jes', '', 'Mount Elizabeth Orchard Hospital', 'false', 'No', '', 'false', '', '', 20, '', '', '', '', '', '2016-08-21', ''),
(7, 'zcrm_1476737000000132240', 'Visa 1', 'James Walter ', 'Employee', 'PT JDA - Indonesia', 'American', 'Male', '15/03/1954', '', 'Visa', '', '', '', '', '', '', '', '', '', '', '', 'false', '', 'Company', 'true', 'Indonesia - VTT 312 - More than 6 months', '', 63, '', '', '', '', '', '2016-09-21', '628111094124'),
(8, 'zcrm_null', 'Medical 1', 'Joyce ', 'Individual', '', 'Indonesian', 'Female', '17/10/1957', '', 'Medical', '', 'Melivon', '', '22/06/2016 14:45', '', '', '', 'Dr. Loh Kok Kit', 'Andreas', '', '', 'false', 'No', '', 'false', '', '', 60, '', '', '', '', '', '2016-09-22', ''),
(9, 'zcrm_null', 'Medical 1', 'Joyce ', 'Individual', '', 'Indonesian', 'Female', '17/10/1957', '', 'Medical', '', 'Melivon', '', '22/06/2016 16:45', 'Adrian Saurajen Siew Ming', 'Surgery - ENT', 'Ear Nose Throat & Snoring Centre Pte Ltd', '', 'Andreas', '', '', 'false', 'No', '', 'false', '', '', 60, '', '', '', '', '', '2016-10-22', ''),
(10, 'zcrm_null', 'Medical 1', 'Khan ', 'Individual', '', 'Bangladeshi', 'Female', '04/10/1967', '', 'Medical', '', 'Mostafizur', '', '09/07/2016 11:00', '', '', '', 'Dr. Richard Chew K. H', 'Zin', '', '', 'false', 'No', '', 'false', '', '', 50, '', '', '', '', '', '2016-11-22', ''),
(11, 'zcrm_null', 'Medical 1', 'Kennedy', 'Individual', '', 'Cambodian', 'Male', '17/02/1982', '', 'Medical', '', 'SMG', '', '23/06/2016 16:00', 'Tay Hin Ngan', 'Surgery - ENT', 'HN Tay ENT Head & Neck Thyroid Sleep Robotic Surgery', '', 'Jes', '', 'Mount Elizabeth Orchard Hospital', 'false', 'No', '', 'false', '', '', 35, '', '', '', '', '', '2016-11-22', ''),
(12, 'zcrm_null', 'Medical 1', 'Adrian ', 'Individual', '', 'American', 'Male', '02/11/2001', '', 'Medical', '', 'US Emb', '', '27/06/2016 10:45', 'Kevin Tan Eng Kiat', 'Medicine - Endocrinology', 'Kevin Tan Clinic for Diabetes, Thyroid & Hormones Pte Ltd', '', 'Zin', '', '', 'false', 'Yes', '', 'false', '', '', 16, '', '', '', '', '', '2016-12-22', ''),
(13, 'zcrm_null', 'Medical 1', 'Vovan', 'Individual', '', '', 'Male', '12/07/1959', '', 'Medical', '', 'SMG', '', '23/06/2016 11:00', '', '', '', 'Dr. Kevin Soh Boon Keng', 'Jes', '', 'Mount Elizabeth Orchard Hospital', 'false', 'No', '', 'false', '', '', 58, '', '', '', '', '', '2016-12-22', ''),
(14, 'zcrm_null', 'Medical 1', 'Liu Xuan', 'Individual', '', 'Chinese', 'Female', '22/07/1985', '', 'Medical', '', '', '', '24/06/2016 09:00', 'Tham Kok Fun', 'Obs & Gynae', 'O&G Centre', '', 'Jes', '', '', 'false', '', '', 'false', '', '', 32, '', '', '', '', '', '2016-12-22', ''),
(15, 'zcrm_null', 'Visa 1', 'PT. Exploration Geosolutions', '', '', '', '', '22/07/1986', '', 'Visa', '', '', '', '', '', '', '', '', '', '', '', 'false', '', '', 'false', '', '', 31, '', '', '', '', '', '2017-01-13', ''),
(16, 'zcrm_1476737000000146005', 'Visa 1', 'Dionisio Jhonna Laine Policarpio', 'Employee', 'PT Archetype Indonesia', 'Filipino', 'Female', '22/07/1987', 'rico.dionisio@archetype-group.com', 'Visa', '', '', '', '', '', '', '', '', '', '', '', 'false', '', 'Company', 'true', 'Indonesia - VKU 211', '', 30, '', '', '', '', '', '2017-01-18', '84584031'),
(17, 'zcrm_null', 'Medical 1', 'Alexander Harjanto', 'Individual', '', 'Indonesian', 'Male', '19/09/1956', '', 'Medical', '', 'Andreas', '', '16/07/2016 11:00', 'Goh Boon Kee', 'Medicine - Dermatology', 'SMG: Skin Physicians', '', 'Andreas', '', '', 'false', 'Yes', '', 'false', '', '', 61, '', '', '', '', '', '2017-01-18', ''),
(18, 'zcrm_null', 'Medical 1', 'Alexander Harjanto', 'Individual', '', 'Indonesian', 'Male', '19/09/1956', '', 'Medical', '', 'Andreas', '', '18/07/2016 11:00', '', '', '', 'Dr. Ng Pock Liok', 'Andreas', '', '', 'false', 'No', '', 'false', '', '', 61, '', '', '', '', '', '2017-01-19', ''),
(19, 'zcrm_null', 'Medical 1', 'Benny Wong', 'Individual', '', 'Indonesian', 'Male', '19/09/1956', '', 'Medical', '', 'Andreas', '', '16/07/2016 11:00', 'Goh Boon Kee', 'Medicine - Dermatology', 'SMG: Skin Physicians', '', 'Andreas', '', '', 'false', 'Yes', '', 'false', '', '', 61, '', '', '', '', '', '2017-01-20', ''),
(20, 'zcrm_null', 'Medical 1', 'Jasper Sia', 'Individual', '', 'Indonesian', 'Male', '19/09/1956', '', 'Medical', '', 'Andreas', '', '18/07/2016 11:00', '', '', '', 'Dr. Ng Pock Liok', 'Andreas', '', '', 'false', 'No', '', 'false', '', '', 61, '', '', '', '', '', '2017-01-20', '');

-- --------------------------------------------------------

--
-- Table structure for table `uploadtime`
--

DROP TABLE IF EXISTS `uploadtime`;
CREATE TABLE `uploadtime` (
  `ID` int(5) NOT NULL,
  `dateTime` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uploadtime`
--

INSERT INTO `uploadtime` (`ID`, `dateTime`) VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL),
(5, NULL),
(6, NULL),
(7, NULL),
(8, NULL),
(9, NULL),
(10, NULL),
(11, NULL),
(12, NULL),
(13, NULL),
(14, NULL),
(15, NULL),
(16, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
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
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `uploadtime`
--
ALTER TABLE `uploadtime`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

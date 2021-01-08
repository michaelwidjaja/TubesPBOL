-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2021 at 03:56 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tubespbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `elements`
--

CREATE TABLE `elements` (
  `Id` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `elements`
--

INSERT INTO `elements` (`Id`, `Name`) VALUES
(1, 'Ice'),
(2, 'Fire'),
(3, 'Wind'),
(4, 'Earth'),
(5, 'Lightning'),
(6, 'Water'),
(7, 'Optional[w]');

-- --------------------------------------------------------

--
-- Table structure for table `enemymonster`
--

CREATE TABLE `enemymonster` (
  `Id` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `HP` int(11) DEFAULT NULL,
  `Attack` int(11) DEFAULT NULL,
  `Elements_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `enemymonster`
--

INSERT INTO `enemymonster` (`Id`, `Name`, `HP`, `Attack`, `Elements_Id`) VALUES
(1, 'BOSS', 5000, 20, 2);

-- --------------------------------------------------------

--
-- Table structure for table `monsters`
--

CREATE TABLE `monsters` (
  `Id` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `HP` int(11) DEFAULT NULL,
  `Attack` int(11) DEFAULT NULL,
  `Elements_Id` int(11) NOT NULL,
  `User_idpengguna` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `monsters`
--

INSERT INTO `monsters` (`Id`, `Name`, `HP`, `Attack`, `Elements_Id`, `User_idpengguna`) VALUES
(1, 'Curacas', 100, 5, 1, 1),
(2, 'Firagas', 100, 10, 2, 1),
(3, 'Bouldar', 1000, 3, 4, 2),
(4, 'Vipara', 200, 2, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idpengguna` int(11) NOT NULL,
  `Nama` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idpengguna`, `Nama`, `username`, `password`) VALUES
(1, 'Admin', 'Admin', 'Admin'),
(2, 'Andika', 'dika', 'ukm123'),
(15, 'can', 'can', '12345'),
(16, 'mek', 'maksdmaks', 'wawaw');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `elements`
--
ALTER TABLE `elements`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `enemymonster`
--
ALTER TABLE `enemymonster`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Elements_Id` (`Elements_Id`);

--
-- Indexes for table `monsters`
--
ALTER TABLE `monsters`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_Monsters_Elements_idx` (`Elements_Id`),
  ADD KEY `fk_user_idpengguna_idx` (`User_idpengguna`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idpengguna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `elements`
--
ALTER TABLE `elements`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `monsters`
--
ALTER TABLE `monsters`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `idpengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `enemymonster`
--
ALTER TABLE `enemymonster`
  ADD CONSTRAINT `Elements_Id` FOREIGN KEY (`Elements_Id`) REFERENCES `elements` (`Id`);

--
-- Constraints for table `monsters`
--
ALTER TABLE `monsters`
  ADD CONSTRAINT `fk_Monsters_Elements` FOREIGN KEY (`Elements_Id`) REFERENCES `elements` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_idpengguna` FOREIGN KEY (`User_idpengguna`) REFERENCES `user` (`idpengguna`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

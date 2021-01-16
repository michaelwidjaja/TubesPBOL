-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 16, 2021 at 06:29 AM
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
(6, 'Water');

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
(1, 'BOSS', 100, 20, 2),
(2, 'Raichu', 85, 25, 1),
(3, 'Pikachu', 98, 20, 3);

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `id` int(15) NOT NULL,
  `tanggal` date NOT NULL,
  `id_user` int(15) NOT NULL,
  `win/lose` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(1, 'Curacas', 95, 5, 1, 1),
(2, 'Firagas', 80, 10, 2, 1),
(3, 'Bouldar', 97, 3, 4, 2),
(4, 'Vipara', 95, 2, 3, 2),
(5, 'Naga', 179, 41, 2, 1),
(6, 'kucing', 78, 30, 6, 1);


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
(3, 'can', 'can', '12345');

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
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_user` (`id_user`);

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
-- AUTO_INCREMENT for table `enemymonster`
--
ALTER TABLE `enemymonster`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `monsters`
--
ALTER TABLE `monsters`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `idpengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `enemymonster`
--
ALTER TABLE `enemymonster`
  ADD CONSTRAINT `Elements_Id` FOREIGN KEY (`Elements_Id`) REFERENCES `elements` (`Id`);

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`idpengguna`);

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

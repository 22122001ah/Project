-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql7.freemysqlhosting.net
-- Generation Time: Feb 10, 2023 at 03:44 PM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql7582716`
--
CREATE DATABASE IF NOT EXISTS `sql7582716` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sql7582716`;

-- --------------------------------------------------------

--
-- Table structure for table `Artists`
--

CREATE TABLE `Artists` (
  `artist_id` int(11) NOT NULL,
  `artist_name` varchar(45) COLLATE latin2_croatian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COLLATE=latin2_croatian_ci;

--
-- Dumping data for table `Artists`
--

INSERT INTO `Artists` (`artist_id`, `artist_name`) VALUES
(1, 'Emir Hadžihafizbegović'),
(2, 'Tatjana Šojić'),
(3, 'Senad Bašić'),
(4, 'Admir Glamočak'),
(5, 'Aleksandar Seksan'),
(6, 'Mirsad Tuka'),
(7, 'Gordana Boban'),
(8, 'Muhamed Hadžović'),
(9, 'Davor Golubović'),
(10, 'Moamer Kasumović'),
(11, 'Ermin Bravo'),
(12, 'Mirjana Karanović'),
(13, 'Sanin Milavić'),
(14, 'Senad Alihodžić'),
(15, 'Dragan Jovičić'),
(16, 'Boris Ler'),
(17, 'Feđa Štukan'),
(18, 'Dženita Imamović Omerović'),
(19, 'Dubravka Drakić'),
(20, 'Dušan Kovačević'),
(21, 'Amar Selimović'),
(22, 'Alen Muratović'),
(23, 'Sunčica Milanović'),
(24, 'Ivana Roščić'),
(25, 'Nenad Tomić'),
(26, 'Ilina Corevska'),
(28, 'Jasna Ornela Bery');

-- --------------------------------------------------------

--
-- Table structure for table `Directors`
--

CREATE TABLE `Directors` (
  `director_id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Directors`
--

INSERT INTO `Directors` (`director_id`, `first_name`) VALUES
(1, 'Selma Spahic'),
(2, 'Elmir Jukic'),
(3, 'Admir Glamocak'),
(4, 'Kokan Mladenovic'),
(5, 'Saša Pevševski'),
(6, 'Sabrina Begovic'),
(7, 'Dino Mustafic'),
(8, 'Sr?an Vuletic'),
(9, 'Francois Lunel'),
(10, 'Emir Zumbul Kapetanovic'),
(11, 'Pjer Žalica'),
(12, 'Irfan Avdic'),
(13, 'Branislav Micunovic'),
(14, 'Jasmin Novljakovic'),
(15, 'Miralem Zupcevic'),
(16, 'Vasil Hristov');

-- --------------------------------------------------------

--
-- Table structure for table `Plays`
--

CREATE TABLE `Plays` (
  `play_id` int(11) NOT NULL,
  `play_name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `genre` varchar(45) CHARACTER SET latin1 NOT NULL,
  `date` datetime NOT NULL,
  `price` int(11) NOT NULL,
  `Description` varchar(512) CHARACTER SET latin1 DEFAULT NULL,
  `dir_id` int(11) DEFAULT NULL,
  `Writer_id` int(11) DEFAULT NULL,
  `maxcap` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COLLATE=latin2_bin;

--
-- Dumping data for table `Plays`
--

INSERT INTO `Plays` (`play_id`, `play_name`, `genre`, `date`, `price`, `Description`, `dir_id`, `Writer_id`, `maxcap`) VALUES
(1, 'Umri muški', 'comedy', '2022-12-22 00:00:00', 30, '', 3, 8, 0),
(2, 'Sjecaš li se Doli Bel', 'drama', '2022-10-24 00:00:00', 30, '', 4, 1, 0),
(3, 'Žaba', 'drama', '2022-10-26 20:00:00', 25, '', 2, 6, 233),
(4, 'Ljubicasto', 'drama', '2022-12-12 20:00:00', 10, '', 1, 14, 90),
(5, 'Mirna Bosna', 'comedy', '2022-12-03 00:00:00', 15, '', 5, 15, 0),
(6, 'Schindlerov lift', 'drama', '2022-12-27 20:00:00', 15, '', 4, 13, 98),
(7, 'Pozorište za pocetnike', 'drama', '2022-10-29 20:00:00', 10, '', 3, 12, 120),
(8, 'Ih,kako bih te ja', 'comedy', '2022-12-15 00:00:00', 15, '', 12, 19, 0),
(9, 'Bez potrfelja', 'comedy', '2022-12-16 20:00:00', 25, '', 13, 18, 150),
(10, 'Naš razred', 'drama', '2022-12-17 20:00:00', 25, '', 14, 2, 200),
(11, 'Djelidba', 'comedy', '2022-12-22 20:00:00', 10, '', 15, 16, 140),
(12, 'Slavna Florens', 'drama', '2023-02-12 20:00:00', 25, '', 3, 11, 65),
(13, 'Tri sestre', 'drama', '2021-09-06 20:00:00', 20, '', 11, 10, 90),
(14, 'Hadersfild', 'drama', '2023-07-20 00:00:00', 10, '', 10, 9, 219),
(15, 'Helverova noc', 'drama', '2022-04-14 20:00:00', 10, '', 7, 7, 87),
(16, 'Radnja na uglu', 'comedy', '2023-01-23 20:00:00', 15, '', 9, 20, 55),
(17, 'Kad bi naglas govorili', 'drama', '2024-03-13 20:00:00', 10, '', 8, 5, 95),
(18, 'Knjiga mojih života', 'drama', '2022-11-13 20:00:00', 20, '', 6, 3, 105);

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `Location` varchar(45) DEFAULT NULL,
  `Management` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`user_id`, `username`, `first_name`, `last_name`, `date_of_birth`, `password`, `gender`, `Location`, `Management`) VALUES
(1, 'aherak2', 'Adna', 'Herak', '2001-12-22', 'a123', 'F', 'Sarajevo', 1),
(3, 'azunic3', 'Azra', 'Zunic', '2002-01-25', 'Grdonj24', 'F', 'Sarajevo', 0),
(4, 'meraaaa', 'Merjem', 'Becirovic', NULL, 'mera', 'F', 'Sarajevo', 0);

-- --------------------------------------------------------

--
-- Table structure for table `Writers`
--

CREATE TABLE `Writers` (
  `writer_id` int(11) NOT NULL,
  `FirstName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Writers`
--

INSERT INTO `Writers` (`writer_id`, `FirstName`) VALUES
(1, 'Abdulah Sidran'),
(2, 'Tadeuš Slobodjanek'),
(3, 'Aleksandar Hemon'),
(5, 'Adnan Lugonic'),
(6, 'Dubravko Mihanovic'),
(7, 'Ingmar Vilkvist'),
(8, 'Aldo Nikolaj'),
(9, 'Uglješa Šajtinac'),
(10, 'Anton Pavlovic Cehov'),
(11, 'Peter Quilter'),
(12, 'Vedran Fajkovic'),
(13, 'Darko Cvijetic'),
(14, 'Selma Spahic'),
(15, 'Boris Lalic'),
(16, 'Skender Kulenovic'),
(17, 'Lars Noren'),
(18, 'Stevan Koprivica'),
(19, 'Vuk Marinic'),
(20, 'L. Miklos');

-- --------------------------------------------------------

--
-- Table structure for table `plays_ins`
--

CREATE TABLE `plays_ins` (
  `plays_in_id` int(11) NOT NULL,
  `Artist_id` int(11) DEFAULT NULL,
  `Play_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plays_ins`
--

INSERT INTO `plays_ins` (`plays_in_id`, `Artist_id`, `Play_id`) VALUES
(1, 2, 1),
(2, 4, 1),
(4, 17, 5),
(5, 21, 8),
(6, 2, 4),
(7, 9, 4),
(8, 16, 4),
(9, 28, 8),
(10, 19, 9),
(11, 20, 9),
(12, 7, 10),
(13, 2, 11),
(14, 2, 6),
(15, 7, 6),
(16, 4, 6),
(17, 8, 6),
(18, 5, 6),
(19, 2, 2),
(24, 8, 10),
(25, 2, 10),
(26, 21, 10),
(27, 16, 10),
(28, 16, 18),
(29, 21, 18),
(30, 7, 5),
(31, 9, 5),
(32, 8, 17),
(33, 16, 17),
(34, 8, 16),
(35, 15, 16),
(36, 21, 16),
(37, 16, 16),
(38, 1, 3),
(39, 6, 3),
(40, 2, 9),
(41, 2, 7),
(42, 8, 7),
(43, 11, 10),
(44, 7, 11),
(45, 9, 11),
(46, 21, 11),
(47, 18, 12),
(48, 2, 13),
(49, 15, 13),
(50, 6, 13),
(51, 7, 13),
(52, 8, 13),
(53, 16, 13),
(54, 17, 13),
(55, 8, 14),
(56, 4, 14),
(57, 13, 14),
(58, 14, 14),
(59, 11, 15),
(60, 12, 15),
(61, 14, 16),
(65, 15, 17),
(66, 28, 17),
(67, 17, 17),
(69, 7, 2),
(70, 6, 2),
(71, 9, 2),
(72, 8, 2),
(73, 4, 2),
(74, 21, 2),
(75, 5, 3),
(76, 10, 3),
(92, 2, 18),
(107, 3, 2),
(108, 3, 1),
(109, 1, 2),
(110, 3, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Artists`
--
ALTER TABLE `Artists`
  ADD PRIMARY KEY (`artist_id`),
  ADD UNIQUE KEY `artist_id_UNIQUE` (`artist_id`);

--
-- Indexes for table `Directors`
--
ALTER TABLE `Directors`
  ADD PRIMARY KEY (`director_id`),
  ADD UNIQUE KEY `director_id_UNIQUE` (`director_id`);

--
-- Indexes for table `Plays`
--
ALTER TABLE `Plays`
  ADD PRIMARY KEY (`play_id`),
  ADD UNIQUE KEY `album_id_UNIQUE` (`play_id`),
  ADD KEY `fk_dir_id_idx` (`dir_id`),
  ADD KEY `Writer_id_idx` (`Writer_id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`);

--
-- Indexes for table `Writers`
--
ALTER TABLE `Writers`
  ADD PRIMARY KEY (`writer_id`),
  ADD UNIQUE KEY `Writer_id_UNIQUE` (`writer_id`);

--
-- Indexes for table `plays_ins`
--
ALTER TABLE `plays_ins`
  ADD PRIMARY KEY (`plays_in_id`),
  ADD KEY `Artist_id_idx` (`Artist_id`),
  ADD KEY `Play_id_idx` (`Play_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Artists`
--
ALTER TABLE `Artists`
  MODIFY `artist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `Directors`
--
ALTER TABLE `Directors`
  MODIFY `director_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `Plays`
--
ALTER TABLE `Plays`
  MODIFY `play_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;
--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `Writers`
--
ALTER TABLE `Writers`
  MODIFY `writer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `plays_ins`
--
ALTER TABLE `plays_ins`
  MODIFY `plays_in_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Plays`
--
ALTER TABLE `Plays`
  ADD CONSTRAINT `fk_dir_id` FOREIGN KEY (`dir_id`) REFERENCES `Directors` (`director_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Writer_id` FOREIGN KEY (`Writer_id`) REFERENCES `Writers` (`Writer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `plays_ins`
--
ALTER TABLE `plays_ins`
  ADD CONSTRAINT `Artist_id` FOREIGN KEY (`Artist_id`) REFERENCES `Artists` (`artist_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Play_id` FOREIGN KEY (`Play_id`) REFERENCES `Plays` (`play_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

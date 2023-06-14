-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: 34.101.170.151
-- Generation Time: 10 Jun 2023 pada 06.50
-- Versi Server: 8.0.26-google
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
-- Database: `db-stunting`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `stuntings`
--

CREATE TABLE `stuntings` (
  `id` int NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `gender` enum('L','P') COLLATE utf8mb4_general_ci NOT NULL,
  `age` decimal(5,2) NOT NULL,
  `height` decimal(5,2) NOT NULL,
  `weight` decimal(5,2) NOT NULL,
  `status` enum('Stunting','Tidak Stunting') COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `stuntings`
--

INSERT INTO `stuntings` (`id`, `name`, `gender`, `age`, `height`, `weight`, `status`) VALUES
(3, 'FAISAL FAKIH RUKMANA', 'L', '1.00', '4.90', '58.00', 'Tidak Stunting'),
(4, 'MUHAMMAD HAFIDZ ATHAFARIZ S', 'L', '1.00', '17.00', '109.00', 'Tidak Stunting'),
(5, 'VIONA FEBBY YASMIN', 'P', '1.00', '7.10', '68.50', 'Tidak Stunting'),
(6, 'Aulia Kezya', 'P', '1.00', '5.70', '72.00', 'Stunting');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stuntings`
--
ALTER TABLE `stuntings`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stuntings`
--
ALTER TABLE `stuntings`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

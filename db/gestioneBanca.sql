-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Ott 21, 2024 alle 15:15
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestioneBanca`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `conto`
--

CREATE TABLE `conto` (
  `IBAN` varchar(20) NOT NULL,
  `intestatario` varchar(100) NOT NULL,
  `saldo` decimal(10,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `conto`
--

INSERT INTO `conto` (`IBAN`, `intestatario`, `saldo`) VALUES
('IBAN-60EFB696', 'Roberto', 1230.00),
('IBAN-9FFFE1AD', 'Simone', 1000.00),
('IBAN-A39B5299', 'Mahima', 0.00),
('IBAN-D876BAE8', 'Kevin', 50000.00),
('IT434B6EA8', 'Luca', 0.00),
('IT841D5ED4', 'Roberto', 0.00);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `conto`
--
ALTER TABLE `conto`
  ADD PRIMARY KEY (`IBAN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

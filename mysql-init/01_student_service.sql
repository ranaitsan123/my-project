-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- Base de données : `student_service`
-- Table `student`

-- 01_student_service.sql
CREATE DATABASE IF NOT EXISTS student_service;
USE student_service;

-- Your table definitions and inserts from the dump...

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Déchargement des données de la table `student`
INSERT INTO `student` (`id`, `email`, `first_name`, `last_name`) VALUES
(6, 'ahmet@gmail.com', 'seivv', 'sava'),
(9, 'aya@hotmail.com', 'aya', 'bahmane'),
(10, 'sas@gmail.com', 's', 'savan'),
(12, 'sas@gmail.com', 'savann', 'savan'),
(13, 'sas@gmail.com', 's', 's'),
(14, 'sas@gmail.com', 's', 'savan');

-- AUTO_INCREMENT pour la table `student`
ALTER TABLE `student`
  AUTO_INCREMENT=16;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

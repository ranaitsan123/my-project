-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 18 jan. 2026 à 15:47
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `formation_service_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `max_students` int(11) DEFAULT NULL,
  `prerequisites` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id`, `name`, `max_students`, `prerequisites`) VALUES
(2, 'isit', 80, 'avoir valide tous le semestree'),
(3, 'isi', 60, 'avoir valide le semestre '),
(4, 'isi', 69, 'avoir valide le semestre '),
(5, 'eee', 3, ''),
(6, 'master isi', 60, 'avoir valide le'),
(8, 'reseaux', 2, 'avoir une moyenne superieur a 10'),
(9, 'nb j', 1, 'NHB JB'),
(10, 'isi', 69, ''),
(11, 'p', 3, 'bn'),
(12, 'isi', 4, ''),
(13, 'isi', 4, 'k'),
(14, 'isi', 4, 'b'),
(15, 'dddddddd', 11, 'SSSSSSSSS'),
(16, 'isit', 80, 'n'),
(17, 'isi', 1, 'NH'),
(18, 'isi', 1, 'h'),
(19, 'isit', 3, 'j'),
(20, 'tt', 7, ''),
(21, 'isi', 9, ''),
(23, 'isi', 2, 'nk,n');

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE `module` (
  `id` bigint(20) NOT NULL,
  `max_students` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `prerequisites` varchar(255) DEFAULT NULL,
  `formation_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `module`
--

INSERT INTO `module` (`id`, `max_students`, `name`, `prerequisites`, `formation_id`) VALUES
(1, 50, 'devellopement web', 'avoir validé', 2),
(2, 8, 'devellopement web', 'avoir validé', 3),
(5, 0, 'spring', NULL, 2),
(6, 0, 'cloud', NULL, 4),
(7, 0, 'big data', NULL, 4),
(8, 0, 'science de donne', NULL, 4),
(9, 0, 'ang', NULL, 4),
(10, 0, 'fr', NULL, 4),
(11, 0, 'sdn', NULL, NULL),
(12, 0, 'spring', NULL, 8),
(13, 0, 'jss', NULL, NULL),
(14, 0, 'jssss', NULL, 9),
(15, 0, 'spring', NULL, 10),
(16, 0, 'spring', NULL, 10),
(17, 0, 'spring', NULL, 11),
(18, 0, 'bb', NULL, NULL),
(19, 0, 'spring', NULL, 13),
(20, 0, 'spring', NULL, 12),
(21, 0, 'spring', NULL, NULL),
(22, 0, 'cloud', NULL, 14),
(23, 0, 'spring', NULL, 14),
(24, 0, 'spring', NULL, 15),
(25, 0, 'spring', NULL, 15),
(27, 0, 'spring', NULL, 16),
(28, 0, 'spring', NULL, 17),
(29, 0, 'spring', NULL, 18),
(30, 0, 'yy', NULL, 18),
(31, 0, 'yyy', NULL, 18),
(32, 0, 'clouud', NULL, 19),
(33, 0, 'cloud', NULL, 20),
(36, 0, 'TPPP', NULL, 21);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgsk5i1p8v7nunsrx7vr0fs1tg` (`formation_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `module`
--
ALTER TABLE `module`
  ADD CONSTRAINT `FKgsk5i1p8v7nunsrx7vr0fs1tg` FOREIGN KEY (`formation_id`) REFERENCES `formation` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

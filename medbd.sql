-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 04 mars 2025 à 23:24
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `medbd`
--

-- --------------------------------------------------------

--
-- Structure de la table `alerte`
--

CREATE TABLE `alerte` (
  `id_alerte` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `id_medecin` int(11) NOT NULL,
  `date_heure` datetime NOT NULL,
  `message` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `donneesmedicales`
--

CREATE TABLE `donneesmedicales` (
  `id_donnee` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `date_heure` datetime NOT NULL,
  `tension` decimal(5,2) DEFAULT NULL,
  `temperature` decimal(5,2) DEFAULT NULL,
  `frequence_cardiaque` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `donneesmedicales`
--

INSERT INTO `donneesmedicales` (`id_donnee`, `id_patient`, `date_heure`, `tension`, `temperature`, `frequence_cardiaque`) VALUES
(1, 1, '2025-02-28 14:56:26', 40.00, 60.00, 30),
(2, 1, '2025-02-28 17:21:31', 30.00, 30.00, 100),
(3, 1, '2025-02-28 17:21:31', 30.00, 40.00, 70),
(32, 1, '2025-02-28 16:44:26', 2.00, 2.00, 2),
(33, 1, '2025-02-28 16:54:33', 20.00, 1.00, 30),
(54, 1, '2024-02-28 08:30:00', 120.50, 36.80, 75),
(65, 3, '2025-02-28 17:57:08', 30.00, 30.00, 20),
(66, 3, '2025-02-28 17:57:08', 10.00, 20.00, 50),
(67, 3, '2025-02-28 17:57:08', 56.00, 36.00, 200),
(68, 3, '2025-02-28 17:57:08', 20.00, 30.00, 120),
(71, 3, '2025-02-28 19:15:08', 20.00, 30.00, 100),
(72, 3, '2025-02-28 19:15:19', 1.00, 1.00, 1),
(73, 3, '2025-02-28 19:40:00', 20.00, 1.00, 1),
(74, 1, '2025-02-28 22:33:04', 10.00, 1.00, 0),
(75, 14, '2025-02-28 22:36:09', 100.00, 37.00, 70),
(76, 14, '2025-02-28 22:39:42', 30.00, 20.00, 110),
(77, 14, '2025-03-01 01:26:45', 20.00, 40.00, 20),
(78, 14, '2025-03-01 01:37:31', 12.00, 4.00, 6),
(79, 14, '2025-03-01 02:32:45', 20.00, 30.00, 200),
(80, 14, '2025-03-01 02:47:35', 2.00, 49.00, 39),
(81, 15, '2025-03-01 04:03:32', 10.00, 30.00, 40),
(82, 14, '2025-03-01 08:41:51', 20.00, 30.00, 40),
(83, 14, '2025-03-01 09:50:53', 30.00, 30.00, 30),
(84, 14, '2025-03-01 10:26:08', 30.00, 30.00, 30),
(85, 14, '2025-03-03 15:49:15', 2.00, 3.00, 4),
(86, 14, '2025-03-03 15:55:49', 1.00, 2.00, 1),
(87, 14, '2025-03-03 16:56:12', 1.00, 1.00, 1),
(88, 14, '2025-03-03 17:03:31', 20.00, 1.00, 200),
(89, 14, '2025-03-03 17:16:51', 1.00, 4.00, 1),
(90, 14, '2025-03-03 17:20:10', 200.00, 100.00, 300),
(91, 14, '2025-03-03 18:06:36', 111.00, 200.00, 200),
(92, 14, '2025-03-03 23:01:08', 20.00, 30.00, 100),
(93, 14, '2025-03-03 23:01:44', 1.00, 10.00, 1000),
(94, 14, '2025-03-04 21:28:45', 200.00, 200.00, 200);

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE `medecin` (
  `id_medecin` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `specialite` varchar(100) NOT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) NOT NULL DEFAULT '',
  `loginm` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`id_medecin`, `nom`, `prenom`, `specialite`, `telephone`, `email`, `password`, `loginm`) VALUES
(1, 'sebbar', 'imane', 'cordiologist', '0666666666', 'imanesebb@icloud.com', 'imane', 'imanesebbar'),
(2, 'Dupont', 'Jacques', 'Cardiologie', '0712345678', 'jacques.dupont@email.com', 'medpass123', 'jacquesd'),
(3, 'Bernard', 'Marie', 'Dermatologie', '0723456789', 'marie.bernard@email.com', 'medpass456', 'marieb'),
(4, 'Thomas', 'Alain', 'Neurologie', '0734567890', 'alain.thomas@email.com', 'medpass789', 'alaint'),
(5, 'Robert', 'Catherine', 'Pédiatrie', '0745678901', 'catherine.robert@email.com', 'medpass101', 'catheriner'),
(6, 'Petit', 'Lucien', 'Gynécologie', '0756789012', 'lucien.petit@email.com', 'medpass202', 'lucienp'),
(7, 'Richard', 'Sophie', 'Orthopédie', '0767890123', 'sophie.richard@email.com', 'medpass303', 'sophier'),
(8, 'Durand', 'Pierre', 'Ophtalmologie', '0778901234', 'pierre.durand@email.com', 'medpass404', 'pierred'),
(9, 'Morel', 'Julie', 'Oncologie', '0789012345', 'julie.morel@email.com', 'medpass505', 'juliem'),
(10, 'Simon', 'Antoine', 'Psychiatrie', '0790123456', 'antoine.simon@email.com', 'medpass606', 'antoines'),
(11, 'Michel', 'Elisabeth', 'Endocrinologie', '0701234567', 'elisabeth.michel@email.com', 'medpass707', 'elisabethm');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `id_patient` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `date_naissance` date NOT NULL,
  `sexe` enum('M','F') NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `loginp` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id_patient`, `nom`, `prenom`, `date_naissance`, `sexe`, `adresse`, `telephone`, `email`, `loginp`, `password`) VALUES
(1, 'akrach', 'nada', '2025-02-05', 'F', 'kenitra', '0000000000', 'nadaak@gmail.com', 'nada', 'nada'),
(3, 'hind', 'hind', '2000-03-01', 'F', 'kenitra', '0123459', 'hinda@gmail.com', 'hind', 'hind'),
(4, 'Durand', 'Jean', '1985-06-12', 'M', '12 Rue Lafayette, Paris', '0612345678', 'jean.durand@email.com', 'jeand', 'pass123'),
(5, 'Martin', 'Claire', '1990-09-24', 'F', '45 Boulevard Haussmann, Paris', '0623456789', 'claire.martin@email.com', 'clairem', 'pass456'),
(6, 'Leroy', 'Luc', '1978-03-15', 'M', '78 Avenue Foch, Lyon', '0634567890', 'luc.leroy@email.com', 'lucl', 'pass789'),
(7, 'Dubois', 'Sophie', '1982-07-30', 'F', '23 Place Bellecour, Lyon', '0645678901', 'sophie.dubois@email.com', 'sophied', 'pass101'),
(8, 'Moreau', 'Paul', '1995-05-10', 'M', '5 Rue Saint-Honoré, Marseille', '0656789012', 'paul.moreau@email.com', 'paulm', 'pass202'),
(9, 'Fournier', 'Emma', '1989-11-21', 'F', '36 Quai des Chartrons, Bordeaux', '0667890123', 'emma.fournier@email.com', 'emmaf', 'pass303'),
(10, 'Girard', 'Nicolas', '1975-04-08', 'M', '10 Place du Capitole, Toulouse', '0678901234', 'nicolas.girard@email.com', 'nicolasg', 'pass404'),
(11, 'Bertrand', 'Julie', '1993-12-19', 'F', '14 Rue de la République, Nice', '0689012345', 'julie.bertrand@email.com', 'julieb', 'pass505'),
(12, 'Lambert', 'Antoine', '1980-01-05', 'M', '9 Avenue Jean Jaurès, Nantes', '0690123456', 'antoine.lambert@email.com', 'antoinel', 'pass606'),
(13, 'Rousseau', 'Elise', '1992-08-14', 'F', '17 Rue de la Gare, Lille', '0601234567', 'elise.rousseau@email.com', 'eliser', 'pass707'),
(14, 'sebbar', 'anas', '2010-05-20', 'M', 'rabat', '0987654321', 'anas@gmail.com', 'anas', 'anas'),
(15, 'lasheen', 'layla', '2000-11-11', 'F', 'casablanca', '0654321789', 'layla@gmail.com', 'layla', 'layla'),
(16, 'sisi', 'vivi', '2000-02-07', 'M', 'egypt', '234567890', 'imane@gmail.com', 'sisi', 'sisi');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `alerte`
--
ALTER TABLE `alerte`
  ADD PRIMARY KEY (`id_alerte`),
  ADD KEY `id_patient` (`id_patient`),
  ADD KEY `id_medecin` (`id_medecin`);

--
-- Index pour la table `donneesmedicales`
--
ALTER TABLE `donneesmedicales`
  ADD PRIMARY KEY (`id_donnee`),
  ADD KEY `id_patient` (`id_patient`);

--
-- Index pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`id_medecin`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id_patient`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `alerte`
--
ALTER TABLE `alerte`
  MODIFY `id_alerte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `donneesmedicales`
--
ALTER TABLE `donneesmedicales`
  MODIFY `id_donnee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT pour la table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `id_medecin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id_patient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `alerte`
--
ALTER TABLE `alerte`
  ADD CONSTRAINT `alerte_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`),
  ADD CONSTRAINT `alerte_ibfk_2` FOREIGN KEY (`id_medecin`) REFERENCES `medecin` (`id_medecin`);

--
-- Contraintes pour la table `donneesmedicales`
--
ALTER TABLE `donneesmedicales`
  ADD CONSTRAINT `donneesmedicales_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

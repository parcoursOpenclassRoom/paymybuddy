SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
--
-- Base de données : `paymybuddy`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

CREATE TABLE `account` (
  `id` int NOT NULL,
  `balance` double NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `default_account` bit(1) NOT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `user_id` int NOT NULL
) 

--
-- Déchargement des données de la table `account`
--

INSERT INTO `account` (`id`, `balance`, `created`, `default_account`, `modified`, `user_id`) VALUES
(3, 4900, '2020-08-01 10:45:25.377000', b'1', '2020-08-01 10:45:25.599000', 1),
(7, 300, '2020-08-01 10:45:25.391000', b'1', '2020-08-01 10:45:25.611000', 5),
(11, 0, '2020-08-01 10:45:25.402000', b'1', '2020-08-01 10:45:25.402000', 9),
(15, 500, '2020-08-01 10:45:25.413000', b'1', '2020-08-01 10:45:25.413000', 13),
(19, 8500, '2020-08-01 10:45:25.423000', b'1', '2020-08-01 10:45:25.423000', 17);

-- --------------------------------------------------------

--
-- Structure de la table `connection`
--

CREATE TABLE `connection` (
  `id` int NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `receiver_id` int NOT NULL,
  `sender_id` int NOT NULL
) 

--
-- Déchargement des données de la table `connection`
--

INSERT INTO `connection` (`id`, `created`, `modified`, `status`, `receiver_id`, `sender_id`) VALUES
(21, '2020-08-01 10:45:25.553000', '2020-08-01 10:45:25.553000', b'1', 5, 1),
(22, '2020-08-01 10:45:25.558000', '2020-08-01 10:45:25.558000', b'1', 9, 1),
(23, '2020-08-01 10:45:25.563000', '2020-08-01 10:45:25.563000', b'1', 13, 1),
(24, '2020-08-01 10:45:25.569000', '2020-08-01 10:45:25.569000', b'1', 17, 1);

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

CREATE TABLE `profil` (
  `id` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
);

--
-- Déchargement des données de la table `profil`
--

INSERT INTO `profil` (`id`, `first_name`, `last_name`) VALUES
(2, 'Aristide', 'BATIONO'),
(6, 'Eugene', 'TOTO'),
(10, 'Laurent', 'Bernard'),
(14, 'Fabrice', 'Robert'),
(18, 'Audrey', 'Fontaine');

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `id` int NOT NULL,
  `amount` double NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `account_id` int NOT NULL
) ;

--
-- Déchargement des données de la table `transaction`
--

INSERT INTO `transaction` (`id`, `amount`, `created`, `description`, `modified`, `type`, `account_id`) VALUES
(4, 5000, '2020-08-01 10:45:25.378000', 'Dépot creation de compte', '2020-08-01 10:45:25.378000', 'credit', 3),
(8, 200, '2020-08-01 10:45:25.392000', 'Dépot creation de compte', '2020-08-01 10:45:25.392000', 'credit', 7),
(12, 0, '2020-08-01 10:45:25.403000', 'Dépot creation de compte', '2020-08-01 10:45:25.403000', 'credit', 11),
(16, 500, '2020-08-01 10:45:25.413000', 'Dépot creation de compte', '2020-08-01 10:45:25.413000', 'credit', 15),
(20, 8500, '2020-08-01 10:45:25.423000', 'Dépot creation de compte', '2020-08-01 10:45:25.423000', 'credit', 19),
(26, 100, '2020-08-01 10:45:25.598000', 'Transfert de fond (Movies Ticket)', '2020-08-01 10:45:25.598000', 'debit', 3),
(27, 100, '2020-08-01 10:45:25.609000', 'Réception de fond (Movies Ticket)', '2020-08-01 10:45:25.609000', 'credit', 7);

-- --------------------------------------------------------

--
-- Structure de la table `transfer`
--

CREATE TABLE `transfer` (
  `id` int NOT NULL,
  `amount` int NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `receiver_id` int NOT NULL,
  `sender_id` int NOT NULL
) 

--
-- Déchargement des données de la table `transfer`
--

INSERT INTO `transfer` (`id`, `amount`, `created`, `description`, `modified`, `status`, `receiver_id`, `sender_id`) VALUES
(25, 100, '2020-08-01 10:45:25.578000', 'Movies Ticket', '2020-08-01 10:45:25.578000', b'1', 7, 3);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_account_non_expired` bit(1) DEFAULT NULL,
  `is_account_non_locked` bit(1) DEFAULT NULL,
  `is_credentials_non_expired` bit(1) DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profil_id` int DEFAULT NULL
) 

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `created`, `email`, `is_account_non_expired`, `is_account_non_locked`, `is_credentials_non_expired`, `is_enabled`, `modified`, `password`, `profil_id`) VALUES
(1, '2020-08-01 10:45:25.374000', 'aristide.laurent@gmail.com', b'0', b'0', b'0', b'1', '2020-08-01 10:45:25.374000', '123456', 2),
(5, '2020-08-01 10:45:25.391000', 'toto@gmail.com', b'0', b'0', b'0', b'1', '2020-08-01 10:45:25.391000', '123456', 6),
(9, '2020-08-01 10:45:25.401000', 'bernard@gmail.com', b'0', b'0', b'0', b'1', '2020-08-01 10:45:25.401000', '123456', 10),
(13, '2020-08-01 10:45:25.412000', 'robert@gmail.com', b'0', b'0', b'0', b'1', '2020-08-01 10:45:25.412000', '123456', 14),
(17, '2020-08-01 10:45:25.422000', 'fontaine@gmail.com', b'0', b'0', b'0', b'1', '2020-08-01 10:45:25.422000', '123456', 18);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKra7xoi9wtlcq07tmoxxe5jrh4` (`user_id`);

--
-- Index pour la table `connection`
--
ALTER TABLE `connection`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6atqv6b0v0aerom83367bkxmd` (`receiver_id`),
  ADD KEY `FK82codnlq7vlqmi9o9a4inpav3` (`sender_id`);

--
-- Index pour la table `profil`
--
ALTER TABLE `profil`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6g20fcr3bhr6bihgy24rq1r1b` (`account_id`);

--
-- Index pour la table `transfer`
--
ALTER TABLE `transfer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7vy3banmwqotrq0v8kcdtn989` (`receiver_id`),
  ADD KEY `FKdwj52dnnulpcd39ywmnc3gnsb` (`sender_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD KEY `FK9560mhp1koi8ld4rajv94r5d0` (`profil_id`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FKra7xoi9wtlcq07tmoxxe5jrh4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `connection`
--
ALTER TABLE `connection`
  ADD CONSTRAINT `FK6atqv6b0v0aerom83367bkxmd` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK82codnlq7vlqmi9o9a4inpav3` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK6g20fcr3bhr6bihgy24rq1r1b` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Contraintes pour la table `transfer`
--
ALTER TABLE `transfer`
  ADD CONSTRAINT `FK7vy3banmwqotrq0v8kcdtn989` FOREIGN KEY (`receiver_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FKdwj52dnnulpcd39ywmnc3gnsb` FOREIGN KEY (`sender_id`) REFERENCES `account` (`id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK9560mhp1koi8ld4rajv94r5d0` FOREIGN KEY (`profil_id`) REFERENCES `profil` (`id`);
COMMIT;


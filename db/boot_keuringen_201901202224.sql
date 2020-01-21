-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Gegenereerd op: 21 jan 2020 om 02:23
-- Serverversie: 10.4.11-MariaDB
-- PHP-versie: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `boot_keuringen`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `boot_eigenschap`
--

CREATE TABLE `boot_eigenschap` (
  `boot_id` bigint(20) NOT NULL,
  `eigenschap_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `boten`
--

CREATE TABLE `boten` (
  `id` bigint(20) NOT NULL,
  `bootNaam` varchar(255) NOT NULL,
  `bouwjaar` varchar(255) NOT NULL,
  `brandstof` varchar(255) DEFAULT NULL,
  `breedte` varchar(255) DEFAULT NULL,
  `kleur` varchar(255) DEFAULT NULL,
  `lengte` varchar(255) DEFAULT NULL,
  `mast` varchar(255) DEFAULT NULL,
  `motorMerk` varchar(255) DEFAULT NULL,
  `shipCode` varchar(255) NOT NULL,
  `spanten` varchar(255) DEFAULT NULL,
  `eigenaar_id` bigint(20) NOT NULL,
  `type_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `controleurs`
--

CREATE TABLE `controleurs` (
  `id` bigint(20) NOT NULL,
  `gebruikersNaam` varchar(255) DEFAULT NULL,
  `naam` varchar(255) NOT NULL,
  `voorNaam` varchar(255) NOT NULL,
  `wachtwoord` varchar(255) DEFAULT NULL,
  `nummer_id` bigint(20) DEFAULT NULL,
  `rank_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `controleurs`
--

INSERT INTO `controleurs` (`id`, `gebruikersNaam`, `naam`, `voorNaam`, `wachtwoord`, `nummer_id`, `rank_id`) VALUES
(0, 'soedit', 'Oedit', 'Shiva', 'password', 0, 3);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `controleur_nummers`
--

CREATE TABLE `controleur_nummers` (
  `id` bigint(20) NOT NULL,
  `controleurNummer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `controleur_nummers`
--

INSERT INTO `controleur_nummers` (`id`, `controleurNummer`) VALUES
(0, '00000001');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `eigenaren`
--

CREATE TABLE `eigenaren` (
  `id` bigint(20) NOT NULL,
  `geboorteDatum` date NOT NULL,
  `naam` varchar(255) NOT NULL,
  `voorNaam` varchar(255) NOT NULL,
  `paspoort_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `eigenschappen`
--

CREATE TABLE `eigenschappen` (
  `id` bigint(20) NOT NULL,
  `eigenschap` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `eigenschappen`
--

INSERT INTO `eigenschappen` (`id`, `eigenschap`) VALUES
(0, 'Anker'),
(1, 'Zeil'),
(2, 'Bedrijfsvaartuig'),
(3, 'Persoonlijk vaartuig');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `keuringen`
--

CREATE TABLE `keuringen` (
  `id` bigint(20) NOT NULL,
  `keuringsDatum` date DEFAULT NULL,
  `status` int(11) NOT NULL,
  `boot_id` bigint(20) NOT NULL,
  `controleur_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `keuring_bewijzen`
--

CREATE TABLE `keuring_bewijzen` (
  `id` bigint(20) NOT NULL,
  `keuringsDatum` date DEFAULT NULL,
  `vervalDatum` date DEFAULT NULL,
  `boot_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `paspoorten`
--

CREATE TABLE `paspoorten` (
  `id` bigint(20) NOT NULL,
  `landCode` varchar(255) DEFAULT NULL,
  `paspoortNummer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `ranken`
--

CREATE TABLE `ranken` (
  `id` bigint(20) NOT NULL,
  `naam` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `ranken`
--

INSERT INTO `ranken` (`id`, `naam`) VALUES
(0, 'Normale Controleur'),
(1, 'Controleur Met Hogere Rank'),
(2, 'Controleur Met Iets Hogere Rank'),
(3, 'Cotroleur Met Hoogste Rank');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `typen`
--

CREATE TABLE `typen` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `typen`
--

INSERT INTO `typen` (`id`, `type`) VALUES
(0, 'Waterscooter'),
(1, 'Passagiersboot'),
(2, 'Zeevissersvaartuig'),
(3, 'Veerboot'),
(4, 'Jacht'),
(5, 'Zeilboot'),
(6, 'Cruise'),
(7, 'Schip');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `boot_eigenschap`
--
ALTER TABLE `boot_eigenschap`
  ADD KEY `FKfemib2l9tvqbdap2faqwe3lcy` (`eigenschap_id`),
  ADD KEY `FKk4m7slmfw5ilg0boyqiwkpd30` (`boot_id`);

--
-- Indexen voor tabel `boten`
--
ALTER TABLE `boten`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_fqlfcbaihevseqrhx0d0v2txk` (`shipCode`),
  ADD KEY `FKt35xb8s2q5k75sbxb0ahd7n9q` (`eigenaar_id`),
  ADD KEY `FK73qc9rx7u2gkywpn3p4vfuk6b` (`type_id`);

--
-- Indexen voor tabel `controleurs`
--
ALTER TABLE `controleurs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnoxbra6a2q45lqulwipymn4ip` (`nummer_id`),
  ADD KEY `FKoaf2ekvdqd80v4i320wtg4hd5` (`rank_id`);

--
-- Indexen voor tabel `controleur_nummers`
--
ALTER TABLE `controleur_nummers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_eg73x54s1lid0sgmvv04m3fag` (`controleurNummer`);

--
-- Indexen voor tabel `eigenaren`
--
ALTER TABLE `eigenaren`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKr9mm7b3kr99i2s81kkhdxwrj4` (`paspoort_id`);

--
-- Indexen voor tabel `eigenschappen`
--
ALTER TABLE `eigenschappen`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `keuringen`
--
ALTER TABLE `keuringen`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmwyd8n43rbt9sumvryjvpe3bg` (`boot_id`),
  ADD KEY `FKfsqspjgjli8n8rh3tx80d4h9n` (`controleur_id`);

--
-- Indexen voor tabel `keuring_bewijzen`
--
ALTER TABLE `keuring_bewijzen`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqv05r2qj4ces7fbn7hus2bcy3` (`boot_id`);

--
-- Indexen voor tabel `paspoorten`
--
ALTER TABLE `paspoorten`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bto3ssauo53dy8draf0j5qrqc` (`paspoortNummer`);

--
-- Indexen voor tabel `ranken`
--
ALTER TABLE `ranken`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `typen`
--
ALTER TABLE `typen`
  ADD PRIMARY KEY (`id`);

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `boot_eigenschap`
--
ALTER TABLE `boot_eigenschap`
  ADD CONSTRAINT `FKfemib2l9tvqbdap2faqwe3lcy` FOREIGN KEY (`eigenschap_id`) REFERENCES `eigenschappen` (`id`),
  ADD CONSTRAINT `FKk4m7slmfw5ilg0boyqiwkpd30` FOREIGN KEY (`boot_id`) REFERENCES `boten` (`id`);

--
-- Beperkingen voor tabel `boten`
--
ALTER TABLE `boten`
  ADD CONSTRAINT `FK73qc9rx7u2gkywpn3p4vfuk6b` FOREIGN KEY (`type_id`) REFERENCES `typen` (`id`),
  ADD CONSTRAINT `FKt35xb8s2q5k75sbxb0ahd7n9q` FOREIGN KEY (`eigenaar_id`) REFERENCES `eigenaren` (`id`);

--
-- Beperkingen voor tabel `controleurs`
--
ALTER TABLE `controleurs`
  ADD CONSTRAINT `FKnoxbra6a2q45lqulwipymn4ip` FOREIGN KEY (`nummer_id`) REFERENCES `controleur_nummers` (`id`),
  ADD CONSTRAINT `FKoaf2ekvdqd80v4i320wtg4hd5` FOREIGN KEY (`rank_id`) REFERENCES `ranken` (`id`);

--
-- Beperkingen voor tabel `eigenaren`
--
ALTER TABLE `eigenaren`
  ADD CONSTRAINT `FKr9mm7b3kr99i2s81kkhdxwrj4` FOREIGN KEY (`paspoort_id`) REFERENCES `paspoorten` (`id`);

--
-- Beperkingen voor tabel `keuringen`
--
ALTER TABLE `keuringen`
  ADD CONSTRAINT `FKfsqspjgjli8n8rh3tx80d4h9n` FOREIGN KEY (`controleur_id`) REFERENCES `controleurs` (`id`),
  ADD CONSTRAINT `FKmwyd8n43rbt9sumvryjvpe3bg` FOREIGN KEY (`boot_id`) REFERENCES `boten` (`id`);

--
-- Beperkingen voor tabel `keuring_bewijzen`
--
ALTER TABLE `keuring_bewijzen`
  ADD CONSTRAINT `FKqv05r2qj4ces7fbn7hus2bcy3` FOREIGN KEY (`boot_id`) REFERENCES `boten` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

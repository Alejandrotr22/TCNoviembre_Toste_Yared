-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 24, 2022 at 09:40 PM
-- Server version: 10.9.4-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: porra_deportes
--

--
-- Dumping data for table apuestas
--



--
-- Dumping data for table deportes
--

INSERT INTO deportes (id, nombre) VALUES
(1, 'futbol'),
(2, 'tenis'),
(3, 'formula_1');

--
-- Dumping data for table equipos
--

INSERT INTO equipos (id, nombre) VALUES
(1, 'Madrid'),
(2, 'Barca'),
(4, 'Russell'),
(5, 'Hamilton'),
(6, 'Alonso'),
(7, 'Verstappen'),
(8, 'Perez'),
(9, 'Leclerc'),
(10, 'Sainz'),
(11, 'Vettel'),
(12, 'Ocon'),
(13, 'Stroll'),
(14, 'Alcaraz'),
(15, 'Nadal'),
(16, 'Ruud'),
(17, 'Tsitsipas'),
(18, 'Djokovic'),
(19, 'Medvedev'),
(20, 'Fritz'),
(21, 'Zverev'),
(22, 'Manchester_City'),
(23, 'Liverpool'),
(100, 'X');

--
-- Dumping data for table eventos
--

INSERT INTO eventos (id, id_deporte, fecha_inicio, fecha_fin, nombre, id_equipo_ganador, resultado) VALUES
(1, 1, '2022-11-24 21:24:04', '2023-11-30 20:20:52', 'Madrid_Barca', NULL, NULL),
(2, 2, '2022-11-24 21:06:07', '2023-11-30 21:05:34', 'Alcaraz_Nadal', NULL, NULL),
(3, 2, '2022-11-24 21:06:39', '2023-11-30 21:05:34', 'Ruud_Zverev', NULL, NULL),
(4, 1, '2022-11-24 21:07:55', '2023-11-30 21:07:34', 'City_Liverpool', NULL, NULL),
(5, 3, '2022-11-24 21:08:33', '2023-11-30 21:08:01', 'GPbahrein', NULL, NULL),
(6, 3, '2022-11-24 21:08:44', '2023-11-30 21:08:01', 'GPspain', NULL, NULL);

--
-- Dumping data for table participantes
--

INSERT INTO participantes (id_equipo, id_evento) VALUES
(1, 1),
(2, 1),
(4, 5),
(4, 6),
(5, 5),
(5, 6),
(6, 5),
(6, 6),
(7, 5),
(7, 6),
(8, 5),
(8, 6),
(9, 5),
(9, 6),
(10, 5),
(10, 6),
(11, 5),
(11, 6),
(12, 5),
(12, 6),
(13, 5),
(13, 6),
(14, 2),
(15, 2),
(16, 3),
(21, 3),
(22, 4),
(23, 4),
(100, 1),
(100, 4);

--
-- Dumping data for table roles
--

INSERT INTO roles (id, nombre) VALUES
(1, 'Usuario'),
(2, 'Gestor');

--
-- Dumping data for table usuarios
--

INSERT INTO usuarios (id, nombre, email, password, id_rol, saldo) VALUES
(1, 'yared', 'email@gmail.com', '$2a$10$F3QahGpvBNwXg5QfgENJFe1Dp3ykSHxnhiTTP2n/PzJR4d9usPyLy', 1, '56.24'),
(2, 'yared70', 'yared@gmail.com', '$2a$10$LATSj/tPVABgIzQU1K5PberqlUcrDlEw7xI2adwT/Tj24IGYC94U.', 1, '0.00'),
(7, 'yaredphp', 'yaredphp@gmail.com', '$2a$10$CRJO5WSg/kzDZig7VhxBLudwTRrN3XrJ0wdUPbKNyw3Xn2BUFLJO6', 1, '0.00'),
(8, 'admin', 'admin@gmail.com', '$2a$10$.a52wUsNqoV9MnNLX04DY.s3886vuXFGKVtmcnc8sbr3hqCcvxaFu', 2, '0.00');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

INSERT INTO apuestas (id, id_evento, id_usuario, prediccion, cuota, cantidad, estado) VALUES
(9, 1, 1, 'Barca', '1.85', '64.76', 'Realizada'),
(10, 4, 1, 'Manchester_City_3_Liverpool_1', '1.85', '34.00', 'Realizada'),
(11, 2, 1, 'Alcaraz', '1.75', '45.00', 'Realizada');

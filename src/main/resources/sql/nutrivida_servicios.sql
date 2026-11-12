-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-11-2026 a las 00:37:33
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nutrivida_servicios`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `run` varchar(12) NOT NULL,
  `pnombre` varchar(50) NOT NULL,
  `snombre` varchar(50) DEFAULT NULL,
  `apaterno` varchar(50) NOT NULL,
  `amaterno` varchar(50) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `direccion` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `run`, `pnombre`, `snombre`, `apaterno`, `amaterno`, `correo`, `fecha_nacimiento`, `direccion`) VALUES
(1, '11111111-1', 'Ana', 'Maria', 'Gonzalez', 'Soto', 'cliente.prueba.nutrivida@example.com', '2000-05-12', 'Avenida Alemania 500, Temuco'),
(3, '33333333-3', 'Camila', 'Andrea', 'Rojas', 'Muñoz', 'camila.test@example.com', '1999-04-15', 'Temuco, Chile'),
(4, '44444444-4', 'Matias', 'Alejandro', 'Soto', 'Perez', 'matias.test@example.com', '1995-08-22', 'Padre Las Casas, Chile'),
(5, '55555555-5', 'Valentina', 'Paz', 'Morales', 'Diaz', 'valentina.test@example.com', '2001-03-10', 'Temuco, Chile'),
(6, '66666666-6', 'Diego', 'Ignacio', 'Fuentes', 'Vera', 'diego.test@example.com', '1997-11-05', 'Villarrica, Chile');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id`, `nombre`, `descripcion`, `precio`, `imagen_url`, `activo`) VALUES
(2, 'Consulta Nutricional Inicial', 'Evaluacion nutricional personalizada', 20000.00, 'consulta-inicial.jpg', 1),
(3, 'Plan Integral Mensual', 'Evaluación, planificación alimentaria y seguimiento durante un mes.', 35000.00, NULL, 1),
(4, 'Plan Control de Peso', 'Orientación y seguimiento para trabajar objetivos relacionados con el peso.', 29000.00, NULL, 1),
(5, 'Plan Nutrición Deportiva', 'Alimentación adaptada al entrenamiento, rendimiento y recuperación.', 32000.00, NULL, 1),
(6, 'Plan Vegetariano y Vegano', 'Orientación especializada para alimentación vegetariana y vegana.', 30000.00, NULL, 1),
(7, 'Pack de Seguimiento', 'Controles para pacientes que ya cuentan con una evaluación y planificación.', 24000.00, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id` bigint(20) NOT NULL,
  `cliente_id` bigint(20) NOT NULL,
  `servicio_id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL DEFAULT 1,
  `total` decimal(10,2) NOT NULL,
  `fecha_venta` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id`, `cliente_id`, `servicio_id`, `cantidad`, `total`, `fecha_venta`) VALUES
(1, 1, 2, 3, 60000.00, '2026-11-11 20:05:48');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `run` (`run`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

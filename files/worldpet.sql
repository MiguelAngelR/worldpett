-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-06-2018 a las 02:07:01
-- Versión del servidor: 10.1.28-MariaDB
-- Versión de PHP: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `worldpet`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id` int(5) NOT NULL,
  `codigo` varchar(10) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  `cargo` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id`, `codigo`, `nombre`, `pass`, `cargo`) VALUES
(1, '1', 'Miguel Angel Rios Alonso', 'GelanSohir33', 'Administrador'),
(2, '2', 'Alberto Camacho Monryo', 'Alberto1234', 'Vendedor'),
(3, '3', 'Avelina Fragoso Sanches', 'Avelina1234', 'Capturista'),
(4, '4', 'Miguel Angel Rios Alonso', 'abc123', 'Administrador'),
(5, '5', 'Alan ', '123456', 'Capturista'),
(6, 'cv31001969', 'Jorge', '123456', 'Vendedor'),
(7, 'cc31001969', 'Raul', '123456', 'Capturista'),
(8, 'cv31001969', 'Jose', '123456', 'Vendedor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(10) NOT NULL,
  `compra` int(10) DEFAULT NULL,
  `venta` int(10) DEFAULT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `proveedor` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `compra`, `venta`, `cantidad`, `descripcion`, `proveedor`) VALUES
(1, 20, 40, 3, 'Alimento para gatos', NULL),
(3, 30, 50, 3, 'Hueso para perro', NULL),
(4, 12, 25, 30, 'Comida para gatos', NULL),
(5, 20, 50, 10, 'Pelotas para gato', 'Mascota'),
(6, 100, 150, 20, 'Rueda para Hamster', 'Mascota');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
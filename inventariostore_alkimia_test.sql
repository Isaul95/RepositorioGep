-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-04-2020 a las 07:09:38
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inventariostore_alkimia_test`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `nombre` char(40) NOT NULL,
  `nombre_usuario` char(40) NOT NULL,
  `apellido_paterno` char(40) DEFAULT NULL,
  `apellido_materno` char(40) DEFAULT NULL,
  `contraseña` char(40) NOT NULL,
  `email` char(40) DEFAULT NULL,
  `RFC` char(40) DEFAULT NULL,
  `Telefono` char(13) DEFAULT NULL,
  `fecha_y_hora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`id_admin`, `nombre`, `nombre_usuario`, `apellido_paterno`, `apellido_materno`, `contraseña`, `email`, `RFC`, `Telefono`, `fecha_y_hora`) VALUES
(4, 'Alexis', 'Deva', 'Luna', 'Gutierrez', 'Deva', 'alekszyzz@hotmail.com', 'LUGA9512151X9', '733-119-1692', '2019-11-16 05:48:56'),
(5, 'Isaul', 'a', 'Hernandez', 'Ramirez', 'a', 'rihr_952@hotmail.com', 'HERA9509021X9', '733-117-00-55', '2020-04-18 20:27:45'),
(6, 'Jose', 'Admin', 'Pendiente', 'Pendiente', 'Admin2705', 'pendiente@hotmail.com', 'Pendiente', 'Pendiente', '2019-11-11 09:00:36');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apertura`
--

CREATE TABLE `apertura` (
  `id_apertura` int(11) NOT NULL,
  `monto` float DEFAULT NULL,
  `fecha` varchar(100) NOT NULL,
  `hora` varchar(100) NOT NULL,
  `usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `apertura`
--

INSERT INTO `apertura` (`id_apertura`, `monto`, `fecha`, `hora`, `usuario`) VALUES
(1, 1000, '2020/03/05', '01:22:28 ', 4),
(2, 1000, '2020/03/08', '11:30:19 ', 4),
(3, 200, '2020/03/16', '06:02:15 ', 4),
(4, 1000, '2020/03/22', '03:14:43 ', 4),
(5, 100, '2020/04/10', '04:29:11 ', 4),
(6, 1000, '2020/04/11', '00:56:33 ', 4),
(7, 500, '2020/04/18', '03:28:26 ', 4),
(8, 699, '2020/04/19', '00:05:06 ', 4),
(9, 1000, '2020/04/20', '00:42:15 ', 4),
(10, 555, '2020/04/21', '01:01:51 ', 4),
(11, 564, '2020/04/22', '01:14:04 ', 4),
(12, 1000, '2020/04/23', '01:58:46 ', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cortes`
--

CREATE TABLE `cortes` (
  `id_corte` int(11) NOT NULL,
  `id_apertura` int(11) DEFAULT NULL,
  `monto_entregado` float DEFAULT NULL,
  `gastos` float DEFAULT NULL,
  `ventas` float DEFAULT NULL,
  `diferencia` float DEFAULT NULL,
  `fecha` varchar(100) NOT NULL,
  `hora` varchar(100) NOT NULL,
  `usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descripcion_de_venta`
--

CREATE TABLE `descripcion_de_venta` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `nombre_producto` char(40) DEFAULT NULL,
  `cantidad` float DEFAULT NULL,
  `precio_unitario` float DEFAULT NULL,
  `importe` float DEFAULT NULL,
  `resultado` varchar(100) DEFAULT NULL,
  `id_venta` int(11) NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `nombre_credito` varchar(100) DEFAULT NULL,
  `estado` char(20) DEFAULT NULL,
  `fecha` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `descripcion_de_venta`
--

INSERT INTO `descripcion_de_venta` (`id`, `id_producto`, `nombre_producto`, `cantidad`, `precio_unitario`, `importe`, `resultado`, `id_venta`, `id_paciente`, `nombre_credito`, `estado`, `fecha`) VALUES
(3, 23, 'colesterol de muy baja densidad', 1, 120, 120, NULL, 1, 4, NULL, 'Realizada', '2020/04/11'),
(4, 15, 'urea en suero', 1, 70, 70, NULL, 2, 5, NULL, 'Realizada', '2020/04/11'),
(5, 17, 'creatinina en  suero', 1, 85, 85, NULL, 2, 5, NULL, 'Realizada', '2020/04/11'),
(6, 20, 'triglicéridos', 1, 85, 85, NULL, 2, 5, NULL, 'Realizada', '2020/04/11'),
(7, 27, 'globulinas', 1, 60, 60, NULL, 3, 6, NULL, 'Realizada', '2020/04/11'),
(8, 29, 'bilirrubina directa', 1, 110, 110, NULL, 3, 6, NULL, 'Realizada', '2020/04/11'),
(9, 28, 'relacion a/g', 1, 0, 0, NULL, 4, 7, NULL, 'Realizada', '2020/04/11'),
(10, 15, 'urea en suero', 2, 70, 140, NULL, 4, 7, NULL, 'Realizada', '2020/04/11'),
(16, 14, 'glucosa en suero', 1, 60, 60, NULL, 5, 8, NULL, 'Realizada', '2020/04/11'),
(17, 14, 'glucosa en suero', 2, 60, 120, NULL, 6, 9, NULL, 'Realizada', '2020/04/11'),
(18, 15, 'urea en suero', 1, 70, 70, NULL, 7, 10, NULL, 'Realizada', '2020/04/11'),
(19, 14, 'glucosa en suero', 1, 60, 60, NULL, 8, 11, NULL, 'Realizada', '2020/04/11'),
(20, 14, 'glucosa en suero', 2, 60, 120, NULL, 9, 12, NULL, 'Realizada', '2020/04/11'),
(21, 15, 'urea en suero', 1, 70, 70, NULL, 10, 13, NULL, 'Realizada', '2020/04/11'),
(22, 15, 'urea en suero', 1, 70, 70, NULL, 11, 14, NULL, 'Cancelada', '2020/04/11'),
(23, 15, 'urea en suero', 1, 70, 70, NULL, 12, 15, NULL, 'Realizada', '2020/04/11'),
(24, 15, 'urea en suero', 1, 70, 70, NULL, 13, 16, NULL, 'Realizada', '2020/04/11'),
(25, 14, 'glucosa en suero', 2, 60, 120, NULL, 14, 17, NULL, 'Realizada', '2020/04/11'),
(26, 15, 'urea en suero', 1, 70, 70, NULL, 15, 18, NULL, 'Realizada', '2020/04/11'),
(27, 16, 'relacion bun/creatinina', 1, 0, 0, NULL, 16, 19, NULL, 'Realizada', '2020/04/11'),
(28, 15, 'urea en suero', 1, 70, 70, NULL, 16, 19, NULL, 'Realizada', '2020/04/11'),
(29, 77, 'Ph', 1, 95, 95, NULL, 17, 20, NULL, 'Realizada', '2020/04/18'),
(30, 79, 'PROTEINAS', 1, 95, 95, NULL, 17, 20, NULL, 'Realizada', '2020/04/18'),
(31, 92, 'OTROS', 1, 95, 95, NULL, 17, 20, NULL, 'Realizada', '2020/04/18'),
(32, 143, 'COPROPARASITOSCOPICO (3) MUESTRAS', 1, 230, 230, NULL, 18, 21, NULL, 'Realizada', '2020/04/18'),
(33, 135, 'ANTÍGENO ESPECÍFICO DE PRÓSTATA TOTAL', 1, 250, 250, NULL, 18, 21, NULL, 'Realizada', '2020/04/18'),
(34, 96, 'VELOCIDAD DE SEDIMENTO GLOVULAR (VSG)', 1, 150, 150, NULL, 18, 21, NULL, 'Realizada', '2020/04/18'),
(35, 32, 'aspartato amino transferasa (TGO) (AST)', 1, 110, 110, NULL, 18, 21, NULL, 'Realizada', '2020/04/18'),
(36, 50, 'FOSFATASA ÁCIDA TOTAL (ACT. ENZIMATICA)', 1, 175, 175, NULL, 18, 21, NULL, 'Realizada', '2020/04/18'),
(37, 34, 'alanino amino transferasa (TGP) (ALT)', 1, 110, 110, NULL, 19, 22, NULL, 'Realizada', '2020/04/19'),
(38, 51, 'FOSFATASA ACIDA-FRACCION PROSTÁTICA', 1, 345, 345, NULL, 19, 22, NULL, 'Realizada', '2020/04/19'),
(39, 57, 'potreina c reactiva ultra sensible', 1, 430, 430, NULL, 19, 22, NULL, 'Realizada', '2020/04/19'),
(40, 17, 'creatinina en  suero', 1, 85, 85, NULL, 20, 23, NULL, 'Realizada', '2020/04/20'),
(41, 57, 'potreina c reactiva ultra sensible', 1, 430, 430, NULL, 20, 23, NULL, 'Realizada', '2020/04/20'),
(42, 114, 'DEHIDROEPIANDROSTERONA SULFATO', 1, 375, 375, NULL, 20, 23, NULL, 'Realizada', '2020/04/20'),
(43, 109, 'TESTOSTERONA TOTAL', 1, 255, 255, NULL, 20, 23, NULL, 'Realizada', '2020/04/20'),
(44, 153, 'PIEZA PATOLOGICA GRANDE', 1, 975, 975, NULL, 20, 23, NULL, 'Realizada', '2020/04/20'),
(53, 16, 'relacion bun/creatinina', 1, 0, 0, NULL, 21, 24, NULL, 'Realizada', '2020/04/20'),
(54, 14, 'glucosa en suero', 1, 60, 60, NULL, 21, 24, NULL, 'Realizada', '2020/04/20'),
(55, 15, 'urea en suero', 1, 70, 70, NULL, 21, 24, NULL, 'Realizada', '2020/04/20'),
(56, 14, 'glucosa en suero', 1, 60, 60, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(57, 19, 'colesterol total', 1, 85, 85, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(58, 22, 'colesterol de baja densidad (ldl)', 1, 145, 145, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(59, 38, 'hierro en suero', 1, 155, 155, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(60, 44, 'magnesio en suero', 1, 225, 225, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(61, 41, '% de saturación', 1, 0, 0, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(62, 56, 'INMUNOGLOBULINA E (IgE)', 1, 290, 290, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(63, 62, 'VCM', 1, 135, 135, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(64, 72, 'Tiempo de tromboplastina (TPT)', 1, 125, 125, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(65, 67, 'Granulocitos', 1, 135, 135, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(66, 82, 'URUBILINOGENO', 1, 95, 95, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(67, 90, 'URATO AMORFO', 1, 95, 95, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(68, 96, 'VELOCIDAD DE SEDIMENTO GLOVULAR (VSG)', 1, 150, 150, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(69, 108, 'CALCITONINA', 1, 615, 615, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(70, 114, 'DEHIDROEPIANDROSTERONA SULFATO', 1, 375, 375, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(71, 125, 'ESTRADIOL (E2)', 1, 270, 270, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(72, 128, 'ANTIGENO CA 19-9', 1, 575, 575, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(73, 129, 'ANTIGENO CA 125', 1, 560, 560, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(74, 155, 'PAPANICOLAOU', 1, 350, 350, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(75, 153, 'PIEZA PATOLOGICA GRANDE', 1, 975, 975, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(76, 159, 'MARIHUANA', 1, 300, 300, NULL, 22, 25, NULL, 'Realizada', '2020/04/20'),
(77, 15, 'urea en suero', 2, 70, 140, NULL, 23, 26, NULL, 'Realizada', '2020/04/21'),
(78, 63, 'HCM', 1, 135, 135, NULL, 23, 26, NULL, 'Realizada', '2020/04/21'),
(79, 102, 'PROTEUS OX-19', 1, 90, 90, NULL, 23, 26, NULL, 'Realizada', '2020/04/21'),
(80, 104, 'VIH 1/2 PRUEBA PRESUNTIVA', 1, 450, 450, NULL, 23, 26, NULL, 'Realizada', '2020/04/21'),
(81, 156, 'HISTOPATOLOGICO DE LILIQUIDO', 1, 550, 550, NULL, 23, 26, NULL, 'Realizada', '2020/04/21'),
(82, 159, 'MARIHUANA', 2, 300, 600, NULL, 23, 26, NULL, 'Realizada', '2020/04/21'),
(83, 162, 'BENZODIACEPINAS', 1, 350, 350, NULL, 23, 26, NULL, 'Realizada', '2020/04/21'),
(84, 14, 'glucosa en suero', 1, 60, 60, NULL, 24, 27, NULL, 'Realizada', '2020/04/22'),
(85, 62, 'VCM', 1, 135, 135, NULL, 24, 27, NULL, 'Realizada', '2020/04/22'),
(86, 59, 'Eritrocitos', 1, 135, 135, NULL, 24, 27, NULL, 'Realizada', '2020/04/22'),
(87, 91, 'BACTERIAS', 1, 95, 95, NULL, 24, 27, NULL, 'Realizada', '2020/04/22'),
(88, 136, 'CULTIVO FARINGEO', 1, 300, 300, NULL, 24, 27, NULL, 'Realizada', '2020/04/22'),
(89, 15, 'urea en suero', 1, 70, 70, NULL, 25, 28, NULL, 'Realizada', '2020/04/22'),
(90, 112, 'DEHIDROEPIANDROSTERONA', 1, 430, 430, NULL, 25, 28, NULL, 'Realizada', '2020/04/22'),
(91, 159, 'MARIHUANA', 1, 300, 300, NULL, 25, 28, NULL, 'Realizada', '2020/04/22'),
(92, 160, 'ANFETAMINAS', 1, 350, 350, NULL, 25, 28, NULL, 'Realizada', '2020/04/22'),
(93, 134, 'HORMONA DE CRECIMIENTO', 1, 445, 445, NULL, 25, 28, NULL, 'Realizada', '2020/04/22'),
(94, 124, 'PROGESTERONA', 1, 270, 270, NULL, 25, 28, NULL, 'Realizada', '2020/04/22'),
(95, 110, 'CORTISOL EN SUERO', 1, 290, 290, NULL, 25, 28, NULL, 'Realizada', '2020/04/22'),
(96, 19, 'colesterol total', 1, 85, 85, NULL, 26, 29, NULL, 'Realizada', '2020/04/23');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descripcion_estudios`
--

CREATE TABLE `descripcion_estudios` (
  `id_descripcion` int(11) NOT NULL,
  `unidades` varchar(700) DEFAULT NULL,
  `valordereferencia` varchar(700) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `descripcion_estudios`
--

INSERT INTO `descripcion_estudios` (`id_descripcion`, `unidades`, `valordereferencia`) VALUES
(1, 'mg/dL', '60.0 - 110.0 gop-pod. Líquido                              '),
(2, 'mg/dL', '9.0 -45.0 1D 6.0 - 54.0  prematuros 15.0 - 45.0 ureasa-gldh.cinético.líquido'),
(3, NULL, 'cálculo aritmético'),
(4, 'mg/dL', '0.20 - 0.80 1D 0.6 - 1.1 fem 0.7 - 1.4 masc jaffé.colorimétrico-cinético'),
(5, 'mg/dL', '3.60 - 7.70 Masc 2.50 - 6.80 Fem uricasa-POD. líquido'),
(6, 'mg/dL', 'menor a 170.0 normal 200-239 límite alto 240 a mayor alto chod-pod. líquido'),
(7, 'mg/dL', 'Menor a 150.0 35.0 - 135.0 fem  normal 40.0 - 160.0 masc  normal 150.0 -199.0 límite alto 200.0 - 499.0 alto 500.0 - 525.0 muy alto gpo-pod. líquido'),
(8, 'mg/dL', 'menor a 40.0 factor principal de riesgo de cardiopatias. mayor a 60.0 factor negativo de riesgo de cardiopatias espectofotometría por refractancia'),
(9, 'mg/dL', '65.0 - 130.0 140.0-159.0 limite alto 160.0 - 200.0 de riesgo espectofotometría por reflectancia'),
(10, 'mg/dL', '30.00 - 50.0 cálculo aritmético'),
(11, NULL, '0.00 - 4.00 cálcuo aritmético'),
(12, 'g/dL', '6.00 - 8.00 espectrofotometría por reflectancia'),
(13, 'g/dL', '2.8 -4.50 3.0 - 5.0  adulto espectrofotometría por reflectancia'),
(14, 'g/dL', '2.00 - 4.00 cálculo aritmético'),
(15, '**', '1.10 - 1.90 cálculo aritmético'),
(16, 'mg/dL', '0.00 - 0.40 dmso. Colorimétrico'),
(17, 'mg/dL', 'menor a 12 recien nacidos 1.10 adultos dmso.colorímetrico'),
(18, 'mg/dL', '0.00 - 1.10 cálculo aritmético'),
(19, 'U/L', '5.00 - 38 masculinob 5.0 - 31 femenino  nadh.cinético uv. ifcc reac. líquido'),
(20, 'U/L', '0.00 - 40.0 masculino 0.00 - 32 femenio'),
(21, 'U/L', 'menor a 500 38.0 - 126.0 adulto espectrofotometía por reflectancia'),
(22, 'U/L', '2.00 - 15.00 7.0 - 35.0 femenino 11.0 - 50.0 masculino sustrato carboxilado.Cinético. Líquido'),
(23, 'U/L', '125.00 - 220.00 120.0 - 246.0 adulto espectrofotometría por reflectancia'),
(24, 'µg/dL', '25.00 - 126 37.0 - 170 femenino 49.0 - 181.0 masculino espectofotometiría por reflectancia'),
(25, 'µg/dL', '140.00 - 346.00 femenino 140.00 - 330.00 masculino cálculo aritmético'),
(26, 'µg/dL', '261.00 - 462.00 espectofotometría por reflectancia'),
(27, '%', '5.00 - 51.00 epectofotometria por reflectancia'),
(28, 'mg/dL', '7.60 - 10.40  0D - 10 D 9.00 - 11. 00  11D - 24M 8.80 - 10.80  2A- 12A 8.40 - 10.20  13A - 60 A 8.80 - 10.00  61A - 99 A espectofotometría por reflectancia'),
(29, 'mg/dL', '3.00 - 6.00 2.50 - 4.50  18 a - 99 a espectofotometría por reflectancia'),
(30, 'mg/dL', '1.70 - 2.30    5M -6A 1.60 - 2.30  18 a - 99 a espectofotometría por reflectancia'),
(31, 'mmol/L', '138.00 - 145.00 1M - 17 A 137.0 - 145.0 18 a- 105 a espectofotometría por ión selectivo'),
(32, 'mmol/L', '3.40 - 4.70  1M - 17 A  espectofotometría'),
(33, 'mmol/L', '98.00 - 107.00 17A - 105A 96.00 - 106.00 1D a 30D 90.00 - 110.00 31D - 17A potenciometría por ión selectivo'),
(34, 'µUl/mL', '1.90 - 23.00 12A - 99A 3.00 - 17.00 2A 12A  QUIMIOLUMINISCENCIA'),
(35, 'U/L', '23.00 - 300.00 ESPCTROFOTOMETRIA POR REFLECTANCIA'),
(36, 'U/L', '0.00 - 10.00 Masc  ESPECTROFOTOMETRIA'),
(37, 'U/L', '0.00 - 1.60       ESPECTROFOTOMETRIA'),
(38, '%', 'Menor a 5.90 NORMAL NO DIABETICO 6.00 - 7.00 BUEN CONTROL 8.00 - 14.00 MAL CONTROL ESPECTROFOTOMETRÍA'),
(39, 'mg/dL', '21.00 - 291.00 1A- 12 A MASC 63.00 484.00 13A - 60A 101.00 - 645.00 61A - 110A 21.00 - 282.00 1A - 12A 65.00 - 421.00  13A - 60A 69.00 - 517.00 61A - 110A INMUNOTURBIDIMETRÍA'),
(40, 'mg/dL', '475.00 - 1210.00 1A - 2A MASC 540.00 - 1822.00 3A - 80A 483.00 - 1226.00 FEM 1A - 2A 522.00 - 1631.00 3A - 80A INMUNOTURBIDIMETRÍA'),
(41, 'mg/dL', '41.00 - 183.00 MASC 1A - 12A 22.00 - 240.00 13A - 110.00 47.00 - 240.00 FEM 1A - 12A 33.00 - 293.00 13A - 110A INMUNOTURBIDIMETRÍA'),
(42, 'UI/mL', '60.00 2A - 5A 90.00 6A - 9A 20.00 10A - 15A 100.00 16A - 99A ELECTROQUIMIOLUMINISCENCIA'),
(43, 'mg/dL', 'menor a 1.00 bajo riesgo cardiovascular 1.0 -3.0 medio riesgo cardiovascular 3.10 - 10.0 alto riesgo cardiovascular  mayor a 10.0 inflamación no cardiovascular'),
(44, '10^6 / ul', '3.5 - 6.0'),
(45, 'g /dl', '12.0 -16.0'),
(46, '10^3 / ul', '4.0 - 11.0'),
(47, '%', '37.0 - 47.0'),
(48, 'fL', '80.0 - 95.0'),
(49, 'pg', '30.0 - 35.0'),
(50, 'g /dl', '30.0 - 35.0'),
(51, '10^3 / ul', '150 - 400'),
(52, 'fL', '5.0 - 15.0'),
(53, '%', '40.0 -70.0'),
(54, '%', '20.0 -45.0'),
(55, '%', '2.0 -10.0'),
(56, 'SEGUNDOS', 'Testigo  12.3'),
(57, NULL, NULL),
(58, 'SEGUNDOS', 'Testigo  30.0'),
(59, 'VALOR EMPTY EN EXCEL', 'VALOR EMPTY EN EXCEL '),
(60, NULL, 'TRANSPARENTE'),
(61, NULL, 'AMARILLO'),
(62, NULL, '1,01'),
(63, NULL, '5.0 - 7.0'),
(64, NULL, 'NEGATIVO'),
(65, NULL, 'NEGATIVO'),
(66, NULL, 'NEGATIVO'),
(67, NULL, 'NEGATIVO'),
(68, NULL, 'NEGATIVO'),
(69, NULL, 'NEGATIVO'),
(70, NULL, 'NEGATIVO'),
(71, NULL, 'NEGATIVO'),
(72, NULL, '0 - 3/ CAMPO'),
(73, NULL, '0/ CAMPO'),
(74, NULL, 'NEGATIVO'),
(75, NULL, 'NEGATIVO'),
(76, NULL, 'NEGATIVO'),
(77, NULL, 'NEGATIVO'),
(78, NULL, 'NEGATIVO'),
(79, NULL, 'NEGATIVO'),
(80, NULL, 'NEGATIVO'),
(81, NULL, 'NEGATIVO'),
(82, 'mm/hr', 'HOMBRES: 0 - 10                           MUJERES 0 -15'),
(83, NULL, 'NEGATIVO'),
(84, NULL, 'NEGATIVO'),
(85, NULL, 'NEGATIVO'),
(86, NULL, 'NEGATIVO'),
(87, NULL, 'NEGATIVO'),
(88, NULL, 'NEGATIVO'),
(89, NULL, 'NEGATIVO'),
(90, NULL, 'NEGATIVO'),
(91, 'U/mL', 'Menor a 5.00 NEGATIVO            Mayor a 5.00 POSITIVO QUIMIOLUMINISCENCIA'),
(92, 'Ul/mL', 'Menor aigual 4.11 NEGATIVO Mayor a 4.11 POSITIVO QUIMIOLUMINISCENCIA'),
(93, 'Ul/mL', 'Menor a 5.61 NEGATIVO Mayor a 5.61 POSITIVO QUIMIOLUMINISCENCIA'),
(94, 'pg/mL', 'Menor a 5.00 Fem Menor a 8.40 Masc QUIMIOLUMINISCENCIA'),
(95, 'ng/mL', '0.07 - 0.79 Fem 21A - 49A QUIMIOLUMINISCENCIA'),
(96, 'µg/dL', '3.70 - 19.40 20A - 99A AM 2.90 - 17.30 20A - 99A PM 1.00 - 38.00 Masc 10A - 13A QUIMIOLUMINISCENCIA'),
(97, 'ng/mL', '0.30 - 3.50 Fem 18A - 99A QUIMIOLUMINISCENCIA'),
(98, 'ng/mL', '1.30 - 9.80 Fem 18A - 99A ENSAYO INMUNOABSORBENTE LIGADO A LA ENZIMA'),
(99, 'ng/mL', '0.10 - 0.80 Fem fase folicular 0.60 - 2.30 Fem fase lutea 0.30 - 1.40 Fem ovulacion 0.00 - 3.20 Fem post acth 2.00 - 12.00 Fem tercer trimestre 0.13 - 0.51 postmenopausia 0.50 - 3.00 Masc Menor a 6.30 MENOR DE 1 AÑO INMUNO ANALISIS ENZIMATICO'),
(100, 'µg/dL', '95.80 - 511.70 Fem 24A - 34A QUIMILUMINISCENCIA'),
(101, '%', '24.00 - 35.00 QUIMIOLUMINISCENCIA'),
(102, 'ng/mL', '58.00 - 159.00 QUIMIOLUMINISCENCIA        '),
(103, 'µg/dL', '4.87 - 11.72 QUIMIOLUMINISCENCIA VC: Menor a: 2.00 Mayor a: 20.00'),
(104, 'µg/dL', '2.90 - 8.10 CÁLCULO ARITMÉTICO'),
(105, 'µg/dL', '1.10 - 4.20 CÁLCULO ARITMÉTICO'),
(106, 'µUl/mL', '0.35 - 4.94 QUIMIOLUMINISCENCIA'),
(107, 'mUl/mL', '0.57 - 12.07 Masc 1.80 - 11.80 Fem FASE FOLICULAR 7.59 - 89.09 Fem OVULACION 0.56 - 14.00Fem FASE LUTEA 5.16 - 61.99 Fem POSTMENOPAUSIA SIN TERAPIA DE REEMPLAZO HOMONAL 0.00 - 3.90 Fem 1A - 10A 0.00 - 3.60 Masc 1A - 10A QUIMIOLUMINISCENCIA'),
(108, 'mUl/mL', '3.03 - 8.08 Fem FASE FOLICULAR 2.55 - 16.69 Fem OVULACION 1.38 - 5.47 Fem FASE LUTEA 26.72 - 133.41 Fem POSTMENOPAUSIA QUIMIOLUMINISCENCIA'),
(109, 'ng/mL', '3.46 - 19.40 Mas 16A - 99A 5.18 - 26.53 Fem 16A - 99A 0.00 - 80.00 Fem 1ER. TRIM. DE GESTACION 0.00 - 160.00 Fem 2DO. TRIM. DE GESTACION 0.00 - 400.00 Fem 3ER. TRIM. DE GESTACION 1.60 - 13.10 Fem 4A - 6A 0.30 - 12.90 Fem 7A - 9A 1.90 - 9.60 Fem 10A - 12A 3.00 - 14.40 Fem 13A - 15A QUIMIOLUMINISCENCIA'),
(110, 'ng/mL', 'Menor a 0.30 Fem FASE FOLICULAR 1.20 - Fem FASE LUTEA Menor a 0.20 Fem POSTMENOPAUSIA 2.80 - 147.30 Fem 1ER. TRIM. DE GESTACION 22.50 - 95.30 Fem 2DO. TRIM. DE GESTACION 27.90 - 242.50 3ER. TRIM. DE GESTACION Menor a 12.00 Fem FASE OVULATORIA QUIMIOLUMINISCENCIA'),
(111, 'pg/mL', '21.00 - 251.00 Fem FASE FOLICULAR 38.00 - 649.00 Fem FASE OVULATORIA 21.00 - 312.00 Fem FASE LUTEINICA Menor a 28.00 Fem MUJERES POSTMENOPAUSICAS QUIMIOLUMISCENCIA'),
(112, 'mUl/mL', 'Menor a 5.00 Masc 0.00 - 5.00 Fem 5.00 - 50.00Fem EDAD GESTACIONAL 0.2 - 1 SEMANAS 50.00 - 500.00 Fem EDAD GESTACIONAL 1 - 2 SEMANAS 100.00 - 5,000.00 Fem EDAD GESTACIONAL 2 - 3 SEMANAS 500.00 - 10,000.00 Fem EDAD GESTACIONAL 3 - 4 SEMANAS 1,000 - 50,000.00 Fem EDAD GESTACIONAL 4 - 5 SEMANAS 10,000.00 - 100,000.00 Fem EDAD GESTACIONAL 5 - 6 SEMANAS 15,000.00 - 200,000.00 Fem EDAD GESTACIONAL 6 - 8 SEMANAS 10,000.00 - 100,000.00 Fem EDAD GESTACIONAL 8 - 12 SEMANAS QUIMIOLUMINISCENCIA'),
(113, 'U/mL', 'Menor a 31.30  QUIMIOLUMINISCENCIA'),
(114, 'U/mL', '0.00 - 34.00 Masc Menor a 35.00 QUIMIOLUMINISCENCIA'),
(115, 'U/mL', 'Menor a 35.00 QUIMIOLUMINISCENCIA'),
(116, 'ng/mL', '1.09 - 8.04  18A - 99A                        SEMANAS DE GESTACION                    VALORES EN SUERO MATERNO                                       15 ……….36.4                           16……….41.4 17……….47.0 18……….53.3 19 ……….60.5 20 ……….68.7 21 ……….78.0 QUIMIOLUMINISCENCIA'),
(117, 'ng/mL', 'Menor a 5.00 QUIMIOLUMINISCENCIA'),
(118, 'ng/mL', '3.50 77.00 ELECTROQUIMIOLUMINISCENCIA'),
(119, 'pg/mL', '15.00 - 65.00 ELECTROQUIMIOLUMINISCENCIA'),
(120, 'ng/mL', '0.13 - 9.88 Fem 18A - 99A 0.03 - 2.47 Masc 18A - 90A ELECTROQUIMIOLUMINISCENCIA'),
(121, 'ng/mL', '0.00 - 4.00 Masc 15A - 55A 0.00 - 4.90 Masc 56A - 65A 0.00 - 7.30 Masc 66A - 75A 0.00 - 8.60 Masc 76A - 105A  QUIMIOLUMINISCENCIA'),
(122, NULL, 'NEGATIVO'),
(123, NULL, 'NEGATIVO'),
(124, NULL, 'NEGATIVO'),
(125, NULL, 'NEGATIVO'),
(126, NULL, 'NEGATIVO'),
(127, NULL, 'NEGATIVO'),
(128, NULL, 'NEGATIVO'),
(129, NULL, 'NEGATIVO   '),
(135, NULL, 'Sin categoria'),
(136, 'Aver', 'Sin categoria1'),
(137, 'Aver2', 'Sin categoria2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `egreso`
--

CREATE TABLE `egreso` (
  `idegreso` int(11) NOT NULL,
  `cantidad` float DEFAULT NULL,
  `tipo` varchar(80) DEFAULT NULL,
  `fecha` varchar(100) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `usuario` int(11) NOT NULL,
  `id_venta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `egreso`
--

INSERT INTO `egreso` (`idegreso`, `cantidad`, `tipo`, `fecha`, `total`, `usuario`, `id_venta`) VALUES
(1, 0, 'V. 13/Descuento: ', '2020/04/11', 20, 4, 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `id_paciente` int(11) NOT NULL,
  `nombre` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `fecha_nacimiento` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `edad` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `sexo` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `dato_auxiliar` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `fecha_ingreso` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`id_paciente`, `nombre`, `fecha_nacimiento`, `edad`, `sexo`, `dato_auxiliar`, `fecha_ingreso`) VALUES
(3, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(4, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(5, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(6, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(7, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(8, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(9, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(10, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(11, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(12, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(13, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(14, NULL, NULL, NULL, NULL, 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(15, 'Otra edicion', '2020/04/23', '12', 'Femenino', 'Editado', '2020-04-18 22:37:27'),
(16, 'Paciente editado', '2020/04/15', '2', 'Femenino', 'Editado', '2020-04-18 22:37:27'),
(17, 'Saul', '2020/04/10', '15', 'Femenino', 'Intento de editar', '2020-04-18 22:37:27'),
(18, 'Alexis', '2020/04/16', '7', 'Femenino', 'Intento de editar', '2020-04-18 22:37:27'),
(19, 'Javier', '2020/04/01', '12 años', 'Masculino', 'Paciente insertado para permitir el flujo del sistema por foreingkey', '2020-04-18 22:37:27'),
(20, 'Rafael Isaul Hernadez Ramirez', '2020/04/18', '12 años.', 'Masculino', 'En espera de venta', '2020-04-18 22:37:27'),
(21, 'Rafael Isaul Hernandez Ramirez', '2020/04/20', '25 años.', 'Masculino', 'En espera de venta', '2020-04-18 22:37:27'),
(22, 'isa', '2020/04/19', '23 años.', 'Masculino', 'En espera de venta', '2020-04-18 23:16:09'),
(23, 'aaa', '2020/04/19', '3 años.', 'Masculino', 'En espera de venta', '2020-04-19 05:05:49'),
(24, 'Chely', '2020/04/19', '23 años.', 'Femenino', 'En espera de venta', '2020-04-19 08:01:27'),
(25, 'diego', '2020/04/20', '12 años.', 'Masculino', 'En espera de venta', '2020-04-19 23:23:39'),
(26, 'soy nuevo', '2020/04/18', '12 años.', 'Masculino', 'En espera de venta', '2020-04-20 05:49:21'),
(27, 'usa', '2020/04/10', '23 años.', 'Masculino', 'En espera de venta', '2020-04-21 06:02:47'),
(28, 'new', '2020/04/03', '3 años.', 'Masculino', 'En espera de venta', '2020-04-22 06:14:46'),
(29, 'mk', '2020/04/11', '2 años.', 'Masculino', 'En espera de venta', '2020-04-22 06:38:08'),
(30, NULL, NULL, NULL, NULL, 'En espera de venta', '2020-04-23 06:59:20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL,
  `nombre_producto` varchar(200) DEFAULT NULL,
  `categoria_estudios` varchar(60) DEFAULT NULL,
  `descripcion_estudio` int(11) NOT NULL,
  `precio` float DEFAULT NULL,
  `cantidad` float DEFAULT NULL,
  `fecha_de_caducidad` varchar(100) DEFAULT NULL,
  `fecha` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `nombre_producto`, `categoria_estudios`, `descripcion_estudio`, `precio`, `cantidad`, `fecha_de_caducidad`, `fecha`) VALUES
(14, 'glucosa en suero', 'QUÍMICA CLÍNICA', 1, 60, 0, 'Sin fecha de caducidad', '2020/03/16'),
(15, 'urea en suero', 'QUÍMICA CLÍNICA', 2, 70, 0, 'Sin fecha de caducidad', '2020/03/16'),
(16, 'relacion bun/creatinina', 'QUÍMICA CLÍNICA', 3, 0, 0, 'Sin fecha de caducidad', '2020/03/16'),
(17, 'creatinina en  suero', 'QUÍMICA CLÍNICA', 4, 85, 0, 'Sin fecha de caducidad', '2020/03/16'),
(18, 'ácido urico en suero', 'QUÍMICA CLÍNICA', 5, 75, 0, 'Sin fecha de caducidad', '2020/03/16'),
(19, 'colesterol total', 'QUÍMICA CLÍNICA', 6, 85, 0, 'Sin fecha de caducidad', '2020/03/16'),
(20, 'triglicéridos', 'QUÍMICA CLÍNICA', 7, 85, 0, 'Sin fecha de caducidad', '2020/03/16'),
(21, 'colesterol de alta densidad (hdl)', 'QUÍMICA CLÍNICA', 8, 135, 1, 'Sin fecha de caducidad', '2020/03/16'),
(22, 'colesterol de baja densidad (ldl)', 'QUÍMICA CLÍNICA', 9, 145, 0, 'Sin fecha de caducidad', '2020/03/16'),
(23, 'colesterol de muy baja densidad', 'QUÍMICA CLÍNICA', 10, 120, 0, 'Sin fecha de caducidad', '2020/03/16'),
(24, 'indice aterogenico', 'QUÍMICA CLÍNICA', 11, 60, 0, 'Sin fecha de caducidad', '2020/03/16'),
(25, 'proteínas totales séricas', 'QUÍMICA CLÍNICA', 12, 100, 2, 'Sin fecha de caducidad', '2020/03/16'),
(26, 'albúmina en suero', 'QUÍMICA CLÍNICA', 13, 105, 3, 'Sin fecha de caducidad', '2020/03/16'),
(27, 'globulinas', 'QUÍMICA CLÍNICA', 14, 60, 1, 'Sin fecha de caducidad', '2020/03/16'),
(28, 'relacion a/g', 'QUÍMICA CLÍNICA', 15, 0, 0, 'Sin fecha de caducidad', '2020/03/16'),
(29, 'bilirrubina directa', 'QUÍMICA CLÍNICA', 16, 110, 1, 'Sin fecha de caducidad', '2020/03/16'),
(30, 'bilirrubina total', 'QUÍMICA CLÍNICA', 17, 120, 0, 'Sin fecha de caducidad', '2020/03/16'),
(31, 'bilirrubina indirecta', 'QUÍMICA CLÍNICA', 18, 120, 0, 'Sin fecha de caducidad', '2020/03/16'),
(32, 'aspartato amino transferasa (TGO) (AST)', 'QUÍMICA CLÍNICA', 19, 110, 0, 'Sin fecha de caducidad', '2020/03/16'),
(34, 'alanino amino transferasa (TGP) (ALT)', 'QUÍMICA CLÍNICA', 20, 110, 0, 'Sin fecha de caducidad', '2020/03/16'),
(35, 'fosfatasa alcalina total', 'QUÍMICA CLÍNICA', 21, 120, 0, 'Sin fecha de caducidad', '2020/03/16'),
(36, 'gamma glutamil transpetidasa', 'QUÍMICA CLÍNICA', 22, 106, 0, 'Sin fecha de caducidad', '2020/03/16'),
(37, 'deshidrogenasa láctica', 'QUÍMICA CLÍNICA', 23, 145, 0, 'Sin fecha de caducidad', '2020/03/16'),
(38, 'hierro en suero', 'QUÍMICA CLÍNICA', 24, 155, 0, 'Sin fecha de caducidad', '2020/03/16'),
(39, 'uibc', 'QUÍMICA CLÍNICA', 25, 0, 0, 'Sin fecha de caducidad', '2020/03/16'),
(40, 'capacidad total de fijacion de hierro', 'QUÍMICA CLÍNICA', 26, 255, 0, 'Sin fecha de caducidad', '2020/03/16'),
(41, '% de saturación', 'QUÍMICA CLÍNICA', 27, 0, 0, 'Sin fecha de caducidad', '2020/03/16'),
(42, 'calcio en suero', 'QUÍMICA CLÍNICA', 28, 110, 0, 'Sin fecha de caducidad', '2020/03/16'),
(43, 'fósforo en suero', 'QUÍMICA CLÍNICA', 29, 120, 0, 'Sin fecha de caducidad', '2020/03/16'),
(44, 'magnesio en suero', 'QUÍMICA CLÍNICA', 30, 225, 0, 'Sin fecha de caducidad', '2020/03/16'),
(45, 'sodio en suero', 'QUÍMICA CLÍNICA', 31, 100, 0, 'Sin fecha de caducidad', '2020/03/16'),
(46, 'potasio en suero', 'QUÍMICA CLÍNICA', 32, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(47, 'cloro en suero', 'QUÍMICA CLÍNICA', 33, 100, 0, 'Sin fecha de caducidad', '2020/03/16'),
(48, 'INSULINA EN SUERO', 'QUÍMICA CLÍNICA', 34, 275, 0, 'Sin fecha de caducidad', '2020/03/16'),
(49, 'LIPASA', 'QUÍMICA CLÍNICA', 35, 250, 0, 'Sin fecha de caducidad', '2020/03/16'),
(50, 'FOSFATASA ÁCIDA TOTAL (ACT. ENZIMATICA)', 'QUÍMICA CLÍNICA', 36, 175, 0, 'Sin fecha de caducidad', '2020/03/16'),
(51, 'FOSFATASA ACIDA-FRACCION PROSTÁTICA', 'QUÍMICA CLÍNICA', 37, 345, 0, 'Sin fecha de caducidad', '2020/03/16'),
(52, 'HEMOGLOBINA GLICOSILADA (Hb-A1c)', 'QUÍMICA CLÍNICA', 38, 250, 0, 'Sin fecha de caducidad', '2020/03/16'),
(53, 'inmunoglobulina a (IgA)', 'QUÍMICA CLÍNICA', 39, 430, 0, 'Sin fecha de caducidad', '2020/03/16'),
(54, 'inmunoglobulina g (IgG)', 'QUÍMICA CLÍNICA', 40, 355, 0, 'Sin fecha de caducidad', '2020/03/16'),
(55, 'inmunoglobulina m (IgM)', 'QUÍMICA CLÍNICA', 41, 310, 0, 'Sin fecha de caducidad', '2020/03/16'),
(56, 'INMUNOGLOBULINA E (IgE)', 'QUÍMICA CLÍNICA', 42, 290, 0, 'Sin fecha de caducidad', '2020/03/16'),
(57, 'potreina c reactiva ultra sensible', 'QUÍMICA CLÍNICA', 43, 430, 0, 'Sin fecha de caducidad', '2020/03/16'),
(58, 'Leucocitos', 'BIOMETRIA HEMÁTICA', 44, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(59, 'Eritrocitos', 'BIOMETRIA HEMÁTICA', 45, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(60, 'Hemoglobina', 'BIOMETRIA HEMÁTICA', 46, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(61, 'Hematocrito', 'BIOMETRIA HEMÁTICA', 47, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(62, 'VCM', 'BIOMETRIA HEMÁTICA', 48, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(63, 'HCM', 'BIOMETRIA HEMÁTICA', 49, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(64, 'CHCM', 'BIOMETRIA HEMÁTICA', 50, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(65, 'Plaquetas', 'BIOMETRIA HEMÁTICA', 51, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(66, 'VPM', 'BIOMETRIA HEMÁTICA', 52, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(67, 'Granulocitos', 'BIOMETRIA HEMÁTICA', 53, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(68, 'Linfocitos', 'BIOMETRIA HEMÁTICA', 54, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(69, 'Monocitos', 'BIOMETRIA HEMÁTICA', 55, 135, 0, 'Sin fecha de caducidad', '2020/03/16'),
(70, 'Tiempo de protrombina (TP)', 'BIOMETRIA HEMÁTICA', 56, 125, 0, 'Sin fecha de caducidad', '2020/03/16'),
(71, 'INR', 'BIOMETRIA HEMÁTICA', 57, 0, 0, 'Sin fecha de caducidad', '2020/03/16'),
(72, 'Tiempo de tromboplastina (TPT)', 'BIOMETRIA HEMÁTICA', 58, 125, 0, 'Sin fecha de caducidad', '2020/03/16'),
(73, 'GRUPO SANGUINEO Y RH', 'BIOMETRIA HEMÁTICA', 59, 55, 0, 'Sin fecha de caducidad', '2020/03/16'),
(74, 'ASPECTO', 'EXAMEN GENERAL DE ORINA', 60, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(75, 'COLOR', 'EXAMEN GENERAL DE ORINA', 61, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(76, 'DENCIDAD', 'EXAMEN GENERAL DE ORINA', 62, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(77, 'Ph', 'EXAMEN GENERAL DE ORINA', 63, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(78, 'NITRITOS', 'EXAMEN GENERAL DE ORINA', 64, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(79, 'PROTEINAS', 'EXAMEN GENERAL DE ORINA', 65, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(80, 'GLUCOSA', 'EXAMEN GENERAL DE ORINA', 66, 95, 1, 'Sin fecha de caducidad', '2020/03/16'),
(81, 'CETONAS', 'EXAMEN GENERAL DE ORINA', 67, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(82, 'URUBILINOGENO', 'EXAMEN GENERAL DE ORINA', 68, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(83, 'BILIRRUBINAS', 'EXAMEN GENERAL DE ORINA', 69, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(84, 'HEMOGLOBINA', 'EXAMEN GENERAL DE ORINA', 70, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(85, 'LEUCOCITOS', 'EXAMEN GENERAL DE ORINA', 71, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(86, 'LEUCOCITOS', 'EXAMEN GENERAL DE ORINA', 72, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(87, 'ERITROCITOS', 'EXAMEN GENERAL DE ORINA', 73, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(88, 'CELULAS EPITELIALES', 'EXAMEN GENERAL DE ORINA', 74, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(89, 'CELULAS RENALES', 'EXAMEN GENERAL DE ORINA', 75, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(90, 'URATO AMORFO', 'EXAMEN GENERAL DE ORINA', 76, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(91, 'BACTERIAS', 'EXAMEN GENERAL DE ORINA', 77, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(92, 'OTROS', 'EXAMEN GENERAL DE ORINA', 78, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(93, 'PROTEINA "C" REATIVA CUANTITATIVO', 'INMUNOLOGIA', 79, 130, 0, 'Sin fecha de caducidad', '2020/03/16'),
(94, 'FACTOR REUMATOIDE CUANTITATIVO', 'INMUNOLOGIA', 80, 145, 0, 'Sin fecha de caducidad', '2020/03/16'),
(95, 'ANTI ESTREPTOLICONAS CUANTITATIVO', 'INMUNOLOGIA', 81, 155, 0, 'Sin fecha de caducidad', '2020/03/16'),
(96, 'VELOCIDAD DE SEDIMENTO GLOVULAR (VSG)', 'INMUNOLOGIA', 82, 150, 0, 'Sin fecha de caducidad', '2020/03/16'),
(97, 'TIFICO "O"', 'INMUNOLOGIA', 83, 90, 0, 'Sin fecha de caducidad', '2020/03/16'),
(98, 'TIFICO "H"', 'INMUNOLOGIA', 84, 90, 0, 'Sin fecha de caducidad', '2020/03/16'),
(99, 'PARATIFICO A', 'INMUNOLOGIA', 85, 90, 0, 'Sin fecha de caducidad', '2020/03/16'),
(100, 'PARATIFICO B', 'INMUNOLOGIA', 86, 90, 0, 'Sin fecha de caducidad', '2020/03/16'),
(101, 'ROSA DE BENGALA', 'INMUNOLOGIA', 87, 90, 0, 'Sin fecha de caducidad', '2020/03/16'),
(102, 'PROTEUS OX-19', 'INMUNOLOGIA', 88, 90, 0, 'Sin fecha de caducidad', '2020/03/16'),
(103, 'VDRL CUANTITATIVO', 'INMUNOLOGIA', 89, 95, 0, 'Sin fecha de caducidad', '2020/03/16'),
(104, 'VIH 1/2 PRUEBA PRESUNTIVA', 'INMUNOLOGIA', 90, 450, 0, 'Sin fecha de caducidad', '2020/03/16'),
(105, 'PÉPTIDO CICLICO CITRULINADO ANTICUERPO IgG', 'INMUNOLOGIA', 91, 935, 0, 'Sin fecha de caducidad', '2020/03/16'),
(106, 'ANTICUERPOS ANTI TIROGLOBULINA (COLOIDE TIROIDEO)', 'ENDOCRINOLOGIA', 92, 360, 0, 'Sin fecha de caducidad', '2020/03/16'),
(107, 'ANICUERPOS ANTI MICROSOMALES( ACS. ANTI PEROXIDASA)', 'ENDOCRINOLOGIA', 93, 355, 0, 'Sin fecha de caducidad', '2020/03/16'),
(108, 'CALCITONINA', 'ENDOCRINOLOGIA', 94, 615, 0, 'Sin fecha de caducidad', '2020/03/16'),
(109, 'TESTOSTERONA TOTAL', 'ENDOCRINOLOGIA', 95, 255, 0, 'Sin fecha de caducidad', '2020/03/16'),
(110, 'CORTISOL EN SUERO', 'ENDOCRINOLOGIA', 96, 290, 0, 'Sin fecha de caducidad', '2020/03/16'),
(111, 'ANDROSTENEDIONA', 'ENDOCRINOLOGIA', 97, 450, 0, 'Sin fecha de caducidad', '2020/03/16'),
(112, 'DEHIDROEPIANDROSTERONA', 'ENDOCRINOLOGIA', 98, 430, 0, 'Sin fecha de caducidad', '2020/03/16'),
(113, '17 ALFA HIDROXI PROGESTERONA (SUERO)', 'ENDOCRINOLOGIA', 99, 470, 0, 'Sin fecha de caducidad', '2020/03/16'),
(114, 'DEHIDROEPIANDROSTERONA SULFATO', 'ENDOCRINOLOGIA', 100, 375, 0, 'Sin fecha de caducidad', '2020/03/16'),
(115, 'TUTAKE (CAPTACION)', 'ENDOCRINOLOGIA', 101, 290, 0, 'Sin fecha de caducidad', '2020/03/16'),
(116, 'T3 TOTAL (TRIYODOTIRONINA)', 'ENDOCRINOLOGIA', 102, 290, 0, 'Sin fecha de caducidad', '2020/03/16'),
(117, 'T4 TOTAL (TIROXINA)', 'ENDOCRINOLOGIA', 103, 300, 0, 'Sin fecha de caducidad', '2020/03/16'),
(118, 'YODO PROTEICO', 'ENDOCRINOLOGIA', 104, 50, 0, 'Sin fecha de caducidad', '2020/03/16'),
(119, 'INDICE DE TIROXINA LIBRE ( ITL, FTL)', 'ENDOCRINOLOGIA', 105, 50, 0, 'Sin fecha de caducidad', '2020/03/16'),
(120, 'HORMONA ESTIMULANATE DE TIROIDES (TSH)', 'ENDOCRINOLOGIA', 106, 155, 0, 'Sin fecha de caducidad', '2020/03/16'),
(121, 'HORMONA LUTEINIZANTE', 'ENDOCRINOLOGIA', 107, 180, 0, 'Sin fecha de caducidad', '2020/03/16'),
(122, 'HORMONA ESTIMULANTE DE FOLICULO', 'ENDOCRINOLOGIA', 108, 180, 0, 'Sin fecha de caducidad', '2020/03/16'),
(123, 'PROLACTINA', 'ENDOCRINOLOGIA', 109, 265, 0, 'Sin fecha de caducidad', '2020/03/16'),
(124, 'PROGESTERONA', 'ENDOCRINOLOGIA', 110, 270, 0, 'Sin fecha de caducidad', '2020/03/16'),
(125, 'ESTRADIOL (E2)', 'ENDOCRINOLOGIA', 111, 270, 0, 'Sin fecha de caducidad', '2020/03/16'),
(126, 'BETA GONADOROFINA CORIONICA HUMANA (HCG-B)', 'ENDOCRINOLOGIA', 112, 325, 0, 'Sin fecha de caducidad', '2020/03/16'),
(127, 'ANTIGENO CA 15-3', 'ENDOCRINOLOGIA', 113, 475, 0, 'Sin fecha de caducidad', '2020/03/16'),
(128, 'ANTIGENO CA 19-9', 'ENDOCRINOLOGIA', 114, 575, 0, 'Sin fecha de caducidad', '2020/03/16'),
(129, 'ANTIGENO CA 125', 'ENDOCRINOLOGIA', 115, 560, 0, 'Sin fecha de caducidad', '2020/03/16'),
(130, 'ALFAFETOPROTEINA', 'ENDOCRINOLOGIA', 116, 365, 0, 'Sin fecha de caducidad', '2020/03/16'),
(131, 'ANTIGENO CARCINOEMBRIONARIO', 'ENDOCRINOLOGIA', 117, 420, 0, 'Sin fecha de caducidad', '2020/03/16'),
(132, 'TIROGLOBULINA (Tg)', 'ENDOCRINOLOGIA', 118, 420, 0, 'Sin fecha de caducidad', '2020/03/16'),
(133, 'HORMONA PARATIROIDEA', 'ENDOCRINOLOGIA', 119, 590, 0, 'Sin fecha de caducidad', '2020/03/16'),
(134, 'HORMONA DE CRECIMIENTO', 'ENDOCRINOLOGIA', 120, 445, 0, 'Sin fecha de caducidad', '2020/03/16'),
(135, 'ANTÍGENO ESPECÍFICO DE PRÓSTATA TOTAL', 'ENDOCRINOLOGIA', 121, 250, 0, 'Sin fecha de caducidad', '2020/03/16'),
(136, 'CULTIVO FARINGEO', 'BACTERIOLOGIA', 122, 300, 0, 'Sin fecha de caducidad', '2020/03/16'),
(137, 'CULTIVO DE SECRECION URETAL', 'BACTERIOLOGIA', 123, 380, 0, 'Sin fecha de caducidad', '2020/03/16'),
(138, 'CULTIVO DE EXUDADO VAGINAL', 'BACTERIOLOGIA', 124, 355, 0, 'Sin fecha de caducidad', '2020/03/16'),
(139, 'CULTIVO DE HERIDAS (AEROBIOS)', 'BACTERIOLOGIA', 125, 415, 0, 'Sin fecha de caducidad', '2020/03/16'),
(140, 'CULTIVO NASAL', 'BACTERIOLOGIA', 126, 355, 0, 'Sin fecha de caducidad', '2020/03/16'),
(141, 'COPROCULTIVO', 'BACTERIOLOGIA', 127, 340, 0, 'Sin fecha de caducidad', '2020/03/16'),
(142, 'COPROPARASITOSCOPICO', 'BACTERIOLOGIA', 128, 100, 0, 'Sin fecha de caducidad', '2020/03/16'),
(143, 'COPROPARASITOSCOPICO (3) MUESTRAS', 'BACTERIOLOGIA', 129, 230, 0, 'Sin fecha de caducidad', '2020/03/16'),
(149, 'PRUEBA DE EMBARAZO SUERO', 'Sin categoria', 135, 180, 0, 'Sin fecha de caducidad', '2020/03/16'),
(150, 'PRUEBA DE EMBARAZO ORINA', 'Sin categoria', 135, 100, 0, 'Sin fecha de caducidad', '2020/03/16'),
(151, 'PIEZA PATOLOGICA CHICA', 'Sin categoria', 135, 755, 0, 'Sin fecha de caducidad', '2020/03/16'),
(152, 'PIEZA PATOLOGICA MEDIANA', 'Sin categoria', 135, 865, 0, 'Sin fecha de caducidad', '2020/03/16'),
(153, 'PIEZA PATOLOGICA GRANDE', 'Sin categoria', 135, 975, 0, 'Sin fecha de caducidad', '2020/03/16'),
(154, 'PIEZA PATOLOGICA MUY GRANDE', 'Sin categoria', 135, 1250, 0, 'Sin fecha de caducidad', '2020/03/16'),
(155, 'PAPANICOLAOU', 'Sin categoria', 135, 350, 0, 'Sin fecha de caducidad', '2020/03/16'),
(156, 'HISTOPATOLOGICO DE LILIQUIDO', 'Sin categoria', 135, 550, 0, 'Sin fecha de caducidad', '2020/03/16'),
(157, 'MORFINA', 'Sin categoria', 135, 350, 0, 'Sin fecha de caducidad', '2020/03/16'),
(158, 'BARBITURICOS', 'Sin categoria', 135, 350, 0, 'Sin fecha de caducidad', '2020/03/16'),
(159, 'MARIHUANA', 'Sin categoria', 136, 300, 0, 'Sin fecha de caducidad', '2020/03/16'),
(160, 'ANFETAMINAS', 'Sin categoria', 137, 350, 0, 'Sin fecha de caducidad', '2020/03/16'),
(161, 'COCAINA', 'Sin categoria', 135, 400, 0, 'Sin fecha de caducidad', '2020/03/16'),
(162, 'BENZODIACEPINAS', 'Sin categoria', 135, 350, 0, 'Sin fecha de caducidad', '2020/03/16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id_usuario` int(11) NOT NULL,
  `nombre` char(40) NOT NULL,
  `nombre_usuario` char(40) NOT NULL,
  `apellido_paterno` char(40) DEFAULT NULL,
  `apellido_materno` char(40) DEFAULT NULL,
  `contraseña` char(40) NOT NULL,
  `email` char(40) DEFAULT NULL,
  `RFC` char(40) DEFAULT NULL,
  `Telefono` char(13) DEFAULT NULL,
  `estado_activo_inactivo` char(10) DEFAULT NULL,
  `fecha_y_hora` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id_usuario`, `nombre`, `nombre_usuario`, `apellido_paterno`, `apellido_materno`, `contraseña`, `email`, `RFC`, `Telefono`, `estado_activo_inactivo`, `fecha_y_hora`) VALUES
(3, 'Juarez', 'central', 'juarez', 'juarez', 'central', 'noemail@hotmail.com', 'NO RFC', 'NO NUMBER', 'Activo', '2019-11-26 14:10:22'),
(4, 'Juarez', 'a', 'juarez', 'juarez', 'a', 'noemail@hotmail.com', 'NO RFC', 'NO NUMBER', 'Activo', '2019-11-11 20:20:38');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `id_usuario` int(4) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `subtotal` float DEFAULT NULL,
  `pago` float DEFAULT NULL,
  `cambio` float DEFAULT NULL,
  `estado_venta` varchar(100) DEFAULT NULL,
  `fecha_reporte` varchar(100) DEFAULT NULL,
  `hora` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id_venta`, `id_usuario`, `descuento`, `total`, `subtotal`, `pago`, `cambio`, `estado_venta`, `fecha_reporte`, `hora`) VALUES
(1, 4, 0, 120, 120, 120, 0, 'Realizada', '2020/04/11', NULL),
(2, 4, 0, 240, 240, 2000, 1760, 'Realizada', '2020/04/11', NULL),
(3, 4, 0, 170, 170, 1500, 1330, 'Realizada', '2020/04/11', NULL),
(4, 4, 0, 140, 140, 140, 0, 'Realizada', '2020/04/11', '02:38:21'),
(5, 4, 0, 60, 60, 100, 40, 'Realizada', '2020/04/11', '03:26:44'),
(6, 4, 0, 120, 120, 120, 0, 'Realizada', '2020/04/11', '03:31:21'),
(7, 4, 0, 70, 70, 100, 30, 'Realizada', '2020/04/11', '03:47:54'),
(8, 4, 0, 60, 60, 100, 40, 'Realizada', '2020/04/11', '03:53:01'),
(9, 4, 0, 120, 120, 130, 10, 'Realizada', '2020/04/11', '03:56:01'),
(10, 4, 0, 70, 70, 100, 30, 'Realizada', '2020/04/11', '03:57:46'),
(11, 4, 0, 70, 70, 100, 30, 'Cancelada', '2020/04/11', '03:59:53'),
(12, 4, 0, 70, 70, 100, 30, 'Realizada', '2020/04/11', '04:01:56'),
(13, 4, 20, 50, 70, 60, 10, 'Realizada', '2020/04/11', '04:08:43'),
(14, 4, 0, 120, 120, 130, 10, 'Realizada', '2020/04/11', '04:10:17'),
(15, 4, 0, 70, 70, 1111, 1041, 'Realizada', '2020/04/11', '06:20:26'),
(16, 4, 0, 70, 70, 80, 10, 'Realizada', '2020/04/11', '07:05:56'),
(17, 4, 0, 285, 285, 500, 215, 'Realizada', '2020/04/18', '15:29:55'),
(18, 4, 0, 915, 915, 1000, 85, 'Realizada', '2020/04/18', '18:16:07'),
(19, 4, 0, 885, 885, 2000, 1115, 'Realizada', '2020/04/19', '00:05:47'),
(20, 4, 0, 2120, 2120, 3333, 1213, 'Realizada', '2020/04/20', '03:01:21'),
(21, 4, 0, 130, 130, 500, 370, 'Realizada', '2020/04/20', '18:23:36'),
(22, 4, 0, 5715, 5715, 6000, 285, 'Realizada', '2020/04/20', '00:49:19'),
(23, 4, 0, 2315, 2315, 5888, 3573, 'Realizada', '2020/04/21', '01:02:45'),
(24, 4, 0, 725, 725, 899, 174, 'Realizada', '2020/04/22', '01:14:43'),
(25, 4, 0, 2155, 2155, 5999, 3844, 'Realizada', '2020/04/22', '01:38:07'),
(26, 4, 0, 85, 85, 100, 15, 'Realizada', '2020/04/23', '01:59:14'),
(27, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indices de la tabla `apertura`
--
ALTER TABLE `apertura`
  ADD PRIMARY KEY (`id_apertura`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `cortes`
--
ALTER TABLE `cortes`
  ADD PRIMARY KEY (`id_corte`),
  ADD KEY `id_apertura` (`id_apertura`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `descripcion_de_venta`
--
ALTER TABLE `descripcion_de_venta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_venta` (`id_venta`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_paciente` (`id_paciente`);

--
-- Indices de la tabla `descripcion_estudios`
--
ALTER TABLE `descripcion_estudios`
  ADD PRIMARY KEY (`id_descripcion`);

--
-- Indices de la tabla `egreso`
--
ALTER TABLE `egreso`
  ADD PRIMARY KEY (`idegreso`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id_paciente`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `descripcion_estudio` (`descripcion_estudio`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `apertura`
--
ALTER TABLE `apertura`
  MODIFY `id_apertura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `cortes`
--
ALTER TABLE `cortes`
  MODIFY `id_corte` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `descripcion_de_venta`
--
ALTER TABLE `descripcion_de_venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;
--
-- AUTO_INCREMENT de la tabla `descripcion_estudios`
--
ALTER TABLE `descripcion_estudios`
  MODIFY `id_descripcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=138;
--
-- AUTO_INCREMENT de la tabla `egreso`
--
ALTER TABLE `egreso`
  MODIFY `idegreso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `id_paciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=163;
--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `apertura`
--
ALTER TABLE `apertura`
  ADD CONSTRAINT `apertura_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `user` (`id_usuario`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `cortes`
--
ALTER TABLE `cortes`
  ADD CONSTRAINT `cortes_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `user` (`id_usuario`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `cortes_ibfk_2` FOREIGN KEY (`id_apertura`) REFERENCES `apertura` (`id_apertura`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `descripcion_de_venta`
--
ALTER TABLE `descripcion_de_venta`
  ADD CONSTRAINT `descripcion_de_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`),
  ADD CONSTRAINT `descripcion_de_venta_ibfk_2` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `descripcion_de_venta_ibfk_3` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `egreso`
--
ALTER TABLE `egreso`
  ADD CONSTRAINT `egreso_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `user` (`id_usuario`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`descripcion_estudio`) REFERENCES `descripcion_estudios` (`id_descripcion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `user` (`id_usuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

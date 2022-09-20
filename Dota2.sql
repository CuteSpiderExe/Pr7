-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Сен 20 2022 г., 18:48
-- Версия сервера: 8.0.30
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `Dota2`
--

-- --------------------------------------------------------

--
-- Структура таблицы `atribut`
--

CREATE TABLE `atribut` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `atribut`
--

INSERT INTO `atribut` (`id`, `name`) VALUES
(3, 'Сила'),
(4, 'Ловкость'),
(5, 'Интелект');

-- --------------------------------------------------------

--
-- Структура таблицы `dota`
--

CREATE TABLE `dota` (
  `id` bigint NOT NULL,
  `damage` int NOT NULL,
  `hp` int NOT NULL,
  `mana` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `atribut_id` bigint DEFAULT NULL,
  `items_id` bigint DEFAULT NULL,
  `spos_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `dota`
--

INSERT INTO `dota` (`id`, `damage`, `hp`, `mana`, `name`, `atribut_id`, `items_id`, `spos_id`) VALUES
(17, 50, 500, 350, 'Shadow Fiend', 4, 1, 6),
(18, 45, 450, 500, 'Dazzle', 5, 2, 7);

-- --------------------------------------------------------

--
-- Структура таблицы `game`
--

CREATE TABLE `game` (
  `id` bigint NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `time` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `game`
--

INSERT INTO `game` (`id`, `date`, `name`, `time`) VALUES
(11, '2022', 'Победа', 30),
(12, '2021', 'Поражение', 40);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(21);

-- --------------------------------------------------------

--
-- Структура таблицы `history`
--

CREATE TABLE `history` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `game_id` bigint NOT NULL,
  `player_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Структура таблицы `items`
--

CREATE TABLE `items` (
  `id` bigint NOT NULL,
  `damage` int NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `items`
--

INSERT INTO `items` (`id`, `damage`, `name`) VALUES
(1, 4444, 'Shadow blade'),
(2, 555, 'Desolator');

-- --------------------------------------------------------

--
-- Структура таблицы `mode`
--

CREATE TABLE `mode` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `mode`
--

INSERT INTO `mode` (`id`, `name`) VALUES
(15, 'Turbo'),
(16, 'All pick');

-- --------------------------------------------------------

--
-- Структура таблицы `player`
--

CREATE TABLE `player` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `dota_id` bigint DEFAULT NULL,
  `history_id` bigint DEFAULT NULL,
  `mode_id` bigint DEFAULT NULL,
  `ranks_id` bigint DEFAULT NULL,
  `server_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `player`
--

INSERT INTO `player` (`id`, `name`, `dota_id`, `history_id`, `mode_id`, `ranks_id`, `server_id`) VALUES
(20, 'Joe', 18, NULL, 16, 10, 14);

-- --------------------------------------------------------

--
-- Структура таблицы `ranks`
--

CREATE TABLE `ranks` (
  `id` bigint NOT NULL,
  `kol` int NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `ranks`
--

INSERT INTO `ranks` (`id`, `kol`, `name`) VALUES
(9, 200, 'Страж'),
(10, 1500, 'Мастер');

-- --------------------------------------------------------

--
-- Структура таблицы `server`
--

CREATE TABLE `server` (
  `id` bigint NOT NULL,
  `loc` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `server`
--

INSERT INTO `server` (`id`, `loc`, `name`) VALUES
(13, 'Москва', 'Server1'),
(14, 'Питер', 'Server2');

-- --------------------------------------------------------

--
-- Структура таблицы `spos`
--

CREATE TABLE `spos` (
  `id` bigint NOT NULL,
  `damage` int NOT NULL,
  `mana` int NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `spos`
--

INSERT INTO `spos` (`id`, `damage`, `mana`, `name`) VALUES
(6, 47, 25, 'Shadowraze'),
(7, 500, 200, 'Requem');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `active`, `password`, `username`) VALUES
(1, b'1', '$2a$08$WG1EtUk9wHquKxE5OLpfb.L8Zfuo0kT4nKhfaNVIUNLyKTKp3R/pC', 'Gabe'),
(2, b'1', '$2a$08$iRfucxhS9O0j/x3iS.SLDOViaOETxS97VEQd57kgqanZYTfMcxeM2', 'Joe');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(1, 'ADMIN'),
(2, 'USER');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `atribut`
--
ALTER TABLE `atribut`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `dota`
--
ALTER TABLE `dota`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7etg5rk93p6x4bvpumhlb19m8` (`atribut_id`),
  ADD KEY `FKe8s93fvt64d0ethgd5hcop50s` (`items_id`),
  ADD KEY `FKrseqq1dt3tdmyt28w9e3guqyn` (`spos_id`);

--
-- Индексы таблицы `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjcjtr1d3of2yubxr5amaj8jga` (`game_id`),
  ADD KEY `FKby4rlp5rjvqj8dufw5cwjkp8y` (`player_id`);

--
-- Индексы таблицы `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `mode`
--
ALTER TABLE `mode`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5w1hm102aqlofx2lu0eihq4gq` (`dota_id`),
  ADD KEY `FK6jdw2w7vid8q42thkvl2sl7ve` (`history_id`),
  ADD KEY `FKd23ol6vwa98bcycekq5qd7px5` (`mode_id`),
  ADD KEY `FK72xaogcjs0q32m1uhsrsu8kbx` (`ranks_id`),
  ADD KEY `FKsnd4v30go7h4gk1v30gohyubr` (`server_id`);

--
-- Индексы таблицы `ranks`
--
ALTER TABLE `ranks`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `server`
--
ALTER TABLE `server`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `spos`
--
ALTER TABLE `spos`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `dota`
--
ALTER TABLE `dota`
  ADD CONSTRAINT `FK7etg5rk93p6x4bvpumhlb19m8` FOREIGN KEY (`atribut_id`) REFERENCES `atribut` (`id`),
  ADD CONSTRAINT `FKe8s93fvt64d0ethgd5hcop50s` FOREIGN KEY (`items_id`) REFERENCES `items` (`id`),
  ADD CONSTRAINT `FKrseqq1dt3tdmyt28w9e3guqyn` FOREIGN KEY (`spos_id`) REFERENCES `spos` (`id`);

--
-- Ограничения внешнего ключа таблицы `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `FKby4rlp5rjvqj8dufw5cwjkp8y` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`),
  ADD CONSTRAINT `FKjcjtr1d3of2yubxr5amaj8jga` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`);

--
-- Ограничения внешнего ключа таблицы `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `FK5w1hm102aqlofx2lu0eihq4gq` FOREIGN KEY (`dota_id`) REFERENCES `dota` (`id`),
  ADD CONSTRAINT `FK6jdw2w7vid8q42thkvl2sl7ve` FOREIGN KEY (`history_id`) REFERENCES `history` (`id`),
  ADD CONSTRAINT `FK72xaogcjs0q32m1uhsrsu8kbx` FOREIGN KEY (`ranks_id`) REFERENCES `ranks` (`id`),
  ADD CONSTRAINT `FKd23ol6vwa98bcycekq5qd7px5` FOREIGN KEY (`mode_id`) REFERENCES `mode` (`id`),
  ADD CONSTRAINT `FKsnd4v30go7h4gk1v30gohyubr` FOREIGN KEY (`server_id`) REFERENCES `server` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

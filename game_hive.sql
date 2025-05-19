-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2025 at 05:32 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `game_hive`
--

-- --------------------------------------------------------

--
-- Table structure for table `developers`
--

CREATE TABLE `developers` (
  `developer_id` int(11) NOT NULL,
  `developer` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `developers`
--

INSERT INTO `developers` (`developer_id`, `developer`) VALUES
(1, 'Supercell'),
(2, 'Activision'),
(3, 'Rockstar Games'),
(4, 'miHoYo'),
(5, 'Shanghai Miha Touring Film Technology Co. Ltd.'),
(6, 'Kebal Badal'),
(7, 'Subodh Badal'),
(8, 'Mojang Studios'),
(9, 'Subod badal'),
(10, 'SNK CORPORATION'),
(11, 'CAPCOM Co. Ltd.'),
(12, 'semiwork'),
(13, 'CD PROJEKT RED'),
(14, 'fullwork'),
(15, 'Ronish'),
(16, 'Kebal');

-- --------------------------------------------------------

--
-- Table structure for table `game_developers`
--

CREATE TABLE `game_developers` (
  `game_id` int(11) NOT NULL,
  `developer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `game_developers`
--

INSERT INTO `game_developers` (`game_id`, `developer_id`) VALUES
(11, 4),
(11, 5),
(12, 8),
(14, 10),
(15, 11),
(16, 12),
(16, 14);

-- --------------------------------------------------------

--
-- Table structure for table `game_genres`
--

CREATE TABLE `game_genres` (
  `game_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `game_genres`
--

INSERT INTO `game_genres` (`game_id`, `genre_id`) VALUES
(11, 5),
(11, 6),
(12, 3),
(14, 5),
(15, 5),
(16, 3);

-- --------------------------------------------------------

--
-- Table structure for table `game_information`
--

CREATE TABLE `game_information` (
  `game_id` int(11) NOT NULL,
  `game_title` varchar(50) NOT NULL,
  `game_description` varchar(100) NOT NULL,
  `game_publisher` varchar(50) NOT NULL,
  `game_released_date` date NOT NULL,
  `game_price` int(11) NOT NULL,
  `game_rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `game_information`
--

INSERT INTO `game_information` (`game_id`, `game_title`, `game_description`, `game_publisher`, `game_released_date`, `game_price`, `game_rating`) VALUES
(11, 'Genshin Impact', 'asfaefsdfasdf', 'COGNOSPHERE PTE. LTD.', '2015-09-14', 0, 4),
(12, 'Minecraft', 'sdklfaljkdflasdf', 'Mojang Studios', '2002-12-29', 30, 4),
(14, 'Fatal Fury: City of Wolves', 'High-octane fighting returns with stylish visuals and legendary warriors reborn.', 'SNK CORPORATION', '2025-04-25', 60, 4),
(15, 'Steet Fighter 6', 'Dynamic battles, stunning visuals, and global warriors clash in epic showdowns.', 'CAPCOM Co. Ltd.', '2023-06-01', 35, 5),
(16, 'R.E.P.O.', 'Cyber-noir bounty hunters enforce justice in a dystopian futuristic world.', 'semiwork', '2025-02-26', 5, 3);

-- --------------------------------------------------------

--
-- Table structure for table `game_platforms`
--

CREATE TABLE `game_platforms` (
  `game_id` int(11) NOT NULL,
  `platform_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `game_platforms`
--

INSERT INTO `game_platforms` (`game_id`, `platform_id`) VALUES
(11, 1),
(11, 2),
(12, 2),
(14, 1),
(15, 1),
(16, 1);

-- --------------------------------------------------------

--
-- Table structure for table `game_reviews`
--

CREATE TABLE `game_reviews` (
  `rating_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `review` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `genre_id` int(11) NOT NULL,
  `genre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`genre_id`, `genre`) VALUES
(2, 'Strategy'),
(3, 'Role-Playing'),
(4, 'Simulation'),
(5, 'Action'),
(6, 'Adventure'),
(7, 'Sports'),
(8, 'Racking/Driving'),
(9, 'Puzzle');

-- --------------------------------------------------------

--
-- Table structure for table `platforms`
--

CREATE TABLE `platforms` (
  `platform_id` int(11) NOT NULL,
  `platform` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `platforms`
--

INSERT INTO `platforms` (`platform_id`, `platform`) VALUES
(1, 'PC'),
(2, 'Xbox'),
(3, 'PlayStation'),
(4, 'Nintendo Switch'),
(5, 'Mobile'),
(6, 'VR');

-- --------------------------------------------------------

--
-- Table structure for table `user_information`
--

CREATE TABLE `user_information` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` varchar(20) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_password` varchar(150) NOT NULL,
  `created_date` date NOT NULL,
  `user_role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_information`
--

INSERT INTO `user_information` (`user_id`, `username`, `date_of_birth`, `gender`, `user_email`, `user_password`, `created_date`, `user_role`) VALUES
(12, 'dog', '2004-12-26', 'male', 'dog@gmail.com', 'ukEZYORheHYkxhPK5k0RkTnZ5ErPbQKnacoRgWOoWuyXmuL9jW0/1JpaoLkTh+/Adftp', '2025-04-29', 'gamer'),
(13, 'cat', '2006-01-01', 'male', 'cat@gmail.com', 'G/dYTjkqevTrYUj9+TCH8vyZqaw4azP+hezemtS49NMu2gdgDpBoLgD5sCy44+DpmQRU', '2025-04-29', 'gamer'),
(15, 'fish', '2006-01-01', 'male', 'fish@gmail.com', 'ZFiIVHcv4so8BuYxP0At8MLxNfimbGOsNABHOzICOu74VXdzfNMyTwsBn2LYv0ncKnvxVg==', '2025-04-29', 'gamer'),
(16, 'Tiger', '2005-04-01', 'male', 'tiger@gmail.com', 'nlU+K2YmfDN7KHBrSjlTZkeVGoN6re5IuLUbVQmYtxIGFrNCN1ymkYpWsvw=', '2025-04-30', 'admin'),
(17, 'lion', '2006-01-01', 'female', 'lion@gmail.com', 'yf0HBOJ2dnF2FfIKp25250t0v1eF0VcsgQ6h7JFGMx1kfGNSFwIUFG6KEngQkah79kk7kw==', '2025-04-30', 'admin'),
(18, 'ronish', '2006-01-06', 'male', 'ronishprajapati50@gmail.com', 'HtV0FXZ6Erv8gW9I4M3zkRo8evFY3pjLveNu0fIATFyH4ZK3uj1ItmphoqS9IWUwPBlL68b7', '2025-05-06', 'gamer'),
(19, 'monkey', '2008-01-18', 'male', 'chimp@gmail.com', '97mftg6MquYTI65I9E0XWmW8CiBlAxj+OWERSgG9oYV9A5iG3ZVukEQb6TAOaZxrB2E3lQ==', '2025-05-07', 'admin'),
(22, 'smallMonkey', '2009-01-16', 'male', 'smallMonkey@gmail.com', 'wkKF0sNNLXwn8oPMspxsMkb9FZIz/ElLu6F7yQ221eA2PpAzN6ufLgY6Qf4+vSCPT84LtWw=', '2025-05-16', 'gamer'),
(23, 'nobita', '2007-01-08', 'male', 'sizuka@123', '1GFD1oYOFvfVreycqchFuVXt+y/wrUoTPsZnq2gIDmvqHfNEI4+ZmBDeKZ2hYLs7rhQjihNS', '2025-05-16', 'gamer'),
(24, 'davidSon', '2012-01-02', 'male', 'david@gmail.com', 's/Suv31HOjyAGT8m81eItIBwdMTtXS9UVIX+NS7Nk++n4Oxf0wm2PMT2mP8FiPB7zFUgO50=', '2025-05-18', 'gamer'),
(25, 'kebal', '2004-06-05', 'male', 'kebal@gmail.com', 'R+M3jxrQ/5lk7D4zXw5H2S+GZvbSg/9QlW2KDDn0P4GJROzosdFQwVAxJR+XNKZ81GNz', '2025-05-18', 'gamer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `developers`
--
ALTER TABLE `developers`
  ADD PRIMARY KEY (`developer_id`);

--
-- Indexes for table `game_developers`
--
ALTER TABLE `game_developers`
  ADD PRIMARY KEY (`game_id`,`developer_id`),
  ADD KEY `developer_id` (`developer_id`);

--
-- Indexes for table `game_genres`
--
ALTER TABLE `game_genres`
  ADD PRIMARY KEY (`game_id`,`genre_id`),
  ADD KEY `genreid` (`genre_id`);

--
-- Indexes for table `game_information`
--
ALTER TABLE `game_information`
  ADD PRIMARY KEY (`game_id`),
  ADD UNIQUE KEY `game_title` (`game_title`);

--
-- Indexes for table `game_platforms`
--
ALTER TABLE `game_platforms`
  ADD PRIMARY KEY (`game_id`,`platform_id`),
  ADD KEY `platform_id` (`platform_id`);

--
-- Indexes for table `game_reviews`
--
ALTER TABLE `game_reviews`
  ADD PRIMARY KEY (`rating_id`,`game_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `game_id` (`game_id`);

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`genre_id`);

--
-- Indexes for table `platforms`
--
ALTER TABLE `platforms`
  ADD PRIMARY KEY (`platform_id`);

--
-- Indexes for table `user_information`
--
ALTER TABLE `user_information`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `user_email` (`user_email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `developers`
--
ALTER TABLE `developers`
  MODIFY `developer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `game_information`
--
ALTER TABLE `game_information`
  MODIFY `game_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `game_reviews`
--
ALTER TABLE `game_reviews`
  MODIFY `rating_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
  MODIFY `genre_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `platforms`
--
ALTER TABLE `platforms`
  MODIFY `platform_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_information`
--
ALTER TABLE `user_information`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `game_developers`
--
ALTER TABLE `game_developers`
  ADD CONSTRAINT `developer_id` FOREIGN KEY (`developer_id`) REFERENCES `developers` (`developer_id`),
  ADD CONSTRAINT `gamId` FOREIGN KEY (`game_id`) REFERENCES `game_information` (`game_id`) ON DELETE CASCADE;

--
-- Constraints for table `game_genres`
--
ALTER TABLE `game_genres`
  ADD CONSTRAINT `gameid` FOREIGN KEY (`game_id`) REFERENCES `game_information` (`game_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `genreid` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`);

--
-- Constraints for table `game_platforms`
--
ALTER TABLE `game_platforms`
  ADD CONSTRAINT `gam_id` FOREIGN KEY (`game_id`) REFERENCES `game_information` (`game_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `platform_id` FOREIGN KEY (`platform_id`) REFERENCES `platforms` (`platform_id`);

--
-- Constraints for table `game_reviews`
--
ALTER TABLE `game_reviews`
  ADD CONSTRAINT `game_id` FOREIGN KEY (`game_id`) REFERENCES `game_information` (`game_id`),
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user_information` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) NOT NULL,
  `status` varchar(16) NOT NULL,
  `author` varchar(50) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `url` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `ip` varchar(15) NOT NULL,
  `useragent` varchar(255) NOT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `meta` (
  `meta_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `slug` varchar(200) NOT NULL,
  `type` varchar(32) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `count` bigint(20) NOT NULL,
  PRIMARY KEY (`meta_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `option` (
  `key` varchar(50) NOT NULL,
  `value` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `option` (`key`, `value`) VALUES
('title', 'XPress'),
('keywords', ''),
('description', ''),
('navbar', '<a href="/" class="site-nav">\n    首页\n</a>\n<a href="/categories" class="site-nav">\n    分类\n</a>\n<a href="/tags" class="site-nav">\n    标签\n</a>'),
('siteURL', ''),
('logo', 'http://www.gravatar.com/avatar/4b2f5068ab4a9ba3ced4cc50acb43721');

CREATE TABLE IF NOT EXISTS `post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(16) NOT NULL,
  `type` varchar(16) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `comments_count` int(10) NOT NULL,
  `is_allow_comments` tinyint(4) NOT NULL,
  `created` int(10) NOT NULL,
  `modified` int(10) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `slug` (`slug`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `relationship` (
  `post_id` bigint(20) NOT NULL,
  `meta_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `upload` (
  `upload_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `mimetype` varchar(50) NOT NULL,
  `size` bigint(20) NOT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `url` varchar(100) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (`user_id`, `username`, `password`, `mail`, `url`, `nickname`, `created`) VALUES
(1, 'admin', '7fef6171469e80d32c0559f88b377245', 'admin@admin.com', 'https://www.domain.com/', 'XPress', 1562746093);

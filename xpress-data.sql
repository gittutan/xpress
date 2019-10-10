SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `meta`
--

CREATE TABLE IF NOT EXISTS `meta` (
  `meta_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `slug` varchar(200) NOT NULL,
  `type` varchar(32) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `count` bigint(20) NOT NULL,
  PRIMARY KEY (`meta_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `meta`
--

INSERT INTO `meta` (`meta_id`, `name`, `slug`, `type`, `description`, `count`) VALUES
(1, '分类1', 'category-1', 'category', '分类1', 2),
(2, '分类2', 'category-2', 'category', '分类2', 2),
(3, '标签1', 'tag-1', 'tag', '标签1', 4),
(4, '标签2', 'tag-2', 'tag', '标签2', 4);

-- --------------------------------------------------------

--
-- 表的结构 `option`
--

CREATE TABLE IF NOT EXISTS `option` (
  `key` varchar(50) NOT NULL,
  `value` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `option`
--

INSERT INTO `option` (`key`, `value`) VALUES
('title', 'XPress'),
('keywords', ''),
('description', ''),
('navbar', '<a href="/" class="site-nav">\n    首页\n</a>\n<a href="/categories" class="site-nav">\n    分类\n</a>\n<a href="/tags" class="site-nav">\n    标签\n</a>\n<a href="/about" class="site-nav">\n    关于\n</a>'),
('siteURL', ''),
('logo', 'http://www.gravatar.com/avatar/4b2f5068ab4a9ba3ced4cc50acb43721');

-- --------------------------------------------------------

--
-- 表的结构 `post`
--

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
  KEY `slug` (`slug`(191))
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `post`
--

INSERT INTO `post` (`post_id`, `status`, `type`, `author_id`, `category_id`, `title`, `content`, `slug`, `comments_count`, `is_allow_comments`, `created`, `modified`) VALUES
(1, 'publish', 'post', 1, 1, '测试文章1', '测试文章1', 'test-post-1', 0, 1, 1570676878, 1570676878),
(2, 'publish', 'post', 1, 1, '测试文章2', '测试文章2', 'test-post-2', 0, 1, 1570676910, 1570676910),
(3, 'publish', 'post', 1, 2, '测试文章3', '测试文章3', 'test-post-3', 0, 1, 1570676939, 1570676939),
(4, 'publish', 'post', 1, 2, '测试文章4', '测试文章4', 'test-post-4', 0, 1, 1570676965, 1570676965),
(5, 'publish', 'page', 1, NULL, '关于我', '关于我', 'about', 0, 1, 1570676991, 1570676991);

-- --------------------------------------------------------

--
-- 表的结构 `relationship`
--

CREATE TABLE IF NOT EXISTS `relationship` (
  `post_id` bigint(20) NOT NULL,
  `meta_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `relationship`
--

INSERT INTO `relationship` (`post_id`, `meta_id`) VALUES
(1, 3),
(1, 4),
(2, 3),
(2, 4),
(3, 3),
(3, 4),
(4, 3),
(4, 4);

-- --------------------------------------------------------

--
-- 表的结构 `upload`
--

CREATE TABLE IF NOT EXISTS `upload` (
  `upload_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `mimetype` varchar(50) NOT NULL,
  `size` bigint(20) NOT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `mail`, `url`, `nickname`, `created`) VALUES
(1, 'admin', '7fef6171469e80d32c0559f88b377245', 'admin@admin.com', 'https://www.domain.com/', 'XPress', 1562746093);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

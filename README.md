# fyp-website-backend

This is the back end structure for a general-use website. The project is implemented by [Spring Boot](https://spring.io/projects/spring-boot) and [MyBatis](https://mybatis.org/mybatis-3/). This project is not stand-alone. It is built for another [front-end project](https://github.com/jzhzhz/fyp-web-frontend).

## Functionalities

- Database record update & retrieval
- Image upload & download
- Database records download as zip file
- Excel download and upload to update database record
- Log file generating

Detailed database settings are listed down below. Functionalities above are built for corresponding [front-end project](https://github.com/jzhzhz/fyp-web-frontend). See the full dependencies in the [pom file](https://github.com/jzhzhz/fyp-website-backend/blob/master/pom.xml).


## Database Settings

DDL for creating table `admins`

```sql
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `password` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

DDL for creating table `cards`

```sql
CREATE TABLE `cards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(600) NOT NULL DEFAULT '',
  `text` varchar(600) NOT NULL DEFAULT '',
  `date` varchar(45) NOT NULL DEFAULT '',
  `url` varchar(45) NOT NULL DEFAULT '',
  `img_name` varchar(45) NOT NULL DEFAULT '',
  `img_url` varchar(100) NOT NULL DEFAULT '',
  `deprecated` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='information cards in the home page'
```

DDL for creating table `faculty`

```sql
CREATE TABLE `faculty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `username` varchar(45) NOT NULL DEFAULT '',
  `phone` varchar(45) NOT NULL DEFAULT '',
  `office` varchar(45) NOT NULL DEFAULT '',
  `email` varchar(45) NOT NULL DEFAULT '',
  `url` varchar(45) NOT NULL DEFAULT '',
  `type` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='faculty record in the different people pages'
```

DDL for creating table `home_events`

```sql
CREATE TABLE `home_events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL DEFAULT '',
  `subtitle` varchar(45) NOT NULL DEFAULT '' COMMENT 'usually to store date information',
  `content` varchar(500) NOT NULL DEFAULT '',
  `url` varchar(45) NOT NULL DEFAULT '',
  `deprecated` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='events blocks in the home page'
```

DDL for creating table `home_textblock`

```sql
CREATE TABLE `home_textblock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL DEFAULT '',
  `content` varchar(5000) NOT NULL DEFAULT '',
  `url` varchar(10000) NOT NULL DEFAULT '',
  `type` varchar(45) NOT NULL DEFAULT '',
  `deprecated` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='several textblocks that belongs to the home page'
```

DDL for creating table `navbar_labels`

```sql
CREATE TABLE `navbar_labels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(45) NOT NULL DEFAULT '',
  `url` varchar(45) NOT NULL DEFAULT '',
  `code_content` mediumtext NOT NULL,
  `deprecated` int(11) NOT NULL DEFAULT '0',
  `type` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='navbar label and its html code'
```

DDL for creating table `profile_card_block`

```sql
CREATE TABLE `profile_card_block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL DEFAULT '',
  `title` varchar(100) NOT NULL DEFAULT '',
  `subtitle` varchar(45) NOT NULL DEFAULT '' COMMENT 'usually to store the authors',
  `text` varchar(500) NOT NULL DEFAULT '',
  `url` varchar(100) NOT NULL DEFAULT '',
  `img_name` varchar(45) NOT NULL DEFAULT '',
  `img_url` varchar(100) NOT NULL DEFAULT '',
  `type` varchar(45) NOT NULL DEFAULT 'publication',
  `deprecated` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='the publication card template record in faculty profile page'
```

DDL for creating table `profile_custom_block`

```sql
CREATE TABLE `profile_custom_block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL DEFAULT '' COMMENT 'refer to the username in the faculty table',
  `date_bar` varchar(45) NOT NULL DEFAULT '' COMMENT 'side bar that usually stores the date information',
  `code_segment` varchar(5000) NOT NULL DEFAULT '' COMMENT 'html code segment in the text block',
  `type` varchar(45) NOT NULL DEFAULT 'news' COMMENT 'profile information type: news or publication',
  `deprecated` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='customized information block in the profile page'
```

DDL for creating creating table `profile_general`

```sql
CREATE TABLE `profile_general` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL DEFAULT '' COMMENT 'refer to the username in the faculty table',
  `intro` varchar(5000) NOT NULL DEFAULT '' COMMENT 'top right introduction information in the profile page',
  `sidebar` varchar(5000) NOT NULL DEFAULT '' COMMENT 'sidebar information in profile page',
  `img_name` varchar(45) NOT NULL DEFAULT '',
  `img_url` varchar(45) NOT NULL DEFAULT '' COMMENT 'image url of the profile photo',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='store the general information in a faculty profile'
```


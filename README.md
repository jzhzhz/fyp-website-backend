# fyp-website-backend

This is the back end structure for general website. The project is implemented by [spring boot](https://spring.io/projects/spring-boot).

## Database Settings

SQL for creating table `admins`

```sql
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `password` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

SQL for creating table `cards`

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

SQL for creating `table faculty`

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

SQL for creating table `home_textblock`

```sql
CREATE TABLE `home_textblock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL DEFAULT '',
  `content` varchar(15000) NOT NULL DEFAULT '',
  `url` varchar(45) NOT NULL DEFAULT '',
  `type` varchar(45) NOT NULL DEFAULT '',
  `deprecated` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='a block of content that belongs to the home page'
```

SQL for creating table `navbar_labels`

```sql
CREATE TABLE `navbar_labels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(45) NOT NULL DEFAULT '',
  `url` varchar(45) NOT NULL DEFAULT '',
  `code_content` mediumtext NOT NULL,
  `deprecated` int(11) NOT NULL DEFAULT '0',
  `type` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

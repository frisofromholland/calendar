CREATE DATABASE `calendar` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `person` (
  `id`            INT(11)      NOT NULL AUTO_INCREMENT,
  `first_name`    VARCHAR(255) NOT NULL,
  `last_name`     VARCHAR(255) NOT NULL,
  `date_of_birth` DATE         NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `role` (
  `id`   INT(11)     NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `user` (
  `id`        INT(11)     NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(20) NOT NULL DEFAULT '',
  `password`  VARCHAR(20) NOT NULL DEFAULT '',
  `enabled`   TINYINT(1)  NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `user_role` (
  `id`      INT(11) NOT NULL AUTO_INCREMENT,
  `role_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)

)


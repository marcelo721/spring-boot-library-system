-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema database_library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema database_library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `database_library` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `database_library` ;

-- -----------------------------------------------------
-- Table `database_library`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database_library`.`authors` (
                                                            `birth_date` DATETIME(6) NULL DEFAULT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    `nationality` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `database_library`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database_library`.`books` (
                                                          `available` BIT(1) NULL DEFAULT NULL,
    `author_id` BIGINT NULL DEFAULT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `isbn` VARCHAR(255) NULL DEFAULT NULL,
    `title` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKfjixh2vym2cvfj3ufxj91jem7` (`author_id` ASC) VISIBLE,
    CONSTRAINT `FKfjixh2vym2cvfj3ufxj91jem7`
    FOREIGN KEY (`author_id`)
    REFERENCES `database_library`.`authors` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `database_library`.`categorys`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database_library`.`categorys` (
                                                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                              `description` VARCHAR(255) NULL DEFAULT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `database_library`.`book_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database_library`.`book_category` (
                                                                  `book_id` BIGINT NOT NULL,
                                                                  `category_id` BIGINT NOT NULL,
                                                                  INDEX `FK7k0c5mr0rx89i8jy5ges23jpe` (`book_id` ASC) VISIBLE,
    INDEX `FKbhblqffvoenjqdkwpdg7uaym6` (`category_id` ASC) VISIBLE,
    CONSTRAINT `FK7k0c5mr0rx89i8jy5ges23jpe`
    FOREIGN KEY (`book_id`)
    REFERENCES `database_library`.`books` (`id`),
    CONSTRAINT `FKbhblqffvoenjqdkwpdg7uaym6`
    FOREIGN KEY (`category_id`)
    REFERENCES `database_library`.`categorys`
(`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `database_library`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database_library`.`users` (
                                                          `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                          `email` VARCHAR(255) NULL DEFAULT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    `password` VARCHAR(255) NULL DEFAULT NULL,
    `role` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `database_library`.`loans`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database_library`.`loans` (
                                                          `book_id` BIGINT NULL DEFAULT NULL,
                                                          `end_date` DATETIME(6) NULL DEFAULT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `start_date` DATETIME(6) NULL DEFAULT NULL,
    `user_id` BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_7t9p42ubdqp606tp4whc233lt` (`book_id` ASC) VISIBLE,
    INDEX `FK6xxlcjc0rqtn5nq28vjnx5t9d` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FK6xxlcjc0rqtn5nq28vjnx5t9d`
    FOREIGN KEY (`user_id`)
    REFERENCES `database_library`.`users` (`id`),
    CONSTRAINT `FKokwvlrv6o4i4h3le3bwhe6kie`
    FOREIGN KEY (`book_id`)
    REFERENCES `database_library`.`books` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

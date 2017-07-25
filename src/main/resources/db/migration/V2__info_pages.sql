-- -----------------------------------------------------
-- Table `cwbusinesservices`.`INFO_PAGES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`INFO_PAGES` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `URL` VARCHAR(150) NOT NULL COMMENT '',
  `HEADER` TEXT NOT NULL COMMENT '',
  `SUB_HEADER` TEXT NULL COMMENT '',
  `TEXT` TEXT NULL COMMENT '',
  `STATUS` TINYINT NOT NULL DEFAULT 0 COMMENT '',
  `META_TITLE` TEXT NULL COMMENT '',
  `META_DESCRIPTION` TEXT NULL COMMENT '',
  `META_KEYWORDS` TEXT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  UNIQUE INDEX `URL_UNIQUE` (`URL` ASC)  COMMENT '',
  FULLTEXT INDEX `URL` (`URL` ASC)  COMMENT '')
  ENGINE = InnoDB;

INSERT INTO `permissions` (`NAME`) VALUES ('CREATE_INFO_PAGE');
INSERT INTO `permissions` (`NAME`) VALUES ('EDIT_INFO_PAGE');
INSERT INTO `permissions` (`NAME`) VALUES ('DELETE_INFO_PAGE');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '4');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '5');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '6');
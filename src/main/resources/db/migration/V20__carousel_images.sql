-- -----------------------------------------------------
-- Table `cwbusinesservices`.`EMPLOYEES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`CAROUSEL_IMAGES` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `DESCRIPTION` TEXT NULL COMMENT '',
  `POSITION` INT NOT NULL DEFAULT 0,
  `HAS_ICON` TINYINT NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '')
  ENGINE = InnoDB;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_CAROUSEL_IMAGE');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_CAROUSEL_IMAGE');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_CAROUSEL_IMAGE');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '54');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '55');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '56');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '54');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '55');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '56');
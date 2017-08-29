-- -----------------------------------------------------
-- Table `cwbusinesservices`.`EMPLOYEES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`EMPLOYEES` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `NAME` VARCHAR(300) NOT NULL COMMENT '',
  `POSITION` VARCHAR(500) NOT NULL COMMENT '',
  `EMAIL` VARCHAR(150) NULL COMMENT '',
  `PHONE` VARCHAR(45) NULL COMMENT '',
  `DESCRIPTION` TEXT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '')
  ENGINE = InnoDB;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_EMPLOYEE');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_EMPLOYEE');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_EMPLOYEE');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '51');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '52');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '53');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '51');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '52');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '53');
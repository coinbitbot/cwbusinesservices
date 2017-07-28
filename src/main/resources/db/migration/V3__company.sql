-- -----------------------------------------------------
-- Table `cwbusinesservices`.`COMPANY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`COMPANY` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `NAME` TEXT NOT NULL COMMENT '',
  `HAS_LOGO` TINYINT NOT NULL DEFAULT 0 COMMENT '',
  `TEXT` TEXT NULL COMMENT '',
  `STATUS` TINYINT NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '')
  ENGINE = InnoDB;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_COMPANY');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_COMPANY');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_COMPANY');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '7');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '8');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '9');
-- -----------------------------------------------------
-- Table `cwbusinesservices`.`SERVICE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`SERVICE` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `HAS_ICON` TINYINT NOT NULL DEFAULT 0 COMMENT '',
  `STATUS` TINYINT NOT NULL DEFAULT 0 COMMENT '',
  `NAME` TEXT NOT NULL COMMENT '',
  `DESCRIPTION` TEXT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '')
ENGINE = InnoDB;

INSERT INTO `permissions` (`NAME`) VALUES ('CREATE_SERVICE');
INSERT INTO `permissions` (`NAME`) VALUES ('EDIT_SERVICE');
INSERT INTO `permissions` (`NAME`) VALUES ('DELETE_SERVICE');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '10');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '11');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '12');
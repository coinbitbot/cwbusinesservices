-- -----------------------------------------------------
-- Table `cwbusinesservices`.`INTERESTED_IN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`INTERESTED_IN` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `NAME` VARCHAR(350) NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cwbusinesservices`.`INDUSTRY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`INDUSTRY` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `NAME` VARCHAR(350) NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC)  COMMENT '')
ENGINE = InnoDB;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_INTEREST');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_INTEREST');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_INTEREST');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '17');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '18');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '19');

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_INDUSTRY');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_INDUSTRY');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_INDUSTRY');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '20');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '21');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '22');
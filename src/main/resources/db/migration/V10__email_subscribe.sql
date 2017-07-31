-- -----------------------------------------------------
-- Table `cwbusinesservices`.`EMAIL_SUBSCRIBE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`EMAIL_SUBSCRIBE` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `EMAIL` VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC)  COMMENT '')
  ENGINE = InnoDB;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_EMAIL_SUBSCRIBE');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_EMAIL_SUBSCRIBE');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_EMAIL_SUBSCRIBE');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '33');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '34');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '35');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '33');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '34');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '35');
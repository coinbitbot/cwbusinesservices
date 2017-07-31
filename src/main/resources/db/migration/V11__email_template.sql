-- -----------------------------------------------------
-- Table `cwbusinesservices`.`EMAIL_TEMPLATE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`EMAIL_TEMPLATE` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `CODE` VARCHAR(100) NOT NULL COMMENT '',
  `TEXT` TEXT NOT NULL COMMENT '',
  `SUBJECT` VARCHAR(350) NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  UNIQUE INDEX `CODE_UNIQUE` (`CODE` ASC)  COMMENT '')
  ENGINE = InnoDB;

INSERT INTO `EMAIL_TEMPLATE` (`CODE`,`TEXT`,`SUBJECT`) VALUES ('NEW_USER_REGISTER','You have registered on {{url}}','You have registered');
INSERT INTO `EMAIL_TEMPLATE` (`CODE`,`TEXT`,`SUBJECT`) VALUES ('EMAIL_SUBSCRIPTION','You have subscribed to news from {{name}}','You have subscribed on news');
INSERT INTO `EMAIL_TEMPLATE` (`CODE`,`TEXT`,`SUBJECT`) VALUES ('NEW_REQUEST_IN_SYSTEM','We have new request please use admin part to answer user','We have new request');

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_EMAIL_TEMPLATE');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_EMAIL_TEMPLATE');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_EMAIL_TEMPLATE');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '37');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '37');
-- -----------------------------------------------------
-- Table `cwbusinesservices`.`REQUEST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`REQUEST` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `USER_ID` INT NOT NULL COMMENT '',
  `INDUSTRY_ID` INT NOT NULL COMMENT '',
  `INTEREST_ALTER` TEXT NULL COMMENT '',
  `COMPANY_NAME` TEXT NULL COMMENT '',
  `HAS_BUSINESS_PLAN` TINYINT NOT NULL DEFAULT 0 COMMENT '',
  `STATUS` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  INDEX `fk_REQUEST_TO_INDUSTRY_idx` (`INDUSTRY_ID` ASC)  COMMENT '',
  INDEX `fk_REQUEST_TO_USER_idx` (`USER_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_REQUEST_TO_INDUSTRY`
  FOREIGN KEY (`INDUSTRY_ID`)
  REFERENCES `cwbusinesservices`.`INDUSTRY` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_REQUEST_TO_USER`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `cwbusinesservices`.`USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cwbusinesservices`.`INTEREST_TO_REQUEST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`INTEREST_TO_REQUEST` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `INTERESTED_IN_ID` INT NOT NULL COMMENT '',
  `REQUEST_ID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  INDEX `fk_REQUEST_TO_INTEREST_idx` (`INTERESTED_IN_ID` ASC)  COMMENT '',
  INDEX `fk_INTERESTED_TO_REQUEST_idx` (`REQUEST_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_REQUEST_TO_INTEREST`
  FOREIGN KEY (`INTERESTED_IN_ID`)
  REFERENCES `cwbusinesservices`.`INTERESTED_IN` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_INTERESTED_TO_REQUEST`
  FOREIGN KEY (`REQUEST_ID`)
  REFERENCES `cwbusinesservices`.`REQUEST` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cwbusinesservices`.`REQUEST_COMMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`REQUEST_COMMENT` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `REQUEST_ID` INT NOT NULL COMMENT '',
  `USER_ID` INT NOT NULL COMMENT '',
  `TEXT` TEXT NULL COMMENT '',
  `HAS_FILE` TINYINT NOT NULL DEFAULT 0 COMMENT '',
  `DATE_CREATED` DATETIME NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  INDEX `fk_COMMENT_TO_REQUEST_idx` (`REQUEST_ID` ASC)  COMMENT '',
  INDEX `fk_COMMENT_TO_USER_idx` (`USER_ID` ASC)  COMMENT '',
  CONSTRAINT `fk_COMMENT_TO_REQUEST`
  FOREIGN KEY (`REQUEST_ID`)
  REFERENCES `cwbusinesservices`.`REQUEST` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COMMENT_TO_USER`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `cwbusinesservices`.`USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_REQUEST');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_REQUEST');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_REQUEST');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('VIEW_REQUEST');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('STATUS_CHANGE_REQUEST');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('1', '23');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('1', '24');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('1', '25');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '26');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '26');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '27');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '27');

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_REQUEST_COMMENT');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_REQUEST_COMMENT');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_REQUEST_COMMENT');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('VIEW_REQUEST_COMMENT');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('1', '28');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('1', '29');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('1', '30');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '28');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '29');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '30');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '31');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '28');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '29');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '30');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '31');


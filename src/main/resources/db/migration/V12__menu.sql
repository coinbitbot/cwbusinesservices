-- -----------------------------------------------------
-- Table `cwbusinesservices`.`MENU`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`MENU` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` TEXT NULL,
  `CODE` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC),
  UNIQUE INDEX `CODE_UNIQUE` (`CODE` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cwbusinesservices`.`MENU_ITEM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`MENU_ITEM` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` TEXT NOT NULL,
  `MENU_ID` INT NULL,
  `URL` TEXT NULL,
  `POSITION` INT NOT NULL DEFAULT 0,
  `PARENT_MENU_ITEM_ID` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_MENU_ITEM_TO_MENU_idx` (`MENU_ID` ASC),
  CONSTRAINT `fk_MENU_ITEM_TO_MENU`
    FOREIGN KEY (`MENU_ID`)
    REFERENCES `cwbusinesservices`.`MENU` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_MENU_ITEM');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_MENU_ITEM');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_MENU_ITEM');

INSERT INTO `MENU` (`NAME`,`CODE`) VALUES ('Main menu', 'MAIN');
INSERT INTO `MENU` (`NAME`,`CODE`) VALUES ('Bottom menu', 'BOTTOM');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '39');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '40');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '41');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '39');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '40');
insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('2', '41');

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_MENU');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_MENU');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_MENU');



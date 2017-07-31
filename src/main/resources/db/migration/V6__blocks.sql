-- -----------------------------------------------------
-- Table `cwbusinesservices`.`BLOCKS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`BLOCKS` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT 'In this table will be collected blocks titles',
  `CODE` VARCHAR(45) NOT NULL COMMENT '',
  `TITLE` TEXT NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  UNIQUE INDEX `CODE_UNIQUE` (`CODE` ASC)  COMMENT '')
ENGINE = InnoDB;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_BLOCK');

insert into `ROLE_PERMISSIONS` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '16');

INSERT INTO `BLOCKS` (`CODE`,`TITLE`) VALUES ('SERVICES', 'Overview of the services');
INSERT INTO `BLOCKS` (`CODE`,`TITLE`) VALUES ('COMPANIES', 'Logos of investee companies');
INSERT INTO `BLOCKS` (`CODE`,`TITLE`) VALUES ('TESTIMONIALS', 'Testimonials');
-- -----------------------------------------------------
-- Table `cwbusinesservices`.`TESTIMONIAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`TESTIMONIAL` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `TEXT` TEXT NOT NULL COMMENT '',
  `NAME` VARCHAR(250) NOT NULL COMMENT '',
  `POSITION` VARCHAR(300) NULL COMMENT '',
  `STATUS` TINYINT NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '')
ENGINE = InnoDB;

INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('CREATE_TESTIMONIAL');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('EDIT_TESTIMONIAL');
INSERT INTO `PERMISSIONS` (`NAME`) VALUES ('DELETE_TESTIMONIAL');

insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '13');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '14');
insert into `ROLE_permissions` (`ROLE_ID`, `PERMISSION_ID`) values ('3', '15');
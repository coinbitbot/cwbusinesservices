-- -----------------------------------------------------
-- Table `cwbusinesservices`.`BLOG_CATEGORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`BLOG_CATEGORY` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(400) NOT NULL,
  `POSITION` INT NOT NULL,
  `CODE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `CODE_UNIQUE` (`CODE` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cwbusinesservices`.`POST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cwbusinesservices`.`POST` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TITLE` TEXT NOT NULL,
  `URL` VARCHAR(150) NOT NULL,
  `SHORT_DESCRIPTION` TEXT NULL,
  `DATE_OF_PUBLICATION` DATE NOT NULL,
  `HAS_IMG` TINYINT NOT NULL DEFAULT 0,
  `META_TITLE` TEXT NULL,
  `META_DESCRIPTION` TEXT NULL,
  `META_KEYWORDS` TEXT NULL,
  `BLOG_CATEGORY_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `URL_UNIQUE` (`URL` ASC),
  INDEX `fk_POST_TO_BLOG_CATEGORY_idx` (`BLOG_CATEGORY_ID` ASC),
  CONSTRAINT `fk_POST_TO_BLOG_CATEGORY`
    FOREIGN KEY (`BLOG_CATEGORY_ID`)
    REFERENCES `cwbusinesservices`.`BLOG_CATEGORY` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
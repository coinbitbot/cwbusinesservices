ALTER TABLE `cwbusinesservices`.`SERVICE`
  ADD COLUMN `POSITION` INT DEFAULT 0;
UPDATE `cwbusinesservices`.`SERVICE` SET `POSITION`=`ID`;

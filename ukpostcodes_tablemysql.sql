
1. CREATE SCHEMA `postal` ;

2. CREATE TABLE `postal`.`postaloutcode` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `postcode` VARCHAR(8) NOT NULL,
  `latitude` DECIMAL(12,9) NULL,
  `longitude` DECIMAL(12,9) NULL,
  PRIMARY KEY (`id`));
  
  select * from postal.postaloutcode;
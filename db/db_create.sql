SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `EBTC` ;
CREATE SCHEMA IF NOT EXISTS `EBTC` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `EBTC` ;

-- -----------------------------------------------------
-- Table `EBTC`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`user` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `username` VARCHAR(30) NOT NULL COMMENT '用户名' ,
  `password` VARCHAR(32) NOT NULL COMMENT '密码' ,
  `payment_code` VARCHAR(32) NOT NULL COMMENT '支付密码' ,
  `nickname` VARCHAR(20) NOT NULL COMMENT '昵称' ,
  `mobile` VARCHAR(20) NOT NULL COMMENT '手机号码' ,
  `email` VARCHAR(45) NOT NULL COMMENT '邮箱' ,
  `state` VARCHAR(2) NOT NULL COMMENT '状态 见Constants' ,
  `type` VARCHAR(2) NOT NULL COMMENT '类型 见 Constants' ,
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间' ,
  `create_user` VARCHAR(10) NULL ,
  `last_login_time` TIMESTAMP NULL COMMENT '最后登陆时间' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`CNY_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`CNY_account` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`CNY_account` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `user_id` INT NOT NULL COMMENT '外键 用户id' ,
  `balance` DECIMAL(20,8) NOT NULL COMMENT '人名币余额(总额)=可用额度+冻结额度' ,
  `freeze` DECIMAL(20,8) NOT NULL COMMENT '冻结人民币额度' ,
  `state` VARCHAR(2) NOT NULL COMMENT '状态 见 constants' ,
  `remark` VARCHAR(50) NULL COMMENT '备注 50' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_account_user_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_account_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `EBTC`.`user` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`BTC_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`BTC_account` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`BTC_account` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `user_id` INT NOT NULL COMMENT '外键' ,
  `balance` DECIMAL(20,8) NOT NULL COMMENT '余额(总额) = 可用额度+冻结额度' ,
  `freeze` DECIMAL(20,8) NOT NULL COMMENT '冻结额度' ,
  `purse_addr` VARCHAR(34) NULL COMMENT '钱包地址' ,
  `state` VARCHAR(2) NOT NULL COMMENT '状态 见 constants' ,
  `remark` VARCHAR(50) NULL COMMENT '备注 50' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_BTC_account_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_BTC_account_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `EBTC`.`user` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`constants`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`constants` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`constants` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `type` VARCHAR(45) NOT NULL COMMENT '类型' ,
  `attr` VARCHAR(45) NULL COMMENT '属性' ,
  `value` VARCHAR(45) NOT NULL COMMENT '值' ,
  `remark` VARCHAR(45) NULL COMMENT '备注 45' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`orders` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NOT NULL ,
  `price` DECIMAL(20,8) NOT NULL COMMENT '单价 1个虚拟币的价格' ,
  `quantity` DECIMAL(20,8) NOT NULL COMMENT '数量' ,
  `remaining` DECIMAL(20,8) NOT NULL ,
  `type` VARCHAR(2) NOT NULL COMMENT '买卖类型 见 constants' ,
  `state` VARCHAR(2) NOT NULL COMMENT '状态 见 constants' ,
  `currency_type` VARCHAR(2) NOT NULL COMMENT '货币类型 见 constants' ,
  `fee` DECIMAL(20,8) NOT NULL ,
  `fee_rate` DECIMAL(5,4) NOT NULL ,
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间' ,
  `create_user` VARCHAR(10) NOT NULL COMMENT '创建人' ,
  `done_time` TIMESTAMP NULL COMMENT '完成交易时间' ,
  `remark` VARCHAR(100) NULL COMMENT '备注 100' ,
  PRIMARY KEY (`id`) ,
  INDEX `index4` USING HASH (`price` ASC) ,
  INDEX `index5` USING HASH (`done_time` ASC) ,
  INDEX `fk_orders_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_orders_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `EBTC`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`payment` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`payment` (
  `id` INT NOT NULL COMMENT '主键\n' ,
  `user_id` INT NOT NULL COMMENT '外键 用户id' ,
  `type` VARCHAR(2) NOT NULL COMMENT '充值类型 见 constants' ,
  `payment_way` VARCHAR(2) NOT NULL COMMENT '付款方式 见constants' ,
  `amount` DECIMAL(12,2) NOT NULL COMMENT '付款金额' ,
  `currency_type` VARCHAR(2) NOT NULL COMMENT '货币类型 见constants' ,
  `state` VARCHAR(2) NOT NULL COMMENT '状态 见constants' ,
  `create_time` TIMESTAMP NOT NULL ,
  `create_user` VARCHAR(10) NOT NULL ,
  `update_time` TIMESTAMP NULL ,
  `update_user` VARCHAR(10) NULL ,
  `remark` VARCHAR(100) NULL COMMENT '备注 100' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_payment_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_payment_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `EBTC`.`user` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`withdraw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`withdraw` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`withdraw` (
  `id` INT NOT NULL ,
  `user_id` INT NOT NULL COMMENT '外键 用户id' ,
  `remark` VARCHAR(100) NULL COMMENT '备注 100' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_withdraw_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_withdraw_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `EBTC`.`user` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`subject` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`subject` (
  `id` INT NOT NULL ,
  `user_id` INT NOT NULL COMMENT '外键 用户id' ,
  `type` VARCHAR(2) NOT NULL COMMENT '类型 见constants' ,
  `state` VARCHAR(2) NULL COMMENT '状态 见constants' ,
  `title` VARCHAR(100) NOT NULL COMMENT '标题' ,
  `content` VARCHAR(4000) NOT NULL COMMENT '内容' ,
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间' ,
  `create_user` VARCHAR(10) NOT NULL COMMENT '创建人' ,
  `update_time` TIMESTAMP NULL COMMENT '更新时间' ,
  `update_user` VARCHAR(10) NULL COMMENT '更新人' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_subject_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_subject_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `EBTC`.`user` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`reply`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`reply` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`reply` (
  `id` INT NOT NULL ,
  `subject_id` INT NOT NULL COMMENT '外键 主题id' ,
  `type` VARCHAR(2) NOT NULL COMMENT '类型 见contants' ,
  `state` VARCHAR(2) NOT NULL COMMENT '状态 见constants' ,
  `content` VARCHAR(4000) NOT NULL COMMENT '内容' ,
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间' ,
  `create_user` VARCHAR(10) NOT NULL COMMENT '创建人' ,
  `update_time` TIMESTAMP NULL COMMENT '更新时间' ,
  `update_user` VARCHAR(10) NULL COMMENT '更新人' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_reply_subject1_idx` (`subject_id` ASC) ,
  CONSTRAINT `fk_reply_subject1`
    FOREIGN KEY (`subject_id` )
    REFERENCES `EBTC`.`subject` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`menu` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`menu` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `parent_id` INT NOT NULL COMMENT '父id' ,
  `name` VARCHAR(40) NOT NULL COMMENT '名称' ,
  `uri` VARCHAR(150) NOT NULL COMMENT 'uri' ,
  `seq` VARCHAR(2) NOT NULL COMMENT '顺序' ,
  `type` VARCHAR(2) NOT NULL COMMENT '类型' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`activate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`activate` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`activate` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `value` VARCHAR(45) NULL COMMENT '值' ,
  `code` VARCHAR(32) NULL COMMENT '码' ,
  PRIMARY KEY (`id`) ,
  INDEX `index2` (`value` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EBTC`.`trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `EBTC`.`trade` ;

CREATE  TABLE IF NOT EXISTS `EBTC`.`trade` (
  `id` INT NOT NULL ,
  `orders_id` INT NOT NULL COMMENT '主订单id' ,
  `trade_order_id` INT NOT NULL COMMENT '交易订单id' ,
  `quantity` VARCHAR(45) NOT NULL COMMENT '数量' ,
  `totalAmount` DECIMAL(20,8) NOT NULL COMMENT '金额' ,
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间' ,
  `create_user` VARCHAR(10) NOT NULL COMMENT '创建用户' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_trade_orders1_idx` (`orders_id` ASC) ,
  INDEX `fk_trade_orders2_idx` (`trade_order_id` ASC) ,
  CONSTRAINT `fk_trade_orders1`
    FOREIGN KEY (`orders_id` )
    REFERENCES `EBTC`.`orders` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trade_orders2`
    FOREIGN KEY (`trade_order_id` )
    REFERENCES `EBTC`.`orders` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `EBTC` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

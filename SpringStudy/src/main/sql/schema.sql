-- 初始化数据库脚本

-- 创建数据库

CREATE DATABASE shawn_test;

USE shawn_test;

-- 创建表
-- CREATE TABLE seckill(
--  'seckill_id' bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
--  'name' varchar(120) NOT NULL COMMENT '商品名称',
--  'number' int NOT NULL COMMENT '库存数量',
--  'start_time' TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
--  'end_time' TIMESTAMP  NOT NULL COMMENT '秒杀结束时间',
--  'create_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--  PRIMARY  KEY (seckill_id),
--  KEY idx_start_time(start_time),
--  KEY idx_end_time(end_time),
--  KEY idx_create_time(create_time)
-- )ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT='秒杀库存表'

-- 创建表
-- CREATE TABLE seckill (
--  seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
--  name varchar(120) NOT NULL COMMENT '商品名称',
--  number int NOT NULL COMMENT '库存数量',
--  start_time TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
--  end_time TIMESTAMP  NOT NULL COMMENT '秒杀结束时间',
--  create_time TIMESTAMP NOT NULL COMMENT '创建时间',
--  PRIMARY  KEY (seckill_id),
--  KEY idx_start_time(start_time),
--  KEY idx_end_time(end_time),
--  KEY idx_create_time(create_time)
-- )ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT='秒杀库存表'

CREATE TABLE seckill (
 seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
 name varchar(120) NOT NULL COMMENT '商品名称',
 number int NOT NULL COMMENT '库存数量',
 start_time TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00 ' COMMENT '秒杀开启时间',
 end_time TIMESTAMP  NOT NULL DEFAULT '0000-00-00 00:00:00 ' COMMENT '秒杀结束时间',
 create_time TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00 '  COMMENT '创建时间',
 PRIMARY  KEY (seckill_id),
 KEY idx_start_time(start_time),
 KEY idx_end_time(end_time),
 KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=UTF8 COMMENT='秒杀库存表'


-- 初始化数据
insert into seckill(name, number,start_time, end_time,create_time)
VALUES ('1000元秒杀iphone7',100,'2017-03-02 00:00:00','2017-03-03 00:00:00', now()),
('2000元秒杀iPad',200,'2017-03-02 00:00:00','2017-03-03 00:00:00', now()),
('300元秒杀红米4',300,'2017-03-02 00:00:00','2017-03-03 00:00:00',now()),
('500元秒杀Mate7',300,'2017-03-02 00:00:00','2017-03-03 00:00:00',now());

CREATE TABLE success_killed(
 seckill_id bigint NOT NULL COMMENT '商品id',
 user_phone bigint NOT NULL COMMENT '用户手机号',
 stat tinyint NOT NULL DEFAULT  -1 COMMENT '状态标志符:-1无效 0:成功...',
 create_time TIMESTAMP NOT NULL COMMENT '创建时间',

 PRIMARY KEY (seckill_id, user_phone),
 KEY idx_create_time(create_time)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='秒杀成功结果表'

--







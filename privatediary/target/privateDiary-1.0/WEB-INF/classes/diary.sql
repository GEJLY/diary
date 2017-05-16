drop DATABASE IF EXISTS privateDiary;

create DATABASE privateDiary;

use privateDiary;

drop table if exists user;

create table user(
  uid int AUTO_INCREMENT,
  zh varchar(50) NOT NULL UNIQUE,
  pwd varchar(20) NOT NULL,
  nickname varchar(20) NOT NULL unique,
  headImg varchar(20) DEFAULT 'default.png',
  sex varchar(5) CHECK (sex IN ('男' OR '女')) ,
  registDate DATETIME,
  PRIMARY KEY (uid, nickname)
)ENGINE=InnoDB AUTO_INCREMENT=1018 DEFAULT CHARSET=gbk;

DROP TABLE IF EXISTS diary;

CREATE TABLE diary(
  did INT NOT NULL AUTO_INCREMENT,
  nickname varchar(20) NOT NULL,
  title varchar(50) NOT NULL,
  weather varchar(10) NOT NULL,
  diaryType varchar(10) NOT NULL,
  context TEXT NOT NULL,
  PRIMARY KEY (did)
)ENGINE=InnoDB AUTO_INCREMENT=1018 DEFAULT CHARSET=gbk;

INSERT INTO user(zh, pwd, nickname, headImg, sex, registDate) VALUE ("1030766748@qq.com", "admin", "admin", "/admin.png", "男", now());

INSERT INTO user(zh, pwd, nickname, headImg, sex, registDate) VALUE ("13025797630", "sadffss", "sadffss", "/sadffss.png", "男", now());

INSERT INTO diary(nickname, title, weather, diaryType, context) VALUE ("admin", "admin", "晴", "其他", "admin");

INSERT INTO diary(nickname, title, weather, diaryType, context) VALUE ("sadffss", "sadffss", "晴", "其他", "sadffss");



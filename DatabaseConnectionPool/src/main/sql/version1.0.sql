use shawn;

create table test
(id int auto_increment primary key,
 name varchar(100) not null,
 remark varchar(500) not null

);


insert into test(name, remark)values ('Shawn', '未来');
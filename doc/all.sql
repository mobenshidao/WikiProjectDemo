drop table if exists `test`;

create table `wikitest`(
    `id` bigint not null comment 'id',
    `name` varchar(50) comment 'name',
    `password` varchar(50) comment 'password',
    primary key(`id`)
)engine = innodb default charset = utf8mb4 comment = 'wikitest';

insert into `wikitest` (id,name,password) value (1, 'test','testpassword');


drop table if exists `demo`;

create table `demo`(
                           `id` bigint not null comment 'id',
                           `name` varchar(50) comment 'name',
                            primary key(`id`)
)engine = innodb default charset = utf8mb4 comment = 'demo';

insert into `demo` (id,name) value (1, 'test');


#ebook
drop table if exists `ebook`;
create table `ebook`(
                           `id` bigint not null comment 'id',
                           `name` varchar(50) comment 'name',
                           `category1Id` bigint comment 'category1',
                           `category2Id` bigint comment 'category2',
                           `description` varchar(200) comment 'description',
                           `cover` varchar(200) comment 'cover',
                           `doc_count` int comment 'doccount',
                           `view_count` int comment 'viewcount',
                           `vote_count` int comment 'votecount',
                           primary key(`id`)
)engine = innodb default charset = utf8mb4 comment = 'ebook';

insert into `ebook` (id,name,description) value (1, 'Spring Boot guide','Guide you use Spring Boot');
insert into `ebook` (id,name,description) value (2, 'Vue guide','Guide you use Vue');
insert into `ebook` (id,name,description) value (3, 'Python guide','Best book teach you how to creat application with Python');
insert into `ebook` (id,name,description) value (4, 'Guide of Mysql','How to use Mysql');
insert into `ebook` (id,name,description) value (5, 'Master of Oracle','Lead you Learning of Oracle');

drop table if exists `category`;
create table `category`(
                           `id` bigint not null comment 'id',
                           `parent` bigint not null default 0 comment 'fatherId',
                           `name` varchar(50) comment 'name',
                           `sort` int comment 'sort',
                           primary key(`id`)
)engine = innodb default charset = utf8mb4 comment = 'category';

insert into `category` (id,parent,name,sort) value (100,000,'前端开发',100);
insert into `category` (id,parent,name,sort) value (101,100,'Vue',101);
insert into `category` (id,parent,name,sort) value (102,100,'HTML & CSS',102);
insert into `category` (id,parent,name,sort) value (200,000,'Java',200);
insert into `category` (id,parent,name,sort) value (201,200,'基础应用',201);
insert into `category` (id,parent,name,sort) value (202,200,'框架应用',202);
insert into `category` (id,parent,name,sort) value (300,000,'Python',300);
insert into `category` (id,parent,name,sort) value (301,300,'基础应用',301);
insert into `category` (id,parent,name,sort) value (302,300,'进阶方向应用',302);
insert into `category` (id,parent,name,sort) value (400,000,'数据库',400);
insert into `category` (id,parent,name,sort) value (401,400,'MySQL',401);
insert into `category` (id,parent,name,sort) value (500,000,'其它',500);
insert into `category` (id,parent,name,sort) value (501,500,'服务器',501);
insert into `category` (id,parent,name,sort) value (502,500,'开发工具',502);
insert into `category` (id,parent,name,sort) value (503,500,'热门服务端语言',503);
##insert into `category` (id,parent,name,sort) value ();


drop table if exists `doc`;
create table `doc` (
                       `id` bigint not null comment 'id',
                       `ebook_id` bigint not null default 0 comment '电子书id',
                       `parent` bigint not null default 0 comment '父id',
                       `name` varchar(50) not null comment '名称',
                       `sort` int comment '顺序',
                       `view_count` int default 0 comment '阅读数',
                       `vote_count` int default 0 comment '点赞数',
                       primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='文档';

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (1, 1, 0, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (2, 1, 1, '文档1.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (3, 1, 0, '文档2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (4, 1, 3, '文档2.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (5, 1, 3, '文档2.2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (6, 1, 5, '文档2.2.1', 1, 0, 0);


-- 文档内容
drop table if exists `content`;
create table `content` (
                           `id` bigint not null comment '文档id',
                           `content` mediumtext not null comment '内容',
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='文档内容';
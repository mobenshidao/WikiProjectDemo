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
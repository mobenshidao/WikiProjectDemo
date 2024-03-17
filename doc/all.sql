drop table if exists `test`;

create table `wikitest`(
    `id` bigint not null comment 'id',
    `name` varchar(50) comment 'name',
    `password` varchar(50) comment 'password',
    primary key(`id`)
)engine = innodb default charset = utf8mb4 comment = 'wikitest';

insert into `wikitest` (id,name,password) value (1, 'test','testpassword');
create table item
(
    id          bigint       not null primary key,
    name        varchar(250)  not null,
    price       decimal      not null,
    description varchar(200) not null
);


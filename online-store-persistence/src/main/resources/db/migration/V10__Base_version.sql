CREATE TABLE IF NOT EXISTS item
(
    id          bigint       not null primary key,
    name        varchar(250)  not null,
    price       decimal      not null,
    description varchar(200) not null
);
alter sequence item_id_seq owned by item.id;


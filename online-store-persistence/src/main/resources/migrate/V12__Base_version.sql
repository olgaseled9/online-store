create table orders
(
    id         bigint not null primary key,
    created_by date   not null,
    status_id  bigint not null,
    user_id    bigint not null
);
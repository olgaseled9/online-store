CREATE TABLE IF NOT EXISTS orders_item
(
    id       bigint not null primary key,
    order_id bigint not null,
    item_id  bigint not null
);
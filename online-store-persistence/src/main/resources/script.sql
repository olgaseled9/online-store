create sequence if not exists users_id_seq start 1;
create sequence if not exists role_id_seq start 1;
create sequence if not exists status_id_seq start 1;
create sequence if not exists order_id_seq start 1;
create sequence if not exists orders_item_id_seq start 1;

create table role
(
    id   bigint  not null primary key,
    name varchar not null
);
alter sequence role_id_seq owned by role.id;


insert into role (id, name)
values (nextval('role_id_seq'), 'ROLE_ADMINISTRATOR'),
       (nextval('role_id_seq'), 'ROLE_SALE_USER'),
       (nextval('role_id_seq'), 'ROLE_CUSTOMER_USER');

create table Users
(
    id         bigint      not null primary key,
    first_name varchar(25) not null,
    last_name  varchar(30) not null,
    patronymic varchar(30) not null,
    user_name  varchar(30) not null,
    password   varchar(20) not null,
    role_id    bigint      not null,
    email      varchar(50),
    telephone  varchar(20),
    foreign key (role_id) references role (id)
);
alter sequence users_id_seq owned by users.id;

insert into users (id, first_name, last_name, patronymic, user_name, password, role_id, email, telephone)
values (nextval('users_id_seq'), 'Ivan', 'Ivanov', 'Ivanovich', 'cat', 'cat', 1, 'admin1@mail.ru', '+375291232108'),
       (nextval('users_id_seq'), 'Vika', 'Petrova', 'Sergeevna', 'dog', 'dog', 2, 'vika2@yandex.by', '+375441212106'),
       (nextval('users_id_seq'), 'Ivanka', 'Tramp', 'Ivanovna', 'bird', 'bird', 3, 'ivanka3@mail.ry', null),
       (nextval('users_id_seq'), 'Viktoria', 'Petrova', 'Ivanovna', 'bird', 'bird', 2, 'viktoria4@mail.ru',
        '+375258256458'),
       (nextval('users_id_seq'), 'John', 'Tramp', 'Vladimirovich', 'bird', 'bird', 2, 'john5@google.com',
        '+375291234569'),
       (nextval('users_id_seq'), 'James', 'Cannon', 'Petrovich', 'bird', 'bird', 3, 'james6@google.com',
        '+375445432525'),
       (nextval('users_id_seq'), 'Natasha', 'Gyk', 'Antonovna', 'bird', 'bird', 3, 'natasha7@yandex.com',
        '+375258522525'),
       (nextval('users_id_seq'), 'Elena', 'Drozdova', 'Ivanovna', 'bird', 'bird', 3, 'elena8@mail.ru', '+375296596415'),
       (nextval('users_id_seq'), 'Slava', 'Drozd', 'Petrovich', 'bird', 'bird', 3, 'slava9@tut.by', '+375296596416');

create table Status
(
    id   bigint      not null primary key,
    name varchar(25) not null
);

insert into Status (id, name)
values (nextval('status_id_seq'), 'NEW'),
       (nextval('status_id_seq'), 'IN_PROGRESS'),
       (nextval('status_id_seq'), 'DELIVERED'),
       (nextval('status_id_seq'), 'REJECTED');

create table orders_item
(
    id       bigint not null primary key,
    order_id bigint not null,
    item_id  bigint not null
);

insert into orders_item (id, order_id, item_id)
values (nextval('orders_item_id_seq'), 1, 1);

create table item
(
    id          bigint       not null primary key,
    name        varchar(250) not null,
    price       decimal      not null,
    description varchar(200) not null
);

insert into item (id, name, price, description)
values (1, 'Christmas tree', 825.00,
        'The tree is decorated with natural cones and fluffy tapered legs with white dusting.'),
       (2, 'Mini spruce', 25.00, 'Snow-covered mini-spruce in a pot.'),
       (3, 'Christmas figurine Santa Claus', 295.00,
        'This large figurine will be a wonderful home decoration for the New Year holidays.'),
       (4, 'Christmas snowman figure', 1200.00, 'Life-size figure of snowman Olaf from the popular cartoon Frozen.'),
       (5, 'Christmas wreath', 134.00,
        'The decor of this Christmas wreath is a combination of snow-covered spruce twigs with red berries and natural cones.');

create table orders
(
    id         bigint not null primary key,
    created_by date   not null,
    status_id  bigint not null,
    user_id    bigint not null
);
insert into orders (id, created_by, status_id, user_id)
values (nextval('order_id_seq'), '2021-11-27', 1, 3);

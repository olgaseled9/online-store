create table Users
(
    id         bigint      not null primary key,
    first_name varchar(25) not null,
    last_name  varchar(30) not null,
    patronymic varchar(30) not null,
    user_name  varchar(30) not null,
    password   varchar(20) not null,
    role_id    bigint      not null,
    email varchar(50),
    telephone varchar (20),
    foreign key (role_id) references role (id)
);
alter sequence users_id_seq owned by users.id;
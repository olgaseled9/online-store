CREATE TABLE IF NOT EXISTS role
(
    id   bigint      not null primary key,
    name varchar not null
);
alter sequence role_id_seq owned by role.id;

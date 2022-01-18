insert into role (id, name)
values (nextval('role_id_seq'), 'ROLE_ADMINISTRATOR'),
       (nextval('role_id_seq'), 'ROLE_SALE_USER'),
       (nextval('role_id_seq'), 'ROLE_CUSTOMER_USER');
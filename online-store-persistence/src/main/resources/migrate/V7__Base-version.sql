insert into Status (id, name)
values (nextval('status_id_seq'), 'NEW'),
       (nextval('status_id_seq'), 'IN_PROGRESS'),
       (nextval('status_id_seq'), 'DELIVERED'),
       (nextval('status_id_seq'), 'REJECTED');
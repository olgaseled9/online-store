insert into users (id, first_name, last_name, patronymic, user_name, password, role_id,email, telephone )
values (nextval('users_id_seq'), 'Ivan', 'Ivanov', 'Ivanovich', 'admin', '$2a$12$vox9vhA6j3KwUszijpAK7O5u0N/jzFZ.veiDrmR26HysFFXwiCsse',1, 'admin1@mail.ru', '+375291232108'),
       (nextval('users_id_seq'), 'Vika', 'Petrova', 'Sergeevna', 'salesman', '$2a$12$DyyKxW7AE8IX7Ev1O7OoGOtlL2.vExbp4J68ZhgYBrFFVzpk6kUB6',2,'vika2@yandex.by','+375441212106'),
       (nextval('users_id_seq'), 'Ivanka', 'Tramp', 'Ivanovna', 'customer', '$2a$12$wxgIq.Nee8PrmQ4DnzATVuNZQKJulL5UlksX9ELpP3ZJ4FDzVYopW',3,'ivanka3@mail.ry','+375441211414');

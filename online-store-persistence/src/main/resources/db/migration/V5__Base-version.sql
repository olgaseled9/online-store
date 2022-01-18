insert into users (id, first_name, last_name, patronymic, user_name, password, role_id,email, telephone )
values (nextval('users_id_seq'), 'Ivan', 'Ivanov', 'Ivanovich', 'cat', '$2a$12$gkqjhdfVkrxWH6TA5YS0o.eqQ2KPDkovbU0FCrLvzG6vRUewC74I2',1, 'admin1@mail.ru', '+375291232108'),
       (nextval('users_id_seq'), 'Vika', 'Petrova', 'Sergeevna', 'dog', '$2a$12$xay6p2d8uWQzBSp8xn/fDOAwa/gbkJNf89EF.vdDCtU6KbwY6Odf2',2,'vika2@yandex.by','+375441212106'),
       (nextval('users_id_seq'), 'Ivanka', 'Tramp', 'Ivanovna', 'bird', '$2a$12$oD2BwMEaJMHRb2hZinKmwu96W3.L81lGkOxzaq9K7p01BI/xEsCRa',3,'ivanka3@mail.ry','+375441211414');

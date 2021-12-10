insert into users (id, first_name, last_name, patronymic, user_name, password, role_id,email, telephone )
values (nextval('users_id_seq'), 'Ivan', 'Ivanov', 'Ivanovich', 'cat', 'cat',1, 'admin1@mail.ru', '+375291232108'),
       (nextval('users_id_seq'), 'Vika', 'Petrova', 'Sergeevna', 'dog', 'dog',2,'vika2@yandex.by','+375441212106'),
       (nextval('users_id_seq'), 'Ivanka', 'Tramp', 'Ivanovna', 'bird', 'bird',3,'ivanka3@mail.ry',null),
       (nextval('users_id_seq'), 'Viktoria', 'Petrova', 'Ivanovna', 'bird', 'bird',2,'viktoria4@mail.ru','+375258256458'),
       (nextval('users_id_seq'), 'John', 'Tramp', 'Vladimirovich', 'bird', 'bird',2,'john5@google.com','+375291234569'),
       (nextval('users_id_seq'), 'James', 'Cannon', 'Petrovich', 'bird', 'bird',3,'james6@google.com','+375445432525'),
       (nextval('users_id_seq'), 'Natasha', 'Gyk', 'Antonovna', 'bird', 'bird',3,'natasha7@yandex.com','+375258522525'),
       (nextval('users_id_seq'), 'Elena', 'Drozdova', 'Ivanovna', 'bird', 'bird',3,'elena8@mail.ru','+375296596415'),
       (nextval('users_id_seq'), 'Slava', 'Drozd', 'Petrovich', 'bird', 'bird',3,'slava9@tut.by','+375296596416');
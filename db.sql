create table bookings
(
  id int auto_increment comment 'id бронирования'
    primary key,
  user_id int not null comment 'id пользователя, который совершал данное бронирование',
  guestrooms_id int not null comment 'id гостинечного номера, который был забронирован',
  night_price double null comment 'цена за ночь, такой же столбец есть в таблице guestrooms, но там указывается текущая цена, сдесь же цена, по которой пользователь снимал номер.',
  start_day date null comment 'первый день брони',
  last_day date null comment 'последний день брони',
  payed tinyint default '0' null comment 'показывает статус оплаты: оплачено/не оплачено',
  book_day datetime not null,
  all_price double null comment 'итоговая стоимость - необходима для егулирования скидок'
)
  comment 'Бронирования номеров, оплаты номеров' engine=InnoDB
;

create index fk_bookings_user1_idx
  on bookings (user_id)
;

create index fk_bookings_guestrooms1_idx
  on bookings (guestrooms_id)
;

create table comments
(
  id int auto_increment comment 'id комментария'
    primary key,
  guestrooms_id int not null,
  user_id int not null,
  comment text null comment 'текст комментария',
  date date null comment 'Дата оставления коммента',
  rate int(1) default '5' null comment 'выставленный пользователем рейтинг от 1 до 5'
)
  comment 'комментарии пользователей к гостиницам' engine=InnoDB
;

create index fk_comments_guestrooms1_idx
  on comments (guestrooms_id)
;

create index fk_comments_user1_idx
  on comments (user_id)
;

create table guestrooms
(
  id int auto_increment comment 'id гостинечного номера'
    primary key,
  hostel_id int not null,
  night_price double null comment 'текущая цена за 1 ночь',
  tv tinyint default '1' null comment 'есть ли телевизор в номере',
  wifi tinyint default '1' null comment 'есть ли wifi',
  bath tinyint default '1' null comment 'у номера своя ванная комната',
  capacity int default '1' null comment 'на сколько людей рассчитан номер'
)
  comment 'Гостинечные номера' engine=InnoDB
;

create index fk_guestrooms_hostel1_idx
  on guestrooms (hostel_id)
;

alter table bookings
  add constraint fk_bookings_guestrooms1
foreign key (guestrooms_id) references guestrooms (id)
;

alter table comments
  add constraint fk_comments_guestrooms1
foreign key (guestrooms_id) references guestrooms (id)
;

create table hostel
(
  id int auto_increment comment 'id отеля'
    primary key,
  stars int(1) default '3' null,
  imgPath varchar(150) null,
  name varchar(120) not null
)
  comment 'существующие отели' engine=InnoDB
;

alter table guestrooms
  add constraint fk_guestrooms_hostel1
foreign key (hostel_id) references hostel (id)
;

create table language
(
  id int auto_increment comment 'id языка'
    primary key,
  language varchar(5) null comment 'язык'
)
  comment 'Таблица языков' engine=InnoDB
;

create table picture
(
  id int auto_increment
    primary key,
  guestrooms_id int not null comment 'id гостинечного номера, картинка которого загружена',
  file varchar(100) null comment 'путь к нужной картинке',
  constraint fk_picture_guestrooms1
  foreign key (guestrooms_id) references guestrooms (id)
)
  engine=InnoDB
;

create index fk_picture_guestrooms1_idx
  on picture (guestrooms_id)
;

create table tguestrooms
(
  language_id int not null comment 'id языка',
  guestrooms_id int not null comment 'id гостинечного номера',
  description text null comment 'описание гостиничного номер',
  primary key (language_id, guestrooms_id),
  constraint fk_tguestrooms_language1
  foreign key (language_id) references language (id),
  constraint fk_tguestrooms_guestrooms1
  foreign key (guestrooms_id) references guestrooms (id)
)
  comment 'описание гостинечного номера на различных языках' engine=InnoDB
;

create index fk_tguestrooms_language1_idx
  on tguestrooms (language_id)
;

create index fk_tguestrooms_guestrooms1_idx
  on tguestrooms (guestrooms_id)
;

create table thostel
(
  language_id int not null comment 'id языка',
  hostel_id int not null comment 'id отеля',
  country varchar(45) null comment 'страна нахождения',
  city varchar(45) null,
  description text null comment 'описание отеля',
  address varchar(200) null,
  primary key (language_id, hostel_id),
  constraint fk_hostel_language1
  foreign key (language_id) references language (id),
  constraint fk_thostel_hostel1
  foreign key (hostel_id) references hostel (id)
)
  comment 'описания отелей на разных языках' engine=InnoDB
;

create index fk_hostel_language1_idx
  on thostel (language_id)
;

create index fk_thostel_hostel1_idx
  on thostel (hostel_id)
;

create table user
(
  id int auto_increment comment 'id пользователя'
    primary key,
  name varchar(45) not null comment 'имя пользователя',
  surname varchar(45) not null comment 'фамилия пользователя',
  login varchar(45) not null comment 'логин пользователя',
  password char(32) not null comment 'пароль пользоваетеля',
  email varchar(45) null comment 'электронная почта пользователя',
  role enum('admin', 'user') default 'user' not null comment 'данное поле может быть либо admin либо user',
  banned tinyint default '0' null comment 'забанен ли пользователь',
  number varchar(20) null comment 'номер телефона',
  constraint user_login_uindex
  unique (login),
  constraint email_UNIQUE
  unique (email)
)
  comment 'Данные о пользователях и администраторов' engine=InnoDB
;

alter table bookings
  add constraint fk_bookings_user1
foreign key (user_id) references user (id)
;

alter table comments
  add constraint fk_comments_user1
foreign key (user_id) references user (id)
;

INSERT INTO hostel.bookings (id, user_id, guestrooms_id, night_price, start_day, last_day, payed, book_day, all_price) VALUES (1, 6, 3, 58, '2018-12-14', '2017-12-30', 1, '2017-05-22 17:11:27', 110);
INSERT INTO hostel.bookings (id, user_id, guestrooms_id, night_price, start_day, last_day, payed, book_day, all_price) VALUES (2, 6, 7, 55, '2017-12-01', '2017-12-02', 1, '2017-05-22 17:12:43', 110);
INSERT INTO hostel.bookings (id, user_id, guestrooms_id, night_price, start_day, last_day, payed, book_day, all_price) VALUES (3, 6, 8, 64, '2017-12-03', '2017-12-05', 0, '2017-06-22 17:13:26', 128);
INSERT INTO hostel.bookings (id, user_id, guestrooms_id, night_price, start_day, last_day, payed, book_day, all_price) VALUES (4, 13, 3, 59, '2017-12-11', '2017-12-14', 1, '2017-05-22 17:15:13', 120);

INSERT INTO hostel.comments (id, guestrooms_id, user_id, comment, date, rate) VALUES (1, 3, 6, 'Etiam quis quam nec diam porttitor luctus id sit amet ex. Donec facilisis lacinia eleifend. Vivamus tristique nibh in dui auctor ultricies. Phasellus mollis est sed justo viverra, non fermentum erat facilisis. Phasellus tortor magna, finibus quis efficitur at, sodales non ipsum. Vivamus at lacinia ante, feugiat dapibus leo. Quisque nibh turpis, rhoncus sit amet egestas rutrum, congue at purus. Aliquam convallis lacinia nisl id rutrum.', '2017-12-22', 5);
INSERT INTO hostel.comments (id, guestrooms_id, user_id, comment, date, rate) VALUES (2, 7, 6, 'Maecenas sollicitudin, tellus quis feugiat pretium, dolor dolor malesuada eros, sed dignissim nisi diam ac diam. In tristique gravida elit ac maximus. In quis maximus mi, ac semper magna. Etiam hendrerit ligula et tincidunt viverra. Vivamus ultricies pharetra mauris. Quisque feugiat velit id elit dapibus, vel imperdiet erat condimentum. Curabitur feugiat eros sit amet tortor dictum, non placerat justo laoreet. Pellentesque a finibus ex.', '2017-12-08', 3);
INSERT INTO hostel.comments (id, guestrooms_id, user_id, comment, date, rate) VALUES (3, 6, 13, 'Ut ullamcorper tellus a turpis auctor, et sollicitudin elit congue. Aliquam erat volutpat. Integer ac felis quis est auctor aliquam. Sed ut ullamcorper sapien. Morbi bibendum enim eu tellus hendrerit, at viverra velit fermentum. Nam molestie a erat quis ornare. Nullam et diam ac urna suscipit facilisis at vel lorem. Ut in maximus odio. In ac quam consectetur lorem molestie euismod. Aenean malesuada justo vitae nunc auctor aliquet. Donec quis orci sit amet justo semper ornare.', '2017-12-27', 4);

INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (1, 1, 40, 1, 0, 0, 4);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (2, 2, 60, 1, 1, 0, 3);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (3, 3, 100, 1, 1, 1, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (4, 5, 14, 0, 0, 0, 1);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (5, 4, 15, 0, 1, 0, 3);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (6, 1, 55, 1, 1, 1, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (7, 1, 45, 0, 1, 0, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (8, 2, 13, 0, 0, 0, 1);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (9, 2, 87, 1, 1, 1, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (10, 2, 135, 1, 1, 1, 3);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (11, 2, 550, 1, 1, 1, 4);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (12, 3, 150, 1, 1, 1, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (13, 3, 25, 0, 1, 1, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (14, 3, 9, 0, 1, 0, 1);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (15, 3, 67, 0, 1, 1, 3);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (16, 4, 13, 1, 0, 0, 1);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (17, 4, 27, 0, 1, 0, 1);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (18, 4, 49, 1, 1, 1, 3);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (19, 4, 71, 1, 1, 1, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (20, 4, 140, 1, 1, 1, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (21, 5, 35, 0, 0, 1, 1);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (22, 5, 47, 0, 1, 1, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (23, 5, 89, 1, 1, 1, 2);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (24, 5, 89, 0, 1, 1, 3);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (25, 5, 91, 0, 1, 1, 4);
INSERT INTO hostel.guestrooms (id, hostel_id, night_price, tv, wifi, bath, capacity) VALUES (26, 5, 112, 0, 1, 1, 2);

INSERT INTO hostel.hostel (id, stars, imgPath, name) VALUES (1, 4, '/img/hotel/1.jpg', 'Motel hotel');
INSERT INTO hostel.hostel (id, stars, imgPath, name) VALUES (2, 3, '/img/hotel/2.jpg', 'Monaco hotel');
INSERT INTO hostel.hostel (id, stars, imgPath, name) VALUES (3, 5, '/img/hotel/3.jpg', 'Arizona hotel');
INSERT INTO hostel.hostel (id, stars, imgPath, name) VALUES (4, 5, '/img/hotel/4.jpg', 'Marco hotel');
INSERT INTO hostel.hostel (id, stars, imgPath, name) VALUES (5, 4, '/img/hotel/5.jpg', 'Irvi hotel');

INSERT INTO hostel.language (id, language) VALUES (1, 'en');
INSERT INTO hostel.language (id, language) VALUES (2, 'ru');

INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (1, 1, '/img/guestroom/1.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (2, 2, '/img/guestroom/2.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (3, 3, '/img/guestroom/3.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (4, 4, '/img/guestroom/4.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (5, 5, '/img/guestroom/5.jpeg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (6, 6, '/img/guestroom/6.jpeg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (7, 7, '/img/guestroom/7.jpeg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (8, 8, '/img/guestroom/8.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (9, 9, '/img/guestroom/9.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (10, 10, '/img/guestroom/10.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (11, 11, '/img/guestroom/11.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (12, 12, '/img/guestroom/12.png');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (13, 13, '/img/guestroom/13.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (14, 14, '/img/guestroom/14.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (15, 15, '/img/guestroom/15.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (16, 16, '/img/guestroom/16.jpeg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (17, 17, '/img/guestroom/17.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (18, 18, '/img/guestroom/18.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (19, 19, '/img/guestroom/19.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (20, 20, '/img/guestroom/20.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (21, 21, '/img/guestroom/21.JPG');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (22, 22, '/img/guestroom/22.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (23, 23, '/img/guestroom/23.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (24, 24, '/img/guestroom/24.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (25, 25, '/img/guestroom/25.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (26, 26, '/img/guestroom/26.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (27, 1, '/img/guestroom/bathroom/1.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (28, 2, '/img/guestroom/bathroom/2.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (29, 3, '/img/guestroom/bathroom/3.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (30, 4, '/img/guestroom/bathroom/4.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (31, 5, '/img/guestroom/bathroom/5.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (32, 6, '/img/guestroom/bathroom/6.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (33, 7, '/img/guestroom/bathroom/7.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (34, 8, '/img/guestroom/bathroom/8.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (35, 9, '/img/guestroom/bathroom/9.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (36, 10, '/img/guestroom/bathroom/10.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (37, 11, '/img/guestroom/bathroom/11.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (38, 12, '/img/guestroom/bathroom/12.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (39, 13, '/img/guestroom/bathroom/13.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (40, 14, '/img/guestroom/bathroom/14.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (41, 15, '/img/guestroom/bathroom/15.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (42, 16, '/img/guestroom/bathroom/16.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (43, 17, '/img/guestroom/bathroom/17.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (44, 18, '/img/guestroom/bathroom/18.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (45, 19, '/img/guestroom/bathroom/19.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (46, 20, '/img/guestroom/bathroom/20.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (47, 21, '/img/guestroom/bathroom/21.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (48, 22, '/img/guestroom/bathroom/22.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (49, 23, '/img/guestroom/bathroom/23.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (50, 24, '/img/guestroom/bathroom/24.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (51, 25, '/img/guestroom/bathroom/25.jpg');
INSERT INTO hostel.picture (id, guestrooms_id, file) VALUES (52, 26, '/img/guestroom/bathroom/26.jpg');

INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 1, 'Donec id enim nisi. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Integer convallis dapibus massa, ut semper tellus egestas sit amet. Nullam sagittis, elit in congue ornare, orci ante maximus mi, non gravida lacus nisi et odio. Vivamus nec magna mi. Nunc venenatis sagittis neque, sed aliquam libero rhoncus nec. Proin at nulla et justo facilisis rutrum quis eget felis. Morbi orci diam, efficitur in lorem non, dictum varius quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Suspendisse vitae sapien quis felis placerat commodo. Etiam sagittis metus nec viverra feugiat. Etiam facilisis aliquam mi, non viverra quam viverra non. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam sem leo, elementum et magna id, efficitur egestas tellus. Nunc ultricies pretium nunc, vel rhoncus leo ornare et.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 2, 'Aenean venenatis lectus justo, nec congue libero accumsan eu. Nunc dignissim nulla sed odio consectetur, eu tincidunt nisi ullamcorper. Vivamus feugiat metus purus, nec rutrum orci vehicula ac. Nullam tempor tincidunt elit, sit amet dictum augue viverra at. Sed sapien lacus, pellentesque aliquet diam eu, fringilla rutrum metus. Phasellus mattis justo est, in faucibus magna vulputate vel. Donec diam sapien, vehicula eget molestie in, ultrices dignissim diam.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 3, 'Donec vestibulum, urna a pretium dapibus, est dui molestie dolor, non accumsan odio erat at neque. Quisque sit amet scelerisque nibh. Nullam vehicula nunc eu ornare fringilla. Nam nec lorem velit. Nulla facilisi. Nullam sollicitudin tincidunt metus sed condimentum. Vestibulum tincidunt porta convallis. Morbi hendrerit augue sed felis maximus, quis efficitur lectus hendrerit.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 4, 'Donec ante libero, molestie in lectus vel, interdum iaculis lorem. Aenean ullamcorper lectus ut magna volutpat vestibulum ut ac orci. Quisque tristique ut mauris non mollis. Vivamus elementum arcu ac tortor blandit fringilla. Vestibulum ultrices ac elit ut tempor. Proin eget consequat neque. Ut id est ante. Proin malesuada laoreet lectus, vel lobortis dui porta vitae. Praesent pellentesque tellus libero, semper efficitur arcu aliquam quis. Praesent et porttitor tellus, vel porttitor risus.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 5, 'Donec a lacus euismod nunc pellentesque feugiat sit amet a elit. Pellentesque euismod ante in ornare accumsan. Nunc quis libero nec dui lacinia aliquet. In tristique cursus consequat. Morbi volutpat mi ut mollis lacinia. Sed auctor, elit vitae viverra consectetur, dolor tortor mattis nisl, vitae facilisis orci eros ac purus. Sed eget blandit leo, vitae finibus sem. Cras a sagittis neque, at dictum diam. In congue metus congue erat laoreet, tempor imperdiet ex aliquet. Mauris sed laoreet erat. Aliquam congue ligula lacus, eu iaculis arcu dictum quis. Morbi et lorem tincidunt, bibendum urna et, porta mauris.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 6, 'Donec id enim nisi. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Integer convallis dapibus massa, ut semper tellus egestas sit amet. Nullam sagittis, elit in congue ornare, orci ante maximus mi, non gravida lacus nisi et odio. Vivamus nec magna mi. Nunc venenatis sagittis neque, sed aliquam libero rhoncus nec. Proin at nulla et justo facilisis rutrum quis eget felis. Morbi orci diam, efficitur in lorem non, dictum varius quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Suspendisse vitae sapien quis felis placerat commodo. Etiam sagittis metus nec viverra feugiat. Etiam facilisis aliquam mi, non viverra quam viverra non. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam sem leo, elementum et magna id, efficitur egestas tellus. Nunc ultricies pretium nunc, vel rhoncus leo ornare et.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 7, 'Aenean venenatis lectus justo, nec congue libero accumsan eu. Nunc dignissim nulla sed odio consectetur, eu tincidunt nisi ullamcorper. Vivamus feugiat metus purus, nec rutrum orci vehicula ac. Nullam tempor tincidunt elit, sit amet dictum augue viverra at. Sed sapien lacus, pellentesque aliquet diam eu, fringilla rutrum metus. Phasellus mattis justo est, in faucibus magna vulputate vel. Donec diam sapien, vehicula eget molestie in, ultrices dignissim diam.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 8, 'Donec vestibulum, urna a pretium dapibus, est dui molestie dolor, non accumsan odio erat at neque. Quisque sit amet scelerisque nibh. Nullam vehicula nunc eu ornare fringilla. Nam nec lorem velit. Nulla facilisi. Nullam sollicitudin tincidunt metus sed condimentum. Vestibulum tincidunt porta convallis. Morbi hendrerit augue sed felis maximus, quis efficitur lectus hendrerit.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 9, 'Donec ante libero, molestie in lectus vel, interdum iaculis lorem. Aenean ullamcorper lectus ut magna volutpat vestibulum ut ac orci. Quisque tristique ut mauris non mollis. Vivamus elementum arcu ac tortor blandit fringilla. Vestibulum ultrices ac elit ut tempor. Proin eget consequat neque. Ut id est ante. Proin malesuada laoreet lectus, vel lobortis dui porta vitae. Praesent pellentesque tellus libero, semper efficitur arcu aliquam quis. Praesent et porttitor tellus, vel porttitor risus.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 10, 'Donec a lacus euismod nunc pellentesque feugiat sit amet a elit. Pellentesque euismod ante in ornare accumsan. Nunc quis libero nec dui lacinia aliquet. In tristique cursus consequat. Morbi volutpat mi ut mollis lacinia. Sed auctor, elit vitae viverra consectetur, dolor tortor mattis nisl, vitae facilisis orci eros ac purus. Sed eget blandit leo, vitae finibus sem. Cras a sagittis neque, at dictum diam. In congue metus congue erat laoreet, tempor imperdiet ex aliquet. Mauris sed laoreet erat. Aliquam congue ligula lacus, eu iaculis arcu dictum quis. Morbi et lorem tincidunt, bibendum urna et, porta mauris.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 11, 'Donec id enim nisi. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Integer convallis dapibus massa, ut semper tellus egestas sit amet. Nullam sagittis, elit in congue ornare, orci ante maximus mi, non gravida lacus nisi et odio. Vivamus nec magna mi. Nunc venenatis sagittis neque, sed aliquam libero rhoncus nec. Proin at nulla et justo facilisis rutrum quis eget felis. Morbi orci diam, efficitur in lorem non, dictum varius quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Suspendisse vitae sapien quis felis placerat commodo. Etiam sagittis metus nec viverra feugiat. Etiam facilisis aliquam mi, non viverra quam viverra non. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam sem leo, elementum et magna id, efficitur egestas tellus. Nunc ultricies pretium nunc, vel rhoncus leo ornare et.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 12, 'Aenean venenatis lectus justo, nec congue libero accumsan eu. Nunc dignissim nulla sed odio consectetur, eu tincidunt nisi ullamcorper. Vivamus feugiat metus purus, nec rutrum orci vehicula ac. Nullam tempor tincidunt elit, sit amet dictum augue viverra at. Sed sapien lacus, pellentesque aliquet diam eu, fringilla rutrum metus. Phasellus mattis justo est, in faucibus magna vulputate vel. Donec diam sapien, vehicula eget molestie in, ultrices dignissim diam.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 13, 'Donec vestibulum, urna a pretium dapibus, est dui molestie dolor, non accumsan odio erat at neque. Quisque sit amet scelerisque nibh. Nullam vehicula nunc eu ornare fringilla. Nam nec lorem velit. Nulla facilisi. Nullam sollicitudin tincidunt metus sed condimentum. Vestibulum tincidunt porta convallis. Morbi hendrerit augue sed felis maximus, quis efficitur lectus hendrerit.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 14, 'Donec ante libero, molestie in lectus vel, interdum iaculis lorem. Aenean ullamcorper lectus ut magna volutpat vestibulum ut ac orci. Quisque tristique ut mauris non mollis. Vivamus elementum arcu ac tortor blandit fringilla. Vestibulum ultrices ac elit ut tempor. Proin eget consequat neque. Ut id est ante. Proin malesuada laoreet lectus, vel lobortis dui porta vitae. Praesent pellentesque tellus libero, semper efficitur arcu aliquam quis. Praesent et porttitor tellus, vel porttitor risus.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 15, 'Donec a lacus euismod nunc pellentesque feugiat sit amet a elit. Pellentesque euismod ante in ornare accumsan. Nunc quis libero nec dui lacinia aliquet. In tristique cursus consequat. Morbi volutpat mi ut mollis lacinia. Sed auctor, elit vitae viverra consectetur, dolor tortor mattis nisl, vitae facilisis orci eros ac purus. Sed eget blandit leo, vitae finibus sem. Cras a sagittis neque, at dictum diam. In congue metus congue erat laoreet, tempor imperdiet ex aliquet. Mauris sed laoreet erat. Aliquam congue ligula lacus, eu iaculis arcu dictum quis. Morbi et lorem tincidunt, bibendum urna et, porta mauris.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 16, 'Donec id enim nisi. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Integer convallis dapibus massa, ut semper tellus egestas sit amet. Nullam sagittis, elit in congue ornare, orci ante maximus mi, non gravida lacus nisi et odio. Vivamus nec magna mi. Nunc venenatis sagittis neque, sed aliquam libero rhoncus nec. Proin at nulla et justo facilisis rutrum quis eget felis. Morbi orci diam, efficitur in lorem non, dictum varius quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Suspendisse vitae sapien quis felis placerat commodo. Etiam sagittis metus nec viverra feugiat. Etiam facilisis aliquam mi, non viverra quam viverra non. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam sem leo, elementum et magna id, efficitur egestas tellus. Nunc ultricies pretium nunc, vel rhoncus leo ornare et.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 17, 'Aenean venenatis lectus justo, nec congue libero accumsan eu. Nunc dignissim nulla sed odio consectetur, eu tincidunt nisi ullamcorper. Vivamus feugiat metus purus, nec rutrum orci vehicula ac. Nullam tempor tincidunt elit, sit amet dictum augue viverra at. Sed sapien lacus, pellentesque aliquet diam eu, fringilla rutrum metus. Phasellus mattis justo est, in faucibus magna vulputate vel. Donec diam sapien, vehicula eget molestie in, ultrices dignissim diam.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 18, 'Donec vestibulum, urna a pretium dapibus, est dui molestie dolor, non accumsan odio erat at neque. Quisque sit amet scelerisque nibh. Nullam vehicula nunc eu ornare fringilla. Nam nec lorem velit. Nulla facilisi. Nullam sollicitudin tincidunt metus sed condimentum. Vestibulum tincidunt porta convallis. Morbi hendrerit augue sed felis maximus, quis efficitur lectus hendrerit.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 19, 'Donec ante libero, molestie in lectus vel, interdum iaculis lorem. Aenean ullamcorper lectus ut magna volutpat vestibulum ut ac orci. Quisque tristique ut mauris non mollis. Vivamus elementum arcu ac tortor blandit fringilla. Vestibulum ultrices ac elit ut tempor. Proin eget consequat neque. Ut id est ante. Proin malesuada laoreet lectus, vel lobortis dui porta vitae. Praesent pellentesque tellus libero, semper efficitur arcu aliquam quis. Praesent et porttitor tellus, vel porttitor risus.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 20, 'Donec a lacus euismod nunc pellentesque feugiat sit amet a elit. Pellentesque euismod ante in ornare accumsan. Nunc quis libero nec dui lacinia aliquet. In tristique cursus consequat. Morbi volutpat mi ut mollis lacinia. Sed auctor, elit vitae viverra consectetur, dolor tortor mattis nisl, vitae facilisis orci eros ac purus. Sed eget blandit leo, vitae finibus sem. Cras a sagittis neque, at dictum diam. In congue metus congue erat laoreet, tempor imperdiet ex aliquet. Mauris sed laoreet erat. Aliquam congue ligula lacus, eu iaculis arcu dictum quis. Morbi et lorem tincidunt, bibendum urna et, porta mauris.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 21, 'Donec id enim nisi. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Integer convallis dapibus massa, ut semper tellus egestas sit amet. Nullam sagittis, elit in congue ornare, orci ante maximus mi, non gravida lacus nisi et odio. Vivamus nec magna mi. Nunc venenatis sagittis neque, sed aliquam libero rhoncus nec. Proin at nulla et justo facilisis rutrum quis eget felis. Morbi orci diam, efficitur in lorem non, dictum varius quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Suspendisse vitae sapien quis felis placerat commodo. Etiam sagittis metus nec viverra feugiat. Etiam facilisis aliquam mi, non viverra quam viverra non. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam sem leo, elementum et magna id, efficitur egestas tellus. Nunc ultricies pretium nunc, vel rhoncus leo ornare et.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 22, 'Aenean venenatis lectus justo, nec congue libero accumsan eu. Nunc dignissim nulla sed odio consectetur, eu tincidunt nisi ullamcorper. Vivamus feugiat metus purus, nec rutrum orci vehicula ac. Nullam tempor tincidunt elit, sit amet dictum augue viverra at. Sed sapien lacus, pellentesque aliquet diam eu, fringilla rutrum metus. Phasellus mattis justo est, in faucibus magna vulputate vel. Donec diam sapien, vehicula eget molestie in, ultrices dignissim diam.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 23, 'Donec vestibulum, urna a pretium dapibus, est dui molestie dolor, non accumsan odio erat at neque. Quisque sit amet scelerisque nibh. Nullam vehicula nunc eu ornare fringilla. Nam nec lorem velit. Nulla facilisi. Nullam sollicitudin tincidunt metus sed condimentum. Vestibulum tincidunt porta convallis. Morbi hendrerit augue sed felis maximus, quis efficitur lectus hendrerit.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 24, 'Donec ante libero, molestie in lectus vel, interdum iaculis lorem. Aenean ullamcorper lectus ut magna volutpat vestibulum ut ac orci. Quisque tristique ut mauris non mollis. Vivamus elementum arcu ac tortor blandit fringilla. Vestibulum ultrices ac elit ut tempor. Proin eget consequat neque. Ut id est ante. Proin malesuada laoreet lectus, vel lobortis dui porta vitae. Praesent pellentesque tellus libero, semper efficitur arcu aliquam quis. Praesent et porttitor tellus, vel porttitor risus.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 25, 'Donec a lacus euismod nunc pellentesque feugiat sit amet a elit. Pellentesque euismod ante in ornare accumsan. Nunc quis libero nec dui lacinia aliquet. In tristique cursus consequat. Morbi volutpat mi ut mollis lacinia. Sed auctor, elit vitae viverra consectetur, dolor tortor mattis nisl, vitae facilisis orci eros ac purus. Sed eget blandit leo, vitae finibus sem. Cras a sagittis neque, at dictum diam. In congue metus congue erat laoreet, tempor imperdiet ex aliquet. Mauris sed laoreet erat. Aliquam congue ligula lacus, eu iaculis arcu dictum quis. Morbi et lorem tincidunt, bibendum urna et, porta mauris.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (1, 26, 'Donec a lacus euismod nunc pellentesque feugiat sit amet a elit. Pellentesque euismod ante in ornare accumsan. Nunc quis libero nec dui lacinia aliquet. In tristique cursus consequat. Morbi volutpat mi ut mollis lacinia. Sed auctor, elit vitae viverra consectetur, dolor tortor mattis nisl, vitae facilisis orci eros ac purus. Sed eget blandit leo, vitae finibus sem. Cras a sagittis neque, at dictum diam. In congue metus congue erat laoreet, tempor imperdiet ex aliquet. Mauris sed laoreet erat. Aliquam congue ligula lacus, eu iaculis arcu dictum quis. Morbi et lorem tincidunt, bibendum urna et, porta mauris.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 1, 'Конечно, можно представить себе, каким воспитателем и отцом мог быть такой человек. С ним как с отцом именно случилось то, что должно было случиться, то есть он вовсе и совершенно бросил своего ребенка, прижитого с Аделаидой Ивановной, не по злобе к нему или не из каких-нибудь оскорбленно-супружеских чувств, а просто потому, что забыл о нем совершенно. Пока он докучал всем своими слезами и жалобами, а дом свой обратил в развратный вертеп, трехлетнего мальчика Митю взял на свое попечение верный слуга этого дома Григорий, и не позаботься он тогда о нем, то, может быть, на ребенке некому было бы переменить рубашонку.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 2, 'К тому же так случилось, что родня ребенка по матери тоже как бы забыла о нем в первое время. Деда его, то есть самого господина Миусова, отца Аделаиды Ивановны, тогда уже не было в живых; овдовевшая супруга его, бабушка Мити, переехавшая в Москву, слишком расхворалась, сестры же повышли замуж, так что почти целый год пришлось Мите пробыть у слуги Григория и проживать у него в дворовой избе. Впрочем, если бы папаша о нем и вспомнил (не мог же он в самом деле не знать о его существовании), то и сам сослал бы его опять в избу, так как ребенок всё же мешал бы ему в его дебоширстве. Но случилось так, что из Парижа вернулся двоюродный брат покойной Аделаиды Ивановны, Петр Александрович Миусов, многие годы сряду выживший потом за границей, тогда же еще очень молодой человек, но человек особенный между Миусовыми, просвещенный, столичный, заграничный и притом всю жизнь свою европеец, а под конец жизни либерал сороковых и пятидесятых годов. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 3, 'В продолжение своей карьеры он перебывал в связях со многими либеральнейшими людьми своей эпохи, и в России и за границей, знавал лично и Прудона и Бакунина и особенно любил вспоминать и рассказывать, уже под концом своих странствий, о трех днях февральской парижской революции сорок восьмого года, намекая, что чуть ли и сам он не был в ней участником на баррикадах. Это было одно из самых отраднейших воспоминаний его молодости. Имел он состояние независимое, по прежней пропорции около тысячи душ. Превосходное имение его находилось сейчас же на выезде из нашего городка и граничило с землей нашего знаменитого монастыря, с которым Петр Александрович, еще в самых молодых летах, как только получил наследство, мигом начал нескончаемый процесс за право каких-то ловель в реке или порубок в лесу, доподлинно не знаю, но начать процесс с «клерикалами» почел даже своею гражданскою и просвещенною обязанностью. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 4, 'Услышав всё про Аделаиду Ивановну, которую, разумеется, помнил и когда-то даже заметил, и узнав, что остался Митя, он, несмотря на всё молодое негодование свое и презрение к Федору Павловичу, в это дело ввязался. Тут-то он с Федором Павловичем в первый раз и познакомился. Он прямо ему объявил, что желал бы взять воспитание ребенка на себя. Он долго потом рассказывал, в виде характерной черты, что когда он заговорил с Федором Павловичем о Мите, то тот некоторое время имел вид совершенно не понимающего, о каком таком ребенке идет дело, и даже как бы удивился, что у него есть где-то в доме маленький сын. Если в рассказе Петра Александровича могло быть преувеличение, то всё же должно было быть и нечто похожее на правду.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 5, 'Но действительно Федор Павлович всю жизнь свою любил представляться, вдруг проиграть пред вами какую-нибудь неожиданную роль, и, главное, безо всякой иногда надобности, даже в прямой ущерб себе, как в настоящем, например, случае. Черта эта, впрочем, свойственна чрезвычайно многим людям, и даже весьма умным, не то что Федору Павловичу. Петр Александрович повел дело горячо и даже назначен был (купно с Федором Павловичем) в опекуны ребенку, потому что всё же после матери оставалось именьице — дом и поместье. Митя действительно переехал к этому двоюродному дяде, но собственного семейства у того не было, а так как сам он, едва лишь уладив и обеспечив свои денежные получения с своих имений, немедленно поспешил опять надолго в Париж, то ребенка и поручил одной из своих двоюродных теток, одной московской барыне. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 6, 'Конечно, можно представить себе, каким воспитателем и отцом мог быть такой человек. С ним как с отцом именно случилось то, что должно было случиться, то есть он вовсе и совершенно бросил своего ребенка, прижитого с Аделаидой Ивановной, не по злобе к нему или не из каких-нибудь оскорбленно-супружеских чувств, а просто потому, что забыл о нем совершенно. Пока он докучал всем своими слезами и жалобами, а дом свой обратил в развратный вертеп, трехлетнего мальчика Митю взял на свое попечение верный слуга этого дома Григорий, и не позаботься он тогда о нем, то, может быть, на ребенке некому было бы переменить рубашонку.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 7, 'К тому же так случилось, что родня ребенка по матери тоже как бы забыла о нем в первое время. Деда его, то есть самого господина Миусова, отца Аделаиды Ивановны, тогда уже не было в живых; овдовевшая супруга его, бабушка Мити, переехавшая в Москву, слишком расхворалась, сестры же повышли замуж, так что почти целый год пришлось Мите пробыть у слуги Григория и проживать у него в дворовой избе. Впрочем, если бы папаша о нем и вспомнил (не мог же он в самом деле не знать о его существовании), то и сам сослал бы его опять в избу, так как ребенок всё же мешал бы ему в его дебоширстве. Но случилось так, что из Парижа вернулся двоюродный брат покойной Аделаиды Ивановны, Петр Александрович Миусов, многие годы сряду выживший потом за границей, тогда же еще очень молодой человек, но человек особенный между Миусовыми, просвещенный, столичный, заграничный и притом всю жизнь свою европеец, а под конец жизни либерал сороковых и пятидесятых годов. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 8, 'В продолжение своей карьеры он перебывал в связях со многими либеральнейшими людьми своей эпохи, и в России и за границей, знавал лично и Прудона и Бакунина и особенно любил вспоминать и рассказывать, уже под концом своих странствий, о трех днях февральской парижской революции сорок восьмого года, намекая, что чуть ли и сам он не был в ней участником на баррикадах. Это было одно из самых отраднейших воспоминаний его молодости. Имел он состояние независимое, по прежней пропорции около тысячи душ. Превосходное имение его находилось сейчас же на выезде из нашего городка и граничило с землей нашего знаменитого монастыря, с которым Петр Александрович, еще в самых молодых летах, как только получил наследство, мигом начал нескончаемый процесс за право каких-то ловель в реке или порубок в лесу, доподлинно не знаю, но начать процесс с «клерикалами» почел даже своею гражданскою и просвещенною обязанностью. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 9, 'Услышав всё про Аделаиду Ивановну, которую, разумеется, помнил и когда-то даже заметил, и узнав, что остался Митя, он, несмотря на всё молодое негодование свое и презрение к Федору Павловичу, в это дело ввязался. Тут-то он с Федором Павловичем в первый раз и познакомился. Он прямо ему объявил, что желал бы взять воспитание ребенка на себя. Он долго потом рассказывал, в виде характерной черты, что когда он заговорил с Федором Павловичем о Мите, то тот некоторое время имел вид совершенно не понимающего, о каком таком ребенке идет дело, и даже как бы удивился, что у него есть где-то в доме маленький сын. Если в рассказе Петра Александровича могло быть преувеличение, то всё же должно было быть и нечто похожее на правду.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 10, 'Но действительно Федор Павлович всю жизнь свою любил представляться, вдруг проиграть пред вами какую-нибудь неожиданную роль, и, главное, безо всякой иногда надобности, даже в прямой ущерб себе, как в настоящем, например, случае. Черта эта, впрочем, свойственна чрезвычайно многим людям, и даже весьма умным, не то что Федору Павловичу. Петр Александрович повел дело горячо и даже назначен был (купно с Федором Павловичем) в опекуны ребенку, потому что всё же после матери оставалось именьице — дом и поместье. Митя действительно переехал к этому двоюродному дяде, но собственного семейства у того не было, а так как сам он, едва лишь уладив и обеспечив свои денежные получения с своих имений, немедленно поспешил опять надолго в Париж, то ребенка и поручил одной из своих двоюродных теток, одной московской барыне. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 11, 'Конечно, можно представить себе, каким воспитателем и отцом мог быть такой человек. С ним как с отцом именно случилось то, что должно было случиться, то есть он вовсе и совершенно бросил своего ребенка, прижитого с Аделаидой Ивановной, не по злобе к нему или не из каких-нибудь оскорбленно-супружеских чувств, а просто потому, что забыл о нем совершенно. Пока он докучал всем своими слезами и жалобами, а дом свой обратил в развратный вертеп, трехлетнего мальчика Митю взял на свое попечение верный слуга этого дома Григорий, и не позаботься он тогда о нем, то, может быть, на ребенке некому было бы переменить рубашонку.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 12, 'К тому же так случилось, что родня ребенка по матери тоже как бы забыла о нем в первое время. Деда его, то есть самого господина Миусова, отца Аделаиды Ивановны, тогда уже не было в живых; овдовевшая супруга его, бабушка Мити, переехавшая в Москву, слишком расхворалась, сестры же повышли замуж, так что почти целый год пришлось Мите пробыть у слуги Григория и проживать у него в дворовой избе. Впрочем, если бы папаша о нем и вспомнил (не мог же он в самом деле не знать о его существовании), то и сам сослал бы его опять в избу, так как ребенок всё же мешал бы ему в его дебоширстве. Но случилось так, что из Парижа вернулся двоюродный брат покойной Аделаиды Ивановны, Петр Александрович Миусов, многие годы сряду выживший потом за границей, тогда же еще очень молодой человек, но человек особенный между Миусовыми, просвещенный, столичный, заграничный и притом всю жизнь свою европеец, а под конец жизни либерал сороковых и пятидесятых годов. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 13, 'В продолжение своей карьеры он перебывал в связях со многими либеральнейшими людьми своей эпохи, и в России и за границей, знавал лично и Прудона и Бакунина и особенно любил вспоминать и рассказывать, уже под концом своих странствий, о трех днях февральской парижской революции сорок восьмого года, намекая, что чуть ли и сам он не был в ней участником на баррикадах. Это было одно из самых отраднейших воспоминаний его молодости. Имел он состояние независимое, по прежней пропорции около тысячи душ. Превосходное имение его находилось сейчас же на выезде из нашего городка и граничило с землей нашего знаменитого монастыря, с которым Петр Александрович, еще в самых молодых летах, как только получил наследство, мигом начал нескончаемый процесс за право каких-то ловель в реке или порубок в лесу, доподлинно не знаю, но начать процесс с «клерикалами» почел даже своею гражданскою и просвещенною обязанностью. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 14, 'Услышав всё про Аделаиду Ивановну, которую, разумеется, помнил и когда-то даже заметил, и узнав, что остался Митя, он, несмотря на всё молодое негодование свое и презрение к Федору Павловичу, в это дело ввязался. Тут-то он с Федором Павловичем в первый раз и познакомился. Он прямо ему объявил, что желал бы взять воспитание ребенка на себя. Он долго потом рассказывал, в виде характерной черты, что когда он заговорил с Федором Павловичем о Мите, то тот некоторое время имел вид совершенно не понимающего, о каком таком ребенке идет дело, и даже как бы удивился, что у него есть где-то в доме маленький сын. Если в рассказе Петра Александровича могло быть преувеличение, то всё же должно было быть и нечто похожее на правду.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 15, 'Но действительно Федор Павлович всю жизнь свою любил представляться, вдруг проиграть пред вами какую-нибудь неожиданную роль, и, главное, безо всякой иногда надобности, даже в прямой ущерб себе, как в настоящем, например, случае. Черта эта, впрочем, свойственна чрезвычайно многим людям, и даже весьма умным, не то что Федору Павловичу. Петр Александрович повел дело горячо и даже назначен был (купно с Федором Павловичем) в опекуны ребенку, потому что всё же после матери оставалось именьице — дом и поместье. Митя действительно переехал к этому двоюродному дяде, но собственного семейства у того не было, а так как сам он, едва лишь уладив и обеспечив свои денежные получения с своих имений, немедленно поспешил опять надолго в Париж, то ребенка и поручил одной из своих двоюродных теток, одной московской барыне. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 16, 'Конечно, можно представить себе, каким воспитателем и отцом мог быть такой человек. С ним как с отцом именно случилось то, что должно было случиться, то есть он вовсе и совершенно бросил своего ребенка, прижитого с Аделаидой Ивановной, не по злобе к нему или не из каких-нибудь оскорбленно-супружеских чувств, а просто потому, что забыл о нем совершенно. Пока он докучал всем своими слезами и жалобами, а дом свой обратил в развратный вертеп, трехлетнего мальчика Митю взял на свое попечение верный слуга этого дома Григорий, и не позаботься он тогда о нем, то, может быть, на ребенке некому было бы переменить рубашонку.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 17, 'К тому же так случилось, что родня ребенка по матери тоже как бы забыла о нем в первое время. Деда его, то есть самого господина Миусова, отца Аделаиды Ивановны, тогда уже не было в живых; овдовевшая супруга его, бабушка Мити, переехавшая в Москву, слишком расхворалась, сестры же повышли замуж, так что почти целый год пришлось Мите пробыть у слуги Григория и проживать у него в дворовой избе. Впрочем, если бы папаша о нем и вспомнил (не мог же он в самом деле не знать о его существовании), то и сам сослал бы его опять в избу, так как ребенок всё же мешал бы ему в его дебоширстве. Но случилось так, что из Парижа вернулся двоюродный брат покойной Аделаиды Ивановны, Петр Александрович Миусов, многие годы сряду выживший потом за границей, тогда же еще очень молодой человек, но человек особенный между Миусовыми, просвещенный, столичный, заграничный и притом всю жизнь свою европеец, а под конец жизни либерал сороковых и пятидесятых годов. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 18, 'В продолжение своей карьеры он перебывал в связях со многими либеральнейшими людьми своей эпохи, и в России и за границей, знавал лично и Прудона и Бакунина и особенно любил вспоминать и рассказывать, уже под концом своих странствий, о трех днях февральской парижской революции сорок восьмого года, намекая, что чуть ли и сам он не был в ней участником на баррикадах. Это было одно из самых отраднейших воспоминаний его молодости. Имел он состояние независимое, по прежней пропорции около тысячи душ. Превосходное имение его находилось сейчас же на выезде из нашего городка и граничило с землей нашего знаменитого монастыря, с которым Петр Александрович, еще в самых молодых летах, как только получил наследство, мигом начал нескончаемый процесс за право каких-то ловель в реке или порубок в лесу, доподлинно не знаю, но начать процесс с «клерикалами» почел даже своею гражданскою и просвещенною обязанностью. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 19, 'Услышав всё про Аделаиду Ивановну, которую, разумеется, помнил и когда-то даже заметил, и узнав, что остался Митя, он, несмотря на всё молодое негодование свое и презрение к Федору Павловичу, в это дело ввязался. Тут-то он с Федором Павловичем в первый раз и познакомился. Он прямо ему объявил, что желал бы взять воспитание ребенка на себя. Он долго потом рассказывал, в виде характерной черты, что когда он заговорил с Федором Павловичем о Мите, то тот некоторое время имел вид совершенно не понимающего, о каком таком ребенке идет дело, и даже как бы удивился, что у него есть где-то в доме маленький сын. Если в рассказе Петра Александровича могло быть преувеличение, то всё же должно было быть и нечто похожее на правду.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 20, 'Но действительно Федор Павлович всю жизнь свою любил представляться, вдруг проиграть пред вами какую-нибудь неожиданную роль, и, главное, безо всякой иногда надобности, даже в прямой ущерб себе, как в настоящем, например, случае. Черта эта, впрочем, свойственна чрезвычайно многим людям, и даже весьма умным, не то что Федору Павловичу. Петр Александрович повел дело горячо и даже назначен был (купно с Федором Павловичем) в опекуны ребенку, потому что всё же после матери оставалось именьице — дом и поместье. Митя действительно переехал к этому двоюродному дяде, но собственного семейства у того не было, а так как сам он, едва лишь уладив и обеспечив свои денежные получения с своих имений, немедленно поспешил опять надолго в Париж, то ребенка и поручил одной из своих двоюродных теток, одной московской барыне. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 21, 'Конечно, можно представить себе, каким воспитателем и отцом мог быть такой человек. С ним как с отцом именно случилось то, что должно было случиться, то есть он вовсе и совершенно бросил своего ребенка, прижитого с Аделаидой Ивановной, не по злобе к нему или не из каких-нибудь оскорбленно-супружеских чувств, а просто потому, что забыл о нем совершенно. Пока он докучал всем своими слезами и жалобами, а дом свой обратил в развратный вертеп, трехлетнего мальчика Митю взял на свое попечение верный слуга этого дома Григорий, и не позаботься он тогда о нем, то, может быть, на ребенке некому было бы переменить рубашонку.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 22, 'К тому же так случилось, что родня ребенка по матери тоже как бы забыла о нем в первое время. Деда его, то есть самого господина Миусова, отца Аделаиды Ивановны, тогда уже не было в живых; овдовевшая супруга его, бабушка Мити, переехавшая в Москву, слишком расхворалась, сестры же повышли замуж, так что почти целый год пришлось Мите пробыть у слуги Григория и проживать у него в дворовой избе. Впрочем, если бы папаша о нем и вспомнил (не мог же он в самом деле не знать о его существовании), то и сам сослал бы его опять в избу, так как ребенок всё же мешал бы ему в его дебоширстве. Но случилось так, что из Парижа вернулся двоюродный брат покойной Аделаиды Ивановны, Петр Александрович Миусов, многие годы сряду выживший потом за границей, тогда же еще очень молодой человек, но человек особенный между Миусовыми, просвещенный, столичный, заграничный и притом всю жизнь свою европеец, а под конец жизни либерал сороковых и пятидесятых годов. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 23, 'В продолжение своей карьеры он перебывал в связях со многими либеральнейшими людьми своей эпохи, и в России и за границей, знавал лично и Прудона и Бакунина и особенно любил вспоминать и рассказывать, уже под концом своих странствий, о трех днях февральской парижской революции сорок восьмого года, намекая, что чуть ли и сам он не был в ней участником на баррикадах. Это было одно из самых отраднейших воспоминаний его молодости. Имел он состояние независимое, по прежней пропорции около тысячи душ. Превосходное имение его находилось сейчас же на выезде из нашего городка и граничило с землей нашего знаменитого монастыря, с которым Петр Александрович, еще в самых молодых летах, как только получил наследство, мигом начал нескончаемый процесс за право каких-то ловель в реке или порубок в лесу, доподлинно не знаю, но начать процесс с «клерикалами» почел даже своею гражданскою и просвещенною обязанностью. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 24, 'Услышав всё про Аделаиду Ивановну, которую, разумеется, помнил и когда-то даже заметил, и узнав, что остался Митя, он, несмотря на всё молодое негодование свое и презрение к Федору Павловичу, в это дело ввязался. Тут-то он с Федором Павловичем в первый раз и познакомился. Он прямо ему объявил, что желал бы взять воспитание ребенка на себя. Он долго потом рассказывал, в виде характерной черты, что когда он заговорил с Федором Павловичем о Мите, то тот некоторое время имел вид совершенно не понимающего, о каком таком ребенке идет дело, и даже как бы удивился, что у него есть где-то в доме маленький сын. Если в рассказе Петра Александровича могло быть преувеличение, то всё же должно было быть и нечто похожее на правду.');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 25, 'Но действительно Федор Павлович всю жизнь свою любил представляться, вдруг проиграть пред вами какую-нибудь неожиданную роль, и, главное, безо всякой иногда надобности, даже в прямой ущерб себе, как в настоящем, например, случае. Черта эта, впрочем, свойственна чрезвычайно многим людям, и даже весьма умным, не то что Федору Павловичу. Петр Александрович повел дело горячо и даже назначен был (купно с Федором Павловичем) в опекуны ребенку, потому что всё же после матери оставалось именьице — дом и поместье. Митя действительно переехал к этому двоюродному дяде, но собственного семейства у того не было, а так как сам он, едва лишь уладив и обеспечив свои денежные получения с своих имений, немедленно поспешил опять надолго в Париж, то ребенка и поручил одной из своих двоюродных теток, одной московской барыне. ');
INSERT INTO hostel.tguestrooms (language_id, guestrooms_id, description) VALUES (2, 26, 'Конечно, можно представить себе, каким воспитателем и отцом мог быть такой человек. С ним как с отцом именно случилось то, что должно было случиться, то есть он вовсе и совершенно бросил своего ребенка, прижитого с Аделаидой Ивановной, не по злобе к нему или не из каких-нибудь оскорбленно-супружеских чувств, а просто потому, что забыл о нем совершенно. Пока он докучал всем своими слезами и жалобами, а дом свой обратил в развратный вертеп, трехлетнего мальчика Митю взял на свое попечение верный слуга этого дома Григорий, и не позаботься он тогда о нем, то, может быть, на ребенке некому было бы переменить рубашонку.');


INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (1, 1, 'Great Britain', 'London', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque ac semper mauris. Phasellus vel tincidunt diam. Phasellus vulputate libero gravida, congue eros ut, rhoncus dolor. Donec neque velit, dapibus sed egestas et, semper ut arcu. Aliquam pharetra semper ligula vitae placerat. Sed ut est non nisl pulvinar feugiat. Nullam ut semper lectus.', '49 Lillie Rd
Fulham, London
SW6 1UA');
INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (1, 2, 'Russia', 'Moscow', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque ac semper mauris. Phasellus vel tincidunt diam. Phasellus vulputate libero gravida, congue eros ut, rhoncus dolor. Donec neque velit, dapibus sed egestas et, semper ut arcu. Aliquam pharetra semper ligula vitae placerat. Sed ut est non nisl pulvinar feugiat. Nullam ut semper lectus.', 'Krasnopresneskaya 12');
INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (1, 3, 'Great Britain', 'Liverpool', 'Cras pharetra diam condimentum maximus dapibus. Donec sit amet ornare tortor. Donec consectetur ornare dolor, vitae consectetur massa. Vivamus et purus in ligula porttitor lobortis. Nam nisl velit, lobortis in convallis ut, viverra sit amet ligula. In hac habitasse platea dictumst. Etiam molestie dolor consectetur imperdiet pretium. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vulputate odio volutpat, venenatis risus eu, placerat purus. In hac habitasse platea dictumst. Vestibulum sollicitudin metus vitae massa rhoncus, at lobortis odio tristique. Praesent vel tellus ac magna accumsan porttitor eget eu dolor. Maecenas facilisis metus eu felis finibus gravida. Ut venenatis nibh porttitor lobortis pharetra.

Nunc tincidunt nulla in lobortis viverra. Quisque tellus dolor, pharetra non tincidunt a, mattis vel neque. Praesent pulvinar massa vel quam tincidunt iaculis. Nunc pellentesque, velit a laoreet congue, sem purus tincidunt mauris, ut mollis justo ligula non dui. Integer euismod interdum elit, vel porta felis facilisis et. Donec sed tempus justo, non fringilla felis. Vivamus aliquet eleifend porta. Morbi euismod felis et nisi lobortis, at ornare felis ornare. Nam sed tellus nisl. Sed convallis sollicitudin pharetra. Vestibulum in augue lacinia, euismod lacus id, lacinia purus. Suspendisse nec tellus nisl.', '22 Water St
Liverpool
L3 1BN');
INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (1, 4, 'China', 'Peking', 'Nunc tincidunt nulla in lobortis viverra. Quisque tellus dolor, pharetra non tincidunt a, mattis vel neque. Praesent pulvinar massa vel quam tincidunt iaculis. Nunc pellentesque, velit a laoreet congue, sem purus tincidunt mauris, ut mollis justo ligula non dui. Integer euismod interdum elit, vel porta felis facilisis et. Donec sed tempus justo, non fringilla felis. Vivamus aliquet eleifend porta. Morbi euismod felis et nisi lobortis, at ornare felis ornare. Nam sed tellus nisl. Sed convallis sollicitudin pharetra. Vestibulum in augue lacinia, euismod lacus id, lacinia purus. Suspendisse nec tellus nisl.', '125 Zhangyicun Rd, Fengtai Qu, Beijing Shi');
INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (1, 5, 'China', 'Shanghai', 'Nunc tincidunt nulla in lobortis viverra. Quisque tellus dolor, pharetra non tincidunt a, mattis vel neque. Praesent pulvinar massa vel quam tincidunt iaculis. Nunc pellentesque, velit a laoreet congue, sem purus tincidunt mauris, ut mollis justo ligula non dui. Integer euismod interdum elit, vel porta felis facilisis et. Donec sed tempus justo, non fringilla felis. Vivamus aliquet eleifend porta. Morbi euismod felis et nisi lobortis, at ornare felis ornare. Nam sed tellus nisl. Sed convallis sollicitudin pharetra. Vestibulum in augue lacinia, euismod lacus id, lacinia purus. Suspendisse nec tellus nisl.', '89 Pudong Xinqu, Shanghai Shi');
INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (2, 1, 'Великобритания', 'Лондон', 'Алексей Федорович Карамазов был третьим сыном помещика нашего уезда Федора Павловича Карамазова, столь известного в свое время (да и теперь еще у нас припоминаемого) по трагической и темной кончине своей, приключившейся ровно тринадцать лет назад и о которой сообщу в своем месте. Теперь же скажу об этом «помещике» (как его у нас называли, хотя он всю жизнь совсем почти не жил в своем поместье) лишь то, что это был странный тип, довольно часто, однако, встречающийся, именно тип человека не только дрянного и развратного, но вместе с тем и бестолкового, — но из таких, однако, бестолковых, которые умеют отлично обделывать свои имущественные делишки, и только, кажется, одни эти. Федор Павлович, например, начал почти что ни с чем, помещик он был самый маленький, бегал обедать по чужим столам, норовил в приживальщики, а между тем в момент кончины его у него оказалось до ста тысяч рублей чистыми деньгами. И в то же время он все-таки всю жизнь свою продолжал быть одним из бестолковейших сумасбродов по всему нашему уезду. Повторю еще: тут не глупость; большинство этих сумасбродов довольно умно и хитро, — а именно бестолковость, да еще какая-то особенная, национальная.', '49 ул. Лилии
Фулхам, Лондон
SW6 1UA');
INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (2, 2, 'Россия', 'Москва', 'Он был женат два раза, и у него было три сына: старший, Дмитрий Федорович, от первой супруги, а остальные два, Иван и Алексей, от второй. Первая супруга Федора Павловича была из довольно богатого и знатного рода дворян Миусовых, тоже помещиков нашего уезда. Как именно случилось, что девушка с приданым, да еще красивая и, сверх того, из бойких умниц, столь нередких у нас в теперешнее поколение, но появлявшихся уже и в прошлом, могла выйти замуж за такого ничтожного «мозгляка», как все его тогда называли, объяснять слишком не стану. Ведь знал же я одну девицу, еще в запрошлом «романтическом» поколении, которая после нескольких лет загадочной любви к одному господину, за которого, впрочем, всегда могла выйти замуж самым спокойным образом, кончила, однако же, тем, что сама навыдумала себе непреодолимые препятствия и в бурную ночь бросилась с высокого берега, похожего на утес, в довольно глубокую и быструю реку и погибла в ней решительно от собственных капризов, единственно из-за того, чтобы походить на шекспировскую Офелию, и даже так, что будь этот утес, столь давно ею намеченный и излюбленный, не столь живописен, а будь на его месте лишь прозаический плоский берег, то самоубийства, может быть, не произошло бы вовсе. Факт этот истинный, и надо думать, что в нашей русской жизни, в два или три последние поколения, таких или однородных с ним фактов происходило немало.', 'Краснопресненская наб., 12
Москва');
INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (2, 3, 'Великобритания', 'Ливерпуль', 'Подобно тому и поступок Аделаиды Ивановны Миусовой был без сомнения отголоском чужих веяний и тоже пленной мысли раздражением. Ей, может быть, захотелось заявить женскую самостоятельность, пойти против общественных условий, против деспотизма своего родства и семейства, а услужливая фантазия убедила ее, положим, на один только миг, что Федор Павлович, несмотря на свой чин приживальщика, все-таки один из смелейших и насмешливейших людей той, переходной ко всему лучшему, эпохи, тогда как он был только злой шут, и больше ничего. Пикантное состояло еще и в том, что дело обошлось увозом, а это очень прельстило Аделаиду Ивановну. Федор же Павлович на все подобные пассажи был даже и по социальному своему положению весьма тогда подготовлен, ибо страстно желал устроить свою карьеру хотя чем бы то ни было; примазаться же к хорошей родне и взять приданое было очень заманчиво. Что же до обоюдной любви, то ее вовсе, кажется, не было — ни со стороны невесты, ни с его стороны, несмотря даже на красивость Аделаиды Ивановны. Так что случай этот был, может быть, единственным в своем роде в жизни Федора Павловича, сладострастнейшего человека во всю свою жизнь, в один миг готового прильнуть к какой угодно юбке, только бы та его поманила. А между тем одна только эта женщина не произвела в нем со страстной стороны никакого особенного впечатления.', '22 ул. Вотэ, Ливерпуль');
INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (2, 4, 'Китай', 'Пекин', 'Аделаида Ивановна, тотчас же после увоза, мигом разглядела, что мужа своего она только презирает и больше ничего. Таким образом, следствия брака обозначились с чрезвычайною быстротой. Несмотря на то что семейство даже довольно скоро примирилось с событием и выделило беглянке приданое, между супругами началась самая беспорядочная жизнь и вечные сцены. Рассказывали, что молодая супруга выказала при том несравненно более благородства и возвышенности, нежели Федор Павлович, который, как известно теперь, подтибрил у нее тогда же, разом, все ее денежки, до двадцати пяти тысяч, только что она их получила, так что тысячки эти с тех пор решительно как бы канули для нее в воду. Деревеньку же и довольно хороший городской дом, которые тоже пошли ей в приданое, он долгое время и изо всех сил старался перевести на свое имя чрез совершение какого-нибудь подходящего акта и наверно бы добился того из одного, так сказать, презрения и отвращения к себе, которое он возбуждал в своей супруге ежеминутно своими бесстыдными вымогательствами и вымаливаниями, из одной ее душевной усталости, только чтоб отвязался. Но, к счастию, вступилось семейство Аделаиды Ивановны и ограничило хапугу. ', '125 ул. Жангыицун, Фенгтаи');
INSERT INTO hostel.thostel (language_id, hostel_id, country, city, description, address) VALUES (2, 5, 'Китай', 'Шанхай', 'Наконец ему удалось открыть следы своей беглянки. Бедняжка оказалась в Петербурге, куда перебралась с своим семинаристом и где беззаветно пустилась в самую полную эмансипацию. Федор Павлович немедленно захлопотал и стал собираться в Петербург, — для чего? — он, конечно, и сам не знал. Право, может быть, он бы тогда и поехал; но, предприняв такое решение, тотчас же почел себя в особенном праве, для бодрости, пред дорогой, пуститься вновь в самое безбрежное пьянство. И вот в это-то время семейством его супруги получилось известие о смерти ее в Петербурге. Она как-то вдруг умерла, где-то на чердаке, по одним сказаниям — от тифа, а по другим — будто бы с голоду. Федор Павлович узнал о смерти своей супруги пьяный; говорят, побежал по улице и начал кричать, в радости воздевая руки к небу: «Ныне отпущаеши», а по другим — плакал навзрыд как маленький ребенок, и до того, что, говорят, жалко даже было смотреть на него, несмотря на всё к нему отвращение. Очень может быть, что было и то, и другое, то есть что и радовался он своему освобождению, и плакал по освободительнице — всё вместе. В большинстве случаев люди, даже злодеи, гораздо наивнее и простодушнее, чем мы вообще о них заключаем. Да и мы сами тоже.', '89 Пудонг Хиняу');

INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (6, 'Любовь', 'Иванова', 'ivan', '1234', 'ivan@gmail.com', 'user', 1, '+375333963693');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (10, 'Katrin', 'Filma', 'katrin', '1234', 'kate.filmanovich@gmail.com', 'user', 0, '+375333932074');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (11, 'admin', 'admin', 'admin', 'admin', 'lux@gmail.com', 'admin', 0, '+963852417441');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (12, 'ww', 'ww', 'ww', '12', 'we@gmail.com', 'user', 0, '+3693636362');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (13, 'alla', 'kruk', 'kruuk', 'jiji', 'truk@gmail.com', 'user', 0, '+375896325689');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (14, 'Petr', 'Murniv', 'Murik', '294487', 'truer@gmail.com', 'user', 0, '+9632');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (15, 'Борис', 'Фёлкин', 'felkin', '4785932', 'tur@gmail.com', 'admin', 1, '+375893692584');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (16, 'Nino', 'Katamadze', 'katamadze', '1234', 'trutr', 'user', 1, '+375896985241');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (17, 'kevin', 'lol', 'lol', '1234', 'ksjf@kjfls.com', 'user', 0, '+3758965375');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (18, 'elvin', 'burunduk', 'elvin', 'tutu', 'elvin@gmail.com', 'user', 0, '+3756325852');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (19, 'Rosa', 'Stown', 'rosa', '2345', 'rosa@gmail.com', 'user', 0, '+375294102220');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (20, 'Alex', 'White', 'white123', '1234', 'white@gmail.com', 'user', 0, '+375336985202');
INSERT INTO hostel.user (id, name, surname, login, password, email, role, banned, number) VALUES (21, 'Имя', 'Фамилия', 'login', 'password', 'imya@gmail.com', 'user', 0, '+37533333204141');


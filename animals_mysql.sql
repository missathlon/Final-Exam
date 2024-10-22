/*
step 7,8
*/

create schema friends_of_man;
drop schema friends_of_man;
use friends_of_man;

create table type_animal(
type_id int primary key auto_increment,
type_name varchar(30) not null
);

create table animal(
animal_id int primary key auto_increment,
animal_name varchar(30) not null,
animal_type int,
foreign key(animal_type) references type_animal(type_id)
);

insert into type_animal(type_name)
values
('Домашние'),
('Вьючные');

insert into animal(animal_name,animal_type)
values
('Собаки', 1),
('Кошки', 1),
('Хомяки', 1),
('Лошади', 2),
('Верблюды', 2),
('Ослы', 2);

select * from animal;
select * from type_animal;
drop table type_animal;
drop table animal;

/*
step 9
*/

rename table animal to what_animal; 

create table animals(
id int primary key auto_increment,
nickname varchar(30) not null,
birth date,
teams text,
what_animal_id int,
foreign key(what_animal_id) references what_animal(animal_id)
);

insert into animals(nickname,birth,teams,what_animal_id)
values
('Мурка', '2021-05-14', 'Умеет открывать двери', 2),
('Шерлок', '2020-11-01', 'Может находить потерянные предметы', 1),
('Барни', '2022-07-17', 'Крутится на месте по команде', 3),
('Зевс', '2023-02-28', 'Может перепрыгивать через препятствия', 4),
('Чарли', '2020-09-10', 'Ловко балансирует на двух лапах', 5),
('Мила', '2023-06-25', 'Может тянуть телегу', 6),
('Тигра', '2021-08-08', 'Способна высоко прыгать', 2),
('Байрон', '2022-12-11', 'Может находить путь в лабиринте', 1);

select * from animals;
select * from what_animal;
drop table animals;
drop table what_animal;

/*
step 10
*/

delete from animals where what_animal_id = 5;
SET SQL_SAFE_UPDATES = 0;
delete from what_animal where animal_name = 'Верблюды';
SET SQL_SAFE_UPDATES = 1;

CREATE TABLE horses_and_donkeys AS
SELECT * FROM what_animal WHERE animal_name IN ('Лошади', 'Ослы');

CREATE TABLE horses_and_donkeys_details AS
SELECT * FROM animals WHERE what_animal_id in(
	select animal_id from horses_and_donkeys
);

select * from horses_and_donkeys;
select * from horses_and_donkeys_details;
drop table horses_and_donkeys_details;

/*
step 11
*/

create table young_animals as
select 
	id,
    nickname,
    birth,
    teams,
    what_animal_id,
    TIMESTAMPDIFF(year, birth, '2024-10-01') as years,
    TIMESTAMPDIFF(month, birth, '2024-10-01') % 12 as months
from animals
where birth between '2021-10-01' and '2023-10-01';

select * from young_animals;
drop table young_animals;

/*
step 12
*/
CREATE TABLE all_animals(
    id int,
    nickname varchar(30),
    birth date,
    teams text,
    what_animal_id int,
    type_name varchar(30),
    another_type varchar(30),
    source_table varchar(30)
);

insert into all_animals(id,nickname,birth,teams,what_animal_id,source_table)
select 
	id
    ,nickname
    ,birth
    ,teams
    ,what_animal_id
    ,'animals'
from animals;

insert into all_animals(id,nickname,birth,teams,what_animal_id,source_table)
select 
	id
    ,nickname
    ,birth
    ,teams
    ,what_animal_id
    ,'horses_and_donkeys'
from horses_and_donkeys_details;

insert into all_animals(id,nickname,birth,teams,what_animal_id,source_table)
select 
	id
    ,nickname
    ,birth
    ,teams
    ,what_animal_id
    ,'young_animals'
from young_animals;

SET SQL_SAFE_UPDATES = 0;

update all_animals a
join what_animal wa on a.what_animal_id = wa.animal_id
set a.type_name = wa.animal_name;

update all_animals a
join what_animal wa on a.what_animal_id = wa.animal_id
join type_animal ty on wa.animal_type = ty.type_id
set a.another_type = ty.type_name;

insert into all_animals(id,nickname,birth,teams,what_animal_id,source_table,type_name,another_type)
select animal_id, null, null, null, animal_id, 'what_animal', animal_name, null
from what_animal;

insert into all_animals(id,nickname,birth,teams,what_animal_id,source_table,type_name,another_type)
select type_id, null, null, null, null, 'type_animal', null, type_name
from type_animal;

SET SQL_SAFE_UPDATES = 1;

select * from all_animals;
drop table all_animals;
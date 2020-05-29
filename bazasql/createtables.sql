/*
thisconnect@notarch:~$ mysql --version
mysql  Ver 8.0.20-0ubuntu0.20.04.1 for Linux on x86_64 ((Ubuntu))
*/

drop database if exists lab3;
create database lab3 CHARACTER SET utf8 COLLATE utf8_general_ci;
use lab3;

drop table if exists filmy;
create table filmy (
       id int not null auto_increment,
       tytul varchar(50) not null,
       rok int(4) not null,
       czas_trwania int not null,
       budzet int null,
       gatunek varchar(50) not null,
       primary key(id)
);
drop table if exists aktorzy;
create table aktorzy
(
	id int not null auto_increment,
	imie varchar(50) not null,
	nazwisko varchar(50) not null,
	data_ur DATE not null,
	narodowosc varchar(50) not null,
	wzrost int null,
	biografia varchar(255) null,	
	primary key(id)
);
drop table if exists role;
create table role
(
	film_id int not null,
	aktor_id int not null,
	imie varchar(50) null,
	nazwisko varchar(50) null,
	pseudonim varchar(50) null,
	constraint id primary key(film_id,aktor_id),
	foreign key (film_id) references filmy(id),
	foreign key (aktor_id) references aktorzy(id)
);
drop table if exists cytaty;
create table cytaty (
       id int not null auto_increment,
       aktor_id int not null,
       tresc varchar(255) not null,
       primary key(id),
       foreign key (aktor_id) references aktorzy(id)
);

drop table if exists ciekawostki;
create table ciekawostki (
       id int not null auto_increment,
       film_id int not null,
       tresc varchar(512) not null,
       primary key(id),
       foreign key (film_id) references filmy(id)
);

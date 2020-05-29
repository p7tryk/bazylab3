use lab3;

insert into filmy (tytul,rok,czas_trwania,budzet,gatunek) values ('auta',2006,117,666420123,'animowany');
insert into filmy (tytul,rok,czas_trwania,budzet,gatunek) values ('terminator',1984,107,777420123,'akcja');
insert into filmy (tytul,rok,czas_trwania,budzet,gatunek) values ('seksmisja',1984,117,20123,'komedia');
insert into filmy (tytul,rok,czas_trwania,budzet,gatunek) values ('raiders of the lost ark',1981,115,73420123,'przygodowy');
insert into filmy (tytul,rok,czas_trwania,budzet,gatunek) values ('Star wars: Revenge of the Sith',2005,140,213103,'fantastyczny');
insert into filmy (tytul,rok,czas_trwania,budzet,gatunek) values ('Star wars: Empire strikes back',1980,124,5123123,'fantastyczny');
insert into filmy (tytul,rok,czas_trwania,budzet,gatunek) values ('Psy',1992,104,23123,'sensacyjny');


insert into aktorzy (imie,nazwisko,data_ur,narodowosc,wzrost,biografia) values ('arnold','schwarzeneger','1947-07-30','austriacka',181,'test');
insert into aktorzy (imie,nazwisko,data_ur,narodowosc,wzrost,biografia) values ('Jerzy','Stuhr','1947-04-18','polska',164,'test');
insert into aktorzy (imie,nazwisko,data_ur,narodowosc,wzrost,biografia) values ('Olgierd','Łukaszewicz','1946-09-7','polska',172,'test');
insert into aktorzy (imie,nazwisko,data_ur,narodowosc,wzrost,biografia) values ('Ian','McDiarmid','1944-08-11','brytyjska',160,'test');
insert into aktorzy (imie,nazwisko,data_ur,narodowosc,wzrost,biografia) values ('Ewan','McGregor','1971-03-31','brytyjska',184,'');
insert into aktorzy (imie,nazwisko,data_ur,narodowosc,wzrost,biografia) values ('Cezary','Pazura','1946-09-07','polska',172,'test');
insert into aktorzy (imie,nazwisko,data_ur,narodowosc,wzrost,biografia) values ('Alec','Guinness','1914-04-02','brytyjska',184,'umarł 5 sierpnia 2000');

insert into role (film_id,aktor_id,imie,nazwisko,pseudonim) values (2,1,'imie','nazwisko','terminator');
insert into role (film_id,aktor_id,imie,nazwisko,pseudonim) values (3,2,'Maksymilian','Paradys','Maks');
insert into role (film_id,aktor_id,imie,nazwisko,pseudonim) values (3,3,'Albert','Saski','');
insert into role (film_id,aktor_id,imie,nazwisko,pseudonim) values (4,1,'Indiana','Jones','');
insert into role (film_id,aktor_id,imie,nazwisko,pseudonim) values (5,4,'Sheev','Palpatine','Darth Sidious');
insert into role (film_id,aktor_id,imie,nazwisko,pseudonim) values (6,4,'Sheev','Palpatine','Darth Sidious');
insert into role (film_id,aktor_id,imie,nazwisko,pseudonim) values (5,5,'Obi-wan','Kenobi','Master Kenobi');
insert into role (film_id,aktor_id,imie,nazwisko,pseudonim) values (7,6,'Waldemar','Morawiec','Nowy');

insert into cytaty (aktor_id,tresc) values (1,'I ll be back');
insert into cytaty (aktor_id,tresc) values (2,'Kobieta mnie bije');
insert into cytaty (aktor_id,tresc) values (2,'Dzień dobry, zastałem Jolkę?');
insert into cytaty (aktor_id,tresc) values (5,'Its over Anakin, I have the high ground.');
insert into cytaty (aktor_id,tresc) values (6,'Wy wszyscy jesteście pojebani');

insert into ciekawostki (film_id,tresc) values (2,'One afternoon during a break in filming, Arnold Schwarzenegger went into a restaurant in downtown L.A. to get some lunch and realized all too late that he was still in Terminator makeup - with a missing eye, exposed jawbone and burned flesh.');
insert into ciekawostki (film_id,tresc) values (6,'The shots where Luke uses his Jedi powers to retrieve his lightsaber from a distance were achieved by having Mark Hamill throw the lightsaber away, and then running the film in reverse.');
insert into ciekawostki (film_id,tresc) values (4,'Freeze-framing during the Well of Souls scene, you can notice a golden pillar with a tiny engraving of R2-D2 and C-3PO from the Star Wars saga. They are also on the wall behind Indy when they first approach the Ark.');


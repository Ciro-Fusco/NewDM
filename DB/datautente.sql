-- script sql per inserire all'interno della tabella "UTENTE" un nuovo utente
use NEGOZIO;


-- per inserire all'interno della tabella UTENTE 
-- bisogna:
-- inserire uno username(stringa), inserire un nome a cui sarà indirizzato lo user(stringa)
-- inserire un cognome(stringa)
-- l'ultimo campo riguarda l'inserimento della password scritta con la codifica "sha1"
-- per la creazione della password basta cercare un qualunque sito che utilizza questo tipo di codifica.
insert into UTENTE values 
("gigi", "Luigi", "Cirillo", "3277d1158b8e7b677d96e0f6f0005c30035a9f37",true,true,true),
("cirofu", "Ciro", "Fusco", "d0a5b8800f98beb61131c1c025dee5bdf98013c5",true,true,true),
("Young", "Vincenzo", "Aiello", "190ad954707e053c18af1de13a140cb70a6ff58c",true,true,true),
("francuzz", "Franco", "Cirillo", "795442997e4d12e6a8dbeba484b67ae77f7519d6",true,true,true);

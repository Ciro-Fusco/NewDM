DROP DATABASE if exists NEGOZIO;
create database NEGOZIO;
use NEGOZIO;
set global local_infile=1; 
set global transaction isolation level read committed; 

create table UTENTE
( USERNAME varchar(30) not null,
  NOME varchar(30),
  COGNOME varchar(30),
  PASSWORD varchar(600) not null,
  constraint pk_username primary key (USERNAME)
  );
  
create table SCONTRINO
( ID int unsigned auto_increment not null ,
  DATA varchar(30) not null,
  IMPORTO_VERSATO double not null,
  TOTALE double not null,
  RESTO double not null,
  constraint pk_id_Scontrino primary key(ID,DATA)
);

create table PRODOTTO
( CODICE bigint not null,
  NOME varchar(30) not null,
  QUANTITY int unsigned not null,
  PREZZO float not null,
  constraint pk_codice primary key(CODICE)
);

create table ELENCA
(
  ID int unsigned not null ,
  DATA varchar(30) not null,
  CODICE bigint not null,
  constraint pk_id_data_codice primary key( ID,DATA,CODICE),
  constraint fk_id_data foreign key (ID,DATA) references SCONTRINO(ID,DATA) on update cascade,
  constraint fk_codice foreign key (CODICE) references PRODOTTO(CODICE) 
  
);

  
create table RICHIESTA_ACQUISTO
( ID int unsigned auto_increment not null,
  QUANTITY int unsigned not null,
  DATA date not null,
  STATO varchar(20) not null,
  PRODOTTO bigint not null,
  constraint pk_id primary key(ID),
  constraint fk_codice_prodotto foreign key(PRODOTTO) references PRODOTTO(CODICE) on update cascade on delete cascade
);
    
create table TICKET
(
  NOME_CLIENTE varchar(40) not null,
  CODICE_FISCALE char(16) not null,
  INDIRIZZO varchar(100) not null,
  NUM_TEL int not null,
  PRODOTTO bigint not null,
  NUMERO_DI_SERIE varchar(60) not null,
  SCONTRINO int unsigned not null,
  PROBLEMA varchar(400) not null,
  DATA date not null,
  STATO varchar(20) not null,
  constraint pk_cf_data_numSer primary key(DATA,CODICE_FISCALE,NUMERO_DI_SERIE),
  constraint fk_prodotto foreign key(PRODOTTO) references PRODOTTO(CODICE) on update cascade,
  constraint fk_scontrino foreign key(SCONTRINO) references SCONTRINO(ID) on update cascade
);

load data local infile "C:\\Users\\Ciro\\Desktop\\IS\\NewDM\\DB\\datautente.sql"
into table UTENTE fields terminated by",";

load data local infile 'C:\\Users\\Ciro\\Desktop\\IS\\NewDM\\DB\\dataprodotti.sql'
into table PRODOTTO fields terminated by",";

load data local infile 'C:\\Users\\Ciro\\Desktop\\IS\\NewDM\\DB\\datascontrini.sql'
into table SCONTRINO fields terminated by",";






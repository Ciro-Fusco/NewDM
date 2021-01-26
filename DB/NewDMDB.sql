DROP DATABASE if exists NEGOZIO;
create database NEGOZIO;
use NEGOZIO;
set global local_infile=1;
SET GLOBAL event_scheduler = ON;

create table UTENTE
( USERNAME varchar(30) not null,
  NOME varchar(30),
  COGNOME varchar(30),
  PASSWORD varchar(500) not null,
  constraint pk_username primary key (USERNAME)
  );
  
create table SCONTRINO
( ID bigint auto_increment not null ,
  DATA varchar(12) not null,
  ORA varchar(12) not null,
  IMPORTO_VERSATO double not null,
  TOTALE double not null,
  RESTO double not null,
  constraint pk_id_Scontrino primary key(ID,DATA)
);

create table PRODOTTO
( CODICE bigint not null,
  NOME varchar(255) not null,
  QUANTITY int unsigned not null,
  PREZZO double not null,
  TIPOLOGIA varchar(20) not null,
  SCADENZA varchar(10) not null,
  DIMENSIONE_CONFEZIONE varchar(10) not null,
  constraint pk_codice primary key(CODICE)
);

create table ELENCA
(
  ID bigint not null ,
  DATA varchar(12) not null,
  CODICE bigint not null,
  QUANTITY int not null,
  constraint pk_id_data_codice primary key( ID,DATA,CODICE),
  constraint fk_id_data foreign key (ID,DATA) references SCONTRINO(ID,DATA) on update cascade,
  constraint fk_codice foreign key (CODICE) references PRODOTTO(CODICE) 
  
);

  
create table RICHIESTA_ACQUISTO
( ID int unsigned auto_increment not null,
  QUANTITY int unsigned not null,
  DATA varchar(12) not null,
  ORA varchar(12) not null,
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
  NUM_TEL bigint not null,
  TIPO varchar(40) not null,
  NOME_PRODOTTO varchar(50) not null,
  PRODOTTO bigint not null,
  NUMERO_DI_SERIE varchar(60) not null,
  SCONTRINO bigint not null,
  DATA_SCONTRINO varchar(12) not null,
  PROBLEMA varchar(400) not null,
  DATA_APERTURA varchar(30) not null,
  STATO varchar(20) not null,
  constraint pk_cf_data_numSer primary key(DATA_APERTURA,CODICE_FISCALE,NUMERO_DI_SERIE),
  constraint fk_prodotto foreign key(PRODOTTO) references PRODOTTO(CODICE) on update cascade,
  constraint fk_scontrino foreign key(SCONTRINO,DATA_SCONTRINO) references SCONTRINO(ID,DATA) on update cascade
);

DELIMITER //
CREATE PROCEDURE reset_id_scontrino()
	BEGIN
	ALTER TABLE Scontrino AUTO_INCREMENT = 1;
    END //

DELIMITER ;

CREATE EVENT delete_queue_daily
	ON SCHEDULE EVERY 1 DAY STARTS '2021-01-01 00:00:00' 
    ON COMPLETION PRESERVE
    DO CALL reset_id_scontrino();


DROP DATABASE if exists Negozio;
create database Negozio;
use Negozio;
set global local_infile=1; 

Create table Utente
( username varchar(30) not null,
  password varchar(600) not null,
  nome varchar(30),
  cognome varchar(30),
  constraint pk_data_ora primary key (username)
  );
  
create table Scontrino
( id int unsigned not null ,
  data date not null,
  importo_versato double not null,
  totale double not null,
  resto double not null,
  constraint pk_id_Scontrino primary key(id,data)
);

create table Prodotto
( codice bigint not null,
  nome varchar(30) not null,
  quantità int unsigned not null,
  prezzo float not null,
  constraint pk_codice primary key(codice)
);

create table ELENCA
(
  id int unsigned not null ,
  data date not null,
  codice bigint not null,
  constraint pk_id_data_codice primary key( id,data,codice),
  constraint fk_id_data foreign key (id,data) references Scontrino(id,data) on update cascade,
  constraint fk_codice foreign key (codice) references Prodotto(codice) 
  
);

  
create table Richiesta_Acquisto
( id int unsigned auto_increment not null,
  quantità int unsigned not null,
  data date not null,
  stato varchar(20) not null,
  prodotto bigint not null,
  constraint pk_id primary key(id),
  constraint fk_codice_prodotto foreign key(prodotto) references Prodotto(codice) on update cascade on delete cascade
);
    
create table Ticket
(
  nome_cliente varchar(40) not null,
  codice_fiscale char(16) not null,
  indirizzo varchar(100) not null,
  num_tel int not null,
  prodotto bigint not null,
  numero_di_serie varchar(60) not null,
  scontrino int unsigned not null,
  problema varchar(400) not null,
  data date not null,
  stato varchar(20) not null,
  constraint pk_cf_data_numSer primary key(data,codice_fiscale,numero_di_serie),
  constraint fk_prodotto foreign key(prodotto) references Prodotto(codice) on update cascade,
  constraint fk_scontrino foreign key(scontrino) references Scontrino(id) on update cascade
);

load data local infile 'C:\Users\Ciro\Desktop\IS\DB\creazione e popolamento\dataprodotti.sql'
into table Prodotto fields terminated by",";

load data local infile 'C:\Users\Ciro\Desktop\IS\DB\creazione e popolamento\datascontrini.sql'
into table Scontrino fields terminated by",";






CREATE TABLE cidade (
	id bigserial not null,
	nome varchar(100) not null,
	estado varchar (2) not null
);
ALTER TABLE cidade ADD CONSTRAINT id_cidade_pk PRIMARY KEY (id);
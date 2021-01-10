CREATE TABLE cidade (
	id bigserial not null,
	nome varchar(100) not null,
	estado varchar (2) not null
);
ALTER TABLE cidade ADD CONSTRAINT id_cidade_pk PRIMARY KEY (id);

CREATE TABLE cliente (
	id bigserial not null,
	nome varchar(100) not null,
	sexo varchar (1) null,
	data_nascimento date null,
	idade integer null,
	id_cidade integer null
);
ALTER TABLE cliente ADD CONSTRAINT id_cliente_pk PRIMARY KEY (id);
ALTER TABLE cliente ADD CONSTRAINT id_fk_cidade FOREIGN KEY(id_cidade) REFERENCES cidade (id);
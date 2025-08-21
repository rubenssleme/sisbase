CREATE TABLE CONDICAO_MORADIA
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE HABITACAO
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE INFRAESTRUTURA_BASICA
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE SITUACAO_HABITACIONAL
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE TIPO_CONSTRUCAO
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE USUARIO_INFRAESTRUTURA_BASICA
(
	ID_INFRAESTRUTURA_BASICA BIGINT NOT NULL,
	ID_USUARIO BIGINT NOT NULL
) WITHOUT OIDS;

ALTER TABLE USUARIO ADD COLUMN ID_CONDICAO_MORADIA BIGINT;
ALTER TABLE USUARIO ADD COLUMN ID_SITUACAO_HABITACIONAL BIGINT;
ALTER TABLE USUARIO ADD COLUMN ID_HABITACAO BIGINT;
ALTER TABLE USUARIO ADD COLUMN ID_TIPO_CONSTRUCAO BIGINT;

ALTER TABLE USUARIO
	ADD FOREIGN KEY (ID_CONDICAO_MORADIA)
	REFERENCES CONDICAO_MORADIA (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO
	ADD FOREIGN KEY (ID_HABITACAO)
	REFERENCES HABITACAO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_INFRAESTRUTURA_BASICA
	ADD FOREIGN KEY (ID_INFRAESTRUTURA_BASICA)
	REFERENCES INFRAESTRUTURA_BASICA (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO
	ADD FOREIGN KEY (ID_SITUACAO_HABITACIONAL)
	REFERENCES SITUACAO_HABITACIONAL (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO
	ADD FOREIGN KEY (ID_TIPO_CONSTRUCAO)
	REFERENCES TIPO_CONSTRUCAO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_SANEAMENTO_BASICO
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

insert into condicao_moradia(descricao)values('Alugada');
insert into condicao_moradia(descricao)values('Favor');
insert into condicao_moradia(descricao)values('Própria');
insert into condicao_moradia(descricao)values('Cedida');

insert into situacao_habitacional(descricao)values('Instituição: Serviço de Acolhimento');
insert into situacao_habitacional(descricao)values('Pensão');
insert into situacao_habitacional(descricao)values('Cortiço');
insert into situacao_habitacional(descricao)values('Favela');
insert into situacao_habitacional(descricao)values('Loteamento Regular');
insert into situacao_habitacional(descricao)values('Loteamento Irregular');

insert into habitacao(descricao)values('Sobrado');
insert into habitacao(descricao)values('Terrea');
insert into habitacao(descricao)values('Apartamento');

insert into tipo_construcao(descricao)values('Madeira');
insert into tipo_construcao(descricao)values('Alvenaria');
insert into tipo_construcao(descricao)values('Mista');

insert into INFRAESTRUTURA_BASICA(descricao)values('Água');
insert into INFRAESTRUTURA_BASICA(descricao)values('Asfalto');
insert into INFRAESTRUTURA_BASICA(descricao)values('Esgoto');
insert into INFRAESTRUTURA_BASICA(descricao)values('Coleta de Lixo');
insert into INFRAESTRUTURA_BASICA(descricao)values('Telefone');
insert into INFRAESTRUTURA_BASICA(descricao)values('Rede Elétrica');
insert into INFRAESTRUTURA_BASICA(descricao)values('Rede de Gás');






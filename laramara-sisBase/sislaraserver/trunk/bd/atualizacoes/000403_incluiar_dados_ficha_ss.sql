CREATE TABLE EXPECTATIVA
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE NECESSIDADE
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE RECURSO_MORADIA
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE USUARIO_EXPECTATIVA
(
	ID_USUARIO BIGINT NOT NULL,
	ID_EXPECTATIVA BIGINT NOT NULL
) WITHOUT OIDS;

CREATE TABLE USUARIO_NECESSIDADE
(
	ID_NECESSIDADE BIGINT NOT NULL,
	ID_USUARIO BIGINT NOT NULL
) WITHOUT OIDS;

CREATE TABLE USUARIO_RECURSO_MORADIA
(
	ID_USUARIO BIGINT NOT NULL,
	ID_RECURSO_MORADIA BIGINT NOT NULL
) WITHOUT OIDS;

ALTER TABLE USUARIO_EXPECTATIVA
	ADD FOREIGN KEY (ID_EXPECTATIVA)
	REFERENCES EXPECTATIVA (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_NECESSIDADE
	ADD FOREIGN KEY (ID_NECESSIDADE)
	REFERENCES NECESSIDADE (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_RECURSO_MORADIA
	ADD FOREIGN KEY (ID_RECURSO_MORADIA)
	REFERENCES RECURSO_MORADIA (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_EXPECTATIVA
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_NECESSIDADE
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_RECURSO_MORADIA
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

insert into recurso_moradia(descricao) values ('Associação de Bairro');
insert into recurso_moradia(descricao) values ('Biblioteca');
insert into recurso_moradia(descricao) values ('Centro Cultural'); 
insert into recurso_moradia(descricao) values ('Centro de Convivência da Juventude');
insert into recurso_moradia(descricao) values ('Centro de Convivência da Pessoa com Deficiência');
insert into recurso_moradia(descricao) values ('Centro de Convivência do Idoso');
insert into recurso_moradia(descricao) values ('Centro Esportivo');
insert into recurso_moradia(descricao) values ('CER');
insert into recurso_moradia(descricao) values ('CRAS');
insert into recurso_moradia(descricao) values ('CREAS');
insert into recurso_moradia(descricao) values ('Creche');
insert into recurso_moradia(descricao) values ('Escola');
insert into recurso_moradia(descricao) values ('Hospitais');
insert into recurso_moradia(descricao) values ('Igreja');
insert into recurso_moradia(descricao) values ('INSS');
insert into recurso_moradia(descricao) values ('Museus');
insert into recurso_moradia(descricao) values ('NIR');
insert into recurso_moradia(descricao) values ('NISA');
insert into recurso_moradia(descricao) values ('Parques Públicos');
insert into recurso_moradia(descricao) values ('Posto de Saúde');
insert into recurso_moradia(descricao) values ('Poupa Tempo / Centro da Cidadania');
insert into recurso_moradia(descricao) values ('Rede de Assistência de Saúde Conveniada');
insert into recurso_moradia(descricao) values ('SER');
insert into recurso_moradia(descricao) values ('Serviço de habilitação e reabilitação');
insert into recurso_moradia(descricao) values ('SESC');
insert into recurso_moradia(descricao) values ('Shopping Center');
insert into recurso_moradia(descricao) values ('Teatro');

insert into necessidade(descricao) values ('Acessibilidade');
insert into necessidade(descricao) values ('Acompanhante');
insert into necessidade(descricao) values ('Adoção');
insert into necessidade(descricao) values ('AVAS');
insert into necessidade(descricao) values ('Braille');
insert into necessidade(descricao) values ('Conhecer Legislação/Direitos');
insert into necessidade(descricao) values ('Cuidador');
insert into necessidade(descricao) values ('Equoterapia');
insert into necessidade(descricao) values ('Estimulação Global');
insert into necessidade(descricao) values ('Estimulação Visual');
insert into necessidade(descricao) values ('Fisioterapia');
insert into necessidade(descricao) values ('Fonoaudiologia');
insert into necessidade(descricao) values ('Hidroterapia');
insert into necessidade(descricao) values ('Informática');
insert into necessidade(descricao) values ('OM');
insert into necessidade(descricao) values ('Orientação para escola');
insert into necessidade(descricao) values ('Orientação para família');
insert into necessidade(descricao) values ('Reabilitação');
insert into necessidade(descricao) values ('Recursos');
insert into necessidade(descricao) values ('Soroban');
insert into necessidade(descricao) values ('Terapia Ocupacional');
insert into necessidade(descricao) values ('Trabalho');

insert into expectativa(descricao) values ('Avaliação');
insert into expectativa(descricao) values ('Orientação');
insert into expectativa(descricao) values ('Atendimento Especializado');
insert into expectativa(descricao) values ('Habilitação e Reabilitação');
insert into expectativa(descricao) values ('Reabilitação Profissional');
insert into expectativa(descricao) values ('Socialização');
insert into expectativa(descricao) values ('Cursos e Oficinas');
insert into expectativa(descricao) values ('Encaminhamento para Trabalho');
insert into expectativa(descricao) values ('Apoio Educacional');


ALTER TABLE familiar RENAME COLUMN CUIDA_DO_USUARIO TO RESPONSAVEL_PELO_USUARIO;

CREATE TABLE SERVICO
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE USUARIO_SERVICO
(
	ID_USUARIO BIGINT NOT NULL,
	ID_SERVICO BIGINT NOT NULL
) WITHOUT OIDS;

ALTER TABLE USUARIO_SERVICO
	ADD FOREIGN KEY (ID_SERVICO)
	REFERENCES SERVICO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_SERVICO
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

insert into servico(descricao)values('Atividades físicas (Ginástica, Natação, Condicionamento Físico etc)');
insert into servico(descricao)values('Cardiologia');
insert into servico(descricao)values('Dermatologia');
insert into servico(descricao)values('Endocrinologia');
insert into servico(descricao)values('Equoterapia');
insert into servico(descricao)values('Fisiatria');
insert into servico(descricao)values('Fisioterapia');
insert into servico(descricao)values('Fonoaudiologia');
insert into servico(descricao)values('Genética');
insert into servico(descricao)values('Geriatria');
insert into servico(descricao)values('Ginecologia');
insert into servico(descricao)values('Herbiatria');
insert into servico(descricao)values('Hidroterapia');
insert into servico(descricao)values('Home Care');
insert into servico(descricao)values('Nefrologia');
insert into servico(descricao)values('Neurologia');
insert into servico(descricao)values('Nutrição');
insert into servico(descricao)values('Odontologia');
insert into servico(descricao)values('Oftalmologia');
insert into servico(descricao)values('Oncologia');
insert into servico(descricao)values('Ortopedia');
insert into servico(descricao)values('Ortóptica');
insert into servico(descricao)values('Pediatria');
insert into servico(descricao)values('Psicologia');
insert into servico(descricao)values('Psicopedagogia');
insert into servico(descricao)values('Psiquiatria');
insert into servico(descricao)values('Reumatologia');
insert into servico(descricao)values('Serviço Social');
insert into servico(descricao)values('Terapia Ocupacional');
insert into servico(descricao)values('Urologia');


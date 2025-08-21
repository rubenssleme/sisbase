CREATE TABLE DOENCA
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE PERIODO_DOENCA
(
	ID BIGSERIAL NOT NULL,
	ID_DOENCA BIGINT NOT NULL,
	DATA_INICIAL_VIGENCIA TIMESTAMP NOT NULL,
	DATA_FINAL_VIGENCIA TIMESTAMP,
	OBS VARCHAR(20000),
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE USUARIO_PERIODO_DOENCA
(
	ID_PERIODO_DOENCA BIGINT NOT NULL,
	ID_USUARIO BIGINT NOT NULL
) WITHOUT OIDS;

ALTER TABLE PERIODO_DOENCA
	ADD FOREIGN KEY (ID_DOENCA)
	REFERENCES DOENCA (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_PERIODO_DOENCA
	ADD FOREIGN KEY (ID_PERIODO_DOENCA)
	REFERENCES PERIODO_DOENCA (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_PERIODO_DOENCA
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO ADD COLUMN CIRURGIAS VARCHAR(20000);
ALTER TABLE USUARIO ADD COLUMN MEDICAMENTOS VARCHAR(20000);
ALTER TABLE USUARIO ADD COLUMN CONSANGUINIDADE VARCHAR(20000);

insert into doenca(id, descricao)values(1, 'Diabetes');
insert into doenca(id, descricao)values(2, 'Hipertensão');
insert into doenca(id, descricao)values(3, 'Doenças cardíacas');
insert into doenca(id, descricao)values(4, 'Doenças vasculares');
insert into doenca(id, descricao)values(5, 'Doenças reumáticas');
insert into doenca(id, descricao)values(6, 'Doenças de pele');
insert into doenca(id, descricao)values(7, 'Doenças renais');
insert into doenca(id, descricao)values(8, 'Doenças hepáticas');
insert into doenca(id, descricao)values(9, 'Doenças gastrointestinais');
insert into doenca(id, descricao)values(10, 'HIV');
insert into doenca(id, descricao)values(11, 'Anemia');
insert into doenca(id, descricao)values(12, 'Alterações neurológicas');
insert into doenca(id, descricao)values(13, 'Câncer');
insert into doenca(id, descricao)values(14, 'Transtornos/Doenças mentais');
insert into doenca(id, descricao)values(15, 'Alergias');
insert into doenca(id, descricao)values(16, 'Doenças respiratórias');
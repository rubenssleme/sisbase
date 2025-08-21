CREATE TABLE CUSTO
(
	ID BIGSERIAL NOT NULL,
	ID_ITEM_CUSTO BIGINT NOT NULL,
	VALOR DECIMAL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE ITEM_CUSTO
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(50) NOT NULL,
	DEFICIENCIA BOOLEAN DEFAULT 'false',
	DOENCA BOOLEAN DEFAULT 'false',
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE USUARIO_CUSTO_DEFICIENCIA
(
	ID_USUARIO BIGINT NOT NULL,
	ID_CUSTO BIGINT NOT NULL
) WITHOUT OIDS;

CREATE TABLE USUARIO_CUSTO_DOENCA
(
	ID_USUARIO BIGINT NOT NULL,
	ID_CUSTO BIGINT NOT NULL
) WITHOUT OIDS;

ALTER TABLE CUSTO
	ADD FOREIGN KEY (ID_ITEM_CUSTO)
	REFERENCES ITEM_CUSTO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_CUSTO_DEFICIENCIA
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE USUARIO_CUSTO_DOENCA
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

insert into item_custo(id, descricao, doenca, deficiencia)values(1, 'Transporte', true, true);
insert into item_custo(id, descricao, doenca, deficiencia)values(2, 'Recursos Específicos', true, true);
insert into item_custo(id, descricao, doenca, deficiencia)values(3, 'Medicamentos', true, true);
insert into item_custo(id, descricao, doenca, deficiencia)values(4, 'Alimentação', true, true);
insert into item_custo(id, descricao, doenca, deficiencia)values(5, 'Terapias', true, true);
insert into item_custo(id, descricao, doenca, deficiencia)values(6, 'Atendimento Especializado', false, true);  
insert into item_custo(id, descricao, doenca, deficiencia)values(7, 'Serviços Médicos', true, true);
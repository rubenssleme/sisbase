CREATE TABLE BLOQUEIO
(
	ID BIGSERIAL NOT NULL,
	ID_DESCRICAO_TIPO_ATENDIMENTO BIGINT NOT NULL,
	ID_MODULO BIGINT NOT NULL,
	AREA VARCHAR(50),
	PRIMARY KEY (ID)
) WITHOUT OIDS;

ALTER TABLE modulo DROP COLUMN bloqueado_lista_espera;

insert into bloqueio(id_descricao_tipo_atendimento, id_modulo, area) values(39, 38, 'LISTA_ESPERA');
insert into bloqueio(id_descricao_tipo_atendimento, id_modulo, area) values(37, 38, 'LISTA_ESPERA');
insert into bloqueio(id_descricao_tipo_atendimento, id_modulo, area) values(41, 1, 'LISTA_ESPERA');
insert into bloqueio(id_descricao_tipo_atendimento, id_modulo, area) values(43, 1, 'LISTA_ESPERA');
insert into bloqueio(id_descricao_tipo_atendimento, id_modulo, area) values(39, 38, 'AGENDA');
insert into bloqueio(id_descricao_tipo_atendimento, id_modulo, area) values(37, 38, 'AGENDA');
insert into bloqueio(id_descricao_tipo_atendimento, id_modulo, area) values(41, 1, 'AGENDA');
insert into bloqueio(id_descricao_tipo_atendimento, id_modulo, area) values(43, 1, 'AGENDA');

update espera set id_descricao_tipo_atendimento = 40 where id_descricao_tipo_atendimento = 41 and id_modulo = 1;
update espera set id_descricao_tipo_atendimento = 42 where id_descricao_tipo_atendimento = 43 and id_modulo = 1;

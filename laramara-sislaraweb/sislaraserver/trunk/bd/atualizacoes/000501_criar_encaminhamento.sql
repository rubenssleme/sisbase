CREATE TABLE ENCAMINHAMENTO
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100),
	PRIMARY KEY (ID)
) WITHOUT OIDS;

CREATE TABLE USUARIO_ENCAMINHAMENTO
(
	ID_USUARIO BIGINT NOT NULL,
	ID_ENCAMINHAMENTO BIGINT NOT NULL
) WITHOUT OIDS;

ALTER TABLE USUARIO_ENCAMINHAMENTO
	ADD FOREIGN KEY (ID_ENCAMINHAMENTO)
	REFERENCES ENCAMINHAMENTO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE USUARIO_ENCAMINHAMENTO
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

insert into encaminhamento(descricao)values('Internos (avaliação / orientação / doação / atendimento / cursos e oficinas / visita domiciliar)');
insert into encaminhamento(descricao)values('Associação de Bairro');
insert into encaminhamento(descricao)values('Benefícios socioassistenciais (link com os benefícios que estão na Aba Outras do usuário)');
insert into encaminhamento(descricao)values('Centro Cultural');
insert into encaminhamento(descricao)values('Centro de Convivência da Pessoa com Deficiência');
insert into encaminhamento(descricao)values('Centro de Convivência da Juventude');
insert into encaminhamento(descricao)values('Centro de Convivência do Idoso');
insert into encaminhamento(descricao)values('Centro Esportivo');
insert into encaminhamento(descricao)values('CER (SUS)');
insert into encaminhamento(descricao)values('Conselho da Pessoa com Deficiência');
insert into encaminhamento(descricao)values('Conselho do Idoso');
insert into encaminhamento(descricao)values('Conselho Tutelar');
insert into encaminhamento(descricao)values('CRAS (SUAS)');
insert into encaminhamento(descricao)values('CREAS (SUAS)');
insert into encaminhamento(descricao)values('Delegacia da Mulher');
insert into encaminhamento(descricao)values('Delegacia da Pessoa com Deficiência');
insert into encaminhamento(descricao)values('INSS');
insert into encaminhamento(descricao)values('Mercado de Trabalho');
insert into encaminhamento(descricao)values('Ministério Público');
insert into encaminhamento(descricao)values('Serviço de documentação');
insert into encaminhamento(descricao)values('Serviço de Educação');
insert into encaminhamento(descricao)values('Serviços de Saúde');
insert into encaminhamento(descricao)values('Serviços Especializados em Deficiência');
insert into encaminhamento(descricao)values('Serviços socioassistenciais governamentais');
insert into encaminhamento(descricao)values('Serviços socioassistenciais não governamentais');

alter table familiar add column FALECIDO BOOLEAN DEFAULT 'false';
update familiar set falecido = true where status = 'FALECIDO';
alter table familiar drop column status;

update periodo_deficiencia set id_deficiencia = 2 where id_deficiencia in (6, 7, 8, 9);
delete from deficiencia where id in (6, 7, 8, 9);

insert into comprometimento(id, descricao)values(9, 'Ausência de outros comprometimentos');
insert into comprometimento(id, descricao)values(10, 'Distúrbios do comportamento');
insert into comprometimento(id, descricao)values(11, 'Cognitivo');
update periodo_comprometimento set id_comprometimento = 11 where id_comprometimento in (3);
delete from comprometimento where id in (3, 4, 5, 6, 7, 8);


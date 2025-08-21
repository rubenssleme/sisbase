CREATE TABLE USUARIO_VULNERABILIDADE_FAMILIA
(
	ID_USUARIO BIGINT NOT NULL,
	ID_VULNERABILIDADE BIGINT NOT NULL
) WITHOUT OIDS;


CREATE TABLE USUARIO_VULNERABILIDADE_USUARIO
(
	ID_USUARIO BIGINT NOT NULL,
	ID_VULNERABILIDADE BIGINT NOT NULL
) WITHOUT OIDS;


CREATE TABLE VULNERABILIDADE
(
	ID BIGSERIAL NOT NULL,
	DESCRICAO VARCHAR(100) NOT NULL,
	DISPONIVEL_PARA_USUARIO BOOLEAN DEFAULT 'false' NOT NULL,
	DISPONIVEL_PARA_FAMILIA BOOLEAN DEFAULT 'false' NOT NULL,
	PRIMARY KEY (ID)
) WITHOUT OIDS;

ALTER TABLE USUARIO_VULNERABILIDADE_FAMILIA
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USUARIO_VULNERABILIDADE_USUARIO
	ADD FOREIGN KEY (ID_USUARIO)
	REFERENCES USUARIO (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USUARIO_VULNERABILIDADE_FAMILIA
	ADD FOREIGN KEY (ID_VULNERABILIDADE)
	REFERENCES VULNERABILIDADE (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USUARIO_VULNERABILIDADE_USUARIO
	ADD FOREIGN KEY (ID_VULNERABILIDADE)
	REFERENCES VULNERABILIDADE (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Acolhimento Institucional', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Alcoolismo', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Baixo nivel de escolaridade', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Baixo auto-estima frente à deficiência e à idade avançada', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Carência economica e familiar', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Deficiência auditiva', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Deficiência intelectual', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Deficiência visual', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Desemprego', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Doença Psiquiatrica', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Drogadição', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('HIV +', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Inatividade da maioria das pessoas idosas e com deficiência', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Impossibilidade de trabalhar devido ao tratamento / cuidador do filho com deficiência', true, false);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Medida Sócio Educadiva', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Precarias relações com o meio onde vive', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Privação de Liberdade', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Relações familizares fragilizadas', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Reduzida oferta de serviços comunitarios e sociais', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Situação de Rua', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Trabalho Infantil', true, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Violência Doméstica', true, true);

insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Abandono', false, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Falta de acompanhante / apoio', false, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Inexistência de oferta de serviços de saúde proximos ao local de moradia', false, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Inexistência de oferta de serviços educacionais proximos ao local de moradia', false, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Inexistência ou dificuldade de transporte para deslocamento casa-escola-casa.', false, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Inexistência ou dificuldade de transporte para deslocamento casa-instituição-casa.', false, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Negligencia dos pais ou responsáveis', false, true);
insert into vulnerabilidade(descricao, disponivel_para_familia, disponivel_para_usuario) values ('Violência ou discriminação no ambiente escolar', false, true);


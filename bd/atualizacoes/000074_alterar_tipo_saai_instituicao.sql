ALTER TABLE instituicao_tipo_saai RENAME TO instituicao_tipo_especialidade;
ALTER TABLE instituicao_tipo_especialidade RENAME tipo_saai TO tipo_especialidade;
ALTER TABLE INSTITUICAO ADD COLUMN TIPO_APOIO VARCHAR(20);
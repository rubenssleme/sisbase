alter table usuario_externo add column e_pessoa_com_deficiencia BOOLEAN DEFAULT 'false';
alter table usuario_externo add column possui_baixa_visao BOOLEAN DEFAULT 'false';
alter table usuario_externo add column possui_cegueira BOOLEAN DEFAULT 'false';
alter table usuario_externo add column outra_deficiencia VARCHAR(100);
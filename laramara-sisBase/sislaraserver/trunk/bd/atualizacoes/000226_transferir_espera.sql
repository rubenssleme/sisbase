alter table tipo_atendimento add column INATIVO BOOLEAN DEFAULT 'false' NOT NULL;
update tipo_atendimento set inativo = true where id = 3;
update espera set id_descricao_tipo_atendimento = 61, id_modulo = 26 where id_descricao_tipo_atendimento = 22 and id_modulo = 26;
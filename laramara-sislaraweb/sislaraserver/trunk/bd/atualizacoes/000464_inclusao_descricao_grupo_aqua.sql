update tipo_atendimento set nome = 'Cultura, Lazer e Esportes' where id = 4;
insert into descricao_tipo_atendimento(id, nome, sigla, id_tipo_atendimento)values(65, 'Atividades Físicas', 'AQUA', 4);
insert into modulo(id, nome) values (144, 'Atividades Aquáticas');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(65, 144);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(4, 65);
insert into nome_grupo(id, nome)values(73, 'AQUA');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(65, 73);
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(65, 'CTO');
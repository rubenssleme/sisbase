insert into descricao_tipo_atendimento (id, nome, sigla, descricao, id_tipo_atendimento, inativo)values(63, 'AE Comunicação', 'COM', '', 1, false);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(63, 1);
insert into nome_grupo(id, nome)values(71, 'COM');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(63, 71);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(1, 63);
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(63, 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(63, 'PROCEJA');
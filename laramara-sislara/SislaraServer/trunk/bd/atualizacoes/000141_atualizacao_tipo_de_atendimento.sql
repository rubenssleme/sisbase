insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values (59, 1, 'AEE Transtorno Global do Desenvolvimento', 'TGD', '');
insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values (60, 1, 'AEE Atividades de Vida Autônoma', 'AVA', '');

insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(59, 1);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(60, 1);

insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(1, 59);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(1, 60);

insert into nome_grupo(id, nome)values(67,'TGD');
insert into nome_grupo(id, nome)values(68,'AVA');

insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(59, 67);
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(60, 68);

insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(59, 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(59, 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(60, 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(60, 'CTO');

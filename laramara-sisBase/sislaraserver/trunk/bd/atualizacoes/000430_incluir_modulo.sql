insert into modulo (id, nome) values (140, 'Autonomia e independência');
insert into modulo (id, nome) values (141, 'Educação tecnológica');
insert into modulo (id, nome) values (142, 'Orientação vocacional e profissional');
insert into modulo (id, nome) values (143, 'Histórias de escola');

insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(1, 140);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(1, 141);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(1, 142);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(1, 143);

insert into nome_grupo values (72, 'GAdol');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(1, 72);

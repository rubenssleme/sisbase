insert into modulo (id, nome)values(123, 'Gincana Cultural');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(24, 123);
update tipo_atendimento set nome = 'Defesa e Garantia de Direitos' where id = 14;

insert into nome_grupo(id, nome) values(69, 'DIR');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(62, 69);
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(62, 'PROCEJA');
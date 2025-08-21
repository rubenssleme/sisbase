insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(59, 90);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(59, 106);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(63, 90);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(63, 106);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(60, 90);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(60, 106);

update espera set id_descricao_tipo_atendimento = 17, id_modulo = 1 where id in (5853, 5859, 6051, 6771, 6835);
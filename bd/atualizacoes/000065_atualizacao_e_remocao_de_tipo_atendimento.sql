update agendamento set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 20 and id_modulo = 1;
update agendamento set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 20 and id_modulo = 95;
update agendamento set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 20 and id_modulo = 96;

update espera set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 20 and id_modulo = 1;
update espera set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 20 and id_modulo = 95;
update espera set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 20 and id_modulo = 96;

update atendimento_individual set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 20 and id_modulo = 1;
update atendimento_individual set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 20 and id_modulo = 95;
update atendimento_individual set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 20 and id_modulo = 96;


update agendamento set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 39 and id_modulo = 1;
update agendamento set id_descricao_tipo_atendimento = 37, id_modulo = 38 where id_descricao_tipo_atendimento = 37 and id_modulo = 1; 

update espera set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 39 and id_modulo = 1;
update espera set id_descricao_tipo_atendimento = 37, id_modulo = 38 where id_descricao_tipo_atendimento = 37 and id_modulo = 1; 

update atendimento_individual set id_descricao_tipo_atendimento = 39, id_modulo = 38 where id_descricao_tipo_atendimento = 39 and id_modulo = 1;
update atendimento_individual set id_descricao_tipo_atendimento = 37, id_modulo = 38 where id_descricao_tipo_atendimento = 37 and id_modulo = 1; 

delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 39 and id_modulo = 1;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 37 and id_modulo = 1;

update modulo set nome = 'Avaliação e Triagem' where id = 37;
update descricao_tipo_atendimento set inativo = true where id = 20;
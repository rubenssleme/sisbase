update espera set id_descricao_tipo_atendimento=37, id_modulo=37 where id_descricao_tipo_atendimento=37 and id_modulo=38;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento=37 and id_modulo=38;

update espera set id_descricao_tipo_atendimento=39, id_modulo=37 where id_descricao_tipo_atendimento=39 and id_modulo=38;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento=39 and id_modulo=38;

update modulo set nome = 'Avaliação - Acompanhamento' where id = 37; 
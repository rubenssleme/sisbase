insert into profissional(nome, habilitado, voluntario, profissional)values('AYDÊ ALMEIDA DOS SANTOS', true, true, true); 
insert into profissional(nome, habilitado, voluntario, profissional)values('DANIEL MONTEIRO', true, true, true); 
update profissional set habilitado = true where id = 14;
insert into modulo(id, nome)values(128, 'Virada inclusiva');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(62, 128);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(62, 120);
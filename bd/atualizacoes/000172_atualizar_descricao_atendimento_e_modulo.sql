update modulo set nome = 'Curso de Autonomia e Independência' where id = 109;
update modulo set nome = 'Curso de Comunicação e Expressão' where id = 49;
update modulo set nome = 'Curso de Inglês' where id = 23;
update modulo set nome = 'Curso de Mundo do Trabalho' where id = 115;
update modulo set nome = 'Curso de Projeto de Vida ' where id = 25;

insert into modulo values (124, 'Orientação Profissional');
insert into modulo values (125, 'Encaminhamento Profissional');
insert into modulo values (126, 'Curso de Cidadania e Direitos Sociais');

insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values (61,109);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values (61,49);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values (61,23);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values (61,25);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values (61,26);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values (61,124);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values (61,125);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values (62,126);

delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 62 and id_modulo = 120;

update descricao_tipo_atendimento set nome = 'Orientação Socioeducativa' where id = 16;

delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 16 and id_modulo = 42;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 16 and id_modulo = 22;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 16 and id_modulo = 23;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 16 and id_modulo = 25;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 16 and id_modulo = 108;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 16 and id_modulo = 109;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 16 and id_modulo = 110;

insert into nome_grupo(id, nome)values(70, 'MT');

update nome_grupo set nome = 'OSE' where id = 17;
update descricao_tipo_atendimento set sigla = 'OSE' where id = 16;
update descricao_tipo_atendimento set sigla = 'DIR' where id = 62;

insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo) values (61, 70);

update grupo set id_nome_grupo = 69, id_descricao_tipo_atendimento = 62, turma = '01' where id = 375;
update modulo_periodo set id_modulo = 126 where id = 580;

update grupo set id_nome_grupo = 70, id_descricao_tipo_atendimento = 61, turma = '01' where id = 367;
update modulo_periodo set id_modulo = 49 where id = 572;

update grupo set id_nome_grupo = 70, id_descricao_tipo_atendimento = 61, turma = '02' where id = 377;
update modulo_periodo set id_modulo = 23 where id = 582;

update grupo set id_nome_grupo = 70, id_descricao_tipo_atendimento = 61, turma = '03' where id = 365;
update modulo_periodo set id_modulo = 109 where id = 570;

update grupo set id_nome_grupo = 70, id_descricao_tipo_atendimento = 61, turma = '04' where id = 376;
update modulo_periodo set id_modulo = 115 where id = 581;

update grupo set id_nome_grupo = 70, id_descricao_tipo_atendimento = 61, turma = '05' where id = 368;
update modulo_periodo set id_modulo = 25 where id = 573;

update grupo set id_nome_grupo = 70, id_descricao_tipo_atendimento = 61, turma = '06' where id = 374;
update modulo_periodo set id_modulo = 23 where id = 579;
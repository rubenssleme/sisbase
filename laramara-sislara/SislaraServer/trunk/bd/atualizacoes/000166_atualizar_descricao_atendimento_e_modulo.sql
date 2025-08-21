update descricao_tipo_atendimento set inativo = true where id = 21;

delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 16 and id_modulo = 21;
delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 16 and id_modulo = 24;

insert into descricao_tipo_atendimento(id, nome, sigla, id_tipo_atendimento)values(61, 'AE Mundo do Trabalho', 'MT', 1);
insert into modulo (id, nome)values(115, 'Mundo do Trabalho');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(61, 115);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(1, 61);

update descricao_tipo_atendimento set nome = 'AE Baixa Visão' where id = 2;
update descricao_tipo_atendimento set nome = 'AE Braille' where id = 3;
update descricao_tipo_atendimento set nome = 'AE Informática e outras Tecnologias Assistivas' where id = 4;
update descricao_tipo_atendimento set nome = 'AE Informática e outras Tecnologias Assistivas (Centro de Recursos)' where id = 5;
update descricao_tipo_atendimento set nome = 'AE Musicografia Braille' where id = 6;
update descricao_tipo_atendimento set nome = 'AE Orientação e Mobilidade' where id = 7; 
update descricao_tipo_atendimento set nome = 'AE Soroban' where id = 8; 
update descricao_tipo_atendimento set nome = 'AE Artes' where id = 9; 
update descricao_tipo_atendimento set nome = 'AE Atividades Corporais' where id = 15; 
update descricao_tipo_atendimento set nome = 'AE Transtorno Global do Desenvolvimento' where id = 59;
update descricao_tipo_atendimento set nome = 'AE Atividades de Vida Autônoma' where id = 60;

insert into modulo (id, nome)values(116, 'Visitas a Exposições e Teatros');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(24, 116);

delete from descricao_tipo_atendimento_modulo where id_descricao_tipo_atendimento = 36 and id_modulo = 67;

insert into tipo_atendimento(id, nome)values(14, 'Defesa e Garantia de Diretos');
insert into descricao_tipo_atendimento(id, nome, sigla, id_tipo_atendimento)values(62, 'Atividades Sociopolíticas', 'ASP', 14);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(14, 62);
insert into modulo (id, nome)values(117, 'Audiências Públicas');
insert into modulo (id, nome)values(118, 'Encontros');
insert into modulo (id, nome)values(119, 'Fóruns');
insert into modulo (id, nome)values(120, 'Manifestações');
insert into modulo (id, nome)values(121, 'Plenárias');
insert into modulo (id, nome)values(122, 'Reuniões');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(62, 117);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(62, 118);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(62, 119);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(62, 120);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(62, 121);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(62, 122);

insert into tipo_vinculo(id, descricao)values(19, 'Família ou Amigo de profissional da Laramara');
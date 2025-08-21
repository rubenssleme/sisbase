ALTER TABLE RECURSO ADD COLUMN DISPONIVEL_PARA_DEMANDA BOOLEAN DEFAULT 'false';

update recurso set descricao = 'Máquina Braille Laramara', disponivel_para_demanda = true where id = 3;
update recurso set descricao = 'Brinquedos Laramara', disponivel_para_demanda = true where id = 2;
update recurso set disponivel_para_demanda = true where id = 10;

insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(11, 'Armação de óculos', false, true);
insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(12, 'Bengala branca', false, true);
insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(13, 'Bengala verde', false, true);
insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(15, 'Lentes', false, true);
insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(16, 'Lupa de apoio', false, true);
insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(17, 'Lupa manual', false, true);
insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(18, 'Óculos', false, true);
insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(19, 'Plano inclinado para leitura', false, true);
insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(20, 'Recursos  CTA', false, true);
insert into recurso(id, descricao, cartela_de_selos, disponivel_para_demanda)values(21, 'Telelupa/Telescópio', false, true);

insert into descricao_recurso(id, descricao)values(1, '1,00');
insert into descricao_recurso(id, descricao)values(2, '1,05');
insert into descricao_recurso(id, descricao)values(3, '1,10');
insert into descricao_recurso(id, descricao)values(4, '1,15');
insert into descricao_recurso(id, descricao)values(5, '1,25');
insert into descricao_recurso(id, descricao)values(6, '1,30');
insert into descricao_recurso(id, descricao)values(7, '1,35');
insert into descricao_recurso(id, descricao)values(8, '02x');
insert into descricao_recurso(id, descricao)values(9, '03x');
insert into descricao_recurso(id, descricao)values(10, '04x');
insert into descricao_recurso(id, descricao)values(11, '05x');
insert into descricao_recurso(id, descricao)values(12, '06x');
insert into descricao_recurso(id, descricao)values(13, '07x');
insert into descricao_recurso(id, descricao)values(14, '08x');
insert into descricao_recurso(id, descricao)values(15, '09x');
insert into descricao_recurso(id, descricao)values(16, '10x');
insert into descricao_recurso(id, descricao)values(17, '11x');
insert into descricao_recurso(id, descricao)values(18, '12x');
insert into descricao_recurso(id, descricao)values(19, '14x');
insert into descricao_recurso(id, descricao)values(20, 'Cadeira adaptada em papelão');
insert into descricao_recurso(id, descricao)values(21, 'Cadeira escolar adaptada');
insert into descricao_recurso(id, descricao)values(22, 'Parapódium');
insert into descricao_recurso(id, descricao)values(23, 'Plano inclinado para leitura');
insert into descricao_recurso(id, descricao)values(24, 'Organizador de gavetas');
insert into descricao_recurso(id, descricao)values(25, 'Anteparo');
insert into descricao_recurso(id, descricao)values(26, 'Adaptador para tablet');
insert into descricao_recurso(id, descricao)values(27, 'Pré-bengala em PVC');
insert into descricao_recurso(id, descricao)values(28, 'Pré-bengala com acompanhante em PVC');


insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(12, 1);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(12, 2);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(12, 3);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(12, 4);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(12, 5);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(12, 6);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(12, 7);

insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(13, 1);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(13, 2);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(13, 3);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(13, 4);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(13, 5);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(13, 6);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(13, 7);

insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(16, 8);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(16, 9);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(16, 10);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(16, 12);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(16, 13);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(16, 14);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(16, 16);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(16, 18);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(16, 19);

insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(17, 9);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(17, 11);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(17, 13);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(17, 15);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(17, 17);

insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(20, 20);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(20, 21);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(20, 22);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(20, 23);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(20, 24);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(20, 25);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(20, 26);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(20, 27);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(20, 28);

insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(21, 8);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(21, 10);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(21, 12);
insert into recurso_descricao_recurso(id_recurso, id_descricao_recurso)values(21, 14);
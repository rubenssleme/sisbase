update tipo_atendimento set nome = 'Atendimento Especializado' where id = 1;
update tipo_atendimento set nome = 'Mundo do Trabalho' where id = 3;
update descricao_tipo_atendimento set nome = 'Atendimento Especializado Global' where id = 1;

insert into tipo_atendimento(id, nome)values(8, 'Capacitações');
insert into tipo_atendimento(id, nome)values(10, 'Visitas Monitoradas');
insert into tipo_atendimento(id, nome)values(11, 'Comissão Científica');
insert into tipo_atendimento(id, nome)values(12, 'Outras Ações');

insert into modulo (id, nome)values(37, 'Entrevista');
insert into modulo (id, nome)values(38, 'Acompanhamento');
insert into modulo (id, nome)values(39, 'Oficina de Cavaquinho');
insert into modulo (id, nome)values(40, 'Oficina de Dança');
insert into modulo (id, nome)values(41, 'Inclusão Escolar');
insert into modulo (id, nome)values(42, 'Inclusão no Trabalho');
update modulo set nome = 'Cinema com Audiodescrição' where id = 27;
update modulo set nome = 'Festas Temáticas' where id = 30;
update modulo set nome = 'Concertos' where id = 31;
update modulo set nome = 'Apresentações e Mostras de Artes' where id = 32;
insert into modulo (id, nome)values(43, 'Shows');
insert into modulo (id, nome)values(44, 'Passeios');
insert into modulo (id, nome)values(45, 'Acessibilidade');
insert into modulo (id, nome)values(46, 'Áreas de atuação Profissional');
insert into modulo (id, nome)values(47, 'Baixa Visão');
insert into modulo (id, nome)values(48, 'Benefícios Sociais');
insert into modulo (id, nome)values(49, 'Competências Profissionais');
insert into modulo (id, nome)values(50, 'Direitos da pessoa com deficiência');
insert into modulo (id, nome)values(51, 'Inclusão');
insert into modulo (id, nome)values(52, 'Sarau');
insert into modulo (id, nome)values(53, 'Informática');
insert into modulo (id, nome)values(54, 'Legislação trabalhista');
insert into modulo (id, nome)values(55, 'Oftalmologia');
insert into modulo (id, nome)values(56, 'Visitas à Exposições e Museus');
insert into modulo (id, nome)values(57, 'Audiodescrição');
insert into modulo (id, nome)values(58, 'Brincar para Todos');
insert into modulo (id, nome)values(59, 'Avaliação');
insert into modulo (id, nome)values(60, 'Adaptação de Acesso ao Currículo Funcional');
insert into modulo (id, nome)values(61, 'Informática e outras Tecnologias');
insert into modulo (id, nome)values(62, 'Orientação e Mobilidade');
insert into modulo (id, nome)values(63, 'Deficiência Múltipla');
insert into modulo (id, nome)values(64, 'Braille');
insert into modulo (id, nome)values(65, 'Intervenção Precoce');
insert into modulo (id, nome)values(66, 'Soroban');
insert into modulo (id, nome)values(67, 'Centro de Tecnologia Adaptada');
insert into modulo (id, nome)values(68, 'Brinquedos');
insert into modulo (id, nome)values(69, 'Máquina Braille');
insert into modulo (id, nome)values(70, 'Relações Institucionais');
insert into modulo (id, nome)values(71, 'CTO');
insert into modulo (id, nome)values(72, 'Proceja');
insert into modulo (id, nome)values(73, 'Outros');
update modulo set nome = 'Atividades Pedagogicas' where id = 35;
insert into modulo (id, nome)values(74, 'Atividades Artísticas');
insert into modulo (id, nome)values(75, 'ABNT');
insert into modulo (id, nome)values(76, 'ABEDEV');
insert into modulo (id, nome)values(77, 'CEAS');
insert into modulo (id, nome)values(78, 'CNAS');
insert into modulo (id, nome)values(79, 'COMAS');
insert into modulo (id, nome)values(80, 'Comitê Iberoamericano de Braille');
insert into modulo (id, nome)values(81, 'FEBAS');
insert into modulo (id, nome)values(82, 'ONCB');
insert into modulo (id, nome)values(83, 'SBVC');
insert into modulo (id, nome)values(84, 'Comitê Nac. de Assessor. e Apoio às Ações da Saúde do Plano Nac. para pessoas com Deficiência');
insert into modulo (id, nome)values(85, 'Análise de projetos de pesquisa e acompanhamento de pesquisadores');
insert into modulo (id, nome)values(86, 'Organização de eventos científicos');
insert into modulo (id, nome)values(87, 'Produção cientifica');
insert into modulo (id, nome)values(88, 'Publicações');
insert into modulo (id, nome)values(89, 'Centro de Estudos Natalie Barraga');

insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(39, 37);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(39, 38);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(16, 41);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(16, 42);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(9, 39);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(9, 40);

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('44', 2, 'Avaliação Soroban', 'AVSOR', '');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(44, 1);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(2, 44);
insert into nome_grupo(id, nome)values(45,'AVSOR');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(44, 45);

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('45', 2, 'Avaliação Braille', 'AVBR', '');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(45, 1);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(2, 45);
insert into nome_grupo(id, nome)values(46,'AVBR');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(45, 46);

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('46', 2, 'Avaliação Baixa Visão', 'AVBV', '');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(46, 1);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(2, 46);
insert into nome_grupo(id, nome)values(47,'AVBV');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(46, 47);

update modulo set nome = 'Curso de Quick Massage e Reflexologia' where id = 26;

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('47', 3, 'Orientação Profissional', 'OP', '');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(47, 1);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(3, 47);
insert into nome_grupo(id, nome)values(48,'OP');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(47, 48);

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('48', 3, 'Encaminhamento Profissional', 'EP', '');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(48, 1);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(3, 48);
insert into nome_grupo(id, nome)values(49,'EP');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(48, 49);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(24, 43);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(24, 44);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(24, 52);

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('49', 8, 'Palestras', 'PALEST', '');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 45);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 46);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 47);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 48);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 49);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 50);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 51);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 53);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 54);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 55);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(49, 57);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(8, 49);
insert into nome_grupo(id, nome)values(50,'PALEST');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(49, 50);
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(49, 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(49, 'CTO');

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('50', 8, 'Cursos', 'CURS', '');
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(8, 50);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 57);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 58);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 59);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 60);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 61);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 62);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 63);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 64);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 65);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(50, 66);
insert into nome_grupo(id, nome)values(51,'CURS');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(50, 51);

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('51', 8, 'Oficinas', 'OFIC', '');
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(8, 51);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 57);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 59);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 60);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 61);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 62);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 63);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 64);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 65);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 66);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 67);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(51, 68);
insert into nome_grupo(id, nome)values(52,'OFIC');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(51, 52);

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('52', 10, 'Visitas Monitoradas', 'VM', '');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(52, 69);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(52, 70);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(52, 71);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(52, 72);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(52, 73);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(10, 52);
insert into nome_grupo(id, nome)values(60,'VM');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(52, 60);

insert into tipo_atendimento(id, nome)values(13, 'Participação em Conselhos e Associações');
insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('53', 13, 'Participação em Conselhos e Associações', 'PCA', '');
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(13, 53);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 75);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 76);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 77);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 78);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 79);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 80);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 81);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 82);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 83);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(53, 84);
insert into nome_grupo(id, nome)values(61,'PCA');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(53, 61);

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('54', 11, 'Comissão Científica', 'CIENTIF', '');
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(11, 54);
insert into nome_grupo(id, nome)values(62,'CIENTIF');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(54, 62);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(54, 85);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(54, 86);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(54, 87);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(54, 88);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(54, 89);

update descricao_tipo_atendimento set inativo = true where id = 25;
update descricao_tipo_atendimento set inativo = true where id = 26;
update descricao_tipo_atendimento set inativo = true where id = 27;
update descricao_tipo_atendimento set inativo = true where id = 28;
update descricao_tipo_atendimento set inativo = true where id = 29;
update descricao_tipo_atendimento set inativo = true where id = 30;
update descricao_tipo_atendimento set inativo = true where id = 32;
update descricao_tipo_atendimento set inativo = true where id = 33;
update descricao_tipo_atendimento set inativo = true where id = 35;

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values ('55', 5, 'Visita Domiciliar', 'VD', '');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(55, 1);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(5, 55);
insert into nome_grupo(id, nome)values(63,'VD');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(55, 63);
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(55, 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor)values(55, 'CTO');

-- Relação descricao_tipo_atendimento_setor
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('1', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('4', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('5', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('6', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('8', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('9', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('15', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('16', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('18', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('20', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('21', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('22', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('23', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('44', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('44', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('45', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('45', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('46', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('46', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('47', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('47', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('48', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('48', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('50', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('50', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('51', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('51', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('52', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('52', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('53', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('53', 'PROCEJA');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('54', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('54', 'PROCEJA');

--Novas alterações
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo)values(36, 67);
update descricao_tipo_atendimento set inativo = true where id = 34;
update descricao_tipo_atendimento set inativo = true where id in (30,31,32);
insert into modulo (id, nome)values(90, 'Psicossocial para as famílias');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(1, 90);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(2, 90);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(3, 90);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(7, 90);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(8, 90);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(37, 37);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(37, 38);

update modulo set nome = 'Oficinas' where id = 18;

update descricao_tipo_atendimento set nome = 'Avaliação Psicossocial' where id = 20;

insert into modulo (id, nome)values(95, 'Psicologia');
insert into modulo (id, nome)values(96, 'Serviço Social');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(20, 95);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(20, 96);

update descricao_tipo_atendimento set inativo = true where id = 23;

update modulo set nome = 'Saraus' where id = 52;

update modulo set nome = 'Adaptação de Acesso ao Currículo' where id = 60;

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values (56, 8, 'Assessoria', 'ASS', '');
insert into modulo (id, nome)values(97, 'Assessoria');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(56, 97);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(8, 56);
insert into nome_grupo(id, nome)values(64,'ASS');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(56, 64);
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('56', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('56', 'PROCEJA');

update modulo set nome = 'Conselho Iberoamericano de Braille' where id = 80;

update tipo_vinculo set descricao = 'Assistente Social' where id = 8;
insert into tipo_vinculo(descricao) values ('Estudante');

insert into modulo (id, nome)values(98, 'Acompanhamento de Estagiários');
insert into modulo (id, nome)values(99, 'Pesquisas');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(54, 98);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(54, 99);

insert into descricao_tipo_atendimento(id, id_tipo_atendimento, nome, sigla, descricao) values (57, 12, 'Equipe Técnica', 'ET', '');
insert into modulo (id, nome)values(100, 'Capacitações');
insert into modulo (id, nome)values(101, 'Reuniões Mensais');
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(57, 100);
insert into descricao_tipo_atendimento_modulo(id_descricao_tipo_atendimento, id_modulo) values(57, 101);
insert into tipo_atendimento_descricao_tipo_atendimento(id_tipo_atendimento, id_descricao_tipo_atendimento)values(12, 57);
insert into nome_grupo(id, nome)values(65,'ET');
insert into descricao_tipo_atendimento_nome_grupo(id_descricao_tipo_atendimento, id_nome_grupo)values(57, 65);
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('57', 'CTO');
insert into descricao_tipo_atendimento_setor(id_descricao_tipo_atendimento, id_setor) values ('57', 'PROCEJA');
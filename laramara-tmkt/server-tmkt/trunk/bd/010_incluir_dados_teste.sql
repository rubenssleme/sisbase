insert into permissao(descricao)values('Nivel Visualizar');
insert into permissao(descricao)values('Nivel Editar');
insert into permissao(descricao)values('Usuário Visualizar');
insert into permissao(descricao)values('Usuário Editar');
insert into permissao(descricao)values('Campanha Visualizar');
insert into permissao(descricao)values('Campanha Editar');
insert into permissao(descricao)values('Listagem Visualizar');
insert into permissao(descricao)values('Doação Efetuar');

insert into nivel(descricao)values('Operador');
insert into nivel(descricao)values('Gerente');

insert into nivel_permissao(id_nivel, id_permissao)values(1, 7);
insert into nivel_permissao(id_nivel, id_permissao)values(1, 8);
insert into nivel_permissao(id_nivel, id_permissao)values(2, 1);
insert into nivel_permissao(id_nivel, id_permissao)values(2, 2);
insert into nivel_permissao(id_nivel, id_permissao)values(2, 3);
insert into nivel_permissao(id_nivel, id_permissao)values(2, 4);
insert into nivel_permissao(id_nivel, id_permissao)values(2, 5);
insert into nivel_permissao(id_nivel, id_permissao)values(2, 6);

insert into conta_acesso(id, login, senha, nome, ativo, id_nivel, turno)values(1000, 'paulo', '81dc9bdb52d04dc20036dbd8313ed055', 'Paulo Augusto', true, 1, 'MANHA');
insert into conta_acesso(id, login, senha, nome, ativo, id_nivel, turno)values(2000, 'jose', '81dc9bdb52d04dc20036dbd8313ed055', 'Jose Augusto', true, 1, 'MANHA');
insert into conta_acesso(id, login, senha, nome, ativo, id_nivel, turno)values(3000, 'carlos', '81dc9bdb52d04dc20036dbd8313ed055', 'Carlos Eduardo', true, 2, 'TARDE');
insert into conta_acesso(id, login, senha, nome, ativo, id_nivel, turno)values(4000, 'a', '0cc175b9c0f1b6a831c399e269772661', 'José Santos', true, 2, 'MANHA');
 
insert into estado(id, descricao) values(19,'Rio de Janeiro');
insert into estado(id, descricao) values(26,'São Paulo');

insert into municipio(id, descricao, id_estado)values(8966,'São Paulo',26);
insert into municipio(id, descricao, id_estado)values(1809,'Campinas',26);
insert into municipio(id, descricao, id_estado)values(7764,'Rio de Janeiro',19);
insert into municipio(id, descricao, id_estado)values(8604,'São Gonçalo',19);

insert into logradouro(cep, descricao, bairro, id_municipio)values('01151000','Rua Brigadeiro Galvão','Barra Funda',8966);
insert into logradouro(cep, descricao, bairro, id_municipio)values('01238010','Rua Dona Veridiana','Higienópolis',8966);

insert into campanha(id, nome, descricao, data_inicio, data_termino, meta_financeira, meta_quantidade_ligacoes)values(1000, 'Nome da campanha A', 'Texto de descrição A', '2018-01-31', '2018-12-31', 10000.50, 10000);
insert into campanha(id, nome, descricao, data_inicio, data_termino, meta_financeira, meta_quantidade_ligacoes)values(2000, 'Nome da campanha B', 'Texto de descrição B', '2018-01-31', '2018-12-31', 50000.10, 20000);

insert into criterio(id, nome, id_municipio, bairro, quantidade_distribuir)values(1000, 'Critério Bairro Santa Secilia', 8966, 'Santa Cecília', 10000);
insert into criterio(id, nome, id_municipio, bairro, quantidade_distribuir)values(2000, 'Critério Bairro Barra Funda', 8966, 'Barra Funda', 20000);

insert into campanha_criterio(id_campanha, id_criterio)values(2000, 1000);
insert into campanha_criterio(id_campanha, id_criterio)values(2000, 2000);

insert into alocacao_operador(id, id_conta_acesso, meta_financeira, meta_quantidade_ligacoes)values(1000, 1000, 1000, 1000);
insert into alocacao_operador(id, id_conta_acesso, meta_financeira, meta_quantidade_ligacoes)values(2000, 2000, 2000, 2000);

insert into campanha_alocacao_operador(id_campanha, id_alocacao_operador)values(2000, 1000); 
insert into campanha_alocacao_operador(id_campanha, id_alocacao_operador)values(2000, 2000); 

insert into contato(id, cpf, nome, cep, numero, complemento, email)values(1000, '71894810287', 'Paulo Augusto', '01151000', '573', 'AP 63', 'pbandeira@yahoo.com.br');
insert into contato(id, cpf, nome, cep, numero, complemento, email)values(2000, '11223344556', 'Joao Augusto', '01151000', '834', 'AP 13', 'joao@yahoo.com.br');

insert into telefone(id, ddd, telefone)values(1000, '11', '111111111');
insert into telefone(id, ddd, telefone)values(2000, '11', '986866204');
insert into telefone(id, ddd, telefone)values(3000, '11', '957878870');
insert into telefone(id, ddd, telefone)values(4000, '11', '981847597');

insert into contato_telefone(id_contato, id_telefone)values(1000, 1000);
insert into contato_telefone(id_contato, id_telefone)values(1000, 2000);
insert into contato_telefone(id_contato, id_telefone)values(2000, 3000);
insert into contato_telefone(id_contato, id_telefone)values(2000, 4000);

insert into historico_status_contato(id, id_conta_acesso, data_inicial_vigencia, status)values(1000, 1000, '2018-01-01 15:00:00', 'NOVO'); 
insert into historico_status_contato(id, id_conta_acesso, data_inicial_vigencia, status)values(2000, 1000, '2018-01-01 15:00:00', 'NOVO'); 

insert into contato_historico_status_contato(id_contato, id_historico_status_contato)values(1000, 1000);
insert into contato_historico_status_contato(id_contato, id_historico_status_contato)values(2000, 2000);

insert into evento_telefonia(id, tipo_evento, ramal, ddd, telefone, uniqueid, duracao_ligacao, data_ocorrencia)values('622044', 'NO_ANSWER', 'RAMAL_6489', '11', '957878870', '1541697708.2484', 8, '2018-11-08 15:21:53.000');


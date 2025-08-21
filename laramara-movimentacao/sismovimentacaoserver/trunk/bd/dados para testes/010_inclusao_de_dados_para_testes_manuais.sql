insert into perfil(descricao)values('Administrador');
insert into perfil(descricao)values('Operador');
insert into perfil_permissoes(id_perfil, permissao)values(1, 'CONTA_ACESSO_TELA_EDICAO_VISUALIZAR');
insert into perfil_permissoes(id_perfil, permissao)values(1, 'CONTA_ACESSO_EDICAO');
insert into perfil_permissoes(id_perfil, permissao)values(1, 'MOVIMENTACAO_EDICAO');
insert into perfil_permissoes(id_perfil, permissao)values(1, 'MOVIMENTACAO_TELA_EDICAO_VISUALIZAR');
insert into perfil_permissoes(id_perfil, permissao)values(1, 'PERFIL_TELA_EDICAO_VISUALIZAR');
insert into perfil_permissoes(id_perfil, permissao)values(1, 'PERFIL_EDICAO');
insert into perfil_permissoes(id_perfil, permissao)values(1, 'DESBLOQUEIO_TELA');
insert into perfil_permissoes(id_perfil, permissao)values(1, 'FINALIZAR_SERVICO');

insert into conta_acesso(id_perfil, login, senha, bloqueado, extensao_relatorios, filtro_grupo)values(1, 'a', '0cc175b9c0f1b6a831c399e269772661', 'false', 'pdf', 'false');
insert into conta_acesso(id_perfil, login, senha, bloqueado, extensao_relatorios, filtro_grupo)values(1, 'b', '92eb5ffee6ae2fec3ad71c777531578f', 'false', 'pdf', 'false');
insert into conta_acesso(id_perfil, login, senha, bloqueado, extensao_relatorios, filtro_grupo)values(2, 'c', '4a8a08f09d37b73795649038408b5f33', 'false', 'xls', 'false');


insert into movimentacao(id) values(1000);
insert into historico_status(id, data_inicial_vigencia, status, id_conta_acesso)values(1000, '2000-01-01', 'ARTE_APROVADA', 1);
insert into movimentacao_historico_status(id_historico_status, id_movimentacao)values(1000, 1000);

insert into movimentacao(id) values(2000);
insert into historico_status(id, data_inicial_vigencia,status, id_conta_acesso)values(2000, '2000-01-01', 'ARTE_EM_APROVACAO', 1);
insert into movimentacao_historico_status(id_historico_status, id_movimentacao)values(2000, 2000);

insert into movimentacao(id) values(3000);
insert into historico_status(id, data_inicial_vigencia, status, id_conta_acesso)values(3000, '2000-01-01', 'ARTE_APROVADA', 2);
insert into movimentacao_historico_status(id_historico_status, id_movimentacao)values(3000, 3000);
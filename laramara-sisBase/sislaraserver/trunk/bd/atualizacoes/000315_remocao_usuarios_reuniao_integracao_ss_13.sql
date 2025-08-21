update modulo_usuario set status='REMOVIDO', obs='Já participou de atividade de integração anteriormente.' where id in(
	select modusu.id
		from usuario usu
		inner join modulo_usuario modusu
			on modusu.id_usuario = usu.id
		inner join modulo_periodo_modulo_usuario modpermodusu
			on modpermodusu.id_modulo_usuario = modusu.id
		inner join modulo_periodo modper
			on modper.id = modpermodusu.id_modulo_periodo
		inner join grupo_modulo_periodo grumodper
			on grumodper.id_modulo_periodo = modper.id
		inner join grupo gru
			on gru.id = grumodper.id_grupo
		inner join descricao_tipo_atendimento destipate
			on destipate.id = gru.id_descricao_tipo_atendimento
		inner join tipo_atendimento tip
			on tip.id = destipate.id_tipo_atendimento
		inner join modulo mod
			on mod.id = modper.id_modulo
		inner join nome_grupo nomgru
			on nomgru.id = gru.id_nome_grupo
	where modusu.status = 'INTEGRADO' and gru.ativo = true and gru.excluido is false and gru.data_inicio between '01/01/2016' and '31/12/2016' and nomgru.nome = 'SS' and gru.turma = '13' 
and usu.id in (
6841 
,10002
,10805
,10498 
,10862
,10123
,5444 
,6621 
,10377 
,10164 
,7572
,861 
,10482
,2097 
,9859 
,9192 
,8052 
,2449 
 )
);

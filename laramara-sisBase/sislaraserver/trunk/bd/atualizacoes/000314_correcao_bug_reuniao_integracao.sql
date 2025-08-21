select gru.turma, tip.nome as tipo, destipate.nome as descricao, mod.nome as modulo, gru.data_inicio, ','||modusu.id as modusu, usu.id as usuario
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
where modusu.status = 'INTEGRADO' and gru.ativo = true and gru.excluido is false and gru.data_inicio between '01/01/2016' and '31/12/2016' and mod.id in (127) and (gru.turma like '15' or gru.turma like '16')
and usu.id 
	in (select usu.id as usuario
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
	where modusu.status = 'INTEGRADO' and gru.ativo = true and gru.excluido is false and gru.data_inicio between '01/01/2016' and '31/12/2016' and mod.id in (127)
	group by usu.id
	having count(*) > 1
	order by usu.id)
order by usu.id, gru.turma;

delete from modulo_periodo_modulo_usuario where id_modulo_usuario in (12236
,12241
,12237
,12231
,12238
,12233
,12239
,12229
,12228
,12242
,12154
,12227
,12234
,12235
,12155
,12240
,12226
,12232
,12230
,12156);

delete from modulo_periodo_modulo_usuario where id_modulo_usuario in (12236
,12241
,12237
,12231
,12238
,12233
,12239
,12229
,12228
,12242
,12154
,12227
,12234
,12235
,12155
,12240
,12226
,12232
,12230
,12156);

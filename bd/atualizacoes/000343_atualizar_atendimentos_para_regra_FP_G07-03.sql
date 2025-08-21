update informacao_atendimento set frequencia ='FP' where id in (
select infateusu.id 
	--,nom.nome, gru.turma, ategru.data, infateusu.frequencia, ateusu.id_usuario, * 
	from atendimento_grupo_atendimento_profissional ategruatepro
	inner join atendimento_profissional atepro
		on ategruatepro.id_atendimento_profissional = atepro.id
	inner join informacao_atendimento infatepro
		on infatepro.id = atepro.id_informacao_atendimento
	inner join atendimento_grupo ategru
		on ategru.id = ategruatepro.id_atendimento_grupo
	inner join modulo_periodo_atendimento_grupo ategrumodper
		on ategrumodper.id_atendimento_grupo = ategru.id
	inner join grupo_modulo_periodo grumodper
		on grumodper.id_modulo_periodo = ategrumodper.id_modulo_periodo
	inner join grupo gru
		on gru.id = grumodper.id_grupo
	inner join nome_grupo nom
		on nom.id = gru.id_nome_grupo
	inner join atendimento_grupo_atendimento_usuario ategruateusu
		on ategruateusu.id_atendimento_grupo = ategruatepro.id_atendimento_grupo
	inner join atendimento_usuario ateusu
		on ateusu.id = ategruateusu.id_atendimento_usuario
	inner join informacao_atendimento infateusu
		on infateusu.id = ateusu.id_informacao_atendimento
where infatepro.frequencia = 'FP' and gru.ativo = true and ategru.status ='NORMAL' and nom.nome like 'G07' and gru.turma like '03' and ategru.data in ('2016-04-25', '2016-07-18', '2016-07-25')
order by nom.nome, gru.turma, ategru.data)
update informacao_atendimento set frequencia = 'RC' where id in(
	select infate.id
		from grupo gru
			inner join grupo_modulo_periodo grumodper
				on gru.id = grumodper.id_grupo
			inner JOIN modulo_periodo modper
				ON grumodper.id_modulo_periodo = modper.id
			inner join descricao_tipo_atendimento destipate
				on (destipate.id = gru.id_descricao_tipo_atendimento)
			inner join tipo_atendimento tip
				on (tip.id = destipate.id_tipo_atendimento)
			inner JOIN modulo mod
				ON modper.id_modulo = mod.id
			inner JOIN modulo_periodo_atendimento_grupo modperaten
				ON modper.id = modperaten.id_modulo_periodo
			inner JOIN atendimento_grupo ate
				ON ate.id = modperaten.id_atendimento_grupo
			inner join atendimento_grupo_atendimento_usuario ategruateusu
				on ate.id = ategruateusu.id_atendimento_grupo
			inner join atendimento_usuario ateusu
				on ateusu.id = ategruateusu.id_atendimento_usuario
			inner join informacao_atendimento infate
				on infate.id = ateusu.id_informacao_atendimento
	where ate.data between '04/07/2016' and '08/07/2016' and gru.ativo = true and gru.excluido = false and gru.id_setor = 'CTO'
);

update informacao_atendimento set frequencia = 'RC' where id in(
	select infatepro.id
		from grupo gru
			inner join grupo_modulo_periodo grumodper
				on gru.id = grumodper.id_grupo
			inner JOIN modulo_periodo modper
				ON grumodper.id_modulo_periodo = modper.id
			inner join descricao_tipo_atendimento destipate
				on (destipate.id = gru.id_descricao_tipo_atendimento)
			inner join tipo_atendimento tip
				on (tip.id = destipate.id_tipo_atendimento)
			inner JOIN modulo mod
				ON modper.id_modulo = mod.id
			inner JOIN modulo_periodo_atendimento_grupo modperaten
				ON modper.id = modperaten.id_modulo_periodo
			inner JOIN atendimento_grupo ate
				ON ate.id = modperaten.id_atendimento_grupo
			inner join atendimento_grupo_atendimento_profissional ategruatepro
				on ategruatepro.id_atendimento_grupo = ate.id
			inner join atendimento_profissional atepro
				on atepro.id = ategruatepro.id_atendimento_profissional
			inner join informacao_atendimento infatepro
				on infatepro.id = atepro.id_informacao_atendimento
	where ate.data between '04/07/2016' and '08/07/2016' and gru.ativo = true and gru.excluido = false and gru.id_setor = 'CTO'
);	
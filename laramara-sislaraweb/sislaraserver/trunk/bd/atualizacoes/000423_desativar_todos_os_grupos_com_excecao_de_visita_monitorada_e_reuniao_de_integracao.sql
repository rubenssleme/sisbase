update grupo set ativo = false where id in (
	select gru.id from grupo gru
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
	WHERE mod.id not in (70, 127) and gru.ativo is true
order by gru.id);
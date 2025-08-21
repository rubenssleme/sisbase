update grupo set ativo = false where id in (
	select gru.id from grupo gru
		inner join descricao_tipo_atendimento destipate
			on gru.id_descricao_tipo_atendimento = destipate.id
		inner join grupo_modulo_periodo grumodper
			on grumodper.id_grupo = gru.id
		inner join modulo_periodo modper
			on modper.id = grumodper.id_modulo_periodo
		inner join modulo mod
			on mod.id = modper.id_modulo
	where gru.ativo is true and destipate.id = 37 and mod.id = 127
);
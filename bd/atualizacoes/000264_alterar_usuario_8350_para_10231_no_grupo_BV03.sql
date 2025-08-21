update atendimento_usuario set id_usuario = 10231 where id in (
	select ateusu.id from grupo gru
			inner join nome_grupo nom
				on nom.id = gru.id_nome_grupo
			inner join grupo_modulo_periodo grumodper
				on gru.id = grumodper.id_grupo
			inner join modulo_periodo modper 
				on grumodper.id_modulo_periodo = modper.id
			inner join modulo mod 
				on modper.id_modulo = mod.id
			inner join modulo_periodo_atendimento_grupo modperaten 
				on modper.id = modperaten.id_modulo_periodo
			inner join atendimento_grupo ate 
				on ate.id = modperaten.id_atendimento_grupo
			inner join atendimento_grupo_atendimento_usuario ategruateusu
				on ate.id = ategruateusu.id_atendimento_grupo
			inner join atendimento_usuario ateusu
				on ateusu.id = ategruateusu.id_atendimento_usuario
		where gru.ativo = true and excluido = false and nom.nome = 'BV' and gru.turma = '03' and mod.nome in ('Atendimento Específico Especializado', 'Reunião com as Instituições de Ensino') and ateusu.id_usuario = 8350
)
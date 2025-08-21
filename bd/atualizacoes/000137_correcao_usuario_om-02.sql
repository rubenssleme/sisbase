update atendimento_usuario set id_usuario = 1734 where id in(
	select ateusu.id from atendimento_grupo ategru
		inner join atendimento_grupo_atendimento_usuario ategruateusu
			on ategru.id = ategruateusu.id_atendimento_grupo
		inner join atendimento_usuario ateusu
			on ateusu.id = ategruateusu.id_atendimento_usuario
		inner join informacao_atendimento infate
			on infate.id = ateusu.id_informacao_atendimento
	where ategru.id in (3196, 3199, 3335, 3452, 3801, 3937, 4103, 4197, 4314, 4434, 4681, 4683, 4684, 4685, 4972)
)
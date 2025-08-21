update modulo_usuario set status = 'DESISTENTE' where id in (
	select modusu.id from modulo_usuario modusu 
		inner join modulo_periodo_modulo_usuario modpermodusu 
			on modpermodusu.id_modulo_usuario = modusu.id
		inner join modulo_periodo modper
			on modper.id = modpermodusu.id_modulo_periodo
		inner join grupo_modulo_periodo grumodper
			on grumodper.id_modulo_periodo = modper.id
		inner join grupo gru
			on gru.id = grumodper.id_grupo
	where modusu.id_usuario in (2336, 8413, 9767, 9835, 9860, 9924, 10131, 10165, 10184, 10197, 10315, 10371, 10377, 10385, 10412) and gru.ativo = true and modusu.status = 'DESLIGADO'
);

update usuario set renda = renda + 1 where id in (
select distinct usu.id from usuario usu 
	inner join usuario_periodo_beneficio usuperben
		on usu.id = usuperben.id_usuario
	inner join periodo_beneficio perben
		on perben.id = usuperben.id_periodo_beneficio
where usu.renda = 0 and id_beneficio = 6)
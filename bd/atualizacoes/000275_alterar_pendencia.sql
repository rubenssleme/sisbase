update pendencia set resolvida = 'true' where id in(
	select pen.id from pendencia pen
		inner join modulo_periodo modper
			on pen.id_modulo_periodo = modper.id
		inner join modulo mod
			on mod.id = modper.id_modulo
	where mod.id = 106
);
update pendencia set resolvida = true where id in (
	select pen.id from pendencia pen
		inner join pendencia_profissional penpro
			on penpro.id_pendencia = pen.id
	where penpro.id_profissional = 9 and pen.data in ('25/08/2016', '01/09/2016') and pen.resolvida = false
);
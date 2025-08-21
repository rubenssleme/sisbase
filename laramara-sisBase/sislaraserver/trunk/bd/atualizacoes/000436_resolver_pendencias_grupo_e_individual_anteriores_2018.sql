update pendencia set resolvida = true where id in (
	select pen.id from pendencia pen
		inner join pendencia_profissional penpro
			on penpro.id_pendencia = pen.id
		inner join profissional pro
			on pro.id = penpro.id_profissional
	where pen.resolvida is false and pen.data between '01/01/2000' and '31/12/2017' and pen.tipo = 'ATENDIMENTO_GRUPO'
	union all
	select pen.id from pendencia pen
		inner join pendencia_profissional penpro
			on penpro.id_pendencia = pen.id
		inner join profissional pro
			on pro.id = penpro.id_profissional
		inner join agendamento age
			on age.id = pen.id_agendamento
	where pen.resolvida is false and age.data between '01/01/2000' and '31/12/2017' and pen.tipo = 'ATENDIMENTO_INDIVIDUAL_USUARIO'
	union all
	select pen.id from pendencia pen
		inner join pendencia_profissional penpro
			on penpro.id_pendencia = pen.id
		inner join profissional pro
			on pro.id = penpro.id_profissional
		inner join agendamento age
			on age.id = pen.id_agendamento
	where pen.resolvida is false and age.data between '01/01/2000' and '31/12/2017' and pen.tipo = 'ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO'
);


update pendencia set resolvida = true where id in (
select pen.id from pendencia pen
	inner join grupo gru
		on gru.id = pen.id_grupo
where gru.id_descricao_tipo_atendimento in (16, 24, 50)  and pen.tipo = 'ATENDIMENTO_GRUPO' and resolvida is false
);
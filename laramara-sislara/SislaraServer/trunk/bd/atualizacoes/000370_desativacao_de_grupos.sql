update grupo set ativo = false where id in (
select gru.id from grupo gru
	inner join nome_grupo nom
		on nom.id = gru.id_nome_grupo
	inner join grupo_modulo_periodo grumodper
		on grumodper.id_grupo = gru.id
	inner join modulo_periodo modper
		on modper.id = grumodper.id_modulo_periodo
	inner join modulo mod
		on mod.id = modper.id_modulo
where gru.ativo = true and gru.data_inicio between '01/01/2016' and '31/12/2016' and mod.id = 70
order by nom.nome, gru.turma, mod.nome);

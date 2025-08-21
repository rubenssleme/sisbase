update grupo set ativo = false where id in (
select gru.id from grupo gru
	inner join nome_grupo nom
		on nom.id = gru.id_nome_grupo
where gru.ativo = true and nom.nome in ('G00', 'G02', 'G04', 'G07', 'G10', 'GJ', 'SOR', 'TGD')
order by nom);
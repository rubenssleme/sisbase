update pendencia set resolvida = true where id in (select pen.id from pendencia pen
	inner join grupo gru
		on pen.id_grupo = gru.id
	inner join nome_grupo nom
		on nom.id = gru.id_nome_grupo
where pen.resolvida = false and nom.nome = 'PSS');
update profissional set nome = 'Marines Targina' where id = 170;

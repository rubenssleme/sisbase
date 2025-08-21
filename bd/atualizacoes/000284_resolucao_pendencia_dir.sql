update pendencia set resolvida = true where id in (select pen.id from pendencia pen
	inner join grupo gru
		on pen.id_grupo = gru.id
	inner join nome_grupo nom
		on nom.id = gru.id_nome_grupo
where pen.resolvida = false and nom.nome = 'DIR');

update historico_status_espera set status = 'CANCELADO' where id in (select hisstaesp.id from espera esp
	inner join espera_historico_status_espera esphisstaesp
		on esphisstaesp.id_espera = esp.id
	inner join historico_status_espera hisstaesp
		on hisstaesp.id = esphisstaesp.id_historico_status_espera
where esp.id = 8589);
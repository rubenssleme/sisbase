update grupo set ativo = false where id in (
	select distinct gru.id from grupo gru
				inner join grupo_modulo_periodo grumodper
					on gru.id = grumodper.id_grupo
				inner JOIN modulo_periodo modper
					ON grumodper.id_modulo_periodo = modper.id
				inner join descricao_tipo_atendimento destipate
					on (destipate.id = gru.id_descricao_tipo_atendimento)
				inner join tipo_atendimento tip
					on (tip.id = destipate.id_tipo_atendimento)
				inner JOIN modulo mod
					ON modper.id_modulo = mod.id
				inner join nome_grupo nom
					on nom.id = gru.id_nome_grupo
	WHERE gru.ativo is true and gru.data_inicio between '01/01/2017' and '31/12/2017' and nom.nome||'-'||gru.turma in ('G04-01', 'G07-01', 'G07-04')
);

update grupo set ativo = true where id in (
	select distinct gru.id from grupo gru
				inner join grupo_modulo_periodo grumodper
					on gru.id = grumodper.id_grupo
				inner JOIN modulo_periodo modper
					ON grumodper.id_modulo_periodo = modper.id
				inner join descricao_tipo_atendimento destipate
					on (destipate.id = gru.id_descricao_tipo_atendimento)
				inner join tipo_atendimento tip
					on (tip.id = destipate.id_tipo_atendimento)
				inner JOIN modulo mod
					ON modper.id_modulo = mod.id
				inner join nome_grupo nom
					on nom.id = gru.id_nome_grupo
	WHERE gru.ativo is false and gru.data_inicio between '01/01/2018' and '31/12/2018' and nom.nome||'-'||gru.turma in ('G04-01', 'G07-01', 'G07-04')
);

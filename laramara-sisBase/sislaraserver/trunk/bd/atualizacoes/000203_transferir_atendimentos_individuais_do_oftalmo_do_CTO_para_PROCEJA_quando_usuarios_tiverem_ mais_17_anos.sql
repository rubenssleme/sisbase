update atendimento_individual set id_setor = 'PROCEJA' where id in (
	select distinct ateind.id
			from atendimento_individual ateind
				left join usuario usu
					on usu.id = ateind.id_usuario
				inner join informacao_essencial infess
					on (infess.id = usu.id_informacao_essencial)
				inner join informacao_atendimento infate
					on infate.id = ateind.id_informacao_atendimento
				inner join descricao_tipo_atendimento destipate
					on (destipate.id = ateind.id_descricao_tipo_atendimento)
				inner join tipo_atendimento tip
					on (tip.id = destipate.id_tipo_atendimento)
				inner join modulo mod
					on (mod.id = ateind.id_modulo)
				inner join atendimento_individual_atendimento_profissional ateindatepro
					on ateindatepro.id_atendimento_individual = ateind.id
				inner join atendimento_profissional atepro
					on atepro.id = ateindatepro.id_atendimento_profissional
				inner join informacao_atendimento infatepro
					on infatepro.id = atepro.id_informacao_atendimento
			where ateind.status = 'NORMAL' and infate.frequencia = 'AT' and infatepro.frequencia = 'AT' and ateind.data between '01/01/2014' and '31/12/2014' and ateind.id_descricao_tipo_atendimento in (40, 41) and ateind.id_setor = 'CTO' 
			and extract(year from age(infess.data_nascimento)) >= 17)

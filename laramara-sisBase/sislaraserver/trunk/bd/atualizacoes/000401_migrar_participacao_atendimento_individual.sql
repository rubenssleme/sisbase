select 'insert into PARTICIPACAO_DETALHADA(participacao, quantidade) values (''', ateind.participacao,''', 1);', 'insert into INFORMACAO_ATENDIMENTO_PARTICIPACAO_DETALHADA(ID_INFORMACAO_ATENDIMENTO, ID_PARTICIPACAO_DETALHADA)values(', infate.id, ',', 'currval(''participacao_detalhada_id_seq'')', ');'  from atendimento_individual ateind
	inner join informacao_atendimento infate
		on infate.id = ateind.id_informacao_atendimento 
where ateind.participacao in ('APENAS_FAMILIA', 'COM_FAMILIA') order by infate.id;
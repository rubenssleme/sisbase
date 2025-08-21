update historico_status_espera set status = 'CANCELADO' where id in (
select hisestesp.id from espera esp
	inner join espera_historico_status_espera esphisstaesp
		on esphisstaesp.id_espera = esp.id 
	inner join historico_status_espera hisestesp
		on hisestesp.id = esphisstaesp.id_historico_status_espera 
where obs like 'Incluído automaticamente em decorrência de falta no atendimento individual de avaliação e triagem agendado por causa de excesso de faltas%' and hisestesp.data_final_vigencia is null and hisestesp.status = 'AGUARDANDO');
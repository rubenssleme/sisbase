update historico_status_espera set status = 'CANCELADO' where id in (
select hisstaesp.id from espera esp
	inner join espera_historico_status_espera eshisstaesp
		on eshisstaesp.id_espera = esp.id
	inner join historico_status_espera hisstaesp
		on hisstaesp.id = eshisstaesp.id_historico_status_espera
where esp.id = 6449 and esp.data is null and hisstaesp.data_final_vigencia is null);
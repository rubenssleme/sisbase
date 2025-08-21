update espera set id_modulo = 1 where id in (
	select esp.id from espera esp
		inner join espera_historico_status_espera esphisstaesp
			on esp.id = esphisstaesp.id_espera 
		inner join historico_status_espera hisestesp
			on hisestesp.id = esphisstaesp.id_historico_status_espera
	where esp.data > '01/01/2017' and esp.id_descricao_tipo_atendimento = 40 and esp.id_modulo = 130 and hisestesp.data_final_vigencia is null and hisestesp.status = 'AGUARDANDO'
	order by esp.data, esp.id_usuario
)
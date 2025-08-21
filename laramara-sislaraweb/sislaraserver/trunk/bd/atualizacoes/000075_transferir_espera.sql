update espera set id_descricao_tipo_atendimento = 17, id_modulo = 1 where espera.id in(select esp.id from espera esp
	inner join espera_historico_status_espera esphisstaesp
		on esp.id = esphisstaesp.id_espera
	inner join historico_status_espera hisstaesp
		on hisstaesp.id = esphisstaesp.id_historico_status_espera
where hisstaesp.data_final_vigencia is null and hisstaesp.id_conta_acesso in(55, 7) and esp.id_descricao_tipo_atendimento = 2 and esp.id_modulo = 1
order by esp.id_usuario);

update espera set id_descricao_tipo_atendimento = 19, id_modulo = 1 where espera.id in(select esp.id from espera esp
	inner join espera_historico_status_espera esphisstaesp
		on esp.id = esphisstaesp.id_espera
	inner join historico_status_espera hisstaesp
		on hisstaesp.id = esphisstaesp.id_historico_status_espera
where hisstaesp.data_final_vigencia is null and hisstaesp.id_conta_acesso in(55) and esp.id_descricao_tipo_atendimento = 7 and esp.id_modulo = 1
order by esp.id_usuario);
update acao_conduta set data_processamento = null, resultado_processamento = '' where id in (2, 3);
update historico_status_espera set status = 'CANCELADO' where id in (select hisstaesp.id from espera esp
		inner join descricao_tipo_atendimento destipate
			on destipate.id = esp.id_descricao_tipo_atendimento
		inner join modulo mod
			on mod.id = esp.id_modulo
		inner join espera_historico_status_espera esphisstaesp
			on esphisstaesp.id_espera = esp.id
		inner join historico_status_espera hisstaesp
			on hisstaesp.id = esphisstaesp.id_historico_status_espera
	where esp.id_descricao_tipo_atendimento = 17 and esp.id_modulo = 1 and hisstaesp.status = 'AGUARDANDO' and hisstaesp.data_final_vigencia is null and esp.id_usuario in (11300, 11207, 11207, 11294, 10909, 11127)
);	
update historico_status_espera set status = 'CANCELADO' where id in (
	select hisstaesp.id from espera esp
		inner join descricao_tipo_atendimento destipate
			on destipate.id = esp.id_descricao_tipo_atendimento
		inner join modulo mod
			on mod.id = esp.id_modulo
		inner join espera_historico_status_espera esphisstaesp
			on esphisstaesp.id_espera = esp.id
		inner join historico_status_espera hisstaesp
			on hisstaesp.id = esphisstaesp.id_historico_status_espera
	where esp.id_descricao_tipo_atendimento = 40 and esp.id_modulo = 1 and hisstaesp.status = 'AGUARDANDO' and hisstaesp.data_final_vigencia is null
);
insert into bloqueio(id_descricao_tipo_atendimento, id_modulo, area) values (40, 1, 'LISTA_ESPERA');
update atendimento_individual set status = 'CANCELADO' where id in (18936);

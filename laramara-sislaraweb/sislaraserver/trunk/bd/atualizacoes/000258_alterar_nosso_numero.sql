ALTER SEQUENCE nosso_numero_seq RESTART WITH 97516;

update historico_status_contribuinte set status = 'DESATIVADO' where id in ( 
	select hisstacon.id from contribuinte con 
		inner join contribuinte_historico_status_contribuinte conhisstatcon
			on conhisstatcon.id_contribuinte = con.id
		inner join historico_status_contribuinte hisstacon
			on conhisstatcon.id_historico_status_contribuinte = hisstacon.id
	where hisstacon.data_final_vigencia is null and con.id in (43, 611, 720, 757, 763, 792, 817, 897)
);
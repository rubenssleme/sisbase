alter table usuario add column FALECIDO BOOLEAN default 'FALSE';

update usuario set falecido = true where id in(
	select id_usuario from usuario_historico_status usuhisusu
		inner join historico_status hissta
			on usuhisusu.id_historico_status = hissta.id
	where hissta.data_final_vigencia is null and hissta.status = 'FALECIDO'
)
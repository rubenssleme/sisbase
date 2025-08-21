update historico_status_usuario set status = 'DESLIGADO' where id in (
select distinct(hisstausu.id) from usuario usu 
	inner join usuario_historico_status_usuario usuhisstausu 
		on usuhisstausu.id_usuario  = usu.id
	inner join historico_status_usuario hisstausu
		on hisstausu.id = usuhisstausu.id_historico_status_usuario
	inner join modulo_usuario modusu
		on modusu.id_usuario = usu.id
where (hisstausu.status = 'CASO_NOVO' and hisstausu.data_final_vigencia is null) and modusu.status in ('DESLIGADO') and modusu.data_inicio > '01-01-2015');

update historico_status_usuario set status = 'INTEGRADO' where id in (
select distinct(hisstausu.id) from usuario usu
	inner join usuario_historico_status_usuario usuhisstausu 
		on usuhisstausu.id_usuario  = usu.id
	inner join historico_status_usuario hisstausu
		on hisstausu.id = usuhisstausu.id_historico_status_usuario
	inner join modulo_usuario modusu
		on modusu.id_usuario = usu.id
where (hisstausu.status = 'CASO_NOVO' and hisstausu.data_final_vigencia is null) and modusu.status in ('INTEGRADO') and modusu.data_inicio > '01-01-2015');

update historico_status_usuario set status = 'DESISTENTE' where id in (
select distinct(hisstausu.id) from usuario usu
	inner join usuario_historico_status_usuario usuhisstausu 
		on usuhisstausu.id_usuario  = usu.id
	inner join historico_status_usuario hisstausu
		on hisstausu.id = usuhisstausu.id_historico_status_usuario
	inner join modulo_usuario modusu
		on modusu.id_usuario = usu.id
where (hisstausu.status = 'CASO_NOVO' and hisstausu.data_final_vigencia is null) and modusu.status in ('REMOVIDO') and modusu.data_inicio > '01-01-2015');
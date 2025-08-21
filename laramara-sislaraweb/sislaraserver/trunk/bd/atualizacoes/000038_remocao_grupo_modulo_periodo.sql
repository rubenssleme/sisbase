-- Desabilitar G02-07 --
update grupo set ativo = 'false' where id in (91);
update grupo set excluido = 'true' where id in (91);
-- Desabilitar Módulo do Grupo ART02-01 --
delete from grupo_modulo_periodo where id_grupo = 71 and id_modulo_periodo = 80;
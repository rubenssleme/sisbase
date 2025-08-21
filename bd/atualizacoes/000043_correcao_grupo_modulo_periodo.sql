update modulo_periodo_programacao set id_modulo_periodo = 116 where id_modulo_periodo = 118 and id_programacao in (7, 12, 13, 16, 56);
update modulo_periodo set (dia_semana, hora_inicio, hora_termino) = ('QUINTA', '14:00:00', '15:15:00') where id in (116);
delete from grupo_modulo_periodo where id_grupo in (90) and id_modulo_periodo in (118, 119, 120);
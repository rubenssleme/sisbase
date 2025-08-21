-- ART – 01 - Remoção do Módulo duplicado e Relacionamento da Programacao --
delete from grupo_modulo_periodo where id_grupo = 70 and id_modulo_periodo in (114);
delete from modulo_periodo_profissional where id_modulo_periodo in (114) and id_profissional in (63);
update modulo_periodo_programacao set id_modulo_periodo = 79 where id_modulo_periodo in (114) and id_programacao in (4, 17, 18);
delete from modulo_periodo where id in (114);
-- MBR – 01 - 02 - Remoção do Módulo duplicado --
delete from grupo_modulo_periodo where id_grupo = 73 and id_modulo_periodo in (88, 106);
delete from modulo_periodo_profissional where id_modulo_periodo in (88, 106) and id_profissional in (63);
delete from modulo_periodo where id in (88, 106);

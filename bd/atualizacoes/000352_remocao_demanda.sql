delete from demanda_historico_status_demanda;
delete from historico_status_demanda;
delete from demanda;
delete from grupo_lote_recurso where id_lote_recurso in (3);
delete from lote_recurso where id_recurso in (4);
delete from usuario_recurso where id_recurso in (4);
delete from recurso where id in (7, 4, 5);
update recurso set cartela_de_selos = true, valor = '2500.00' where id in (3);
update recurso set cartela_de_selos = true, valor = '2100.00' where id in (9);
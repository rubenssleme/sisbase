update recurso set cartela_de_selos = true, valor = '2500.00' where id in (3);
update recurso set cartela_de_selos = true, valor = '2100.00' where id in (9);
ALTER SEQUENCE demanda_id_seq RESTART WITH 758;
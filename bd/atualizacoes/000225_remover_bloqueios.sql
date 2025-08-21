delete from bloqueio where id in (94,95);

update instituicao set id_endereco = 0 where id = 99999;
update endereco set id = 999999999 where id = 99999;
update instituicao set id_endereco = 999999999 where id = 99999;

update instituicao set id_contato = 0 where id = 99999;
update contato set id = 999999999 where id = 99999;
update instituicao set id_contato = 999999999 where id = 99999;

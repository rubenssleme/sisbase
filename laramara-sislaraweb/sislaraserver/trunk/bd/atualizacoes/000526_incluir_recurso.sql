insert into recurso(id, descricao) values (25, 'Cadeira de rodas');
update recurso_relacionamento set id_recurso = 1 where id_recurso = 12; 
update recurso_descricao_recurso set id_recurso = 1 where id_recurso = 12;
update recurso set disponivel_para_demanda = true where id = 1;
delete from recurso where id = 12;

select 'insert into recurso_relacionamento(id_recurso, possui_necessita)values(25, ''POSSUI'');', 
'insert into usuario_recurso_relacionamento(id_usuario, id_recurso_relacionamento)values('||usu.id||', currval(''recurso_relacionamento_id_seq''));'  
from usuario usu 
where usu.cadeira_de_rodas is true;

ALTER TABLE usuario RENAME COLUMN cadeira_de_rodas TO xxxxxxxxxxx_cadeira_de_rodas;
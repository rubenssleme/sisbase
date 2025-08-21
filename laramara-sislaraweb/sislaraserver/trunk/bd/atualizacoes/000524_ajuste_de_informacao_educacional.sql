insert into escolaridade(id, descricao) values (9, 'Não alfabetizado');

select 'insert into informacao_educacional(data_inicial_vigencia, data_referencia'||case when usu.nao_alfabetizado is true THEN ', id_escolaridade' else '' end||''||case when usu.nao_esta_na_escola is true THEN ', situacao' else '' end||', excluido)values(now(), now()'||case when usu.nao_alfabetizado is true THEN ', 9' else '' end||''||case when usu.nao_esta_na_escola is true THEN ', ''NÃO_ESTA_NA_ESCOLA''' else '' end||', false);', 
'insert into usuario_informacao_educacional(id_usuario, id_informacao_educacional)values('||usu.id||', currval(''informacao_educacional_id_seq''));'  
from usuario usu 
	left join usuario_informacao_educacional usuinfedu
		on usuinfedu.id_usuario = usu.id
	left join informacao_educacional infedu
		on usuinfedu.id_informacao_educacional = infedu.id
where (usu.nao_alfabetizado is true or usu.nao_esta_na_escola is true) 
group by usu.id
having count(infedu.id) = 0
order by usu.id;

ALTER TABLE usuario RENAME COLUMN nao_alfabetizado TO xxxxxxxxxxx_nao_alfabetizado;
ALTER TABLE usuario RENAME COLUMN nao_esta_na_escola TO xxxxxxxxxxx_nao_esta_na_escola;

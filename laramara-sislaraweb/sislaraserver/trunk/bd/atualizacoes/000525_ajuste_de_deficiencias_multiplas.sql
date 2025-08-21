insert into comprometimento(id, descricao) values (12, 'Deficiência múltipla');

select 'insert into periodo_comprometimento(id_comprometimento, data_inicial_vigencia)values(12, now());', 
'insert into usuario_periodo_comprometimento(id_usuario, id_periodo_comprometimento)values('||usu.id||', currval(''periodo_comprometimento_id_seq''));'  
from usuario usu 
where usu.multipla_deficiencia is true;

ALTER TABLE usuario RENAME COLUMN multipla_deficiencia TO xxxxxxxxxxx_multipla_deficiencia;

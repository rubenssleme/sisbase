CREATE OR REPLACE FUNCTION remover_acento(text) 
RETURNS text AS 
$BODY$ 
select 
translate($1,'áàâãäéèêëíìïóòôõöúùûüÁÀÂÃÄÉÈÊËÍÌÏÓÒÔÕÖÚÙÛÜçÇ', 'aaaaaeeeeiiiooooouuuuAAAAAEEEEIIIOOOOOUUUUcC'); 
$BODY$ 
LANGUAGE 'sql' IMMUTABLE STRICT; 
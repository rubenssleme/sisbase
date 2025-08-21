ALTER TABLE informacao_essencial ADD COLUMN CPF VARCHAR(11);

UPDATE 
  informacao_essencial t1  
SET 
  cpf = t3.cpf
FROM 
  informacao_essencial t2 
  INNER JOIN usuario t3 
	on t3.id_informacao_essencial = t2.id 
WHERE 
  t1.id = t2.id;

ALTER TABLE usuario DROP COLUMN cpf;
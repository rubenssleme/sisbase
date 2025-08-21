ALTER TABLE USUARIO ALTER COLUMN RENDA TYPE DECIMAL;
update usuario set renda = renda * 788.00 
where renda is not null;

ALTER TABLE FAMILIAR ALTER COLUMN RENDA TYPE DECIMAL;
update FAMILIAR set renda = renda * 788.00 
where renda is not null;
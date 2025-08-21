ALTER TABLE usuario ADD COLUMN cadeira_de_rodas boolean DEFAULT false;

update Usuario set cadeira_de_rodas = false;
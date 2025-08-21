ALTER TABLE instituicao ALTER COLUMN tipo_apoio TYPE varchar(40);
update instituicao set tipo_apoio = 'SALA_DE_RECURSO_DO_ESTADO' where tipo_apoio = 'SALA_DE_RECURSO';
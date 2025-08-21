ALTER TABLE inscricao ADD COLUMN id_grupo bigint not null;
ALTER TABLE inscricao ADD FOREIGN KEY (id_grupo) REFERENCES grupo(id);
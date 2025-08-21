ALTER TABLE profissional ADD COLUMN ESPECIALIDADE VARCHAR(50);
update profissional set especialidade = 'OFTALMOLOGIA' where id in (10, 11);
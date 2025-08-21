ALTER SEQUENCE doenca_id_seq RESTART WITH 18;
update modulo_usuario set data_inicio = '06/04/2016' where id = 11370;
insert into profissional(nome, habilitado, voluntario, profissional)values('Célio Figueira Costa Filho', true, true, true);	
insert into cargo(nome)values('Autônomo');
insert into doenca(descricao)values('Síndrome'); 
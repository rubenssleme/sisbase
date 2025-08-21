insert into profissional(nome, habilitado, voluntario)values('SIDNEI IVANOF', true, true);
insert into profissional(nome, habilitado, voluntario)values('ROBERTO LOPES JUNIOR', true, true);
insert into profissional(nome, habilitado, voluntario)values('GENTIL NISHIOKA', true, true);
insert into profissional(nome, habilitado, voluntario)values('DANILO NAMO COSTA', true, true);
insert into profissional(nome, habilitado, voluntario)values('NELSON RODOVERI JUNIOR', true, true);
update profissional set habilitado = FALSE, voluntario = TRUE where id in (62);
update profissional set voluntario = TRUE where id in (72, 73, 71, 61, 63, 66, 68, 70, 64, 67);
update profissional set habilitado = FALSE where id in (74);
delete from tipo_vinculo where id in(1, 2, 4, 6, 7, 8, 14);
update tipo_vinculo set descricao = 'Terapeuta' where id = 3;
insert into tipo_vinculo(id, descricao) values (15, 'Representante da Família');
update tipo_vinculo set descricao = 'Representante de Instituição Especializada' where id = 11;

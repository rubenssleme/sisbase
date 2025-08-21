delete from usuario_encaminhamento where id_encaminhamento in (150, 161, 190, 194, 222, 227);
delete from encaminhamento where id_origem_encaminhamento in (4, 5);
delete from origem_encaminhamento where id in (4, 5);
insert into origem_encaminhamento(descricao)values('INSS');
insert into origem_encaminhamento(descricao)values('Instituição Especializada');
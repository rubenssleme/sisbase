insert into origem_encaminhamento(descricao)values('Profissional Liberal');
insert into origem_encaminhamento(descricao)values('Amigos');
insert into origem_encaminhamento(descricao)values('Médico');
insert into origem_encaminhamento(descricao)values('Psicólogo');
insert into origem_encaminhamento(descricao)values('Mídia');
insert into origem_encaminhamento(descricao)values('Laratec');
insert into origem_encaminhamento(descricao)values('Familiar');
insert into origem_encaminhamento(descricao)values('CER');
insert into origem_encaminhamento(descricao)values('CRAS');

insert into rede_encaminhamento(descricao)values('SUS');
insert into rede_encaminhamento(descricao)values('EDUCAÇÃO');
insert into rede_encaminhamento(descricao)values('PRIVADO');

insert into cargo(nome)values('Sargento');

insert into servico(descricao)values('Neurocirurgião');
insert into servico(descricao)values('Nutrólogo');
insert into servico(descricao)values('Pneumologista');

insert into necessidade(descricao)values('Oftalmologista');

update item_custo set descricao = 'Farmácia' where id = 3;
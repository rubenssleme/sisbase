alter table modulo_usuario add column IGNORAR_REGRA_REUNIAO_DE_INTEGRACAO BOOLEAN DEFAULT 'false' NOT NULL;
update modulo_usuario set ignorar_regra_reuniao_de_integracao = true;
update pendencia set tipo = 'TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO' where tipo = 'REUNIAO_DE_INTEGRACAO';
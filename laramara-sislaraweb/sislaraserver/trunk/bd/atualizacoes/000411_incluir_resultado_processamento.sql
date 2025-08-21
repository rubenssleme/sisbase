ALTER TABLE acao_conduta ADD COLUMN RESULTADO_PROCESSAMENTO VARCHAR(20000);
update acao_conduta set data_processamento = null where id = 1;

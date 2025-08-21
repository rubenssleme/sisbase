update solicitacao_relatorio_finalidade_relatorio set finalidade_relatorio = 'ISENCAO_DE_IPI_NA_COMPRA_DE_CARRO' where finalidade_relatorio = 'ISENCAO_PARA_COMPRA_DE_VEICULO';
update solicitacao_relatorio_finalidade_relatorio set finalidade_relatorio = 'PROCESSO_SELETIVO_E_TRABALHO' where finalidade_relatorio = 'TRABALHO';
update solicitacao_relatorio_finalidade_relatorio set finalidade_relatorio = 'EMTU' where finalidade_relatorio = 'CARTEIRA_ESPECIAL_DE_EMTU';
update solicitacao_relatorio_finalidade_relatorio set finalidade_relatorio = 'SPTRANS' where finalidade_relatorio in ('CARTEIRA_ESPECIAL_DE_METRO', 'CARTEIRA_ESPECIAL_DE_ONIBUS', 'CARTEIRA_ESPECIAL_DE_TREM');

package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import java.util.Date;

public class ModeloTabelaProjeto extends ModeloTabela<ProjetoDTO> {

    public ModeloTabelaProjeto(){
        super(new String[]{"ID", "Nome do Projeto", "Data Inicial", "Data Final", "Ativo", "Recursos", "Valor Produtos", "Valor Outros", "Valor Total"},
                new Class[]{Long.class, String.class, Date.class, Date.class, Boolean.class, String.class, String.class, String.class, String.class},
                new int[]{10, 120, 100, 100, 20, 50, 20, 20, 20});
    }
    
    @Override
    protected void adicionarDado(ProjetoDTO objetoDto) {
            addRow(new Object[]{
                objetoDto.getId(),
                objetoDto.getNome(),
                DataHoraUtils.obterDataValidaInvalidaOuNulo(objetoDto.getDataInicial()).getTime(),
                !objetoDto.getDataFinal().isEmpty() ? DataHoraUtils.obterDataValidaInvalidaOuNulo(objetoDto.getDataFinal()).getTime() : null,
                objetoDto.isAtivo(),
                TextoUtils.removerChaves(objetoDto.getLoteRecursoDto().toString()),
                objetoDto.getValorProdutos(),
                objetoDto.getValorOutros(),
                objetoDto.getValorTotal()
                });
            }
    }
package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import java.util.Date;

public class ModeloTabelaRecibo extends ModeloTabela<ReciboDTO> {
    
    public ModeloTabelaRecibo() {
        super(new String[]{"Número", "Nome", "CFP/CNPJ", "Valor", "Data", "Data/Hora do Registro", "Filial", "Descrição", "Cancelado", "Motivo do Cancelamento", "Histórico de operações"},
                new Class[]{Long.class, String.class, String.class, String.class, Date.class, String.class, Long.class, String.class, Boolean.class, String.class, String.class},
                new int[]{20, 100, 20, 10, 15, 15, 5, 100, 10, 100, 100});
    }

    @Override
    protected void adicionarDado(ReciboDTO objetoDto) {
        addRow(new Object[]{
            objetoDto.getId(),
            objetoDto.getNome(),
            objetoDto.getCpfCnpj(),
            objetoDto.getValorTotalRecibo(),
            DataHoraUtils.obterDataOuNulo(objetoDto.getData()),
            objetoDto.getDataRegistro(),
            objetoDto.getFilial().getId(),
            objetoDto.getDescricao(),
            objetoDto.isCancelado(),
            objetoDto.getMotivoDoCancelamento(),
            objetoDto.getHistoricoOperacoes()
        });
    }
}

package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import java.util.Date;

public class ModeloTabelaContribuinte extends ModeloTabela<ContribuinteDTO> {
    
    public ModeloTabelaContribuinte() {
        super(new String[]{"ID", "Data de Cadastro", "Nome/Empresa"},
                new Class[]{Long.class, Date.class, String.class},
                new int[]{20, 50, 200});
    }

    @Override
    protected void adicionarDado(ContribuinteDTO objetoDto) {
        addRow(new Object[]{
            objetoDto.getId(),
            DataHoraUtils.obterDataOuNulo(objetoDto.getDataCadastro()),
            objetoDto.getNomeEmpresa()
        });
    }
}

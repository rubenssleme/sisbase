package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloUsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import java.util.Date;

public class ModeloTabelaModuloUsuario extends ModeloTabela<ModuloUsuarioDTO> {

    public ModeloTabelaModuloUsuario() {
        super(new String[]{"Protuário", "Nome", "Data Inicial", "Data da Ocorrência", "Status", "Aprovado", "ID", "OBS", "Ignorar Reunião de Integração", "Usuário do PROCEJA", "Usuário do CTO"},
                new Class[]{Long.class, String.class, Date.class, Date.class, String.class, Boolean.class, String.class, String.class, Boolean.class, Boolean.class, Boolean.class},
                new int[]{80, 400, 110, 110, 100, 80, 50, 100, 100, 100, 100});
    }

    @Override
    protected void adicionarDado(ModuloUsuarioDTO objetoDto) {
        addRow(new Object[]{objetoDto.getUsuarioDto().getId(),
                    objetoDto.getUsuarioDto().getInformacaoEssencialDto().getNome(),
                    DataHoraUtils.obterDataOuNulo(objetoDto.getDataInicio()),
                    DataHoraUtils.obterDataOuNulo(objetoDto.getDataOcorrencia()),
                    objetoDto.getStatusDto().toString(),
                    objetoDto.isAprovado(),
                    objetoDto.getId(),
                    objetoDto.getObs(),
                    objetoDto.isIgnorarRegraDeReuniaoDeIntegracao(),
                    objetoDto.getUsuarioDto().isAssociadoAoSetorProceja(),
                    objetoDto.getUsuarioDto().isAssociadoAoSetorCTO()});
    }
}


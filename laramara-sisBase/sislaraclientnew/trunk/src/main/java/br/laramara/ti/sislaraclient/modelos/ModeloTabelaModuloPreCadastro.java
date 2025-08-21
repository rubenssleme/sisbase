
package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPreCadastroDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import java.util.Date;

public class ModeloTabelaModuloPreCadastro extends ModeloTabela<ModuloPreCadastroDTO> {

    public ModeloTabelaModuloPreCadastro() {
        super(new String[]{"Nome", "Formação", "Nome da Origem", "Curso", "Tipo", "Instituição", "DRE / CEFAI", "Diretoria de Ensino", "SAAI", "Sala de Recurso", "Outros AEE", "Data Inicial", "Data da Ocorrência", "Status", "Prontuário Vinculado", "ID"},
                new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Date.class, Date.class, String.class, String.class, String.class},
                new int[]{400, 300, 300, 300, 150, 400, 300, 300, 300, 300, 300, 300, 300, 110, 110, 100, 50, 50});
    }

    @Override
    protected void adicionarDado(ModuloPreCadastroDTO objetoDto) {
        addRow(new Object[]{objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getNome(),
                    objetoDto.getTipoVinculoDto().toString(),
                    objetoDto.getNomeOrigemComunidade(),
                    objetoDto.getCurso(),
                    objetoDto.getInstituicaoDto() != null ? objetoDto.getInstituicaoDto().getTipoInstituicaoDto().toString() : null,
                    objetoDto.getInstituicaoDto() != null ? objetoDto.getInstituicaoDto().getNome() : null,
                    objetoDto.getDreCefaiDto() != null ? objetoDto.getDreCefaiDto().toString() : null,
                    objetoDto.getDiretoriaEnsinoDto() != null ? objetoDto.getDiretoriaEnsinoDto().toString() : null,
                    objetoDto.getInstituicaoComSRMsDto()!= null ? objetoDto.getInstituicaoComSRMsDto() : null,
                    objetoDto.getInstituicaoComSalaDeRecursoDto() != null ? objetoDto.getInstituicaoComSalaDeRecursoDto() : null,
                    objetoDto.getInstituicaoComOutrosAEEDto() != null ? objetoDto.getInstituicaoComOutrosAEEDto() : null,
                    DataHoraUtils.obterDataOuNulo(objetoDto.getDataInicio()),
                    DataHoraUtils.obterDataOuNulo(objetoDto.getDataOcorrencia()),
                    objetoDto.getStatusDto().toString(),
                    objetoDto.getUsuarioVinculadoDto() != null ? objetoDto.getUsuarioVinculadoDto().getId() : null,
                    objetoDto.getId()});
    }
}

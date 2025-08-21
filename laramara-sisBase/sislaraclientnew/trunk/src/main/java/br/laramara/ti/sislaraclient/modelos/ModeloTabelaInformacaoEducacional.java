
package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;

public class ModeloTabelaInformacaoEducacional extends ModeloTabela<InformacaoEducacionalDTO> {
    
    public ModeloTabelaInformacaoEducacional() {
        super(new String[]{"Tipo", "Instituicao", "Escolaridade", "Serie", "Situacao", "Periodo", "Data de Referência", "Área de Formação", "Professor", "Tipo de Especialidade", "Tipo Apoio", "DRE/CEFAIs", "Diretoria de Ensino"},
                new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class, String.class, String.class},
                new int[]{100, 300, 100, 100, 100, 100, 100, 30, 100, 100, 100, 300, 300});
    }

    @Override
    protected void adicionarDado(InformacaoEducacionalDTO objetoDto) {
        addRow(new Object[]{objetoDto.getInstituicaoDto() != null ? objetoDto.getInstituicaoDto().getTipoInstituicaoDto() : "",
                            objetoDto.getInstituicaoDto() != null ? objetoDto.getInstituicaoDto().getNome() : "", 
                            objetoDto.getEscolaridadeDto(), 
                            objetoDto.getSerieDto(), 
                            objetoDto.getSituacaoEducacionalDto(), 
                            objetoDto.getPeriodoDto(), 
                            objetoDto.getDataReferencia(),
                            objetoDto.getAreaFormacaoDto()!=null ? objetoDto.getAreaFormacaoDto().toString() : "",
                            objetoDto.getNomeProfessor(),
                            objetoDto.getInstituicaoDto() != null ? TextoUtils.removerChaves(objetoDto.getInstituicaoDto().getTiposEspecialidadeDTO().toString()) : "",
                            objetoDto.getInstituicaoDto() != null ? objetoDto.getInstituicaoDto().getTipoApoioDto() : "",
                            objetoDto.getInstituicaoDto() != null ?  objetoDto.getInstituicaoDto().getDreCefaiDto() : "",
                            objetoDto.getInstituicaoDto() != null ? objetoDto.getInstituicaoDto().getDiretoriaEnsinoDto() : ""});
    }
}
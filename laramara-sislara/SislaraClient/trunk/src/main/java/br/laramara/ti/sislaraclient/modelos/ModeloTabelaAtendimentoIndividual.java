package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import java.util.Date;

public class ModeloTabelaAtendimentoIndividual extends ModeloTabela<AtendimentoIndividualDTO> {
    
    public ModeloTabelaAtendimentoIndividual (){
        super(new String[]{"Data", "Hora Início", "Hora Termino", "Prontuário", "Nome", "Descrição", "Interdisciplinar", "Frequência", "Justificativa/OBS", "Status", "Participação", "Tipo de Atendimento", "Descrição do Tipo de Atendimento", "Modulo", "Profisssional", "ID", "Setor", "Primeira Vez ou Retorno"},
                new Class[] {Date.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Long.class, String.class, String.class},
                new int[]{100, 80, 80, 80, 400, 400, 400, 80, 400, 100, 150, 350, 500, 350, 400, 50, 100, 20});
    }
                
    @Override
    protected void adicionarDado(AtendimentoIndividualDTO objetoDto) {
        addRow(new Object[]{
                    DataHoraUtils.obterDataOuNulo(objetoDto.getData()),
                    objetoDto.getHorarioDto().getHoraInicio(),
                    objetoDto.getHorarioDto().getHoraTermino(),
                    objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getId().toString() : null,
                    objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getInformacaoEssencialDto().getNome() : 
                        objetoDto.getPreCadastroDto() != null ? objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getNome() :  null,
                    objetoDto.getInformacaoAtendimentoDto().getDescricao(),
                    objetoDto.getInterdisciplinar(),
                    objetoDto.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
                    objetoDto.getInformacaoAtendimentoDto().getJustificativa(),
                    objetoDto.getStatusAtendimentoDto().toString(),
                    objetoDto.getParticipacaoDto() != null ? objetoDto.getParticipacaoDto().toString() : null,
                    objetoDto.getDescricaoTipoAtendimentoDto().getTipoAtendimentoDto().toString(),
                    objetoDto.getDescricaoTipoAtendimentoDto().toString(),
                    objetoDto.getModuloDto().toString(),
                    TextoUtils.removerChaves(objetoDto.getAtendimentosProfissionalDto().toString()),
                    objetoDto.getId(),
                    objetoDto.getSetorDto().toString(),
                    objetoDto.getPrimeiraVezOuRetornoDto() != null ? objetoDto.getPrimeiraVezOuRetornoDto().toString() : null
        });
    }
}

package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import java.util.Date;

public class ModeloTabelaAtendimentoIndividual extends ModeloTabela<AtendimentoIndividualDTO> {
    
    public ModeloTabelaAtendimentoIndividual (){
        super(new String[]{"Data", "Hora Início", "Hora Termino", "Prontuário", "Nome", "Descrição", "Interdisciplinar", "Frequência", "Justificativa/OBS", "Status", "Participação Detalhada", "Tipo de Atendimento", "Descrição do Tipo de Atendimento", "Modulo", "Profisssional", "ID", "Setor", "Primeira Vez ou Retorno", "Idade", "Integração", "Discussão de Caso"},
                new Class[] {Date.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Long.class, String.class, String.class, String.class, String.class, String.class},
                new int[]{100, 80, 80, 80, 400, 400, 400, 80, 400, 100, 150, 350, 500, 350, 400, 50, 100, 20, 40, 100, 100});
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
                    objetoDto.getInformacaoAtendimentoDto().obterParticipacoesDetalhadas(),
                    objetoDto.getDescricaoTipoAtendimentoDto().getTipoAtendimentoDto().toString(),
                    objetoDto.getDescricaoTipoAtendimentoDto().toString(),
                    objetoDto.getModuloDto().toString(),
                    TextoUtils.removerChaves(objetoDto.getAtendimentosProfissionalDto().toString()),
                    objetoDto.getId(),
                    objetoDto.getSetorDto().toString(),
                    objetoDto.getPrimeiraVezOuRetornoDto() != null ? objetoDto.getPrimeiraVezOuRetornoDto().toString() : null,
                    objetoDto.getIdadeDoUsuarioOuPreCadastro(),
                    objetoDto.getOpcaoIntegracaoDto() != null ? objetoDto.getOpcaoIntegracaoDto().toString() : null,
                    objetoDto.getDiscussaoCasoSimNaoDto() != null ? objetoDto.getDiscussaoCasoSimNaoDto().toString() : null
        });
    }
}

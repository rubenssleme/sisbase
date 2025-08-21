package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import java.util.Date;

public class ModeloTabelaAgendamento extends ModeloTabela<AgendamentoDTO> {
    
    public ModeloTabelaAgendamento (){
        super(new String[]{"Data", "Hora Início", "Hora Termino", "Prontuário", "Nome/Grupo", "Status", "Reservador Para", "Nome Contato", "Telefone Contato", "Setor", "Profissional", "Tipo de Atendimento", "Descricao do Tipo de Atendimento", "Modulo/Atividade", "Sala", "OBS", "Dia da Semana", "Motivo do Cancelamento", "Justificativa do Cancelamento", "Responsáveis por Operações", "ID", "Prontuário Criado", "Idade"},
                new Class[] {Date.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Long.class, Long.class, String.class},
                new int[]{100, 80, 80, 80, 400, 100, 300, 400, 400, 150, 300, 350, 400, 400, 400, 500, 100, 300, 500, 2000, 50, 20, 40});
    }
                
    @Override
    protected void adicionarDado(AgendamentoDTO objetoDto) {
        addRow(new Object[]{
            DataHoraUtils.obterDataOuNulo(objetoDto.getData()),
            objetoDto.getHorarioDto().getHoraInicio(),
            objetoDto.getHorarioDto().getHoraTermino(),
            objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getId().toString() : null,
            objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getInformacaoEssencialDto().getNome()
            : objetoDto.getPreCadastroDto() != null ? objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getNome()
            : !objetoDto.getGruposDto().isEmpty() ? TextoUtils.removerChaves(objetoDto.getGruposDto().toString()) : null,
            objetoDto.getStatusDto().toString(),
            objetoDto.getReservaParaDto().toString(),
            objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getInformacaoEssencialDto().getContatoDto().getNomeContato()
            : objetoDto.getPreCadastroDto() != null ? objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getContatoDto().getNomeContato() : null,
            objetoDto.getUsuarioDto() != null ? TextoUtils.removerChaves(objetoDto.getUsuarioDto().getInformacaoEssencialDto().getContatoDto().getTelefonesDto().toString())
            : objetoDto.getPreCadastroDto() != null ? TextoUtils.removerChaves(objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getContatoDto().getTelefonesDto().toString()) : null,
            objetoDto.getSetorDto().toString(),
            objetoDto.getProfissionalDto().toString(),
            objetoDto.getDescricaoTipoAtendimentoDto().getTipoAtendimentoDto(),
            objetoDto.getDescricaoTipoAtendimentoDto().toString(),
            objetoDto.getModuloDto().toString(),
            objetoDto.getLocalAtendimentoDto().toString(),
            objetoDto.getObs(),
            objetoDto.getDiaSemanaDto().toString(),
            objetoDto.getMotivoCancelamentoDTO() != null ? objetoDto.getMotivoCancelamentoDTO().toString() : null,
            objetoDto.getJustificativaCancelamento(),
            objetoDto.getResponsaveisOperacoes(),
            objetoDto.getId(),
            objetoDto.getUsuarioCriadoPeloPreCadastro() != null ? objetoDto.getUsuarioCriadoPeloPreCadastro().getId() : null,
            objetoDto.getIdadeDoUsuarioOuPreCadastro()
        });
    }
}

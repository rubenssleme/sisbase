package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import java.util.Date;

public class ModeloTabelaListaEspera extends ModeloTabela<EsperaDTO> {

    public ModeloTabelaListaEspera() {
        super(new String[]{"Data de Cadastro", "Tipo", "Data de Expectativa", "Prontuário", "Nome", "Idade", "Status", "Telefone Contato", "Nome Contato", "Tipo de Atendimento", "Descrição de Tipo de Atendimento", "Modulo/Atividade", "Nome do Grupo", "Setor da Espera", "Solicitado Por", "OBS", "Justificativa do Cancelamento", "Responsáveis por Operações", "ID", "RG", "Setor do Usuário", "Prontuário Criado", "Status do Usuário", "Interesse/Usuário ligou", "LM ligou", "Pendencias", "Última data de ligação do usuário"},
                new Class[]{Date.class, String.class, Date.class, Long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Long.class, String.class, String.class, Long.class, String.class, Boolean.class, Boolean.class, Boolean.class, Date.class},
                new int[]{110, 10, 110, 80, 400, 80, 120, 400, 300, 350, 500, 350, 100, 100, 300, 400, 400, 2000, 50, 50, 50, 80, 20, 20, 20, 20, 110});
    }

    @Override
    protected void adicionarDado(EsperaDTO objetoDto) {
        addRow(new Object[]{
            DataHoraUtils.obterDataOuNulo(objetoDto.getDataCadastro()),
            objetoDto.getTipoEsperaDto() != null ? objetoDto.getTipoEsperaDto().toString() : "",
            DataHoraUtils.obterDataOuNulo(objetoDto.getDataExpectativa()),
            objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getId() : null,
            objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getInformacaoEssencialDto().getNome() : 
                        objetoDto.getPreCadastroDto() != null ? objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getNome() :  null,
            objetoDto.getUsuarioDto()!=null ? objetoDto.getUsuarioDto().getInformacaoEssencialDto().getIdade() : objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getIdade(),
            objetoDto.getStatusDto().toString(),
            objetoDto.getUsuarioDto() != null ? TextoUtils.removerChaves(objetoDto.getUsuarioDto().getInformacaoEssencialDto().getContatoDto().getTelefonesDto().toString()) : 
                        objetoDto.getPreCadastroDto() != null ? TextoUtils.removerChaves(objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getContatoDto().getTelefonesDto().toString()) : null,
            objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getInformacaoEssencialDto().getContatoDto().getNomeContato() :
                        objetoDto.getPreCadastroDto() != null ? objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getContatoDto().getNomeContato() : null,
            objetoDto.getDescricaoTipoAtendimentoDto().getTipoAtendimentoDto().toString(),
            objetoDto.getDescricaoTipoAtendimentoDto().toString(),
            objetoDto.getModuloDto().toString(),
            objetoDto.getNomeGrupoDto() != null ? objetoDto.getNomeGrupoDto().toString() : null,
            objetoDto.getSetorDto().toString(),
            objetoDto.getProfissionalSolicitioDto() != null ? objetoDto.getProfissionalSolicitioDto().toString() : null,
            objetoDto.getObs(),
            objetoDto.getJustificativaCancelamento(),
            objetoDto.getHistorioOperacoes(),
            objetoDto.getId(),
            objetoDto.getPreCadastroDto() != null ? objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getRg() : objetoDto.getUsuarioDto().getInformacaoEssencialDto().getRg(),
            TextoUtils.removerChaves(objetoDto.getSetoresUsuario().toString()),
            objetoDto.getUsuarioCriadoPeloPreCadastro() != null ? objetoDto.getUsuarioCriadoPeloPreCadastro().getId() : null, 
            objetoDto.getUsuarioDto() != null && objetoDto.getUsuarioDto().getStatusUsuarioAtualDto() != null ? objetoDto.getUsuarioDto().getStatusUsuarioAtualDto().toString() : null,
            objetoDto.isInteresse(), 
            objetoDto.isLmLigou(),
            objetoDto.isPendencias(),
            DataHoraUtils.obterDataOuNulo(objetoDto.getDataUltimaLigacaoInteresse())
        });
    }
}

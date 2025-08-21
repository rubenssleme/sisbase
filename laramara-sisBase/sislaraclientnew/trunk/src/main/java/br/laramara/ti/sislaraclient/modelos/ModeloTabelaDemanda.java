package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import java.util.Date;

public class ModeloTabelaDemanda extends ModeloTabela<DemandaDTO> {

    public ModeloTabelaDemanda(){
        super(new String[]{"ID", "Prazo de captação", "Valor Captado", "Valor da Cartela", "Saldo",  "Status", "Prontuário", "Usuário", "Pré-Cadastro", "Recurso", "Cartela de Selos", "Observações", "Responsáveis por Operações", "Motivo de cancelamento"},
                new Class[] {Long.class, Date.class, String.class, String.class, String.class, String.class, Long.class, String.class, String.class, String.class, Boolean.class, String.class, String.class, String.class},
                new int[]{10, 50, 50, 50, 100, 100, 50, 100, 100, 100, 100, 100, 100, 100});    
    }
    
    @Override
    protected void adicionarDado(DemandaDTO objetoDto) {
        addRow(new Object[]{
                    objetoDto.getId(),
                    DataHoraUtils.obterDataOuNulo(objetoDto.getDataPrazoCaptacao()),
                    objetoDto.getValorTotalCaptado(),
                    objetoDto.getValorCartela(),
                    objetoDto.getValorSaldo(),
                    objetoDto.getStatusDemandaDto().toString(),
                    objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getId() : null,
                    objetoDto.getUsuarioDto() != null ? objetoDto.getUsuarioDto().getInformacaoEssencialDto().getNome() : null,
                    objetoDto.getPreCadastroDto() != null ? objetoDto.getPreCadastroDto().getInformacaoEssencialDto().getNome() : null,
                    objetoDto.getRecursoDto().toString(),  
                    objetoDto.isCartelaDeSelos(),
                    objetoDto.getObs(),
                    objetoDto.getResponsaveisOperacoes(),
                    objetoDto.getMotivoCancelamentoDTO()});
    }
}

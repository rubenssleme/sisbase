
package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;

public class ModeloTabelaSolicitacaoRelatorio extends ModeloTabela<SolicitacaoRelatorioDTO> {
    
    public ModeloTabelaSolicitacaoRelatorio (){
        super(new String[]{"Protocolo", "Nome do Solicitante", "RG do Solicitante", "Prontu�rio", "Usu�rio", "Elaborador", "Status Atual", "Enviar via Correio", "Finalidade do Relat�rio", "Qtd de relat�rios emitidos", "Qtd de relat�rios entregues", "OBS", "Respons�veis por opera��es"},
                new Class[] {Long.class, String.class, String.class, Long.class, String.class, String.class, String.class, Boolean.class, String.class, String.class, String.class, String.class, String.class},
                new int[]{30, 100, 50, 15, 100, 20, 25, 10, 300, 10, 10, 400, 200});
    }
                
    @Override
    protected void adicionarDado(SolicitacaoRelatorioDTO objetoDto) {
        addRow(new Object[]{
                    objetoDto.getId(),
                    objetoDto.getNomeSolicitante(),
                    objetoDto.getRgSolicitante(),
                    objetoDto.getUsuarioDto().getId(),
                    objetoDto.getUsuarioDto().getInformacaoEssencialDto().getNome(),
                    objetoDto.getElaboradorDto().toString(),    
                    objetoDto.getStatusAtual().toString(),
                    objetoDto.isEnviarPeloCorreio(),
                    TextoUtils.removerChaves(objetoDto.getFinalidadesRelatorios()),
                    objetoDto.getQuantidadeRelatoriosEmitidos(),
                    objetoDto.getQuantidadeRelatoriosEntregues(),
                    objetoDto.getObs(),
                    objetoDto.getResponsavelPorOperacao()});
    }
    
}

package br.laramara.ti.sislaraclient.modelos;

import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import java.util.Date;

public class ModeloTabelaProgramacao extends ModeloTabela<ProgramacaoDTO> {
    
    public ModeloTabelaProgramacao (){
        super(new String[]{"Aula", "Data", "Conteúdo", "Estratégia", "Recado para Família/OBS", "Local de Atendimento"},
                new Class[] {Integer.class, Date.class, String.class, String.class, String.class, String.class},
                new int[]{50, 100, 400, 600, 400, 150});
    }
                
     @Override
    protected void adicionarDado(ProgramacaoDTO objetoDto) {
        addRow(new Object[]{new Integer(objetoDto.getAula()), 
                            DataHoraUtils.obterDataValidaInvalidaOuNulo(objetoDto.getData()).getTime(), 
                            objetoDto.getTemaConteudo(), 
                            objetoDto.getEstrategias(),
                            objetoDto.getRecadoFamilia(),
                            objetoDto.getLocalAtendimentoDTO()});
    
    }
}
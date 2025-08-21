package br.laramara.ti.sismovimentacaoclient.modelos;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;

public class ModeloMovimentacao extends ModeloTabela<MovimentacaoDTO> {

    public ModeloMovimentacao() {
        super(new String[]{"ID", "Status", "Classificacao", "GL", "Cliente", "Código do Produto", "Descrição", "Descrição do Produto", "Quantidade de Cor", "Cor", "Direção Fibra", "Formato", "Código Anterior", "Gramatura", "Laetus", "Obs de especificação", "Obs de Artes", "Fibra", "Papel", "Sangria", "ABDB", "Especificação", "GR", "Pasta", "Bobina", "Plana Papel", "Tipo de Prova"},
                new Class[]{Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class},
                new int[]{50, 150, 100, 100, 200, 120, 200, 200, 100, 100, 100, 100, 100, 100, 100, 200, 200, 100, 100, 100, 100, 100, 300, 50, 50, 50, 50, 100});
    }

    @Override
    protected void adicionarDado(MovimentacaoDTO objetoDto) {
        addRow(new Object[]{
                    objetoDto.getId(),
                    objetoDto.getStatus() != null ? objetoDto.getStatus().toString() : "",
                    objetoDto.getStatus() != null ? objetoDto.getStatus().getClassificacaoDto().toString() : "",
                    objetoDto.getGl(),
                    objetoDto.getCliente(),
                    objetoDto.getCodigoProduto(),
                    objetoDto.getDescricao(),
                    objetoDto.getDescricaoProduto(),
                    objetoDto.getQuantidadeCor(),
                    objetoDto.getCor(),
                    objetoDto.getDirecaoFibra(),
                    objetoDto.getFormato(),
                    objetoDto.getCodigoAnterior(),
                    objetoDto.getGramatura(),
                    objetoDto.getLaetus(),
                    objetoDto.getObsEspecificacao(),
                    objetoDto.getObsArte(),
                    objetoDto.getFibraDto() != null ? objetoDto.getFibraDto().toString() : "",
                    objetoDto.getPapelDto() != null ? objetoDto.getPapelDto().toString() : "",
                    objetoDto.getSangriaSimNaoDto() != null ? objetoDto.getSangriaSimNaoDto().toString() : "",
                    objetoDto.getAbdbDto() != null ? objetoDto.getAbdbDto().toString() : "",
                    objetoDto.getEspecificacaoSimNaoDto() != null ? objetoDto.getEspecificacaoSimNaoDto().toString() : "",
                    objetoDto.getGr(),
                    objetoDto.getPasta(),
                    objetoDto.getBobina(),
                    objetoDto.getPlanaPapel(),
                    objetoDto.getTipoProva()});
    }
}

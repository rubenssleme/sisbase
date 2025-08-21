package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.DocumentoSolicitacaoDoacaoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.DocumentoSolicitacaoDoacao;

public class FabricaDocumentoSolicitacaoDoacao
		extends FabricaBase<DocumentoSolicitacaoDoacaoDTO, DocumentoSolicitacaoDoacao> {

	@Override
	public DocumentoSolicitacaoDoacaoDTO converterParaDTO(DocumentoSolicitacaoDoacao documentoSolicitacaoDoacao) {
		DocumentoSolicitacaoDoacaoDTO documentoSolicitacaoDoacaoDto = null;
		if (documentoSolicitacaoDoacao != null) {
			documentoSolicitacaoDoacaoDto = new DocumentoSolicitacaoDoacaoDTO();
			documentoSolicitacaoDoacaoDto.setId(documentoSolicitacaoDoacao.getId());
			documentoSolicitacaoDoacaoDto.setNomeDocumentoDTO(
					new FabricaNomeDocumento().converterParaDTO(documentoSolicitacaoDoacao.getNomeDocumento()));
			documentoSolicitacaoDoacaoDto
					.setArquivoDTO(new FabricaArquivo().converterParaDTO(documentoSolicitacaoDoacao.getArquivo()));
		}
		return documentoSolicitacaoDoacaoDto;
	}

	@Override
	public DocumentoSolicitacaoDoacao converterParaDominio(
			DocumentoSolicitacaoDoacaoDTO documentoSolicitacaoDoacaoDto) {
		DocumentoSolicitacaoDoacao documentoSolicitacaoDoacao = new DocumentoSolicitacaoDoacao();
		documentoSolicitacaoDoacao.setId(documentoSolicitacaoDoacaoDto.getId());
		documentoSolicitacaoDoacao.setNomeDocumento(
				new FabricaNomeDocumento().converterParaDominio(documentoSolicitacaoDoacaoDto.getNomeDocumentoDTO()));
		documentoSolicitacaoDoacao
				.setArquivo(new FabricaArquivo().converterParaDominio(documentoSolicitacaoDoacaoDto.getArquivoDTO()));
		return documentoSolicitacaoDoacao;
	}
}

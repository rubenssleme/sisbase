package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;
import java.util.Map;

import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.seguranca.Area;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public interface RepositorioSislara {
	public List<Map<String, Object>> obterFrenquenciaPorProntuarioExcluindoArea(
			Long idusuario, Area area);

	public List<Map<String, Object>> obterFrenquenciaDeModulosNoMesmoDia(
			Long idusuario, Long idDescricaoTipoAtendimento, Long... idModulos);

	public List<Map<String, Object>> obterStatusDoUsuarioNosModulosDosGruposIniciadosNoAnoCorrenteComExcessaoDoReuniaoDeIntegracao(Usuario usuario);

	public boolean possuiAlgumAtendimentoNosUltimosDoisAnos(Espera espera);
	
	public Long obterProximoNossoNumero();

	public void aplicarSequence(Long sequence);

	public boolean nuncaParticipouReuniaoIntegracao(Usuario usuario);
	
	public boolean cpfJaExiste(InformacaoEssencial informacaoEssencial);
	
	public boolean cpfCnpjJaExisteDoacaoMaquinaBrailleCartelaDeSelos(InformacaoEssencial informacaoEssencial);
	
	public void resolverPendencia(Grupo pendencia);
	
	public void resolverPendencia(Long idPendencia);
	
	public boolean existeFeriadoOuPonteEmConflito(Agendamento agendamento);
	
	public List<Map<String, Object>> gerarRelatorioFrequenciasDeAtendimentosGlobaisPorUsuarioNoPeriodo(String prontuarios,
			String dataInicio, String dataTermino);
}

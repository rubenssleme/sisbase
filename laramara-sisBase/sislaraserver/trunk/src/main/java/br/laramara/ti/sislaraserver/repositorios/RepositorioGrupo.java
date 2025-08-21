package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public interface RepositorioGrupo {
	public Grupo obterPorId(Long id);

	public List<Grupo> obterInativosPorNome(String nomeGrupo);

	public List<String> obterNomesGrupos(String nomeGrupo);

	public Grupo salvar(Grupo grupo);

	public List<Grupo> obterAtivos();

	public boolean possuiGrupoAtivo(Grupo grupo);
	
	public Grupo obterGrupoPorId(Long id);

	public boolean confirmaVersaoAtualPorIdGrupo(Long id, String versao);

	public boolean confirmaVersaoAtualPorIdModuloPeriodo(Long id, String versao);

	public ModuloPeriodo obterModuloPeriodoPorId(Long id);

	public List<Grupo> obterAtivos(boolean todos, String nomeGrupoETurma,
			boolean exato);

	public List<Grupo> obterInativos(String parametro);

	public List<Grupo> obterGruposAtivosSemAtendimentosEIntegrantes(
			String parametro);

	public Grupo obterGrupoSemAtendimentosEIntegrantesPorId(Long id);

	public List<ModuloPeriodo> obterModulosPeriodosDeGruposAtivosComUsuarioNaoDesligado(Usuario usuario);
	
	public List<ModuloPeriodo> obterModulosPeriodosIrmaos(ModuloPeriodo moduloPeriodo);
	
	public Grupo obterGrupoPorIdModuloPeriodo(Long idModuloPeriodo);
	
	public Grupo obterGrupoPorIdAtendimentoGrupo(Long idAtendimentoGrupo);
	
	public List<ModuloPeriodo> obterModuloPeriodoServicoSocialReuniaoIntegracaoAtivosComDataFuturaEPeriodoCompativel(ModuloPeriodo moduloPeriodo);

	public List<Grupo> obterGruposDescricaoServicoSocialModuloReuniaoDeIntegracaoAtivos();
	
	public List<Grupo> obterGruposAtivosComDescricaoEModulo(DescricaoTipoAtendimento descricaoTipoAtendimento, Modulo modulo);

	public List<Profissional> obterProfissionaisDeModulosPeriodosDeGruposAtivosComUsuarioIntegrado(Usuario usuario);

	public List<Grupo> obterTodosAtivosComModuloAEE();
}

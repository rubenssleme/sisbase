package br.laramara.sistelemarketingserver.dominio;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.laramara.sistelemarketingserver.dominio.seguranca.Validavel;
import br.laramara.sistelemarketingserver.utilitarios.MaquinaTempo;

@MappedSuperclass
public class Historico extends Validavel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "data_inicial_vigencia", nullable = false)
	protected LocalDateTime dataInicialVigencia;

	@Column(name = "data_final_vigencia")
	protected LocalDateTime dataFinalVigencia;
	
	public Historico() {
		dataInicialVigencia = obterDataHoraAtual();
	}
	
	protected LocalDateTime obterDataHoraAtual(){
		return MaquinaTempo.obterDataEHoraAtual();
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public boolean possuiId() {
		return id != null;
	}
	
	public Boolean vigenciaEncerrada() {
		return dataFinalVigencia != null;
	}
	
	public static Historico obterHistoricoAtual(List<? extends Historico> historicos) {
		Historico retorno = null;
		if (historicos != null) {
			for (Historico historico : historicos) {
				if (!historico.vigenciaEncerrada()) {
					retorno = historico;
				}
			}
		}
		return retorno;
	}
	
	public void encerrarVigencia() {
		if (dataFinalVigencia == null){
			dataFinalVigencia = obterDataHoraAtual();
		}
	}
}

select con.nome_empresa, ende.id, ende.cep, ende.endereco from endereco ende
	inner join contribuinte con
		on ende.id = con.id_endereco
	inner join contribuinte_historico_status_contribuinte conhisstacon
		on conhisstacon.id_contribuinte = con.id
	inner join historico_status_contribuinte hisstacon
		on conhisstacon.id_historico_status_contribuinte = hisstacon.id
where ende.id_municipio is null and hisstacon.status = 'ATIVADO';
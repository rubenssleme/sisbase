select ende.cep, ende.endereco, bai.bairro, ende.id_cidade, cid.uf from tend_endereco ende 
	inner join tend_cidade cid 
		on ende.id_cidade = cid.id_cidade
	inner join tend_bairro bai
		on ende.id_bairro = bai.id_bairro
where ende.cep = '01151000'
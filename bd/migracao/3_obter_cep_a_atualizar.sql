CREATE EXTENSION dblink;
select 'update endereco set id_municipio='||mun.id||',uf='''||mun.uf||''' where id = '||ende.id||';' from endereco ende 
	inner join (	SELECT *
			FROM dblink('host=localhost user=postgres password=paulo12 dbname=cep','select ende.cep, cid.cidade, cid.uf from tend_endereco ende 
				inner join tend_cidade cid 
					on ende.id_cidade = cid.id_cidade
				   ') AS (cep CHARACTER VARYING, cidade CHARACTER VARYING, uf CHARACTER VARYING)
		   ) aux 
		on ende.cep = aux.cep
	inner join municipio mun
		on mun.nome = aux.cidade
where ende.id >= 20168 and ende.id_municipio is null --Mudar na producao	
order by ende.id			     
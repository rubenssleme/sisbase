update logradouro set cep = lpad(cast(cep as varchar),8,'0') where cep in (
	select cep from logradouro
	where length(cep) = 7
);
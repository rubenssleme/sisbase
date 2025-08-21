update endereco set cep = lpad(cep, 8, '0') 
where cep not like '' and length(cep)<=7 and id >= 20168;
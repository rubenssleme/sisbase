update informacao_atendimento infate
	set frequencia = 'FP'
from atendimento_usuario ateusu, atendimento_grupo_atendimento_usuario ategruateusu
where ateusu.id = ategruateusu.id_atendimento_usuario and ateusu.id_informacao_atendimento = infate.id and ategruateusu.id_atendimento_grupo in (
select 
--nom.nome, gru.turma, ategru.data, 
ategru.id
	from atendimento_grupo_atendimento_profissional ategruatepro
	inner join atendimento_profissional atepro
		on ategruatepro.id_atendimento_profissional = atepro.id
	inner join informacao_atendimento infatepro
		on infatepro.id = atepro.id_informacao_atendimento
	inner join atendimento_grupo ategru
		on ategru.id = ategruatepro.id_atendimento_grupo
	inner join modulo_periodo_atendimento_grupo ategrumodper
		on ategrumodper.id_atendimento_grupo = ategru.id
	inner join grupo_modulo_periodo grumodper
		on grumodper.id_modulo_periodo = ategrumodper.id_modulo_periodo
	inner join grupo gru
		on gru.id = grumodper.id_grupo
	inner join nome_grupo nom
		on nom.id = gru.id_nome_grupo
where gru.ativo = true and ategru.status ='NORMAL'
group by nom.nome, gru.turma, ategru.data, ategru.id
having count(infatepro.id) = 1 and ategru.id in (
	select ategru2.id 
	from atendimento_grupo_atendimento_profissional ategruatepro2
	inner join atendimento_profissional atepro2
		on ategruatepro2.id_atendimento_profissional = atepro2.id
	inner join informacao_atendimento infatepro2
		on infatepro2.id = atepro2.id_informacao_atendimento
	inner join atendimento_grupo ategru2
		on ategru2.id = ategruatepro2.id_atendimento_grupo
	where infatepro2.frequencia = 'FP'
)
order by nom.nome, gru.turma, ategru.data
);



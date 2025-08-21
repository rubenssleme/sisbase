insert into informacao_atendimento(frequencia) values ('AT');
insert into atendimento_usuario(id_usuario, id_informacao_atendimento)values(8619, currval('informacao_atendimento_id_seq'));
insert into atendimento_grupo_atendimento_usuario(
	select distinct(ategruateusu.id_atendimento_grupo), currval('atendimento_usuario_id_seq') as id_atendimento_usuario from grupo gru
		inner join nome_grupo nom
			on nom.id = gru.id_nome_grupo
		inner join grupo_modulo_periodo grumodper
			on gru.id = grumodper.id_grupo
		inner join modulo_periodo modper 
			on grumodper.id_modulo_periodo = modper.id
		inner join modulo mod 
			on modper.id_modulo = mod.id
		inner join modulo_periodo_atendimento_grupo modperaten 
			on modper.id = modperaten.id_modulo_periodo
		inner join atendimento_grupo ate 
			on ate.id = modperaten.id_atendimento_grupo
		inner join atendimento_grupo_atendimento_usuario ategruateusu
			on ate.id = ategruateusu.id_atendimento_grupo
	where gru.ativo = true and excluido = false and nom.nome = 'G07' and gru.turma = '05' and mod.nome = 'Atendimento Específico Especializado' and ate.data in ('04/09/2015'));
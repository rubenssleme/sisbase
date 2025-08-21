update pendencia set resolvida = true where id in (
	select id from pendencia 
	where tipo in ('TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO', 'INCLUSAO_DE_REUNIAO_DE_INTEGRACAO') and resolvida is false and prontuario in (10375, 4447, 7316, 11382, 9075, 5885, 8671, 6939, 11103, 10270, 9100, 10076, 8941, 10350, 10122)
);

update modulo_usuario set ignorar_regra_reuniao_de_integracao = true where id in (
SELECT
     modusu.id as id_modulo_usuario
/*
     gru.id as id_grupo,
     gru.descricao as obs,
     nomgru.nome AS nomegrupo,
     gru.turma AS turma,
     gru.data_inicio AS data_inicio_grupo,
     gru.data_termino as data_termino_grupo,
     tipoaten.nome AS tipo,
     desctipoaten.nome as descricao,
     mod.nome AS modulo,
     gru.ativo as ativo,
     modusu.status AS status,
     infess.nome as nome */
FROM grupo gru
     INNER JOIN grupo_modulo_periodo grumodper ON gru.id = grumodper.id_grupo
     INNER JOIN modulo_periodo modper ON grumodper.id_modulo_periodo = modper.id
     INNER JOIN modulo_periodo_modulo_usuario modpermodusu ON modpermodusu.id_modulo_periodo = modper.id
     INNER JOIN modulo_usuario modusu ON modusu.id = modpermodusu.id_modulo_usuario
     INNER JOIN usuario usu on usu.id = modusu.id_usuario
     INNER JOIN modulo mod ON modper.id_modulo = mod.id
     INNER JOIN nome_grupo nomgru ON gru.id_nome_grupo = nomgru.id
     INNER JOIN descricao_tipo_atendimento desctipoaten ON gru.id_descricao_tipo_atendimento = desctipoaten.id
     INNER JOIN tipo_atendimento tipoaten ON desctipoaten.id_tipo_atendimento = tipoaten.id
     INNER JOIN informacao_essencial infess ON usu.id_informacao_essencial = infess.id
WHERE
     usu.id in (10375, 4447, 7316, 11382, 9075, 5885, 8671, 6939, 11103, 10270, 9100, 10076, 8941, 10350, 10122) and gru.ativo = true and modusu.ignorar_regra_reuniao_de_integracao is false and modusu.status = 'INTEGRADO' and gru.data_inicio between '01/01/2018' and '31/12/2018');

update modulo_usuario set status = 'REMOVIDO', obs='erro de integração.' where id in (
SELECT
     modusu.id as id_modulo_usuario
     /*gru.id as id_grupo,
     gru.descricao as obs,
     nomgru.nome AS nomegrupo,
     gru.turma AS turma,
     gru.data_inicio AS data_inicio_grupo,
     gru.data_termino as data_termino_grupo,
     tipoaten.nome AS tipo,
     desctipoaten.nome as descricao,
     mod.nome AS modulo,
     gru.ativo as ativo,
     modusu.status AS status,
     infess.nome as nome*/
FROM grupo gru
     INNER JOIN grupo_modulo_periodo grumodper ON gru.id = grumodper.id_grupo
     INNER JOIN modulo_periodo modper ON grumodper.id_modulo_periodo = modper.id
     INNER JOIN modulo_periodo_modulo_usuario modpermodusu ON modpermodusu.id_modulo_periodo = modper.id
     INNER JOIN modulo_usuario modusu ON modusu.id = modpermodusu.id_modulo_usuario
     INNER JOIN usuario usu on usu.id = modusu.id_usuario
     INNER JOIN modulo mod ON modper.id_modulo = mod.id
     INNER JOIN nome_grupo nomgru ON gru.id_nome_grupo = nomgru.id
     INNER JOIN descricao_tipo_atendimento desctipoaten ON gru.id_descricao_tipo_atendimento = desctipoaten.id
     INNER JOIN tipo_atendimento tipoaten ON desctipoaten.id_tipo_atendimento = tipoaten.id
     INNER JOIN informacao_essencial infess ON usu.id_informacao_essencial = infess.id
WHERE
     usu.id in (10375, 4447, 7316, 11382, 9075, 5885, 8671, 6939, 11103, 10270, 9100, 10076, 8941, 10350, 10122) and nomgru.nome like 'SS%' and gru.ativo = true and modusu.status = 'INTEGRADO' and gru.data_inicio between '01/01/2018' and '31/12/2018');


delete from atendimento_grupo_atendimento_usuario where id_atendimento_usuario in (
 SELECT
     ateusu.id as id_atendimento_usuario
     /*ate.id as id_atendimento,
     gru.id as id_grupo,
     nomgru.nome AS nomegrupo,
     gru.turma AS turma,
     gru.data_inicio AS data_inicio_grupo,
     mod.nome AS modulo,
     usu.id AS prontuario,
     infess.nome AS nomeusuario,
     ate.data AS data,
     ate.total_horas AS totalhoras,
     infoaten.frequencia AS frequencia,
     tipoaten.nome AS tipoatendimento,
     infoaten.justificativa AS justificativa,
     ate.descricao as descricaoatendimento,
     ate.interdisciplinar as interatencimento,
     infoaten.descricao AS descricaoatendimentoindividual,
     pro.nome as nome_profissional*/
FROM
     atendimento_grupo ate INNER JOIN atendimento_grupo_atendimento_usuario ateateusu ON ate.id = ateateusu.id_atendimento_grupo
     INNER JOIN atendimento_usuario ateusu ON ateateusu.id_atendimento_usuario = ateusu.id
     INNER JOIN usuario usu ON ateusu.id_usuario = usu.id
     INNER JOIN informacao_atendimento infoaten ON ateusu.id_informacao_atendimento = infoaten.id
     INNER JOIN informacao_essencial infess ON usu.id_informacao_essencial = infess.id
     INNER JOIN modulo_periodo_atendimento_grupo modperaten ON ate.id = modperaten.id_atendimento_grupo
     INNER JOIN modulo_periodo modper ON modperaten.id_modulo_periodo = modper.id
     INNER JOIN modulo mod ON modper.id_modulo = mod.id
     INNER JOIN grupo_modulo_periodo grumodper ON modper.id = grumodper.id_modulo_periodo
     INNER JOIN grupo gru ON grumodper.id_grupo = gru.id
     INNER JOIN nome_grupo nomgru ON gru.id_nome_grupo = nomgru.id
     INNER JOIN descricao_tipo_atendimento desctipoaten ON gru.id_descricao_tipo_atendimento = desctipoaten.id
     INNER JOIN tipo_atendimento tipoaten ON desctipoaten.id_tipo_atendimento = tipoaten.id
     inner join atendimento_grupo_atendimento_profissional ategruatepro on ategruatepro.id_atendimento_grupo = ate.id
     inner join atendimento_profissional atepro on atepro.id = ategruatepro.id_atendimento_profissional
     inner join informacao_atendimento infatepro on infatepro.id = atepro.id_informacao_atendimento
     inner join profissional pro on pro.id = atepro.id_profissional
WHERE
     usu.id in (10375, 4447, 7316, 11382, 9075, 5885, 8671, 6939, 11103, 10270, 9100, 10076, 8941, 10350, 10122) AND ate.status='NORMAL' and nomgru.nome like 'SS%' and gru.ativo = true and gru.data_inicio between '01/01/2018' and '31/12/2018');

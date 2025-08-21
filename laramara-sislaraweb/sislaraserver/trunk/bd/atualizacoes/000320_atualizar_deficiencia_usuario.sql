update deficiencia set descricao = 'Sem deficiência visual', etiologia_obrigatorio = false where id = 11;

delete from usuario_periodo_deficiencia where id_periodo_deficiencia in (1758
,1690
,157
,1783
,1708
,166
,1425
,2513
,1977);

delete from periodo_deficiencia where id in (1758
,1690
,157
,1783
,1708
,166
,1425
,2513
,1977);

delete from deficiencia where id = 12;

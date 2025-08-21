update espera set id_modulo = 130 
where id in (
9941,
9954,
9955,
9967,
9972,
9982,
9997,
10006,
10011,
10028,
10051,
10072,
10101,
10114,
10126,
10127,
10144,
10147,
10156,
10181,
10187,
10239,
10254,
10263,
10269,
10415,
10496,
10504,
10529,
10661,
10736,
11039);

update historico_status_espera set status = 'TRIANDO' where id in( 
	select hisesp.id from espera esp 
		inner join espera_historico_status_espera esphisstatesp 
			on esphisstatesp.id_espera = esp.id
		inner join historico_status_espera hisesp
			on esphisstatesp.id_historico_status_espera = hisesp.id
	where esp.id in (
9941,
9954,
9955,
9967,
9972,
9982,
9997,
10006,
10011,
10028,
10051,
10072,
10101,
10114,
10126,
10127,
10144,
10147,
10156,
10181,
10187,
10239,
10254,
10263,
10269,
10415,
10496,
10504,
10529,
10661,
10736,
11039)
)



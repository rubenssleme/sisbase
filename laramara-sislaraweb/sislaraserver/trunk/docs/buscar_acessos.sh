#!/bin/bash
echo "Pesquisando bloqueios do Usuário..."
grep -E '. 9971|rio=9971' -A 2 -B 2 sislaraserver.log sislaraserver.log.2013-07 sislaraserver.log.2013-06 sislaraserver.log.2013-05 sislaraserver.log.2013-04 sislaraserver.log.2013-03 sislaraserver.log.2013-02 sislaraserver.log.2013-01 > resultado_usuario.txt
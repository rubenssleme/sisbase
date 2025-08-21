for %%f in (dir *.sql) do (
	psql -U postgres -d sislara -f %%f
)

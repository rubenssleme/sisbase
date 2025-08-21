ALTER TABLE PROFISSIONAL ADD COLUMN PROFISSIONAL BOOLEAN DEFAULT 'false' NOT NULL;
update profissional set profissional = 'true'; 
update profissional set profissional = 'false' where id in (85, 84, 86, 83);	
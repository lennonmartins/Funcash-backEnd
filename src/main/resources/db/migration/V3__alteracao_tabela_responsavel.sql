sp_rename 'dbo.responsavel.data_de_nascimento_responsavel', 'data_de_nascimento', 'column';

alter table dbo.responsavel
alter column genero varchar(20);
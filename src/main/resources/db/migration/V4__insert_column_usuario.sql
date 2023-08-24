alter table dbo.usuario
add crianca_id numeric(19,0)

alter table dbo.usuario
add constraint usuario_crianca_fk foreign key (crianca_id) references dbo.crianca (id);


Begin transaction;
Begin try
    insert into usuario(email, senha, crianca_id)
    select c.email, c.senha, c.id
    From Crianca c;
    commit
end try
begin catch
    rollback
end catch;
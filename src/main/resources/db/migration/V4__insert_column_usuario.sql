alter table dbo.usuario
add crianca_id numeric(19,0)

alter table dbo.usuario
add constraint usuario_crianca_fk foreign key (crianca_id) references dbo.crianca (id);
create table if not exists paciente(id int auto_increment primary key, nome varchar(255), sobrenome varchar(255), rg varchar(255), data varchar(255), endereco int);
create table if not exists endereco(id int auto_increment primary key, rua varchar(255), numero varchar(255), cidade varchar(255), bairro varchar(255));

DROP TABLE IF EXISTS endereco;
CREATE TABLE endereco (
    id int auto_increment primary key,
    rua varchar(255),
    numero varchar(10),
    bairro varchar(90),
    cidade varchar(90)
)

DROP TABLE IF EXISTS paciente;
CREATE TABLE paciente (
    id int auto_increment primary key,
    nome varchar(100),
    sobrenome varchar(100),
    rg varchar(20),
    data_cadastro date,
    endereco int
    FOREIGN KEY (endereco) REFERENCES endereco(id);
)

CREATE TABLE IF NOT EXISTS filial (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nomeFilial VARCHAR(255),
    rua VARCHAR(255),
    numero VARCHAR(30),
    cidade VARCHAR(100),
    estado VARCHAR(2),
    eCincoEstrelas BIT
)

# Clínica Odontológica

Esta aplicação é uma Restful API desenvolvida no ecossistema Spring para simular um
sistema BackEnd de cadastro de Pacientes, Dentistas e Consultas. Sendo possível
criar, atualizar, buscar e deletar (CRUD) dos elementos citados. A aplicação faz
parte do Projeto Integrador da disciplina BackEnd I do curso CTD pela Digital House.

## Instalação

Clone o repositório via HTTP, SSH ou CLI

```bash
    git clone https://github.com/DH-BE-G6/ClinicaOdontologica.git

    git clone git@github.com:DH-BE-G6/ClinicaOdontologica.git

    gh repo clone DH-BE-G6/ClinicaOdontologica
```

Abra a pasta clonada dentro de qualquer IDE Java (preferencialmente IntellijIDEA)

```bash
    <path>/ClinicaOdontologica
```

Deixe que o IntellijIDEA (ou IDE Alternativa) baixe os plugins, libs, utilitários
e SDK automaticamente.

Execute a aplicação na classe **ClinicaOdontologicaG6Application.java** :
```bash
    src
    |_main
        |_java
            |_com
                |_digitalhouse
                    |_clinicaodontologicag6
                        |_ClinicaOdontologicaG6Application.java
```
Realize o CRUD através do POSTMAN ou, se preferir, de uma View customizada no Front End.


## Stack e Ferramentas utilizadas no Projeto

**Linguagem:** Java

**Framework:** Spring Boot( Web, JPA, H2, Security )

**Banco de Dados:** H2 Database

**Bibliotecas e Utilitários:** Maven, JSON Web Token (jwt), JUnit5, Lombok, Postman,

**IDE:** IntellijIDEA

## Requisições e Autenticação

### Permissões necessária para Requisição
Antes de realizar quaisquer requisições, é necessário realizar uma autenticação através
do JWT e do Spring Security, definindo um `username` e um `password`.<br> 
Dependendo do nível de permissão definido, você terá acesso às requisições.
Para entender melhor, siga a orientação das tabelas abaixo para cada clase e requisição.
Os itens necessários para realizar cada requisição pode ser localizado nas classes **Entitiy**
do projeto.

#### Paciente

| Requisição | URL     | Nível de Permissão (Role)           | 
| :------    | :--------- | :------------------------------  |
| GET  | `/paciente/buscar`   | **ADMIN** |
| GET ALL | `/paciente/listar`   | **ADMIN** |
| POST  |`/paciente/cadastrar`   | **ADMIN** |
| POST(Auth)  |`/paciente/auth`   | **ADMIN ou USER** |
| PUT  | `/paciente/atualizar`   | **ADMIN OU USER** |
| DELETE  | `/paciente/excluir`   | **ADMIN** |

#### Dentista

| Requisição | URL     | Nível de Permissão (Role)           |
| :------    | :--------- | :--------------------------------|
| GET  | `/dentista/buscar`   | **ADMIN** |
| GET ALL | `/dentista/listar`   | **ADMIN** |
| POST  |`/dentista/cadastrar`   | **ADMIN** |
| POST(Auth)  |`/dentista/auth`   | **ADMIN ou USER** |
| PUT  | `/dentista/atualizar`   | **ADMIN OU USER** |
| DELETE  | `/dentista/excluir`   | **ADMIN** |

#### Consulta

| Requisição | URL     | Nível de Permissão (Role)           |
| :------    | :--------- | :--------------------------------|
| GET  | `/consulta/buscar`   | **ADMIN** |
| GET ALL | `/consulta/listar`   | **ADMIN** |
| POST  |`/consulta/cadastrar`   | **ADMIN OU USER** |
| PUT  | `/consulta/atualizar`   | **ADMIN** |
| DELETE  | `/consulta/excluir`   | **ADMIN** |


## Integrantes

![Carlos de Lima Junior](https://github.com/CJBiohacker) <br>
![Matheus Rayol](https://github.com/matheusrayol) <br>
![Wallacy ](https://github.com/wallacy-1) <br>
![Marta Pederiva](https://github.com/mpederiva) <br>
![Joanderson Felipe](https://github.com/jfelipedev) <br>
![Eduardo Russo](https://github.com/ESRusso) <br>

# Challenge-LiterAlura
Projeto obrigatório de um curso. 

LiterAlura é uma aplicação Java desenvolvida com Spring Boot que permite consultar livros da API Gutendex e armazenar informações relevantes em um banco de dados PostgreSQL. O sistema permite que o usuário busque livros pelo título, armazene os resultados localmente e realize consultas sobre os dados salvos.

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Jackson (processamento de JSON)

## Funcionalidades

A aplicação oferece um menu interativo no terminal com as seguintes opções:

1. Buscar livro por título
   Consulta a API Gutendex, obtém o primeiro resultado encontrado e salva o livro e seu autor no banco de dados.
2. Listar livros registrados
   Exibe todos os livros previamente armazenados no banco de dados.
3. Listar autores
   Mostra todos os autores associados aos livros registrados.
4. Listar autores vivos em determinado ano
   Permite informar um ano e retorna os autores que estavam vivos nesse período.
5. Listar livros por idioma
   Filtra os livros armazenados de acordo com o idioma informado.

## Funcionamento

A aplicação realiza consultas na API Gutendex apenas quando o usuário solicita uma busca. Os dados retornados são convertidos de JSON para objetos Java utilizando a biblioteca Jackson. Após a conversão, os dados são persistidos no banco de dados PostgreSQL através do Spring Data JPA. O banco de dados armazena apenas os livros pesquisados pelo usuário, não sendo feita a importação completa da base da API.

## Configuração do Banco de Dados

No arquivo `application.properties`, configure a conexão com o PostgreSQL:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Execução

1. Criar o banco de dados `literalura` no PostgreSQL.
2. Configurar usuário e senha no arquivo `application.properties`.
3. Executar a aplicação pela classe `LiterAluraApplication`.
4. Utilizar o menu no terminal para realizar consultas e armazenar livros.

<p align="right">
  <sub>Realizado usando VS Code.</sub>
</p>

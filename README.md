# spring-java

## Agregador de Investimentos API  

  O presente projeto é uma API REST desenvolvida para atuar como o núcleo de um sistema agregador de investimentos. O objetivo principal é permitir que usuários gerenciem suas carteiras, contas de investimento e           acompanhem a performance de ativos (stocks) de forma centralizada.
  
  A aplicação resolve o problema da fragmentação de dados financeiros, oferecendo uma interface padronizada para operações de CRUD (Create, Read, Update, Delete) de usuários e, futuramente, integração com APIs de cotações em tempo real.

## Tecnologias Utilizadas

  Java 21: Utilização das últimas features da linguagem, como Records para DTOs, garantindo imutabilidade e código conciso.
  
  Spring Boot: Framework base que acelera o desenvolvimento através da configuração automática e servidor embutido.
  
  Spring Web: Utilizado para a criação dos endpoints RESTful e gerenciamento de requisições HTTP.
  
  Spring Data JPA: Abstração sobre o Hibernate para facilitar a persistência de dados e consultas ao banco sem a necessidade de SQL.
  
  MySQL e Hibernate: Banco de dados relacional para persistência, com o Hibernate atuando como ferramenta de ORM (Object-Relational Mapping).
  
  Docker: Containerização do banco de dados MySQL para garantir um ambiente de desenvolvimento idêntico em qualquer máquina.
  
  Maven: Gerenciador de dependências e automação de build.

## Arquitetura do projeto

  controller -> Porta de entrada: recebe requests HTTP e retorna respostas.
  service -> Camada de Negócio: contém a lógica e regras da aplicação.
  repository -> Camada de Dados: interfaces que comunicam com o banco (JPA).
  entity -> Modelos de Dados: representação das tabelas do banco.
  dto -> Objetos de Transferência: definem o contrato da API.

## Fluxo da aplicação

## Injeção de Dependência

  O projeto utiliza o conceito de Inversão de Controle através da Injeção de Dependência do Spring. Em vez de a classe criar suas próprias instâncias (usando new), o Spring gerencia o ciclo de vida dos objetos.

  Por que usar? Principalmente por desacoplamento, as classes dependem de abstrações, não de implementações concretas.
  Exemplo de uso:

  ```java
// Exemplo de Injeção via Construtor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

## DTO 

  Implementamos DTOs usando Java Records para garantir que a API não exponha diretamente as entidades do banco de dados.

  Por que usar? Principalmente por segurança, o uso de DTO evita que campos como senha sejam expostos ou alterados indevidamente.
  Exemplo de uso:

```java
//DTO para criação de usuário
public record CreateUserDto(String username, String email, String password) {}
```
## Requisições para teste:

### Criar usuário (POST)
http://localhost:8080/v1/users
  ```json
{
  "username": "Davison",
  "email": "davison@exemplo.com",
  "password": "123"
}
```
### Listar Usuários (GET)
http://localhost:8080/v1/users

### Buscar Usuário por ID (GET)
http://localhost:8080/v1/users/{userId}

### Atualizar Usuário (PUT)
http://localhost:8080/v1/users/{userId}
  ```json
{
  "username": "Dava",
  "password": "1234"
}
```

### Criar conta (POST)
http://localhost:8080/v1/users/{userId}/accounts
  ```json
{
 "description": "conta de investimentos",
  "street": "myStreet",
	"number": 500 
}
```
### Listar conta (GET)
http://localhost:8080/v1/users/{userId}/accounts

### Criar ação/stock (POST)
http://localhost:8080/v1/stocks
  ```json
{
 "stockId": "PETR4",
  "description": "Petobras"
}
```

### Associar stock com conta (POST)
http://localhost:8080/v1/users/{accountId}/stocks
  ```json
{ 
 "stockId": "PETR4",
  "description": "Petobras"
}
```

### Verificar stocks de determinada account (GET)
http://localhost:8080/v1/accounts/{accountId}/stocks

Obs: O último get faz requisição à API da Brapi para utilizar o regularMarketPrice, então, se faz necessário criar uma conta no site,
gerar uma key e criar no projeto uma variável de ambiente no modelo: TOKEN -> key

desta forma, quando testar, receberá como resposta um json como este:

  ```json
{ 
	"stockId": "PETR4",
	"quantity": 2,
	"total": 98.16
}
```

## DEPENDENCIAS DO PROJETO (start.spring.io)
  spring web  
  spring data jpa  
  mysql driver  

## Iniciando mysql localmente via Docker compose
  Utilizado exatamente os exemplos do spring.io para utilizar o docker, segue o link:  
  https://spring.io/guides/gs/accessing-data-mysql/  
  https://github.com/spring-guides/gs-accessing-data-mysql/blob/main/complete/compose.yaml  

  criado novo arquivo no projeto nomeado docker-composer.yml e colado o cod abaixo presente no link conforme ex:  
  
 ```yaml
services:
  mysql:
    image: 'mysql:8.4.7'
    environment:
      - 'MYSQL_DATABASE=mydatabase'
      - 'MYSQL_PASSWORD=password'
      - 'MYSQL_ROOT_PASSWORD=password'
      - 'MYSQL_USER=myuser'
    ports:
      - '3306:3306'
 ```

aberto terminal e utilizado comando **`Docker-compose up`** para subir o mysql 

## Configurando Mysql
  Conforme o exemplo do spring.io, em **`src/main/sources/aplication.properties`** cola o seguinte:  
  
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/mydatabase
spring.datasource.username=myuser
spring.datasource.password=secret
```
obs: precisa conforme o arquivo docker-compose  

## Teste
run no arquivo em /src/java/arquivo  
vai sinalizar a situação no terminal

## Diagrama ER da API
<div align="center">
  <img src="https://private-user-images.githubusercontent.com/93564378/577703738-a3e272b7-a479-4d99-b9fe-70e0287c52fd.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NzYxMjY4MjksIm5iZiI6MTc3NjEyNjUyOSwicGF0aCI6Ii85MzU2NDM3OC81Nzc3MDM3MzgtYTNlMjcyYjctYTQ3OS00ZDk5LWI5ZmUtNzBlMDI4N2M1MmZkLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNjA0MTQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjYwNDE0VDAwMjg0OVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTJlNDMxYzM2YjQwNTM1NGQ1NDNhMDQ0NjY4YjUxYWU3OThjYjkxNjdkODVjYmQ3MWMwMjc4N2E4NGI4ZTE4YmUmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JnJlc3BvbnNlLWNvbnRlbnQtdHlwZT1pbWFnZSUyRnBuZyJ9.jejsu0gqn86Y70qhPTqPcFUeWnPu1VZlnkV1ZLBRqsA" width="700px" />
</div>

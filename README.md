# spring-java

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

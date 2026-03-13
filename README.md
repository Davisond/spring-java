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




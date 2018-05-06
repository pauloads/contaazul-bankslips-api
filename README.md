# Bank slips API

API REST para geração de boletos

## Requisitos

- Java 8+
- Maven

## Como executar

### Maven
Após clonar o repositório, vá até a pasta raiz e digite
```
mvn spring-boot:run
```

### JAR executável
```
mvn package
java -jar target/bankslips-api-0.0.1-SNAPSHOT.jar
```

## Endpoints

### Criar boleto

Endpoint:​ POST http://localhost:8080/rest/bankslips

Esse método deve receber um novo boleto e inseri-lo em um banco de dados para
ser consumido pela própria API. Todos os campos são obrigatórios.

```
{
	"due_date":"2018-01-01",
	"total_in_cents":"100000",
	"customer":"Trillian Company",
	"status":"PENDING"
}
```
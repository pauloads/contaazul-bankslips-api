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

![](https://github.com/pauloads/contaazul-bankslips-api/blob/dev/readme_files/create.PNG?raw=true)

#### Mensagens de resposta

- 201 : Bankslip created
- 400 : Bankslip not provided in the request body
- 422 : Invalid bankslip provided.The possible reasons are:
	- A field of the provided bankslip was null or with invalid values

### Listar boletos

Endpoint: ​GET http://localhost:8080/rest/bankslips/

Esse método da API deve retornar uma lista de boletos em formato JSON.

```
[
	{
		"id":"84e8adbf-1a14-403b-ad73-d78ae19b59bf",
		"due_date":"2018-01-01",
		"total_in_cents":"100000",
		"customer":"Ford Prefect Company"
	},
	{
		"id":"c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89",
		"due_date":"2018-02-01",
		"total_in_cents":"200000",
		"customer":"Zaphod Company"
	}
]
```

#### Mensagens de resposta

- 200 : Ok

### Ver detalhes de um boleto

Endpoint: ​GET http://localhost:8080/rest/bankslips/{id}

Esse método da API deve retornar um boleto filtrado pelo id, caso o boleto estiver
atrasado deve ser calculado o valor da multa.

Regra para o cálculo da multa aplicada por dia para os boletos atrasados:

- Até 10 dias: Multa de 0,5% (Juros Simples)
- Acima de 10 dias: Multa de 1% (Juros Simples)

```
{
	"id":"c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89",
	"due_date":"2018-01-01",
	"total_in_cents":"100000",
	"customer":"Ford Prefect Company",
	"fine":"1000",
	"status":"PENDING"
}
```

#### Mensagens de resposta

- 200 : Ok
- 400 : Invalid id provided - it must be a valid UUID
- 404 : Bankslip not found with the specified id

### Pagar um boleto

Endpoint:​ PUT http://localhost:8080/rest/bankslips/{id}/pay

Esse método da API deve alterar o status do boleto para PAID de acordo com o id.

```
{
	"id":"c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89"
}
```

#### Mensagens de resposta

- 200 : Bankslip paid
- 404 : Bankslip not found with the specified id

### Cancelar um boleto

Endpoint:​ DELETE http://localhost:8080/rest/bankslips/{id}/cancel

Esse método da API deve alterar o status do boleto para CANCELED de acordo
com o id.

```
{
	"id":"c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89"
}
```

#### Mensagens de resposta
- 200 : Bankslip canceled
- 404 : Bankslip not found with the specified id

## Dúvidas e comentários: paulocorrea.ads@gmail.com
# transfer

## Inserção de agendamento de transferências financeiras

## Parametros Consulta

- Endpoint: http://localhost:8080/cvc/transfers?page=0&size=20
- page = Pagina atual. Default = 0
- size = Tamanho da página a ser buscada no banco. Default = 20
```shell
curl -X GET "http://localhost:8080/cvc/transfers?page=0&size=20" -H  "accept: application/json"

```
## Post (Salvar agendamento)

### Request
```shell
curl -X POST "http://localhost:8080/cvc/transfers" 
-H  "accept: application/json" 
-H  "Content-Type: application/json" 
-d "{\"amount\":100000,\"transferAt\":\"2022-02-10\",\"accountOrigin\":\"1234\",\"accountDestination\":\"2345\"}"
```
### Response
```shell
{
  "id": 1,
  "amount": 100000,
  "feeAmount": 4000,
  "type": "C",
  "createdAt": "2022-01-10",
  "transferAt": "2022-02-10",
  "accountOrigin": "1234",
  "accountDestination": "2345"
}
```

## Swagger

- http://localhost:8080/cvc/swagger-ui


Requisitos:
- Persistência de dados em memória => Banco H2
- Código em última versão do Java => 11
- Código com ferramenta de build => Gradle
- Casos de testes => Ok
- Testes unitários => Ok
- Sem arquivos de IDE => .gitignore atualizado
- Código em inglês => Ok

Recomendações:
- Utilizado Spring Boot em sua última versão com arquitetura clean architecture separada em camadas baseada em suas responsabilidades
- Para executar o projeto Executar a classe TransferApplication.
- Regras de negócio descritas foram aplicadas.
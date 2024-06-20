# Micronaut Project

Este é um projeto desenvolvido usando Micronaut, Java, JPA, Hibernate e Lombok. O objetivo deste projeto é criar um sistema de gerenciamento de problemas, casos de teste e submissões de soluções.

## Endpoints da API

### Problemas

#### Criar Problema

**URL:** /activity  
**Método:** POST  
**Request Body:**

```json
{
  "code": "ZERINHO_OU_UM",
  "name": "Zerinho ou Um",
  "description": "Jogo de escolha de 0 ou 1."
}
```

**Resposta:**

```json
{
  "code": "ZERINHO_OU_UM",
  "name": "Zerinho ou Um",
  "description": "Jogo de escolha de 0 ou 1."
}
```

#### Listar Problemas

**URL:** /activity  
**Método:** GET  
**Resposta:**

```json
[
  {
    "code": "ZERINHO_OU_UM",
    "name": "Zerinho ou Um",
    "description": "Jogo de escolha de 0 ou 1."
  }
]
```

### Casos de Teste

#### Criar Caso de Teste

**URL:** /tc  
**Método:** POST  
**Request Body:**

```json
{
  "problemCode": "ZERINHO_OU_UM",
  "inputFileName": "zerinho_ou_um_input_1.txt",
  "expectedOutputFileName": "zerinho_ou_um_output_1.txt"
}
```

**Resposta:**

```json
{
  "problemCode": "ZERINHO_OU_UM",
  "inputFileName": "zerinho_ou_um_input_1.txt",
  "expectedOutputFileName": "zerinho_ou_um_output_1.txt"
}
```

#### Listar Casos de Teste

**URL:** /tc/{problemCode}  
**Método:** GET  
**Resposta:**

```json
[
  {
    "problemCode": "ZERINHO_OU_UM",
    "inputFileName": "zerinho_ou_um_input_1.txt",
    "expectedOutputFileName": "zerinho_ou_um_output_1.txt"
  }
]
```

### Soluções

#### Submeter Solução

**URL:** /activity/solution  
**Método:** POST  
**Request Body (multipart/form-data):**

- file: Arquivo de código fonte (ex: ZerinhoOuUm.java)
- author: Autor da solução
- problemCode: Código do problema (ex: ZERINHO_OU_UM)

**Resposta:**

```json
{
  "id": 1,
  "author": "autor",
  "problemCode": "ZERINHO_OU_UM",
  "filename": "ZerinhoOuUm.java",
  "sourceCode": "public class ZerinhoOuUm { ... }",
  "createdAt": "2024-06-20T14:00:00",
  "status": "SUCCESS"
}
```

#### Listar Soluções

**URL:** /activity/solution/{problemCode}  
**Método:** GET  
**Resposta:**

```json
[
  {
    "id": 1,
    "author": "autor",
    "problemCode": "ZERINHO_OU_UM",
    "filename": "ZerinhoOuUm.java",
    "sourceCode": "public class ZerinhoOuUm { ... }",
    "createdAt": "2024-06-20T14:00:00",
    "status": "SUCCESS"
  }
]
```

## Testando a API

Você pode testar a API usando cURL ou uma ferramenta de teste de API como Postman ou Insomnia.

### Exemplos de Testes Usando cURL

#### Criar Problema

```bash
curl -X POST http://localhost:8080/activity \
-H "Content-Type: application/json" \
-d '{
  "code": "ZERINHO_OU_UM",
  "name": "Zerinho ou Um",
  "description": "Jogo de escolha de 0 ou 1."
}'
```

#### Listar Problemas

```bash
curl -X GET http://localhost:8080/activity
```

#### Criar Caso de Teste

```bash
curl -X POST http://localhost:8080/tc \
-H "Content-Type: application/json" \
-d '{
  "problemCode": "ZERINHO_OU_UM",
  "inputFileName": "zerinho_ou_um_input_1.txt",
  "expectedOutputFileName": "zerinho_ou_um_output_1.txt"
}'
```

#### Listar Casos de Teste

```bash
curl -X GET http://localhost:8080/tc/ZERINHO_OU_UM
```

#### Submeter Solução

```bash
curl -X POST http://localhost:8080/activity/solution \
-H "Content-Type: multipart/form-data" \
-F "file=@/caminho/para/ZerinhoOuUm.java" \
-F "author=autor" \
-F "problemCode=ZERINHO_OU_UM"
```

#### Listar Soluções

```bash
curl -X GET http://localhost:8080/activity/solution/ZERINHO_OU_UM
```


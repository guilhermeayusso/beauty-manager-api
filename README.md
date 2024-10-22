

[![codecov](https://codecov.io/gh/guilhermeayusso/beauty-manager-api/branch/main/graph/badge.svg)](https://codecov.io/gh/guilhermeayusso/beauty-manager-api)


# Beauty Manager API

A **Beauty Manager API** é uma API RESTful para gerenciar agendamentos, profissionais e serviços em estabelecimentos de beleza e bem-estar, como barbearias, salões de beleza e similares.

## Endpoints Principais

### **1. Agendamentos**

#### Criar Agendamento
- **Método**: `POST`
- **URL**: `/api/v1/agendamentos`
- **Descrição**: Cria um novo agendamento.
- **Corpo da requisição**:
  ```json
  {
    "clienteId": 1,
    "profissionalId": 1,
    "servicoId": 1,
    "estabelecimentoId": 1,
    "dataHora": "2024-10-21T23:56:40.424Z",
    "status": "ABERTO"
  }
  ```
- **Códigos de resposta**:
    - `201 CREATED`: Agendamento criado com sucesso.
    - `400 BAD REQUEST`: Requisição inválida.

#### Buscar Agendamento por ID
- **Método**: `GET`
- **URL**: `/api/v1/agendamentos/{id}`
- **Descrição**: Busca um agendamento pelo seu ID.
- **Parâmetros**:
    - `id` (obrigatório): ID do agendamento a ser buscado.
- **Códigos de resposta**:
    - `200 OK`: Agendamento encontrado com sucesso.
    - `404 NOT FOUND`: Agendamento não encontrado.

#### Atualizar Status do Agendamento
- **Método**: `PATCH`
- **URL**: `/api/v1/agendamentos/{id}`
- **Descrição**: Atualiza o status de um agendamento existente.
- **Corpo da requisição**:
  ```json
  {
    "status": "CONCLUIDO"
  }
  ```
- **Códigos de resposta**:
    - `204 NO CONTENT`: Status do agendamento atualizado com sucesso.
    - `404 NOT FOUND`: Agendamento não encontrado.

### **2. Profissionais**

#### Criar Profissional
- **Método**: `POST`
- **URL**: `/api/v1/estabelecimentos/profissionais`
- **Descrição**: Cria um novo profissional no sistema.
- **Corpo da requisição**:
  ```json
  {
    "nome": "João da Silva",
    "especialidades": "Cortes de cabelo",
    "statusProfissional": "DISPONIVEL",
    "estabelecimento_id": 1
  }
  ```
- **Códigos de resposta**:
    - `201 CREATED`: Profissional criado com sucesso.
    - `400 BAD REQUEST`: Requisição inválida.

#### Buscar Profissional por ID
- **Método**: `GET`
- **URL**: `/api/v1/estabelecimentos/profissionais/{id}`
- **Descrição**: Busca um profissional pelo ID.
- **Parâmetros**:
    - `id` (obrigatório): ID do profissional.
- **Códigos de resposta**:
    - `200 OK`: Profissional encontrado com sucesso.
    - `404 NOT FOUND`: Profissional não encontrado.

#### Atualizar Status do Profissional
- **Método**: `PATCH`
- **URL**: `/api/v1/estabelecimentos/profissionais/{id}`
- **Descrição**: Atualiza o status de um profissional existente.
- **Corpo da requisição**:
  ```json
  {
    "statusProfissional": "INDISPONIVEL"
  }
  ```
- **Códigos de resposta**:
    - `204 NO CONTENT`: Status do profissional atualizado com sucesso.
    - `404 NOT FOUND`: Profissional não encontrado.

### **3. Serviços**

#### Criar Serviço
- **Método**: `POST`
- **URL**: `/api/v1/servicos`
- **Descrição**: Cria um novo serviço oferecido por um estabelecimento.
- **Corpo da requisição**:
  ```json
  {
    "nome": "Corte Masculino",
    "descricao": "Corte de cabelo masculino",
    "preco": 35.00,
    "estabelecimentoId": 1,
    "profissionaisIds": [1, 2]
  }
  ```
- **Códigos de resposta**:
    - `201 CREATED`: Serviço criado com sucesso.
    - `400 BAD REQUEST`: Requisição inválida.

#### Buscar Serviço por ID
- **Método**: `GET`
- **URL**: `/api/v1/servicos/{id}`
- **Descrição**: Busca um serviço pelo seu ID.
- **Parâmetros**:
    - `id` (obrigatório): ID do serviço.
- **Códigos de resposta**:
    - `200 OK`: Serviço encontrado com sucesso.
    - `404 NOT FOUND`: Serviço não encontrado.

### **4. Estabelecimentos**

#### Buscar Estabelecimento por ID
- **Método**: `GET`
- **URL**: `/api/v1/estabelecimentos/{id}`
- **Descrição**: Busca um estabelecimento pelo ID.
- **Parâmetros**:
    - `id` (obrigatório): ID do estabelecimento.
- **Códigos de resposta**:
    - `200 OK`: Estabelecimento encontrado com sucesso.
    - `404 NOT FOUND`: Estabelecimento não encontrado.

## Tecnologias Utilizadas

- **Java 8+**
- **Spring Boot**
- **Hibernate/JPA**
- **PostgreSQL** (ou outro banco de dados)
- **Swagger** para documentação de API
- **Maven** para gerenciamento de dependências

## Como Rodar o Projeto Localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuprojeto.git
   ```

2. Entre no diretório do projeto:
   ```bash
   cd beauty-manager-api
   ```

3. Configure o banco de dados no arquivo `application.properties` ou `application.yml`.

4. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

5. Acesse a documentação da API no Swagger:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

## Testes

Você pode rodar os testes utilizando o Maven:
```bash
mvn test
```

## Documentação

A documentação completa da API pode ser acessada no Swagger:
```
https://beauty-manager-api-142a506a8af9.herokuapp.com/swagger-ui/index.html
```

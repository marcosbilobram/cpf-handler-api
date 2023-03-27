# High Risk CPF API
Olá! Este projeto tem como objetivo trazer uma API que gerencia uma lista de CPFs com risco de fraude.
O projeto foi desenvolvido utilizando JDK 17, Spring Framework, JPA, banco de dados relacional H2, biblioteca Lombok e a biblioteca springdoc-openapi para documentação com swagger.

## Estrutura do Projeto

Este projeto foi estruturado realizando a separação em 6 packages , sendo elas:

### Controllers

Esta package tem por objetivo armazenar o controlador das requisições HTTP realizadas pelo usuário. Fazendo uma ligação direta com a camada de serviços, o controlador será responsável também por fornecer respostas ao usuário de acordo com as requisições e as regras de negócio implementadas na camada de serviço.

### Services

Esta package tem por objetivo armazenar a camada de serviço da aplicação. Nesta camada, todas as regras de negócio serão implementadas para que todas as requisições feitas pelo usuário possam ser respondidas de acordo com as regras impostas na ideação do projeto. Nesta camada os dados enviados são validados, tratados e a intermediação entre o controlador e o repositório é feita para que a requisição seja respondida.

### Repositories

Esta package tem por objetivo armazenar a camada de repositório da aplicação. Esta camada irá implementar uma interface que acessará e manipulará os dados do banco de dados da aplicação. Estando em contato direto com o banco de dados, esta interface
tornará o acesso mais simples e rápido.

### Entities

Esta package tem por objetivo armazenar a camada de entidades da aplicação. Esta camada será responsável por definir as estruturas de dados da aplicação e do banco de dados, e terá contato direto com a camada de repositório.

### DTO

Esta package tem por objetivo armazenar a camada de Data Transfer Objects da aplicação. A camada de DTO é responsável por definir as estruturas de dados usadas para transferir informações entre as diferentes camadas da aplicação ou entre a aplicação e outros sistemas externos. DTOs são projetados para serem simples e leves, contendo apenas os dados necessários para a transferência de informações. A camada de DTO ajuda a separar a lógica de negócios da lógica de transferência de dados e a melhorar o desempenho e a escalabilidade da aplicação.

### Exceptions

Esta package tem por objetivo armazenar as classes de implementação das exceções próprias do projeto. Estas exceções contextualizarão os possíveis erros de uma forma melhor e aprimorarão a experiência do usuário.

## Testes Unitários

Neste projeto foram implementados testes unitários para as camadas Controllers, Services e Repositories. Para executá-los todos juntos, após clonar o projeto na IDE Intellij, selecione a pasta de nome "java" localizada dentro da pasta de nome "test" da aplicação, clique com o botão direito e selecione "Run 'All Tests'". Os testes estão nomeados de acordo com a função de cada teste.

## Instruções para Execução do Projeto

Para executar a aplicação, pode ser usado o botão de Run de cada IDE. Mas caso seja necessário outro método, deve-se localizar a classe "HighRiskCpfApplication", seleciona-lá com o botão direito do mouse e selecionar a opção "Run HighRiskCpfApplication".

## Configuração

Toda configuração de conexão e execução do banco de dados esta disponível no arquivo application.properties localizado na pasta "resources" do projeto. O banco será executado na porta "http://localhost:8080" e o acesso as tabelas será feito pela path "http://localhost:8080/h2-console/login.jsp". Ao cliclar no botão connect será liberado o acesso ao banco de dados da aplicação.

### Baixando arquivo .zip

Faça o download do arquivo zip e descompacte na workspace da IDE. Então importe e abra o arquivo descompactado localizado na workspace da IDE.

### Clonando via Github

Requisitos:
Git : https://git-scm.com  
Na pasta onde se deseja salvar o clone do projeto, clique com o botão direito na área da pasta e selecione a opção "Git Bash Here". Quando o terminal for aberto copie, cole e execute o seguinte comando :

git clone https://github.com/marcosbilobram/high-risk-cpf-api.git

### Importando postman collection

Requisitos : 
Postman: https://www.postman.com

Ao ser realizado o clone ou download do projeto, caso deseje utilizar a ferramenta postman para os testes de métodos HTTP, abra o programa postman, entre ou crie uma workspace, clique no botão "import" localizado no canto superior esquerdo e insira o arquivo "High-Risk-Cpf-API methods.postman_collection" na drop box ou apenas recupere o mesmo da pasta clonada do projeto.

# Detalhamento dos endpoints

Para um detalhamento completo dos endpoints feito pelo swagger, após a execução da aplicação, acesse a interface do swagger da aplicação pelo link : http://localhost:8080/swagger-ui/index.html#/.

Segue abaixo detalhamento dos endpoint:

Conforme citado anteriormente, o projeto é executado na URL http://localhost:8080

---
> **GET** /high-risk-cpf/cpf
>
> Retorna todos os CPFs cadastrados ou array vazio

```js
Response:
[
    {
        "cpf": "413.530.550-43",
        "createdAt" : "2023-03-27T02:41:56"
    },
    {
        "cpf": "428.594.522-57",
        "createdAt" : "2023-03-27T02:42:56"
    }
]

Or:
    []

```
---

> **POST** /high-risk-cpf/cpf
> 
> Insere o cpf fornecido na base de dados

``` js
Request Body:
{
	"cpf": "413.530.550-43"
}
``` 
---


> **GET** /high-risk-cpf/cpf/{cpf}

> Retorna o CPF fornecido se estiver na base de dados

``` js
Request Body:
{
	"cpf": "413.530.550-43"
}
``` 

---

```js
Response Body:
    {
        "cpf": "413.530.550-43",
        "createdAt" : "2023-03-27T02:41:56"
    }
```

---

> **Delete** /high-risk-cpf/cpf/{cpf}

> Remove o CPF fornecido se estiver na base de dados

``` js
Request Body:
{
	"cpf": "413.530.550-43"
}
``` 
---

# Exceções

Foram implementadas exceções para tratar possíveis erros na aplicação.

> Caso o CPF fornecido seja inválido:

``` js
InvalidCpfException:
{
	"type" : "InvalidCpfException",
	"message" : "CPF is not valid."
}
```
> Caso o CPF fornecido no método POST já exista na base de dados

``` js
ExistsCpfException:
{
	"type" : "ExistsCpfException",
	"message" : "CPF already exists in databse"
}
```

> Caso o CPF fornecido nos métodos GET não sejam encontrados na base de dados

``` js
NotFoundCpfException:
{
	"type" : "NotFoundCpfException",
	"message" : "Can't find the given CPF in data bank"
}
```

# cadastro-service
Projeto para recrutamento compasso uol. Permite o cadastro de cliente e também cidades


## Banco de dados
A projeto foi desenvolvido utilizando o banco de dados PostgreSQL. Foi testado na versão 12.5

Para utilizar o projeto deve ser criado o database e executar o script de banco que está dentro do projeto. O arquivo se chama scriptbanco.sql. Será criado as tabelas "cidade" e "cliente".

Para alterar o servidor do banco, senha e nome do database basta alterar as propriedades do arquivo application.yml. No meu ambiente o banco se chama "cadastro_service", rodando localmente e com senha 123


## Postman
Foi adicionado também dentro do projeto a colection do postman para realizar os testes dos métodos rest. O arquivo de chama recrutamento_compassouol.postman_collection.json.

Só importar para dentro do postman que será criado a colection com os métodos abaixo:



Cidade:

GET:
http://localhost:8080/cadastro-service/rest/cidade

POST:
http://localhost:8080/cadastro-service/rest/cidade


Cliente:

GET:
http://localhost:8080/cadastro-service/rest/cliente

POST:
http://localhost:8080/cadastro-service/rest/cliente

PUT:
http://localhost:8080/cadastro-service/rest/cliente/{idCliente}

DELETE:
http://localhost:8080/cadastro-service/rest/cliente/{idCliente}



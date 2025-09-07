## Portifólio API

### API  de gerenciamento de Projeto para o portifólio de uma empresa

Este projeto disponibiliza endpoints para manipulação completa de entidades Projeto, Membro e ProjetoMembro (N pra N das entidades anteriores).

Ao baixar o projeto via github, leia com atenção as variáveis de ambiente requeridas em ".env.example", copie-as para um novo arquivo local ".env" e substitua os valores padrão pelos valores do seu ambiente de desevolvimento.
Instale as dependências com o comando:

``` shell
mvn clean install
```

O API foi documentada com Swagger, sendo que a interface gráfica para as requisições podem ser acessadas em:

http://localhost:8080/swagger-ui/index.html

Já no Swagger: 

- Faça login na rota "auth/login" com as credenciais do user pré-cadastrado em "SecurityConfig";
- Recupere o token de acesso e cadastre na autencicação do Swagger.

Pronto, desta forma todas as rotas estarão liberadas dentro do Swagger para testes.

Perceba também que o pacote "config" tem arquivos que pré-cadastram dados em todas tabelas, de forma que o projeto já inicia populado com dados fictíceos.


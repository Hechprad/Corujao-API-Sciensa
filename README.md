# Back end code challenge - Sciensa
***
Este é um projeto Spring Boot Java de uma API que consome e produz json, baseada em uma documentação criada com [Swagger 2.0](https://github.com/Hechprad/Corujao-API-Sciensa/blob/master/api.yml)

A API cadastra e realiza buscas de gêneros, artistas e filmes.
***
### 📌Requirements - Exigências para execução da API

- Para execução com Docker:
    - Docker
- Para execução local com uma IDE:
    - Java
    - Maven
    - Postgres

***
### 📌Getting Started

- Clone ou faça o download deste repositório:
- Com Docker
    - Acesse codigo-fonte-api\sciensa-corujao-api\src\main\resources e mude o comentário para acesso ao banco de dados com Docker.
    - Execute no terminal:
      - cd codigo-fonte-api\sciensa-corujao-api
      - docker-compose up
 - Com IDE (Possível realizar a execução de testes):
   - Importe o projeto.
   - Crie o banco de dados 'movies_db' no postgres.
 - Caso queira gerar um novo arquivo '.jar': 
   - pelo terminal acesse o diretório 'sciensa-corujao-api' que possue o pom.xml e execute o maven:
   - mvn clean package
   - mvn -Dmaven.test.skip clean package
   
***

- Endpoints:
  - Contrato Swagger 2.0:
        - http://localhost:5000/v1/swagger-ui.html
  - Gêneros: 
      - Post:
          - Cadastra um gênero - http://localhost:5000/v1/genres
          - Exemplo de json para cadastro: 
              - {"description": "Ficção"}
      - Get:
          - Retorna todos os gêneros - http://localhost:5000/v1/genres
          - Retorna um gênero pelo id - http://localhost:5000/v1/genres/{id}
      - Put:
          - Atualiza um gênero cadastrado - http://localhost:5000/v1/genres/{id}
          - Exemplo de json para cadastro: 
              - {"description": "Animação"}
  - Artistas:
      - Post:
          - http://localhost:5000/v1/artists
              - Exemplo json para cadastro:
      - Get:
      - Put:
  - Filmes:
      
### 📌Built With

 - Java
 - Spring Boot
 - Maven
 - Tomcat
 - PostgreSQL
 - Hibernate
 
***
### 📌Author

 - [Jorge Hecherat](https://github.com/Hechprad)



# Sciensa: Teste de desenvolvedor back-end

Neste teste, avaliaremos a sua capacidade de implementar corretamente uma API REST.
  - Faça um fork desse repositório
  - Implemente a API documentada no arquivo `api.yml`, presente na raiz deste repositório
  - Use o repositório de dados de sua preferência (mongo, redis, mysql, mssql etc)
  - Forneça acesso de leitura do seu fork para o avaliador da Sciensa

# Diferenciais que levaremos em consideração ao avaliar o seu teste!
  - Cobertura de testes
  - Automação (imagem de docker e ambiente local containerizado com docker-compose)
  - Estrutura do projeto
  - Qualidade de código

Boa sorte!  
#vamosjuntos

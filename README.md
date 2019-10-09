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
        - Se quiser, rode o seguinte comando para subir um container com postgresql:
            - docker run --name postgres_eclipse -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=postgres  -e POSTGRES_DB=movies_db -d -p 5432:5432 postgres

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
   - Execute a classe Swagger2SpringBoot.java
   
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
          - Retorna uma lista todos os gêneros cadastrados - http://localhost:5000/v1/genres
          - Retorna um gênero pelo id - http://localhost:5000/v1/genres/{id}
      - Put:
          - Atualiza um gênero cadastrado - http://localhost:5000/v1/genres/{id}
          - Exemplo de json para atualização: 
              - {"description": "Animação"}
  - Artistas:
      - Post:
          - Cadastra um artista - http://localhost:5000/v1/artists
          - Exemplo json para cadastro:
              - {"dateOfBirth": "1939-05-25","firstName": "Ian","lastName": "McKellen"}
      - Get:
          - Retorna uma lista com todos os artistas cadastrados - http://localhost:5000/v1/artists
          - Retorna um artista pelo id - http://localhost:5000/v1/artists/{id}
          - Retorna a filmografia de um artista pelo id - http://localhost:5000/v1/artists/{id}/filmography
      - Put:
          - Atualiza um artista cadastrado - http://localhost:5000/v1/artists/{id}
          - Exemplo de json para atualização: 
              - {"dateOfBirth": "1981-01-28","firstName": "Elijah","lastName": "Wood"}
  - Filmes:
      - Post:
          - Cadastra um filme - http://localhost:5000/v1/movies
          - Exemplo json para cadastro:
              - {"releaseYear": 2001,"title": "O Senhor dos Anéis: A Sociedade do Anel"}
      - Get:
          - Retorna uma lista todos os filmes cadastrados - http://localhost:5000/v1/movies
          - Retorna um filme pelo id - http://localhost:5000/v1/gmovies/{id}
      - Put:
          - Atualiza um filme cadastrado - http://localhost:5000/v1/artists/{id}
          - Exemplo de json para atualização:
               - {"releaseYear": 2000,"title": "Harry Potter","genres":[{"id":1},{"id":2}],"cast":[{"id":1},{"id":2}],"director": {"id":3}}
      - Delete:
          - Deleta um filme com o id cadastrado - http://localhost:5000/v1/movies/{id}

📌*NOTA 1:*
- Diretor, lista de gêneros e lista de atores (cast), se já estiverem cadastrados no banco de dados podem ser adicionados na hora do cadastro do filme.
    
```
EXEMPLO:
{
    "releaseYear": 2001,
    "title": "O Senhor dos Anéis: A Sociedade do Anel",
    "genres":[{"id":1},{"id":2},{"id":3}],
    "cast":[{"id":1},{"id":2}],
    "director": {"id":3}
}
```

📌*NOTA 2:*
- Nos endpoints que retornam uma lista é possivel passar query params:
    - qual página acessar começando da página 0: "**page=0**"
    - quantidade de elementos em cada páginas: "**size=3**"
    - ordenação crescente ou decrescente por atributo da entidade: "**sort=description,desc**" 
    - filtragem:
        - Campo "description" de Gênero: "**search=Harry**"
        - Campo "firstName lastName" de Artista: "**search=Elijah**"
        - Campo "title" de Gênero: "**search=Fantasia**"

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

***
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

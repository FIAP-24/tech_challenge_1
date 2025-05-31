# Tech Challenge 1 - User Management API

API REST para gerenciamento de usuários, desenvolvida com Spring Boot, MySQL e documentação via Swagger.

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA
- MySQL
- Maven
- Docker Compose
- SpringDoc OpenAPI (Swagger)

## Como executar

1. **Clone o repositório:**
   ```sh
   git clone <https://github.com/FIAP-24/tech_challenge_1.git>
   cd tech_challenge_1

2. **Execute o Docker Compose:**
   ```sh
   docker compose up -d
   ```
Isso irá subir o banco de dados MySQL e a aplicação.


3. **Acesse a aplicação:**
   - Acesse o Swagger UI para visualizar a documentação da API:
   ```sh
   http://localhost:8080/swagger-ui.html
   ```

Swagger UI: http://localhost:8080/swagger-ui.html


**Endpoints**

- Usuários
  - `GET /api/v1/usuarios`: Lista todos os usuários.
  - `GET /api/v1/usuarios/{id}`: Busca usuário por ID.
  - `POST /api/v1/usuarios`: Cria um novo usuário.
  - `PUT /api/v1/usuarios/{id}`: Atualiza um usuário existente.
  - `DELETE /api/v1/usuarios/{id}`: Remove um usuário.
  - `POST /api/v1/usuarios/login`: Autentica um usuário.

Lista todos os usuários.
- `GET /api/v1/usuarios



Busca usuário por ID.
- `GET /api/v1/usuarios/{id}

Cria um novo usuário.
- `POST /api/v1/usuarios
    
  - Body:
    ```json
    {
    "nome": "string",
    "email": "string",
    "perfil": "PROPRIETARIO ou CLIENTE",
    "login": "string",
    "senha": "string",
        "endereco": {
            "logradouro": "string",
            "numero": "string",
            "complemento": "string",
            "bairro": "string",
            "cidade": "string",
            "estado": "string",
            "cep": "string"
        }
    }
    ```

Atualiza um usuário existente.
- `PUT /api/v1/usuarios/{id}

  - Body:
      ```json
      {
      "nome": "string",
      "email": "string",
      "perfil": "ADMIN ou USER",
      "senha": "string",
        "endereco": {
            "logradouro": "string",
            "numero": "string",
            "complemento": "string",
            "bairro": "string",
            "cidade": "string",
            "estado": "string",
            "cep": "string"
        }
      }
    ```
    
Autentica um usuário.    
- `POST /api/v1/usuarios/login

  - Body:
    ```json
    {
    "login": "string",
    "senha": "string"
    }
    ```

Remove um usuário.
- `DELETE /api/v1/usuarios/{id}



Documentação
Acesse a documentação interativa em:
http://localhost:8080/swagger-ui.html

<hr></hr>
Observação:
Certifique-se de que as portas 8080 (aplicação) e 3308 (MySQL) estejam livres em sua máquina.





.


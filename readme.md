
# 🎮 Game Catalog with Spring Boot

Aplicação Java com Spring Boot para gerenciamento de estúdios e jogos. O projeto permite o cadastro, listagem e busca de jogos e estúdios, utilizando arquitetura modularizada, banco de dados PostgreSQL (produção) e H2 (testes), além de documentação automática via Swagger/OpenAPI.

## 🧱 Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL (produção)
- H2 Database (testes)
- Lombok
- JUnit 5
- Mockito
- Swagger (SpringDoc OpenAPI)

---

## 📁 Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   └── br.com.wbs
│   │       ├── config.swagger        # Configuração do Swagger
│   │       ├── exceptions            # Exceções customizadas e handlers
│   │       └── modules
│   │           ├── game              # Módulo de jogos (entidade, DTO, use cases, etc)
│   │           └── studio            # Módulo de estúdios (entidade, DTO, use cases, etc)
│   └── resources
│       ├── application.yml
│       └── application-test.yml
│
├── test
│   └── java
│       └── br.com.wbs.modules
│           ├── game                 # Testes de game (repos, use cases)
│           └── studio               # Testes de studio (controladores, use cases)
```

---

## 🚀 Como executar

### Pré-requisitos

- Java 21+
- Maven 3.8+
- Docker (opcional para banco)

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/wbsfps/game-catalog-with-spring-boot.git
cd game-catalog-with-spring-boot
```

2. Execute com o Spring Boot:

```bash
./mvnw spring-boot:run
```

3. Acesse a documentação Swagger:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Rodando os testes

O projeto possui testes unitários utilizando JUnit e Mockito. Para executar:

```bash
./mvnw test
```

---

## 📦 Endpoints principais

### 🎮 Jogos

- `POST /api/game/` — Cadastrar novo jogo
- `GET /api/game/` — Listar todos os jogos
- `GET /game/{id}` — Buscar jogo por ID

### 🏢 Estúdios

- `POST /studios/` — Cadastrar novo estúdio
- `GET /studios/` — Listar todos os estúdios
- `GET /studios/{id}` — Buscar estúdio por ID

---

## 🧑‍💻 Autor

**William Andrade**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-wbsfps-blue)](https://www.linkedin.com/in/william-andrade-78b4a6219/)  
[![GitHub](https://img.shields.io/badge/GitHub-wbsfps-black)](https://github.com/wbsfps)

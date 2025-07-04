# 🔐 KeyManager

O **KeyManager** é uma aplicação desenvolvida com o objetivo de testar novas regras de negócio, aplicar conhecimentos já adquiridos e enfrentar desafios que contribuam para o meu aperfeiçoamento como desenvolvedor.

Essa aplicação permite o armazenamento e a manipulação de dados de contas vinculadas a usuários, com uma interface simples, intuitiva e funcionalidades pensadas para facilitar a experiência do usuário.

> 💡 Este é, até o momento, o projeto mais robusto que desenvolvi — tanto em complexidade quanto em escopo. Utilizei diversas tecnologias com as quais já estava familiarizado, como **Lombok**, **JPA** e **Spring Web**, e também explorei novas como o **Spring Security**.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Maven**
- **Spring Boot**
- **Spring Security**
- **Lombok**
- **JPA (Hibernate)**
- **MySQL**
- **HTML + CSS**
- **JavaScript**

---

## ⚙️ Como Funciona

### 🔧 Back-end
A API foi desenvolvida seguindo os princípios REST, promovendo padronização, legibilidade e fácil manutenção.

- As funcionalidades principais seguem o padrão **CRUD** para manipulação dos dados das contas.
- O **Spring Security** foi integrado para realizar o *hashing* das senhas dos usuários, garantindo maior segurança.
- A comunicação entre o front-end e a API é feita por meio de requisições HTTP, retornando dados estruturados para uso nas interfaces.

### 🖥️ Front-end
O front-end foi construído de forma leve e funcional, com o foco em exibir e manipular os dados da API de forma acessível ao usuário.

#### Funcionalidades disponíveis:
- Criar novas contas
- Editar dados
- Excluir contas
- Marcar contas como favoritas
- Copiar senha com um clique

---

## 🛠️ Como Executar Localmente

> ❗ Por limitações com o Java 24 (versão em desenvolvimento que utilizei inicialmente), não consegui realizar o deploy do back-end em produção. No entanto, a aplicação está pronta para rodar em ambientes locais com Java 17.

### Passos para rodar o projeto:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/MatheusMedeiroscl/KeyManager.git
2. **Execute o projeto:**
   ```bash
   git clone https://github.com/MatheusMedeiroscl/KeyManager.git
3. **Acesse o Front end:**
   
   [https://matheusmedeiroscl.github.io/Front_KeyManager/static/views/access.html](https://matheusmedeiroscl.github.io/KeyManager/src/main/resources/static/views/access.html)

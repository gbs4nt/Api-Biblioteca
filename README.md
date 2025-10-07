
<h1  align="center">

API Biblioteca
</h1>

  
  
  

API para gerenciar uma biblioteca que permite cadastrar usuários e livros, além emprestar e devolver livros.


  
  
  

## Tecnologias

  <p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,spring,postgres,docker&perline=6" />
  </a>
</p>


  
  

## ⚙️ Endpoints

### 🧾 **Livros**

#### ➕ Criar livro  
**POST** `/livros`  
Cria um novo livro na biblioteca.

**Body (JSON):**
```
json
{
  "titulo": "O Senhor dos Anéis",
  "autor": "J.R.R. Tolkien",
  "anoPublicacao": 1954
}
```
Resposta: ```201 created```

## 📖 Emprestar Livro

**PUT** `/livros/{usuarioId}/{livroId}/emprestar`

Registra o empréstimo de um livro por um usuário.

Exemplo: livros/1/2/emprestar

Resposta: ```204 No Content```

## 🔁 Devolver livro

**PUT** `/livros/{usuarioId}/{livroId}/devolver`

Registra a devolução de um livro emprestado.

Exemplo de URL:

Exemplo: /livros/1/10/devolver


Resposta: ```204 No Content```


### 👤 Usuários
### ➕ Criar usuário

***POST*** `/usuarios`
Cadastra um novo usuário da biblioteca.

**Body (JSON):**
```
json
{
  "nome": "Gabriel Lima",
  "email": "gabriel@email.com"
}
```

Resposta: ```201 Created```


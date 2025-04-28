# CP 04 API MTG


<!--Primeira parte: Introduzir o que é -->
Este projeto irá documentar a  construção de uma API RESTful de decks de Magic: The Gathering.

O sistema construido tem o objetivo de manipular decks de Magic, dessa forma será permitido criar, pesquisar, editar, e organizar seus decks como preferir.


<!--Segunda parte descrever todas as 20 rotas-->

### Rotas da API 

| Método | Rota         | Descrição                     | Status Codes     |
|--------|--------------|-------------------------------|------------------|
| GET    | /decks       | Listar todos os decks         | 200, 500         |
| GET    | /decks/{id}  | Buscar um deck por ID         | 200, 404, 500    |
| POST   | /decks       | Criar um novo deck            | 201, 400, 500    |
| PUT    | /decks/{id}  | Atualizar um deck existente   | 200, 400, 404, 500 |
| DELETE | /decks/{id}  | Deletar um deck               | 204, 404, 500    |
| GET    | /cartas      | Listar todas as cartas        | 200, 500         |
| GET    | /cartas/{id} | Buscar uma carta por ID       | 200, 404, 500    |
| POST   | /cartas      | Cria uma nova carta           | 201, 400, 500    |
| PUT    | /cartas/{id} | Atualizar uma carta existente | 200, 400, 404, 500 |
| DELETE | /cartas/{id} | Deletar uma carta             | 204, 404, 500 |
| GET    | /decks/{deckId}/cartas/ | Listar todas as cartas dentro de um deck | 200, 404, 500 |
| GET    | /decks/{deckId}/cartas/{cartaId} | Busca dentro de deck uma carta por ID| 200, 404, 500 |
| POST   | /decks/{deckId}/cartas | Adiciona uma carta dentro do deck | 201, 400, 500 |
| PUT    | /decks/{deckId}/cartas/{cartaId} | Atualiza uma carta existente dentro do deck | 200, 400, 404, 500 |
| DELETE | /decks/{deckId}/cartas/{cartaId} | Deletar uma carta dentro de deck | 204, 404, 500 |
| GET    | /decks/{deckId}/cartas/tipo/{tipo}| Lista todas as cartas com filtro de tipo aplicado dentro de deck | 200, 404, 500 |
| GET    | /usuarios | Listar todos os usuários| 200, 500 |
| GET    | /usuarios/{id}  | Busca um usuario por ID | 200, 404, 500 |
| POST   | /usuarios       | Criar um novo usuário | 201, 400, 500 |
| PUT    | /usuarios/{id}  | Atualizar um usuário existente | 200, 400, 404, 500 |
| DELETE | /usuarios/{id}  | Deletar um usuário | 204, 404, 500 |
| GET    | /usuarios/{id}/decks  | Listar todos os decks pertencentes ao usuário | 200, 404, 500 |


<!-- Próximo passo comecar a fazer o DAO -->


### DTOs e Modelos de Dados

#### DeckDTO

Representa um deck de cartas de Magic: The Gathering.

**Atributos:**
- `id` (integer, obrigatório): Identificador único do deck.
- `nome` (string, obrigatório): Nome do deck.
- `descricao` (string, opcional): Descrição do deck.
- `formato` (string, obrigatório): Formato do deck (ex: "Commander", "Standard", "Modern").
- `cartas` (array de CartaDTO, opcional): Lista de cartas que compõem o deck.
- `usuarioId` (integer, obrigatório): ID do usuário dono do deck.
- `dataCriacao` (string, obrigatório): Data de criação do deck no formato ISO 8601.

**Exemplo de JSON:**
```json
{
  "id": 1,
  "nome": "Deck de Dragões",
  "descricao": "Um deck focado em criaturas dragões lendárias.",
  "formato": "Commander",
  "cartas": [
    {
      "id": 101,
      "nome": "Shivan Dragon",
      "tipo": "Criatura",
      "cor": "Vermelho",
      "custoMana": "4RR",
      "quantidade": 2
    },
    {
      "id": 102,
      "nome": "Dragonlord Atarka",
      "tipo": "Criatura",
      "cor": "Vermelho-Verde",
      "custoMana": "5RG",
      "quantidade": 1
    }
  ],
  "usuarioId": 12,
  "dataCriacao": "2025-04-28T14:30:00Z"
}
```


#### CartaDTO

Representa uma carta de Magic: The Gathering.

**Atributos:**
- `id` (integer, obrigatório): Identificador único de carta.
- `nome` (string, obrigatório): Nome da carta.
- `descricao` (string, opcional): Descrição opcional explicando a carta.
- `tipo` (string, obrigatório): Tipo da carta (ex: "vanguard", "tribal", "Enchantment").
- `cor` (string, obrigatório): Cor da carta (ex: "Red", "Blue", "Green").
- `custoMana` (integer, obrigatório): Custo de mana da carta (ex: "2R", "3GG").
- `quantidade` (integer, obrigatório): Quantidade dessa carta no deck.

**Exemplo de JSON:**
```json
{
  "id": 205,
  "nome": "Xyris, the Writhing Storm",
  "tipo": "Creature",
  "cor": "Gold",
  "custoMana": "3GG",
  "quantidade": 4,
  "descricao": "Causa 3 pontos de dano a qualquer alvo."
}
```

#### UsuarioDTO

Representa os dados de um usuário do sistema.

**Atributos:**
- `id` (integer, obrigatório): Identificador único do usuário.
- `nome` (string, obrigatório): Nome completo do usuário.
- `email` (string, obrigatório): E-mail do usuário.
- `senha` (integer, obrigatório): Senha de acesso (armazenada de forma segura).
- `dataCriacao` (string, opcional): Data de criação da conta (formato ISO 8601).

**Exemplo de JSON:**
```json
{
  "id": 10,
  "nome": "José Carlos",
  "email": "jose.carlos@gmail.com",
  "senha": "minhasenha123",
  "dataCriacao": "2025-04-28T14:30:00Z"
}
```













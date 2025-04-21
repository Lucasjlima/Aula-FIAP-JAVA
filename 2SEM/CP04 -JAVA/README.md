# CP 04 API MTG


<!--Primeira parte: Introduzir o que é -->
Este projeto irá documentar a  construção de uma API RESTful de decks de Magic: The Gathering.

O sistema construido tem o objetivo de manipular decks de Magic, dessa forma será permitido criar, pesquisar, editar, e organizar seus decks como preferir.


<!--Segunda parte descrever todas as 20 rotas-->

# Rotas da API 

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
| DELETE | /cartas/{id} | Deletar uma carta            | 204, 404, 500     |
| GET    | /decks/cartas/| Listar todas as cartas        | 200, 500        |
| GET    | /cartas/{id} | Buscar uma carta por ID       | 200, 404, 500    |
| POST   | /cartas      | Cria uma nova carta           | 201, 400, 500    |
| PUT    | /cartas/{id} | Atualizar uma carta existente | 200, 400, 404, 500 |
| DELETE | /cartas/{id} | Deletar uma carta            | 204, 404, 500     |






# 41_challenge-AZCap_API-noframework_fullJava

nota:
precisa adicionar livraria externa antes de executar

<h1>endpoints:</h1>
<hr>
Adicionar Nova Sala

Esta rota permite adicionar um novo projeto ao sistema.
Método
POST
path:
/rooms/add
Parâmetros da Requisição
Nome (obrigatório): AZCapital.
Descrição: Uma breve descrição do projeto.

Data de Início:terca feira 02/04/2024.
Data de Término terca feira 03/04/2024.
Responsável: J.
Corpo da Requisição
{
    "roomName": "Nome da Sala",
    "type": "tipo de sala",(MEETING_ROOM,CONFERENCE_ROOM,TRAINING_ROOM,OTHER)
    "capacity": "quantidade de pessoas",
    
}
Respostas
200 OK: O projeto foi adicionado com sucesso. Retorna os dados do projeto adicionado.
400 Bad Request: Um ou mais parâmetros estão faltando ou em formato inválido. Retorna uma mensagem de erro detalhada.
500 Internal Server Error: Ocorreu um erro interno ao adicionar o projeto. Retorna uma mensagem de erro.
Exemplo de Requisição
POST /projects HTTP/1.1
Host: example.com
Content-Type: application/json

Corpo da Requisição
{
    "roomName": Mongolia",
    "type": "MEETING_ROOM",
    "capacity": 50,
    
}



Exemplo de Resposta (200 OK)
{ empty(void)  }

![postman_post](https://github.com/josevflores911/41_challenge-AZCap_API-noframework_fullJava/assets/59713685/c38f4fb5-f2e2-464b-9ec0-9ba345017887)


Obter todas as salas

Esta rota permite ver a lista de salas disponiveis.

Método
GET
path:
/rooms/all

Respostas
200 OK: O projeto foi adicionado com sucesso. Retorna os dados do projeto adicionado.
400 Bad Request: Um ou mais parâmetros estão faltando ou em formato inválido. Retorna uma mensagem de erro detalhada.
500 Internal Server Error: Ocorreu um erro interno ao adicionar o projeto. Retorna uma mensagem de erro.
Exemplo de Requisição
POST /projects HTTP/1.1
Host: example.com
Content-Type: application/json


Exemplo de Resposta (200 OK)
[
{"id":1,"type":"MEETING_ROOM","roomName":"Olimpo","capacity":10},{"id":2,"type":"CONFERENCE_ROOM","roomName":"Atenas","capacity":20}
]


Observações
•	Certifique-se de enviar um token de autenticação válido no cabeçalho da requisição para autenticar a chamada à API.
•	Todos os parâmetros são obrigatórios, exceto a descrição.
•	A data de término estimada deve ser posterior à data de início.
•	A API retorna um código de status HTTP correspondente à situação da requisição.
•	Utilize uma biblioteca cliente HTTP ou ferramenta como cURL ou Postman para fazer chamadas à API.

![browser_get](https://github.com/josevflores911/41_challenge-AZCap_API-noframework_fullJava/assets/59713685/a7e47398-112d-42d7-a408-b690ba8620a5)

![postman_get](https://github.com/josevflores911/41_challenge-AZCap_API-noframework_fullJava/assets/59713685/cbe38faf-52cf-4f54-a37b-7e3f54b1a13f)

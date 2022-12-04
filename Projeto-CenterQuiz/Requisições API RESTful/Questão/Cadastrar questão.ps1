curl --location --request POST 'localhost:8080/centerquiz/api/questao' \
--header 'Content-Type: application/json' \
--data-raw '{
    "titulo": "Titulo teseee",
    "texto": "Texto testee",
    "opcoes": [
        "Opção 1",
        "Opção 2",
        "Opção 3"
    ],
    "respostas": [
        0
    ],
    "idBancoDeQuestoes":38
}'
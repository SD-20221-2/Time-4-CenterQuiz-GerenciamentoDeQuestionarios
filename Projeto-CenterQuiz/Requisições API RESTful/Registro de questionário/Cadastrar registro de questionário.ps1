curl --location --request POST 'localhost:8080/centerquiz/api/registro-questionario' \
--header 'Content-Type: application/json' \
--data-raw '{
    "questionario": {
        "nome":"Questionario teste",
        "tipoQuestionario":true,
        "dataInicil":null,
        "dataFim":null,
        "duracao":1
    },
    "bancoDeQuestoes": {
        "questoes": [
            {
                "titulo": "questao teste 1",
                "texto": "texto teste 1",
                "opcoes": [
                    "Opção 1",
                    "Opção 2"
                ],
                "respostas": [
                    1,
                    2
                ],
                "vezesPerguntado": 4
            }
        ]
    }
}'
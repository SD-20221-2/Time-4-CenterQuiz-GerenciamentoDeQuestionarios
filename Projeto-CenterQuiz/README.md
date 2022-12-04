## Time: 
    MATHEUS LÁZARO HONÓRIO DA SILVA
    JOSE TEIXEIRA MENDES JUNIOR
    IGOR MATEUS FRANCA DINIZ LOVI
    MARCOS RODRIGUES

## Projeto:
#### Desenvolver um Web Service multiusuário de criação e resolução de questionários para:
1. Avaliação de negócios;
2. Treinamento profissional;
3. Educação com testes;
4. Avaliações pré-contratação técnica e de personalidade;
5. Recrutamento;
6. Questionários de saúde e segurança
7. Dentre outros possíveis.

#### Outras particularidades de implementação:
1. Limite de tempo;
2. Acesso público ou privado;
3. Perguntas aleatórias;
4. Feedback instantâneo
5. Escolha única
6. Escolha múltipla;
7. Resposta curta.
#### Uso de uma API REST, um banco de dados incorporado, segurança e possível interface de usuário (Web).


### Descrição:
1. Criação de modelo cliente-servidor, onde o cliente pode ser um navegador, uma ferramenta como o Curl, ou alguma interface de testes como o Postman.
2. Desenvolvimento back-end com contrução de um banco de dados integrado.
3. Utilização do framework Spring Boot para implementação e testes.

### Etapas do projeto:
1. Desenvolvimento de uma API REST JSON que crie ou retorne um Quiz (Questionário) a ser resolvido, suportando a obtenção do Quiz e a solução dele passando-se uma resposta. Podendo ser múltiplos questionários.
2. Impedimento de criação de questionário inconsistente.
3. Criação do banco de dados para armazenamento e manipulação dos questionários.
4. Criação de serviço de suporte aos usuários e processo de autorização. Criação de privilégios a usuários, com cadastro de usuário e exclusão de questionário criado pelo usuário logado.
5. Mecanismo de Atualização dos questionários, através de PUT e PATCH.
6. Criação de paginação ao obter questionários.
7. Obter todas as conclusões de questionários com paginação.
8. Proteção de conexão utilizando protocolo HTTPS.

### Outros possíveis tópicos de sistemas distribuídos a serem aplicados
1. Computação móvel através de criação da interface.
2. Implementação de um cliente de e-mail para informe de resultado dos questionários, com algum protocolo (como o SMTP, por exemplo).

### Tecnologias:
* Spring Framework
* IDE: Eclipse
* Servidor: Tomcat
* Analisador de API: Postman

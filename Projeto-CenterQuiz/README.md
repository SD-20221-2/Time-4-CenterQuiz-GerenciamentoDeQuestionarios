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


<hr/>

## Como hospedar na AWS gratuitamente, o backend Spring Boot e banco de dados Postgres
Aqui estão os passos para hospedar seu projeto Spring Boot e banco de dados Postgres gratuitamente na AWS:

1. Crie uma conta gratuita na AWS (https://aws.amazon.com/).
2. Após criar a conta, acesse o console da AWS.
3. No console, selecione o serviço "EC2".
4. Clique em "Launch Instance" para iniciar o processo de criação de uma nova instância.
5. Na tela "Choose AMI", selecione o sistema operacional desejado para sua instância. Por exemplo, "Ubuntu Server 20.04 LTS".
6. Na tela "Choose Instance Type", selecione a opção "t2.micro", que é a opção gratuita da AWS.
7. Na tela "Configure Instance Details", mantenha as configurações padrão e clique em "Next".
8. Na tela "Add Storage", selecione a quantidade de armazenamento que você precisa. O plano gratuito da AWS oferece até 30GB de armazenamento gratuito.
9. Na tela "Add Tags", insira um nome para sua instância (por exemplo, "MeuProjeto").
10. Na tela "Configure Security Group", crie um novo grupo de segurança e abra as portas 22 (SSH), 80 (HTTP) e 443 (HTTPS).
11. Clique em "Review and Launch" e, em seguida, em "Launch".
12. Na tela "Select an existing key pair or create a new key pair", crie uma nova chave SSH e faça o download da chave privada. Essa chave será necessária para acessar a instância.
13. Depois que a instância estiver em execução, copie o endereço IP público da instância. Você precisará dele para se conectar via SSH.
14. Conecte-se à sua instância via SSH, usando o endereço IP público e a chave privada que você baixou no passo 12.
15. Instale o Postgres na instância usando o seguinte comando:
sql
Copy code
sudo apt-get update
sudo apt-get install postgresql postgresql-contrib
16. Configure o Postgres para aceitar conexões remotas editando o arquivo de configuração do Postgres:
bash
Copy code
sudo nano /etc/postgresql/12/main/postgresql.conf
Altere a linha #listen_addresses = 'localhost' para listen_addresses = '*' e salve o arquivo.
17. Ainda no mesmo arquivo, adicione a seguinte linha ao final:

python
Copy code
include_dir = '/etc/postgresql/12/main/conf.d'
18. Crie um novo arquivo de configuração para permitir conexões remotas:
bash
Copy code
sudo nano /etc/postgresql/12/main/conf.d/pg_hba.conf
Adicione a seguinte linha ao final do arquivo:

python
Copy code
host all all 0.0.0.0/0 md5
Salve o arquivo.
19. Reinicie o serviço Postgres:

Copy code
sudo service postgresql restart
20. Faça o deploy do seu projeto Spring Boot na instância. Isso pode ser feito usando o Git ou qualquer outra ferramenta de sua preferência.
21. Certifique-se de que o arquivo application.properties do seu projeto Spring Boot esteja configurado para se conectar ao banco de dados Postgres na sua instância da AWS.
22. Acesse seu aplicativo Spring Boot usando o endereço IP público da instância na porta configurada em seu projeto Spring Boot (geralmente a porta padrão é a 8080). Você pode testar a conexão ao seu aplicativo abrindo um navegador e acessando http://<IP-PUBLICO>:8080.

Pronto! Seu projeto Spring Boot e banco de dados Postgres estão hospedados gratuitamente na AWS. Lembre-se de monitorar o uso da instância, pois o plano gratuito da AWS tem limitações em relação a recursos disponíveis. Além disso, certifique-se de que sua instância está sempre atualizada e protegida contra possíveis vulnerabilidades.




------------------------
Preciso que, após criado o Postgres, seja criado o banco denominado "center-quiz"

Claro! Após instalar o Postgres na instância da AWS, você pode criar um novo banco de dados chamado "center-quiz" com os seguintes passos:

Conecte-se à sua instância via SSH, usando o endereço IP público e a chave privada que você baixou.
Acesse o prompt do Postgres digitando o seguinte comando:
Copy code
sudo -u postgres psql
No prompt do Postgres, crie o novo banco de dados digitando o seguinte comando:
sql
Copy code
CREATE DATABASE center_quiz;
Verifique se o banco de dados foi criado com sucesso digitando o seguinte comando:
Copy code
\list
Este comando exibirá a lista de todos os bancos de dados disponíveis. O nome do banco de dados "center_quiz" deve aparecer na lista.

Pronto! O banco de dados "center-quiz" foi criado com sucesso. Agora você pode configurar o arquivo application.properties do seu projeto Spring Boot para se conectar ao novo banco de dados na instância da AWS. 
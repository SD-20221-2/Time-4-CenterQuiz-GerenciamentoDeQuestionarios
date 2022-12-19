* Definição de sistemas distribuídos
    * O sistema no qual componentes localizados em uma rede de computadores comunicam e coordenam suas ações somente através de passagem de mensagens.
* Questões relacionadas à definição de sistemas distribuídos
    * Concorrência
    * Não há relógio global
    * Falhas são independentes
* Motivação
    * Comportamento de recursos
    * Elevada disponibilidade de serviço
    * extensibilidade
    * desempenho
    * suporte a organização distribuídas
    * Interação e coordenação entre smart devices
    * Comunicação
* Tipos de sistemas distribuídos
    * Computação distribuída de alto desempenho
    * Sistemas de informação distribuída
    * Sistemas Pervasivos
    * Computação Distribuída de Alto desempenho
        * Cluster computing
        * Grid computing
        * Cloud computing
    * Sistemas de Informação distribuída
        * Processamento de transações distribuídas (DTP)
        * Integração de aplicações empresariais (EAI)
    * Sistemas Pervasivos
        * Computação ubíqua e móvel
        * Sistemas sensíveis ao contexto
        * Redes de sensores
* Cluster computing
* Cloud computing
* Transações distribuídas
* Integração de aplicações empresariais (EAI)
* Principais elementos na definição de sistemas distribuídos
    * Infraestrutura para:
        * aplicações
        * Serviços
    * Estrutura física
        * Múltiplos computadores conectados em rede
    * Autonomia
    * Sem memória compartilhada
        * Sistema fracamente acoplado
    * Sem relógio global
    * Comunicação através de passagem de mensagens
        * tipicamente assíncronas
    * Cooperação e coordenação
* Exemplo de sistema distribuído:
    * a Web
    * Computação na nuvem (Cloud computing)
* Tendências
    * Rede em todos os lugares e a internet
    * Computação móvel e ubíqua
    * Computação em grade
    * Redes de sensores
    * Computação distribuída como uma utility (Computação em grade e em nuvem)
    * Model-Drivel Middleware
    * Adaptive and Reflective Middleware
    * IoT
    * Autonomic Computing
    * Systems of Systems
* Principais desafios em SD
    * Desafios que um desenvolvedor deve considerar no desenvolvimento de um sistema distribuído
    * Heterogeneidade
        * Middlewares: Camada de software que fornece abstração de programação e pode <u>mascarar heterogeneidade</u> de:
            * Redes de computadores
            * Máquinas
            * Sistemas operacionais
            * Linguagens de programação
            * Exemplos de middlewares:
                * Socket: midware que envia mensagem. Não é request-reply
                * RPC ou RMI são request-reply
    * Abertura (openness)
    * Segurança
    * Escalabilidade
        * Um sistema distribuído é escalável se ele permanece funcionando adequadamente à medida que novos componentes ou parâmetros (ex. usuários) são acrescentados.
        * é usado como critério de comparação entre sistemas.
    * Tratamento de falhas
    * Concorrência
        * Ocorre quando mais de um cliente tenta modificar uma estrutura de dados compartilhada na rede. O sistema deve mentar o componente compartilhado em estado consistente (sincronização)
    * Transparência
        * Tornar invisíveis as aplicações geradas pela distribuição a diferentes usuários.
    
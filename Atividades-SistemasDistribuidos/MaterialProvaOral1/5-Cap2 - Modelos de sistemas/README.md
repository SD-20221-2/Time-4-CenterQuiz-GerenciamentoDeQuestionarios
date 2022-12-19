* Introdução
    * Modelos físicos:
        * maneira mais explícita de descrever um sistema
        * Capturam a composição e hardware de um sistema, em termos dos coputadores e outros equipamentos, e suas redes de interconexão.
    * Modelos de arquitetura
        * descreve um sistema em termos das tarefas computacionais e de comunicação realizadas por seus elementos computacionais.
        * Exemplos:
            * Computação móvel
            * Computação ubíqua (Smart homes)
            * Computação na nuvem (Cloud computing)
    * Modelos fundamentais
        * perspectiva abstrata para examinar os aspectos individuais de um sistema distribuído.
<hr/>

* Modelos de arquitetura para sistemas distribuídos
    * Modelo cliente-servidor
    * Peer-to-peer
        * Todos os processos envolvidos fazem funções semelhantes, cooperando em pares, sem diferença entre clientes e servidores ou computadores.
        * todos os processos executam o mesmo progrrama e oferecem o mesmo conjunto de interfaces.
        * Exemplo:
            * Sistema de compartilhamento de arquivos BitTorrent
    * Objetos distribuídos
    * Componentes distribuídos
    * Baseados em eventos
* Paradigmas de comunicação
    * Comunicação entre processos (IPC - Interprocess Communication)
        * Message-passing
        * Socket
        * Multicast
    * Invocação remota
        * Envolve um sender (cliente) e um receiver (servidor)
        * Exemplos:
            * Request-reply
                * usado no HTTP
            * RPC (Remote Procedure Call)
                * procedimentos nos processo de computadores remotos podem ser chamados como se fossem procedimentos no espeço de endereçamento local.
                * Stub:
                    * Interface criada para isolar o programador dos detalhes referentes à comunicação através da rede.
            * RMI (Remote method Invocation)
                * Parecida com o RPC, mas voltada para objetos distribuídos.
                * Um objeto chamador pode invocar um método em um objeto potencialmente remoto.
                * Proxy(stub) / skeleton(objeto remoto): interface para receber as chamadas do cliente e reencaminhar ao objeto remoto.

                
* Camadas de software e hardware em SD
    1. Aplicações e serviços
    2. Middlewares
    3. Sistema operacional
    4. Hardware
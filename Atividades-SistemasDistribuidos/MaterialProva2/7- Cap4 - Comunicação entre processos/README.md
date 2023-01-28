* Características da comunicação entre processos
    * Comunicação síncrona e assíncrona
        * Síncrona:
            * processos origem e destino são sincronizados a cada mensagem. Send (cliente) e receive (servidor) são operações que causam bloqueio.
        * Assíncrona:
            * o Send (cliente) é não bloqueante. A mensagem é enviada para um buffer local. A transmissão da mensagem ocorre em paralelo com o processo origem.
            * tem a complexidade extra no proceso destino, para ler uma mensagem recebida fora do fluxo normal de execução.

* Sockets
    * Middleware que envia mensagem
    * Não é request-reply
    * Protocolos de comunicação:
        * TCP
        * UDP (comunicação por datagrama)
            * sem confirmações ou reenvio de mensagem
    * Vinculação de Sockets por porta e IP

* Representação externa de dados
    * marshalling
    
* Representação comum de dados

* Serialização de objetos em Java

* XML

* Referências a objetos remotos

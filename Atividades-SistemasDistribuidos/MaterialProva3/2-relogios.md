# Relógios lógicos
## Motivação
* Necessidade de medir o tempo com precisão
    * para saber a hora em que um evento ocorreu em um computador.
    * para fazer isso, precisamos sincronizar seu relógio com um relógio externo autoritativo.
* Algoritmos para sincronização de relógio úteis para:
    * Controle de concorrência baseado em timestamp ordenação (por exemplo, em transações distribuídas).
    * Autenticidade dos pedidos, por ex. Kerberos.
    * Objetivos de auditoria, por exemplo no cenário de comércio eletrônico.
    * Ordenação de eventos - também útil para manutenção da consistência dos dados (por ex, em replicação).
## Eventos e estados do processo.
...
<hr/><hr/><hr/>

#### Respostas do Chat GPT

* Qual o problema relacionado a relógios lógicos em sistemas distribuídos?
    * O problema relacionado a relógios lógicos em sistemas distribuídos é a necessidade de sincronização de eventos entre componentes remotos que podem estar rodando em máquinas diferentes e ter desvios de tempo entre si. Isso pode causar problemas de ordenação de eventos e pode afetar a consistência dos dados. Algoritmos como o Protocolo de Tempo de Berkeley (BFT) e o Protocolo de Tempo de Lamport são usados para sincronizar os relógios lógicos em sistemas distribuídos.
* Faça uma breve contextualização sobre relógios lógicos em sistemas distribuídos
    * Os relógios lógicos são uma técnica utilizada em sistemas distribuídos para sincronizar o tempo entre diferentes nós em uma rede. Eles permitem que os nós coordenem suas ações e mantenham uma visão consistente do tempo, o que é importante para aplicações que requerem ordenação de eventos, como por exemplo, a garantia da ordem de mensagens em um sistema de mensagens distribuído. Os relógios lógicos podem ser baseados em tempo real ou em versões lógicas, como por exemplo, o vector clock.
* Descreva sobre a noção de tempo em sistemas distribuídos
    * A noção de tempo em sistemas distribuídos é diferente da noção de tempo em sistemas locais, pois em sistemas distribuídos, não há um relógio único que possa ser usado como referência para todos os nós. Em vez disso, cada nó tem sua própria visão do tempo, que pode ser influenciada por fatores externos, como a latência na rede ou a capacidade de processamento. Isso pode levar a diferenças significativas entre a visão do tempo de cada nó, o que pode ser um problema para aplicações que requerem a ordenação de eventos. Para lidar com isso, são utilizados os relógios lógicos, que fornecem uma representação abstrata e coordenada do tempo entre os nós em uma rede distribuída.
* Quais são os algoritmos que dependem da sincronização do relógio para vários problemas de distribuição?
    * Existem vários algoritmos que dependem da sincronização do relógio para solucionar diferentes problemas de distribuição. Alguns exemplos incluem:
        * Algoritmos de eleição de líderes: esses algoritmos usam a sincronização do relógio para determinar qual nó deve ser o líder em uma rede distribuída.
        * Algoritmos de consenso: esses algoritmos usam a sincronização do relógio para garantir a consistência de dados em uma rede distribuída.
        * Algoritmos de ordenação de mensagens: esses algoritmos usam a sincronização do relógio para garantir que as mensagens sejam entregues na ordem correta em uma rede distribuída.
        * Algoritmos de sincronização de arquivos: esses algoritmos usam a sincronização do relógio para garantir que as versões mais recentes de arquivos sejam compartilhadas corretamente em uma rede distribuída.
    * Estes são apenas alguns exemplos de como a sincronização do relógio é importante para vários problemas de distribuição.
* O que é a distorção entre relógios de computador em um sistema distribuído?
    * A distorção entre relógios de computadores em um sistema distribuído refere-se à diferença entre a visão do tempo de cada nó na rede. Isso pode ser causado por fatores externos, como latência na rede, capacidade de processamento diferente entre nós, ou desvios de tempo devido à má qualidade do hardware do relógio. A distorção entre relógios pode causar problemas para aplicações que requerem a ordenação de eventos, pois pode haver diferenças significativas entre a ordem de eventos vista por diferentes nós na rede. Para lidar com a distorção entre relógios, é necessário usar técnicas de sincronização de relógios, como relógios lógicos, que fornecem uma representação abstrata e coordenada do tempo em uma rede distribuída.
* Defina desvio de relógio e derivação de relógio
    * Desvio de relógio é a diferença entre a hora real e a hora registrada por um relógio de computador. Isso pode ser causado por problemas de hardware, como a má qualidade dos componentes do relógio, ou problemas de software, como a falta de ajuste regular do relógio.
    * Derivação de relógio é a taxa de mudança da diferença entre a hora real e a hora registrada por um relógio de computador ao longo do tempo. Em outras palavras, é a velocidade com que o desvio de relógio muda. A derivação de relógio pode ser causada por problemas com a estabilidade do relógio, como a influência de temperatura e umidade, ou por problemas com a qualidade do hardware do relógio.
    * Em sistemas distribuídos, é importante controlar o desvio e a derivação de relógios para garantir a precisão da sincronização de relógios e evitar problemas de ordenação de eventos.
* Descreva sobre o tempo universal coordenado
    * O Tempo Universal Coordenado (UTC, na sigla em inglês) é um sistema de tempo de referência utilizado em todo o mundo. É uma variação do Tempo Universal (UT) e é mantido por um conjunto de relógios atômicos precisos que são sincronizados regularmente. O UTC é usado como padrão para a sincronização de relógios em aplicações críticas, como a navegação aérea e marítima, e para a distribuição de horários precisos na internet e em redes de telecomunicações.
    * O UTC é definido como sendo exatamente igual ao Tempo Internacional (TI), que é uma média do tempo solar. O TI é baseado em medições de tempo feitas em observatórios em todo o mundo, e o UTC é sincronizado com o TI por meio de correções periódicas. Isso permite que o UTC seja mantido com uma precisão extremamente alta, o que é importante para muitas aplicações críticas que dependem da precisão do tempo.
* Defina tempo lógico e faça relação com os relógios lógicos
    * Tempo Lógico é uma representação abstrata do tempo que é usada em sistemas distribuídos. É uma forma de sincronizar relógios em diferentes nós da rede, independentemente das diferenças entre os relógios físicos. Em vez de usar a hora real medida por relógios físicos, o tempo lógico é uma seqüência de números inteiros que representam o tempo de uma forma coordenada em toda a rede.
    * Relógios Lógicos são algoritmos que implementam o tempo lógico em sistemas distribuídos. Eles sincronizam as visões do tempo em diferentes nós da rede, fornecendo uma representação abstrata e coordenada do tempo. Isso permite que aplicações distribuídas possam ordenar eventos com precisão, independentemente das diferenças entre relógios físicos. Alguns exemplos de relógios lógicos incluem o Relógio Lógico de Lamport, o Relógio Lógico de vector clock e o Relógio Lógico de FIFO.
* Como funciona o relógio lógico de Lamport?
    * O Relógio Lógico de Lamport é um algoritmo de sincronização de relógios lógicos em sistemas distribuídos. Ele é baseado na ideia de que cada nó de uma rede distribuída pode incrementar seu próprio relógio lógico local quando ele gera um evento. Quando um nó envia uma mensagem para outro nó, ele inclui seu valor atual de relógio lógico no pacote de mensagem. Quando o nó destinatário recebe a mensagem, ele atualiza seu próprio relógio lógico local para o maior valor entre o seu próprio relógio lógico e o relógio lógico da mensagem mais recente recebida.
    * Assim, o relógio lógico de Lamport é uma forma de sincronizar relógios lógicos em diferentes nós da rede, garantindo que o tempo lógico seja coordenado em toda a rede. Isso permite que aplicações distribuídas possam ordenar eventos com precisão, independentemente das diferenças entre relógios físicos. Além disso, o relógio lógico de Lamport é fácil de implementar e é eficiente em termos de uso de recursos de rede.
* O que são relógios lógicos totalmente ordenados?
    * Relógios Lógicos Totalmente Ordenados são algoritmos de sincronização de relógios lógicos que fornecem uma ordenação total e única dos eventos em sistemas distribuídos. Eles asseguram que, para todo par de eventos, a ordem dos eventos é clara e inquestionável em toda a rede.
    * Em outras palavras, os relógios lógicos totalmente ordenados garantem que todos os nós da rede tenham a mesma visão da ordem dos eventos, independentemente das diferenças entre os relógios físicos. Isso permite que aplicações distribuídas possam realizar tarefas que dependem da ordenação dos eventos, como a replicação de banco de dados ou a resolução de conflitos em sistemas distribuídos.
    * Alguns exemplos de relógios lógicos totalmente ordenados incluem o Relógio Lógico de Vector Clock Totalmente Ordenado e o Relógio Lógico de FIFO Totalmente Ordenado.
* O que são relógios vetoriais?
    * Relógios Vetoriais são algoritmos de sincronização de relógios lógicos em sistemas distribuídos. Eles funcionam armazenando um vetor de inteiros para cada nó da rede, onde cada elemento do vetor representa o relógio lógico local para um nó específico. Quando um nó gera um evento, ele incrementa o elemento correspondente ao seu próprio nó no vetor. Quando um nó envia uma mensagem para outro nó, ele inclui seu vetor atual de relógio lógico no pacote de mensagem. O nó destinatário atualiza seu próprio vetor de relógio lógico local para o maior valor entre o seu próprio vetor e o vetor da mensagem mais recente recebida.
    * Os relógios vetoriais são úteis para sincronizar relógios lógicos em sistemas distribuídos, pois eles permitem que os eventos sejam ordenados de forma consistente em toda a rede. Além disso, eles são eficientes em termos de uso de recursos de rede e fáceis de implementar. No entanto, eles não fornecem uma ordenação total e única dos eventos, o que pode ser um problema para algumas aplicações distribuídas.






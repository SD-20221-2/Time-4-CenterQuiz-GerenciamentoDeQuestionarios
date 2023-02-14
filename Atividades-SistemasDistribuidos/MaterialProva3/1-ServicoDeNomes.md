# Serviço de nomes  - slides
## Motivação
* Evitar o uso de endereços físicos para a localização de componentes
#### Naming
* Localização de componentes por meio de nomes externos.
* Similar às páginas brancas de um catálogo telefônico.
#### Trading
* Localização de componentes por meio de características de serviço.
* Similar às páginas amarelas.

## Príncípios básicos
* Plataformas de middleware orientadas a objetos utilizam-se de referências de objetos para endereçar objetos servidores.
* É necessária uma forma de obter tais referências de objetos sem a necessidade de suposições sobre localização física.
* Um nome é uma sequência de cadeias de caracteres que pode ser "amarrada" a uma referência de objeto (nem binding).
* Um nome pode ser resolvido para se obter a referência de objeto correspondente.
* Podem haver muitos objetos servidores em um sistema de objetos distribuídos.
* Objetos servodores podem ter vários nomes.
* Isto leva a um grande número de "name bindings".
* O espaço de nomes deve ser organizado em uma hierarquia para evitar:
    * Conflito de nomes
    * baixo desempenho na resolução de nomes.
* Esta hierarquia pode ser construída através de contextos de nomes.
### Nomes compostos
* Nomes podem ser formados através da composição de vários nomes simples (strings).
* A sequência de componentes de um nome descreve um caminho através do grafo de contextos de nomes.
* Exemplo: Nome do objeto representando o time Chelsea: ("UEFA", "England", "Premiere", "Chelsea")
### Servidor de nomes
* "Amarrações" de nomes (a referências de objetos) são administradas por servidores de nomes.
* Nem todo objeto servidor precisa de um nome.
* Alguns objetos servidores podem ter vários nomes.
* Os servidores de nomes devem armazenar as amarrações de nomes persistentemente.
* Os servidores de nomes devem ser "calibrados" com vistas à eficiência na resolução de nomes.
* O próprio servidor de nomes pode ser distribuído.
#### Servidor de nomes de CORBA
* Provê suporte para a amarração de nomes (name binding) a referências de objetos CORBA.
* Escopo de um nome: contexto de nomes.
* Múltiplos nomes podem ser definidos para um mesma referência de objeto.
* Nem todas as referências de objetos precisam de nomes.
#### Nomes em CORBA
* Nomes de objetos são compostos por nomes simples
* Nomes simples (componentes): pares valor-tipo.
    * O atributo valor é usado para resolução de nomes.
    * O atributo tipo é usado para fornecer informação a respeito do papel do objeto.
### Interfaces IDL do serviço de nomes
* O serviço de nomes é especificado através de duas interfaces:
    * <b>NamingContext</b> define operações para ligar objetos a nomes e para a resolução de nomes
    * <b>BindingIterator</b> define operações para iterar em um conjunto de nomes definido em um dado contexto de nomes.
### O serviço de nomes em Java RMI: Registry
* Versão simplificada do serviço de nomes de CORBA
* Sem suporte para nomes compostos.
* Restrição de segurança: ligações de nomes não podem ser criadas a partir de máquinas remotas.
* Deve haver um registry em cada máquina.
* Diferentes registries devem estar integrados dentro de um espaço de nomes federado.
### Avaliação
* A ausência de nomes hierárquicos aumenta o número de operações remotas necessárias para a resolução de nomes.
* A descoberta do registry raiz não é necessariamente transparente de localização.
### Limitações do serviço de nomes
* Em todas as abordagens mencionadas:
    * Os clientes sempre devem identificar o servidor específico por meio de um nome.
* Inapropriado se o cliente apenas deseja usar um serviço com certas características e qualidades, mas não sabe (ou não precisa saber) em que servidor:
    * Comércio eletrônico
    * Vídeo sob demanda
    * Venda automática de bilhetes de cinema

<hr/><hr/><hr/>

#### Respostas do Chat GPT
* Descreva sobre serviço de nomes em sistemas distribuídos
    * O serviço de nomes em sistemas distribuídos é uma camada de abstração que permite aos componentes de um sistema distribuído localizar uns aos outros de forma transparente. Ele funciona como um diretório de nomes que mapeia nomes de recursos (como endereços de IP) para identificadores únicos (como URLs). O serviço de nomes permite que os componentes do sistema se comuniquem sem se preocupar com detalhes técnicos como endereços IP dinâmicos ou falhas de rede. Exemplos de serviços de nomes incluem o Domain Name System (DNS) e o serviço de nomes do sistema de arquivos distribuídos (DFS).
* como evitar o uso de endereços físicos para a localização de componentes
    * O uso de endereços físicos para localização de componentes em sistemas distribuídos pode ser evitado através do uso de serviços de nomes. Em vez de usar endereços físicos (como endereços IP), os componentes se registram com o serviço de nomes e obtêm um nome lógico que pode ser usado para se comunicar com outros componentes. Quando um componente precisa se comunicar com outro, ele envia uma solicitação ao serviço de nomes, que retorna o endereço físico correspondente. Dessa forma, o componente pode se comunicar com o outro componente sem precisar conhecer o endereço físico diretamente. Isso é útil porque torna o sistema mais flexível e tolerante a falhas, já que a mudança de endereços físicos não afeta a capacidade dos componentes de se comunicarem uns com os outros.
* Qual a diferença entre Naming e Trading?
    * Naming e Trading são conceitos diferentes em sistemas distribuídos.
    * Naming se refere ao processo de atribuir nomes a recursos em um sistema distribuído, de forma a tornar mais fácil para os componentes do sistema encontrar uns aos outros. O serviço de nomes é responsável por gerenciar a atribuição de nomes e mantê-los atualizados quando houver mudanças de endereço ou outros detalhes.
    * Trading, por outro lado, se refere ao processo de negociação de recursos entre componentes em um sistema distribuído. O trading envolve a negociação de informações sobre recursos, como sua disponibilidade, capacidade e requisitos, para que os componentes possam selecionar os melhores recursos para suas necessidades. O trading é uma forma de garantir que os recursos sejam alocados de maneira eficiente e equilibrada em um sistema distribuído.
    * Em resumo, o Naming é responsável por tornar mais fácil para os componentes encontrar uns aos outros, enquanto o Trading é responsável por negociar o uso de recursos entre componentes.
* existe uma diferença entre o serviço de nomes e o DNS?
    * Sim, existe uma diferença entre o serviço de nomes e o Domain Name System (DNS).
    * O serviço de nomes é um conceito mais amplo que se aplica a sistemas distribuídos em geral. É um mecanismo que permite que os componentes de um sistema se registrem e obtenham informações sobre outros componentes usando nomes lógicos em vez de endereços físicos.
    * O DNS, por outro lado, é um serviço de nomes específico para a Internet que mapeia nomes de domínio (como www.example.com) para endereços IP. O DNS é amplamente utilizado na Internet para permitir que os usuários acessem sites da web usando nomes de domínio em vez de endereços IP.
    * Em resumo, o serviço de nomes é um conceito mais amplo aplicável a sistemas distribuídos em geral, enquanto o DNS é um exemplo específico de serviço de nomes aplicável à Internet.
* descreva brevemente sobre contextos de nomes, nomes compostos e servidor de nomes
    * Contextos de nomes: Em sistemas distribuídos, os contextos de nomes são estruturas de dados que permitem agrupar recursos em categorias lógicas. Cada contexto de nomes é uma coleção de nomes atribuídos a recursos, permitindo que os componentes do sistema encontrem esses recursos usando nomes lógicos. Os contextos de nomes também podem ser aninhados, permitindo uma hierarquia de categorias e subcategorias de recursos.
    * Nomes compostos: Em sistemas distribuídos, os nomes compostos são nomes formados por várias partes, cada uma identificando um contexto de nomes específico. Os nomes compostos permitem que os componentes encontrem recursos em uma estrutura de hierarquia de contextos de nomes. Por exemplo, um nome composto pode ser formado por uma parte que identifica um contexto de nomes de uma região geográfica, seguido por uma parte que identifica um contexto de nomes de uma organização, seguido por uma parte que identifica um recurso específico.
    * Servidor de nomes: O servidor de nomes é o componente responsável por gerenciar a atribuição de nomes e manter informações atualizadas sobre os recursos em um sistema distribuído. Os componentes se registram com o servidor de nomes, fornecendo informações sobre seus recursos, e o servidor de nomes mantém essas informações atualizadas e as disponibiliza a outros componentes quando solicitadas. O servidor de nomes permite que os componentes encontrem uns aos outros usando nomes lógicos, tornando mais fácil para eles se comunicarem e colaborarem.
* no artigo "Implementing Remote Procedure Calls", de autoria de Andrew Birrell e Bruce Nelson, descreve-se sobre serviço de nomes?
    * Sim, o artigo "Implementing Remote Procedure Calls" de Andrew Birrell e Bruce Nelson descreve o uso de serviços de nomes em sistemas distribuídos.
    * No artigo, os autores discutem a implementação de chamadas de procedimentos remotos (RPCs), que permitem que os componentes de um sistema distribuído se comuniquem e colaborem. Um dos desafios enfrentados pelos autores é como os componentes devem encontrar uns aos outros para se comunicarem. Eles apresentam a ideia de um serviço de nomes, que permite que os componentes registrem informações sobre seus recursos e encontrem outros recursos usando nomes lógicos em vez de endereços físicos.
    * O artigo explica a arquitetura do serviço de nomes e como ele pode ser usado para resolver nomes lógicos em endereços físicos. Além disso, os autores discutem como o serviço de nomes pode ser escalável e tolerante a falhas, garantindo a disponibilidade e a confiabilidade do sistema distribuído.
    * Em resumo, o artigo "Implementing Remote Procedure Calls" apresenta o uso de serviços de nomes como uma parte importante da arquitetura de um sistema distribuído e discute como eles podem ser implementados de forma eficiente e escalável.
 
* qual a nomenclatura utilizada para descrever o serviço de nomes no artigo de Birrell?
    * No artigo "Implementing Remote Procedure Calls" de Andrew Birrell e Bruce Nelson, a nomenclatura utilizada para descrever o serviço de nomes é "Name Server". Os autores apresentam o uso de um servidor de nomes como uma parte importante da arquitetura de um sistema distribuído, permitindo que os componentes registrem informações sobre seus recursos e encontrem outros recursos usando nomes lógicos em vez de endereços físicos.
    * Os autores descrevem o servidor de nomes como uma entidade centralizada que mantém informações sobre os recursos disponíveis no sistema distribuído. Eles explicam como o servidor de nomes pode ser usado para resolver nomes lógicos em endereços físicos e como ele pode ser escalável e tolerante a falhas para garantir a disponibilidade e a confiabilidade do sistema distribuído.
    * Em resumo, no artigo de Andrew Birrell e Bruce Nelson, o serviço de nomes é descrito como um "Name Server", que é responsável por permitir que os componentes de um sistema distribuído se encontrem usando nomes lógicos.
* Me informe sobre o serviço de nomes de CORBA
    * O Common Object Request Broker Architecture (CORBA) é uma arquitetura padrão para a construção de sistemas distribuídos e o serviço de nomes é uma parte importante dessa arquitetura.
    * O serviço de nomes de CORBA é responsável por fornecer uma forma de localização de objetos remotos no sistema distribuído. Ele permite que os objetos sejam identificados por um nome lógico, chamado de Identificador de Nome, que pode ser usado para localizar o objeto em si. Isso é útil porque permite que os objetos possam ser encontrados e acessados independentemente de seu endereço físico.
    * O serviço de nomes de CORBA implementa um diretório centralizado que armazena informações sobre os objetos registrados. Cada objeto registra seu nome e suas informações de endereçamento no diretório. Quando um objeto precisa acessar outro objeto, ele consulta o diretório usando o nome lógico para obter informações de endereçamento.
    * O serviço de nomes de CORBA é baseado na Interface Definition Language (IDL) e é fornecido por meio de uma interface padrão, chamada Naming Service, que é implementada pelo objeto servidor de nomes. Essa interface permite que os objetos sejam registrados e localizados de forma transparente, sem que seja necessário conhecer detalhes da implementação do serviço de nomes.
    * Em resumo, o serviço de nomes de CORBA é uma parte importante da arquitetura de CORBA que permite que os objetos remotos sejam localizados usando nomes lógicos em vez de endereços físicos. Ele implementa um diretório centralizado que armazena informações sobre os objetos registrados e fornece uma interface padrão para acessar esse diretório.
* quais as limitações do serviço de nomes?
    * O serviço de nomes pode ter algumas limitações, incluindo:
        * Escalabilidade: O serviço de nomes pode não ser capaz de lidar com altos volumes de solicitações ou muitos objetos registrados, o que pode levar a lentidão e instabilidade.
        * Falta de segurança: O serviço de nomes pode não ser seguro, permitindo acesso não autorizado a objetos registrados ou a alterações não autorizadas de registros.
        * Complexidade de configuração: Configurar e gerenciar um serviço de nomes pode ser complexo e exigir conhecimento técnico avançado.
        * Dependência de rede: O serviço de nomes pode ser afetado por problemas de rede, como perda de pacotes ou latência, o que pode resultar em falhas no acesso a objetos registrados.
        * Limitado ao sistema: O serviço de nomes pode estar limitado a um único sistema ou grupo de sistemas, o que pode dificultar o acesso a objetos registrados em outras redes ou sistemas.
    * É importante levar essas limitações em consideração ao planejar e implementar um serviço de nomes, para garantir que ele atenda às suas necessidades de forma confiável e escalável.
* Qual a diferença do DNS para o RMI Registry?
    * DNS (Domain Name System) e RMI (Remote Method Invocation) são tecnologias diferentes que servem propósitos distintos.
    * DNS:
        * É um sistema de nomes de domínio distribuído utilizado na Internet para traduzir nomes de host em endereços IP.
        * É amplamente utilizado e padronizado, tornando-o uma escolha comum para resolução de nomes em sistemas distribuídos.
        * É projetado para lidar com altos volumes de solicitações e com grandes quantidades de informações de nomes registrados.
RMI:
    * É uma tecnologia Java que permite a invocação de métodos em objetos remotos.
    * Permite que objetos Java sejam compartilhados e invocados entre diferentes JVMs (Java Virtual Machines) em diferentes sistemas.
    * Utiliza o registro RMI para permitir que objetos remotos sejam localizados e invocados.
    * Em resumo, o DNS é um sistema de nomes distribuído utilizado para resolução de nomes na Internet, enquanto o RMI é uma tecnologia Java que permite a invocação de objetos remotos. Ambas as tecnologias são úteis em sistemas distribuídos, mas o DNS é usado para resolução de nomes em uma ampla gama de sistemas, enquanto o RMI é usado especificamente para a invocação de objetos remotos na plataforma Java.
* RMI é exclusiva do Java?
    * Não, o Remote Method Invocation (RMI) não é exclusivo do Java. RMI é um conceito genérico que pode ser implementado em várias linguagens de programação diferentes. No entanto, a implementação específica de RMI no Java é amplamente utilizada e fornece uma maneira conveniente e fácil de invocar métodos em objetos remotos na plataforma Java. Outras linguagens, como C++ e Python, também possuem suas próprias implementações de RMI ou equivalentes.
* qual a definição de serviço de diretório?
    * Um serviço de diretório é um tipo de serviço de nomes que fornece uma estrutura centralizada de dados para armazenar e recuperar informações de identificação de recursos em sistemas distribuídos. Essas informações incluem informações sobre objetos, usuários, grupos e aplicativos, entre outros. O serviço de diretório usa uma estrutura hierárquica para organizar informações, permitindo que informações sejam facilmente localizadas e recuperadas. Alguns exemplos de serviços de diretório incluem o Microsoft Active Directory, o Novell eDirectory e o OpenLDAP. O serviço de diretório é amplamente utilizado para autenticação de usuários, gerenciamento de identidade e controle de acesso a recursos em sistemas distribuídos.

<hr/><hr/><hr/>

# Livro
enquanto o RMI é uma tecnologia Java que permite a invocação de objetos remotos. Ambas as tecnologias são úteis em sistemas distribuídos, mas o DNS é usado para resolução de nomes em uma ampla gama de sistemas, enquanto o RMI é usado especificamente para a invocação de objetos remotos na plataforma Java.
* RMI é exclusiva do Java?
    * Não, o Remote Method Invocation (RMI) não é exclusivo do Java. RMI é um conceito genérico que pode ser implementado em várias linguagens de programação diferentes. No entanto, a implementação específica de RMI no Java é amplamente utilizada e fornece uma maneira conveniente e fácil de invocar métodos em objetos remotos na plataforma Java. Outras linguagens, como C++ e Python, também possuem suas próprias implementações de RMI ou equivalentes.
* qual a definição de serviço de diretório?
    * Um serviço de diretório é um tipo de serviço de nomes que fornece uma estrutura centralizada de dados para armazenar e recuperar informações de identificação de recursos em sistemas distribuídos. Essas informações incluem informações sobre objetos, usuários, grupos e aplicativos, entre outros. O serviço de diretório usa uma estrutura hierárquica para organizar informações, permitindo que informações sejam facilmente localizadas e recuperadas. Alguns exemplos de serviços de diretório incluem o Microsoft Active Directory, o Novell eDirectory e o OpenLDAP. O serviço de diretório é amplamente utilizado para autenticação de usuários, gerenciamento de identidade e controle de acesso a recursos em sistemas distribuídos.

<hr/><hr/><hr/>

# Livro
## 1. Introdução - pg 566
* A atribuição de nomes é um problema facilemente desprezado, mas com certeza fundamental no projeto de sistemas distribuídos.
    * Nomes facilitam a comunicação e o compartilhamento de recursos.
    * Ex:
        * é necessário um nome na forma de um URL para acessar uma página WEB.
        * Endereços de e-mail.
        * DNS (Domain Name Service)
        * GNS (Global Name Service)
        * X.500
### Nomes, endereços e outros atributos.
* Needham [1993]
    * Nomes puros:
        * são simplesmente padrões de bits não interpretados.
    * Nomes não puros:
        * contêm informações sobre o objeto que nomeiam; em particular, podem conter informações sobre a localização do objeto.
* Binding (vínculo): Associação entre um nome e um objeto.
* <b>DNS</b>:
    * faz o mapeamento de nomes de domínio Internet para atributos de um computador: seu endereço IP, o tipo de entrada (por exemplo, uma referência a uma serviço de correio eletrônico ou a outro computador) e, por exemplo, o período de tempo durante o qual a entrada desse computador permanecerá válida.
* <b>X500</b>
    * serviço de diretório que pode ser usado para fazer o mapeamento do nome de uma pessoa em atributos, incluindo ser endereço de e-mail e o número de telefone.
* <b>CORBA</b>
    * Serviço de nomes (Naming Service) e Serviço de negociação (Trading Service).
    * Serviço de nomes:
        * faz o mapeamento do nome de um objeto remoto para sua referência de objeto remota.
    * Serviço de negociação:
        * faz o mesmo mapeamento junto a um número arbitrário de atributos descrevendo o objeto em termos inteligíveis por usuários humanos.

* <b>URI</b> - Uniform Resource Identifiers
    * Surgiram da necessidade de identificar recursos Web e outros recursos de Internet, como as caixas de corrêncio eletrônico.
    * Um objetivo importante era identificar esses recursos de maneira coerente, de modo que todos pudessem ser processados por um software comum, como os navagadores.

* <b>URL</b> - Uniform Resource Locators
    * é frequentemente usado para URIs que fornecem informações de localização e especificam o método para acessar um recurso, incluindo os URLs http.

## 2. Serviços de nomes e o DNS (Domain Name System) - pg 569

## 3. Serviços de diretório - pg 584
## 4. Estudo de caso: Global Name Service - pg 585
## 5. Estudo de caso: X.500 Directory Service - pg 588
## 6. Resumo - pg 592
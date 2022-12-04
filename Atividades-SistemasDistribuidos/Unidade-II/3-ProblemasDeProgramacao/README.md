PROGRAMAÇÃO DISTRIBUÍDA
PROF. SÉRGIO T. CARVALHO

## Solução com Sockets, RMI E RPC, PARA CADA EXERCICIO.

### Analise cada enunciado e separe o cliente do servidor. Por exemplo, para o exercício
no. 1, crie um programa cliente que realize a entrada de dados ( nome, cargo e salário
de um funcionário ) e os envie para um programa servidor, o qual deverá então realizar
a operação desejada, ou seja, o cálculo do reajuste salarial. O programa servidor deve,
então, enviar o resultado para o cliente. 

1. Faça um programa que leia o nome, o cargo e o salário de um funcionário e escreva
seu salário reajustado. Se o cargo do funcionário for operador, ele deverá receber
um reajuste de 20%, se for programador, ele deverá receber um reajuste de 18%. O
programa deve escrever o nome do funcionário e seu salário reajustado.
<hr/>

2. Faça um programa que leia o nome, o sexo e a idade de uma pessoa e determine se
a pessoa já atingiu a maioridade sabendo-se que: as pessoas do sexo masculino
atingem a maioridade aos 18 anos e as pessoas do sexo feminino atingem a
maioridade aos 21 anos. O programa deve escrever o resultado encontrado.
<hr/>

3. Escreva um programa que leia as três notas (N1, N2 e N3) de um aluno de
Faculdade e escreva se o mesmo foi aprovado ou reprovado. Considere as regras: se
a média aritmética M, entre N1 e N2, for maior ou igual a 7,0, o aluno está
aprovado; se a média aritmética M entre N1 e N2 for maior que 3,0 e menor que
7,0, o aluno deve fazer a N3. O aluno é aprovado se a média aritmética entre M e
N3 for maior ou igual a 5,0. 
<hr/>

4. Tendo como dados de entrada a altura e o sexo de uma pessoa, construa um
programa que calcule seu peso ideal, utilizando as seguintes fórmulas:
- para homens: (72.7 * altura) – 58;
- para mulheres (62.1 * altura) – 44.7. 
<hr/>

5. Elabore um programa que leia a idade de um nadador e escreva em qual classificação
o mesmo se enquadra, conforme as seguintes categorias:
Categoria Idade
infantil A 5 - 7 anos
infantil B 8-10 anos
juvenil A 11-13 anos
juvenil B 14-17 anos
adulto maiores de 18 anos
<hr/>

6. Faça um programa que leia o nome, nível, salário bruto e número de dependentes de
um funcionário. A partir destes dados, o programa deve calcular e escrever o salário
Prof. Sérgio Carvalho 
líquido do funcionário, juntamente com o seu nome e seu nível. Para o cálculo do
salário líquido considere que: 
 para o nível "A", o desconto é de 3% se o funcionário não tiver
dependentes e 8% se o funcionário tiver dependentes;
 para o nível "B", o desconto é de 5% se o funcionário não tiver
dependentes e 10% se o funcionário tiver dependentes;
 para o nível "C", o desconto é de 8% se o funcionário não tiver
dependentes e 15% se o funcionário tiver dependentes;
 para o nível "D", o desconto é de 10% se o funcionário não tiver
dependentes e 17% se o funcionário tiver dependentes.
<hr/>

7. Elabore um programa que escreva se um funcionário já pode se aposentar, a partir
da leitura de sua idade e tempo de serviço. Considere que um funcionário só pode se
aposentar se todas as condições abaixo forem satisfeitas:
 ter no mínimo 65 anos de idade;
 ter trabalhado, no mínimo, 30 anos;
 ter no mínimo 60 anos de idade e ter trabalhado no mínimo 25
anos
<hr/>

8. Um banco concederá um crédito especial aos seus clientes, variável com o saldo
médio no último ano. Faça um programa que leia o saldo médio de um cliente e
calcule o valor do crédito de acordo com a tabela abaixo. O programa deve mostrar
uma mensagem informando o saldo médio e o valor do crédito. 
Saldo médio Percentual de Crédito
de 0 a 200 nenhum crédito
de 201 a 400 20% do valor do saldo médio 
de 401 a 600 30% do valor do saldo médio 
acima de 601 40% do valor do saldo médio
<hr/>

9. Escreva uma classe que encapsule uma carta de baralho, com um valor que
represente o valor da carta, de um (ás) a treze (rei), e outro valor correspondente ao
naipe (1 = ouros, 2 = paus, 3 = copas e 4 = espadas). Escreva nessa classe um
método que imprima o nome da carta por extenso. Escreva ainda um programa em
Java que instancie alguns objetos desta classe.
import xmlrpc.client

nome = input("Digite o nome: ")
nivel = input("Digite o nível (A, B, C ou D): ")
salarioBruto = input("Digite o salário bruto: ")
numeroDependentes = input("Digite o número de dependentes: ")

with xmlrpc.client.ServerProxy("http://localhost:8080/") as proxy:

    print("Olá ", nome
          , "\nVocê pertence ao nível \"", nivel
          , "\" e seu salário líquido é = R$ ", proxy.reajustar(nivel, float(salarioBruto), int(numeroDependentes)))
import xmlrpc.client

nome = input("Digite o nome: ")
n1 = input("Digite a n1: ")
n2 = input("Digite a n2: ")
n3 = input("Digite a n3: ")

with xmlrpc.client.ServerProxy("http://localhost:8080/") as proxy:
    print("Olá ", nome)
    if (proxy.verificarAprovado(float(n1), float(n2), float(n3)) == True):
        print("\nvocê está aprovado!")
    else:
        print("\nvocê não está aprovado!")
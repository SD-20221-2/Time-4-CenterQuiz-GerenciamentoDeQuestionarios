import xmlrpc.client

nome = input("Digite o nome: ")
saldo = input("Digite o saldo: ")

with xmlrpc.client.ServerProxy("http://localhost:8080/") as proxy:
    print("Olá ", nome)
    print("\n", proxy.credito(float(saldo)))
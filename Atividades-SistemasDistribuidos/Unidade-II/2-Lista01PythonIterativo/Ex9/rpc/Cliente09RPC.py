import xmlrpc.client

valor = input("Digite o valor da carta: ")
naipe = input("Digite o naipe da carta: ")

with xmlrpc.client.ServerProxy("http://localhost:8080/") as proxy:
    print("\n", proxy.nomeCarta(int(valor), int(naipe)))
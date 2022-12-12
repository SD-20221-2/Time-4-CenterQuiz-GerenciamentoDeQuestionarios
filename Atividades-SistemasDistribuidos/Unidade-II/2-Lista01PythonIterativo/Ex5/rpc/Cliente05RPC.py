import xmlrpc.client

nome = input("Digite o nome: ")
idade = input("Digite a idade: ")

with xmlrpc.client.ServerProxy("http://localhost:8080/") as proxy:
    print("Olá ", nome, "\nVocê se enquadra na categoria \"" + proxy.obterCategoria(int(idade))
          + "\" de nadadores")
import xmlrpc.client

nome = input("Digite o nome: ")
sexo = input("Digite o sexo: ")
idade = input("Digite a idade: ")

with xmlrpc.client.ServerProxy("http://localhost:8080/") as proxy:
    print("Olá ", nome)
    isTrue = proxy.verificar(sexo, int(idade))
    if (isTrue == True):
        print("\nvocê atingiu a maioridade!")
    else:
        print("\nvocê não atingiu a maioridade!")
import xmlrpc.client

nome = input("Digite o nome: ")
idade = input("Digite a idade: ")
tempoServico = input("Digite o tempo de serviço: ")

with xmlrpc.client.ServerProxy("http://localhost:8080/") as proxy:
    print("Olá ", nome)
    if (proxy.verificar(int(idade), int(tempoServico)) == True):
        print("\nvocê pode se aponsentar!")
    else:
        print("\nvocê ainda não pode se aposentar!")
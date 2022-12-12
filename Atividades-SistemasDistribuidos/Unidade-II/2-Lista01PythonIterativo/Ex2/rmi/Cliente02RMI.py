import Pyro4

class Cliente02RMI(object):
    # startHosts()
    nome = input("Digite o nome: ")
    sexo = input("Digite o sexo: ")
    idade = input("Digite a idade: ")

    obj = Pyro4.core.Proxy('PYRO:Servidor02RMI@' + 'localhost' + ':8000')

    print("Olá ", nome)
    isTrue = obj.verificar(sexo, int(idade))
    if (isTrue == True):
        print("\nvocê atingiu a maioridade!")
    else:
        print("\nvocê não atingiu a maioridade!")
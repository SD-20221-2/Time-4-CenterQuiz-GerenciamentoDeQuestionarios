import Pyro4

class Cliente02RMI(object):
    # startHosts()
    nome = input("Digite o nome: ")
    n1 = input("Digite a n1: ")
    n2 = input("Digite a n2: ")
    n3 = input("Digite a n3: ")

    obj = Pyro4.core.Proxy('PYRO:Servidor03RMI@' + 'localhost' + ':8000')

    isTrue = obj.verificarAprovado(float(n1), float(n2), float(n3))
    print("Olá ", nome)
    if (isTrue == True):
        print("\nvocê está aprovado!")
    else:
        print("\nvocê não está aprovado!")
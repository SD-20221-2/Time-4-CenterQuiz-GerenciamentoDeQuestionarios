import Pyro4

class Cliente08RMI(object):
    # startHosts()
    nome = input("Digite o nome: ")
    saldo = input("Digite o saldo: ")

    obj = Pyro4.core.Proxy('PYRO:Servidor08RMI@' + 'localhost' + ':8000')

    print("Olá ", nome)
    print("\n", obj.credito(float(saldo)))
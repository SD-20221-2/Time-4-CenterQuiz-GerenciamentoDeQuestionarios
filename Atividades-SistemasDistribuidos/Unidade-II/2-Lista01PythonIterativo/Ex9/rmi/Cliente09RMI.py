import Pyro4

class Cliente09RMI(object):
    # startHosts()

    valor = input("Digite o valor da carta: ")
    naipe = input("Digite o naipe da carta: ")

    obj = Pyro4.core.Proxy('PYRO:Servidor09RMI@' + 'localhost' + ':8000')

    print("Nome da carta: ", obj.nomeCarta(int(valor), int(naipe)))
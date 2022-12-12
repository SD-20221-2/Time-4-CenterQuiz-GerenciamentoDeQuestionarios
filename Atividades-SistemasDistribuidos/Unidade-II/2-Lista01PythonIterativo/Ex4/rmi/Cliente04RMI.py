import Pyro4

class Cliente04RMI(object):
    # startHosts()
    nome = input("Digite o nome: ")
    altura = input("Digite a altura: ")
    sexo = input("Digite o sexo: ")

    obj = Pyro4.core.Proxy('PYRO:Servidor04RMI@' + 'localhost' + ':8000')

    print("Olá ", nome, "\nSeu peso ideal é ", obj.calcular(float(altura), sexo))
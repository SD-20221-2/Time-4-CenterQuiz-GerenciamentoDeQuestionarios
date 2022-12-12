import Pyro4

class Cliente04RMI(object):
    # startHosts()
    nome = input("Digite o nome: ")
    idade = input("Digite a idade: ")

    obj = Pyro4.core.Proxy('PYRO:Servidor05RMI@' + 'localhost' + ':8000')

    print("Olá ", nome, "\nVocê se enquadra na categoria \"" + obj.obterCategoria(int(idade))
          + "\" de nadadores")
import Pyro4

class Cliente04RMI(object):
    # startHosts()
    nome = input("Digite o nome: ")
    idade = input("Digite a idade: ")
    tempoServico = input("Digite o tempo de serviço: ")

    obj = Pyro4.core.Proxy('PYRO:Servidor07RMI@' + 'localhost' + ':8000')

    print("Olá ", nome)
    if (obj.verificar(int(idade), int(tempoServico)) == True):
        print("\nvocê pode se aponsentar!")
    else:
        print("\nvocê ainda não pode se aposentar!")
import Pyro4

class Cliente01RMI(object):
    # startHosts()
    nome = input("Digite o nome: ")
    cargo = input("Digite o cargo: ")
    salario = input("Digite o salário: ")

    obj = Pyro4.core.Proxy('PYRO:Servidor01RMI@' + 'localhost' + ':8000')

    print("Olá ", nome, "\nseu salário reajustado é: R$ ", obj.reajustar(cargo, float(salario)))
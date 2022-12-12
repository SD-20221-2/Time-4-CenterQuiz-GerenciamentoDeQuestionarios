import Pyro4

class Cliente04RMI(object):
    # startHosts()
    nome = input("Digite o nome: ")
    nivel = input("Digite o nível (A, B, C ou D): ")
    salarioBruto = input("Digite o salário bruto: ")
    numeroDependentes = input("Digite o número de dependentes: ")

    obj = Pyro4.core.Proxy('PYRO:Servidor06RMI@' + 'localhost' + ':8000')

    print("Olá ", nome
          , "\nVocê pertence ao nível \"", nivel
          , "\" e seu salário líquido é = R$ ",
          obj.reajustar(nivel, float(salarioBruto), int(numeroDependentes)))
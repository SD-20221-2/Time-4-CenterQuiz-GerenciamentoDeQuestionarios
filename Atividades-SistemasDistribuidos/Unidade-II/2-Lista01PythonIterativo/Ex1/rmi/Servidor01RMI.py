import Pyro4

@Pyro4.expose
class Servidor01RMI(object):

    def reajustar(cargo, salario):
        if cargo.lower() == "operador":
            return salario * 1.2
        elif (cargo.lower() == "programador"):
            return salario * 1.18
        else:
            return salario


daemon = Pyro4.Daemon.serveSimple({Servidor01RMI: 'Servidor01RMI', }, host="localhost", port=8000, ns=False, verbose=True)
ns = Pyro4.locateNS()
server = Servidor01RMI()
uri = daemon.register(server)
ns.register('calculator_Server', uri)
print('Calculator Server ...')
daemon.requestLoop()
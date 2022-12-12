import Pyro4

@Pyro4.expose
class Servidor06RMI(object):

    def reajustar(self, nivel, salarioBruto, numeroDependentes):
        if (nivel == "A"):
            if (numeroDependentes == 0):
                return salarioBruto * (1 - 0.03)
            else:
                return salarioBruto * (1 - 0.08)
        elif (nivel == "B"):
            if (numeroDependentes == 0):
                return salarioBruto * (1 - 0.05)
            else:
                return salarioBruto * (1 - 0.1)
        elif (nivel == "C"):
            if (numeroDependentes == 0):
                return salarioBruto * (1 - 0.08)
            else:
                return salarioBruto * (1 - 0.15)
        elif (nivel == "D"):
            if (numeroDependentes == 0):
                return salarioBruto * (1 - 0.1)
            else:
                return salarioBruto * (1 - 0.17)
        else:
            return salarioBruto

daemon = Pyro4.Daemon.serveSimple({Servidor06RMI: 'Servidor06RMI', }, host="localhost", port=8000, ns=False, verbose=True)
ns = Pyro4.locateNS()
server = Servidor06RMI()
uri = daemon.register(server)
ns.register('serverRMI', uri)
print('Server ...')
daemon.requestLoop()
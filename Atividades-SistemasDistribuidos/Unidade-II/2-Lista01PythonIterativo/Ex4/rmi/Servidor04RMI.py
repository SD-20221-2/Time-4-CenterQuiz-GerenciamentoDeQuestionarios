import Pyro4

@Pyro4.expose
class Servidor04RMI(object):

    def calcular(self, altura, sexo):
        if (sexo == "M"):
            return (72.7 * altura) - 58
        else:
            return (62.1 * altura) - 44.7

daemon = Pyro4.Daemon.serveSimple({Servidor04RMI: 'Servidor04RMI', }, host="localhost", port=8000, ns=False, verbose=True)
ns = Pyro4.locateNS()
server = Servidor04RMI()
uri = daemon.register(server)
ns.register('serverRMI', uri)
print('Server ...')
daemon.requestLoop()
import Pyro4

@Pyro4.expose
class Servidor07RMI(object):

    def verificar(self, idade, tempoServico):
        if (idade >= 65
            and tempoServico >= 30
            and idade >= 60
            and tempoServico >= 25):
            return True
        else:
            return False

daemon = Pyro4.Daemon.serveSimple({Servidor07RMI: 'Servidor07RMI', }, host="localhost", port=8000, ns=False, verbose=True)
ns = Pyro4.locateNS()
server = Servidor07RMI()
uri = daemon.register(server)
ns.register('serverRMI', uri)
print('Server ...')
daemon.requestLoop()